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
