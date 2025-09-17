# Day4 工廠模式 (Factory Pattern)

## 擬人化角色：【嚴謹的精靈鑄造師】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/3-Factory.png)

- 種族： 精靈
- 外貌： 身材修長，銀白色長髮編成長辮，眼神銳利而沉穩。他身著華麗且實用的長袍，手中常持一根魔杖，指向不同的魔法傳送門。
- 性格： 沉靜、睿智、注重細節和分工。他深知每個物件的製作過程都應由最專業的個體負責，因此他擅長將任務分配給不同的「分身」或「學徒」。
- 能力： 他的魔法工廠不會直接製造物件，而是透過創造出特定的「子類別」實體來負責生產。他更像是一位指揮家，確保每個子類別都遵循其專屬的製造藍圖。
- 代表語： 「我創造製造者，而非成品本身。」
- 背景故事： 在精靈王國的深處，這位精靈鑄造師掌管著最重要的武器和工具生產線。他並不像地精工匠那樣親自動手，而是設計了一套複雜的魔法系統，讓不同的元素精靈或專門的工匠負責製造特定的武器。例如，火元素精靈負責鍛造劍，水元素精靈負責製作弓。他的智慧在於巧妙地將創造實體的權責下放，確保了生產的多樣性和專業性。

---

## 範例

### Java

```java
//MagicWeapon.java
// 魔法武器介面
public interface MagicWeapon {
    void enchant();
    void use();
    String getElement();
    String getType();
}
```

```java
//FireSword.java
// 火焰劍
public class FireSword implements MagicWeapon {
    @Override
    public void enchant() {
        System.out.println("火元素精靈注入烈焰之力...");
    }

    @Override
    public void use() {
        System.out.println("火焰劍爆發出炙熱的劍氣，灼燒敵人！");
    }

    @Override
    public String getElement() {
        return "火焰";
    }

    @Override
    public String getType() {
        return "烈焰長劍";
    }
}
```

```java
//WaterBow.java
// 水流弓
public class WaterBow implements MagicWeapon {
    @Override
    public void enchant() {
        System.out.println("水元素精靈灌注流水之韻...");
    }

    @Override
    public void use() {
        System.out.println("水流弓射出冰冷的箭矢，穿透敵人！");
    }

    @Override
    public String getElement() {
        return "水流";
    }

    @Override
    public String getType() {
        return "潮汐長弓";
    }
}
```

```java
//EarthStaff.java
// 大地法杖
public class EarthStaff implements MagicWeapon {
    @Override
    public void enchant() {
        System.out.println("土元素精靈凝聚大地之力...");
    }

    @Override
    public void use() {
        System.out.println("大地法杖召喚岩石風暴，震撼戰場！");
    }

    @Override
    public String getElement() {
        return "大地";
    }

    @Override
    public String getType() {
        return "磐石法杖";
    }
}
```

```java
//ElfForgemaster.java
// 精靈鑄造師抽象類別
public abstract class ElfForgemaster {

    // 工廠方法 - 由子類別實現具體的創建邏輯
    protected abstract MagicWeapon createMagicWeapon();

    // 模板方法 - 定義武器製作的完整流程
    public final MagicWeapon forgeWeapon() {
        System.out.println("=== 精靈鑄造師開始工作 ===");

        // 準備工作
        prepareWorkshop();

        // 委託給子類別創建具體武器
        MagicWeapon weapon = createMagicWeapon();

        // 統一的後處理
        weapon.enchant();
        finalizeWeapon(weapon);

        return weapon;
    }

    private void prepareWorkshop() {
        System.out.println("準備魔法工坊，點燃元素火焰...");
    }

    private void finalizeWeapon(MagicWeapon weapon) {
        System.out.println("為 " + weapon.getType() + " 加上精靈祝福");
        System.out.println("武器鍛造完成！\n");
    }
}
```

```java
//FireElementForgemaster.java
// 火元素精靈鑄造師
public class FireElementForgemaster extends ElfForgemaster {
    @Override
    protected MagicWeapon createMagicWeapon() {
        System.out.println("召喚火元素精靈協助鍛造...");
        return new FireSword();
    }
}
```

```java
//WaterElementForgemaster.java
// 水元素精靈鑄造師
public class WaterElementForgemaster extends ElfForgemaster {
    @Override
    protected MagicWeapon createMagicWeapon() {
        System.out.println("召喚水元素精靈協助鍛造...");
        return new WaterBow();
    }
}
```

