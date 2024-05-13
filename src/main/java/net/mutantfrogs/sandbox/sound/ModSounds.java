package net.mutantfrogs.sandbox.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.mutantfrogs.sandbox.Sandbox;

public class ModSounds {
    public static final SoundEvent DROOPY = registerSoundEvent("droopy");
    public static final SoundEvent BRUH = registerSoundEvent("bruh");

    private static SoundEvent registerSoundEvent(String name){
        Identifier id = new Identifier(Sandbox.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds(){
        Sandbox.LOGGER.info("Registering Sounds for " + Sandbox.MOD_ID);
    }
}
