package myujiku.magikazero;

import myujiku.magikazero.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MagikaZERO implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("magika-zero");

	@Override
	public void onInitialize() {
		ModItems.registerItems();
	}

}