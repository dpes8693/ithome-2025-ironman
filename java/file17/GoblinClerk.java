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
