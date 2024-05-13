package net.mutantfrogs.sandbox.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.mutantfrogs.sandbox.block.custom.AmethystResonator;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NoteBlock.class)
public class ResonatorFunctionalityMixin {
    @Inject(method = "playNote", at = @At("TAIL"))

    private void getModdedSound(@Nullable Entity entity, BlockState state, World world, BlockPos pos, CallbackInfo info) {
        BlockState blockState = world.getBlockState(pos.up());
        if (blockState.getBlock() instanceof AmethystResonator) {
            LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            lightning.setPosition(pos.getX(), pos.getY(), pos.getZ());
            world.spawnEntity(lightning);
            //world.addSyncedBlockEvent(pos, (NoteBlock)(Object)this, 0, 0);
            //world.emitGameEvent(entity, GameEvent.ENTITY_ROAR, pos);
        }
    }
}
