package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.items.renderer.*;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.items.fossils.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpectrobesItemsRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpectrobesInfo.MOD_ID);

    private static List<Item> all_fossils = new ArrayList<>();

    public static final RegistryObject<BlockItem> grilda_fossil_item =
            ITEMS.register("grilda_fossil_item",
                    () -> new GrildaFossilItem(SpectrobesBlocks.grilda_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> GrildaFossilItemRenderer::new)
                                    .group(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> harumi_fossil_item =
            ITEMS.register("harumi_fossil_item",
                    () -> new HarumiFossilItem(SpectrobesBlocks.harumi_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> HarumiFossilItemRenderer::new)
                                    .group(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> komainu_fossil_item =
            ITEMS.register("komainu_fossil_item",
                    () -> new KomainuFossilItem(SpectrobesBlocks.komainu_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> KomainuFossilItemRenderer::new)
                                    .group(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> kubaku_fossil_item =
            ITEMS.register("kubaku_fossil_item",
                    () -> new KubakuFossilItem(SpectrobesBlocks.kubaku_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> KubakuFossilItemRenderer::new)
                                    .group(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> nagu_fossil_item =
            ITEMS.register("nagu_fossil_item",
                    () -> new NaguFossilItem(SpectrobesBlocks.nagu_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> NaguFossilItemRenderer::new)
                                    .group(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> samukabu_fossil_item =
            ITEMS.register("samukabu_fossil_item",
                    () -> new SamukabuFossilItem(SpectrobesBlocks.samukabu_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> SamukabuFossilItemRenderer::new)
                                    .group(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> segu_fossil_item =
            ITEMS.register("segu_fossil_item",
                    () -> new SeguFossilItem(SpectrobesBlocks.segu_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> SeguFossilItemRenderer::new)
                                    .group(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> shakin_fossil_item =
            ITEMS.register("shakin_fossil_item",
                    () -> new ShakinFossilItem(SpectrobesBlocks.shakin_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> ShakinFossilItemRenderer::new)
                                    .group(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> spiko_fossil_item =
            ITEMS.register("spiko_fossil_item",
                    () -> new SpikoFossilItem(SpectrobesBlocks.spiko_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> SpikoFossilItemRenderer::new)
                                    .group(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> vilar_fossil_item =
            ITEMS.register("vilar_fossil_item",
                    () -> new VilarFossilItem(SpectrobesBlocks.vilar_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> VilarFossilItemRenderer::new)
                                    .group(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> zoza_fossil_item =
            ITEMS.register("zoza_fossil_item",
                    () -> new ZozaFossilItem(SpectrobesBlocks.zoza_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> ZozaFossilItemRenderer::new)
                                    .group(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    private static void init() {
        all_fossils.add(grilda_fossil_item.get());
        all_fossils.add(harumi_fossil_item.get());
        all_fossils.add(komainu_fossil_item.get());
        all_fossils.add(kubaku_fossil_item.get());
        all_fossils.add(nagu_fossil_item.get());
        all_fossils.add(samukabu_fossil_item.get());
        all_fossils.add(segu_fossil_item.get());
        all_fossils.add(shakin_fossil_item.get());
        all_fossils.add(vilar_fossil_item.get());
        all_fossils.add(zoza_fossil_item.get());
    }

    public static ItemStack getRandomFossil() {
        if(all_fossils.isEmpty()) {
            init();
        }

        Random random = new Random();
        int index = random.nextInt(all_fossils.size());
        return new ItemStack(all_fossils.get(index));
    }
}
