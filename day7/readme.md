# Day7 裝飾模式 (Decorator Pattern)

## 擬人化角色：【華麗的魔法師】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/6-Decorator.png)

- 種族： 半精靈
- 外貌： 一位身著華麗星空長袍的半精靈魔法師，他有著一頭飄逸的紫色長髮。他的長袍上鑲嵌著發光的魔法符文和肩甲，手中握著一根頂端有翅膀水晶的法杖，周圍環繞著閃爍的魔法光暈。他站在一個充滿魔法書籍的宏偉圖書館中。
- 性格： 創造、靈活、追求卓越。他不斷為已有的法術或裝備添加新的效果和功能，使其變得更強大、更多樣。
- 能力： 動態地將功能附加到物件上。他可以為一個簡單的火球術添加「追蹤」效果，為一個普通的防禦盾牌添加「反彈」屬性，或為一件長袍附加「飛行」能力。這些附加的功能可以在不改變原有物件結構的情況下，隨時增強其能力。
- 代表語： 「再平凡的魔法，也能因我的點綴而綻放光芒。」
- 背景故事： 這位半精靈魔法師以其對魔法的創新應用而聞名。他從不滿足於法術的原始形態。當一位戰士來請他為劍施法時，他不會重新打造一把劍，而是在原有的劍上施加「火焰附魔」、「破甲增強」或「吸血詛咒」等多種效果。同樣，當一位法師需要更強大的法術時，他會為現有的法術「裝飾」上更多變數和威力。他證明了無需從頭再來，只需巧妙「裝飾」，便能無限提升物件的價值和功能。

---

## 範例

### Java

```java
//Spell.java
// 法術介面
public interface Spell {
    void cast();
    String getDescription();
    int getPower();
}
```

```java
//BasicFireball.java
// 基礎火球術
public class BasicFireball implements Spell {
    @Override
    public void cast() {
        System.out.println("施展基礎火球術！");
    }

    @Override
    public String getDescription() {
        return "基礎火球術";
    }

    @Override
    public int getPower() {
        return 50;
    }
}
```

```java
//BasicShield.java
// 基礎防護盾
public class BasicShield implements Spell {
    @Override
    public void cast() {
        System.out.println("展開基礎防護盾！");
    }

    @Override
    public String getDescription() {
        return "基礎防護盾";
    }

    @Override
    public int getPower() {
        return 30;
    }
}
```

```java
//SpellDecorator.java
// 法術裝飾器抽象類
public abstract class SpellDecorator implements Spell {
    protected Spell spell;

    public SpellDecorator(Spell spell) {
        this.spell = spell;
    }

    @Override
    public void cast() {
        spell.cast();
    }

    @Override
    public String getDescription() {
        return spell.getDescription();
    }

    @Override
    public int getPower() {
        return spell.getPower();
    }
}
```

```java
//TrackingEnhancement.java
// 追蹤強化裝飾器
public class TrackingEnhancement extends SpellDecorator {
    public TrackingEnhancement(Spell spell) {
        super(spell);
    }

    @Override
    public void cast() {
        super.cast();
        System.out.println("→ 附加追蹤效果，法術會自動鎖定目標！");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + 追蹤強化";
    }

    @Override
    public int getPower() {
        return super.getPower() + 20;
    }
}
```

```java
//FireEnchantment.java
// 火焰附魔裝飾器
public class FireEnchantment extends SpellDecorator {
    public FireEnchantment(Spell spell) {
        super(spell);
    }

    @Override
    public void cast() {
        super.cast();
        System.out.println("→ 附加火焰附魔，造成額外灼燒傷害！");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + 火焰附魔";
    }

    @Override
    public int getPower() {
        return super.getPower() + 30;
    }
}
```

```java
//ReflectEnhancement.java
// 反彈強化裝飾器
public class ReflectEnhancement extends SpellDecorator {
    public ReflectEnhancement(Spell spell) {
        super(spell);
    }

    @Override
    public void cast() {
        super.cast();
        System.out.println("→ 附加反彈效果，可以反射敵人攻擊！");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + 反彈強化";
    }

    @Override
    public int getPower() {
        return super.getPower() + 25;
    }
}
```

