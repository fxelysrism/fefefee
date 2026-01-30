package com.insanecraft.fabric.registry;

import com.insanecraft.fabric.InsaneCraftFabricMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Registers InsaneCraft blocks and their corresponding items.
 */
public final class InsaneCraftBlocks {
    public static final Block OVERENCHANTMENT_TABLE = registerBlock(
        "overenchantment_table",
        new Block(FabricBlockSettings.copyOf(Blocks.ENCHANTING_TABLE))
    );
    public static final Block XP_GENERATOR = registerBlock(
        "xp_generator",
        new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK))
    );
    public static final Block RUBY_FORGE = registerBlock(
        "ruby_forge",
        new Block(FabricBlockSettings.copyOf(Blocks.ANVIL))
    );
    public static final Block MOONSTONE_RITUAL = registerBlock(
        "moonstone_ritual",
        new Block(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK))
    );
    public static final Block CELESTIAL_ALTAR = registerBlock(
        "celestial_altar",
        new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK))
    );
    public static final Block STORM_TOTEM = registerBlock(
        "storm_totem",
        new Block(FabricBlockSettings.copyOf(Blocks.LIGHTNING_ROD))
    );
    public static final Block VOID_HARVESTER = registerBlock(
        "void_harvester",
        new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN))
    );

    private InsaneCraftBlocks() {
    }

    public static void register() {
        // Intentionally empty. The static initializer registers all blocks.
    }

    private static Block registerBlock(String name, Block block) {
        Identifier id = new Identifier(InsaneCraftFabricMod.MOD_ID, name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new FabricItemSettings()));
        return block;
    }
}
