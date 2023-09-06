package net.davey.originadditions.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab ORIGINADDITIONS_TAB = new CreativeModeTab("originadditions_tab") {
        @Override
        public ItemStack makeIcon() {
        return new ItemStack(ModItems.ZIRCON.get());
        }
    };
}
