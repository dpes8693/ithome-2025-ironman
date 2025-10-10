package file21;
//FireSpell.java
// 火焰魔法實現
public class FireSpell implements MagicSpell {
    private String spellName;
    private String target;
    private int damage;

    public FireSpell() {
        this.spellName = "烈焰風暴";
        this.damage = 100;
    }

    @Override
    public MagicSpell clone() {
        FireSpell cloned = new FireSpell();
        cloned.spellName = this.spellName;
        cloned.target = this.target;
        cloned.damage = this.damage;
        return cloned;
    }

    @Override
    public void cast() {
        System.out.println("施放 " + spellName + " 對 " + target + " 造成 " + damage + " 點火焰傷害！");
    }

    @Override
    public String getSpellName() {
        return spellName;
    }

    @Override
    public void setTarget(String target) {
        this.target = target;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
