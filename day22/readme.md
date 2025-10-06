# Day22 橋接模式 (Bridge Pattern)

## 擬人化角色：【穩重的彩虹橋守護者】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/21-Bridge.png)

- 種族： 泰坦/古神
- 外貌： 一位由岩石和藤蔓構成的巨型泰坦神祇，他穩穩地屹立於兩座高峰之間。他的雙手分別連接到兩個發光的、不同材質的浮空平台，平台下方是一座跨越峽谷的彩虹橋。一道道發光的能量線從他身上延伸，連結著橋面和平台。
- 性格： 沉穩、堅定、注重分離與獨立。他扮演著「橋樑」的角色，將抽象的設計與具體的實現分開，讓兩者都能獨立發展而不相互影響。
- 能力： 將抽象介面與實作類別切開，使兩者可以各自變化而不影響彼此。泰坦神祇本身就是抽象的「橋樑」，它將不同「類型」的交通工具（比如飛行器和陸地車輛）與不同的「道路類型」（比如彩虹橋面和能量浮空平台）連接起來。飛行器可以在能量平台上運行，而陸地車輛可以在彩虹橋上行駛，兩者由泰坦連接，但各自的運作方式和升級改造互不干擾。
- 代表語： 「兩岸獨立，我為連通。」
- 背景故事： 在一個由多個獨立浮空島組成的世界中，這位泰坦神祇是維持島嶼之間聯繫的唯一存在。他並非直接建造道路或交通工具，而是將「交通概念」與「實現方式」徹底分離。例如，他可以提供一個「旅行」的抽象介面，但旅行的具體實現可以由「步行者」（通過彩虹橋）或「飛行者」（通過能量平台）來完成。無論哪種旅行方式，都可以獨立地改進和升級，而不會影響到另一個。泰坦神祇確保了這種分離，讓各種旅行方式都能靈活發展。

---

## 範例

### Java

```java
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

### JavaScript

```javascript
// 旅行實現介面（實現部分）
class TravelImplementation {
  startJourney() {
    throw new Error("子類必須實現 startJourney 方法");
  }

  travel(destination) {
    throw new Error("子類必須實現 travel 方法");
  }

  endJourney() {
    throw new Error("子類必須實現 endJourney 方法");
  }

  getTransportType() {
    throw new Error("子類必須實現 getTransportType 方法");
  }
}

// 彩虹橋實現（陸地交通）
class RainbowBridgeImpl extends TravelImplementation {
  startJourney() {
    console.log("踏上彩虹橋，感受七彩光芒的指引...");
  }

  travel(destination) {
    console.log(`沿著彩虹橋穩步前進，目標: ${destination}`);
    console.log("橋面在腳下閃爍著美麗的光彩");
  }

  endJourney() {
    console.log("安全抵達目的地，彩虹橋逐漸消散");
  }

  getTransportType() {
    return "彩虹橋";
  }
}

// 能量平台實現（空中交通）
class EnergyPlatformImpl extends TravelImplementation {
  startJourney() {
    console.log("啟動能量平台，浮空引擎開始運轉...");
  }

  travel(destination) {
    console.log(`駕駛能量平台飛向: ${destination}`);
    console.log("平台在空中劃出璀璨的能量軌跡");
  }

  endJourney() {
    console.log("能量平台緩緩降落，引擎停止運轉");
  }

  getTransportType() {
    return "能量平台";
  }
}

// 神秘傳送門實現（瞬間傳送）
class MysticPortalImpl extends TravelImplementation {
  startJourney() {
    console.log("神秘傳送門開始發光，空間開始扭曲...");
  }

  travel(destination) {
    console.log(`通過傳送門瞬間抵達: ${destination}`);
    console.log("感受到時空穿梭的神奇力量");
  }

  endJourney() {
    console.log("傳送門關閉，空間恢復平靜");
  }

  getTransportType() {
    return "神秘傳送門";
  }
}

// 旅行抽象類（抽象部分）
class Travel {
  constructor(implementation) {
    this.implementation = implementation;
  }

  // 基本旅行方法
  executeJourney(destination) {
    this.implementation.startJourney();
    this.implementation.travel(destination);
    this.implementation.endJourney();
  }

  // 抽象方法，由子類實現
  planJourney(destination) {
    throw new Error("子類必須實現 planJourney 方法");
  }

  getTravelType() {
    throw new Error("子類必須實現 getTravelType 方法");
  }
}

// 冒險旅行（精煉抽象）
class AdventureTravel extends Travel {
  constructor(implementation, adventurerName) {
    super(implementation);
    this.adventurerName = adventurerName;
  }

  planJourney(destination) {
    console.log("=== 冒險旅行計劃 ===");
    console.log(`冒險者: ${this.adventurerName}`);
    console.log(`目的地: ${destination}`);
    console.log(`交通工具: ${this.implementation.getTransportType()}`);
    console.log("準備冒險裝備和補給...");
  }

  getTravelType() {
    return "冒險旅行";
  }

  encounterChallenge() {
    console.log(
      `${
        this.adventurerName
      } 遭遇挑戰，使用 ${this.implementation.getTransportType()} 的優勢應對！`
    );
  }
}

// 外交旅行（精煉抽象）
class DiplomaticTravel extends Travel {
  constructor(implementation, diplomatName, mission) {
    super(implementation);
    this.diplomatName = diplomatName;
    this.mission = mission;
  }

