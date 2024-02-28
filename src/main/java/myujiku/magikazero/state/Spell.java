package myujiku.magikazero.state;

import myujiku.magikazero.spells.SpellModuleGroup;
import myujiku.magikazero.spells.SpellTarget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Spell {
    public String name = "";
    public String description = "";
    public SpellModuleGroup mainGroup;
    public HashMap<String, SpellModuleGroup> extraGroups;

    public Spell() {
        mainGroup = new SpellModuleGroup();
        extraGroups = new HashMap<>();
    }

    public Spell(String name, String description, SpellModuleGroup mainGroup, HashMap<String, SpellModuleGroup> extraGroups) {
        this.name = name;
        this.description = description;
        this.mainGroup = mainGroup;
        this.extraGroups = extraGroups;
    }

    public void cast(World world, PlayerEntity caster) {
        ArrayList<SpellTarget> targets = new ArrayList<>();
        HashMap<String, Double> manaSources = new HashMap<>();

        mainGroup.cast(world, caster, targets, manaSources, this);
    }

    public void castGroup(World world, PlayerEntity caster, ArrayList<SpellTarget> targets, HashMap<String, Double> manaSources, String group) {
        Optional.ofNullable(extraGroups.get(group)).ifPresentOrElse(
                spellModuleGroup -> spellModuleGroup.cast(world, caster, targets, manaSources, this),
                () -> caster.sendMessage(Text.of("Spell group with name `" + group + "` not found"))
        );
    }

    public NbtCompound asNbt() {
        NbtCompound nbt = new NbtCompound();

        nbt.putString("name", name);
        nbt.putString("description", description);
        nbt.put("main_group", mainGroup.asNbt());

        NbtCompound extraGroupsNbt = new NbtCompound();
        extraGroups.forEach((name, group) -> extraGroupsNbt.put(name, group.asNbt()));
        nbt.put("extra_groups", extraGroupsNbt);

        return nbt;
    }

    public static Spell fromNbt(NbtCompound nbt) {
        HashMap<String, SpellModuleGroup> extraGroups = new HashMap<>();
        NbtCompound extraGroupsNbt = nbt.getCompound("extra_groups");

        for (String key : extraGroupsNbt.getKeys()) {
            extraGroups.put(key, SpellModuleGroup.fromNbt(extraGroupsNbt.getCompound(key)));
        }

        return new Spell(
                nbt.getString("name"),
                nbt.getString("description"),
                SpellModuleGroup.fromNbt(nbt.getCompound("main_group")),
                extraGroups
        );
    }
}
