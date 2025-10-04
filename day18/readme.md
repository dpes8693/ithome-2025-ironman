# Day18 責任鏈模式 (Chain Of Responsibility Pattern)

## 擬人化角色：【幽暗地下城的護衛序列】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/17-ChainOfResponsibility.png)

- 種族： 哥布林、獸人、石像鬼、惡魔領主（形成序列）
- 外貌： 一個小小的哥布林斥候，手持一份發光的緊急報告，正把它遞給坐在低矮桌子後、忙碌處理文書的哥布林書記官。在他們上方的層層階梯和平台，分別駐守著體型更大的守衛，例如揮舞巨斧的獸人、長有雙翼的惡魔，以及最頂端坐鎮的石像鬼領主。所有層級之間都由發光的藍色能量線相連，代表著請求的傳遞路徑。
- 性格： 各司其職、遵守階級、不越權。每個守衛（處理者）只會處理自己權限範圍內的威脅或請求，無法處理的則自動上報給上級。
- 能力： 讓不同的物件有機會處理同一個請求。當小哥布林斥候（請求發起者）提交一份關於入侵者的報告時，這份報告會首先由哥布林書記官（低級處理者）審核。如果威脅很小，書記官就能處理（如召喚少量巡邏兵）。如果威脅超出其能力範圍，他會將報告轉呈給獸人戰士（中級處理者）。獸人戰士如果能處理就出動，否則繼續上報給惡魔（高級處理者），直至達到最高級別的石像鬼領主。
- 代表語： (哥布林書記官)：「這不是我的職責，向上級報告！」 (惡魔)：「小事不足掛齒，大事上報領主。」
- 背景故事： 在一個陰森的地下城深處，防禦體系是由一系列嚴密的護衛層級構成的。當任何地方發生異動或入侵時，最底層的哥布林巡邏兵會將警報報告給最近的哥布林書記官。書記官只負責處理輕微的騷動。如果入侵者實力較強，書記官就會將警報上報給駐守在通道中的獸人戰士，獸人負責迎擊中等威脅。如果威脅依然巨大，獸人則會將警報傳遞給更深層的惡魔守衛，甚至最終直達最高統治者——沉睡中的石像鬼領主。這個「護衛序列」確保了任何威脅都能被正確的級別處理，避免了低級守衛處理不來的狀況，也防止了高級守衛為小事分心。

---

## 範例

### Java

```java
//IntrusionReport.java
// 入侵報告
public class IntrusionReport {
    private String location;
    private int threatLevel;
    private String description;
    private String intruderType;

    public IntrusionReport(String location, int threatLevel, String description, String intruderType) {
        this.location = location;
        this.threatLevel = threatLevel;
        this.description = description;
        this.intruderType = intruderType;
    }

    public String getLocation() {
        return location;
    }

    public int getThreatLevel() {
        return threatLevel;
    }

    public String getDescription() {
        return description;
    }

    public String getIntruderType() {
        return intruderType;
    }

    @Override
    public String toString() {
        return String.format("【位置：%s】威脅等級：%d - %s（%s）",
                           location, threatLevel, description, intruderType);
    }
}
```

```java
//DungeonGuard.java
// 地下城護衛抽象類別
public abstract class DungeonGuard {
    protected DungeonGuard nextGuard;
    protected String guardType;
    protected int maxThreatLevel;

    public DungeonGuard(String guardType, int maxThreatLevel) {
        this.guardType = guardType;
        this.maxThreatLevel = maxThreatLevel;
    }

    // 設定下一個處理者
    public void setNextGuard(DungeonGuard nextGuard) {
        this.nextGuard = nextGuard;
    }

    // 處理入侵報告的模板方法
    public final void handleReport(IntrusionReport report) {
        System.out.println(guardType + " 收到報告：" + report);

        if (canHandle(report)) {
            processReport(report);
        } else {
            System.out.println(guardType + "：「這不是我的職責，向上級報告！」");

            if (nextGuard != null) {
                System.out.println("將報告轉呈給上級...\n");
                nextGuard.handleReport(report);
            } else {
                System.out.println("⚠️ 警告：沒有更高級的護衛可以處理此威脅！");
            }
        }
    }

    // 判斷是否能處理該報告
    protected boolean canHandle(IntrusionReport report) {
        return report.getThreatLevel() <= maxThreatLevel;
    }

    // 具體處理邏輯，由子類別實現
    protected abstract void processReport(IntrusionReport report);
}
```

