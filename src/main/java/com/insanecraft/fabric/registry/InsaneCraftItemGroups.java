package com.insanecraft.fabric.registry;

import com.insanecraft.fabric.InsaneCraftFabricMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * Registers InsaneCraft creative mode item groups.
 */
public final class InsaneCraftItemGroups {
    public static final ItemGroup INSANECRAFT_GROUP = FabricItemGroup.builder()
        .icon(() -> new ItemStack(InsaneCraftBlocks.OVERENCHANTMENT_TABLE))
        .displayName(Text.translatable("itemGroup.insanecraft"))
        .entries((context, entries) -> {
            entries.add(InsaneCraftBlocks.OVERENCHANTMENT_TABLE);
            entries.add(InsaneCraftBlocks.XP_GENERATOR);
            entries.add(InsaneCraftBlocks.RUBY_FORGE);
            entries.add(InsaneCraftBlocks.MOONSTONE_RITUAL);
            entries.add(InsaneCraftBlocks.CELESTIAL_ALTAR);
            entries.add(InsaneCraftBlocks.STORM_TOTEM);
            entries.add(InsaneCraftBlocks.VOID_HARVESTER);
        })
        .build();

    private InsaneCraftItemGroups() {
    }

    public static void register() {
        Registry.register(
            Registries.ITEM_GROUP,
            new Identifier(InsaneCraftFabricMod.MOD_ID, "insanecraft"),
            INSANECRAFT_GROUP
        );
    }
}
