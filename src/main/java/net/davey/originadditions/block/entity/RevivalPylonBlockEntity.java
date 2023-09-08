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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RevivalPylonBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress =0;
    private int maxProgress=78;


    public RevivalPylonBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.REVIVAL_PYLON.get(), p_155229_, p_155230_);
        this.data = new ContainerData() {
            @Override
            public int get(int p_39284_) {
                return switch (p_39284_) {
                    case 0 -> RevivalPylonBlockEntity.this.progress;
                    case 1 -> RevivalPylonBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> RevivalPylonBlockEntity.this.progress = value;
                    case 1 -> RevivalPylonBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
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
        return new RevivalPylonMenu(id, inventory, this, this.data);

    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.put("revival_pylon.progress", serializeNBT());

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));

        super.load(nbt);

        progress = nbt.getInt("revival_pylon.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, RevivalPylonBlockEntity revivalPylonBlockEntity) {
        if(level.isClientSide()) {
            return;
        }

        if(hasRecipe(revivalPylonBlockEntity)) {
            revivalPylonBlockEntity.progress++;
            setChanged(level, pos, state);

            if(revivalPylonBlockEntity.progress >= revivalPylonBlockEntity.maxProgress) {
                craftItem(revivalPylonBlockEntity);
            }
        } else {
            revivalPylonBlockEntity.resetProgress();
            setChanged(level, pos, state);
        }

    }

    private static boolean hasRecipe(RevivalPylonBlockEntity revivalPylonBlockEntity) {
        SimpleContainer inventory = new SimpleContainer(revivalPylonBlockEntity.itemHandler.getSlots());
        for (int i = 0; i < revivalPylonBlockEntity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, revivalPylonBlockEntity.itemHandler.getStackInSlot(i));
        }

        boolean hasRawGemInFirstSlot = revivalPylonBlockEntity.itemHandler.getStackInSlot(1).getItem() == Items.RAW_IRON.asItem();

        return hasRawGemInFirstSlot && canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory,
                new ItemStack(ModItems.ZIRCON.get(), 1));
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(2).getItem() == stack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static void craftItem(RevivalPylonBlockEntity revivalPylonBlockEntity) {

        if(hasRecipe(revivalPylonBlockEntity)) {
            revivalPylonBlockEntity.itemHandler.extractItem(1, 1, false);
            revivalPylonBlockEntity.itemHandler.setStackInSlot(2, new ItemStack(ModItems.ZIRCON.get(),
                    revivalPylonBlockEntity.itemHandler.getStackInSlot(2).getCount() + 1));

            revivalPylonBlockEntity.resetProgress();
        }

    }

    private void resetProgress() {
        this.progress = 0;
    }
}
