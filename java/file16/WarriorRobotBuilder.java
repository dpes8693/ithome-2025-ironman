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
        System.out.println("✅ 戰士型機器人建造完成!");
        return robot;
    }
}
