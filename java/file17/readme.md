```java
//IntrusionReport.java
package file17;
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
package file17;
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
package file17;
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
package file17;
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
package file17;
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
package file17;
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
package file17;
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