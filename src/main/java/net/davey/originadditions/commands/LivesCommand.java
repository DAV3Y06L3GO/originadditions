package net.davey.originadditions.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import quantumxenon.origins_randomiser.enums.Objective;
import quantumxenon.origins_randomiser.utils.ScoreboardUtils;

public class LivesCommand {
    public LivesCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder<CommandSourceStack>) Commands.literal("lives").executes((context) -> {
            return lives((CommandSourceStack)context.getSource());
        }));
    }

    private int lives(CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayer();

        player.sendSystemMessage(Component.literal("You have " + ScoreboardUtils.getValue(Objective.LIVES, player) + " lives left!"));


        return 1;
    }
}
