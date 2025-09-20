# Day6 策略模式 (Strategy Pattern)

## 擬人化角色：【多變的戰術大師】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/5-Strategy.png)

- 種族： 人類
- 外貌： 一位身穿輕甲的年輕指揮官，神情堅毅，手指著一張發光的戰術桌。桌面上方漂浮著多個代表不同戰術（騎兵衝鋒、弓箭手齊射、魔法空襲、潛行突襲）的全息影像。他身處一個簡樸的戰術帳篷內，窗外是陰沉的戰場。
- 性格： 冷靜、靈活、深謀遠慮。他從不拘泥於一種作戰方式，而是會根據不同的戰況和敵人，選擇最合適的「策略」來應對。
- 能力： 將各種可互換的演算法（策略 Strategy）包裝成一個類別。這位指揮官（上下文）會根據戰場情況（如地形、敵方兵力、天氣）來選擇不同的「作戰策略」。他可以選擇「正面突擊策略」（使用騎兵衝鋒）、或「遠程壓制策略」（使用弓箭手和魔法空襲）、或「奇襲策略」（夜間潛行突襲）。每種策略都是一個獨立的作戰計畫，可以隨時更換，但指揮官的目標始終是取得勝利。
- 代表語： 「面對不同的敵人，需有不同的戰法。」
- 背景故事： 在一個長期戰亂的時代，這位戰術大師以其多變的戰術聞名。他的部隊規模並非最大，但總能以出其不意的策略克敵制勝。在與獸人作戰時，他會選擇「重甲防禦」的策略；而面對精靈弓箭手時，他則會換成「快速突襲」的策略。他深知沒有一種策略是萬能的，只有根據實際情況靈活選擇和替換策略，才能在瞬息萬變的戰場上立於不敗之地。

---

## 範例

### Java

```java
//BattleStrategy.java
// 戰術策略介面
public interface BattleStrategy {
    void execute();
    String getStrategyName();
    String getDescription();
}
```

```java
//FrontalAssaultStrategy.java
// 正面突擊策略
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

### JavaScript

```javascript
// 戰術策略基類
class BattleStrategy {
  execute() {
    throw new Error("子類必須實現 execute 方法");
  }

  getStrategyName() {
    throw new Error("子類必須實現 getStrategyName 方法");
  }

  getDescription() {
    throw new Error("子類必須實現 getDescription 方法");
  }
}

// 正面突擊策略
class FrontalAssaultStrategy extends BattleStrategy {
  execute() {
    console.log("騎兵集結完畢！");
    console.log("重裝騎兵發起衝鋒，直取敵軍心臟！");
    console.log("正面突破敵軍防線，勢如破竹！");
  }

  getStrategyName() {
    return "正面突擊策略";
  }

  getDescription() {
    return "適用於敵軍士氣低落或防線薄弱時使用";
  }
}

// 遠程壓制策略
class RangedSuppressionStrategy extends BattleStrategy {
  execute() {
    console.log("弓箭手就位，魔法師開始詠唱！");
    console.log("箭雨如暴風般傾瀉而下！");
    console.log("魔法轟炸覆蓋戰場，敵軍陷入混亂！");
  }

  getStrategyName() {
    return "遠程壓制策略";
  }

  getDescription() {
    return "適用於地形開闊，敵軍缺乏掩護時使用";
  }
}

// 奇襲策略
class StealthRaidStrategy extends BattleStrategy {
  execute() {
    console.log("夜幕降臨，刺客小隊悄然行動！");
    console.log("潛行繞後，直搗敵軍指揮部！");
    console.log("斬首行動成功，敵軍群龍無首！");
  }

  getStrategyName() {
    return "奇襲策略";
  }

  getDescription() {
    return "適用於夜戰或敵軍戒備鬆懈時使用";
  }
}

// 防禦策略
class DefensiveStrategy extends BattleStrategy {
  execute() {
    console.log("盾牆架起，長槍如林！");
    console.log("堅守要塞，以逸待勞！");
    console.log("消耗敵軍體力，等待反擊時機！");
  }

