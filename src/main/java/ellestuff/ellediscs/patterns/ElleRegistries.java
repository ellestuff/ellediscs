package ellestuff.ellediscs.patterns;

import com.mojang.serialization.Lifecycle;
import ellestuff.ellediscs.ElleDiscs;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ElleRegistries {
    public static final RegistryKey<Registry<DiscPattern>> DISC_PATTERN_REGISTRY_KEY = RegistryKey.ofRegistry(new Identifier(ElleDiscs.MODID, "disc_pattern"));
    public static final SimpleRegistry<DiscPattern> DISC_PATTERN = FabricRegistryBuilder.createDefaulted(DISC_PATTERN_REGISTRY_KEY, new Identifier(ElleDiscs.MODID, "none"))
            .buildAndRegister();

    public static DiscPattern register(String name) {
        return Registry.register(DISC_PATTERN, new Identifier(ElleDiscs.MODID, name), new DiscPattern(name, ElleDiscs.MODID));
    }
}
