package myujiku.magikazero.spells;

import myujiku.magikazero.state.Spell;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class SpellModuleGroup {
    public ArrayList<Identifier> moduleIDs;
    public ArrayList<NbtCompound> moduleSettings;

    public SpellModuleGroup() {
        moduleIDs = new ArrayList<>();
        moduleSettings = new ArrayList<>();
    }

    public SpellModuleGroup(int capacity) {
        moduleIDs = new ArrayList<>(capacity);
        moduleSettings = new ArrayList<>(capacity);
    }

    public void cast(World world, PlayerEntity caster, ArrayList<SpellTarget> targets, HashMap<String, Double> manaSources, Spell spell) {
        AtomicBoolean terminate = new AtomicBoolean(false); // IDK if I like this or not

        for (int i = 0; i < moduleIDs.size(); i++) {
            Identifier moduleID = moduleIDs.get(i);
            NbtCompound settings = moduleSettings.get(i);
            SpellModuleRegistry.get(moduleID).ifPresentOrElse(
                    module -> module.apply(world, caster, targets, manaSources, settings).ifPresent(spellBranch -> {
                        spell.castGroup(world, caster, targets, manaSources, spellBranch.targetBranch);
                        terminate.set(spellBranch.terminate);
                    }),
                    () -> caster.sendMessage(Text.of("Module with id " + moduleID + " not found"))
            );

            if (terminate.get()) {
                break;
            }
        }
    }

    public NbtCompound asNbt() {
        NbtCompound nbt = new NbtCompound();

        NbtCompound modulesNbt = new NbtCompound();
        for (int i = 0; i < moduleIDs.size(); i++) {
            modulesNbt.putString(i + "n", moduleIDs.get(i).getNamespace());
            modulesNbt.putString(i + "p", moduleIDs.get(i).getPath());
            modulesNbt.put(i + "s", moduleSettings.get(i));
        }

        nbt.putInt("modules_size", moduleIDs.size());
        nbt.put("modules", modulesNbt);

        return nbt;
    }

    public static SpellModuleGroup fromNbt(NbtCompound nbt) {
        int len = nbt.getInt("modules_size");

        SpellModuleGroup group = new SpellModuleGroup(len);

        NbtCompound modulesNbt = nbt.getCompound("modules");

        for (int i = 0; i < len; i++) {
            group.moduleIDs.add(Identifier.of(
                    modulesNbt.getString(i + "n"),
                    modulesNbt.getString(i + "p")
            ));
            group.moduleSettings.add(modulesNbt.getCompound(i + "s"));
        }

        return group;
    }
}
