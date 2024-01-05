package net.mutantfrogs.sandbox.item.custom;

import net.minecraft.block.*;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.mutantfrogs.sandbox.Sandbox;

import static net.minecraft.block.HorizontalFacingBlock.FACING;


public class WrenchItem extends Item {
    public WrenchItem(Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos blockPos = context.getBlockPos();
        World world = context.getWorld();
        BlockState blockState = context.getWorld().getBlockState(blockPos);
        Direction direction = context.getSide();
        Sandbox.LOGGER.info("Direction" + direction);

        switch (direction){
            case NORTH: direction = Direction.WEST; break;
            case WEST: direction = Direction.SOUTH; break;
            case SOUTH: direction = Direction.EAST; break;
            case EAST: direction = Direction.NORTH; break;
            case UP, DOWN: return super.useOnBlock(context);
        }

        BlockState newState = blockState.with(FACING, direction);
        world.setBlockState(blockPos, newState, 3);
        return super.useOnBlock(context);
    }
}
