# Day20 中介者模式 (Mediator Pattern)

## 擬人化角色：【智能交通指揮官】

![智能交通指揮官](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/19-Mediator.png)

- 種族： 人類
- 外貌： 一位身穿高科技制服、神情專注的指揮官，雙手懸浮在一個發光的控制台上方，無數藍色的光線從他的指尖延伸，連接到周圍的半透明顯示屏。這些屏幕上實時顯示著城市中各種飛行器、自動駕駛汽車和行人動態。他正從一個高聳的城市交通控制中心俯瞰著繁忙的賽博朋克都市夜景，窗外下著雨。
- 性格： 冷靜、高效、統籌全局。他本人不直接與每個交通工具或行人溝通，而是作為唯一的「中介」，協調所有交通參與者之間的互動，確保城市交通順暢且安全。
- 能力： 當有多個物件之間有交互作用時，使用一個中介物件來負責這些物件的交互。這位指揮官（中介者）是整個城市交通系統的核心。每一輛自動駕駛汽車、每一架飛行器、甚至智慧交通燈，都不會直接彼此溝通。所有關於路線、速度、避讓的請求都會先傳達給指揮官。指揮官接收到這些訊息後，會進行綜合判斷和調度，然後再將指令發送給相關的交通參與者。
- 代表語： 「萬千路徑，皆由我協調。」
- 背景故事： 在一個高度自動化的未來都市，交通堵塞和碰撞曾是巨大難題。直到這位智能交通指揮官，或稱為「中央調度 AI」被啟用。他不僅監控所有飛行器和地表車輛的實時位置，還能預測潛在的衝突，並提前發出指令。例如，當兩輛自動駕駛汽車可能在十字路口相撞時，它們不會直接互相發出警告，而是向指揮官匯報。指揮官會根據全局數據，判斷出最佳的避讓方案，並同時向兩輛車發出指令。這極大地簡化了各個交通物件之間的複雜通訊，將所有交互的複雜性都集中到了指揮官這個唯一的中心點上。

---

## 範例

### Java

```java
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

        System.out.println("🎯 智慧城市交通管制演示完成！");
    }
}
```

### JavaScript

