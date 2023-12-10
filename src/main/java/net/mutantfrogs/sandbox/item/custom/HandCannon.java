package net.mutantfrogs.sandbox.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import java.util.Random;

public class HandCannon extends Item {
    public HandCannon(Settings settings) {
        super(settings.maxDamage(5));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if(!world.isClient()) {
            FireballEntity fireball = new FireballEntity(world, user, 0,0,0, 1);

            Random random = new Random();
            Vec3d rotationVec = user.getRotationVec(0);
            int failChance = random.nextInt(4);

            if(failChance == 0){
                world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 1f, 1f);
                world.createExplosion(null, user.getX(), user.getY() + 1, user.getZ(), 1.0f, false, World.ExplosionSourceType.BLOCK);
                itemStack.damage(3, user, playerEntity -> playerEntity.sendToolBreakStatus(hand));
            }

            else {
                world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.HOSTILE, 1f, 1f);
                rotationVec = rotationVec.multiply(4);
                fireball.setVelocity(rotationVec);
                world.spawnEntity(fireball);
            }
        }

        itemStack.damage(1, user, playerEntity -> playerEntity.sendToolBreakStatus(hand));
        user.getItemCooldownManager().set(this, 40);
        return super.use(world, user, hand);
    }
}

