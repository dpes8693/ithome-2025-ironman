
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