package com.spectrobes.spectrobesmod.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.spectrobes.spectrobesmod.common.krawl.KrawlInfectionManager;
import com.spectrobes.spectrobesmod.common.save_data.KrawlNest;
import com.spectrobes.spectrobesmod.common.save_data.SpectrobesWorldSaveData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;

import java.util.function.Consumer;

/**
 * /spectrobes nest ... debug commands for krawl nests. Commands act on the nearest nest to the caller.
 */
public final class KrawlNestCommand {
    private KrawlNestCommand() {
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("spectrobes")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("nest")
                        .then(Commands.literal("list").executes(KrawlNestCommand::list))
                        .then(Commands.literal("info").executes(context -> withNearest(context, nest ->
                                reply(context, KrawlInfectionManager.describe(context.getSource().getLevel(), nest)))))
                        .then(Commands.literal("found").executes(KrawlNestCommand::found))
                        .then(Commands.literal("addbiomass")
                                .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0))
                                        .executes(context -> withNearest(context, nest -> {
                                            double amount = DoubleArgumentType.getDouble(context, "amount");
                                            KrawlInfectionManager.depositBiomass(context.getSource().getLevel(), nest, amount);
                                            reply(context, String.format("Added %.1f biomass", amount));
                                        }))))
                        .then(Commands.literal("activate").executes(context -> withNearest(context, nest -> {
                            KrawlInfectionManager.reactivate(context.getSource().getLevel(), nest);
                            reply(context, "Nest reactivated");
                        })))
                        .then(Commands.literal("deactivate").executes(context -> withNearest(context, nest -> {
                            KrawlInfectionManager.deactivate(context.getSource().getLevel(), nest);
                            reply(context, "Nest deactivated");
                        })))
                        .then(Commands.literal("delete").executes(context -> withNearest(context, nest -> {
                            SpectrobesWorldSaveData data = SpectrobesWorldSaveData.getWorldData(context.getSource().getLevel());
                            data.getNests().remove(nest);
                            data.setDirty();
                            reply(context, "Nest record deleted (blocks are left in place)");
                        })))));
    }

    private static int list(CommandContext<CommandSourceStack> context) {
        ServerLevel level = context.getSource().getLevel();
        SpectrobesWorldSaveData data = SpectrobesWorldSaveData.getWorldData(level);

        if (data.getNests().isEmpty()) {
            reply(context, "No krawl nests in this dimension");
            return 0;
        }

        for (KrawlNest nest : data.getNests()) {
            reply(context, KrawlInfectionManager.describe(level, nest));
        }

        return data.getNests().size();
    }

    private static int found(CommandContext<CommandSourceStack> context) {
        ServerLevel level = context.getSource().getLevel();
        BlockPos pos = BlockPos.containing(context.getSource().getPosition());
        KrawlNest nest = KrawlInfectionManager.foundNest(level, pos, 0);

        if (nest == null) {
            context.getSource().sendFailure(Component.literal("Could not found a nest here (another nest in range, or no solid ground and free space)"));
            return 0;
        }

        reply(context, "Founded nest " + nest.id);
        return 1;
    }

    private static int withNearest(CommandContext<CommandSourceStack> context, Consumer<KrawlNest> action) {
        ServerLevel level = context.getSource().getLevel();
        BlockPos pos = BlockPos.containing(context.getSource().getPosition());
        KrawlNest nest = SpectrobesWorldSaveData.getWorldData(level).findNearest(pos, Double.MAX_VALUE / 4, n -> true);

        if (nest == null) {
            context.getSource().sendFailure(Component.literal("No krawl nests in this dimension"));
            return 0;
        }

        action.accept(nest);
        return 1;
    }

    private static void reply(CommandContext<CommandSourceStack> context, String message) {
        context.getSource().sendSuccess(() -> Component.literal(message), false);
    }
}
