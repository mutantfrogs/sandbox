package net.mutantfrogs.sandbox.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mutantfrogs.sandbox.Sandbox;
import net.mutantfrogs.sandbox.block.custom.AmethystResonator;

public class ModBlocks {

    public static final Block QUARTZ_TILE = registerBlock("quartz_tile", new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK)));
    public static final Block AMETHYST_RESONATOR = registerBlock("amethyst_resonator", new AmethystResonator(FabricBlockSettings.copyOf(Blocks.AMETHYST_CLUSTER)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Sandbox.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, Identifier.of(Sandbox.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }
    public static void registerModBlocks(){
        Sandbox.LOGGER.info("Registering ModBlocks for" + Sandbox.MOD_ID);
    }

}
