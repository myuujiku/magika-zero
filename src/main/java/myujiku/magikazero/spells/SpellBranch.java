package myujiku.magikazero.spells;

public class SpellBranch {
    public final boolean terminate;
    public final String targetBranch;

    public SpellBranch(String targetBranch, boolean terminate) {
        this.targetBranch = targetBranch;
        this.terminate = terminate;
    }
}
