package myujiku.magikazero.spells;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Position;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Function;

public class SpellTarget {
    @Nullable
    private Entity entity;
    @Nullable
    private Position position;
    @Nullable
    private Integer inventorySlot;

    private SpellTarget(@Nullable Entity entity, @Nullable Position position, @Nullable Integer inventorySlot) {
        this.entity = entity;
        this.position = position;
        this.inventorySlot = inventorySlot;
    }

    public static SpellTarget entity(Entity entity) {
        return new SpellTarget(entity, null, null);
    }

    public static SpellTarget position(Position position) {
        return new SpellTarget(null, position, null);
    }

    public static SpellTarget inventorySlot(Integer inventorySlot) {
        return new SpellTarget(null, null, inventorySlot);
    }

    public Optional<Entity> entity() {
        return Optional.ofNullable(entity);
    }

    public Optional<Position> position() {
        return Optional.ofNullable(position);
    }

    public Optional<Integer> inventorySlot() {
        return Optional.ofNullable(inventorySlot);
    }

    public Optional<Position> asPosition() {
        return Optional.ofNullable(match(
                Entity::getPos,
                position -> position,
                inventorySlot -> null
        ));
    }

    public <T> T match(
            Function<Entity, T> matchEntity,
            Function<Position, T> matchPosition,
            Function<Integer, T> matchInventorySlot
    ) {
        if (entity != null) {
            return matchEntity.apply(entity);
        }
        if (position != null) {
            return matchPosition.apply(position);
        }
        if (inventorySlot != null) {
            return matchInventorySlot.apply(inventorySlot);
        }
        throw new IllegalArgumentException("Invalid");
    }
}
