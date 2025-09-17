# Day5 抽象工廠模式 (Abstract Factory Pattern)

## 擬人化角色：【古老的矮人宗師】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/4-AbstractFactory.png)

- 種族： 矮人
- 外貌： 身材矮壯，絡腮鬍鬚梳理得一絲不苟，滿臉皺紋透露出歲月的痕跡。他身著厚重的鍛造服，頭戴鑲嵌寶石的頭盔，背後是熊熊燃燒的爐火，以及三個閃耀著不同光芒的傳送門。
- 性格： 固執、嚴謹，對傳統和品質有著近乎偏執的追求。他不會親手製作任何單一物件，而是設計出一整個「產品系列」的生產流程。
- 能力： 他是一個總體設計師，建立了一個工廠界面，可以產生一系列相關的物件，但實際的製造工作由底層的具體工廠來完成。他確保所有的產品系列都風格統一，並且彼此相容。
- 代表語： 「一套完整的裝備，才能成就真正的勇士。」
- 背景故事： 在矮人山脈深處的巨型鍛造廠中，這位矮人宗師是整個裝備生產線的總負責人。他不會像其他工匠一樣只打造一把劍或一面盾，而是提供不同「套裝」的生產藍圖。例如，他有一個「戰士套裝工廠」負責生產一系列的戰士裝備（劍、盾、盔甲），一個「法師套裝工廠」負責生產法師裝備（法杖、法袍、魔戒）。這些套裝的風格和功能都完美契合，並且由不同的矮人分工廠實作。他保證了每一套裝備的完整性和協調性。

---

## 範例

### Java

```java
//Weapon.java
// 武器抽象介面
public interface Weapon {
    void attack();
    String getName();
    String getStyle();
}
```

```java
//Armor.java
// 護甲抽象介面
public interface Armor {
    void defend();
    String getName();
    String getStyle();
}
```

```java
//Accessory.java
// 配飾抽象介面
public interface Accessory {
    void enhance();
    String getName();
    String getStyle();
}
```

```java
//WarriorSword.java
// 戰士劍
public class WarriorSword implements Weapon {
    @Override
    public void attack() {
        System.out.println("戰士劍發出沉重的劈砍聲，勇猛攻擊！");
    }

    @Override
    public String getName() {
        return "矮人戰士劍";
    }

    @Override
    public String getStyle() {
        return "戰士系列";
    }
}
```

```java
//WarriorShield.java
// 戰士盾牌
public class WarriorShield implements Armor {
    @Override
    public void defend() {
        System.out.println("戰士盾牌閃耀著堅固光芒，抵擋攻擊！");
    }

    @Override
    public String getName() {
        return "矮人戰士盾牌";
    }

    @Override
    public String getStyle() {
        return "戰士系列";
    }
}
```

```java
//WarriorGauntlet.java
// 戰士護手
public class WarriorGauntlet implements Accessory {
    @Override
    public void enhance() {
        System.out.println("戰士護手增強握力，提升攻擊精準度！");
    }

    @Override
    public String getName() {
        return "矮人戰士護手";
    }

    @Override
    public String getStyle() {
        return "戰士系列";
    }
}
```

```java
//MageStaff.java
// 法師法杖
public class MageStaff implements Weapon {
    @Override
    public void attack() {
        System.out.println("法師法杖釋放魔法能量，施展強力法術！");
    }

    @Override
    public String getName() {
        return "矮人法師法杖";
    }

    @Override
    public String getStyle() {
        return "法師系列";
    }
}
```

```java
//MageRobe.java
// 法師法袍
public class MageRobe implements Armor {
    @Override
    public void defend() {
        System.out.println("法師法袍散發魔法護盾，抵禦魔法攻擊！");
    }

    @Override
    public String getName() {
        return "矮人法師法袍";
    }

    @Override
    public String getStyle() {
        return "法師系列";
    }
}
```

