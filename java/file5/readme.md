```java
//BattleStrategy.java
// 戰術策略介面
package file5;
public interface BattleStrategy {
    void execute();
    String getStrategyName();
    String getDescription();
}
```

```java
//FrontalAssaultStrategy.java
// 正面突擊策略
package file5;
public class FrontalAssaultStrategy implements BattleStrategy {
    @Override
    public void execute() {
        System.out.println("騎兵集結完畢！");
        System.out.println("重裝騎兵發起衝鋒，直取敵軍心臟！");
        System.out.println("正面突破敵軍防線，勢如破竹！");
    }

    @Override
    public String getStrategyName() {
        return "正面突擊策略";
    }

    @Override
    public String getDescription() {
        return "適用於敵軍士氣低落或防線薄弱時使用";
    }
}
```

```java
//RangedSuppressionStrategy.java
// 遠程壓制策略
package file5;
public class RangedSuppressionStrategy implements BattleStrategy {
    @Override
    public void execute() {
        System.out.println("弓箭手就位，魔法師開始詠唱！");
        System.out.println("箭雨如暴風般傾瀉而下！");
        System.out.println("魔法轟炸覆蓋戰場，敵軍陷入混亂！");
    }

    @Override
    public String getStrategyName() {
        return "遠程壓制策略";
    }

    @Override
    public String getDescription() {
        return "適用於地形開闊，敵軍缺乏掩護時使用";
    }
}
```

```java
//StealthRaidStrategy.java
// 奇襲策略
package file5;
public class StealthRaidStrategy implements BattleStrategy {
    @Override
    public void execute() {
        System.out.println("夜幕降臨，刺客小隊悄然行動！");
        System.out.println("潛行繞後，直搗敵軍指揮部！");
        System.out.println("斬首行動成功，敵軍群龍無首！");
    }

    @Override
    public String getStrategyName() {
        return "奇襲策略";
    }

    @Override
    public String getDescription() {
        return "適用於夜戰或敵軍戒備鬆懈時使用";
    }
}
```

```java
//DefensiveStrategy.java
// 防禦策略
package file5;
public class DefensiveStrategy implements BattleStrategy {
    @Override
    public void execute() {
        System.out.println("盾牆架起，長槍如林！");
        System.out.println("堅守要塞，以逸待勞！");
        System.out.println("消耗敵軍體力，等待反擊時機！");
    }

    @Override
    public String getStrategyName() {
        return "防禦策略";
    }

    @Override
    public String getDescription() {
        return "適用於敵強我弱或需要拖延時間時使用";
    }
}
```

```java
//TacticalCommander.java
// 戰術大師指揮官
package file5;
public class TacticalCommander {
    private BattleStrategy currentStrategy;
    private String commanderName;

    public TacticalCommander(String name) {
        this.commanderName = name;
    }

    // 設定戰術策略
    public void setStrategy(BattleStrategy strategy) {
        this.currentStrategy = strategy;
        System.out.println(commanderName + " 選擇了: " + strategy.getStrategyName());
        System.out.println("策略說明: " + strategy.getDescription());
    }

    // 執行當前策略
    public void executeBattle() {
        if (currentStrategy == null) {
            System.out.println("警告：尚未選擇作戰策略！");
            return;
        }

        System.out.println("\n=== 戰術執行開始 ===");
        System.out.println("指揮官 " + commanderName + " 下達作戰命令：");
        currentStrategy.execute();
        System.out.println("=== 戰術執行完畢 ===\n");
    }

    // 分析戰況並建議策略
    public static void analyzeBattlefield() {
        System.out.println("=== 戰場分析 ===");
        System.out.println("1. 敵軍兵力薄弱，士氣低落 → 建議使用正面突擊");
        System.out.println("2. 地形開闊，敵軍無掩護 → 建議使用遠程壓制");
        System.out.println("3. 夜戰環境，敵軍戒備鬆懈 → 建議使用奇襲策略");
        System.out.println("4. 敵強我弱，需要拖延時間 → 建議使用防禦策略");
        System.out.println("「面對不同的敵人，需有不同的戰法。」\n");
    }
}
```

