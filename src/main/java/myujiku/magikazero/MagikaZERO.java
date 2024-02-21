package myujiku.magikazero;

import io.wispforest.owo.network.OwoNetChannel;
import myujiku.magikazero.item.BookOfKnowledge;
import myujiku.magikazero.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MagikaZERO implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("magika-zero");
	public static final OwoNetChannel CHANNEL = OwoNetChannel.create(new Identifier("magika-zero", "main"));

	@Override
	public void onInitialize() {
		CHANNEL.registerClientboundDeferred(BookOfKnowledge.OpenUIPacket.class);
		ModItems.registerItems();
	}

}