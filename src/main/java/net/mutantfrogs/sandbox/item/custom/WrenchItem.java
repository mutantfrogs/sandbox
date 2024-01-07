package net.mutantfrogs.sandbox.item.custom;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.mutantfrogs.sandbox.Sandbox;

import static net.minecraft.block.HorizontalFacingBlock.FACING;
import static net.minecraft.state.property.Properties.AXIS;


public class WrenchItem extends Item {
    public WrenchItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            ItemStack itemStack = user.getStackInHand(hand);
            return TypedActionResult.success(itemStack);
        }
        return super.use(world, user, hand);
    }
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos blockPos = context.getBlockPos();
        World world = context.getWorld();
        PlayerEntity user = context.getPlayer();
        BlockState blockState = context.getWorld().getBlockState(blockPos);

            if(blockState.contains(AXIS)){
                Direction.Axis currentAxis = blockState.get(AXIS);
                Direction.Axis rotatedAxis = flipAxis(currentAxis);

                BlockState newState = blockState.with(AXIS, rotatedAxis);
                world.setBlockState(blockPos, newState, 3);
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.PLAYERS, 1f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                return ActionResult.SUCCESS;
            }

            if (blockState.contains(FACING)) {
                Direction currentFacing = blockState.get(FACING);
                Direction rotatedFacing = rotateHorizontalClockwise(currentFacing);

                BlockState newState = blockState.with(FACING, rotatedFacing);
                world.setBlockState(blockPos, newState, 3);
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.PLAYERS, 1f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                return ActionResult.SUCCESS;
            }

        return super.useOnBlock(context);
    }

    private Direction rotateHorizontalClockwise(Direction direction) {
        switch (direction) {
            case NORTH: return Direction.EAST;
            case EAST: return Direction.SOUTH;
            case SOUTH: return Direction.WEST;
            case WEST: return Direction.NORTH;
            default: return direction;
        }
    }

    private Direction.Axis flipAxis(Direction.Axis axis){
        switch(axis){
            case X: return Direction.Axis.Y;
            case Y: return Direction.Axis.Z;
            case Z: return Direction.Axis.X;
            default: return axis;
        }
    }
}
