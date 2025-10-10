package file16;

//MechRobot.java
// 機械機器人產品類
public class MechRobot {
    private String bodyFrame;      // 機體框架
    private String powerCore;      // 動力核心
    private String weaponSystem;   // 武器系統
    private String aiModule;       // 人工智慧模組
    private String shield;         // 護盾系統
    private String mobility;       // 移動系統

    // 建構子設為 package-private,只有 Builder 可以建立
    MechRobot() {}

    // Getter 方法
    public String getBodyFrame() { return bodyFrame; }
    public String getPowerCore() { return powerCore; }
    public String getWeaponSystem() { return weaponSystem; }
    public String getAiModule() { return aiModule; }
    public String getShield() { return shield; }
    public String getMobility() { return mobility; }

    // Setter 方法 (package-private)
    void setBodyFrame(String bodyFrame) { this.bodyFrame = bodyFrame; }
    void setPowerCore(String powerCore) { this.powerCore = powerCore; }
    void setWeaponSystem(String weaponSystem) { this.weaponSystem = weaponSystem; }
    void setAiModule(String aiModule) { this.aiModule = aiModule; }
    void setShield(String shield) { this.shield = shield; }
    void setMobility(String mobility) { this.mobility = mobility; }

    // 展示機器人規格
    public void displaySpecs() {
        System.out.println("🤖 === 機械機器人規格 ===");
        System.out.println("🏗️  機體框架:" + (bodyFrame != null ? bodyFrame : "未安裝"));
        System.out.println("⚡ 動力核心:" + (powerCore != null ? powerCore : "未安裝"));
        System.out.println("🔫 武器系統:" + (weaponSystem != null ? weaponSystem : "未安裝"));
        System.out.println("🧠 AI模組:" + (aiModule != null ? aiModule : "未安裝"));
        System.out.println("🛡️  護盾系統:" + (shield != null ? shield : "未安裝"));
        System.out.println("🦵 移動系統:" + (mobility != null ? mobility : "未安裝"));
    }

    // 啟動機器人
    public void activate() {
        System.out.println("⚡ 機械機器人啟動中...");
        if (bodyFrame != null && powerCore != null) {
            System.out.println("✅ 機器人成功啟動!");
            if (aiModule != null) {
                System.out.println("🧠 AI系統上線:" + aiModule);
            }
            if (weaponSystem != null) {
                System.out.println("🔫 武器系統就緒:" + weaponSystem);
            }
        } else {
            System.out.println("❌ 啟動失敗:缺少基本組件");
        }
    }
}
