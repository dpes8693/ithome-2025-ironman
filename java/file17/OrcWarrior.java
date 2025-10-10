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
