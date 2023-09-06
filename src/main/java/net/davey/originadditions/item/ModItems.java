package net.davey.originadditions.item;

import net.davey.originadditions.OriginAdditions;
import net.davey.originadditions.item.custom.EightBallItem;
import net.davey.originadditions.item.custom.HeartContainerItem;
import net.davey.originadditions.item.custom.RandomizeChargeItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OriginAdditions.MOD_ID);

    // adds Item named zircon and adds it to the creative tab
    public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ORIGINADDITIONS_TAB)));
    public static final RegistryObject<Item> EIGHT_BALL = ITEMS.register("eight_ball",
            () -> new EightBallItem(new Item.Properties().tab(ModCreativeModeTab.ORIGINADDITIONS_TAB)));
    public static final RegistryObject<Item> RANDOMIZE_CHARGE = ITEMS.register("randomize_charge",
            () -> new RandomizeChargeItem(new Item.Properties().tab(ModCreativeModeTab.ORIGINADDITIONS_TAB)));
    public static final RegistryObject<Item> RANDOMIZER_CORE = ITEMS.register("randomizer_core",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ORIGINADDITIONS_TAB)));

    public static final RegistryObject<Item> EMPTY_CHARM = ITEMS.register("empty_charm",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ORIGINADDITIONS_TAB)));
    public static final RegistryObject<Item> HEART_CONTAINER = ITEMS.register("heart_container",
            () -> new HeartContainerItem(new Item.Properties().tab(ModCreativeModeTab.ORIGINADDITIONS_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
