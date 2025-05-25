package ellestuff.ellediscs.patterns;

import net.minecraft.util.Identifier;

public class DiscPattern {
    private final Identifier identifier;
    private final Identifier modelId;

    public DiscPattern(Identifier identifier) {
        this.identifier = identifier;
        this.modelId = new Identifier(identifier.getNamespace(), "disc_patterns/" + identifier.getPath());
    }

    public String getName() { return identifier.getPath(); }
    public Identifier getModelId() { return modelId; }
    public String getTranslationKey() { return identifier.toTranslationKey("discpattern"); }
    public Identifier getIdentifier() { return identifier; }
}
