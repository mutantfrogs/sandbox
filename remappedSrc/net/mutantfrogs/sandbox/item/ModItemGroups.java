package net.mutantfrogs.sandbox.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.mutantfrogs.sandbox.Sandbox;
import net.mutantfrogs.sandbox.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup SANDBOX_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Sandbox.MOD_ID, "ruby"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.sandbox"))
                    .icon(() -> new ItemStack(Items.SAND)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.QUARTZ_TILE);
                        entries.add(ModBlocks.AMETHYST_RESONATOR);
                        entries.add(ModItems.HANDCANNON);
                        entries.add(ModItems.LIGHTNING_IN_A_BOTTLE);
                        entries.add(ModItems.WRENCH);
                        //entries.add(ModItems.DROOPY);

                    }).build());

    public static void registerItemGroups() {
        Sandbox.LOGGER.info("Registering Item Groups for " + Sandbox.MOD_ID);
    }
}
