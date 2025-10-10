package file21;
//IllusionMagician.java
// 幻影魔術師類
public class IllusionMagician {
    private MagicSpell fireSpellPrototype;
    private MagicSpell iceSpellPrototype;
    private MagicSpell healSpellPrototype;

    public IllusionMagician() {
        // 初始化原型咒語
        this.fireSpellPrototype = new FireSpell();
        this.iceSpellPrototype = new IceSpell();
        this.healSpellPrototype = new HealSpell();
        
        System.out.println("幻影魔術師準備好了原型魔法！");
    }

    // 複製火焰魔法
    public MagicSpell createFireSpell(String target) {
        MagicSpell spell = fireSpellPrototype.clone();
        spell.setTarget(target);
        System.out.println("複製火焰魔法，目標: " + target);
        return spell;
    }

    // 複製冰霜魔法
    public MagicSpell createIceSpell(String target) {
        MagicSpell spell = iceSpellPrototype.clone();
        spell.setTarget(target);
        System.out.println("複製冰霜魔法，目標: " + target);
        return spell;
    }

    // 複製治療魔法
    public MagicSpell createHealSpell(String target) {
        MagicSpell spell = healSpellPrototype.clone();
        spell.setTarget(target);
        System.out.println("複製治療魔法，目標: " + target);
        return spell;
    }

    // 展示魔術師的能力
    public void showMagicSkills() {
        System.out.println("=== 幻影魔術師的魔法技能 ===");
        System.out.println("1. " + fireSpellPrototype.getSpellName() + " (火焰系)");
        System.out.println("2. " + iceSpellPrototype.getSpellName() + " (冰霜系)");
        System.out.println("3. " + healSpellPrototype.getSpellName() + " (治療系)");
        System.out.println("複製，然後改變，何必從零開始？");
    }
}
