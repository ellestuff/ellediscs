package ellestuff.ellediscs;

import net.minecraft.util.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ElleSoundEvents {
    public static final SoundEvent SILENCE = registerSoundEvent("silence");

    public static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(ElleDiscs.MODID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
}
