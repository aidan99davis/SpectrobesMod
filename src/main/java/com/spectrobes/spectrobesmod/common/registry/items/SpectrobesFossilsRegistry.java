package com.spectrobes.spectrobesmod.common.registry.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.items.renderer.*;
import com.spectrobes.spectrobesmod.common.items.fossils.*;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class SpectrobesFossilsRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, SpectrobesInfo.MOD_ID);

    private static final List<Item> all_fossils = new ArrayList<>();
    private static final List<Item> flash_fossils = new ArrayList<>();
    private static final List<Item> aurora_fossils = new ArrayList<>();
    private static final List<Item> corona_fossils = new ArrayList<>();

    public static final Supplier<BlockItem> grilda_fossil_item =
            ITEMS.register("grilda_fossil_item",
                    () -> new GrildaFossilItem(SpectrobesBlocks.grilda_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> danawa_fossil_item =
            ITEMS.register("danawa_fossil_item",
                    () -> new DanawaFossilItem(SpectrobesBlocks.danawa_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> gejio_fossil_item =
            ITEMS.register("gejio_fossil_item",
                    () -> new GejioFossilItem(SpectrobesBlocks.gejio_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> mesa_fossil_item =
            ITEMS.register("mesa_fossil_item",
                    () -> new MesaFossilItem(SpectrobesBlocks.mesa_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> aoi_fossil_item =
            ITEMS.register("aoi_fossil_item",
                    () -> new AoiFossilItem(SpectrobesBlocks.aoi_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> inkana_fossil_item =
            ITEMS.register("inkana_fossil_item",
                    () -> new InkanaFossilItem(SpectrobesBlocks.inkana_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> bartor_fossil_item =
            ITEMS.register("bartor_fossil_item",
                    () -> new BartorFossilItem(SpectrobesBlocks.bartor_fossil.get(),
                            new Item.Properties()));


    public static final Supplier<BlockItem> harumi_fossil_item =
            ITEMS.register("harumi_fossil_item",
                    () -> new HarumiFossilItem(SpectrobesBlocks.harumi_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> komainu_fossil_item =
            ITEMS.register("komainu_fossil_item",
                    () -> new KomainuFossilItem(SpectrobesBlocks.komainu_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> dongor_fossil_item =
            ITEMS.register("dongor_fossil_item",
                    () -> new DongorFossilItem(SpectrobesBlocks.dongor_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> kubaku_fossil_item =
            ITEMS.register("kubaku_fossil_item",
                    () -> new KubakuFossilItem(SpectrobesBlocks.kubaku_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> nagu_fossil_item =
            ITEMS.register("nagu_fossil_item",
                    () -> new NaguFossilItem(SpectrobesBlocks.nagu_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> samukabu_fossil_item =
            ITEMS.register("samukabu_fossil_item",
                    () -> new SamukabuFossilItem(SpectrobesBlocks.samukabu_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> segu_fossil_item =
            ITEMS.register("segu_fossil_item",
                    () -> new SeguFossilItem(SpectrobesBlocks.segu_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> shakin_fossil_item =
            ITEMS.register("shakin_fossil_item",
                    () -> new ShakinFossilItem(SpectrobesBlocks.shakin_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> spiko_fossil_item =
            ITEMS.register("spiko_fossil_item",
                    () -> new SpikoFossilItem(SpectrobesBlocks.spiko_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> vilar_fossil_item =
            ITEMS.register("vilar_fossil_item",
                    () -> new VilarFossilItem(SpectrobesBlocks.vilar_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> zoza_fossil_item =
            ITEMS.register("zoza_fossil_item",
                    () -> new ZozaFossilItem(SpectrobesBlocks.zoza_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> mossari_fossil_item =
            ITEMS.register("mossari_fossil_item",
                    () -> new MossariFossilItem(SpectrobesBlocks.mossari_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> masetto_fossil_item =
            ITEMS.register("masetto_fossil_item",
                    () -> new MasettoFossilItem(SpectrobesBlocks.masetto_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> tenkro_fossil_item =
            ITEMS.register("tenkro_fossil_item",
                    () -> new TenkroFossilItem(SpectrobesBlocks.tenkro_fossil.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> kasumi_fossil_item =
            ITEMS.register("kasumi_fossil_item",
                    () -> new KasumiFossilItem(SpectrobesBlocks.kasumi_fossil.get(),
                            new Item.Properties()));

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new AoiFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.aoi_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new BartorFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.bartor_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new DanawaFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.danawa_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new DongorFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.dongor_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new GejioFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.gejio_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new GrildaFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.grilda_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new HarumiFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.harumi_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new InkanaFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.inkana_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new KasumiFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.kasumi_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new KomainuFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.komainu_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new KubakuFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.kubaku_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new MasettoFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.masetto_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new MesaFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.mesa_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new MossariFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.mossari_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new NaguFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.nagu_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new SamukabuFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.samukabu_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new SeguFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.segu_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new ShakinFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.shakin_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new SpikoFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.spiko_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new TenkroFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.tenkro_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new VilarFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.vilar_fossil_item.get()
        );
        event.registerItem(
                new IClientItemExtensions() {
                    private final BlockEntityWithoutLevelRenderer renderer = new ZozaFossilItemRenderer();

                    @Override
                    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                        return renderer;
                    }
                },
                SpectrobesFossilsRegistry.zoza_fossil_item.get()
        );
    }

    private static void init() {
        flash_fossils.add(harumi_fossil_item.get());
        flash_fossils.add(nagu_fossil_item.get());
        flash_fossils.add(aoi_fossil_item.get());
        flash_fossils.add(shakin_fossil_item.get());
        flash_fossils.add(samukabu_fossil_item.get());
        flash_fossils.add(mossari_fossil_item.get());
        flash_fossils.add(kasumi_fossil_item.get());

        aurora_fossils.add(segu_fossil_item.get());
        aurora_fossils.add(spiko_fossil_item.get());
        aurora_fossils.add(bartor_fossil_item.get());
        aurora_fossils.add(grilda_fossil_item.get());
        aurora_fossils.add(kubaku_fossil_item.get());
        aurora_fossils.add(gejio_fossil_item.get());
        aurora_fossils.add(masetto_fossil_item.get());

        corona_fossils.add(komainu_fossil_item.get());
        corona_fossils.add(vilar_fossil_item.get());
        corona_fossils.add(zoza_fossil_item.get());
        corona_fossils.add(mesa_fossil_item.get());
        corona_fossils.add(dongor_fossil_item.get());
        corona_fossils.add(inkana_fossil_item.get());
        corona_fossils.add(danawa_fossil_item.get());
        corona_fossils.add(tenkro_fossil_item.get());

        all_fossils.addAll(corona_fossils);
        all_fossils.addAll(flash_fossils);
        all_fossils.addAll(aurora_fossils);
    }

    public static ItemStack getRandomFossil() {
        if(all_fossils.isEmpty()) {
            init();
        }

        Random random = new Random();
        int index = random.nextInt(all_fossils.size());
        return new ItemStack(all_fossils.get(index));
    }

    public static ItemStack getRandomFossil(SpectrobeProperties.Nature bias) {
        if(all_fossils.isEmpty()) {
            init();
        }

        int randInt = new Random().nextInt(10);
        if(randInt < 2) { // so that you still get slight variation on nature despite being in a specific biome.
            Random random = new Random();
            int index = random.nextInt(all_fossils.size());
            return new ItemStack(all_fossils.get(index));
        } else {
            int index;
            switch (bias) {
                case FLASH:
                    index = new Random().nextInt(flash_fossils.size());
                    return new ItemStack(flash_fossils.get(index));
                case AURORA:
                    index = new Random().nextInt(aurora_fossils.size());
                    return new ItemStack(aurora_fossils.get(index));
                case CORONA:
                    index = new Random().nextInt(corona_fossils.size());
                    return new ItemStack(corona_fossils.get(index));
                default:
                    index = new Random().nextInt(all_fossils.size());
                    return new ItemStack(all_fossils.get(index));
            }
        }
    }
}
