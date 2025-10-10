package file22;
//BridgeExample.java
// 使用範例
public class BridgeExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到泰坦橋守護者的領域 ===\n");

        // 創建泰坦橋守護者
        TitanBridgeGuardian guardian = new TitanBridgeGuardian("古老泰坦");
        guardian.showAvailableTransports();
        System.out.println();

        // 創建不同類型的旅行組合
        Travel adventureWithBridge = guardian.createTravel("adventure", "bridge", "勇者亞瑟");
        Travel diplomaticWithPlatform = guardian.createTravel("diplomatic", "platform", "智者梅林");
        Travel adventureWithPortal = guardian.createTravel("adventure", "portal", "法師甘道夫");

        System.out.println();

        // 執行冒險旅行（彩虹橋）
        if (adventureWithBridge != null) {
            adventureWithBridge.planJourney("龍之谷");
            adventureWithBridge.executeJourney("龍之谷");
            ((AdventureTravel) adventureWithBridge).encounterChallenge();
            System.out.println();
        }

        // 執行外交旅行（能量平台）
        if (diplomaticWithPlatform != null) {
            diplomaticWithPlatform.planJourney("精靈王國");
            diplomaticWithPlatform.executeJourney("精靈王國");
            ((DiplomaticTravel) diplomaticWithPlatform).conductNegotiation();
            System.out.println();
        }

        // 執行冒險旅行（神秘傳送門）
        if (adventureWithPortal != null) {
            adventureWithPortal.planJourney("黑暗之地");
            adventureWithPortal.executeJourney("黑暗之地");
            ((AdventureTravel) adventureWithPortal).encounterChallenge();
            System.out.println();
        }

        // 展示橋接模式的彈性
        demonstrateFlexibility();
    }

    private static void demonstrateFlexibility() {
        System.out.println("=== 橋接模式彈性展示 ===");
        System.out.println("同一個冒險者可以使用不同的交通工具：");
        
        String travelerName = "法師甘道夫";
        String destination = "火山島";
        
        // 同一個旅行者使用不同的實現
        TravelImplementation[] implementations = {
            new RainbowBridgeImpl(),
            new EnergyPlatformImpl(),
            new MysticPortalImpl()
        };
        
        for (TravelImplementation impl : implementations) {
            AdventureTravel travel = new AdventureTravel(impl, travelerName);
            System.out.println(travelerName + " 計劃使用 " + impl.getTransportType() + " 前往 " + destination);
        }
    }
}