```java
//MageRing.java
// 法師魔戒
public class MageRing implements Accessory {
    @Override
    public void enhance() {
        System.out.println("法師魔戒增強魔力，加速法術詠唱！");
    }

    @Override
    public String getName() {
        return "矮人法師魔戒";
    }

    @Override
    public String getStyle() {
        return "法師系列";
    }
}
```

```java
//DwarvenEquipmentFactory.java
// 矮人裝備抽象工廠
public abstract class DwarvenEquipmentFactory {
    // 抽象工廠方法 - 創建武器
    public abstract Weapon createWeapon();

    // 抽象工廠方法 - 創建護甲
    public abstract Armor createArmor();

    // 抽象工廠方法 - 創建配飾
    public abstract Accessory createAccessory();

    // 模板方法 - 創建完整套裝
    public final void createCompleteSet() {
        System.out.println("=== 矮人宗師開始鍛造套裝 ===");

        Weapon weapon = createWeapon();
        Armor armor = createArmor();
        Accessory accessory = createAccessory();

        System.out.println("套裝鍛造完成！");
        System.out.println("風格: " + weapon.getStyle());
        System.out.println("包含: " + weapon.getName() + "、" +
                          armor.getName() + "、" + accessory.getName());

        // 測試套裝
        System.out.println("\n=== 套裝測試 ===");
        weapon.attack();
        armor.defend();
        accessory.enhance();
        System.out.println();
    }

    // 靜態工廠方法 - 根據類型創建工廠
    public static DwarvenEquipmentFactory getFactory(String type) {
        switch (type.toLowerCase()) {
            case "warrior":
            case "戰士":
                return new WarriorEquipmentFactory();
            case "mage":
            case "法師":
                return new MageEquipmentFactory();
            default:
                throw new IllegalArgumentException("未知的裝備類型: " + type);
        }
    }
}
```

```java
//WarriorEquipmentFactory.java
// 戰士裝備具體工廠
public class WarriorEquipmentFactory extends DwarvenEquipmentFactory {
    @Override
    public Weapon createWeapon() {
        System.out.println("鍛造戰士武器...");
        return new WarriorSword();
    }

    @Override
    public Armor createArmor() {
        System.out.println("鍛造戰士護甲...");
        return new WarriorShield();
    }

    @Override
    public Accessory createAccessory() {
        System.out.println("鍛造戰士配飾...");
        return new WarriorGauntlet();
    }
}
```

```java
//MageEquipmentFactory.java
// 法師裝備具體工廠
public class MageEquipmentFactory extends DwarvenEquipmentFactory {
    @Override
    public Weapon createWeapon() {
        System.out.println("鍛造法師武器...");
        return new MageStaff();
    }

    @Override
    public Armor createArmor() {
        System.out.println("鍛造法師護甲...");
        return new MageRobe();
    }

    @Override
    public Accessory createAccessory() {
        System.out.println("鍛造法師配飾...");
        return new MageRing();
    }
}
```

