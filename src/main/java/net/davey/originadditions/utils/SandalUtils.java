package net.davey.originadditions.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import quantumxenon.origins_randomiser.enums.Objective;
import quantumxenon.origins_randomiser.utils.ScoreboardUtils;

public interface SandalUtils {
    static  void growObj(Objective obj, Player player) {
        ScoreboardUtils.setValue(obj, (ScoreboardUtils.getValue(obj, (ServerPlayer)player) + 1), (ServerPlayer)player);
        player.sendSystemMessage(Component.literal(String.format("WOWZA! YOU HAVE %s %s!!!!!!", ScoreboardUtils.getValue(obj, (ServerPlayer)player), obj)));
    }

    static void itemShrink(Player player, InteractionHand hand) {
        player.getItemInHand(hand).shrink(1);
    }
}
