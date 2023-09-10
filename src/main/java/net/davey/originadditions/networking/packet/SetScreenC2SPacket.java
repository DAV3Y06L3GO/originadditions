package net.davey.originadditions.networking.packet;

import net.davey.originadditions.block.entity.RevivalPylonBlockEntity;
import net.davey.originadditions.screen.RevivalPylonScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;



public class SetScreenC2SPacket {

    private Minecraft minecraft;
    private BlockPos pPos;
    private Screen screen;

    public SetScreenC2SPacket(Minecraft minecraft, BlockPos pPos, Screen screen) {
        this.minecraft = minecraft;
        this.pPos = pPos;
        this.screen = screen;
    }

    public SetScreenC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!

            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            BlockEntity entity = level.getBlockEntity(pPos);

            if (entity instanceof RevivalPylonBlockEntity) {
                this.minecraft.setScreen(screen);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }

        });
        return true;
    }

}