```java
//AbstractFactoryExample.java
// 使用範例
public class AbstractFactoryExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到矮人宗師的裝備工坊 ===\n");

        try {
            // 創建戰士套裝
            System.out.println(">>> 創建戰士套裝 <<<");
            DwarvenEquipmentFactory warriorFactory =
                DwarvenEquipmentFactory.getFactory("warrior");
            warriorFactory.createCompleteSet();

            // 創建法師套裝
            System.out.println(">>> 創建法師套裝 <<<");
            DwarvenEquipmentFactory mageFactory =
                DwarvenEquipmentFactory.getFactory("mage");
            mageFactory.createCompleteSet();

            // 示範客戶端如何使用
            demonstrateClientUsage();

        } catch (IllegalArgumentException e) {
            System.out.println("錯誤: " + e.getMessage());
        }
    }

    private static void demonstrateClientUsage() {
        System.out.println("=== 客戶端使用示範 ===");

        // 客戶端不需要知道具體的產品類別
        DwarvenEquipmentFactory factory = DwarvenEquipmentFactory.getFactory("warrior");

        Weapon weapon = factory.createWeapon();
        Armor armor = factory.createArmor();

        System.out.println("獲得 " + weapon.getName() + " 和 " + armor.getName());
        System.out.println("確認風格一致: " + weapon.getStyle().equals(armor.getStyle()));
    }

    /**output
    === 歡迎來到矮人宗師的裝備工坊 ===

    >>> 創建戰士套裝 <<<
    === 矮人宗師開始鍛造套裝 ===
    鍛造戰士武器...
    鍛造戰士護甲...
    鍛造戰士配飾...
    套裝鍛造完成！
    風格: 戰士系列
    包含: 矮人戰士劍、矮人戰士盾牌、矮人戰士護手

    === 套裝測試 ===
    戰士劍發出沉重的劈砍聲，勇猛攻擊！
    戰士盾牌閃耀著堅固光芒，抵擋攻擊！
    戰士護手增強握力，提升攻擊精準度！

    >>> 創建法師套裝 <<<
    === 矮人宗師開始鍛造套裝 ===
    鍛造法師武器...
    鍛造法師護甲...
    鍛造法師配飾...
    套裝鍛造完成！
    風格: 法師系列
    包含: 矮人法師法杖、矮人法師法袍、矮人法師魔戒

    === 套裝測試 ===
    法師法杖釋放魔法能量，施展強力法術！
    法師法袍散發魔法護盾，抵禦魔法攻擊！
    法師魔戒增強魔力，加速法術詠唱！

    === 客戶端使用示範 ===
    鍛造戰士武器...
    鍛造戰士護甲...
    獲得 矮人戰士劍 和 矮人戰士盾牌
    確認風格一致: true
    */
}
```

### JavaScript

