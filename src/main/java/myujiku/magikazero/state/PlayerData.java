package myujiku.magikazero.state;

import net.minecraft.nbt.NbtCompound;

import java.util.HashMap;
import java.util.UUID;

public class PlayerData {
    public HashMap<UUID, Spell> spells = new HashMap<>();

    public void addSpell(Spell spell) {
        spells.put(UUID.randomUUID(), spell);
    }

    public void addSpell(Spell spell, UUID uuid) {
        spells.put(uuid, spell);
    }

    public NbtCompound asNbt() {
        NbtCompound nbt = new NbtCompound();

        spells.forEach(((uuid, spell) -> {
            nbt.put(uuid.toString(), spell.asNbt());
        }));

        return nbt;
    }

    public static PlayerData fromNbt(NbtCompound nbt) {
        PlayerData data = new PlayerData();

        nbt.getKeys().forEach(key -> {
            data.spells.put(UUID.fromString(key), Spell.fromNbt(nbt.getCompound(key)));
        });

        return data;
    }
}
