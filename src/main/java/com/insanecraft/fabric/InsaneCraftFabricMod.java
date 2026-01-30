package com.insanecraft.fabric;

import com.insanecraft.InsaneCraftMod;
import com.insanecraft.fabric.registry.InsaneCraftBlocks;
import com.insanecraft.fabric.registry.InsaneCraftItemGroups;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Fabric mod entrypoint that wires the core InsaneCraft systems.
 */
public final class InsaneCraftFabricMod implements ModInitializer {
    public static final String MOD_ID = "insanecraft";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        InsaneCraftBlocks.register();
        InsaneCraftItemGroups.register();
        InsaneCraftMod mod = new InsaneCraftMod();
        mod.initialize();
        LOGGER.info("InsaneCraft systems initialized and blocks registered.");
    }
}
