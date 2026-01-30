package com.insanecraft.fabric.item;

import com.insanecraft.fabric.InsaneCraftFabricMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class InsaneCraftItems {
    public static final Item DIAMOND_BLOCK_WALL = registerItem("diamond_block_wall", new Item(new FabricItemSettings()));
    public static final Item IRON_GEODE = registerItem("iron_geode", new Item(new FabricItemSettings()));
    public static final Item MOONSTONE = registerItem("moonstone", new Item(new FabricItemSettings()));

    private InsaneCraftItems() {
    }

    public static void register() {
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(InsaneCraftFabricMod.MOD_ID, name), item);
    }
}
