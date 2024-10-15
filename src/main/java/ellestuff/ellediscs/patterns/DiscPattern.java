package ellestuff.ellediscs.patterns;

import net.minecraft.util.Identifier;

public class DiscPattern {
    private final Identifier identifier;
    private final String texturePath;

    public DiscPattern(Identifier identifier) {
        this.identifier = identifier;
        this.texturePath = identifier.getNamespace() + ":item/disc_patterns/" + identifier.getPath();
    }

    public String getName() { return identifier.getPath(); }
    public String getTexturePath() { return texturePath; }
    public String getTranslationKey() { return identifier.toTranslationKey("discpattern"); }
    public Identifier getIdentifier() { return identifier; }
}
