package com.insanecraft.fabric;

import com.insanecraft.fabric.block.InsaneCraftBlockEntities;
import com.insanecraft.fabric.block.InsaneCraftBlocks;
import com.insanecraft.fabric.item.InsaneCraftItems;
import net.fabricmc.api.ModInitializer;

public class InsaneCraftFabricMod implements ModInitializer {
    public static final String MOD_ID = "insanecraft";

    @Override
    public void onInitialize() {
        InsaneCraftItems.register();
        InsaneCraftBlocks.register();
        InsaneCraftBlockEntities.register();
    }
}
