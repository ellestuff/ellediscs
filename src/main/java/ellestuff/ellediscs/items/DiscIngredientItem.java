package ellestuff.ellediscs.items;

import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class DiscIngredientItem extends Item implements DyeableItem {
    int DEFAULT_COLOR;

    public DiscIngredientItem (int defaultColour, Item.Settings settings) {
        super(settings);
        DEFAULT_COLOR = defaultColour;
    }

    @Override
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("display");
        return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : DEFAULT_COLOR;
    }
}
