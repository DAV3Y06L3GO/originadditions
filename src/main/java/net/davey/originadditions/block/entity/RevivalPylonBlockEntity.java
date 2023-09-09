package net.davey.originadditions.block.entity;

import net.davey.originadditions.item.ModItems;
import net.davey.originadditions.screen.RevivalPylonMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class RevivalPylonBlockEntity extends BlockEntity implements MenuProvider {

    protected final ContainerData data;

    public RevivalPylonBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.REVIVAL_PYLON.get(), p_155229_, p_155230_);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return 0;
            }

            @Override
            public void set(int pIndex, int pValue) {

            }

            @Override
            public int getCount() {
                return 0;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Revival Pylon");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new RevivalPylonMenu(id, inventory, this);

    }

    public static void tick(Level level, BlockPos pos, BlockState state, RevivalPylonBlockEntity revivalPylonBlockEntity) {
        if(level.isClientSide()) {
            return;

        }

    }

}