```java
//StrategyPatternExample.java
// 使用範例
package file5;
public class StrategyPatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到戰術大師的指揮帳 ===\n");

        // 創建指揮官
        TacticalCommander commander = new TacticalCommander("艾瑞克");

        // 分析戰場情況
        TacticalCommander.analyzeBattlefield();

        // 創建不同的策略
        BattleStrategy frontalAssault = new FrontalAssaultStrategy();
        BattleStrategy rangedSuppression = new RangedSuppressionStrategy();
        BattleStrategy stealthRaid = new StealthRaidStrategy();
        BattleStrategy defensive = new DefensiveStrategy();

        // 場景一：面對士氣低落的敵軍
        System.out.println("【場景一】偵查兵回報：敵軍士氣低落，防線鬆散");
        commander.setStrategy(frontalAssault);
        commander.executeBattle();

        // 場景二：在開闊平原作戰
        System.out.println("【場景二】來到開闊平原，敵軍缺乏掩護");
        commander.setStrategy(rangedSuppression);
        commander.executeBattle();

        // 場景三：夜戰環境
        System.out.println("【場景三】夜幕降臨，適合進行奇襲");
        commander.setStrategy(stealthRaid);
        commander.executeBattle();

        // 場景四：面對強大敵軍
        System.out.println("【場景四】敵軍實力強大，需要保存實力");
        commander.setStrategy(defensive);
        commander.executeBattle();

        /**output
        === 歡迎來到戰術大師的指揮帳 ===

        === 戰場分析 ===
        1. 敵軍兵力薄弱，士氣低落 → 建議使用正面突擊
        2. 地形開闊，敵軍無掩護 → 建議使用遠程壓制
        3. 夜戰環境，敵軍戒備鬆懈 → 建議使用奇襲策略
        4. 敵強我弱，需要拖延時間 → 建議使用防禦策略
        「面對不同的敵人，需有不同的戰法。」

        【場景一】偵查兵回報：敵軍士氣低落，防線鬆散
        艾瑞克 選擇了: 正面突擊策略
        策略說明: 適用於敵軍士氣低落或防線薄弱時使用

        === 戰術執行開始 ===
        指揮官 艾瑞克 下達作戰命令：
        騎兵集結完畢！
        重裝騎兵發起衝鋒，直取敵軍心臟！
        正面突破敵軍防線，勢如破竹！
        === 戰術執行完畢 ===

        【場景二】來到開闊平原，敵軍缺乏掩護
        艾瑞克 選擇了: 遠程壓制策略
        策略說明: 適用於地形開闊，敵軍缺乏掩護時使用

        === 戰術執行開始 ===
        指揮官 艾瑞克 下達作戰命令：
        弓箭手就位，魔法師開始詠唱！
        箭雨如暴風般傾瀉而下！
        魔法轟炸覆蓋戰場，敵軍陷入混亂！
        === 戰術執行完畢 ===

        【場景三】夜幕降臨，適合進行奇襲
        艾瑞克 選擇了: 奇襲策略
        策略說明: 適用於夜戰或敵軍戒備鬆懈時使用

        === 戰術執行開始 ===
        指揮官 艾瑞克 下達作戰命令：
        夜幕降臨，刺客小隊悄然行動！
        潛行繞後，直搗敵軍指揮部！
        斬首行動成功，敵軍群龍無首！
        === 戰術執行完畢 ===

        【場景四】敵軍實力強大，需要保存實力
        艾瑞克 選擇了: 防禦策略
        策略說明: 適用於敵強我弱或需要拖延時間時使用

        === 戰術執行開始 ===
        指揮官 艾瑞克 下達作戰命令：
        盾牆架起，長槍如林！
        堅守要塞，以逸待勞！
        消耗敵軍體力，等待反擊時機！
        === 戰術執行完畢 ===
        */
    }
}
```
