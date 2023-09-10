package net.davey.originadditions.networking.packet;

import net.davey.originadditions.block.entity.RevivalPylonBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;



public class SetScreenS2CPacket {

    private Minecraft minecraft;
    private Screen screen;

    public SetScreenS2CPacket(Screen screen) {
        this.screen = screen;
    }

    public SetScreenS2CPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            minecraft = Minecraft.getInstance();

            minecraft.setScreen(screen);

        });
        return true;
    }

}
