package net.mutantfrogs.sandbox.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.potion.PotionUtil;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.mutantfrogs.sandbox.entity.ModEntities;
import net.mutantfrogs.sandbox.item.ModItems;

public class LightningInABottleEntity extends ThrownItemEntity {

    public LightningInABottleEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public LightningInABottleEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.LIGHTNING_IN_A_BOTTLE_ENTITY, livingEntity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.LIGHTNING_IN_A_BOTTLE;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        //summon lightning entity on collision
        World world = getWorld();
        Vec3d hitPos = hitResult.getPos();
        world.syncWorldEvent(WorldEvents.SPLASH_POTION_SPLASHED, this.getBlockPos(), 0xd5e4ec);
        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        lightning.setPosition(hitPos.x, hitPos.y, hitPos.z);
        world.spawnEntity(lightning);

        this.discard();
        super.onCollision(hitResult);
    }
}