```javascript
// 武器抽象類別
class Weapon {
  attack() {
    throw new Error("子類必須實現 attack 方法");
  }

  getName() {
    throw new Error("子類必須實現 getName 方法");
  }

  getStyle() {
    throw new Error("子類必須實現 getStyle 方法");
  }
}

// 護甲抽象類別
class Armor {
  defend() {
    throw new Error("子類必須實現 defend 方法");
  }

  getName() {
    throw new Error("子類必須實現 getName 方法");
  }

  getStyle() {
    throw new Error("子類必須實現 getStyle 方法");
  }
}

// 配飾抽象類別
class Accessory {
  enhance() {
    throw new Error("子類必須實現 enhance 方法");
  }

  getName() {
    throw new Error("子類必須實現 getName 方法");
  }

  getStyle() {
    throw new Error("子類必須實現 getStyle 方法");
  }
}

// 戰士系列產品
class WarriorSword extends Weapon {
  attack() {
    console.log("戰士劍發出沉重的劈砍聲，勇猛攻擊！");
  }

  getName() {
    return "矮人戰士劍";
  }

  getStyle() {
    return "戰士系列";
  }
}

class WarriorShield extends Armor {
  defend() {
    console.log("戰士盾牌閃耀著堅固光芒，抵擋攻擊！");
  }

  getName() {
    return "矮人戰士盾牌";
  }

  getStyle() {
    return "戰士系列";
  }
}

class WarriorGauntlet extends Accessory {
  enhance() {
    console.log("戰士護手增強握力，提升攻擊精準度！");
  }

  getName() {
    return "矮人戰士護手";
  }

  getStyle() {
    return "戰士系列";
  }
}

// 法師系列產品
class MageStaff extends Weapon {
  attack() {
    console.log("法師法杖釋放魔法能量，施展強力法術！");
  }

  getName() {
    return "矮人法師法杖";
  }

  getStyle() {
    return "法師系列";
  }
}

class MageRobe extends Armor {
  defend() {
    console.log("法師法袍散發魔法護盾，抵禦魔法攻擊！");
  }

  getName() {
    return "矮人法師法袍";
  }

  getStyle() {
    return "法師系列";
  }
}

class MageRing extends Accessory {
  enhance() {
    console.log("法師魔戒增強魔力，加速法術詠唱！");
  }

  getName() {
    return "矮人法師魔戒";
  }

  getStyle() {
    return "法師系列";
  }
}

// 矮人裝備抽象工廠
class DwarvenEquipmentFactory {
  // 抽象工廠方法
  createWeapon() {
    throw new Error("子類必須實現 createWeapon 方法");
  }

  createArmor() {
    throw new Error("子類必須實現 createArmor 方法");
  }

  createAccessory() {
    throw new Error("子類必須實現 createAccessory 方法");
  }

  // 模板方法 - 創建完整套裝
  createCompleteSet() {
    console.log("=== 矮人宗師開始鍛造套裝 ===");

    const weapon = this.createWeapon();
    const armor = this.createArmor();
    const accessory = this.createAccessory();

    console.log("套裝鍛造完成！");
    console.log(`風格: ${weapon.getStyle()}`);
    console.log(
      `包含: ${weapon.getName()}、${armor.getName()}、${accessory.getName()}`
    );

    // 測試套裝
    console.log("\n=== 套裝測試 ===");
    weapon.attack();
    armor.defend();
    accessory.enhance();
    console.log();
  }

  // 靜態工廠方法
  static getFactory(type) {
    switch (type.toLowerCase()) {
      case "warrior":
      case "戰士":
        return new WarriorEquipmentFactory();
      case "mage":
      case "法師":
        return new MageEquipmentFactory();
      default:
        throw new Error(`未知的裝備類型: ${type}`);
    }
  }
}

// 戰士裝備具體工廠
class WarriorEquipmentFactory extends DwarvenEquipmentFactory {
  createWeapon() {
    console.log("鍛造戰士武器...");
    return new WarriorSword();
  }

  createArmor() {
    console.log("鍛造戰士護甲...");
    return new WarriorShield();
  }

  createAccessory() {
    console.log("鍛造戰士配飾...");
    return new WarriorGauntlet();
  }
}

// 法師裝備具體工廠
class MageEquipmentFactory extends DwarvenEquipmentFactory {
  createWeapon() {
    console.log("鍛造法師武器...");
    return new MageStaff();
  }

  createArmor() {
    console.log("鍛造法師護甲...");
    return new MageRobe();
  }

  createAccessory() {
    console.log("鍛造法師配飾...");
    return new MageRing();
  }
}

// 客戶端使用示範
function demonstrateClientUsage() {
  console.log("=== 客戶端使用示範 ===");

  // 客戶端不需要知道具體的產品類別
  const factory = DwarvenEquipmentFactory.getFactory("warrior");

  const weapon = factory.createWeapon();
  const armor = factory.createArmor();

  console.log(`獲得 ${weapon.getName()} 和 ${armor.getName()}`);
  console.log(`確認風格一致: ${weapon.getStyle() === armor.getStyle()}`);
}

// 使用範例
console.log("=== 歡迎來到矮人宗師的裝備工坊 ===\n");

try {
  // 創建戰士套裝
  console.log(">>> 創建戰士套裝 <<<");
  const warriorFactory = DwarvenEquipmentFactory.getFactory("warrior");
  warriorFactory.createCompleteSet();

  // 創建法師套裝
  console.log(">>> 創建法師套裝 <<<");
  const mageFactory = DwarvenEquipmentFactory.getFactory("mage");
  mageFactory.createCompleteSet();

  // 示範客戶端如何使用
  demonstrateClientUsage();
} catch (error) {
  console.log(`錯誤: ${error.message}`);
}

/** output
=== 歡迎來到矮人宗師的裝備工坊 ===

>>> 創建戰士套裝 <<<
=== 矮人宗師開始鍛造套裝 ===
鍛造戰士武器...
鍛造戰士護甲...
鍛造戰士配飾...
套裝鍛造完成！
風格: 戰士系列
包含: 矮人戰士劍、矮人戰士盾牌、矮人戰士護手

=== 套裝測試 ===
戰士劍發出沉重的劈砍聲，勇猛攻擊！
戰士盾牌閃耀著堅固光芒，抵擋攻擊！
戰士護手增強握力，提升攻擊精準度！

>>> 創建法師套裝 <<<
=== 矮人宗師開始鍛造套裝 ===
鍛造法師武器...
鍛造法師護甲...
鍛造法師配飾...
套裝鍛造完成！
風格: 法師系列
包含: 矮人法師法杖、矮人法師法袍、矮人法師魔戒

=== 套裝測試 ===
法師法杖釋放魔法能量，施展強力法術！
法師法袍散發魔法護盾，抵禦魔法攻擊！
法師魔戒增強魔力，加速法術詠唱！

=== 客戶端使用示範 ===
鍛造戰士武器...
鍛造戰士護甲...
獲得 矮人戰士劍 和 矮人戰士盾牌
確認風格一致: true
 */
```

