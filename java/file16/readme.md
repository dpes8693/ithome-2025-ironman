
```java
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

    // 建構子設為 package-private，只有 Builder 可以建立
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
        System.out.println("🏗️  機體框架：" + (bodyFrame != null ? bodyFrame : "未安裝"));
        System.out.println("⚡ 動力核心：" + (powerCore != null ? powerCore : "未安裝"));
        System.out.println("🔫 武器系統：" + (weaponSystem != null ? weaponSystem : "未安裝"));
        System.out.println("🧠 AI模組：" + (aiModule != null ? aiModule : "未安裝"));
        System.out.println("🛡️  護盾系統：" + (shield != null ? shield : "未安裝"));
        System.out.println("🦵 移動系統：" + (mobility != null ? mobility : "未安裝"));
    }

    // 啟動機器人
    public void activate() {
        System.out.println("⚡ 機械機器人啟動中...");
        if (bodyFrame != null && powerCore != null) {
            System.out.println("✅ 機器人成功啟動！");
            if (aiModule != null) {
                System.out.println("🧠 AI系統上線：" + aiModule);
            }
            if (weaponSystem != null) {
                System.out.println("🔫 武器系統就緒：" + weaponSystem);
            }
        } else {
            System.out.println("❌ 啟動失敗：缺少基本組件");
        }
    }
}
```

```java
package file16;

//RobotBuilder.java
// 機器人建造者介面
public interface RobotBuilder {
    RobotBuilder buildBodyFrame();
    RobotBuilder buildPowerCore();
    RobotBuilder buildWeaponSystem();
    RobotBuilder buildAiModule();
    RobotBuilder buildShield();
    RobotBuilder buildMobility();
    MechRobot getResult();
}
```

```java
package file16;

//WarriorRobotBuilder.java
// 戰士型機器人建造者
public class WarriorRobotBuilder implements RobotBuilder {
    private MechRobot robot;

    public WarriorRobotBuilder() {
        this.robot = new MechRobot();
        System.out.println("🔨 開始建造戰士型機器人");
    }

    @Override
    public RobotBuilder buildBodyFrame() {
        robot.setBodyFrame("重裝甲戰鬥機體");
        System.out.println("🏗️  安裝重裝甲戰鬥機體");
        return this;
    }

    @Override
    public RobotBuilder buildPowerCore() {
        robot.setPowerCore("高輸出反應爐");
        System.out.println("⚡ 安裝高輸出反應爐");
        return this;
    }

    @Override
    public RobotBuilder buildWeaponSystem() {
        robot.setWeaponSystem("重型火炮系統");
        System.out.println("🔫 安裝重型火炮系統");
        return this;
    }

    @Override
    public RobotBuilder buildAiModule() {
        robot.setAiModule("戰術指揮AI");
        System.out.println("🧠 安裝戰術指揮AI");
        return this;
    }

    @Override
    public RobotBuilder buildShield() {
        robot.setShield("能量護盾產生器");
        System.out.println("🛡️  安裝能量護盾產生器");
        return this;
    }

    @Override
    public RobotBuilder buildMobility() {
        robot.setMobility("重型履帶系統");
        System.out.println("🦵 安裝重型履帶系統");
        return this;
    }

    @Override
    public MechRobot getResult() {
        System.out.println("✅ 戰士型機器人建造完成！");
        return robot;
    }
}
```

```java
package file16;

//ScoutRobotBuilder.java
// 偵察型機器人建造者
public class ScoutRobotBuilder implements RobotBuilder {
    private MechRobot robot;

    public ScoutRobotBuilder() {
        this.robot = new MechRobot();
        System.out.println("🔨 開始建造偵察型機器人");
    }

    @Override
    public RobotBuilder buildBodyFrame() {
        robot.setBodyFrame("輕量化機動機體");
        System.out.println("🏗️  安裝輕量化機動機體");
        return this;
    }

    @Override
    public RobotBuilder buildPowerCore() {
        robot.setPowerCore("高效能電池組");
        System.out.println("⚡ 安裝高效能電池組");
        return this;
    }

    @Override
    public RobotBuilder buildWeaponSystem() {
        robot.setWeaponSystem("輕型雷射武器");
        System.out.println("🔫 安裝輕型雷射武器");
        return this;
    }

    @Override
    public RobotBuilder buildAiModule() {
        robot.setAiModule("偵察分析AI");
        System.out.println("🧠 安裝偵察分析AI");
        return this;
    }

    @Override
    public RobotBuilder buildShield() {
        robot.setShield("光學迷彩系統");
        System.out.println("🛡️  安裝光學迷彩系統");
        return this;
    }

    @Override
    public RobotBuilder buildMobility() {
        robot.setMobility("高速飛行推進器");
        System.out.println("🦵 安裝高速飛行推進器");
        return this;
    }

    @Override
    public MechRobot getResult() {
        System.out.println("✅ 偵察型機器人建造完成！");
        return robot;
    }
}
```

