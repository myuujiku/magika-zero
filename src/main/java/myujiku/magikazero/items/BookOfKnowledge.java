package myujiku.magikazero.items;

import myujiku.magikazero.MagikaZERO;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BookOfKnowledge extends Item {
    public record OpenUIPacket() {
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            return super.use(world, user, hand);
        }

        MagikaZERO.CHANNEL.serverHandle(user).send(new OpenUIPacket());

        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    public BookOfKnowledge(Settings settings) {
        super(settings);
    }
}
