package myujiku.magikazero.spells;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Optional;

public interface SpellModule {
    Optional<SpellBranch> apply(World world, PlayerEntity caster, ArrayList<SpellTarget> targets, NbtCompound settings);
}
