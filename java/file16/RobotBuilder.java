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
