package net.davey.originadditions.item.custom;

import net.davey.originadditions.utils.SandalUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import quantumxenon.origins_randomiser.enums.Objective;

public class HeartContainerItem extends Item {
    public HeartContainerItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && player.isCrouching() && hand == InteractionHand.MAIN_HAND) {
            SandalUtils.growObj(Objective.LIVES, player);

        } else if (player.isCrouching()) {
            SandalUtils.itemShrink(player, hand);
        }


        return super.use(level, player, hand);
    }
}
