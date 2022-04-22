package committee.nova.dacron.common.sound.init;

import committee.nova.dacron.Dacron;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class SoundEventInit {
    public static final String[] names = new String[]{
            "item.armor.equip_netherite", "block.smithing_table.use", "block.ancient_debris.break", "block.ancient_debris.step", "block.ancient_debris.place",
            "block.ancient_debris.hit", "block.ancient_debris.fall", "block.netherite_block.break", "block.netherite_block.step", "block.netherite_block.place",
            "block.netherite_block.hit", "block.netherite_block.fall"
    };
    public static final HashMap<String, SoundEvent> soundList = new HashMap<>();

    public static void init() {
        putSound();
        soundList.forEach((id, sound) -> Registry.register(Registry.SOUND_EVENT, new Identifier(Dacron.MODID, id), sound));
    }

    public static void putSound() {
        for (final String name : names) soundList.put(name, sound(name));
    }

    public static SoundEvent sound(String id) {
        return new SoundEvent(new Identifier(Dacron.MODID, id));
    }

    public static SoundEvent getSound(int i) {
        return soundList.get(names[i]);
    }
}
