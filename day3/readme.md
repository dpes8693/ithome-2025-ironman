# Day3 簡單工廠模式 (Simple Factory Pattern)

## 擬人化角色：【靈巧的地精工匠】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/2-SimpleFactory.png)

- 種族： 地精
- 外貌： 滿臉鬍鬚，戴著厚重的護目鏡，身穿沾滿油漬的皮圍裙。他的工作室堆滿了各式各樣的工具和零件。他手上經常拿著一個閃爍著魔法光芒的工具。
- 性格： 友善、樂於助人，但有點古怪。他能夠根據客戶的不同需求，快速地製作出各種小玩意或簡單的機械。他喜歡直接了當，不喜歡複雜的流程。
- 能力： 能夠透過簡單的輸入（參數），快速且精準地產出不同類型的物件。他的工作坊雖然不大，但效率極高，彷彿有無數個「生產線」在同時運轉。
- 代表語： 「告訴我你想要什麼，我馬上給你變出來！」
- 背景故事： 在一個科技與魔法並存的世界裡，這位地精工匠是所有新手冒險者和村民最常光顧的對象。無論是修補破損的農具，還是打造一把適合新手用的劍，他都能用最少的步驟完成。他堅信，解決問題的方法不一定需要複雜，簡單直接往往是最有效的。

---

## 範例

### Java

```java
//Weapon.java
// 武器介面
public interface Weapon {
    void use();
    String getType();
}
```

```java
//Sword.java
// 劍類實現
public class Sword implements Weapon {
    @Override
    public void use() {
        System.out.println("揮舞長劍，劃出優美的弧線！");
    }

    @Override
    public String getType() {
        return "新手長劍";
    }
}
```

```java
//Bow.java
// 弓類實現
public class Bow implements Weapon {
    @Override
    public void use() {
        System.out.println("拉滿弓弦，射出精準的箭矢！");
    }

    @Override
    public String getType() {
        return "精靈短弓";
    }
}
```

```java
//Staff.java
// 法杖類實現
public class Staff implements Weapon {
    @Override
    public void use() {
        System.out.println("舉起法杖，詠唱出強力的魔法！");
    }

    @Override
    public String getType() {
        return "魔法法杖";
    }
}
```

```java
//GoblinWeaponFactory.java
// 地精工匠的武器工廠
public class GoblinWeaponFactory {

    // 工廠方法：根據類型創建武器
    public static Weapon createWeapon(String weaponType) {
        if (weaponType == null || weaponType.isEmpty()) {
            return null;
        }

        System.out.println("地精工匠開始製作: " + weaponType);

        switch (weaponType.toLowerCase()) {
            case "sword":
                return new Sword();
            case "bow":
                return new Bow();
            case "staff":
                return new Staff();
            default:
                System.out.println("抱歉，我還不會製作這種武器: " + weaponType);
                return null;
        }
    }

    // 展示工匠的能力
    public static void showCraftingSkills() {
        System.out.println("=== 地精工匠的技能清單 ===");
        System.out.println("1. 新手長劍 (sword)");
        System.out.println("2. 精靈短弓 (bow)");
        System.out.println("3. 魔法法杖 (staff)");
        System.out.println("告訴我你想要什麼，我馬上給你變出來！");
    }
}
```

```java
//SimpleFactoryExample.java
// 使用範例
public class SimpleFactoryExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到地精工匠的工作坊 ===\n");

        // 展示工匠技能
        GoblinWeaponFactory.showCraftingSkills();
        System.out.println();

        // 創建不同類型的武器
        Weapon sword = GoblinWeaponFactory.createWeapon("sword");
        Weapon bow = GoblinWeaponFactory.createWeapon("bow");
        Weapon staff = GoblinWeaponFactory.createWeapon("staff");
        Weapon unknown = GoblinWeaponFactory.createWeapon("hammer");

        System.out.println();

        // 測試武器功能
        if (sword != null) {
            System.out.println("獲得武器: " + sword.getType());
            sword.use();
        }

        if (bow != null) {
            System.out.println("獲得武器: " + bow.getType());
            bow.use();
        }

        if (staff != null) {
            System.out.println("獲得武器: " + staff.getType());
            staff.use();
        }

        if (unknown == null) {
            System.out.println("無法製作未知武器");
        }

        /**output
        === 歡迎來到地精工匠的工作坊 ===

        === 地精工匠的技能清單 ===
        1. 新手長劍 (sword)
        2. 精靈短弓 (bow)
        3. 魔法法杖 (staff)
        告訴我你想要什麼，我馬上給你變出來！

        地精工匠開始製作: sword
        地精工匠開始製作: bow
        地精工匠開始製作: staff
        地精工匠開始製作: hammer
        抱歉，我還不會製作這種武器: hammer

        獲得武器: 新手長劍
        揮舞長劍，劃出優美的弧線！
        獲得武器: 精靈短弓
        拉滿弓弦，射出精準的箭矢！
        獲得武器: 魔法法杖
        舉起法杖，詠唱出強力的魔法！
        無法製作未知武器
        */
    }
}
```

