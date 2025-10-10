package file20;
//FlyingVehicle.java
// 飛行器
public class FlyingVehicle extends TrafficVehicle {
    private int altitude;
    
    public FlyingVehicle(String vehicleId, int priority, TrafficMediator mediator) {
        super(vehicleId, "飛行器", priority, mediator);
        this.altitude = 0;
    }
    
    @Override
    public void receiveInstruction(String instruction) {
        System.out.println(String.format("🚁 [執行] %s 接收指令：%s", vehicleId, instruction));
        
        // 模擬執行指令
        if (instruction.contains("升高")) {
            altitude += 100;
            System.out.println("    → 上升至 " + altitude + " 米高度");
        } else if (instruction.contains("降低")) {
            altitude = Math.max(0, altitude - 100);
            System.out.println("    → 下降至 " + altitude + " 米高度");
        } else if (instruction.contains("盤旋")) {
            System.out.println("    → 在當前位置盤旋等待");
        } else if (instruction.contains("緊急降落")) {
            System.out.println("    → 立即尋找安全降落點");
        }
    }
    
    public int getAltitude() { return altitude; }
}