```java
//GoblinClerk.java
// 哥布林書記官（低級處理者）
public class GoblinClerk extends DungeonGuard {

    public GoblinClerk() {
        super("哥布林書記官", 2);
    }

    @Override
    protected void processReport(IntrusionReport report) {
        System.out.println("📋 " + guardType + " 處理中...");
        System.out.println("派遣哥布林巡邏隊前往 " + report.getLocation());
        System.out.println("✅ 小規模威脅已成功處理！\n");
    }
}
```

```java
//OrcWarrior.java
// 獸人戰士（中級處理者）
public class OrcWarrior extends DungeonGuard {

    public OrcWarrior() {
        super("獸人戰士", 5);
    }

    @Override
    protected void processReport(IntrusionReport report) {
        System.out.println("⚔️ " + guardType + " 處理中...");
        System.out.println("舉起巨斧，親自前往 " + report.getLocation() + " 迎擊入侵者");
        System.out.println("「哼！區區 " + report.getIntruderType() + "，看我的斧頭厲害！」");
        System.out.println("✅ 中等威脅已成功壓制！\n");
    }
}
```

```java
//DemonGuard.java
// 惡魔守衛（高級處理者）
public class DemonGuard extends DungeonGuard {

    public DemonGuard() {
        super("惡魔守衛", 8);
    }

    @Override
    protected void processReport(IntrusionReport report) {
        System.out.println("🔥 " + guardType + " 處理中...");
        System.out.println("展開黑暗之翼，降臨至 " + report.getLocation());
        System.out.println("「愚蠢的 " + report.getIntruderType() + "，感受地獄之火的恐怖吧！」");
        System.out.println("釋放地獄烈焰，將入侵者化為灰燼");
        System.out.println("✅ 高等威脅已被徹底消滅！\n");
    }
}
```

```java
//GargoyleLord.java
// 石像鬼領主（最高級處理者）
public class GargoyleLord extends DungeonGuard {

    public GargoyleLord() {
        super("石像鬼領主", 10);
    }

    @Override
    protected void processReport(IntrusionReport report) {
        System.out.println("👑 " + guardType + " 甦醒！");
        System.out.println("「什麼？竟有如此強大的入侵者敢挑戰我的領域？」");
        System.out.println("石像鬼領主飛向 " + report.getLocation() + "，地下城為之震動");
        System.out.println("「" + report.getIntruderType() + "，你將為愚蠢的行為付出代價！」");
        System.out.println("釋放毀滅級法術，整個區域陷入黑暗");
        System.out.println("✅ 最高威脅已被親自處決！領主重新沉睡...\n");
    }
}
```