  getStrategyName() {
    return "防禦策略";
  }

  getDescription() {
    return "適用於敵強我弱或需要拖延時間時使用";
  }
}

// 戰術大師指揮官
class TacticalCommander {
  constructor(name) {
    this.currentStrategy = null;
    this.commanderName = name;
  }

  // 設定戰術策略
  setStrategy(strategy) {
    this.currentStrategy = strategy;
    console.log(`${this.commanderName} 選擇了: ${strategy.getStrategyName()}`);
    console.log(`策略說明: ${strategy.getDescription()}`);
  }

  // 執行當前策略
  executeBattle() {
    if (!this.currentStrategy) {
      console.log("警告：尚未選擇作戰策略！");
      return;
    }

    console.log("\n=== 戰術執行開始 ===");
    console.log(`指揮官 ${this.commanderName} 下達作戰命令：`);
    this.currentStrategy.execute();
    console.log("=== 戰術執行完畢 ===\n");
  }

  // 分析戰況並建議策略
  static analyzeBattlefield() {
    console.log("=== 戰場分析 ===");
    console.log("1. 敵軍兵力薄弱，士氣低落 → 建議使用正面突擊");
    console.log("2. 地形開闊，敵軍無掩護 → 建議使用遠程壓制");
    console.log("3. 夜戰環境，敵軍戒備鬆懈 → 建議使用奇襲策略");
    console.log("4. 敵強我弱，需要拖延時間 → 建議使用防禦策略");
    console.log("「面對不同的敵人，需有不同的戰法。」\n");
  }
}

// 使用範例
console.log("=== 歡迎來到戰術大師的指揮帳 ===\n");

// 創建指揮官
const commander = new TacticalCommander("艾瑞克");

// 分析戰場情況
TacticalCommander.analyzeBattlefield();

// 創建不同的策略
const frontalAssault = new FrontalAssaultStrategy();
const rangedSuppression = new RangedSuppressionStrategy();
const stealthRaid = new StealthRaidStrategy();
const defensive = new DefensiveStrategy();

// 場景一：面對士氣低落的敵軍
console.log("【場景一】偵查兵回報：敵軍士氣低落，防線鬆散");
commander.setStrategy(frontalAssault);
commander.executeBattle();

// 場景二：在開闊平原作戰
console.log("【場景二】來到開闊平原，敵軍缺乏掩護");
commander.setStrategy(rangedSuppression);
commander.executeBattle();

// 場景三：夜戰環境
console.log("【場景三】夜幕降臨，適合進行奇襲");
commander.setStrategy(stealthRaid);
commander.executeBattle();

// 場景四：面對強大敵軍
console.log("【場景四】敵軍實力強大，需要保存實力");
commander.setStrategy(defensive);
commander.executeBattle();

/** output
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
```

## 小總結

Strategy 設計模式就像我們故事中的多變戰術大師，透過`可互換的演算法策略`來應對不同的情況

**核心特點：**

- **演算法封裝**：將不同的演算法或行為封裝成獨立的策略類別
- **動態切換**：在執行時期可以自由切換不同的策略
- **開放封閉**：對擴展開放（新增策略），對修改封閉（不改變現有程式碼）
- **消除條件分支**：可避免大量的 if-else 或 switch-case 語句

**使用時機：**

- 有多種方式完成同一個任務時（ex: 不同的排序演算法、支付方式、壓縮演算法）
- 需要在執行時期動態選擇演算法（ex: 根據檔案大小選擇不同的處理策略）
- 想要避免複雜的條件判斷邏輯（ex: 會員等級對應不同折扣計算）
- 適合在策略之間差異明顯且需要頻繁切換的場景使用

**與其他模式的區別：**

- **vs Simple Factory**：Factory 著重於物件創建，Strategy 著重於行為選擇
<!-- - **vs State Pattern**：Strategy 由外部決定策略，State 由內部狀態驅動轉換
- **vs Command Pattern**：Strategy 封裝演算法，Command 封裝請求操作 -->