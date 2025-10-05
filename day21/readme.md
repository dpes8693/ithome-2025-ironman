# Day21 原型模式 (Prototype Pattern)

## 擬人化角色：【多變的幻影魔術師】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/20-Prototype.png)

- 種族： 人類/法師
- 外貌： 身穿華麗的燕尾服，頭戴高筒禮帽，舉手投足間充滿了神秘感。他周圍環繞著數個半透明的、與他一模一樣的幻影分身，每個分身都手持不同的魔法道具。
- 性格： 優雅、狡黠，善於複製和變形。他從不直接「創造」新的事物，而是利用現有的「原型」進行複製，再加以微調。
- 能力： 能夠複製一個物件，而非重新創建一個。他可以輕易地製造出自己的幻影分身，這些分身與他本人擁有相同的基本特徵，但可以被賦予不同的任務或能力。
- 代表語： 「複製，然後改變，何必從零開始？」
- 背景故事： 這位幻影魔術師是一位傳奇人物，他的魔法源於對「原型」的掌握。當他需要一個助手、一個守衛，甚至是一個誘餌時，他從不需要費力去召喚或創造，只需輕輕一揮手，就能從自己身上複製出一個分身。這些分身雖然源自他自己，但可以根據需要持有不同的武器，施放不同的法術。他用這種方式，在不耗費大量魔力的前提下，實現了高效的多元化操作。

---

## 範例

### Java

```java
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

### JavaScript

```javascript
// 魔法咒語基類
class MagicSpell {
  clone() {
    throw new Error("子類必須實現 clone 方法");
  }

  cast() {
    throw new Error("子類必須實現 cast 方法");
  }

  getSpellName() {
    throw new Error("子類必須實現 getSpellName 方法");
  }

  setTarget(target) {
    this.target = target;
  }
}

// 火焰魔法實現
class FireSpell extends MagicSpell {
  constructor() {
    super();
    this.spellName = "烈焰風暴";
    this.damage = 100;
  }

  clone() {
    const cloned = new FireSpell();
    cloned.spellName = this.spellName;
    cloned.target = this.target;
    cloned.damage = this.damage;
    return cloned;
  }

  cast() {
    console.log(
      `施放 ${this.spellName} 對 ${this.target} 造成 ${this.damage} 點火焰傷害！`
    );
  }

  getSpellName() {
    return this.spellName;
  }

  setDamage(damage) {
    this.damage = damage;
  }
}

// 冰霜魔法實現
class IceSpell extends MagicSpell {
  constructor() {
    super();
    this.spellName = "極地冰封";
    this.freezeDuration = 3;
  }

  clone() {
    const cloned = new IceSpell();
    cloned.spellName = this.spellName;
    cloned.target = this.target;
    cloned.freezeDuration = this.freezeDuration;
    return cloned;
  }

  cast() {
    console.log(
      `施放 ${this.spellName} 對 ${this.target} 冰封 ${this.freezeDuration} 秒！`
    );
  }

  getSpellName() {
    return this.spellName;
  }

  setFreezeDuration(duration) {
    this.freezeDuration = duration;
  }
}

// 治療魔法實現
class HealSpell extends MagicSpell {
  constructor() {
    super();
    this.spellName = "神聖治療";
    this.healAmount = 80;
  }

  clone() {
    const cloned = new HealSpell();
    cloned.spellName = this.spellName;
    cloned.target = this.target;
    cloned.healAmount = this.healAmount;
    return cloned;
  }

  cast() {
    console.log(
      `施放 ${this.spellName} 對 ${this.target} 回復 ${this.healAmount} 點生命值！`
    );
  }

  getSpellName() {
    return this.spellName;
  }

  setHealAmount(amount) {
    this.healAmount = amount;
  }
}

// 幻影魔術師類
class IllusionMagician {
  constructor() {
    // 初始化原型咒語
    this.fireSpellPrototype = new FireSpell();
    this.iceSpellPrototype = new IceSpell();
    this.healSpellPrototype = new HealSpell();

    console.log("幻影魔術師準備好了原型魔法！");
  }

  // 複製火焰魔法
  createFireSpell(target) {
    const spell = this.fireSpellPrototype.clone();
    spell.setTarget(target);
    console.log(`複製火焰魔法，目標: ${target}`);
    return spell;
  }

  // 複製冰霜魔法
  createIceSpell(target) {
    const spell = this.iceSpellPrototype.clone();
    spell.setTarget(target);
    console.log(`複製冰霜魔法，目標: ${target}`);
    return spell;
  }

  // 複製治療魔法
  createHealSpell(target) {
    const spell = this.healSpellPrototype.clone();
    spell.setTarget(target);
    console.log(`複製治療魔法，目標: ${target}`);
    return spell;
  }

  // 展示魔術師的能力
  showMagicSkills() {
    console.log("=== 幻影魔術師的魔法技能 ===");
    console.log(`1. ${this.fireSpellPrototype.getSpellName()} (火焰系)`);
    console.log(`2. ${this.iceSpellPrototype.getSpellName()} (冰霜系)`);
    console.log(`3. ${this.healSpellPrototype.getSpellName()} (治療系)`);
    console.log("複製，然後改變，何必從零開始？");
  }
}

// 使用範例
console.log("=== 歡迎來到幻影魔術師的表演 ===\n");

// 創建幻影魔術師
const magician = new IllusionMagician();
console.log("");

// 展示魔術師技能
magician.showMagicSkills();
console.log("");

// 複製不同的魔法攻擊不同目標
const fireSpell1 = magician.createFireSpell("哥布林");
const fireSpell2 = magician.createFireSpell("獸人戰士");
const iceSpell1 = magician.createIceSpell("巨龍");
const healSpell1 = magician.createHealSpell("戰友");

console.log("");

// 施放魔法
console.log("=== 魔法施放展示 ===");
fireSpell1.cast();
fireSpell2.cast();
iceSpell1.cast();
healSpell1.cast();

console.log("");

// 展示原型的獨立性
console.log("=== 驗證複製品的獨立性 ===");
if (fireSpell1 !== fireSpell2) {
  console.log("火焰魔法複製品是獨立的物件");
}

// 修改其中一個複製品不會影響其他的
fireSpell1.setDamage(150);
console.log("\n強化後的魔法:");
fireSpell1.cast();
console.log("原始版本:");
fireSpell2.cast();

/** output
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
```

## 小總結

Prototype 設計模式就像我們故事中的多變幻影魔術師，透過`複製現有物件`來創建新的實例，而不是重新建構

clone() 方法減少很多初始賦值的步驟

**核心特點：**

- **物件複製**：透過複製現有的原型物件來創建新實例
- **避免重新建構**：不需要重新執行複雜的初始化過程
- **獨立性**：複製出的物件是獨立的，修改不會影響原型或其他複製品
- **效能優化**：對於創建成本高昂的物件特別有效

**使用時機：**

- 物件的創建成本很高（ex: 需要從資料庫載入大量資料的物件）
- 需要創建大量相似但略有不同的物件（ex: 遊戲中的角色模板）
- 避免使用複雜的工廠階層結構（ex: 當產品類別層次複雜時）
- 在運行時動態地創建物件（ex: 圖形編輯器中的圖形物件）

**注意事項：**

- 需要實現複製方法（clone），要考慮深複製 vs 淺複製
- 複製包含其他物件的複合物件時要特別小心
- 在某些語言中需要處理複製的異常情況
- 適合用於物件創建比直接實例化更有效率的場景
