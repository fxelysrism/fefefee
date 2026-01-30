package com.insanecraft.fabric.block;

import com.insanecraft.fabric.item.InsaneCraftItems;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class OreUpgraderBlock extends Block {
    private static final Map<Item, ItemStack> UPGRADE_RESULTS = new HashMap<>();

    static {
        UPGRADE_RESULTS.put(InsaneCraftItems.DIAMOND_BLOCK_WALL, new ItemStack(Items.DIAMOND_BLOCK));
        UPGRADE_RESULTS.put(InsaneCraftItems.IRON_GEODE, new ItemStack(Items.IRON_BLOCK, 4));
        UPGRADE_RESULTS.put(InsaneCraftItems.MOONSTONE, new ItemStack(Items.ENDER_EYE));
    }

    public OreUpgraderBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(
        BlockState state,
        World world,
        BlockPos pos,
        PlayerEntity player,
        Hand hand,
        BlockHitResult hit
    ) {
        ItemStack heldStack = player.getStackInHand(hand);
        if (heldStack.isEmpty()) {
            return ActionResult.PASS;
        }

        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        Item heldItem = heldStack.getItem();
        if (heldItem == Items.CRYING_OBSIDIAN) {
            return tryCraftOverenchantmentTable(world, player, heldStack);
        }

        ItemStack result = UPGRADE_RESULTS.get(heldItem);
        if (result == null) {
            player.sendMessage(Text.translatable("message.insanecraft.ore_upgrader.invalid"), true);
            return ActionResult.CONSUME;
        }

        heldStack.decrement(1);
        player.giveItemStack(result.copy());
        world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 0.8f, 1.2f);
        return ActionResult.CONSUME;
    }

    private ActionResult tryCraftOverenchantmentTable(World world, PlayerEntity player, ItemStack heldStack) {
        int requiredDiamonds = 4;
        if (player.getInventory().count(Items.DIAMOND_BLOCK) < requiredDiamonds) {
            player.sendMessage(Text.translatable("message.insanecraft.ore_upgrader.missing_diamonds"), true);
            return ActionResult.CONSUME;
        }

        heldStack.decrement(1);
        player.getInventory().remove(Items.DIAMOND_BLOCK, requiredDiamonds);
        player.giveItemStack(new ItemStack(InsaneCraftBlocks.OVERENCHANTMENT_TABLE));
        world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 0.9f, 1.0f);
        return ActionResult.CONSUME;
    }
}
