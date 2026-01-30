package com.insanecraft.fabric.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Base block that displays a simple interaction message when used.
 */
public class InteractiveBlock extends Block {
    private final String messageKey;

    public InteractiveBlock(Settings settings, String messageKey) {
        super(settings);
        this.messageKey = messageKey;
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
            player.sendMessage(Text.translatable(messageKey), true);
        }
        return ActionResult.SUCCESS;
    }
}
