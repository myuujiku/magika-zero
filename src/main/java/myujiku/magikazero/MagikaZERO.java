package myujiku.magikazero;

import io.wispforest.owo.network.OwoNetChannel;
import myujiku.magikazero.items.BookOfKnowledge;
import myujiku.magikazero.items.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MagikaZERO implements ModInitializer {
	public static final String MOD_ID = "magika-zero";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final OwoNetChannel CHANNEL = OwoNetChannel.create(new Identifier(MOD_ID, "main"));

	@Override
	public void onInitialize() {
		CHANNEL.registerClientboundDeferred(BookOfKnowledge.OpenUIPacket.class);
		ModItems.registerItems();
	}

}