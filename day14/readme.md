# Day14 狀態模式 (State Pattern)

## 擬人化角色：【多重人格的變形術士】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/13-State.png)

- 種族： 人類/法師
- 外貌： 一位身形模糊、周圍能量不斷變換的法師。他周圍環繞著多個代表不同形態（火焰形態、冰霜形態、雷電形態、潛行形態）的魔法虛影。每個形態都有獨特的武器和表情。他身處一個充滿魔法符文和懸浮水晶的圓形祭壇中央。
- 性格： 多變、適應性強，但每個「人格」都遵循自己的規則。他本身的行為會完全由當前所處的「狀態」來決定。
- 能力： 將物件的狀態封裝成類別，讓物件隨著狀態改變時能有不同的行為。這位變形術士（上下文物件）本身並不會直接決定自己的行為，而是將不同的「狀態」（如「火焰狂怒狀態」、「冰霜防禦狀態」、「雷電突襲狀態」、「潛伏隱匿狀態」）封裝成不同的獨立實體。當術士進入「火焰狀態」時，他的所有行為都會變為釋放火焰法術；進入「冰霜狀態」時，則會專注於防禦和冰凍。
- 代表語： 「我非我，我為此刻之形。」
- 背景故事： 這位神秘的變形術士擁有改變自身屬性和能力的奇特天賦。在戰鬥中，他可以根據需要隨時切換形態。當面對一群敵人時，他會進入「火焰狂怒狀態」，釋放鋪天蓋地的火球術；當需要保護隊友時，則切換到「冰霜防禦狀態」，築起冰牆。他的力量不在於施展單一強大法術，而在於能靈活地切換不同「狀態」，以應對各種局面。術士本身只是這些狀態的容器，真正的「行為邏輯」都寫在這些狀態之中。

---

## 範例

### Java

```java
//MageState.java
// 法師狀態介面
public interface MageState {
    void cast();
    void defend();
    void move();
    String getFormName();
}
```

```java
//FireState.java
// 火焰狂怒狀態
public class FireState implements MageState {
    @Override
    public void cast() {
        System.out.println("🔥 火焰爆發！釋放大範圍火球術！");
    }

    @Override
    public void defend() {
        System.out.println("🔥 火焰護盾展開，燒毀來犯敵人！");
    }

    @Override
    public void move() {
        System.out.println("🔥 火焰衝刺，留下燃燒足跡！");
    }

    @Override
    public String getFormName() {
        return "火焰狂怒形態";
    }
}
```

```java
//IceState.java
// 冰霜防禦狀態
public class IceState implements MageState {
    @Override
    public void cast() {
        System.out.println("❄️ 冰霜風暴！凍結所有敵人！");
    }

    @Override
    public void defend() {
        System.out.println("❄️ 堅固冰牆升起，形成絕對防護！");
    }

    @Override
    public void move() {
        System.out.println("❄️ 冰面滑行，優雅且迅速！");
    }

    @Override
    public String getFormName() {
        return "冰霜防禦形態";
    }
}
```

```java
//LightningState.java
// 雷電突襲狀態
public class LightningState implements MageState {
    @Override
    public void cast() {
        System.out.println("⚡ 雷霆萬鈞！閃電鏈連續打擊！");
    }

    @Override
    public void defend() {
        System.out.println("⚡ 雷電護罩反彈攻擊！");
    }

    @Override
    public void move() {
        System.out.println("⚡ 閃電瞬移，瞬間出現在敵人身後！");
    }

    @Override
    public String getFormName() {
        return "雷電突襲形態";
    }
}
```

```java
//StealthState.java
// 潛伏隱匿狀態
public class StealthState implements MageState {
    @Override
    public void cast() {
        System.out.println("🌙 暗影箭矢，無聲無息命中要害！");
    }

    @Override
    public void defend() {
        System.out.println("🌙 影分身術，真身消失在陰影中！");
    }

    @Override
    public void move() {
        System.out.println("🌙 如影隨行，悄無聲息地潛行！");
    }

    @Override
    public String getFormName() {
        return "潛伏隱匿形態";
    }
}
```

