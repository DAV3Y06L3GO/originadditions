package net.davey.originadditions.events;

import net.davey.originadditions.OriginAdditions;
import net.davey.originadditions.commands.LivesCommand;
import net.davey.originadditions.commands.UsesCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber( modid = OriginAdditions.MOD_ID)
public class OriginAdditionsEvents {

    @SubscribeEvent
    public static void OnCommandsRegister(RegisterCommandsEvent event) {
        new UsesCommand(event.getDispatcher());
        new LivesCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}