```java
//ChainOfResponsibilityExample.java
// 使用範例
public class ChainOfResponsibilityExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到幽暗地下城防禦系統 ===\n");

        // 建立護衛鏈
        DungeonGuard goblinClerk = new GoblinClerk();
        DungeonGuard orcWarrior = new OrcWarrior();
        DungeonGuard demonGuard = new DemonGuard();
        DungeonGuard gargoyleLord = new GargoyleLord();

        // 設定責任鏈
        goblinClerk.setNextGuard(orcWarrior);
        orcWarrior.setNextGuard(demonGuard);
        demonGuard.setNextGuard(gargoyleLord);

        System.out.println("🏰 護衛序列已就位：");
        System.out.println("哥布林書記官 → 獸人戰士 → 惡魔守衛 → 石像鬼領主\n");

        // 創建不同威脅等級的入侵報告
        IntrusionReport[] reports = {
            new IntrusionReport("北側通道", 1, "小規模騷動", "迷路的冒險者新手"),
            new IntrusionReport("東側大廳", 4, "中等規模入侵", "熟練的盜賊團"),
            new IntrusionReport("核心區域", 7, "高等威脅入侵", "強大的法師"),
            new IntrusionReport("王座廳", 10, "極度危險入侵", "傳說級英雄"),
            new IntrusionReport("禁忌之門", 15, "毀滅級威脅", "古代龍王")
        };

        // 處理每個報告
        for (int i = 0; i < reports.length; i++) {
            System.out.println("📢 第 " + (i + 1) + " 份緊急報告：");
            goblinClerk.handleReport(reports[i]);
            System.out.println("─".repeat(50));
        }

        System.out.println("=== 地下城防禦系統運行完畢 ===");
    }

    /**output
    === 歡迎來到幽暗地下城防禦系統 ===

    🏰 護衛序列已就位：
    哥布林書記官 → 獸人戰士 → 惡魔守衛 → 石像鬼領主

    📢 第 1 份緊急報告：
    哥布林書記官 收到報告：【位置：北側通道】威脅等級：1 - 小規模騷動（迷路的冒險者新手）
    📋 哥布林書記官 處理中...
    派遣哥布林巡邏隊前往 北側通道
    ✅ 小規模威脅已成功處理！

    ──────────────────────────────────────────────────────────────────────────────────────────────
    📢 第 2 份緊急報告：
    哥布林書記官 收到報告：【位置：東側大廳】威脅等級：4 - 中等規模入侵（熟練的盜賊團）
    哥布林書記官：「這不是我的職責，向上級報告！」
    將報告轉呈給上級...

    獸人戰士 收到報告：【位置：東側大廳】威脅等級：4 - 中等規模入侵（熟練的盜賊團）
    ⚔️ 獸人戰士 處理中...
    舉起巨斧，親自前往 東側大廳 迎擊入侵者
    「哼！區區 熟練的盜賊團，看我的斧頭厲害！」
    ✅ 中等威脅已成功壓制！

    ──────────────────────────────────────────────────────────────────────────────────────────────
    📢 第 3 份緊急報告：
    哥布林書記官 收到報告：【位置：核心區域】威脅等級：7 - 高等威脅入侵（強大的法師）
    哥布林書記官：「這不是我的職責，向上級報告！」
    將報告轉呈給上級...

    獸人戰士 收到報告：【位置：核心區域】威脅等級：7 - 高等威脅入侵（強大的法師）
    獸人戰士：「這不是我的職責，向上級報告！」
    將報告轉呈給上級...

    惡魔守衛 收到報告：【位置：核心區域】威脅等級：7 - 高等威脅入侵（強大的法師）
    🔥 惡魔守衛 處理中...
    展開黑暗之翼，降臨至 核心區域
    「愚蠢的 強大的法師，感受地獄之火的恐怖吧！」
    釋放地獄烈焰，將入侵者化為灰燼
    ✅ 高等威脅已被徹底消滅！

    ──────────────────────────────────────────────────────────────────────────────────────────────
    📢 第 4 份緊急報告：
    哥布林書記官 收到報告：【位置：王座廳】威脅等級：10 - 極度危險入侵（傳說級英雄）
    哥布林書記官：「這不是我的職責，向上級報告！」
    將報告轉呈給上級...

    獸人戰士 收到報告：【位置：王座廳】威脅等級：10 - 極度危險入侵（傳說級英雄）
    獸人戰士：「這不是我的職責，向上級報告！」
    將報告轉呈給上級...

    惡魔守衛 收到報告：【位置：王座廳】威脅等級：10 - 極度危險入侵（傳說級英雄）
    惡魔守衛：「這不是我的職責，向上級報告！」
    將報告轉呈給上級...

    石像鬼領主 收到報告：【位置：王座廳】威脅等級：10 - 極度危險入侵（傳說級英雄）
    👑 石像鬼領主 甦醒！
    「什麼？竟有如此強大的入侵者敢挑戰我的領域？」
    石像鬼領主飛向 王座廳，地下城為之震動
    「傳說級英雄，你將為愚蠢的行為付出代價！」
    釋放毀滅級法術，整個區域陷入黑暗
    ✅ 最高威脅已被親自處決！領主重新沉睡...

    ──────────────────────────────────────────────────────────────────────────────────────────────
    📢 第 5 份緊急報告：
    哥布林書記官 收到報告：【位置：禁忌之門】威脅等級：15 - 毀滅級威脅（古代龍王）
    哥布林書記官：「這不是我的職責，向上級報告！」
    將報告轉呈給上級...

    獸人戰士 收到報告：【位置：禁忌之門】威脅等級：15 - 毀滅級威脅（古代龍王）
    獸人戰士：「這不是我的職責，向上級報告！」
    將報告轉呈給上級...

    惡魔守衛 收到報告：【位置：禁忌之門】威脅等級：15 - 毀滅級威脅（古代龍王）
    惡魔守衛：「這不是我的職責，向上級報告！」
    將報告轉呈給上級...

    石像鬼領主 收到報告：【位置：禁忌之門】威脅等級：15 - 毀滅級威脅（古代龍王）
    石像鬼領主：「這不是我的職責，向上級報告！」
    ⚠️ 警告：沒有更高級的護衛可以處理此威脅！

    ──────────────────────────────────────────────────────────────────────────────────────────────
    === 地下城防禦系統運行完畢 ===

    */
}
```

