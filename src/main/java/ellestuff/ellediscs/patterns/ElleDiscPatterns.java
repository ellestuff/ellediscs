package ellestuff.ellediscs.patterns;

import ellestuff.ellediscs.items.DiscPatternItem;
import ellestuff.ellediscs.items.ElleItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public class ElleDiscPatterns {
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

    public static final Item PATTERN_CENTER = ElleItems.registerItem("disc_pattern_center", new DiscPatternItem(CENTER, 0xffffff, new FabricItemSettings()));
    public static final Item PATTERN_CRACKED = ElleItems.registerItem("disc_pattern_cracked", new DiscPatternItem(CRACKED, 0xffffff, new FabricItemSettings()));
    public static final Item PATTERN_DUAL = ElleItems.registerItem("disc_pattern_dual", new DiscPatternItem(DUAL, 0xffffff, new FabricItemSettings()));
    public static final Item PATTERN_EDGES = ElleItems.registerItem("disc_pattern_edges", new DiscPatternItem(EDGES, 0xffffff, new FabricItemSettings()));
    public static final Item PATTERN_GILDED = ElleItems.registerItem("disc_pattern_gilded", new DiscPatternItem(GILDED, 0xffffff, new FabricItemSettings()));
    public static final Item PATTERN_GRADIENT = ElleItems.registerItem("disc_pattern_gradient", new DiscPatternItem(GRADIENT, 0xffffff, new FabricItemSettings()));
    public static final Item PATTERN_SPIRAL = ElleItems.registerItem("disc_pattern_spiral", new DiscPatternItem(SPIRAL, 0xffffff, new FabricItemSettings()));
    public static final Item PATTERN_STREAKED = ElleItems.registerItem("disc_pattern_streaked", new DiscPatternItem(STREAKED, 0xffffff, new FabricItemSettings()));
    public static final Item PATTERN_STRIPED = ElleItems.registerItem("disc_pattern_striped", new DiscPatternItem(STRIPED, 0xffffff, new FabricItemSettings()));
}
