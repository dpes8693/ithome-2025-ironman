package file21;
//IceSpell.java
// 冰霜魔法實現
public class IceSpell implements MagicSpell {
    private String spellName;
    private String target;
    private int freezeDuration;

    public IceSpell() {
        this.spellName = "極地冰封";
        this.freezeDuration = 3;
    }

    @Override
    public MagicSpell clone() {
        IceSpell cloned = new IceSpell();
        cloned.spellName = this.spellName;
        cloned.target = this.target;
        cloned.freezeDuration = this.freezeDuration;
        return cloned;
    }

    @Override
    public void cast() {
        System.out.println("施放 " + spellName + " 對 " + target + " 冰封 " + freezeDuration + " 秒！");
    }

    @Override
    public String getSpellName() {
        return spellName;
    }

    @Override
    public void setTarget(String target) {
        this.target = target;
    }

    public void setFreezeDuration(int duration) {
        this.freezeDuration = duration;
    }
}
