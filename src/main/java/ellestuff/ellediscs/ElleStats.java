package ellestuff.ellediscs;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.Stat;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

public class ElleStats {
	public static final Stat<Identifier> CLEAN_DISC_PART = registerStat(new Identifier(ElleDiscs.MODID, "clean_disc_part"), StatFormatter.DEFAULT);
	public static final Stat<Identifier> DISC_SEPARATE = registerStat(new Identifier(ElleDiscs.MODID, "disc_separate"), StatFormatter.DEFAULT);

	public static Stat<Identifier> registerStat(Identifier id, StatFormatter formatter) {
		Registry.register(Registries.CUSTOM_STAT, id.getPath(), id);
		return Stats.CUSTOM.getOrCreateStat(id, formatter);
	}

	public static void registerElleStats() {}
}
