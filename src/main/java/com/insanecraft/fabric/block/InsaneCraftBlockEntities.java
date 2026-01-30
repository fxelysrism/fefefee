package com.insanecraft.fabric.block;

import com.insanecraft.fabric.InsaneCraftFabricMod;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class InsaneCraftBlockEntities {
    public static final BlockEntityType<XPGeneratorBlockEntity> XP_GENERATOR =
        Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(InsaneCraftFabricMod.MOD_ID, "xp_generator"),
            FabricBlockEntityTypeBuilder.create(XPGeneratorBlockEntity::new, InsaneCraftBlocks.XP_GENERATOR)
                .build()
        );

    private InsaneCraftBlockEntities() {
    }

    public static void register() {
    }
}
