package com.insanecraft.core.player;

import com.insanecraft.core.item.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Minimal inventory model for demo purposes.
 */
public class PlayerInventory {
    private final Map<String, Integer> items = new HashMap<>();

    public void add(ItemStack stack) {
        items.merge(stack.getItemId(), stack.getCount(), Integer::sum);
    }

    public boolean has(ItemStack stack) {
        return items.getOrDefault(stack.getItemId(), 0) >= stack.getCount();
    }

    public boolean consumeAll(List<ItemStack> stacks) {
        for (ItemStack stack : stacks) {
            if (!has(stack)) {
                return false;
            }
        }
        for (ItemStack stack : stacks) {
            items.merge(stack.getItemId(), -stack.getCount(), Integer::sum);
        }
        return true;
    }

    public int getCount(String itemId) {
        return items.getOrDefault(itemId, 0);
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
