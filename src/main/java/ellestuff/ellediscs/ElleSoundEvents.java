package ellestuff.ellediscs;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ElleSoundEvents {
    public static final SoundEvent SILENCE = registerSoundEvent("silence");

    public static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(ElleDiscs.MODID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }


    public static void registerElleSounds() {

    }
}
