package file20;
//MediatorPatternExample.java
// 使用範例
public class MediatorPatternExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("🌃 歡迎來到未來智慧城市交通管制系統 🌃\n");
        
        // 創建智能交通指揮官
        SmartTrafficController controller = new SmartTrafficController();
        
        // 創建各種交通工具
        AutonomousCar car1 = new AutonomousCar("CAR-001", 1, controller);
        AutonomousCar car2 = new AutonomousCar("CAR-002", 2, controller);
        AutonomousCar ambulance = new AutonomousCar("AMB-001", 3, controller);
        FlyingVehicle drone1 = new FlyingVehicle("DRONE-001", 1, controller);
        FlyingVehicle airTaxi = new FlyingVehicle("TAXI-001", 2, controller);
        
        // 註冊到交通管制系統
        System.out.println("【階段1：車輛註冊】");
        controller.registerVehicle(car1);
        controller.registerVehicle(car2);
        controller.registerVehicle(ambulance);
        controller.registerVehicle(drone1);
        controller.registerVehicle(airTaxi);
        
        controller.displaySystemStatus();
        
        // 測試正常通行請求
        System.out.println("【階段2：正常通行請求】");
        car1.requestRoute("市中心");
        Thread.sleep(1000);
        
        car2.requestRoute("金融區"); // 與市中心衝突
        Thread.sleep(1000);
        
        drone1.requestRoute("機場");
        Thread.sleep(1000);
        
        System.out.println();
        
        // 測試緊急情況
        System.out.println("【階段3：緊急情況處理】");
        ambulance.reportEmergency("醫療緊急事件");
        Thread.sleep(4000); // 等待緊急狀況解除
        
        System.out.println();
        
        // 測試飛行器協調
        System.out.println("【階段4：飛行器協調】");
        airTaxi.requestRoute("國際區");
        Thread.sleep(1000);
        
        // 測試多車輛衝突
        System.out.println("【階段5：多車輛路線衝突】");
        car1.requestRoute("港口");
        car2.requestRoute("工業區");
        drone1.requestRoute("物流中心");
        Thread.sleep(2000);
        
        // 最終狀態
        controller.displaySystemStatus();
        
        // 移除部分車輛
        System.out.println("【階段6：車輛離開系統】");
        controller.removeVehicle(car1);
        controller.removeVehicle(drone1);
        
        controller.displaySystemStatus();
        
        System.out.println("🎯 智慧城市交通管制演示完成！");
    }
}