```java
//EarthElementForgemaster.java
// 土元素精靈鑄造師
public class EarthElementForgemaster extends ElfForgemaster {
    @Override
    protected MagicWeapon createMagicWeapon() {
        System.out.println("召喚土元素精靈協助鍛造...");
        return new EarthStaff();
    }
}
```

```java
//FactoryPatternExample.java
// 使用範例
public class FactoryPatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到精靈王國的魔法工坊 ===\n");

        // 創建不同的精靈鑄造師
        ElfForgemaster fireForgemaster = new FireElementForgemaster();
        ElfForgemaster waterForgemaster = new WaterElementForgemaster();
        ElfForgemaster earthForgemaster = new EarthElementForgemaster();

        // 讓每個鑄造師鍛造專屬武器
        MagicWeapon fireSword = fireForgemaster.forgeWeapon();
        MagicWeapon waterBow = waterForgemaster.forgeWeapon();
        MagicWeapon earthStaff = earthForgemaster.forgeWeapon();

        // 測試武器功能
        System.out.println("=== 武器測試 ===");
        testWeapon(fireSword);
        testWeapon(waterBow);
        testWeapon(earthStaff);
    }

    private static void testWeapon(MagicWeapon weapon) {
        System.out.println("測試武器: " + weapon.getType() + " (" + weapon.getElement() + "屬性)");
        weapon.use();
        System.out.println();
    }

    /**output
    === 歡迎來到精靈王國的魔法工坊 ===

    === 精靈鑄造師開始工作 ===
    準備魔法工坊，點燃元素火焰...
    召喚火元素精靈協助鍛造...
    火元素精靈注入烈焰之力...
    為 烈焰長劍 加上精靈祝福
    武器鍛造完成！

    === 精靈鑄造師開始工作 ===
    準備魔法工坊，點燃元素火焰...
    召喚水元素精靈協助鍛造...
    水元素精靈灌注流水之韻...
    為 潮汐長弓 加上精靈祝福
    武器鍛造完成！

    === 精靈鑄造師開始工作 ===
    準備魔法工坊，點燃元素火焰...
    召喚土元素精靈協助鍛造...
    土元素精靈凝聚大地之力...
    為 磐石法杖 加上精靈祝福
    武器鍛造完成！

    === 武器測試 ===
    測試武器: 烈焰長劍 (火焰屬性)
    火焰劍爆發出炙熱的劍氣，灼燒敵人！

    測試武器: 潮汐長弓 (水流屬性)
    水流弓射出冰冷的箭矢，穿透敵人！

    測試武器: 磐石法杖 (大地屬性)
    大地法杖召喚岩石風暴，震撼戰場！

    */
}
```

### JavaScript

