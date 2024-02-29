package myujiku.magikazero.spells;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public interface SpellModule {
    Optional<SpellBranch> apply(World world, PlayerEntity caster, ArrayList<SpellTarget> targets,
            HashMap<String, Double> manaSources, NbtCompound settings);

    double calculateManaCost(NbtCompound settings, ArrayList<SpellTarget> targets);

    // TODO: Make sure the original settings are not modified
    NbtCompound processSettings(NbtCompound settings, HashMap<String, Double> manaSources);
}