```java
//DecoratorExample.java
// 使用範例
public class DecoratorExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到華麗魔法師的魔法工坊 ===\n");

        // 基礎法術
        System.out.println("--- 基礎法術展示 ---");
        Spell basicFireball = new BasicFireball();
        System.out.println("法術: " + basicFireball.getDescription());
        System.out.println("威力: " + basicFireball.getPower());
        basicFireball.cast();
        System.out.println();

        // 為火球術添加追蹤效果
        System.out.println("--- 魔法師開始施展裝飾魔法 ---");
        Spell trackingFireball = new TrackingEnhancement(basicFireball);
        System.out.println("法術: " + trackingFireball.getDescription());
        System.out.println("威力: " + trackingFireball.getPower());
        trackingFireball.cast();
        System.out.println();

        // 為火球術添加追蹤和火焰附魔雙重效果
        Spell superFireball = new FireEnchantment(new TrackingEnhancement(basicFireball));
        System.out.println("法術: " + superFireball.getDescription());
        System.out.println("威力: " + superFireball.getPower());
        superFireball.cast();
        System.out.println();

        // 為防護盾添加反彈效果
        System.out.println("--- 防護盾強化展示 ---");
        Spell basicShield = new BasicShield();
        Spell reflectShield = new ReflectEnhancement(basicShield);
        System.out.println("法術: " + reflectShield.getDescription());
        System.out.println("威力: " + reflectShield.getPower());
        reflectShield.cast();
        System.out.println();

        // 終極組合：火焰追蹤反彈防護盾
        Spell ultimateShield = new ReflectEnhancement(
            new FireEnchantment(
                new TrackingEnhancement(basicShield)
            )
        );
        System.out.println("--- 終極組合法術 ---");
        System.out.println("法術: " + ultimateShield.getDescription());
        System.out.println("威力: " + ultimateShield.getPower());
        ultimateShield.cast();

        /**output
        === 歡迎來到華麗魔法師的魔法工坊 ===

        --- 基礎法術展示 ---
        法術: 基礎火球術
        威力: 50
        施展基礎火球術！

        --- 魔法師開始施展裝飾魔法 ---
        法術: 基礎火球術 + 追蹤強化
        威力: 70
        施展基礎火球術！
        → 附加追蹤效果，法術會自動鎖定目標！

        法術: 基礎火球術 + 追蹤強化 + 火焰附魔
        威力: 100
        施展基礎火球術！
        → 附加追蹤效果，法術會自動鎖定目標！
        → 附加火焰附魔，造成額外灼燒傷害！

        --- 防護盾強化展示 ---
        法術: 基礎防護盾 + 反彈強化
        威力: 55
        展開基礎防護盾！
        → 附加反彈效果，可以反射敵人攻擊！

        --- 終極組合法術 ---
        法術: 基礎防護盾 + 追蹤強化 + 火焰附魔 + 反彈強化
        威力: 105
        展開基礎防護盾！
        → 附加追蹤效果，法術會自動鎖定目標！
        → 附加火焰附魔，造成額外灼燒傷害！
        → 附加反彈效果，可以反射敵人攻擊！
        */
    }
}
```

### JavaScript

