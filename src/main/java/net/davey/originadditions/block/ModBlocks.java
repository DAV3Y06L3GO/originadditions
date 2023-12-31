package net.davey.originadditions.block;

import net.davey.originadditions.OriginAdditions;
import net.davey.originadditions.block.custom.RevivalPylonBlock;
import net.davey.originadditions.item.ModCreativeModeTab;
import net.davey.originadditions.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OriginAdditions.MOD_ID);

    // creates new  zircon block
    public static final RegistryObject<Block> ZIRCON_BLOCK = registerBlock("zircon_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST).strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.ORIGINADDITIONS_TAB);
    public static final RegistryObject<Block> REVIVAL_PYLON = registerBlock("revival_pylon",
            () -> new RevivalPylonBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(1f).noOcclusion()), ModCreativeModeTab.ORIGINADDITIONS_TAB);


    // WTF IS THIS!!!!!!!!!!!!!!!!
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