```java
package file16;

//MechTitanDirector.java
// 機械泰坦指揮者
public class MechTitanDirector {
    private String name;

    public MechTitanDirector(String name) {
        this.name = name;
        System.out.println("🤖 機械泰坦 " + name + " 啟動建造程序");
    }

    // 建造標準戰士型機器人
    public MechRobot constructWarriorRobot(RobotBuilder builder) {
        System.out.println("⚡ " + name + " 開始建造標準戰士型機器人");
        return builder
                .buildBodyFrame()
                .buildPowerCore()
                .buildWeaponSystem()
                .buildAiModule()
                .buildShield()
                .buildMobility()
                .getResult();
    }

    // 建造輕裝偵察機器人
    public MechRobot constructScoutRobot(RobotBuilder builder) {
        System.out.println("⚡ " + name + " 開始建造輕裝偵察機器人");
        return builder
                .buildBodyFrame()
                .buildPowerCore()
                .buildAiModule()
                .buildShield()      // 偵察機器人需要隱蔽能力
                .buildMobility()    // 偵察機器人需要高機動性
                .getResult();       // 不安裝重型武器
    }

    // 建造基礎型機器人
    public MechRobot constructBasicRobot(RobotBuilder builder) {
        System.out.println("⚡ " + name + " 開始建造基礎型機器人");
        return builder
                .buildBodyFrame()
                .buildPowerCore()
                .buildAiModule()
                .getResult();       // 只安裝基本組件
    }

    // 自訂建造流程
    public MechRobot constructCustomRobot(RobotBuilder builder, boolean needWeapons, 
                                         boolean needShield, boolean needMobility) {
        System.out.println("⚡ " + name + " 開始建造自訂機器人");
        
        // 基本組件
        builder.buildBodyFrame().buildPowerCore().buildAiModule();
        
        // 可選組件
        if (needWeapons) {
            builder.buildWeaponSystem();
        }
        if (needShield) {
            builder.buildShield();
        }
        if (needMobility) {
            builder.buildMobility();
        }
        
        return builder.getResult();
    }

    // 展示建造能力
    public void showConstructionCapabilities() {
        System.out.println("🏭 === " + name + " 的建造能力 ===");
        System.out.println("⚔️  標準戰士型：全功能戰鬥機器人");
        System.out.println("👁️  輕裝偵察型：高機動偵察機器人");
        System.out.println("🔧 基礎型：基本功能機器人");
        System.out.println("🎯 自訂型：依需求客製化");
        System.out.println("「每一個偉大的創造，都始於精確的組裝。」");
    }
}
```

