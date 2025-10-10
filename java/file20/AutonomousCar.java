package file20;
//AutonomousCar.java
// 自動駕駛汽車
public class AutonomousCar extends TrafficVehicle {
    
    public AutonomousCar(String vehicleId, int priority, TrafficMediator mediator) {
        super(vehicleId, "自動駕駛汽車", priority, mediator);
    }
    
    @Override
    public void receiveInstruction(String instruction) {
        System.out.println(String.format("🚗 [執行] %s 接收指令：%s", vehicleId, instruction));
        
        // 模擬執行指令
        if (instruction.contains("等待")) {
            System.out.println("    → 啟動等待模式，引擎怠速");
        } else if (instruction.contains("前進")) {
            System.out.println("    → 加速前進，更新GPS路線");
        } else if (instruction.contains("讓道")) {
            System.out.println("    → 切換到慢車道，降低速度");
        } else if (instruction.contains("停車")) {
            System.out.println("    → 啟動緊急煞車，靠邊停車");
        }
    }
}
