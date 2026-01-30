package com.insanecraft.fabric.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
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
import org.jetbrains.annotations.Nullable;

public class XPGeneratorBlock extends BlockWithEntity {
    private static final int[] XP_OUTPUT = {1, 3, 5, 8, 12};

    public XPGeneratorBlock(Settings settings) {
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
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        BlockEntity entity = world.getBlockEntity(pos);
        if (!(entity instanceof XPGeneratorBlockEntity generator)) {
            return ActionResult.PASS;
        }

        ItemStack heldStack = player.getStackInHand(hand);
        if (heldStack.isOf(Items.EXPERIENCE_BOTTLE)) {
            if (generator.tryUpgrade()) {
                heldStack.decrement(1);
                playUpgradeSound(world, pos);
                player.sendMessage(
                    Text.translatable("message.insanecraft.xp_generator.upgraded", generator.getLevel()),
                    true
                );
            } else {
                player.sendMessage(Text.translatable("message.insanecraft.xp_generator.max"), true);
            }
            return ActionResult.CONSUME;
        }

        int xp = XP_OUTPUT[Math.min(generator.getLevel() - 1, XP_OUTPUT.length - 1)];
        player.addExperience(xp);
        playXpSound(world, pos);
        player.sendMessage(
            Text.translatable("message.insanecraft.xp_generator.collect", xp, generator.getLevel()),
            true
        );
        return ActionResult.CONSUME;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new XPGeneratorBlockEntity(pos, state);
    }

    private void playUpgradeSound(World world, BlockPos pos) {
        world.playSound(null, pos, SoundEvents.BLOCK_BEACON_POWER_SELECT, SoundCategory.BLOCKS, 0.8f, 1.1f);
    }

    private void playXpSound(World world, BlockPos pos) {
        world.playSound(null, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.BLOCKS, 0.6f, 1.2f);
    }
}
