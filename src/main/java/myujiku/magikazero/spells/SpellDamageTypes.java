package myujiku.magikazero.spells;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class SpellDamageTypes {
    public static final RegistryKey<DamageType> SPELL_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            new Identifier("magika-zero", "spell_damage"));
    public static final RegistryKey<DamageType> HEALTH_CONVERSION = RegistryKey.of(RegistryKeys.DAMAGE_TYPE,
            new Identifier("magika-zero", "health_conversion"));

    public static DamageSource of(World world, RegistryKey<DamageType> key, Entity caster) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key), caster);
    }
}