```javascript
// 法術基類
class Spell {
  cast() {
    throw new Error("子類必須實現 cast 方法");
  }

  getDescription() {
    throw new Error("子類必須實現 getDescription 方法");
  }

  getPower() {
    throw new Error("子類必須實現 getPower 方法");
  }
}

// 基礎火球術
class BasicFireball extends Spell {
  cast() {
    console.log("施展基礎火球術！");
  }

  getDescription() {
    return "基礎火球術";
  }

  getPower() {
    return 50;
  }
}

// 基礎防護盾
class BasicShield extends Spell {
  cast() {
    console.log("展開基礎防護盾！");
  }

  getDescription() {
    return "基礎防護盾";
  }

  getPower() {
    return 30;
  }
}

// 法術裝飾器基類
class SpellDecorator extends Spell {
  constructor(spell) {
    super();
    this.spell = spell;
  }

  cast() {
    this.spell.cast();
  }

  getDescription() {
    return this.spell.getDescription();
  }

  getPower() {
    return this.spell.getPower();
  }
}

// 追蹤強化裝飾器
class TrackingEnhancement extends SpellDecorator {
  constructor(spell) {
    super(spell);
  }

  cast() {
    super.cast();
    console.log("→ 附加追蹤效果，法術會自動鎖定目標！");
  }

  getDescription() {
    return super.getDescription() + " + 追蹤強化";
  }

  getPower() {
    return super.getPower() + 20;
  }
}

// 火焰附魔裝飾器
class FireEnchantment extends SpellDecorator {
  constructor(spell) {
    super(spell);
  }

  cast() {
    super.cast();
    console.log("→ 附加火焰附魔，造成額外灼燒傷害！");
  }

  getDescription() {
    return super.getDescription() + " + 火焰附魔";
  }

  getPower() {
    return super.getPower() + 30;
  }
}

// 反彈強化裝飾器
class ReflectEnhancement extends SpellDecorator {
  constructor(spell) {
    super(spell);
  }

  cast() {
    super.cast();
    console.log("→ 附加反彈效果，可以反射敵人攻擊！");
  }

  getDescription() {
    return super.getDescription() + " + 反彈強化";
  }

  getPower() {
    return super.getPower() + 25;
  }
}

// 使用範例
console.log("=== 歡迎來到華麗魔法師的魔法工坊 ===\n");

// 基礎法術
console.log("--- 基礎法術展示 ---");
const basicFireball = new BasicFireball();
console.log(`法術: ${basicFireball.getDescription()}`);
console.log(`威力: ${basicFireball.getPower()}`);
basicFireball.cast();
console.log("");

// 為火球術添加追蹤效果
console.log("--- 魔法師開始施展裝飾魔法 ---");
const trackingFireball = new TrackingEnhancement(basicFireball);
console.log(`法術: ${trackingFireball.getDescription()}`);
console.log(`威力: ${trackingFireball.getPower()}`);
trackingFireball.cast();
console.log("");

// 為火球術添加追蹤和火焰附魔雙重效果
const superFireball = new FireEnchantment(
  new TrackingEnhancement(basicFireball)
);
console.log(`法術: ${superFireball.getDescription()}`);
console.log(`威力: ${superFireball.getPower()}`);
superFireball.cast();
console.log("");

// 為防護盾添加反彈效果
console.log("--- 防護盾強化展示 ---");
const basicShield = new BasicShield();
const reflectShield = new ReflectEnhancement(basicShield);
console.log(`法術: ${reflectShield.getDescription()}`);
console.log(`威力: ${reflectShield.getPower()}`);
reflectShield.cast();
console.log("");

// 終極組合：火焰追蹤反彈防護盾
const ultimateShield = new ReflectEnhancement(
  new FireEnchantment(new TrackingEnhancement(basicShield))
);
console.log("--- 終極組合法術 ---");
console.log(`法術: ${ultimateShield.getDescription()}`);
console.log(`威力: ${ultimateShield.getPower()}`);
ultimateShield.cast();

/** output
=== 歡迎來到華麗魔法師的魔法工坊 ===

--- 基礎法術展示 ---
法術: 基礎火球術
威力: 50
施展基礎火球術！

--- 魔法師開始施展裝飾魔法 ---
法術: 基礎火球術 + 追蹤強化
威力: 70
施展基礎火球術！
→ 附加追蹤效果，法術會自動鎖定目標！

法術: 基礎火球術 + 追蹤強化 + 火焰附魔
威力: 100
施展基礎火球術！
→ 附加追蹤效果，法術會自動鎖定目標！
→ 附加火焰附魔，造成額外灼燒傷害！

--- 防護盾強化展示 ---
法術: 基礎防護盾 + 反彈強化
威力: 55
展開基礎防護盾！
→ 附加反彈效果，可以反射敵人攻擊！

--- 終極組合法術 ---
法術: 基礎防護盾 + 追蹤強化 + 火焰附魔 + 反彈強化
威力: 105
展開基礎防護盾！
→ 附加追蹤效果，法術會自動鎖定目標！
→ 附加火焰附魔，造成額外灼燒傷害！
→ 附加反彈效果，可以反射敵人攻擊！
 */
```

## 小總結

Decorator 設計模式就像我們故事中的華麗魔法師，透過`動態附加功能`來增強現有物件的能力

- 招式
  - 基礎火球術 50
  - 基礎防護盾 30
- 附加能力
  - 追蹤強化 +20
  - 火焰附魔 +30
  - 反彈強化 +25

**核心特點：**

- **動態擴展**：在執行時期`動態地`為物件添加新功能
- **保持介面一致**：裝飾後的物件仍然符合原始介面
- **組合優於繼承**：透過物件`組合`而非繼承來擴展功能
- **靈活堆疊**：可以`多層裝飾`，創造出豐富的功能組合

**使用時機：**

- 想要動態地組合多種功能（ex: 飲料店的加料系統）
- 避免產生大量的子類別來應對功能組合（ex: 文字處理器的格式設定）

**注意事項：**

- 裝飾鏈過長時可能影響效能和除錯難度
- 需要確保所有裝飾器都正確實作介面
- 適合功能相對獨立且可任意組合的場景
- 與 Strategy 模式不同，Decorator 是疊加功能而非替換演算法