## 小總結

Abstract Factory Pattern（抽象工廠模式）就像我們故事中的古老矮人宗師，提供創建一系列相關產品族的介面

**核心特點：**

- **產品族創建**：一次創建多個相關且相容的產品
- **風格一致性**：確保同一工廠產出的產品風格統一
- **多重抽象**：多個產品類型都有抽象介面
- **工廠抽象化**：工廠本身也是抽象的，由具體工廠實現

## 🏭 三種工廠模式比較

| 特徵         | Simple Factory   | Factory Pattern  | Abstract Factory Pattern   |
| ------------ | ---------------- | ---------------- | -------------------------- |
| **目的**     | 創建單一產品     | 創建單一產品類型 | 創建產品族（多個相關產品） |
| **產品數量** | 一個             | 一個             | 多個相關產品               |
| **工廠結構** | 靜態方法         | 抽象類別+子類別  | 抽象工廠+具體工廠          |
| **擴展方式** | 修改工廠方法     | 新增工廠子類別   | 新增整個工廠實現           |
| **開放封閉** | ❌ 不符合        | ✅ 符合          | ✅ 符合                    |
| **複雜度**   | 🟢 簡單          | 🟡 中等          | 🔴 複雜                    |
| **使用場景** | 產品類型少且固定 | 單一產品多變化   | 多產品需保持一致性         |

### 🎯 使用時機對照

#### Simple Factory 適用於

- 產品類型少（3-5 種）且變化不頻繁
- 創建邏輯簡單，不需要複雜的初始化
- 快速原型開發或小型專案
- **範例**：根據檔案副檔名創建解析器

#### Factory Pattern 適用於

- 需要委託子類決定創建哪個產品
- 產品創建過程複雜，需要模板方法
- 需要高度擴展性但只涉及單一產品類型
- **範例**：不同平台的 UI 元件創建

#### Abstract Factory Pattern 適用於

- 需要創建一系列相關或相依的產品
- 產品需要保持風格或介面一致性
- 系統需要在多個產品族之間切換
- **範例**：不同風格的 UI 主題、跨平台應用程式

### 🚀 進階考量

**效能影響：**

- Simple Factory：最輕量，直接創建
- Factory Pattern：中等，需要繼承結構
- Abstract Factory：最重，多層抽象

**維護性：**

- Simple Factory：集中但易違反開放封閉原則
- Factory Pattern：分散職責，易於維護
- Abstract Factory：最佳分離，但類別數量最多

**學習曲線：**

- Simple Factory：容易理解和實現
- Factory Pattern：需要理解抽象和繼承
- Abstract Factory：需要理解複雜的抽象結構

### 💡 選擇建議

1. **從簡單開始**：優先考慮 Simple Factory
2. **需求驅動**：當 Simple Factory 不足時再升級
3. **預見未來**：如果確定會有複雜需求，直接選擇適當模式
4. **團隊能力**：考慮團隊對設計模式的理解程度

**決策樹：**

```text
需要創建物件？
├─ 只有一種產品類型？
│  ├─ 創建邏輯簡單？ → Simple Factory
│  └─ 需要複雜流程？ → Factory Pattern
└─ 需要多個相關產品？ → Abstract Factory Pattern
```

**古老矮人宗師的智慧**：「一套完整的裝備，才能成就真正的勇士。選擇合適的工廠模式，就如同選擇合適的工具，簡單的任務不需要複雜的設備。」
