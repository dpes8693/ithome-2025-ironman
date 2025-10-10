package file14;

// 真實的秘密特工（RealSubject）
public class RealSecretAgent implements SecretAgent {
    private String codeName;
    private String location;
    private boolean onMission;

    public RealSecretAgent(String codeName, String location) {
        this.codeName = codeName;
        this.location = location;
        this.onMission = false;
        System.out.println("🕵️ 真實特工 " + codeName + " 已就位於 " + location);
    }

    @Override
    public void executeTask(String taskName) {
        this.onMission = true;
        System.out.println("🎯 " + codeName + " 正在執行高級任務：" + taskName);
        System.out.println("⚠️  風險警告：真實身份可能暴露！");
        
        // 模擬任務執行時間
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("✅ 任務完成：" + taskName);
        this.onMission = false;
    }

    @Override
    public String gatherIntelligence(String target) {
        System.out.println("🔍 " + codeName + " 親自收集關於 " + target + " 的機密情報");
        return "機密等級A：" + target + " 的詳細檔案";
    }

    @Override
    public void reportStatus() {
        String status = onMission ? "執行任務中" : "待命中";
        System.out.println("📋 " + codeName + " 狀態報告：" + status + " (位置：" + location + ")");
    }

    @Override
    public boolean isAvailable() {
        return !onMission;
    }
}