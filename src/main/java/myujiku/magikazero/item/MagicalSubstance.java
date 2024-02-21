package myujiku.magikazero.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MagicalSubstance extends Item {
    public MagicalSubstance(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt() && stack.getNbt().contains("magika-zero.mana", NbtElement.INT_TYPE)) {
            int mana = stack.getNbt().getInt("magika-zero.mana");
            if (mana > 0) {
                tooltip.add(Text.translatable("item.magika-zero.magical_substance.tooltip", mana)
                        .formatted(Formatting.BLUE)
                        .formatted(Formatting.ITALIC));
            }
        }

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.hasNbt() && stack.getNbt().getInt("magika-zero.mana") > 0;
    }
}
