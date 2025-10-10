
```java
package file21;
//MagicSpell.java
// 魔法咒語介面
public interface MagicSpell {
    MagicSpell clone();
    void cast();
    String getSpellName();
    void setTarget(String target);
}
```

```java
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
```

```java
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
```

```java
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
```

```java
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
```

```java
package file21;
//PrototypeExample.java
// 使用範例
public class PrototypeExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到幻影魔術師的表演 ===\n");

        // 創建幻影魔術師
        IllusionMagician magician = new IllusionMagician();
        System.out.println();

        // 展示魔術師技能
        magician.showMagicSkills();
        System.out.println();

        // 複製不同的魔法攻擊不同目標
        MagicSpell fireSpell1 = magician.createFireSpell("哥布林");
        MagicSpell fireSpell2 = magician.createFireSpell("獸人戰士");
        MagicSpell iceSpell1 = magician.createIceSpell("巨龍");
        MagicSpell healSpell1 = magician.createHealSpell("戰友");

        System.out.println();

        // 施放魔法
        System.out.println("=== 魔法施放展示 ===");
        fireSpell1.cast();
        fireSpell2.cast();
        iceSpell1.cast();
        healSpell1.cast();

        System.out.println();

        // 展示原型的獨立性
        System.out.println("=== 驗證複製品的獨立性 ===");
        if (fireSpell1 != fireSpell2) {
            System.out.println("火焰魔法複製品是獨立的物件");
        }
        
        // 修改其中一個複製品不會影響其他的
        ((FireSpell) fireSpell1).setDamage(150);
        System.out.println("\n強化後的魔法:");
        fireSpell1.cast();
        System.out.println("原始版本:");
        fireSpell2.cast();

        /**output
        === 歡迎來到幻影魔術師的表演 ===

        幻影魔術師準備好了原型魔法！

        === 幻影魔術師的魔法技能 ===
        1. 烈焰風暴 (火焰系)
        2. 極地冰封 (冰霜系)
        3. 神聖治療 (治療系)
        複製，然後改變，何必從零開始？

        複製火焰魔法，目標: 哥布林
        複製火焰魔法，目標: 獸人戰士
        複製冰霜魔法，目標: 巨龍
        複製治療魔法，目標: 戰友

        === 魔法施放展示 ===
        施放 烈焰風暴 對 哥布林 造成 100 點火焰傷害！
        施放 烈焰風暴 對 獸人戰士 造成 100 點火焰傷害！
        施放 極地冰封 對 巨龍 冰封 3 秒！
        施放 神聖治療 對 戰友 回復 80 點生命值！

        === 驗證複製品的獨立性 ===
        火焰魔法複製品是獨立的物件

        強化後的魔法:
        施放 烈焰風暴 對 哥布林 造成 150 點火焰傷害！
        原始版本:
        施放 烈焰風暴 對 獸人戰士 造成 100 點火焰傷害！
        */
    }
}
```