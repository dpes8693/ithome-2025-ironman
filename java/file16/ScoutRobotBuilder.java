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
        System.out.println("✅ 偵察型機器人建造完成!");
        return robot;
    }
}
