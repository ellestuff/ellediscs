package ellestuff.ellediscs.patterns;

import net.minecraft.util.Identifier;

public class DiscPattern {
    private final String name;
    private final String namespace;
    private final String texturePath;

    public DiscPattern(String name, String namespace) {
        this.name = name;
        this.texturePath = namespace + ":textures/disc_patterns/" + name + ".png";
        this.namespace = namespace;
    }

    public String getName() { return name; }
    public String getTexturePath() { return texturePath; }
    public String getTranslationKey() { return "discpattern." + namespace + "." + name; }
}
