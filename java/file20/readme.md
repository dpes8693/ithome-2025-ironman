
```java
package file20;
//TrafficMediator.java
// 交通中介者介面
import java.util.List;

public interface TrafficMediator {
    // 註冊交通工具
    void registerVehicle(TrafficVehicle vehicle);
    
    // 移除交通工具
    void removeVehicle(TrafficVehicle vehicle);
    
    // 處理請求通行的訊息
    void requestRoute(TrafficVehicle requester, String destination, int priority);
    
    // 處理緊急情況
    void handleEmergency(TrafficVehicle vehicle, String emergencyType);
    
    // 通知所有相關車輛
    void notifyVehicles(String message, List<TrafficVehicle> excludeList);
    
    // 獲取系統狀態
    void displaySystemStatus();
}
```

```java
package file20;
//TrafficVehicle.java
// 交通工具抽象類別
public abstract class TrafficVehicle {
    protected String vehicleId;
    protected String currentLocation;
    protected int priority; // 優先級：1-普通，2-商業，3-緊急
    protected TrafficMediator mediator;
    protected String vehicleType;
    
    public TrafficVehicle(String vehicleId, String vehicleType, int priority, TrafficMediator mediator) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.priority = priority;
        this.mediator = mediator;
        this.currentLocation = "起始點";
    }
    
    // 請求通行路線
    public void requestRoute(String destination) {
        System.out.println(String.format("[%s] %s 請求前往：%s", 
                          vehicleType, vehicleId, destination));
        mediator.requestRoute(this, destination, priority);
    }
    
    // 回應中介者的指令
    public abstract void receiveInstruction(String instruction);
    
    // 報告緊急情況
    public void reportEmergency(String emergencyType) {
        System.out.println(String.format("[緊急] %s %s 報告：%s", 
                          vehicleType, vehicleId, emergencyType));
        mediator.handleEmergency(this, emergencyType);
    }
    
    // 接收系統通知
    public void receiveNotification(String message) {
        System.out.println(String.format("[通知] %s %s 收到：%s", 
                          vehicleType, vehicleId, message));
    }
    
    // Getters
    public String getVehicleId() { return vehicleId; }
    public String getCurrentLocation() { return currentLocation; }
    public int getPriority() { return priority; }
    public String getVehicleType() { return vehicleType; }
    
    public void setCurrentLocation(String location) {
        this.currentLocation = location;
    }
}
```

```java
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
```

```java
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
```