```javascript
// 交通中介者介面
class TrafficMediator {
  // 註冊交通工具
  registerVehicle(vehicle) {
    throw new Error("子類必須實現 registerVehicle 方法");
  }

  // 移除交通工具
  removeVehicle(vehicle) {
    throw new Error("子類必須實現 removeVehicle 方法");
  }

  // 處理請求通行的訊息
  requestRoute(requester, destination, priority) {
    throw new Error("子類必須實現 requestRoute 方法");
  }

  // 處理緊急情況
  handleEmergency(vehicle, emergencyType) {
    throw new Error("子類必須實現 handleEmergency 方法");
  }

  // 通知所有相關車輛
  notifyVehicles(message, excludeList) {
    throw new Error("子類必須實現 notifyVehicles 方法");
  }

  // 獲取系統狀態
  displaySystemStatus() {
    throw new Error("子類必須實現 displaySystemStatus 方法");
  }
}

// 交通工具抽象類別
class TrafficVehicle {
  constructor(vehicleId, vehicleType, priority, mediator) {
    this.vehicleId = vehicleId;
    this.vehicleType = vehicleType;
    this.priority = priority; // 優先級：1-普通，2-商業，3-緊急
    this.mediator = mediator;
    this.currentLocation = "起始點";
  }

  // 請求通行路線
  requestRoute(destination) {
    console.log(
      `[${this.vehicleType}] ${this.vehicleId} 請求前往：${destination}`
    );
    this.mediator.requestRoute(this, destination, this.priority);
  }

  // 回應中介者的指令
  receiveInstruction(instruction) {
    throw new Error("子類必須實現 receiveInstruction 方法");
  }

  // 報告緊急情況
  reportEmergency(emergencyType) {
    console.log(
      `[緊急] ${this.vehicleType} ${this.vehicleId} 報告：${emergencyType}`
    );
    this.mediator.handleEmergency(this, emergencyType);
  }

  // 接收系統通知
  receiveNotification(message) {
    console.log(
      `[通知] ${this.vehicleType} ${this.vehicleId} 收到：${message}`
    );
  }

  // Getters and Setters
  getVehicleId() {
    return this.vehicleId;
  }
  getCurrentLocation() {
    return this.currentLocation;
  }
  getPriority() {
    return this.priority;
  }
  getVehicleType() {
    return this.vehicleType;
  }

  setCurrentLocation(location) {
    this.currentLocation = location;
  }
}

// 自動駕駛汽車
class AutonomousCar extends TrafficVehicle {
  constructor(vehicleId, priority, mediator) {
    super(vehicleId, "自動駕駛汽車", priority, mediator);
  }

  receiveInstruction(instruction) {
    console.log(`🚗 [執行] ${this.vehicleId} 接收指令：${instruction}`);

    // 模擬執行指令
    if (instruction.includes("等待")) {
      console.log("    → 啟動等待模式，引擎怠速");
    } else if (instruction.includes("前進")) {
      console.log("    → 加速前進，更新GPS路線");
    } else if (instruction.includes("讓道")) {
      console.log("    → 切換到慢車道，降低速度");
    } else if (instruction.includes("停車")) {
      console.log("    → 啟動緊急煞車，靠邊停車");
    }
  }
}

// 飛行器
class FlyingVehicle extends TrafficVehicle {
  constructor(vehicleId, priority, mediator) {
    super(vehicleId, "飛行器", priority, mediator);
    this.altitude = 0;
  }

  receiveInstruction(instruction) {
    console.log(`🚁 [執行] ${this.vehicleId} 接收指令：${instruction}`);

    // 模擬執行指令
    if (instruction.includes("升高")) {
      this.altitude += 100;
      console.log(`    → 上升至 ${this.altitude} 米高度`);
    } else if (instruction.includes("降低")) {
      this.altitude = Math.max(0, this.altitude - 100);
      console.log(`    → 下降至 ${this.altitude} 米高度`);
    } else if (instruction.includes("盤旋")) {
      console.log("    → 在當前位置盤旋等待");
    } else if (instruction.includes("緊急降落")) {
      console.log("    → 立即尋找安全降落點");
    }
  }

  getAltitude() {
    return this.altitude;
  }
}

// 智能交通指揮官（具體中介者）
class SmartTrafficController extends TrafficMediator {
  constructor() {
    super();
    this.vehicles = [];
    this.routeConflicts = new Map();
    this.pendingRequests = [];
    this.emergencyMode = false;

    this.initializeRouteConflicts();
  }

  initializeRouteConflicts() {
    // 定義可能衝突的路線
    this.routeConflicts.set("市中心", ["金融區", "商業區"]);
    this.routeConflicts.set("機場", ["國際區", "物流中心"]);
    this.routeConflicts.set("港口", ["工業區", "物流中心"]);
  }

  registerVehicle(vehicle) {
    this.vehicles.push(vehicle);
    console.log(
      `🎯 指揮官：${vehicle.getVehicleType()} ${vehicle.getVehicleId()} 已加入交通管制系統`
    );
  }

  removeVehicle(vehicle) {
    const index = this.vehicles.indexOf(vehicle);
    if (index > -1) {
      this.vehicles.splice(index, 1);
    }
    console.log(
      `📤 指揮官：${vehicle.getVehicleType()} ${vehicle.getVehicleId()} 已離開交通管制系統`
    );
  }

  requestRoute(requester, destination, priority) {
    console.log(
      `🎛️ 指揮官分析請求：${requester.getVehicleId()} → ${destination} (優先級: ${priority})`
    );

    if (this.emergencyMode && priority < 3) {
      requester.receiveInstruction("緊急狀態中，請等待通行許可");
      this.pendingRequests.push(`${requester.getVehicleId()}:${destination}`);
      return;
    }

    // 檢查路線衝突
    const conflictVehicles = this.checkRouteConflicts(destination);

    if (conflictVehicles.length === 0) {
      // 無衝突，直接通行
      this.grantRouteAccess(requester, destination);
    } else {
      // 有衝突，協調通行
      this.coordinateTraffic(requester, destination, conflictVehicles);
    }
  }

  checkRouteConflicts(destination) {
    const conflicts = [];
    const conflictRoutes = this.routeConflicts.get(destination);

    if (conflictRoutes) {
      for (const vehicle of this.vehicles) {
        if (conflictRoutes.includes(vehicle.getCurrentLocation())) {
          conflicts.push(vehicle);
        }
      }
    }

    return conflicts;
  }

  grantRouteAccess(vehicle, destination) {
    console.log("✅ 指揮官：路線暢通，准許通行");
    vehicle.receiveInstruction(`前進至 ${destination}，保持標準速度`);
    vehicle.setCurrentLocation(destination);
  }

  coordinateTraffic(requester, destination, conflictVehicles) {
    console.log("⚠️ 指揮官：發現路線衝突，開始協調...");

    // 根據優先級決定通行順序
    if (requester.getPriority() >= 3) {
      // 高優先級，其他車輛讓道
      for (const vehicle of conflictVehicles) {
        if (vehicle instanceof FlyingVehicle) {
          vehicle.receiveInstruction("升高高度，為緊急車輛讓道");
        } else {
          vehicle.receiveInstruction("讓道至次要路線，等待通行許可");
        }
      }
      this.grantRouteAccess(requester, destination);
    } else {
      // 普通優先級，等待或繞行
      if (conflictVehicles.length > 2) {
        requester.receiveInstruction("交通繁忙，建議繞行至替代路線");
      } else {
        requester.receiveInstruction("等待 30 秒後再次嘗試通行");
        this.pendingRequests.push(`${requester.getVehicleId()}:${destination}`);
      }
    }
  }

  handleEmergency(vehicle, emergencyType) {
    console.log("🚨 指揮官：收到緊急情況報告，啟動應急處理");
    this.emergencyMode = true;

    // 通知所有車輛緊急狀況
    const excludeList = [vehicle];
    this.notifyVehicles("系統進入緊急模式，所有非緊急車輛請讓道", excludeList);

    // 給緊急車輛特殊指令
    if (emergencyType.includes("醫療")) {
      vehicle.receiveInstruction("醫療緊急通道已開啟，直接前往目的地");
    } else if (emergencyType.includes("事故")) {
      vehicle.receiveInstruction("事故處理模式，謹慎通行");
    }

    // 其他車輛執行避讓
    for (const v of this.vehicles) {
      if (v !== vehicle) {
        if (v instanceof FlyingVehicle) {
          v.receiveInstruction("升高至安全高度，避讓緊急車輛");
        } else {
          v.receiveInstruction("靠邊停車，為緊急車輛讓道");
        }
      }
    }

    // 3秒後解除緊急模式（模擬）
    setTimeout(() => {
      this.emergencyMode = false;
      console.log("✅ 指揮官：緊急狀況已解除，恢復正常通行");
      this.processPendingRequests();
    }, 3000);
  }

  processPendingRequests() {
    console.log("📋 指揮官：處理待處理的通行請求...");
    while (this.pendingRequests.length > 0) {
      const request = this.pendingRequests.shift();
      const [vehicleId, destination] = request.split(":");
      // 這裡簡化處理，實際應該找到對應的車輛物件
      console.log(`✅ 准許 ${vehicleId} 前往 ${destination}`);
    }
  }

  notifyVehicles(message, excludeList = []) {
    for (const vehicle of this.vehicles) {
      if (!excludeList.includes(vehicle)) {
        vehicle.receiveNotification(message);
      }
    }
  }

  displaySystemStatus() {
    console.log("\n" + "═".repeat(60));
    console.log("🎛️ 智能交通控制系統狀態報告");
    console.log("═".repeat(60));
    console.log(`緊急模式：${this.emergencyMode ? "🔴 啟動" : "🟢 正常"}`);
    console.log(`管理車輛數：${this.vehicles.length}`);
    console.log(`待處理請求：${this.pendingRequests.length}`);

    console.log("\n車輛詳情：");
    for (const vehicle of this.vehicles) {
      console.log(
        `  • ${vehicle.getVehicleType()} ${vehicle.getVehicleId()} - 位置：${vehicle.getCurrentLocation()} (優先級：${vehicle.getPriority()})`
      );
    }
    console.log("═".repeat(60) + "\n");
  }
}

// 使用範例
async function runExample() {
  console.log("🌃 歡迎來到未來智慧城市交通管制系統 🌃\n");

  // 創建智能交通指揮官
  const controller = new SmartTrafficController();

  // 創建各種交通工具
  const car1 = new AutonomousCar("CAR-001", 1, controller);
  const car2 = new AutonomousCar("CAR-002", 2, controller);
  const ambulance = new AutonomousCar("AMB-001", 3, controller);
  const drone1 = new FlyingVehicle("DRONE-001", 1, controller);
  const airTaxi = new FlyingVehicle("TAXI-001", 2, controller);

  // 註冊到交通管制系統
  console.log("【階段1：車輛註冊】");
  controller.registerVehicle(car1);
  controller.registerVehicle(car2);
  controller.registerVehicle(ambulance);
  controller.registerVehicle(drone1);
  controller.registerVehicle(airTaxi);

  controller.displaySystemStatus();

  // 測試正常通行請求
  console.log("【階段2：正常通行請求】");
  car1.requestRoute("市中心");
  await sleep(1000);

  car2.requestRoute("金融區"); // 與市中心衝突
  await sleep(1000);

  drone1.requestRoute("機場");
  await sleep(1000);

  console.log("");

  console.log("🎯 智慧城市交通管制演示完成！");
}

// 輔助函數
function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

// 執行範例
runExample();

/** output
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
✅ 指揮官：路線暢通，准許通行
🚗 [執行] CAR-002 接收指令：前進至 金融區，保持標準速度
    → 加速前進，更新GPS路線

[飛行器] DRONE-001 請求前往：機場
🎛️ 指揮官分析請求：DRONE-001 → 機場 (優先級: 1)
✅ 指揮官：路線暢通，准許通行
🚁 [執行] DRONE-001 接收指令：前進至 機場，保持標準速度

🎯 智慧城市交通管制演示完成！

 */
```