```java
package file16;

//BuilderPatternExample.java
// 使用範例
public class BuilderPatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到機械泰坦的建造工廠 ===\n");

        // 創建機械泰坦指揮者
        MechTitanDirector titan = new MechTitanDirector("泰坦-001");
        titan.showConstructionCapabilities();
        System.out.println();

        // 建造標準戰士型機器人
        System.out.println("1. 建造標準戰士型機器人：");
        RobotBuilder warriorBuilder = new WarriorRobotBuilder();
        MechRobot warrior = titan.constructWarriorRobot(warriorBuilder);
        System.out.println();
        warrior.displaySpecs();
        warrior.activate();
        System.out.println();

        // 建造輕裝偵察機器人
        System.out.println("2. 建造輕裝偵察機器人：");
        RobotBuilder scoutBuilder = new ScoutRobotBuilder();
        MechRobot scout = titan.constructScoutRobot(scoutBuilder);
        System.out.println();
        scout.displaySpecs();
        scout.activate();
        System.out.println();

        // 建造基礎型機器人
        System.out.println("3. 建造基礎型機器人：");
        RobotBuilder basicBuilder = new WarriorRobotBuilder();
        MechRobot basic = titan.constructBasicRobot(basicBuilder);
        System.out.println();
        basic.displaySpecs();
        basic.activate();
        System.out.println();

        // 自訂建造
        System.out.println("4. 建造自訂機器人：");
        RobotBuilder customBuilder = new ScoutRobotBuilder();
        MechRobot custom = titan.constructCustomRobot(customBuilder, true, false, true);
        System.out.println();
        custom.displaySpecs();
        custom.activate();

        /**output
        === 歡迎來到機械泰坦的建造工廠 ===

        🤖 機械泰坦 泰坦-001 啟動建造程序
        🏭 === 泰坦-001 的建造能力 ===
        ⚔️  標準戰士型：全功能戰鬥機器人
        👁️  輕裝偵察型：高機動偵察機器人
        🔧 基礎型：基本功能機器人
        🎯 自訂型：依需求客製化
        「每一個偉大的創造，都始於精確的組裝。」

        1. 建造標準戰士型機器人：
        🔨 開始建造戰士型機器人
        ⚡ 泰坦-001 開始建造標準戰士型機器人
        🏗️  安裝重裝甲戰鬥機體
        ⚡ 安裝高輸出反應爐
        🔫 安裝重型火炮系統
        🧠 安裝戰術指揮AI
        🛡️  安裝能量護盾產生器
        🦵 安裝重型履帶系統
        ✅ 戰士型機器人建造完成！

        🤖 === 機械機器人規格 ===
        🏗️  機體框架：重裝甲戰鬥機體
        ⚡ 動力核心：高輸出反應爐
        🔫 武器系統：重型火炮系統
        🧠 AI模組：戰術指揮AI
        🛡️  護盾系統：能量護盾產生器
        🦵 移動系統：重型履帶系統
        ⚡ 機械機器人啟動中...
        ✅ 機器人成功啟動！
        🧠 AI系統上線：戰術指揮AI
        🔫 武器系統就緒：重型火炮系統

        2. 建造輕裝偵察機器人：
        🔨 開始建造偵察型機器人
        ⚡ 泰坦-001 開始建造輕裝偵察機器人
        🏗️  安裝輕量化機動機體
        ⚡ 安裝高效能電池組
        🧠 安裝偵察分析AI
        🛡️  安裝光學迷彩系統
        🦵 安裝高速飛行推進器
        ✅ 偵察型機器人建造完成！

        🤖 === 機械機器人規格 ===
        🏗️  機體框架：輕量化機動機體
        ⚡ 動力核心：高效能電池組
        🔫 武器系統：未安裝
        🧠 AI模組：偵察分析AI
        🛡️  護盾系統：光學迷彩系統
        🦵 移動系統：高速飛行推進器
        ⚡ 機械機器人啟動中...
        ✅ 機器人成功啟動！
        🧠 AI系統上線：偵察分析AI

        3. 建造基礎型機器人：
        🔨 開始建造戰士型機器人
        ⚡ 泰坦-001 開始建造基礎型機器人
        🏗️  安裝重裝甲戰鬥機體
        ⚡ 安裝高輸出反應爐
        🧠 安裝戰術指揮AI
        ✅ 戰士型機器人建造完成！

        🤖 === 機械機器人規格 ===
        🏗️  機體框架：重裝甲戰鬥機體
        ⚡ 動力核心：高輸出反應爐
        🔫 武器系統：未安裝
        🧠 AI模組：戰術指揮AI
        🛡️  護盾系統：未安裝
        🦵 移動系統：未安裝
        ⚡ 機械機器人啟動中...
        ✅ 機器人成功啟動！
        🧠 AI系統上線：戰術指揮AI

        4. 建造自訂機器人：
        🔨 開始建造偵察型機器人
        ⚡ 泰坦-001 開始建造自訂機器人
        🏗️  安裝輕量化機動機體
        ⚡ 安裝高效能電池組
        🧠 安裝偵察分析AI
        🔫 安裝輕型雷射武器
        🦵 安裝高速飛行推進器
        ✅ 偵察型機器人建造完成！

        🤖 === 機械機器人規格 ===
        🏗️  機體框架：輕量化機動機體
        ⚡ 動力核心：高效能電池組
        🔫 武器系統：輕型雷射武器
        🧠 AI模組：偵察分析AI
        🛡️  護盾系統：未安裝
        🦵 移動系統：高速飛行推進器
        ⚡ 機械機器人啟動中...
        ✅ 機器人成功啟動！
        🧠 AI系統上線：偵察分析AI
        🔫 武器系統就緒：輕型雷射武器
        */
    }
}
```