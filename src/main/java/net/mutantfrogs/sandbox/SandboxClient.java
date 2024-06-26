package net.mutantfrogs.sandbox;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.mutantfrogs.sandbox.entity.ModEntities;

public class SandboxClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.LIGHTNING_IN_A_BOTTLE_ENTITY, FlyingItemEntityRenderer::new);
    }
}