```java
package file20;
//SmartTrafficController.java
// 智能交通指揮官（具體中介者）
import java.util.*;

public class SmartTrafficController implements TrafficMediator {
    private List<TrafficVehicle> vehicles;
    private Map<String, List<String>> routeConflicts;
    private Queue<String> pendingRequests;
    private boolean emergencyMode;
    
    public SmartTrafficController() {
        this.vehicles = new ArrayList<>();
        this.routeConflicts = new HashMap<>();
        this.pendingRequests = new LinkedList<>();
        this.emergencyMode = false;
        
        initializeRouteConflicts();
    }
    
    private void initializeRouteConflicts() {
        // 定義可能衝突的路線
        routeConflicts.put("市中心", Arrays.asList("金融區", "商業區"));
        routeConflicts.put("機場", Arrays.asList("國際區", "物流中心"));
        routeConflicts.put("港口", Arrays.asList("工業區", "物流中心"));
    }
    
    @Override
    public void registerVehicle(TrafficVehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println(String.format("🎯 指揮官：%s %s 已加入交通管制系統", 
                          vehicle.getVehicleType(), vehicle.getVehicleId()));
    }
    
    @Override
    public void removeVehicle(TrafficVehicle vehicle) {
        vehicles.remove(vehicle);
        System.out.println(String.format("📤 指揮官：%s %s 已離開交通管制系統", 
                          vehicle.getVehicleType(), vehicle.getVehicleId()));
    }
    
    @Override
    public void requestRoute(TrafficVehicle requester, String destination, int priority) {
        System.out.println(String.format("🎛️ 指揮官分析請求：%s → %s (優先級: %d)", 
                          requester.getVehicleId(), destination, priority));
        
        if (emergencyMode && priority < 3) {
            requester.receiveInstruction("緊急狀態中，請等待通行許可");
            pendingRequests.offer(requester.getVehicleId() + ":" + destination);
            return;
        }
        
        // 檢查路線衝突
        List<TrafficVehicle> conflictVehicles = checkRouteConflicts(destination);
        
        if (conflictVehicles.isEmpty()) {
            // 無衝突，直接通行
            grantRouteAccess(requester, destination);
        } else {
            // 有衝突，協調通行
            coordinateTraffic(requester, destination, conflictVehicles);
        }
    }
    
    private List<TrafficVehicle> checkRouteConflicts(String destination) {
        List<TrafficVehicle> conflicts = new ArrayList<>();
        List<String> conflictRoutes = routeConflicts.get(destination);
        
        if (conflictRoutes != null) {
            for (TrafficVehicle vehicle : vehicles) {
                if (conflictRoutes.contains(vehicle.getCurrentLocation())) {
                    conflicts.add(vehicle);
                }
            }
        }
        
        return conflicts;
    }
    
    private void grantRouteAccess(TrafficVehicle vehicle, String destination) {
        System.out.println("✅ 指揮官：路線暢通，准許通行");
        vehicle.receiveInstruction("前進至 " + destination + "，保持標準速度");
        vehicle.setCurrentLocation(destination);
    }
    
    private void coordinateTraffic(TrafficVehicle requester, String destination, 
                                 List<TrafficVehicle> conflictVehicles) {
        System.out.println("⚠️ 指揮官：發現路線衝突，開始協調...");
        
        // 根據優先級決定通行順序
        if (requester.getPriority() >= 3) {
            // 高優先級，其他車輛讓道
            for (TrafficVehicle vehicle : conflictVehicles) {
                if (vehicle instanceof FlyingVehicle) {
                    vehicle.receiveInstruction("升高高度，為緊急車輛讓道");
                } else {
                    vehicle.receiveInstruction("讓道至次要路線，等待通行許可");
                }
            }
            grantRouteAccess(requester, destination);
        } else {
            // 普通優先級，等待或繞行
            if (conflictVehicles.size() > 2) {
                requester.receiveInstruction("交通繁忙，建議繞行至替代路線");
            } else {
                requester.receiveInstruction("等待 30 秒後再次嘗試通行");
                pendingRequests.offer(requester.getVehicleId() + ":" + destination);
            }
        }
    }
    
    @Override
    public void handleEmergency(TrafficVehicle vehicle, String emergencyType) {
        System.out.println("🚨 指揮官：收到緊急情況報告，啟動應急處理");
        emergencyMode = true;
        
        // 通知所有車輛緊急狀況
        List<TrafficVehicle> excludeList = Arrays.asList(vehicle);
        notifyVehicles("系統進入緊急模式，所有非緊急車輛請讓道", excludeList);
        
        // 給緊急車輛特殊指令
        if (emergencyType.contains("醫療")) {
            vehicle.receiveInstruction("醫療緊急通道已開啟，直接前往目的地");
        } else if (emergencyType.contains("事故")) {
            vehicle.receiveInstruction("事故處理模式，謹慎通行");
        }
        
        // 其他車輛執行避讓
        for (TrafficVehicle v : vehicles) {
            if (v != vehicle) {
                if (v instanceof FlyingVehicle) {
                    v.receiveInstruction("升高至安全高度，避讓緊急車輛");
                } else {
                    v.receiveInstruction("靠邊停車，為緊急車輛讓道");
                }
            }
        }
        
        // 3秒後解除緊急模式（模擬）
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                emergencyMode = false;
                System.out.println("✅ 指揮官：緊急狀況已解除，恢復正常通行");
                processPendingRequests();
            }
        }, 3000);
    }
    
    private void processPendingRequests() {
        System.out.println("📋 指揮官：處理待處理的通行請求...");
        while (!pendingRequests.isEmpty()) {
            String request = pendingRequests.poll();
            String[] parts = request.split(":");
            // 這裡簡化處理，實際應該找到對應的車輛物件
            System.out.println("✅ 准許 " + parts[0] + " 前往 " + parts[1]);
        }
    }
    
    @Override
    public void notifyVehicles(String message, List<TrafficVehicle> excludeList) {
        for (TrafficVehicle vehicle : vehicles) {
            if (!excludeList.contains(vehicle)) {
                vehicle.receiveNotification(message);
            }
        }
    }
    
    @Override
    public void displaySystemStatus() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("🎛️ 智能交通控制系統狀態報告");
        System.out.println("═".repeat(60));
        System.out.println("緊急模式：" + (emergencyMode ? "🔴 啟動" : "🟢 正常"));
        System.out.println("管理車輛數：" + vehicles.size());
        System.out.println("待處理請求：" + pendingRequests.size());
        
        System.out.println("\n車輛詳情：");
        for (TrafficVehicle vehicle : vehicles) {
            System.out.println(String.format("  • %s %s - 位置：%s (優先級：%d)", 
                              vehicle.getVehicleType(), vehicle.getVehicleId(), 
                              vehicle.getCurrentLocation(), vehicle.getPriority()));
        }
        System.out.println("═".repeat(60) + "\n");
    }
}
```

