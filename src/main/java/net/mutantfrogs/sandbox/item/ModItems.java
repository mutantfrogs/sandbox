package net.mutantfrogs.sandbox.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mutantfrogs.sandbox.Sandbox;
import net.mutantfrogs.sandbox.item.custom.HandCannon;

public class ModItems{

    //list of all items
    public static final Item HANDCANNON = registerItem("handcannon", new HandCannon(new FabricItemSettings()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Sandbox.MOD_ID, name), item);
    }
    public static void registerModItems(){
        Sandbox.LOGGER.info("Registering Mod Items for " + Sandbox.MOD_ID);
    }
}
