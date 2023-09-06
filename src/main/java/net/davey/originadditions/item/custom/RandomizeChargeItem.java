package net.davey.originadditions.item.custom;

import com.mojang.authlib.GameProfile;
import net.davey.originadditions.utils.SandalUtils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import quantumxenon.origins_randomiser.enums.Objective;
import quantumxenon.origins_randomiser.utils.ScoreboardUtils;

public class RandomizeChargeItem extends Item{
    public RandomizeChargeItem(Properties properties) { super(properties); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && player.isCrouching() && hand == InteractionHand.MAIN_HAND) {
            SandalUtils.growObj(Objective.USES, player);

        } else if (player.isCrouching()) {
                SandalUtils.itemShrink(player, hand);
            }
        return super.use(level, player, hand);
    }

}
