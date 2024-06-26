package net.mutantfrogs.sandbox.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mutantfrogs.sandbox.Sandbox;
import net.mutantfrogs.sandbox.entity.custom.LightningInABottleEntity;

public class ModEntities {
    public static final EntityType<LightningInABottleEntity> LIGHTNING_IN_A_BOTTLE_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Sandbox.MOD_ID, "lightning_in_a_bottle_entity"),
            FabricEntityTypeBuilder.<LightningInABottleEntity>create(SpawnGroup.MISC, LightningInABottleEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());

}