### JavaScript

```javascript
// 武器基類
class Weapon {
  use() {
    throw new Error("子類必須實現 use 方法");
  }

  getType() {
    throw new Error("子類必須實現 getType 方法");
  }
}

// 劍類實現
class Sword extends Weapon {
  use() {
    console.log("揮舞長劍，劃出優美的弧線！");
  }

  getType() {
    return "新手長劍";
  }
}

// 弓類實現
class Bow extends Weapon {
  use() {
    console.log("拉滿弓弦，射出精準的箭矢！");
  }

  getType() {
    return "精靈短弓";
  }
}

// 法杖類實現
class Staff extends Weapon {
  use() {
    console.log("舉起法杖，詠唱出強力的魔法！");
  }

  getType() {
    return "魔法法杖";
  }
}

// 地精工匠的武器工廠
class GoblinWeaponFactory {
  // 工廠方法：根據類型創建武器
  static createWeapon(weaponType) {
    if (!weaponType) {
      return null;
    }

    console.log(`地精工匠開始製作: ${weaponType}`);

    switch (weaponType.toLowerCase()) {
      case "sword":
        return new Sword();
      case "bow":
        return new Bow();
      case "staff":
        return new Staff();
      default:
        console.log(`抱歉，我還不會製作這種武器: ${weaponType}`);
        return null;
    }
  }

  // 展示工匠的能力
  static showCraftingSkills() {
    console.log("=== 地精工匠的技能清單 ===");
    console.log("1. 新手長劍 (sword)");
    console.log("2. 精靈短弓 (bow)");
    console.log("3. 魔法法杖 (staff)");
    console.log("告訴我你想要什麼，我馬上給你變出來！");
  }
}

// 使用範例
console.log("=== 歡迎來到地精工匠的工作坊 ===\n");

// 展示工匠技能
GoblinWeaponFactory.showCraftingSkills();
console.log();

// 創建不同類型的武器
const sword = GoblinWeaponFactory.createWeapon("sword");
const bow = GoblinWeaponFactory.createWeapon("bow");
const staff = GoblinWeaponFactory.createWeapon("staff");
const unknown = GoblinWeaponFactory.createWeapon("hammer");

console.log();

// 測試武器功能
if (sword) {
  console.log(`獲得武器: ${sword.getType()}`);
  sword.use();
}

if (bow) {
  console.log(`獲得武器: ${bow.getType()}`);
  bow.use();
}

if (staff) {
  console.log(`獲得武器: ${staff.getType()}`);
  staff.use();
}

if (!unknown) {
  console.log("無法製作未知武器");
}

/** output
=== 歡迎來到地精工匠的工作坊 ===

=== 地精工匠的技能清單 ===
1. 新手長劍 (sword)
2. 精靈短弓 (bow)
3. 魔法法杖 (staff)
告訴我你想要什麼，我馬上給你變出來！

地精工匠開始製作: sword
地精工匠開始製作: bow
地精工匠開始製作: staff
地精工匠開始製作: hammer
抱歉，我還不會製作這種武器: hammer

獲得武器: 新手長劍
揮舞長劍，劃出優美的弧線！
獲得武器: 精靈短弓
拉滿弓弦，射出精準的箭矢！
獲得武器: 魔法法杖
舉起法杖，詠唱出強力的魔法！
無法製作未知武器
 */
```

## 小總結

Simple Factory 設計模式就像我們故事中的靈巧地精工匠，透過`統一的工廠`來創建不同類型的物件

**核心特點：**

- **集中創建**：將物件的創建邏輯集中在一個工廠類別中
- **簡化客戶端**：客戶端不需要知道具體類別的實作細節
- **參數驅動**：透過參數來決定創建哪種類型的物件
- **易於擴展**：新增產品類型只需修改工廠方法

**使用時機：**

- 需要根據條件創建不同類型的物件（ex: 根據檔案副檔名創建不同的解析器）
- 想要將物件創建邏輯與業務邏輯分離（ex: 遊戲中根據職業創建角色）

**注意事項：**

- 不符合開放封閉原則：新增產品需要修改工廠類別
- 工廠類別可能變得龐大，承擔過多責任
- 相較於其他工廠模式較為簡單，適合產品類型較少的場景
- 可以考慮使用反射或配置檔來提高靈活性
