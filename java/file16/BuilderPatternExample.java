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
        System.out.println("1. 建造標準戰士型機器人:");
        RobotBuilder warriorBuilder = new WarriorRobotBuilder();
        MechRobot warrior = titan.constructWarriorRobot(warriorBuilder);
        System.out.println();
        warrior.displaySpecs();
        warrior.activate();
        System.out.println();

        // 建造輕裝偵察機器人
        System.out.println("2. 建造輕裝偵察機器人:");
        RobotBuilder scoutBuilder = new ScoutRobotBuilder();
        MechRobot scout = titan.constructScoutRobot(scoutBuilder);
        System.out.println();
        scout.displaySpecs();
        scout.activate();
        System.out.println();

        // 建造基礎型機器人
        System.out.println("3. 建造基礎型機器人:");
        RobotBuilder basicBuilder = new WarriorRobotBuilder();
        MechRobot basic = titan.constructBasicRobot(basicBuilder);
        System.out.println();
        basic.displaySpecs();
        basic.activate();
        System.out.println();

        // 自訂建造
        System.out.println("4. 建造自訂機器人:");
        RobotBuilder customBuilder = new ScoutRobotBuilder();
        MechRobot custom = titan.constructCustomRobot(customBuilder, true, false, true);
        System.out.println();
        custom.displaySpecs();
        custom.activate();
    }
}
