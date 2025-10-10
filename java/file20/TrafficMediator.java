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
