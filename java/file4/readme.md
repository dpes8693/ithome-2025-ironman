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
