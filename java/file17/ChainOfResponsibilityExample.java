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
}
