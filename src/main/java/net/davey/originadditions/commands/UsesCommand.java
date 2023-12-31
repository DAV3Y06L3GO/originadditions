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

public class UsesCommand {
    public UsesCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder<CommandSourceStack>) Commands.literal("uses").executes((context) -> {
            return uses((CommandSourceStack)context.getSource());
        }));
    }

    private int uses(CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayer();

        player.sendSystemMessage(Component.literal("You have " + ScoreboardUtils.getValue(Objective.USES, player) + " uses left!"));


        return 1;
    }
}
