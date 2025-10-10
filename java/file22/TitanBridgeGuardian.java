package file22;
//TitanBridgeGuardian.java
// 泰坦橋守護者（管理者）
public class TitanBridgeGuardian {
    private String guardianName;

    public TitanBridgeGuardian(String guardianName) {
        this.guardianName = guardianName;
    }

    // 展示所有可用的交通方式
    public void showAvailableTransports() {
        System.out.println("=== " + guardianName + " 的交通網絡 ===");
        System.out.println("1. 彩虹橋 - 穩固的陸地連接");
        System.out.println("2. 能量平台 - 自由的空中飛行");
        System.out.println("3. 神秘傳送門 - 瞬間的空間穿越");
        System.out.println("兩岸獨立，我為連通。");
    }

    // 創建旅行組合
    public Travel createTravel(String travelType, String transportType, String travelerName) {
        TravelImplementation implementation = createImplementation(transportType);
        
        if (implementation == null) {
            System.out.println("未知的交通工具類型: " + transportType);
            return null;
        }

        switch (travelType.toLowerCase()) {
            case "adventure":
                return new AdventureTravel(implementation, travelerName);
            case "diplomatic":
                return new DiplomaticTravel(implementation, travelerName, "和平談判");
            default:
                System.out.println("未知的旅行類型: " + travelType);
                return null;
        }
    }

    private TravelImplementation createImplementation(String transportType) {
        switch (transportType.toLowerCase()) {
            case "bridge":
                return new RainbowBridgeImpl();
            case "platform":
                return new EnergyPlatformImpl();
            case "portal":
                return new MysticPortalImpl();
            default:
                return null;
        }
    }
}
