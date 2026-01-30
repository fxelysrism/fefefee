package com.insanecraft.fabric.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Simple interactive block that grants a small amount of XP on use.
 */
public class XPGeneratorBlock extends InteractiveBlock {
    public XPGeneratorBlock(Settings settings, String messageKey) {
        super(settings, messageKey);
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
        if (!world.isClient) {
            player.addExperienceLevels(1);
            player.sendMessage(Text.translatable("message.insanecraft.xp_generator_grant"), true);
        }
        return ActionResult.SUCCESS;
    }
}
