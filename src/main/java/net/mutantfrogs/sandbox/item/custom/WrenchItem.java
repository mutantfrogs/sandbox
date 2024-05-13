package net.mutantfrogs.sandbox.item.custom;

import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import static net.minecraft.block.HorizontalFacingBlock.FACING;
import static net.minecraft.state.property.Properties.AXIS;


public class WrenchItem extends Item {
    public WrenchItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos blockPos = context.getBlockPos();
        World world = context.getWorld();
        PlayerEntity user = context.getPlayer();
        BlockState blockState = context.getWorld().getBlockState(blockPos);

            // rotates logs, pillars, hay bales, etc.
            if(blockState.contains(AXIS)){
                Direction.Axis currentAxis = blockState.get(AXIS);
                Direction.Axis rotatedAxis = rotateAxis(currentAxis);

                BlockState newState = blockState.with(AXIS, rotatedAxis);
                world.setBlockState(blockPos, newState, 3);
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.PLAYERS, 1f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));

                return ActionResult.SUCCESS;
            }

            // rotates stairs, furnaces, anvils, terracotta, doors, buttons, etc.
            if (blockState.contains(FACING)) {
                Direction currentFacing = blockState.get(FACING);
                Direction rotatedFacing = rotateHorizontal(currentFacing);

                // additional logic for flipping stairs
                if(blockState.contains(Properties.BLOCK_HALF) && rotatedFacing == Direction.WEST){
                    BlockHalf halfState = blockState.get(Properties.BLOCK_HALF);
                    halfState = (halfState == BlockHalf.TOP) ? BlockHalf.BOTTOM : BlockHalf.TOP;
                    BlockState newHalfState = blockState.with(Properties.BLOCK_HALF, halfState).with(FACING, rotatedFacing);
                    world.setBlockState(blockPos, newHalfState, 3);
                    world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.PLAYERS, 1f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                    return ActionResult.SUCCESS;
                }

                BlockState newState = blockState.with(FACING, rotatedFacing);
                world.setBlockState(blockPos, newState, 3);
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.PLAYERS, 1f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));

                return ActionResult.SUCCESS;
            }

            // rotates observers, pistons, barrels, end rods, etc.
            if (blockState.contains(Properties.FACING)) {
                Direction currentFacing = blockState.get(Properties.FACING);
                Direction rotatedFacing = rotateAll(currentFacing);

                BlockState newState = blockState.with(Properties.FACING, rotatedFacing);
                world.setBlockState(blockPos, newState, 3);
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.PLAYERS, 1f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));

                return ActionResult.SUCCESS;
            }

            // rotates signs, banners, player heads
            if (blockState.contains(Properties.ROTATION)) {
                int currentFacing = blockState.get(Properties.ROTATION);
                int rotatedFacing = rotateRotation(currentFacing);

                BlockState newState = blockState.with(Properties.ROTATION, rotatedFacing);
                world.setBlockState(blockPos, newState, 3);
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.PLAYERS, 1f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));

                return ActionResult.SUCCESS;
            }

            // rotates slabs
            if (blockState.contains(Properties.SLAB_TYPE)) {
                SlabType currentType = blockState.get(Properties.SLAB_TYPE);
                SlabType rotatedType = rotateSlab(currentType);

                BlockState newState = blockState.with(Properties.SLAB_TYPE, rotatedType);
                world.setBlockState(blockPos, newState, 3);
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.PLAYERS, 1f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));

                return ActionResult.SUCCESS;
            }

            // rotates hoppers
            if (blockState.contains(Properties.HOPPER_FACING)) {
                Direction currentFacing = blockState.get(Properties.HOPPER_FACING);
                Direction rotatedFacing = rotateHopper(currentFacing);

                BlockState newState = blockState.with(Properties.HOPPER_FACING, rotatedFacing);
                world.setBlockState(blockPos, newState, 3);
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.PLAYERS, 1f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));

                return ActionResult.SUCCESS;
            }

        return super.useOnBlock(context);
    }

 /*   public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand){

        entity.kill();

        return ActionResult.PASS;
    }*/

    // rotateHorizontal is for blocks using FACING for orientation
    private Direction rotateHorizontal(Direction direction) {
        switch (direction) {
            case NORTH: return Direction.EAST;
            case EAST: return Direction.SOUTH;
            case SOUTH: return Direction.WEST;
            case WEST: return Direction.NORTH;
            default: return direction;
        }
    }

    // rotateALL is for blocks using Properties.FACING for orientation
    private Direction rotateAll(Direction direction) {
        switch (direction) {
            case NORTH: return Direction.EAST;
            case EAST: return Direction.SOUTH;
            case SOUTH: return Direction.WEST;
            case WEST: return Direction.UP;
            case UP: return Direction.DOWN;
            case DOWN: return Direction.NORTH;
            default: return direction;
        }
    }

    // rotateAxis is for blocks using Axis for orientation
    private Direction.Axis rotateAxis(Direction.Axis axis){
        switch(axis){
            case X: return Direction.Axis.Y;
            case Y: return Direction.Axis.Z;
            case Z: return Direction.Axis.X;
            default: return axis;
        }
    }

    // rotateRotation is for blocks using Axis for orientation
    private int rotateRotation(int rotation){
        rotation++;

        if(rotation == 16){
            rotation = 0;
        }

        return rotation;
    }

    // rotateHopper is for rotating hoppers
    public Direction rotateHopper(Direction facing) {
        switch(facing){
            case NORTH: return Direction.EAST;
            case EAST: return Direction.SOUTH;
            case SOUTH: return Direction.WEST;
            case WEST: return Direction.DOWN;
            case DOWN: return Direction.NORTH;
            default: return facing;
        }
    }

    // rotateSlab is for orientating slabs
    public SlabType rotateSlab(SlabType type) {
        switch(type){
            case TOP: return SlabType.BOTTOM;
            case BOTTOM: return SlabType.TOP;
            default: return type;
        }
    }
}
