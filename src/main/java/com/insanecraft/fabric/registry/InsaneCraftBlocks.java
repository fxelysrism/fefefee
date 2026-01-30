package com.insanecraft.fabric.registry;

import com.insanecraft.fabric.InsaneCraftFabricMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import com.insanecraft.fabric.block.InteractiveBlock;
import com.insanecraft.fabric.block.XPGeneratorBlock;
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
        new InteractiveBlock(
            FabricBlockSettings.copyOf(Blocks.ENCHANTING_TABLE),
            "message.insanecraft.overenchantment_table"
        )
    );
    public static final Block XP_GENERATOR = registerBlock(
        "xp_generator",
        new XPGeneratorBlock(
            FabricBlockSettings.copyOf(Blocks.IRON_BLOCK),
            "message.insanecraft.xp_generator"
        )
    );
    public static final Block RUBY_FORGE = registerBlock(
        "ruby_forge",
        new InteractiveBlock(
            FabricBlockSettings.copyOf(Blocks.ANVIL),
            "message.insanecraft.ruby_forge"
        )
    );
    public static final Block MOONSTONE_RITUAL = registerBlock(
        "moonstone_ritual",
        new InteractiveBlock(
            FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK),
            "message.insanecraft.moonstone_ritual"
        )
    );
    public static final Block CELESTIAL_ALTAR = registerBlock(
        "celestial_altar",
        new InteractiveBlock(
            FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK),
            "message.insanecraft.celestial_altar"
        )
    );
    public static final Block STORM_TOTEM = registerBlock(
        "storm_totem",
        new InteractiveBlock(
            FabricBlockSettings.copyOf(Blocks.LIGHTNING_ROD),
            "message.insanecraft.storm_totem"
        )
    );
    public static final Block VOID_HARVESTER = registerBlock(
        "void_harvester",
        new InteractiveBlock(
            FabricBlockSettings.copyOf(Blocks.OBSIDIAN),
            "message.insanecraft.void_harvester"
        )
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