## 小總結

Mediator Pattern（中介者模式）就像我們故事中的智能交通指揮官，透過一個中介物件來協調多個物件之間的互動關係

**核心特點：**

- **解耦物件間的直接通信**：各物件不直接相互引用，而是透過中介者通信
- **集中控制交互邏輯**：所有複雜的交互邏輯都集中在中介者中
- **降低系統耦合度**：物件之間的依賴關係從多對多變成一對多
- **提高系統可維護性**：修改交互邏輯只需要修改中介者

**主要組件：**

- **抽象中介者**：定義同事物件與中介者之間的通信介面（`TrafficMediator`）
- **具體中介者**：實現抽象中介者，協調各同事物件的交互（`SmartTrafficController`）
- **抽象同事類別**：定義同事物件的基本功能（`TrafficVehicle`）
- **具體同事類別**：實現具體的業務邏輯，透過中介者與其他同事交互（`AutonomousCar`、`FlyingVehicle`）

**使用時機：**

- 一組物件之間的通信方式複雜，導致相互依賴關係混亂
- 想要複用某個物件，但該物件與其他多個物件緊密耦合
- 需要定制在多個類別中分布的行為，又不想產生太多子類別
- 物件間的交互邏輯複雜且經常變化

**實際應用場景：**

- **聊天室系統**：聊天室作為中介者，協調用戶之間的訊息傳遞
- **工作流引擎**：協調各個工作步驟之間的執行順序
- **MVC 架構中的 Controller**：協調 Model 和 View 之間的交互
- **消息佇列系統**：作為生產者和消費者之間的中介
- **航空交通管制**：協調多架飛機的飛行路線和時間

<!-- **優點：**

- 減少類別間的依賴，將多對多的依賴關係轉化為一對多
- 各個同事類別可以獨立變化和重用
- 符合迪米特法則（最少知識原則）
- 集中控制邏輯，便於理解和維護

**缺點：**

- 中介者可能變得過於複雜，承擔過多責任
- 中介者本身可能成為系統的瓶頸
- 系統中多了一個中介者類別，增加了系統複雜度
- 如果設計不當，中介者可能變成一個"上帝類別" -->

**與其他模式的關係：**

- **觀察者模式(Observer Pattern)**：中介者模式可以使用觀察者模式來實現通知機制
- **外觀模式(Facade Pattern)**：都有簡化複雜系統的作用，但中介者重在協調，外觀重在簡化介面
- **命令模式(Command Pattern)**：可以結合使用，將請求封裝成命令透過中介者傳遞