### JavaScript

```javascript
// 入侵報告類別
class IntrusionReport {
  constructor(location, threatLevel, description, intruderType) {
    this.location = location;
    this.threatLevel = threatLevel;
    this.description = description;
    this.intruderType = intruderType;
  }

  getLocation() {
    return this.location;
  }

  getThreatLevel() {
    return this.threatLevel;
  }

  getDescription() {
    return this.description;
  }

  getIntruderType() {
    return this.intruderType;
  }

  toString() {
    return `【位置：${this.location}】威脅等級：${this.threatLevel} - ${this.description}（${this.intruderType}）`;
  }
}

// 地下城護衛抽象類別
class DungeonGuard {
  constructor(guardType, maxThreatLevel) {
    this.guardType = guardType;
    this.maxThreatLevel = maxThreatLevel;
    this.nextGuard = null;
  }

  // 設定下一個處理者
  setNextGuard(nextGuard) {
    this.nextGuard = nextGuard;
  }

  // 處理入侵報告的模板方法
  handleReport(report) {
    console.log(`${this.guardType} 收到報告：${report}`);

    if (this.canHandle(report)) {
      this.processReport(report);
    } else {
      console.log(`${this.guardType}：「這不是我的職責，向上級報告！」`);

      if (this.nextGuard) {
        console.log("將報告轉呈給上級...\n");
        this.nextGuard.handleReport(report);
      } else {
        console.log("⚠️ 警告：沒有更高級的護衛可以處理此威脅！");
      }
    }
  }

  // 判斷是否能處理該報告
  canHandle(report) {
    return report.getThreatLevel() <= this.maxThreatLevel;
  }

  // 具體處理邏輯，由子類別實現
  processReport(report) {
    throw new Error("子類必須實現 processReport 方法");
  }
}

// 哥布林書記官（低級處理者）
class GoblinClerk extends DungeonGuard {
  constructor() {
    super("哥布林書記官", 2);
  }

  processReport(report) {
    console.log(`📋 ${this.guardType} 處理中...`);
    console.log(`派遣哥布林巡邏隊前往 ${report.getLocation()}`);
    console.log("✅ 小規模威脅已成功處理！\n");
  }
}

// 獸人戰士（中級處理者）
class OrcWarrior extends DungeonGuard {
  constructor() {
    super("獸人戰士", 5);
  }

  processReport(report) {
    console.log(`⚔️ ${this.guardType} 處理中...`);
    console.log(`舉起巨斧，親自前往 ${report.getLocation()} 迎擊入侵者`);
    console.log(`「哼！區區 ${report.getIntruderType()}，看我的斧頭厲害！」`);
    console.log("✅ 中等威脅已成功壓制！\n");
  }
}

// 惡魔守衛（高級處理者）
class DemonGuard extends DungeonGuard {
  constructor() {
    super("惡魔守衛", 8);
  }

  processReport(report) {
    console.log(`🔥 ${this.guardType} 處理中...`);
    console.log(`展開黑暗之翼，降臨至 ${report.getLocation()}`);
    console.log(
      `「愚蠢的 ${report.getIntruderType()}，感受地獄之火的恐怖吧！」`
    );
    console.log("釋放地獄烈焰，將入侵者化為灰燼");
    console.log("✅ 高等威脅已被徹底消滅！\n");
  }
}

// 石像鬼領主（最高級處理者）
class GargoyleLord extends DungeonGuard {
  constructor() {
    super("石像鬼領主", 10);
  }

  processReport(report) {
    console.log(`👑 ${this.guardType} 甦醒！`);
    console.log("「什麼？竟有如此強大的入侵者敢挑戰我的領域？」");
    console.log(`石像鬼領主飛向 ${report.getLocation()}，地下城為之震動`);
    console.log(`「${report.getIntruderType()}，你將為愚蠢的行為付出代價！」`);
    console.log("釋放毀滅級法術，整個區域陷入黑暗");
    console.log("✅ 最高威脅已被親自處決！領主重新沉睡...\n");
  }
}

// 使用範例
console.log("=== 歡迎來到幽暗地下城防禦系統 ===\n");

// 建立護衛鏈
const goblinClerk = new GoblinClerk();
const orcWarrior = new OrcWarrior();
const demonGuard = new DemonGuard();
const gargoyleLord = new GargoyleLord();

// 設定責任鏈
goblinClerk.setNextGuard(orcWarrior);
orcWarrior.setNextGuard(demonGuard);
demonGuard.setNextGuard(gargoyleLord);

console.log("🏰 護衛序列已就位：");
console.log("哥布林書記官 → 獸人戰士 → 惡魔守衛 → 石像鬼領主\n");

// 創建不同威脅等級的入侵報告
const reports = [
  new IntrusionReport("北側通道", 1, "小規模騷動", "迷路的冒險者新手"),
  new IntrusionReport("東側大廳", 4, "中等規模入侵", "熟練的盜賊團"),
  new IntrusionReport("核心區域", 7, "高等威脅入侵", "強大的法師"),
  new IntrusionReport("王座廳", 10, "極度危險入侵", "傳說級英雄"),
  new IntrusionReport("禁忌之門", 15, "毀滅級威脅", "古代龍王"),
];

// 處理每個報告
reports.forEach((report, index) => {
  console.log(`📢 第 ${index + 1} 份緊急報告：`);
  goblinClerk.handleReport(report);
  console.log("─".repeat(50));
});

console.log("=== 地下城防禦系統運行完畢 ===");

/** output
=== 歡迎來到幽暗地下城防禦系統 ===

🏰 護衛序列已就位：
哥布林書記官 → 獸人戰士 → 惡魔守衛 → 石像鬼領主

📢 第 1 份緊急報告：
哥布林書記官 收到報告：【位置：北側通道】威脅等級：1 - 小規模騷動（迷路的冒險者新手）
📋 哥布林書記官 處理中...
派遣哥布林巡邏隊前往 北側通道
✅ 小規模威脅已成功處理！

──────────────────────────────────────────────────────────────────────────────────────────────
📢 第 2 份緊急報告：
哥布林書記官 收到報告：【位置：東側大廳】威脅等級：4 - 中等規模入侵（熟練的盜賊團）
哥布林書記官：「這不是我的職責，向上級報告！」
將報告轉呈給上級...

獸人戰士 收到報告：【位置：東側大廳】威脅等級：4 - 中等規模入侵（熟練的盜賊團）
⚔️ 獸人戰士 處理中...
舉起巨斧，親自前往 東側大廳 迎擊入侵者
「哼！區區 熟練的盜賊團，看我的斧頭厲害！」
✅ 中等威脅已成功壓制！

──────────────────────────────────────────────────────────────────────────────────────────────
📢 第 3 份緊急報告：
哥布林書記官 收到報告：【位置：核心區域】威脅等級：7 - 高等威脅入侵（強大的法師）
哥布林書記官：「這不是我的職責，向上級報告！」
將報告轉呈給上級...

獸人戰士 收到報告：【位置：核心區域】威脅等級：7 - 高等威脅入侵（強大的法師）
獸人戰士：「這不是我的職責，向上級報告！」
將報告轉呈給上級...

惡魔守衛 收到報告：【位置：核心區域】威脅等級：7 - 高等威脅入侵（強大的法師）
🔥 惡魔守衛 處理中...
展開黑暗之翼，降臨至 核心區域
「愚蠢的 強大的法師，感受地獄之火的恐怖吧！」
釋放地獄烈焰，將入侵者化為灰燼
✅ 高等威脅已被徹底消滅！

──────────────────────────────────────────────────────────────────────────────────────────────
📢 第 4 份緊急報告：
哥布林書記官 收到報告：【位置：王座廳】威脅等級：10 - 極度危險入侵（傳說級英雄）
哥布林書記官：「這不是我的職責，向上級報告！」
將報告轉呈給上級...

獸人戰士 收到報告：【位置：王座廳】威脅等級：10 - 極度危險入侵（傳說級英雄）
獸人戰士：「這不是我的職責，向上級報告！」
將報告轉呈給上級...

惡魔守衛 收到報告：【位置：王座廳】威脅等級：10 - 極度危險入侵（傳說級英雄）
惡魔守衛：「這不是我的職責，向上級報告！」
將報告轉呈給上級...

石像鬼領主 收到報告：【位置：王座廳】威脅等級：10 - 極度危險入侵（傳說級英雄）
👑 石像鬼領主 甦醒！
「什麼？竟有如此強大的入侵者敢挑戰我的領域？」
石像鬼領主飛向 王座廳，地下城為之震動
「傳說級英雄，你將為愚蠢的行為付出代價！」
釋放毀滅級法術，整個區域陷入黑暗
✅ 最高威脅已被親自處決！領主重新沉睡...

──────────────────────────────────────────────────────────────────────────────────────────────
📢 第 5 份緊急報告：
哥布林書記官 收到報告：【位置：禁忌之門】威脅等級：15 - 毀滅級威脅（古代龍王）
哥布林書記官：「這不是我的職責，向上級報告！」
將報告轉呈給上級...

獸人戰士 收到報告：【位置：禁忌之門】威脅等級：15 - 毀滅級威脅（古代龍王）
獸人戰士：「這不是我的職責，向上級報告！」
將報告轉呈給上級...

惡魔守衛 收到報告：【位置：禁忌之門】威脅等級：15 - 毀滅級威脅（古代龍王）
惡魔守衛：「這不是我的職責，向上級報告！」
將報告轉呈給上級...

石像鬼領主 收到報告：【位置：禁忌之門】威脅等級：15 - 毀滅級威脅（古代龍王）
石像鬼領主：「這不是我的職責，向上級報告！」
⚠️ 警告：沒有更高級的護衛可以處理此威脅！

──────────────────────────────────────────────────────────────────────────────────────────────
=== 地下城防禦系統運行完畢 ===

 */
```

