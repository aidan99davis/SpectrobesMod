package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.items.renderer.*;
import com.spectrobes.spectrobesmod.client.items.weapons.renderer.BasicSwordItemRenderer;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.items.fossils.*;
import com.spectrobes.spectrobesmod.common.items.machines.HealerBlockItem;
import com.spectrobes.spectrobesmod.common.items.weapons.BasicSwordItem;
import com.spectrobes.spectrobesmod.common.items.weapons.SpectrobesWeapon;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
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

    private static final List<Item> all_fossils = new ArrayList<>();
    private static final List<Item> flash_fossils = new ArrayList<>();
    private static final List<Item> aurora_fossils = new ArrayList<>();
    private static final List<Item> corona_fossils = new ArrayList<>();

    public static final RegistryObject<SpectrobesWeapon> basic_sword_item =
            ITEMS.register("basic_sword_item",
                    () -> new BasicSwordItem(new Item.Properties()
                            .setISTER(() -> BasicSwordItemRenderer::new)
                            .tab(SpectrobesItems.SpectrobesWeaponsItemGroup.Instance)));
    
    public static final RegistryObject<Item> metalium = 
    		ITEMS.register("metalium",
    				() -> new Item(new Item.Properties().tab(SpectrobesItems.SpectrobesMineralItemGroup.Instance)));
    
    public static final RegistryObject<Item> titanium = 
    		ITEMS.register("titanium",
    				() -> new Item(new Item.Properties().tab(SpectrobesItems.SpectrobesMineralItemGroup.Instance)));
    
    public static final RegistryObject<Item> marble = 
    		ITEMS.register("marble",
    				() -> new Item(new Item.Properties().tab(SpectrobesItems.SpectrobesMineralItemGroup.Instance)));

    @SuppressWarnings("unused")
    public static final RegistryObject<BlockItem> healer_block_item =
            ITEMS.register("healer_block_item",
                    () -> new HealerBlockItem(SpectrobesBlocks.healer_block.get(),
                            new Item.Properties()
                                    .setISTER(() -> HealerBlockItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<BlockItem> grilda_fossil_item =
            ITEMS.register("grilda_fossil_item",
                    () -> new GrildaFossilItem(SpectrobesBlocks.grilda_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> GrildaFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> gejio_fossil_item =
            ITEMS.register("gejio_fossil_item",
                    () -> new GejioFossilItem(SpectrobesBlocks.gejio_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> GejioFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> mesa_fossil_item =
            ITEMS.register("mesa_fossil_item",
                    () -> new MesaFossilItem(SpectrobesBlocks.mesa_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> MesaFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> aoi_fossil_item =
            ITEMS.register("aoi_fossil_item",
                    () -> new AoiFossilItem(SpectrobesBlocks.aoi_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> AoiFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> inkana_fossil_item =
            ITEMS.register("inkana_fossil_item",
                    () -> new InkanaFossilItem(SpectrobesBlocks.inkana_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> InkanaFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> bartor_fossil_item =
            ITEMS.register("bartor_fossil_item",
                    () -> new BartorFossilItem(SpectrobesBlocks.bartor_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> BartorFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));


    public static final RegistryObject<BlockItem> harumi_fossil_item =
            ITEMS.register("harumi_fossil_item",
                    () -> new HarumiFossilItem(SpectrobesBlocks.harumi_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> HarumiFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> komainu_fossil_item =
            ITEMS.register("komainu_fossil_item",
                    () -> new KomainuFossilItem(SpectrobesBlocks.komainu_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> KomainuFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> dongor_fossil_item =
            ITEMS.register("dongor_fossil_item",
                    () -> new DongorFossilItem(SpectrobesBlocks.dongor_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> DongorFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> kubaku_fossil_item =
            ITEMS.register("kubaku_fossil_item",
                    () -> new KubakuFossilItem(SpectrobesBlocks.kubaku_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> KubakuFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> nagu_fossil_item =
            ITEMS.register("nagu_fossil_item",
                    () -> new NaguFossilItem(SpectrobesBlocks.nagu_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> NaguFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> samukabu_fossil_item =
            ITEMS.register("samukabu_fossil_item",
                    () -> new SamukabuFossilItem(SpectrobesBlocks.samukabu_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> SamukabuFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> segu_fossil_item =
            ITEMS.register("segu_fossil_item",
                    () -> new SeguFossilItem(SpectrobesBlocks.segu_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> SeguFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> shakin_fossil_item =
            ITEMS.register("shakin_fossil_item",
                    () -> new ShakinFossilItem(SpectrobesBlocks.shakin_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> ShakinFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> spiko_fossil_item =
            ITEMS.register("spiko_fossil_item",
                    () -> new SpikoFossilItem(SpectrobesBlocks.spiko_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> SpikoFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> vilar_fossil_item =
            ITEMS.register("vilar_fossil_item",
                    () -> new VilarFossilItem(SpectrobesBlocks.vilar_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> VilarFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> zoza_fossil_item =
            ITEMS.register("zoza_fossil_item",
                    () -> new ZozaFossilItem(SpectrobesBlocks.zoza_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> ZozaFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    public static final RegistryObject<BlockItem> mossari_fossil_item =
            ITEMS.register("mossari_fossil_item",
                    () -> new MossariFossilItem(SpectrobesBlocks.mossari_fossil.get(),
                            new Item.Properties()
                                    .setISTER(() -> MossariFossilItemRenderer::new)
                                    .tab(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));

    private static void init() {
        flash_fossils.add(harumi_fossil_item.get());
        flash_fossils.add(nagu_fossil_item.get());
        flash_fossils.add(aoi_fossil_item.get());
        flash_fossils.add(shakin_fossil_item.get());
        flash_fossils.add(samukabu_fossil_item.get());
        flash_fossils.add(mossari_fossil_item.get());

        aurora_fossils.add(segu_fossil_item.get());
        aurora_fossils.add(spiko_fossil_item.get());
        aurora_fossils.add(bartor_fossil_item.get());
        aurora_fossils.add(grilda_fossil_item.get());
        aurora_fossils.add(kubaku_fossil_item.get());
        aurora_fossils.add(gejio_fossil_item.get());

        corona_fossils.add(komainu_fossil_item.get());
        corona_fossils.add(vilar_fossil_item.get());
        corona_fossils.add(zoza_fossil_item.get());
        corona_fossils.add(mesa_fossil_item.get());
        corona_fossils.add(dongor_fossil_item.get());
        corona_fossils.add(inkana_fossil_item.get());

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
