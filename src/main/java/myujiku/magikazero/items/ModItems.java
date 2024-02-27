package myujiku.magikazero.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item BOOK_OF_KNOWLEDGE = new BookOfKnowledge(new FabricItemSettings().maxCount(1));
    public static final Item MAGICAL_SUBSTANCE = new MagicalSubstance(new FabricItemSettings().maxCount(1));

    public static void registerItems() {
        registerItem(BOOK_OF_KNOWLEDGE, "book_of_knowledge", ItemGroups.TOOLS);
        registerItem(MAGICAL_SUBSTANCE, "magical_substance");
    }

    private static void registerItem(Item item, String path) {
        Registry.register(Registries.ITEM, new Identifier("magika-zero", path), item);
    }

    private static void registerItem(Item item, String path, RegistryKey<ItemGroup> group) {
        registerItem(item, path);
        ItemGroupEvents.modifyEntriesEvent(group).register(content -> {
                content.add(item);
        });
    }
}
