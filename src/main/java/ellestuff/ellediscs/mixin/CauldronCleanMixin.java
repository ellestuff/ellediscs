package ellestuff.ellediscs.mixin;

import ellestuff.ellediscs.ElleCauldronBehaviour;
import ellestuff.ellediscs.ElleStats;
import ellestuff.ellediscs.items.ElleItems;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static ellestuff.ellediscs.ElleCauldronBehaviour.*;
import static net.minecraft.block.cauldron.CauldronBehavior.*;

@Mixin(CauldronBehavior.class)
public interface CauldronCleanMixin {
	@Inject(method = "registerBehavior", at = @At("TAIL"))
	private static void registerDiscBehaviour(CallbackInfo ci) {
		WATER_CAULDRON_BEHAVIOR.put(ElleItems.DISC_RECORD, CLEAN_CUSTOM_DYEABLE_ITEM);
		WATER_CAULDRON_BEHAVIOR.put(ElleItems.DISC_LABEL, CLEAN_CUSTOM_DYEABLE_ITEM);
		WATER_CAULDRON_BEHAVIOR.put(ElleItems.LABEL_PATTERN, CLEAN_CUSTOM_DYEABLE_ITEM);

		WATER_CAULDRON_BEHAVIOR.put(Items.MUSIC_DISC_5, WASH_DISC);
		WATER_CAULDRON_BEHAVIOR.put(Items.MUSIC_DISC_11, WASH_DISC);
		WATER_CAULDRON_BEHAVIOR.put(Items.MUSIC_DISC_13, WASH_DISC);
		WATER_CAULDRON_BEHAVIOR.put(Items.MUSIC_DISC_BLOCKS, WASH_DISC);
		WATER_CAULDRON_BEHAVIOR.put(Items.MUSIC_DISC_CAT, WASH_DISC);
		WATER_CAULDRON_BEHAVIOR.put(Items.MUSIC_DISC_CHIRP, WASH_DISC);
		WATER_CAULDRON_BEHAVIOR.put(Items.MUSIC_DISC_FAR, WASH_DISC);
		WATER_CAULDRON_BEHAVIOR.put(Items.MUSIC_DISC_MALL, WASH_DISC);
		WATER_CAULDRON_BEHAVIOR.put(Items.MUSIC_DISC_MELLOHI, WASH_DISC);
		WATER_CAULDRON_BEHAVIOR.put(Items.MUSIC_DISC_OTHERSIDE, WASH_DISC);
		WATER_CAULDRON_BEHAVIOR.put(Items.MUSIC_DISC_PIGSTEP, WASH_DISC);
		WATER_CAULDRON_BEHAVIOR.put(Items.MUSIC_DISC_RELIC, WASH_DISC);
		WATER_CAULDRON_BEHAVIOR.put(Items.MUSIC_DISC_STAL, WASH_DISC);
		WATER_CAULDRON_BEHAVIOR.put(Items.MUSIC_DISC_STRAD, WASH_DISC);
		WATER_CAULDRON_BEHAVIOR.put(Items.MUSIC_DISC_WAIT, WASH_DISC);
		WATER_CAULDRON_BEHAVIOR.put(Items.MUSIC_DISC_WARD, WASH_DISC);

		// These aren't in 1.20.1 but I've added recipes for them.
		//WATER_CAULDRON_BEHAVIOR.put(Items.MUSIC_DISC_PRECIPICE, WASH_DISC);
	}
}
