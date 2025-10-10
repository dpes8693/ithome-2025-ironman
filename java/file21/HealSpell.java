package file21;
//HealSpell.java
// 治療魔法實現
public class HealSpell implements MagicSpell {
    private String spellName;
    private String target;
    private int healAmount;

    public HealSpell() {
        this.spellName = "神聖治療";
        this.healAmount = 80;
    }

    @Override
    public MagicSpell clone() {
        HealSpell cloned = new HealSpell();
        cloned.spellName = this.spellName;
        cloned.target = this.target;
        cloned.healAmount = this.healAmount;
        return cloned;
    }

    @Override
    public void cast() {
        System.out.println("施放 " + spellName + " 對 " + target + " 回復 " + healAmount + " 點生命值！");
    }

    @Override
    public String getSpellName() {
        return spellName;
    }

    @Override
    public void setTarget(String target) {
        this.target = target;
    }

    public void setHealAmount(int amount) {
        this.healAmount = amount;
    }
}
