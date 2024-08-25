package ellestuff.ellediscs.patterns;

import ellestuff.ellediscs.ElleDiscs;
import net.minecraft.util.Identifier;

public class ElleDiscPatterns {
    public static DiscPattern getById(String id) {
        Identifier identifier = new Identifier(id);
        DiscPattern pattern = ElleRegistries.DISC_PATTERN.get(identifier);

        return pattern != null ? pattern : ElleRegistries.DISC_PATTERN.get(new Identifier(ElleDiscs.MODID, "none"));
    }

    public static DiscPattern getById(String namespace, String id) {
        return getById(namespace + ":" + id);
    }

    public static final DiscPattern NONE = ElleRegistries.register("none");
    public static final DiscPattern CENTER = ElleRegistries.register("center");
    public static final DiscPattern CRACKED = ElleRegistries.register("cracked");
    public static final DiscPattern DUAL = ElleRegistries.register("dual");
    public static final DiscPattern EDGES = ElleRegistries.register("edges");
    public static final DiscPattern GILDED = ElleRegistries.register("gilded");
    public static final DiscPattern GRADIENT = ElleRegistries.register("gradient");
    public static final DiscPattern SPIRAL = ElleRegistries.register("spiral");
    public static final DiscPattern STREAKED = ElleRegistries.register("streaked");
    public static final DiscPattern STRIPED = ElleRegistries.register("striped");
}
