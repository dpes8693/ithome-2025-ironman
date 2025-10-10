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
        System.out.println("⚔️  標準戰士型:全功能戰鬥機器人");
        System.out.println("👁️  輕裝偵察型:高機動偵察機器人");
        System.out.println("🔧 基礎型:基本功能機器人");
        System.out.println("🎯 自訂型:依需求客製化");
        System.out.println("「每一個偉大的創造,都始於精確的組裝。」");
    }
}