```java
//ShapeshifterMage.java
// 變形術士（Context上下文）
public class ShapeshifterMage {
    private MageState currentState;
    private String name;

    public ShapeshifterMage(String name) {
        this.name = name;
        // 預設狀態為火焰形態
        this.currentState = new FireState();
    }

    // 切換狀態
    public void changeState(MageState newState) {
        this.currentState = newState;
        System.out.println("✨ " + name + " 變形為：" + currentState.getFormName());
    }

    // 委託給當前狀態執行動作
    public void cast() {
        System.out.print(name + " - ");
        currentState.cast();
    }

    public void defend() {
        System.out.print(name + " - ");
        currentState.defend();
    }

    public void move() {
        System.out.print(name + " - ");
        currentState.move();
    }

    public String getCurrentForm() {
        return currentState.getFormName();
    }

    // 展示所有可用形態
    public void showAvailableForms() {
        System.out.println("=== " + name + " 的變形能力 ===");
        System.out.println("🔥 火焰狂怒形態 - 強力攻擊");
        System.out.println("❄️ 冰霜防禦形態 - 堅固防守");
        System.out.println("⚡ 雷電突襲形態 - 快速打擊");
        System.out.println("🌙 潛伏隱匿形態 - 隱密行動");
        System.out.println("「我非我，我為此刻之形。」");
    }
}
```

```java
//StatePatternExample.java
// 使用範例
public class StatePatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到變形術士的魔法殿堂 ===\n");

        // 創建變形術士
        ShapeshifterMage mage = new ShapeshifterMage("艾莉安娜");

        // 展示能力
        mage.showAvailableForms();
        System.out.println();

        // 測試火焰形態（預設）
        System.out.println("當前形態：" + mage.getCurrentForm());
        mage.cast();
        mage.defend();
        mage.move();
        System.out.println();

        // 切換到冰霜防禦形態
        mage.changeState(new IceState());
        mage.cast();
        mage.defend();
        mage.move();
        System.out.println();

        // 切換到雷電突襲形態
        mage.changeState(new LightningState());
        mage.cast();
        mage.defend();
        mage.move();
        System.out.println();

        // 切換到潛伏隱匿形態
        mage.changeState(new StealthState());
        mage.cast();
        mage.defend();
        mage.move();

        /**output
        === 歡迎來到變形術士的魔法殿堂 ===

        === 艾莉安娜 的變形能力 ===
        🔥 火焰狂怒形態 - 強力攻擊
        ❄️ 冰霜防禦形態 - 堅固防守
        ⚡ 雷電突襲形態 - 快速打擊
        🌙 潛伏隱匿形態 - 隱密行動
        「我非我，我為此刻之形。」

        當前形態：火焰狂怒形態
        艾莉安娜 - 🔥 火焰爆發！釋放大範圍火球術！
        艾莉安娜 - 🔥 火焰護盾展開，燒毀來犯敵人！
        艾莉安娜 - 🔥 火焰衝刺，留下燃燒足跡！

        ✨ 艾莉安娜 變形為：冰霜防禦形態
        艾莉安娜 - ❄️ 冰霜風暴！凍結所有敵人！
        艾莉安娜 - ❄️ 堅固冰牆升起，形成絕對防護！
        艾莉安娜 - ❄️ 冰面滑行，優雅且迅速！

        ✨ 艾莉安娜 變形為：雷電突襲形態
        艾莉安娜 - ⚡ 雷霆萬鈞！閃電鏈連續打擊！
        艾莉安娜 - ⚡ 雷電護罩反彈攻擊！
        艾莉安娜 - ⚡ 閃電瞬移，瞬間出現在敵人身後！

        ✨ 艾莉安娜 變形為：潛伏隱匿形態
        艾莉安娜 - 🌙 暗影箭矢，無聲無息命中要害！
        艾莉安娜 - 🌙 影分身術，真身消失在陰影中！
        艾莉安娜 - 🌙 如影隨行，悄無聲息地潛行！
        */
    }
}
```

