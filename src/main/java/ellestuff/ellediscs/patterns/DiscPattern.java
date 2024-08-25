package ellestuff.ellediscs.patterns;

public class DiscPattern {
    private final String name;
    private final String namespace;
    private final String texturePath;

    public DiscPattern(String name, String namespace) {
        this.name = name;
        this.texturePath = namespace + ":item/disc_patterns/" + name;
        this.namespace = namespace;
    }

    public String getName() { return name; }
    public String getTexturePath() { return texturePath; }
    public String getTranslationKey() { return "discpattern." + namespace + "." + name; }
    public String getId() { return namespace + ":" + name; }
}
