package myujiku.magikazero.spells;

import myujiku.magikazero.MagikaZERO;
import myujiku.magikazero.spells.modules.BranchModule;
import myujiku.magikazero.spells.modules.CasterModule;
import myujiku.magikazero.spells.modules.HurtModule;
import myujiku.magikazero.spells.modules.MaterializeModule;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Optional;

public final class SpellModuleRegistry {
    private static final HashMap<Identifier, SpellModule> REGISTRY = new HashMap<>();

    private SpellModuleRegistry() throws Exception {
        throw new Exception("SpellModuleRegistry cannot be instantiated");
    }

    public static boolean register(Identifier identifier, SpellModule module) {
        if (REGISTRY.containsKey(identifier)) {
            MagikaZERO.LOGGER.error("SpellRegistry: Duplicate registration of " + identifier.toString());

            return false;
        }

        if (REGISTRY.put(identifier, module) == null) {
            MagikaZERO.LOGGER.debug("SpellModuleRegistry: Registered " + identifier.toString());

            return true;
        }

        MagikaZERO.LOGGER.error("SpellRegistry: Invalid registration");
        return false;
    }

    public static Optional<SpellModule> get(Identifier identifier) {
        if (REGISTRY.containsKey(identifier)) {
            return Optional.of(REGISTRY.get(identifier));
        }

        return Optional.empty();
    }

    private static void registerDefault(String name, SpellModule module) {
        register(new Identifier("magika-zero", name), module);
    }

    public static void registerDefault() {
        registerDefault("branch", new BranchModule());
        registerDefault("caster", new CasterModule());
        registerDefault("materialize", new MaterializeModule());
        registerDefault("hurt", new HurtModule());
    }
}
