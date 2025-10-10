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
