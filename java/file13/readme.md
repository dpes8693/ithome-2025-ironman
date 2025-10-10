
```java
package file13;
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
package file13;
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
package file13;
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
package file13;
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
package file13;
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
package file13;
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
package file13;
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