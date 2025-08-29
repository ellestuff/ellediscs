package ellestuff.ellediscs;

import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import ellestuff.ellediscs.items.CustomDiscItem;

public interface ElleCauldronBehaviour {
	CauldronBehavior CLEAN_CUSTOM_DYEABLE_ITEM = (state, world, pos, player, hand, stack) -> {
		Item item = stack.getItem();
		if (!(item instanceof DyeableItem dyeableItem)) {
			return ActionResult.PASS;
		} else if (!dyeableItem.hasColor(stack)) {
			return ActionResult.PASS;
		} else {
			if (!world.isClient) {
				dyeableItem.removeColor(stack);
				player.incrementStat(ElleStats.CLEAN_DISC_PART);
				LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
			}

			return ActionResult.success(world.isClient);
		}
	};

	CauldronBehavior WASH_DISC = (state, world, pos, player, hand, stack) -> {
		if (!world.isClient) {

			// campfire code
			Inventory inventory = new SimpleInventory(stack);
			ItemStack output = world.getRecipeManager().getFirstMatch(ElleDiscs.CAULDRON_RECIPE_TYPE, inventory, world).map((recipe) -> recipe.craft(inventory, world.getRegistryManager())).orElse(stack);
			if (output.isItemEnabled(world.getEnabledFeatures())) {
				CustomDiscItem.dismantleHeldDisc(player,hand,output);
			}

			player.incrementStat(ElleStats.DISC_SEPARATE);
			LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
		}
		return ActionResult.success(world.isClient);
	};

	CauldronBehavior WASH_CUSTOM_DISC = (state, world, pos, player, hand, stack) -> {
		if (!world.isClient) {

			// campfire code
			Inventory inventory = new SimpleInventory(stack);
			ItemStack output = world.getRecipeManager().getFirstMatch(ElleDiscs.CAULDRON_RECIPE_TYPE, inventory, world).map((recipe) -> recipe.craft(inventory, world.getRegistryManager())).orElse(stack);
			if (output.isItemEnabled(world.getEnabledFeatures())) {
				CustomDiscItem.dismantleHeldDisc(player,hand,output);
			}

			player.incrementStat(ElleStats.DISC_SEPARATE);
			LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
		}
		return ActionResult.success(world.isClient);
	};
}
