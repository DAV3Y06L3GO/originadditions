package net.davey.originadditions.block.entity;

import net.davey.originadditions.OriginAdditions;
import net.davey.originadditions.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, OriginAdditions.MOD_ID);

    public static final RegistryObject<BlockEntityType<RevivalPylonBlockEntity>> REVIVAL_PYLON =
            BLOCK_ENTITIES.register("revival_pylon", () ->
                    BlockEntityType.Builder.of(RevivalPylonBlockEntity::new, ModBlocks.REVIVAL_PYLON.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
