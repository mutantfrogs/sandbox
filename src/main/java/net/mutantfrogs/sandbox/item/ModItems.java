package net.mutantfrogs.sandbox.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.impl.itemgroup.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mutantfrogs.sandbox.Sandbox;
import net.mutantfrogs.sandbox.item.custom.HandCannon;

public class ModItems{

    public static final Item HANDCANNON = registerItem("handcannon", new HandCannon(new FabricItemSettings()));

    private static void addItemsToIngredientsGroup(FabricItemGroupEntries entries) {
        entries.add(HANDCANNON);
    }

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Sandbox.MOD_ID, name), item);
    }
    public static void registerModItems(){
        Sandbox.LOGGER.info("Registering Mod Items for " + Sandbox.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientsGroup);
    }
}
