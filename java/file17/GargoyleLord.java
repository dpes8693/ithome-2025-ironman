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
