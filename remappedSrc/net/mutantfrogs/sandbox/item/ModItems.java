package net.mutantfrogs.sandbox.item;

import net.minecraft.item.Item;
//import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mutantfrogs.sandbox.Sandbox;
import net.mutantfrogs.sandbox.item.custom.HandCannonItem;
import net.mutantfrogs.sandbox.item.custom.LightningInABottleItem;
import net.mutantfrogs.sandbox.item.custom.WrenchItem;
import net.mutantfrogs.sandbox.sound.ModSounds;

public class ModItems{

    //list of all items
    public static final Item HANDCANNON = registerItem("handcannon", new HandCannonItem(new Item.Settings()));
    //public static final Item DROOPY = registerItem("droopy", new MusicDiscItem(7, ModSounds.DROOPY, new Item.Settings().maxCount(1), 210));
    public static final Item LIGHTNING_IN_A_BOTTLE = registerItem("lightning_in_a_bottle", new LightningInABottleItem(new Item.Settings()));
    public static final Item WRENCH = registerItem("wrench", new WrenchItem(new Item.Settings().maxCount(1)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(Sandbox.MOD_ID, name), item);
    }
    public static void registerModItems(){
        Sandbox.LOGGER.info("Registering Items for " + Sandbox.MOD_ID);
    }
}
