package com.insanecraft.fabric.block;

import com.insanecraft.fabric.InsaneCraftFabricMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public final class InsaneCraftBlocks {
    public static final Block ORE_UPGRADER = registerBlock(
        "ore_upgrader",
        new OreUpgraderBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.IRON_GRAY)
            .instrument(Instrument.IRON_XYLOPHONE)
            .strength(4.0f, 6.0f)
            .sounds(BlockSoundGroup.METAL)
            .requiresTool())
    );

    public static final Block OVERENCHANTMENT_TABLE = registerBlock(
        "overenchantment_table",
        new Block(AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE)
            .instrument(Instrument.IRON_XYLOPHONE)
            .strength(5.0f, 8.0f)
            .sounds(BlockSoundGroup.STONE)
            .requiresTool())
    );

    public static final Block XP_GENERATOR = registerBlock(
        "xp_generator",
        new XPGeneratorBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.EMERALD_GREEN)
            .instrument(Instrument.BASS)
            .strength(3.5f, 6.0f)
            .sounds(BlockSoundGroup.METAL)
            .requiresTool())
    );

    private InsaneCraftBlocks() {
    }

    public static void register() {
    }

    private static Block registerBlock(String name, Block block) {
        Identifier id = new Identifier(InsaneCraftFabricMod.MOD_ID, name);
        Block registered = Registry.register(Registries.BLOCK, id, block);
        Registry.register(
            Registries.ITEM,
            id,
            new BlockItem(registered, new FabricItemSettings())
        );
        return registered;
    }
}
