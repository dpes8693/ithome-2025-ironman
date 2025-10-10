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