```javascript
// 魔法武器基類
class MagicWeapon {
  enchant() {
    throw new Error("子類必須實現 enchant 方法");
  }

  use() {
    throw new Error("子類必須實現 use 方法");
  }

  getElement() {
    throw new Error("子類必須實現 getElement 方法");
  }

  getType() {
    throw new Error("子類必須實現 getType 方法");
  }
}

// 火焰劍
class FireSword extends MagicWeapon {
  enchant() {
    console.log("火元素精靈注入烈焰之力...");
  }

  use() {
    console.log("火焰劍爆發出炙熱的劍氣，灼燒敵人！");
  }

  getElement() {
    return "火焰";
  }

  getType() {
    return "烈焰長劍";
  }
}

// 水流弓
class WaterBow extends MagicWeapon {
  enchant() {
    console.log("水元素精靈灌注流水之韻...");
  }

  use() {
    console.log("水流弓射出冰冷的箭矢，穿透敵人！");
  }

  getElement() {
    return "水流";
  }

  getType() {
    return "潮汐長弓";
  }
}

// 大地法杖
class EarthStaff extends MagicWeapon {
  enchant() {
    console.log("土元素精靈凝聚大地之力...");
  }

  use() {
    console.log("大地法杖召喚岩石風暴，震撼戰場！");
  }

  getElement() {
    return "大地";
  }

  getType() {
    return "磐石法杖";
  }
}

// 精靈鑄造師抽象類別
class ElfForgemaster {
  // 工廠方法 - 由子類別實現具體的創建邏輯
  createMagicWeapon() {
    throw new Error("子類必須實現 createMagicWeapon 方法");
  }

  // 模板方法 - 定義武器製作的完整流程
  forgeWeapon() {
    console.log("=== 精靈鑄造師開始工作 ===");

    // 準備工作
    this.prepareWorkshop();

    // 委託給子類別創建具體武器
    const weapon = this.createMagicWeapon();

    // 統一的後處理
    weapon.enchant();
    this.finalizeWeapon(weapon);

    return weapon;
  }

  prepareWorkshop() {
    console.log("準備魔法工坊，點燃元素火焰...");
  }

  finalizeWeapon(weapon) {
    console.log(`為 ${weapon.getType()} 加上精靈祝福`);
    console.log("武器鍛造完成！\n");
  }
}

// 火元素精靈鑄造師
class FireElementForgemaster extends ElfForgemaster {
  createMagicWeapon() {
    console.log("召喚火元素精靈協助鍛造...");
    return new FireSword();
  }
}

// 水元素精靈鑄造師
class WaterElementForgemaster extends ElfForgemaster {
  createMagicWeapon() {
    console.log("召喚水元素精靈協助鍛造...");
    return new WaterBow();
  }
}

// 土元素精靈鑄造師
class EarthElementForgemaster extends ElfForgemaster {
  createMagicWeapon() {
    console.log("召喚土元素精靈協助鍛造...");
    return new EarthStaff();
  }
}

// 使用範例
console.log("=== 歡迎來到精靈王國的魔法工坊 ===\n");

// 創建不同的精靈鑄造師
const fireForgemaster = new FireElementForgemaster();
const waterForgemaster = new WaterElementForgemaster();
const earthForgemaster = new EarthElementForgemaster();

// 讓每個鑄造師鍛造專屬武器
const fireSword = fireForgemaster.forgeWeapon();
const waterBow = waterForgemaster.forgeWeapon();
const earthStaff = earthForgemaster.forgeWeapon();

// 武器測試函數
function testWeapon(weapon) {
  console.log(`測試武器: ${weapon.getType()} (${weapon.getElement()}屬性)`);
  weapon.use();
  console.log();
}

// 測試武器功能
console.log("=== 武器測試 ===");
testWeapon(fireSword);
testWeapon(waterBow);
testWeapon(earthStaff);

/** output
=== 歡迎來到精靈王國的魔法工坊 ===

=== 精靈鑄造師開始工作 ===
準備魔法工坊，點燃元素火焰...
召喚火元素精靈協助鍛造...
火元素精靈注入烈焰之力...
為 烈焰長劍 加上精靈祝福
武器鍛造完成！

=== 精靈鑄造師開始工作 ===
準備魔法工坊，點燃元素火焰...
召喚水元素精靈協助鍛造...
水元素精靈灌注流水之韻...
為 潮汐長弓 加上精靈祝福
武器鍛造完成！

=== 精靈鑄造師開始工作 ===
準備魔法工坊，點燃元素火焰...
召喚土元素精靈協助鍛造...
土元素精靈凝聚大地之力...
為 磐石法杖 加上精靈祝福
武器鍛造完成！

=== 武器測試 ===
測試武器: 烈焰長劍 (火焰屬性)
火焰劍爆發出炙熱的劍氣，灼燒敵人！

測試武器: 潮汐長弓 (水流屬性)
水流弓射出冰冷的箭矢，穿透敵人！

測試武器: 磐石法杖 (大地屬性)
大地法杖召喚岩石風暴，震撼戰場！

 */
```

## 小總結

Factory Pattern（工廠模式）就像我們故事中的嚴謹精靈鑄造師，透過抽象工廠類別將物件創建的責任委託給子類別

**核心特點：**

- **委託創建**：抽象工廠定義創建介面，具體工廠負責實際創建
- **開放封閉**：符合開放封閉原則，新增產品類型只需新增工廠子類別
- **模板方法**：可結合模板方法模式，統一創建流程但允許客製化步驟
- **單一職責**：每個具體工廠只負責創建特定類型的產品

**與 Simple Factory 的差異：**

| 特徵         | Simple Factory    | Factory Pattern   |
| ------------ | ----------------- | ----------------- |
| 創建方式     | 靜態方法 + switch | 抽象類別 + 子類別 |
| 擴展性       | 需修改工廠類別    | 新增工廠子類別    |
| 開放封閉原則 | 不符合            | 符合              |
| 複雜度       | 簡單              | 較複雜            |

**使用時機：**

- 需要創建一系列相關產品（ex: 不同平台的 UI 組件）
- 希望將物件創建邏輯與使用邏輯完全分離
- 需要在運行時決定創建哪個具體類別的實例
- 系統需要高度的可擴展性和維護性

**注意事項：**

- 增加了系統的複雜度，需要更多的類別和介面
- 適合產品變化較多且需要經常擴展的場景
- 可與其他模式結合使用（如模板方法、策略模式等）
- 在簡單場景中可能過度設計，應根據實際需求選擇
