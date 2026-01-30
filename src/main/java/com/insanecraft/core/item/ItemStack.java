package com.insanecraft.core.item;

import java.util.Objects;

/**
 * Minimal item stack representation for mod logic scaffolding.
 */
public final class ItemStack {
    private final String itemId;
    private final int count;

    public ItemStack(String itemId, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("count must be positive");
        }
        this.itemId = Objects.requireNonNull(itemId, "itemId");
        this.count = count;
    }

    public String getItemId() {
        return itemId;
    }

    public int getCount() {
        return count;
    }

    public ItemStack withCount(int newCount) {
        return new ItemStack(itemId, newCount);
    }

    @Override
    public String toString() {
        return itemId + " x" + count;
    }
}
