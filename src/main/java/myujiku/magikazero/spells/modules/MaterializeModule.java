package myujiku.magikazero.spells.modules;

import myujiku.magikazero.items.ModItems;
import myujiku.magikazero.spells.SpellBranch;
import myujiku.magikazero.spells.SpellModule;
import myujiku.magikazero.spells.SpellTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Optional;

public class MaterializeModule implements SpellModule {
    @Override
    public Optional<SpellBranch> apply(World world, PlayerEntity caster, ArrayList<SpellTarget> targets, NbtCompound settings) {
        for (SpellTarget target : targets) {
            target.asPosition().ifPresent(position -> {
                ItemEntity entity = new ItemEntity(world, position.getX(), position.getY(), position.getZ(), ModItems.MAGICAL_SUBSTANCE.getDefaultStack());
                entity.setOwner(caster.getUuid());
                world.spawnEntity(entity);
            });
        }

        return Optional.empty();
    }
}
