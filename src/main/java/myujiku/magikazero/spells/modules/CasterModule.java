package myujiku.magikazero.spells.modules;

import myujiku.magikazero.spells.SpellBranch;
import myujiku.magikazero.spells.SpellModule;
import myujiku.magikazero.spells.SpellTarget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class CasterModule implements SpellModule {
    @Override
    public Optional<SpellBranch> apply(World world, PlayerEntity caster, ArrayList<SpellTarget> targets, HashMap<String, Double> manaSources, NbtCompound settings) {
        targets.add(SpellTarget.entity(caster));

        return Optional.empty();
    }

    @Override
    public double calculateManaCost(NbtCompound settings, ArrayList<SpellTarget> targets) {
        return 0;
    }

    @Override
    public NbtCompound processSettings(NbtCompound settings, HashMap<String, Double> manaSources) {
        return settings;
    }
}
