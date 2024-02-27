package myujiku.magikazero.state;

import myujiku.magikazero.MagikaZERO;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.UUID;

public class CustomState extends PersistentState {
    public HashMap<UUID, PlayerData> players = new HashMap<>();

    private static Type<CustomState> type = new Type<>(
            CustomState::new,
            CustomState::fromNbt,
            null
    );

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtCompound playerNbt = new NbtCompound();

        players.forEach((uuid, data) -> {
            playerNbt.put(uuid.toString(), data.asNbt());
        });

        nbt.put("players", playerNbt);

        return nbt;
    }

    public static CustomState fromNbt(NbtCompound nbt) {
        CustomState state = new CustomState();

        NbtCompound playerNbt = nbt.getCompound("players");
        playerNbt.getKeys().forEach(key -> {
            state.players.put(UUID.fromString(key), PlayerData.fromNbt(playerNbt.getCompound(key)));
        });

        return state;
    }

    public static CustomState serverState(MinecraftServer server) {
        PersistentStateManager persistentStateManager = server.getWorld(World.OVERWORLD).getPersistentStateManager();

        CustomState state = persistentStateManager.getOrCreate(type, MagikaZERO.MOD_ID);
        state.markDirty();

        return state;
    }

    public static PlayerData playerState(LivingEntity player) {
        CustomState serverState = serverState(player.getWorld().getServer());

        return serverState.players.computeIfAbsent(player.getUuid(), uuid -> new PlayerData());
    }
}