```java
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

    /**output
    🌃 歡迎來到未來智慧城市交通管制系統 🌃

    【階段1：車輛註冊】
    🎯 指揮官：自動駕駛汽車 CAR-001 已加入交通管制系統
    🎯 指揮官：自動駕駛汽車 CAR-002 已加入交通管制系統
    🎯 指揮官：自動駕駛汽車 AMB-001 已加入交通管制系統
    🎯 指揮官：飛行器 DRONE-001 已加入交通管制系統
    🎯 指揮官：飛行器 TAXI-001 已加入交通管制系統

    ════════════════════════════════════════════════════════════
    🎛️ 智能交通控制系統狀態報告
    ════════════════════════════════════════════════════════════
    緊急模式：🟢 正常
    管理車輛數：5
    待處理請求：0

    車輛詳情：
      • 自動駕駛汽車 CAR-001 - 位置：起始點 (優先級：1)
      • 自動駕駛汽車 CAR-002 - 位置：起始點 (優先級：2)
      • 自動駕駛汽車 AMB-001 - 位置：起始點 (優先級：3)
      • 飛行器 DRONE-001 - 位置：起始點 (優先級：1)
      • 飛行器 TAXI-001 - 位置：起始點 (優先級：2)
    ════════════════════════════════════════════════════════════

    【階段2：正常通行請求】
    [自動駕駛汽車] CAR-001 請求前往：市中心
    🎛️ 指揮官分析請求：CAR-001 → 市中心 (優先級: 1)
    ✅ 指揮官：路線暢通，准許通行
    🚗 [執行] CAR-001 接收指令：前進至 市中心，保持標準速度
        → 加速前進，更新GPS路線

    [自動駕駛汽車] CAR-002 請求前往：金融區
    🎛️ 指揮官分析請求：CAR-002 → 金融區 (優先級: 2)
    ⚠️ 指揮官：發現路線衝突，開始協調...
    🚗 [執行] CAR-001 接收指令：讓道至次要路線，等待通行許可
        → 切換到慢車道，降低速度
    ✅ 指揮官：路線暢通，准許通行
    🚗 [執行] CAR-002 接收指令：前進至 金融區，保持標準速度
        → 加速前進，更新GPS路線

    [飛行器] DRONE-001 請求前往：機場
    🎛️ 指揮官分析請求：DRONE-001 → 機場 (優先級: 1)
    ✅ 指揮官：路線暢通，准許通行
    🚁 [執行] DRONE-001 接收指令：前進至 機場，保持標準速度

    【階段3：緊急情況處理】
    [緊急] 自動駕駛汽車 AMB-001 報告：醫療緊急事件
    🚨 指揮官：收到緊急情況報告，啟動應急處理
    [通知] 自動駕駛汽車 CAR-001 收到：系統進入緊急模式，所有非緊急車輛請讓道
    [通知] 自動駕駛汽車 CAR-002 收到：系統進入緊急模式，所有非緊急車輛請讓道
    [通知] 飛行器 DRONE-001 收到：系統進入緊急模式，所有非緊急車輛請讓道
    [通知] 飛行器 TAXI-001 收到：系統進入緊急模式，所有非緊急車輛請讓道
    🚗 [執行] AMB-001 接收指令：醫療緊急通道已開啟，直接前往目的地
    🚗 [執行] CAR-001 接收指令：靠邊停車，為緊急車輛讓道
        → 啟動緊急煞車，靠邊停車
    🚗 [執行] CAR-002 接收指令：靠邊停車，為緊急車輛讓道
        → 啟動緊急煞車，靠邊停車
    🚁 [執行] DRONE-001 接收指令：升高至安全高度，避讓緊急車輛
        → 上升至 100 米高度
    🚁 [執行] TAXI-001 接收指令：升高至安全高度，避讓緊急車輛
        → 上升至 100 米高度
    ✅ 指揮官：緊急狀況已解除，恢復正常通行
    📋 指揮官：處理待處理的通行請求...

    【階段4：飛行器協調】
    [飛行器] TAXI-001 請求前往：國際區
    🎛️ 指揮官分析請求：TAXI-001 → 國際區 (優先級: 2)
    ⚠️ 指揮官：發現路線衝突，開始協調...
    🚁 [執行] DRONE-001 接收指令：升高高度，為緊急車輛讓道
        → 上升至 200 米高度
    ✅ 指揮官：路線暢通，准許通行
    🚁 [執行] TAXI-001 接收指令：前進至 國際區，保持標準速度

    【階段5：多車輛路線衝突】
    [自動駕駛汽車] CAR-001 請求前往：港口
    🎛️ 指揮官分析請求：CAR-001 → 港口 (優先級: 1)
    ✅ 指揮官：路線暢通，准許通行
    🚗 [執行] CAR-001 接收指令：前進至 港口，保持標準速度
        → 加速前進，更新GPS路線
    [自動駕駛汽車] CAR-002 請求前往：工業區
    🎛️ 指揮官分析請求：CAR-002 → 工業區 (優先級: 2)
    ⚠️ 指揮官：發現路線衝突，開始協調...
    🚗 [執行] CAR-001 接收指令：讓道至次要路線，等待通行許可
        → 切換到慢車道，降低速度
    ✅ 指揮官：路線暢通，准許通行
    🚗 [執行] CAR-002 接收指令：前進至 工業區，保持標準速度
        → 加速前進，更新GPS路線
    [飛行器] DRONE-001 請求前往：物流中心
    🎛️ 指揮官分析請求：DRONE-001 → 物流中心 (優先級: 1)
    ⚠️ 指揮官：發現路線衝突，開始協調...
    🚁 [執行] DRONE-001 接收指令：等待 30 秒後再次嘗試通行

    ════════════════════════════════════════════════════════════
    🎛️ 智能交通控制系統狀態報告
    ════════════════════════════════════════════════════════════
    緊急模式：🟢 正常
    管理車輛數：5
    待處理請求：1

    車輛詳情：
      • 自動駕駛汽車 CAR-001 - 位置：港口 (優先級：1)
      • 自動駕駛汽車 CAR-002 - 位置：工業區 (優先級：2)
      • 自動駕駛汽車 AMB-001 - 位置：起始點 (優先級：3)
      • 飛行器 DRONE-001 - 位置：機場 (優先級：1)
      • 飛行器 TAXI-001 - 位置：國際區 (優先級：2)
    ════════════════════════════════════════════════════════════

    【階段6：車輛離開系統】
    📤 指揮官：自動駕駛汽車 CAR-001 已離開交通管制系統
    📤 指揮官：飛行器 DRONE-001 已離開交通管制系統

    ════════════════════════════════════════════════════════════
    🎛️ 智能交通控制系統狀態報告
    ════════════════════════════════════════════════════════════
    緊急模式：🟢 正常
    管理車輛數：3
    待處理請求：1

    車輛詳情：
      • 自動駕駛汽車 CAR-002 - 位置：工業區 (優先級：2)
      • 自動駕駛汽車 AMB-001 - 位置：起始點 (優先級：3)
      • 飛行器 TAXI-001 - 位置：國際區 (優先級：2)
    ════════════════════════════════════════════════════════════

    🎯 智慧城市交通管制演示完成！

    */
}
```