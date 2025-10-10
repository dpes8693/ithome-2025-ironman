
```java
package file22;
//TravelImplementation.java
// 旅行實現介面（實現部分）
public interface TravelImplementation {
    void startJourney();
    void travel(String destination);
    void endJourney();
    String getTransportType();
}
```

```java
package file22;
//RainbowBridgeImpl.java
// 彩虹橋實現（陸地交通）
public class RainbowBridgeImpl implements TravelImplementation {
    @Override
    public void startJourney() {
        System.out.println("踏上彩虹橋，感受七彩光芒的指引...");
    }

    @Override
    public void travel(String destination) {
        System.out.println("沿著彩虹橋穩步前進，目標: " + destination);
        System.out.println("橋面在腳下閃爍著美麗的光彩");
    }

    @Override
    public void endJourney() {
        System.out.println("安全抵達目的地，彩虹橋逐漸消散");
    }

    @Override
    public String getTransportType() {
        return "彩虹橋";
    }
}
```

```java
package file22;
//EnergyPlatformImpl.java
// 能量平台實現（空中交通）
public class EnergyPlatformImpl implements TravelImplementation {
    @Override
    public void startJourney() {
        System.out.println("啟動能量平台，浮空引擎開始運轉...");
    }

    @Override
    public void travel(String destination) {
        System.out.println("駕駛能量平台飛向: " + destination);
        System.out.println("平台在空中劃出璀璨的能量軌跡");
    }

    @Override
    public void endJourney() {
        System.out.println("能量平台緩緩降落，引擎停止運轉");
    }

    @Override
    public String getTransportType() {
        return "能量平台";
    }
}
```

```java
package file22;
//MysticPortalImpl.java
// 神秘傳送門實現（瞬間傳送）
public class MysticPortalImpl implements TravelImplementation {
    @Override
    public void startJourney() {
        System.out.println("神秘傳送門開始發光，空間開始扭曲...");
    }

    @Override
    public void travel(String destination) {
        System.out.println("通過傳送門瞬間抵達: " + destination);
        System.out.println("感受到時空穿梭的神奇力量");
    }

    @Override
    public void endJourney() {
        System.out.println("傳送門關閉，空間恢復平靜");
    }

    @Override
    public String getTransportType() {
        return "神秘傳送門";
    }
}
```

```java
package file22;
//Travel.java
// 旅行抽象類（抽象部分）
public abstract class Travel {
    protected TravelImplementation implementation;

    public Travel(TravelImplementation implementation) {
        this.implementation = implementation;
    }

    // 基本旅行方法
    public void executeJourney(String destination) {
        implementation.startJourney();
        implementation.travel(destination);
        implementation.endJourney();
    }

    // 抽象方法，由子類實現
    public abstract void planJourney(String destination);
    public abstract String getTravelType();
}
```

```java
package file22;
//AdventureTravel.java
// 冒險旅行（精煉抽象）
public class AdventureTravel extends Travel {
    private String adventurerName;

    public AdventureTravel(TravelImplementation implementation, String adventurerName) {
        super(implementation);
        this.adventurerName = adventurerName;
    }

    @Override
    public void planJourney(String destination) {
        System.out.println("=== 冒險旅行計劃 ===");
        System.out.println("冒險者: " + adventurerName);
        System.out.println("目的地: " + destination);
        System.out.println("交通工具: " + implementation.getTransportType());
        System.out.println("準備冒險裝備和補給...");
    }

    @Override
    public String getTravelType() {
        return "冒險旅行";
    }

    public void encounterChallenge() {
        System.out.println(adventurerName + " 遭遇挑戰，使用 " + implementation.getTransportType() + " 的優勢應對！");
    }
}
```

```java
package file22;
//DiplomaticTravel.java
// 外交旅行（精煉抽象）
public class DiplomaticTravel extends Travel {
    private String diplomatName;
    private String mission;

    public DiplomaticTravel(TravelImplementation implementation, String diplomatName, String mission) {
        super(implementation);
        this.diplomatName = diplomatName;
        this.mission = mission;
    }

    @Override
    public void planJourney(String destination) {
        System.out.println("=== 外交旅行計劃 ===");
        System.out.println("外交官: " + diplomatName);
        System.out.println("目的地: " + destination);
        System.out.println("任務: " + mission);
        System.out.println("交通工具: " + implementation.getTransportType());
        System.out.println("準備外交文件和禮品...");
    }

    @Override
    public String getTravelType() {
        return "外交旅行";
    }

    public void conductNegotiation() {
        System.out.println(diplomatName + " 通過 " + implementation.getTransportType() + " 展現威嚴，進行 " + mission);
    }
}
```

```java
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
```

```java
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

        /**output
        === 歡迎來到泰坦橋守護者的領域 ===

        === 古老泰坦 的交通網絡 ===
        1. 彩虹橋 - 穩固的陸地連接
        2. 能量平台 - 自由的空中飛行
        3. 神秘傳送門 - 瞬間的空間穿越
        兩岸獨立，我為連通。

        === 冒險旅行計劃 ===
        冒險者: 勇者亞瑟
        目的地: 龍之谷
        交通工具: 彩虹橋
        準備冒險裝備和補給...
        踏上彩虹橋，感受七彩光芒的指引...
        沿著彩虹橋穩步前進，目標: 龍之谷
        橋面在腳下閃爍著美麗的光彩
        安全抵達目的地，彩虹橋逐漸消散
        勇者亞瑟 遭遇挑戰，使用 彩虹橋 的優勢應對！

        === 外交旅行計劃 ===
        外交官: 智者梅林
        目的地: 精靈王國
        任務: 和平談判
        交通工具: 能量平台
        準備外交文件和禮品...
        啟動能量平台，浮空引擎開始運轉...
        駕駛能量平台飛向: 精靈王國
        平台在空中劃出璀璨的能量軌跡
        能量平台緩緩降落，引擎停止運轉
        智者梅林 通過 能量平台 展現威嚴，進行 和平談判

        === 冒險旅行計劃 ===
        冒險者: 法師甘道夫
        目的地: 黑暗之地
        交通工具: 神秘傳送門
        準備冒險裝備和補給...
        神秘傳送門開始發光，空間開始扭曲...
        通過傳送門瞬間抵達: 黑暗之地
        感受到時空穿梭的神奇力量
        傳送門關閉，空間恢復平靜
        法師甘道夫 遭遇挑戰，使用 神秘傳送門 的優勢應對！

        === 橋接模式彈性展示 ===
        同一個冒險者可以使用不同的交通工具：
        法師甘道夫 計劃使用 彩虹橋 前往 火山島
        法師甘道夫 計劃使用 能量平台 前往 火山島
        法師甘道夫 計劃使用 神秘傳送門 前往 火山島
        */
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
```