### JavaScript

```javascript
// 法師狀態基類
class MageState {
  cast() {
    throw new Error("子類必須實現 cast 方法");
  }

  defend() {
    throw new Error("子類必須實現 defend 方法");
  }

  move() {
    throw new Error("子類必須實現 move 方法");
  }

  getFormName() {
    throw new Error("子類必須實現 getFormName 方法");
  }
}

// 火焰狂怒狀態
class FireState extends MageState {
  cast() {
    console.log("🔥 火焰爆發！釋放大範圍火球術！");
  }

  defend() {
    console.log("🔥 火焰護盾展開，燒毀來犯敵人！");
  }

  move() {
    console.log("🔥 火焰衝刺，留下燃燒足跡！");
  }

  getFormName() {
    return "火焰狂怒形態";
  }
}

// 冰霜防禦狀態
class IceState extends MageState {
  cast() {
    console.log("❄️ 冰霜風暴！凍結所有敵人！");
  }

  defend() {
    console.log("❄️ 堅固冰牆升起，形成絕對防護！");
  }

  move() {
    console.log("❄️ 冰面滑行，優雅且迅速！");
  }

  getFormName() {
    return "冰霜防禦形態";
  }
}

// 雷電突襲狀態
class LightningState extends MageState {
  cast() {
    console.log("⚡ 雷霆萬鈞！閃電鏈連續打擊！");
  }

  defend() {
    console.log("⚡ 雷電護罩反彈攻擊！");
  }

  move() {
    console.log("⚡ 閃電瞬移，瞬間出現在敵人身後！");
  }

  getFormName() {
    return "雷電突襲形態";
  }
}

// 潛伏隱匿狀態
class StealthState extends MageState {
  cast() {
    console.log("🌙 暗影箭矢，無聲無息命中要害！");
  }

  defend() {
    console.log("🌙 影分身術，真身消失在陰影中！");
  }

  move() {
    console.log("🌙 如影隨行，悄無聲息地潛行！");
  }

  getFormName() {
    return "潛伏隱匿形態";
  }
}

// 變形術士（Context上下文）
class ShapeshifterMage {
  constructor(name) {
    this.name = name;
    // 預設狀態為火焰形態
    this.currentState = new FireState();
  }

  // 切換狀態
  changeState(newState) {
    this.currentState = newState;
    console.log(`✨ ${this.name} 變形為：${this.currentState.getFormName()}`);
  }

  // 委託給當前狀態執行動作
  cast() {
    process.stdout.write(`${this.name} - `);
    this.currentState.cast();
  }

  defend() {
    process.stdout.write(`${this.name} - `);
    this.currentState.defend();
  }

  move() {
    process.stdout.write(`${this.name} - `);
    this.currentState.move();
  }

  getCurrentForm() {
    return this.currentState.getFormName();
  }

  // 展示所有可用形態
  showAvailableForms() {
    console.log(`=== ${this.name} 的變形能力 ===`);
    console.log("🔥 火焰狂怒形態 - 強力攻擊");
    console.log("❄️ 冰霜防禦形態 - 堅固防守");
    console.log("⚡ 雷電突襲形態 - 快速打擊");
    console.log("🌙 潛伏隱匿形態 - 隱密行動");
    console.log("「我非我，我為此刻之形。」");
  }
}

// 使用範例
console.log("=== 歡迎來到變形術士的魔法殿堂 ===\n");

// 創建變形術士
const mage = new ShapeshifterMage("艾莉安娜");

// 展示能力
mage.showAvailableForms();
console.log();

// 測試火焰形態（預設）
console.log(`當前形態：${mage.getCurrentForm()}`);
mage.cast();
mage.defend();
mage.move();
console.log();

// 切換到冰霜防禦形態
mage.changeState(new IceState());
mage.cast();
mage.defend();
mage.move();
console.log();

// 切換到雷電突襲形態
mage.changeState(new LightningState());
mage.cast();
mage.defend();
mage.move();
console.log();

// 切換到潛伏隱匿形態
mage.changeState(new StealthState());
mage.cast();
mage.defend();
mage.move();

/** output
=== 歡迎來到變形術士的魔法殿堂 ===

=== 艾莉安娜 的變形能力 ===
🔥 火焰狂怒形態 - 強力攻擊
❄️ 冰霜防禦形態 - 堅固防守
⚡ 雷電突襲形態 - 快速打擊
🌙 潛伏隱匿形態 - 隱密行動
「我非我，我為此刻之形。」

當前形態：火焰狂怒形態
艾莉安娜 - 🔥 火焰爆發！釋放大範圍火球術！
艾莉安娜 - 🔥 火焰護盾展開，燒毀來犯敵人！
艾莉安娜 - 🔥 火焰衝刺，留下燃燒足跡！

✨ 艾莉安娜 變形為：冰霜防禦形態
艾莉安娜 - ❄️ 冰霜風暴！凍結所有敵人！
艾莉安娜 - ❄️ 堅固冰牆升起，形成絕對防護！
艾莉安娜 - ❄️ 冰面滑行，優雅且迅速！

✨ 艾莉安娜 變形為：雷電突襲形態
艾莉安娜 - ⚡ 雷霆萬鈞！閃電鏈連續打擊！
艾莉安娜 - ⚡ 雷電護罩反彈攻擊！
艾莉安娜 - ⚡ 閃電瞬移，瞬間出現在敵人身後！

✨ 艾莉安娜 變形為：潛伏隱匿形態
艾莉安娜 - 🌙 暗影箭矢，無聲無息命中要害！
艾莉安娜 - 🌙 影分身術，真身消失在陰影中！
艾莉安娜 - 🌙 如影隨行，悄無聲息地潛行！
 */
```

