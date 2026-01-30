package com.insanecraft.core.ui;

import com.insanecraft.core.item.ItemStack;
import com.insanecraft.core.player.PlayerInventory;
import com.insanecraft.core.xp.XPGenerator;
import com.insanecraft.core.xp.XPGeneratorState;

import java.util.ArrayList;
import java.util.List;

/**
 * UI helper for the XP Generator block.
 */
public class XPGeneratorUI {
    private static final String XP_BOTTLE_ITEM = "xp_bottle";
    private static final int XP_PER_BOTTLE = 5;

    private final XPGenerator generator;

    public XPGeneratorUI(XPGenerator generator) {
        this.generator = generator;
    }

    public UiPanel render(XPGeneratorState state, PlayerInventory inventory) {
        List<UiLine> lines = new ArrayList<>();
        lines.add(new UiLine("Level", String.valueOf(state.getLevel())));
        lines.add(new UiLine("Stored XP", String.valueOf(state.getStoredXp())));
        lines.add(new UiLine("Output Tier", generator.getOutputForLevel(state.getLevel())));
        lines.add(new UiLine("XP Bottles", String.valueOf(inventory.getCount(XP_BOTTLE_ITEM))));
        lines.add(new UiLine("XP To Next", String.valueOf(generator.getRequiredXpForNextLevel(state.getLevel()))));
        return new UiPanel(
            "XP Generator",
            "Feed XP bottles to upgrade the generator and harvest stored XP.",
            lines,
            List.of("Upgrade", "Collect XP")
        );
    }

    public UiActionResult<XPGeneratorState> upgrade(int bottleCount, PlayerInventory inventory, XPGeneratorState state) {
        if (bottleCount <= 0) {
            return UiActionResult.failure("No XP bottles provided.");
        }
        if (!inventory.consumeAll(List.of(new ItemStack(XP_BOTTLE_ITEM, bottleCount)))) {
            return UiActionResult.failure("Not enough XP bottles in inventory.");
        }
        int upgrades = 0;
        for (int i = 0; i < bottleCount; i++) {
            int requiredXp = generator.getRequiredXpForNextLevel(state.getLevel());
            boolean upgraded = state.tryUpgrade(XP_PER_BOTTLE, requiredXp, generator.getMaxLevel());
            if (upgraded) {
                upgrades++;
            }
        }
        String message = upgrades > 0
            ? "Generator upgraded to level " + state.getLevel() + "."
            : "XP stored. Current level " + state.getLevel() + ".";
        return UiActionResult.success(message, state);
    }

    public UiActionResult<Integer> collectXp(XPGeneratorState state) {
        int xpYield = getXpYield(state.getLevel());
        return UiActionResult.successWithXp("Collected XP from generator.", xpYield, xpYield);
    }

    private int getXpYield(int level) {
        if (level >= 5) {
            return 40;
        }
        if (level >= 4) {
            return 25;
        }
        if (level >= 3) {
            return 15;
        }
        if (level >= 2) {
            return 8;
        }
        return 5;
    }
}