  planJourney(destination) {
    console.log("=== 外交旅行計劃 ===");
    console.log(`外交官: ${this.diplomatName}`);
    console.log(`目的地: ${destination}`);
    console.log(`任務: ${this.mission}`);
    console.log(`交通工具: ${this.implementation.getTransportType()}`);
    console.log("準備外交文件和禮品...");
  }

  getTravelType() {
    return "外交旅行";
  }

  conductNegotiation() {
    console.log(
      `${
        this.diplomatName
      } 通過 ${this.implementation.getTransportType()} 展現威嚴，進行 ${
        this.mission
      }`
    );
  }
}

// 泰坦橋守護者（管理者）
class TitanBridgeGuardian {
  constructor(guardianName) {
    this.guardianName = guardianName;
  }

  // 展示所有可用的交通方式
  showAvailableTransports() {
    console.log(`=== ${this.guardianName} 的交通網絡 ===`);
    console.log("1. 彩虹橋 - 穩固的陸地連接");
    console.log("2. 能量平台 - 自由的空中飛行");
    console.log("3. 神秘傳送門 - 瞬間的空間穿越");
    console.log("兩岸獨立，我為連通。");
  }

  // 創建旅行組合
  createTravel(travelType, transportType, travelerName) {
    const implementation = this.createImplementation(transportType);

    if (!implementation) {
      console.log(`未知的交通工具類型: ${transportType}`);
      return null;
    }

    switch (travelType.toLowerCase()) {
      case "adventure":
        return new AdventureTravel(implementation, travelerName);
      case "diplomatic":
        return new DiplomaticTravel(implementation, travelerName, "和平談判");
      default:
        console.log(`未知的旅行類型: ${travelType}`);
        return null;
    }
  }

  createImplementation(transportType) {
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

// 展示橋接模式彈性的函數
function demonstrateFlexibility() {
  console.log("=== 橋接模式彈性展示 ===");
  console.log("同一個冒險者可以使用不同的交通工具：");

  const travelerName = "法師甘道夫";
  const destination = "火山島";

  // 同一個旅行者使用不同的實現
  const implementations = [
    new RainbowBridgeImpl(),
    new EnergyPlatformImpl(),
    new MysticPortalImpl(),
  ];

  implementations.forEach((impl) => {
    const travel = new AdventureTravel(impl, travelerName);
    console.log(
      `${travelerName} 計劃使用 ${impl.getTransportType()} 前往 ${destination}`
    );
  });
}

// 使用範例
console.log("=== 歡迎來到泰坦橋守護者的領域 ===\n");
console.log("");

// 創建泰坦橋守護者
const guardian = new TitanBridgeGuardian("古老泰坦");
guardian.showAvailableTransports();

// 創建不同類型的旅行組合
const adventureWithBridge = guardian.createTravel(
  "adventure",
  "bridge",
  "勇者亞瑟"
);
const diplomaticWithPlatform = guardian.createTravel(
  "diplomatic",
  "platform",
  "智者梅林"
);
const adventureWithPortal = guardian.createTravel(
  "adventure",
  "portal",
  "法師甘道夫"
);

console.log("");

// 執行冒險旅行（彩虹橋）
if (adventureWithBridge) {
  adventureWithBridge.planJourney("龍之谷");
  adventureWithBridge.executeJourney("龍之谷");
  adventureWithBridge.encounterChallenge();
  console.log("");
}

// 執行外交旅行（能量平台）
if (diplomaticWithPlatform) {
  diplomaticWithPlatform.planJourney("精靈王國");
  diplomaticWithPlatform.executeJourney("精靈王國");
  diplomaticWithPlatform.conductNegotiation();
  console.log("");
}

// 執行冒險旅行（神秘傳送門）
if (adventureWithPortal) {
  adventureWithPortal.planJourney("黑暗之地");
  adventureWithPortal.executeJourney("黑暗之地");
  adventureWithPortal.encounterChallenge();
  console.log("");
}

// 展示橋接模式的彈性
demonstrateFlexibility();

/** output
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
```

## 小總結

Bridge 設計模式就像我們故事中的穩重彩虹橋守護者，將`抽象部分`與`實現部分`分離，使兩者都能獨立變化

**核心特點：**

- **分離抽象與實現**：將抽象介面與具體實現分開，形成兩個獨立的階層
- **組合優於繼承**：透過組合關係連接抽象與實現，避免多重繼承的複雜性
- **雙向獨立擴展**：抽象層和實現層都可以獨立地擴展和修改
- **運行時綁定**：可在運行時動態切換不同的實現

**使用時機：**

- 希望避免抽象與實現之間的永久綁定（ex: 圖形系統支援多種渲染引擎）
- 抽象和實現都需要獨立擴展（ex: 遙控器與電器設備的組合）
- 需要在多個物件間共享實現（ex: 不同的 UI 元件共享相同的繪圖實現）
- 想要對客戶隱藏實現的細節（ex: 資料庫驅動的抽象層）

**注意事項：**

- 增加了系統的複雜性，需要更多的類別和介面
- 需要正確識別系統中變化的維度，避免過度設計
- 要平衡抽象層和實現層的職責分配
- 適合用於有兩個（或多個）獨立變化維度的情況
- 與 Adapter 模式不同，Bridge 在設計階段就考慮分離，而非事後適配