## 小總結

State 設計模式就像我們故事中的多重人格變形術士，讓物件能夠`根據內部狀態改變其行為`

**核心特點：**

- **狀態封裝**：將每個狀態封裝成獨立的類別，各自處理該狀態下的行為
- **行為委託**：上下文物件將行為委託給當前狀態物件執行
- **狀態切換**：可以動態地切換狀態，改變物件的行為
- **消除條件判斷**：避免在上下文中使用大量的 if-else 或 switch 語句

**使用時機：**

- 物件的行為會隨著內部狀態改變而有所不同（ex: 遊戲角色的不同狀態）
- 有大量與狀態相關的條件語句需要簡化（ex: 狀態機、工作流程）

## State Pattern vs Factory Pattern 差異比較

### 🎯 **核心目的差異**

| 設計模式            | 核心目的                           | 關注重點       |
| ------------------- | ---------------------------------- | -------------- |
| **State Pattern**   | 讓物件能夠根據**內部狀態改變行為** | 行為的動態切換 |
| **Factory Pattern** | 負責**物件的創建和選擇**           | 物件的創建過程 |

### 🔄 **物件生命週期**

**State Pattern：**

- 物件在運行時**動態切換狀態**
- 同一個 Context 物件會持續存在，只是內部狀態會改變
- 狀態之間存在**轉換關係**和**生命週期**

**Factory Pattern：**

- 根據參數**一次性創建**適當的物件
- 創建完成後，物件類型通常**不再改變**
- 專注於**如何創建**，而非創建後的行為變化

### 🎮 **實際應用場景對比**

**State Pattern 適用場景：**

```java
// 遊戲角色狀態：健康 → 受傷 → 瀕死 → 死亡
GameCharacter character = new GameCharacter();
character.takeDamage(50);    // 狀態：健康 → 受傷
character.takeDamage(30);    // 狀態：受傷 → 瀕死
character.heal(20);          // 狀態：瀕死 → 受傷
```

**Factory Pattern 適用場景：**

```java
// 根據需求創建不同類型的武器
WeaponFactory factory = new WeaponFactory();
Weapon sword = factory.createWeapon("SWORD");     // 創建劍
Weapon bow = factory.createWeapon("BOW");         // 創建弓
// 武器創建後類型固定，不會自動變成其他武器
```