## 小總結

Chain of Responsibility Pattern（責任鏈模式）就像我們故事中的幽暗地下城護衛序列，讓多個物件都有機會處理同一個請求

**核心特點：**

- **解耦請求者與處理者**：發送者不需要知道具體是誰處理請求
- **動態責任鏈**：可以在運行時動態改變鏈的結構和順序
- **單一職責**：每個處理者只負責處理自己能力範圍內的請求
- **靈活性**：容易新增或移除處理者，不影響其他部分

**實現方式：**

- **抽象處理者**：定義處理請求的介面和設定下一個處理者的方法
- **具體處理者**：實現具體的處理邏輯，決定是否處理或傳遞請求
- **模板方法**：在抽象類別中定義處理流程，子類別實現具體邏輯

**使用時機：**

- 有多個物件可以處理同一請求，但具體處理者在運行時才確定
- 希望在不明確指定接收者的情況下向多個物件中的某一個提交請求
- 處理請求的物件集合需要動態指定（如審批流程、事件處理系統）

**實際應用場景：**

- **審批系統**：請假申請根據金額由不同級別主管審批
- **異常處理**：不同類型的異常由不同的處理器處理
- **中間件模式**：Web 框架中的請求處理鏈（如 Express.js 的中間層）
- **事件處理**：GUI 中的事件冒泡機制

**注意事項：**

<!-- - 如果責任鏈過長，可能影響性能
- 需要確保至少有一個處理者能處理請求，否則請求可能丟失
- 調試時追蹤請求流程可能較困難 -->
- 要小心避免循環引用導致無限循環
