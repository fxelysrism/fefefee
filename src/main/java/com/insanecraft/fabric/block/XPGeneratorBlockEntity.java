package com.insanecraft.fabric.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class XPGeneratorBlockEntity extends BlockEntity {
    private static final int MAX_LEVEL = 5;
    private int level = 1;

    public XPGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(InsaneCraftBlockEntities.XP_GENERATOR, pos, state);
    }

    public int getLevel() {
        return level;
    }

    public boolean tryUpgrade() {
        if (level >= MAX_LEVEL) {
            return false;
        }
        level += 1;
        markDirty();
        if (world != null) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
        return true;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("Level", level);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        level = Math.max(1, Math.min(MAX_LEVEL, nbt.getInt("Level")));
    }
}
