package myujiku.magikazero;

import myujiku.magikazero.gui.BookOfKnowledgeUI;
import myujiku.magikazero.item.BookOfKnowledge;
import net.fabricmc.api.ClientModInitializer;

public class MagikaZEROClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		MagikaZERO.CHANNEL.registerClientbound(BookOfKnowledge.OpenUIPacket.class, ((message, access) -> {
			System.out.println("help");
			access.runtime().setScreen(new BookOfKnowledgeUI());
		}));
	}
}