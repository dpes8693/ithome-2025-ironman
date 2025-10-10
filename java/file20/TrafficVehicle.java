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
