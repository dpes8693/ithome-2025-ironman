# Day17 建造者模式 (Builder Pattern)

## 擬人化角色：【專精的機械泰坦】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/16-Builder.png)

- 種族： 機械生命體/泰坦
- 外貌： 龐大而威武的蒸汽朋克風格機器人，全身由精密的齒輪和金屬板構成，雙眼閃爍著藍色的光芒。牠的工作間是一個巨大的工廠，周圍懸浮著全息設計圖，牠正用巨手組裝一個小型機器人。
- 性格： 嚴謹、耐心、注重步驟和流程。牠不直接生產最終產品，而是將複雜產品的建造過程分解為一系列可控的步驟。
- 能力： 牠將一個由各種組件組合的複雜產品建造過程封裝起來。牠的目標是確保每一個複雜的組合物件都能按照精確的步驟被建造出來，且各部分能完美契合。
- 代表語： 「每一個偉大的創造，都始於精確的組裝。」
- 背景故事： 在一個高度發達的機械文明中，這位機械泰坦是所有巨型工程的總設計師和建造者。牠不會直接製造一艘飛船或一座城市，而是負責監督和執行建造的每一個階段。例如，當需要建造一個巨型機器人時，牠會先設計機體結構，然後分配動力系統的安裝，再是武器模組的組裝，最後是人工智慧核心的植入。每個步驟都由不同的子建造者或機器人來完成，而泰坦則確保整個建造過程井然有序，最終產出一個功能完整的複雜產品。

---

## 範例

### Java

```java
//MechRobot.java
// 機械機器人產品類
public class MechRobot {
    private String bodyFrame;      // 機體框架
    private String powerCore;      // 動力核心
    private String weaponSystem;   // 武器系統
    private String aiModule;       // 人工智慧模組
    private String shield;         // 護盾系統
    private String mobility;       // 移動系統

    // 建構子設為 package-private，只有 Builder 可以建立
    MechRobot() {}

    // Getter 方法
    public String getBodyFrame() { return bodyFrame; }
    public String getPowerCore() { return powerCore; }
    public String getWeaponSystem() { return weaponSystem; }
    public String getAiModule() { return aiModule; }
    public String getShield() { return shield; }
    public String getMobility() { return mobility; }

    // Setter 方法 (package-private)
    void setBodyFrame(String bodyFrame) { this.bodyFrame = bodyFrame; }
    void setPowerCore(String powerCore) { this.powerCore = powerCore; }
    void setWeaponSystem(String weaponSystem) { this.weaponSystem = weaponSystem; }
    void setAiModule(String aiModule) { this.aiModule = aiModule; }
    void setShield(String shield) { this.shield = shield; }
    void setMobility(String mobility) { this.mobility = mobility; }

    // 展示機器人規格
    public void displaySpecs() {
        System.out.println("🤖 === 機械機器人規格 ===");
        System.out.println("🏗️  機體框架：" + (bodyFrame != null ? bodyFrame : "未安裝"));
        System.out.println("⚡ 動力核心：" + (powerCore != null ? powerCore : "未安裝"));
        System.out.println("🔫 武器系統：" + (weaponSystem != null ? weaponSystem : "未安裝"));
        System.out.println("🧠 AI模組：" + (aiModule != null ? aiModule : "未安裝"));
        System.out.println("🛡️  護盾系統：" + (shield != null ? shield : "未安裝"));
        System.out.println("🦵 移動系統：" + (mobility != null ? mobility : "未安裝"));
    }

    // 啟動機器人
    public void activate() {
        System.out.println("⚡ 機械機器人啟動中...");
        if (bodyFrame != null && powerCore != null) {
            System.out.println("✅ 機器人成功啟動！");
            if (aiModule != null) {
                System.out.println("🧠 AI系統上線：" + aiModule);
            }
            if (weaponSystem != null) {
                System.out.println("🔫 武器系統就緒：" + weaponSystem);
            }
        } else {
            System.out.println("❌ 啟動失敗：缺少基本組件");
        }
    }
}
```

```java
//RobotBuilder.java
// 機器人建造者介面
public interface RobotBuilder {
    RobotBuilder buildBodyFrame();
    RobotBuilder buildPowerCore();
    RobotBuilder buildWeaponSystem();
    RobotBuilder buildAiModule();
    RobotBuilder buildShield();
    RobotBuilder buildMobility();
    MechRobot getResult();
}
```

```java
//WarriorRobotBuilder.java
// 戰士型機器人建造者
public class WarriorRobotBuilder implements RobotBuilder {
    private MechRobot robot;

    public WarriorRobotBuilder() {
        this.robot = new MechRobot();
        System.out.println("🔨 開始建造戰士型機器人");
    }

    @Override
    public RobotBuilder buildBodyFrame() {
        robot.setBodyFrame("重裝甲戰鬥機體");
        System.out.println("🏗️  安裝重裝甲戰鬥機體");
        return this;
    }

    @Override
    public RobotBuilder buildPowerCore() {
        robot.setPowerCore("高輸出反應爐");
        System.out.println("⚡ 安裝高輸出反應爐");
        return this;
    }

    @Override
    public RobotBuilder buildWeaponSystem() {
        robot.setWeaponSystem("重型火炮系統");
        System.out.println("🔫 安裝重型火炮系統");
        return this;
    }

    @Override
    public RobotBuilder buildAiModule() {
        robot.setAiModule("戰術指揮AI");
        System.out.println("🧠 安裝戰術指揮AI");
        return this;
    }

    @Override
    public RobotBuilder buildShield() {
        robot.setShield("能量護盾產生器");
        System.out.println("🛡️  安裝能量護盾產生器");
        return this;
    }

    @Override
    public RobotBuilder buildMobility() {
        robot.setMobility("重型履帶系統");
        System.out.println("🦵 安裝重型履帶系統");
        return this;
    }

    @Override
    public MechRobot getResult() {
        System.out.println("✅ 戰士型機器人建造完成！");
        return robot;
    }
}
```

```java
//ScoutRobotBuilder.java
// 偵察型機器人建造者
public class ScoutRobotBuilder implements RobotBuilder {
    private MechRobot robot;

    public ScoutRobotBuilder() {
        this.robot = new MechRobot();
        System.out.println("🔨 開始建造偵察型機器人");
    }

    @Override
    public RobotBuilder buildBodyFrame() {
        robot.setBodyFrame("輕量化機動機體");
        System.out.println("🏗️  安裝輕量化機動機體");
        return this;
    }

    @Override
    public RobotBuilder buildPowerCore() {
        robot.setPowerCore("高效能電池組");
        System.out.println("⚡ 安裝高效能電池組");
        return this;
    }

    @Override
    public RobotBuilder buildWeaponSystem() {
        robot.setWeaponSystem("輕型雷射武器");
        System.out.println("🔫 安裝輕型雷射武器");
        return this;
    }

    @Override
    public RobotBuilder buildAiModule() {
        robot.setAiModule("偵察分析AI");
        System.out.println("🧠 安裝偵察分析AI");
        return this;
    }

    @Override
    public RobotBuilder buildShield() {
        robot.setShield("光學迷彩系統");
        System.out.println("🛡️  安裝光學迷彩系統");
        return this;
    }

    @Override
    public RobotBuilder buildMobility() {
        robot.setMobility("高速飛行推進器");
        System.out.println("🦵 安裝高速飛行推進器");
        return this;
    }

    @Override
    public MechRobot getResult() {
        System.out.println("✅ 偵察型機器人建造完成！");
        return robot;
    }
}
```

```java
//MechTitanDirector.java
// 機械泰坦指揮者
public class MechTitanDirector {
    private String name;

    public MechTitanDirector(String name) {
        this.name = name;
        System.out.println("🤖 機械泰坦 " + name + " 啟動建造程序");
    }

    // 建造標準戰士型機器人
    public MechRobot constructWarriorRobot(RobotBuilder builder) {
        System.out.println("⚡ " + name + " 開始建造標準戰士型機器人");
        return builder
                .buildBodyFrame()
                .buildPowerCore()
                .buildWeaponSystem()
                .buildAiModule()
                .buildShield()
                .buildMobility()
                .getResult();
    }

    // 建造輕裝偵察機器人
    public MechRobot constructScoutRobot(RobotBuilder builder) {
        System.out.println("⚡ " + name + " 開始建造輕裝偵察機器人");
        return builder
                .buildBodyFrame()
                .buildPowerCore()
                .buildAiModule()
                .buildShield()      // 偵察機器人需要隱蔽能力
                .buildMobility()    // 偵察機器人需要高機動性
                .getResult();       // 不安裝重型武器
    }

    // 建造基礎型機器人
    public MechRobot constructBasicRobot(RobotBuilder builder) {
        System.out.println("⚡ " + name + " 開始建造基礎型機器人");
        return builder
                .buildBodyFrame()
                .buildPowerCore()
                .buildAiModule()
                .getResult();       // 只安裝基本組件
    }

    // 自訂建造流程
    public MechRobot constructCustomRobot(RobotBuilder builder, boolean needWeapons,
                                         boolean needShield, boolean needMobility) {
        System.out.println("⚡ " + name + " 開始建造自訂機器人");

        // 基本組件
        builder.buildBodyFrame().buildPowerCore().buildAiModule();

        // 可選組件
        if (needWeapons) {
            builder.buildWeaponSystem();
        }
        if (needShield) {
            builder.buildShield();
        }
        if (needMobility) {
            builder.buildMobility();
        }

        return builder.getResult();
    }

    // 展示建造能力
    public void showConstructionCapabilities() {
        System.out.println("🏭 === " + name + " 的建造能力 ===");
        System.out.println("⚔️  標準戰士型：全功能戰鬥機器人");
        System.out.println("👁️  輕裝偵察型：高機動偵察機器人");
        System.out.println("🔧 基礎型：基本功能機器人");
        System.out.println("🎯 自訂型：依需求客製化");
        System.out.println("「每一個偉大的創造，都始於精確的組裝。」");
    }
}
```

```java
//BuilderPatternExample.java
// 使用範例
public class BuilderPatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到機械泰坦的建造工廠 ===\n");

        // 創建機械泰坦指揮者
        MechTitanDirector titan = new MechTitanDirector("泰坦-001");
        titan.showConstructionCapabilities();
        System.out.println();

        // 建造標準戰士型機器人
        System.out.println("1. 建造標準戰士型機器人：");
        RobotBuilder warriorBuilder = new WarriorRobotBuilder();
        MechRobot warrior = titan.constructWarriorRobot(warriorBuilder);
        System.out.println();
        warrior.displaySpecs();
        warrior.activate();
        System.out.println();

        // 建造輕裝偵察機器人
        System.out.println("2. 建造輕裝偵察機器人：");
        RobotBuilder scoutBuilder = new ScoutRobotBuilder();
        MechRobot scout = titan.constructScoutRobot(scoutBuilder);
        System.out.println();
        scout.displaySpecs();
        scout.activate();
        System.out.println();

        // 建造基礎型機器人
        System.out.println("3. 建造基礎型機器人：");
        RobotBuilder basicBuilder = new WarriorRobotBuilder();
        MechRobot basic = titan.constructBasicRobot(basicBuilder);
        System.out.println();
        basic.displaySpecs();
        basic.activate();
        System.out.println();

        // 自訂建造
        System.out.println("4. 建造自訂機器人：");
        RobotBuilder customBuilder = new ScoutRobotBuilder();
        MechRobot custom = titan.constructCustomRobot(customBuilder, true, false, true);
        System.out.println();
        custom.displaySpecs();
        custom.activate();

        /**output
        === 歡迎來到機械泰坦的建造工廠 ===

        🤖 機械泰坦 泰坦-001 啟動建造程序
        🏭 === 泰坦-001 的建造能力 ===
        ⚔️  標準戰士型：全功能戰鬥機器人
        👁️  輕裝偵察型：高機動偵察機器人
        🔧 基礎型：基本功能機器人
        🎯 自訂型：依需求客製化
        「每一個偉大的創造，都始於精確的組裝。」

        1. 建造標準戰士型機器人：
        🔨 開始建造戰士型機器人
        ⚡ 泰坦-001 開始建造標準戰士型機器人
        🏗️  安裝重裝甲戰鬥機體
        ⚡ 安裝高輸出反應爐
        🔫 安裝重型火炮系統
        🧠 安裝戰術指揮AI
        🛡️  安裝能量護盾產生器
        🦵 安裝重型履帶系統
        ✅ 戰士型機器人建造完成！

        🤖 === 機械機器人規格 ===
        🏗️  機體框架：重裝甲戰鬥機體
        ⚡ 動力核心：高輸出反應爐
        🔫 武器系統：重型火炮系統
        🧠 AI模組：戰術指揮AI
        🛡️  護盾系統：能量護盾產生器
        🦵 移動系統：重型履帶系統
        ⚡ 機械機器人啟動中...
        ✅ 機器人成功啟動！
        🧠 AI系統上線：戰術指揮AI
        🔫 武器系統就緒：重型火炮系統

        2. 建造輕裝偵察機器人：
        🔨 開始建造偵察型機器人
        ⚡ 泰坦-001 開始建造輕裝偵察機器人
        🏗️  安裝輕量化機動機體
        ⚡ 安裝高效能電池組
        🧠 安裝偵察分析AI
        🛡️  安裝光學迷彩系統
        🦵 安裝高速飛行推進器
        ✅ 偵察型機器人建造完成！

        🤖 === 機械機器人規格 ===
        🏗️  機體框架：輕量化機動機體
        ⚡ 動力核心：高效能電池組
        🔫 武器系統：未安裝
        🧠 AI模組：偵察分析AI
        🛡️  護盾系統：光學迷彩系統
        🦵 移動系統：高速飛行推進器
        ⚡ 機械機器人啟動中...
        ✅ 機器人成功啟動！
        🧠 AI系統上線：偵察分析AI

        3. 建造基礎型機器人：
        🔨 開始建造戰士型機器人
        ⚡ 泰坦-001 開始建造基礎型機器人
        🏗️  安裝重裝甲戰鬥機體
        ⚡ 安裝高輸出反應爐
        🧠 安裝戰術指揮AI
        ✅ 戰士型機器人建造完成！

        🤖 === 機械機器人規格 ===
        🏗️  機體框架：重裝甲戰鬥機體
        ⚡ 動力核心：高輸出反應爐
        🔫 武器系統：未安裝
        🧠 AI模組：戰術指揮AI
        🛡️  護盾系統：未安裝
        🦵 移動系統：未安裝
        ⚡ 機械機器人啟動中...
        ✅ 機器人成功啟動！
        🧠 AI系統上線：戰術指揮AI

        4. 建造自訂機器人：
        🔨 開始建造偵察型機器人
        ⚡ 泰坦-001 開始建造自訂機器人
        🏗️  安裝輕量化機動機體
        ⚡ 安裝高效能電池組
        🧠 安裝偵察分析AI
        🔫 安裝輕型雷射武器
        🦵 安裝高速飛行推進器
        ✅ 偵察型機器人建造完成！

        🤖 === 機械機器人規格 ===
        🏗️  機體框架：輕量化機動機體
        ⚡ 動力核心：高效能電池組
        🔫 武器系統：輕型雷射武器
        🧠 AI模組：偵察分析AI
        🛡️  護盾系統：未安裝
        🦵 移動系統：高速飛行推進器
        ⚡ 機械機器人啟動中...
        ✅ 機器人成功啟動！
        🧠 AI系統上線：偵察分析AI
        🔫 武器系統就緒：輕型雷射武器
        */
    }
}
```

### JavaScript

```javascript
// 機械機器人產品類
class MechRobot {
  constructor() {
    this.bodyFrame = null; // 機體框架
    this.powerCore = null; // 動力核心
    this.weaponSystem = null; // 武器系統
    this.aiModule = null; // 人工智慧模組
    this.shield = null; // 護盾系統
    this.mobility = null; // 移動系統
  }

  // 展示機器人規格
  displaySpecs() {
    console.log("🤖 === 機械機器人規格 ===");
    console.log(`🏗️ 機體框架：${this.bodyFrame || "未安裝"}`);
    console.log(`⚡ 動力核心：${this.powerCore || "未安裝"}`);
    console.log(`🔫 武器系統：${this.weaponSystem || "未安裝"}`);
    console.log(`🧠 AI模組：${this.aiModule || "未安裝"}`);
    console.log(`🛡️ 護盾系統：${this.shield || "未安裝"}`);
    console.log(`🦵 移動系統：${this.mobility || "未安裝"}`);
  }

  // 啟動機器人
  activate() {
    console.log("⚡ 機械機器人啟動中...");
    if (this.bodyFrame && this.powerCore) {
      console.log("✅ 機器人成功啟動！");
      if (this.aiModule) {
        console.log(`🧠 AI系統上線：${this.aiModule}`);
      }
      if (this.weaponSystem) {
        console.log(`🔫 武器系統就緒：${this.weaponSystem}`);
      }
    } else {
      console.log("❌ 啟動失敗：缺少基本組件");
    }
  }
}

// 機器人建造者基類
class RobotBuilder {
  constructor() {
    this.robot = new MechRobot();
  }

  buildBodyFrame() {
    throw new Error("子類必須實現 buildBodyFrame 方法");
  }

  buildPowerCore() {
    throw new Error("子類必須實現 buildPowerCore 方法");
  }

  buildWeaponSystem() {
    throw new Error("子類必須實現 buildWeaponSystem 方法");
  }

  buildAiModule() {
    throw new Error("子類必須實現 buildAiModule 方法");
  }

  buildShield() {
    throw new Error("子類必須實現 buildShield 方法");
  }

  buildMobility() {
    throw new Error("子類必須實現 buildMobility 方法");
  }

  getResult() {
    return this.robot;
  }
}

// 戰士型機器人建造者
class WarriorRobotBuilder extends RobotBuilder {
  constructor() {
    super();
    console.log("🔨 開始建造戰士型機器人");
  }

  buildBodyFrame() {
    this.robot.bodyFrame = "重裝甲戰鬥機體";
    console.log("🏗️ 安裝重裝甲戰鬥機體");
    return this;
  }

  buildPowerCore() {
    this.robot.powerCore = "高輸出反應爐";
    console.log("⚡ 安裝高輸出反應爐");
    return this;
  }

  buildWeaponSystem() {
    this.robot.weaponSystem = "重型火炮系統";
    console.log("🔫 安裝重型火炮系統");
    return this;
  }

  buildAiModule() {
    this.robot.aiModule = "戰術指揮AI";
    console.log("🧠 安裝戰術指揮AI");
    return this;
  }

  buildShield() {
    this.robot.shield = "能量護盾產生器";
    console.log("🛡️ 安裝能量護盾產生器");
    return this;
  }

  buildMobility() {
    this.robot.mobility = "重型履帶系統";
    console.log("🦵 安裝重型履帶系統");
    return this;
  }

  getResult() {
    console.log("✅ 戰士型機器人建造完成！");
    return this.robot;
  }
}

// 偵察型機器人建造者
class ScoutRobotBuilder extends RobotBuilder {
  constructor() {
    super();
    console.log("🔨 開始建造偵察型機器人");
  }

  buildBodyFrame() {
    this.robot.bodyFrame = "輕量化機動機體";
    console.log("🏗️ 安裝輕量化機動機體");
    return this;
  }

  buildPowerCore() {
    this.robot.powerCore = "高效能電池組";
    console.log("⚡ 安裝高效能電池組");
    return this;
  }

  buildWeaponSystem() {
    this.robot.weaponSystem = "輕型雷射武器";
    console.log("🔫 安裝輕型雷射武器");
    return this;
  }

  buildAiModule() {
    this.robot.aiModule = "偵察分析AI";
    console.log("🧠 安裝偵察分析AI");
    return this;
  }

  buildShield() {
    this.robot.shield = "光學迷彩系統";
    console.log("🛡️ 安裝光學迷彩系統");
    return this;
  }

  buildMobility() {
    this.robot.mobility = "高速飛行推進器";
    console.log("🦵 安裝高速飛行推進器");
    return this;
  }

  getResult() {
    console.log("✅ 偵察型機器人建造完成！");
    return this.robot;
  }
}

// 機械泰坦指揮者
class MechTitanDirector {
  constructor(name) {
    this.name = name;
    console.log(`🤖 機械泰坦 ${name} 啟動建造程序`);
  }

  // 建造標準戰士型機器人
  constructWarriorRobot(builder) {
    console.log(`⚡ ${this.name} 開始建造標準戰士型機器人`);
    return builder
      .buildBodyFrame()
      .buildPowerCore()
      .buildWeaponSystem()
      .buildAiModule()
      .buildShield()
      .buildMobility()
      .getResult();
  }

  // 建造輕裝偵察機器人
  constructScoutRobot(builder) {
    console.log(`⚡ ${this.name} 開始建造輕裝偵察機器人`);
    return builder
      .buildBodyFrame()
      .buildPowerCore()
      .buildAiModule()
      .buildShield() // 偵察機器人需要隱蔽能力
      .buildMobility() // 偵察機器人需要高機動性
      .getResult(); // 不安裝重型武器
  }

  // 建造基礎型機器人
  constructBasicRobot(builder) {
    console.log(`⚡ ${this.name} 開始建造基礎型機器人`);
    return builder
      .buildBodyFrame()
      .buildPowerCore()
      .buildAiModule()
      .getResult(); // 只安裝基本組件
  }

  // 自訂建造流程
  constructCustomRobot(builder, needWeapons, needShield, needMobility) {
    console.log(`⚡ ${this.name} 開始建造自訂機器人`);

    // 基本組件
    builder.buildBodyFrame().buildPowerCore().buildAiModule();

    // 可選組件
    if (needWeapons) {
      builder.buildWeaponSystem();
    }
    if (needShield) {
      builder.buildShield();
    }
    if (needMobility) {
      builder.buildMobility();
    }

    return builder.getResult();
  }

  // 展示建造能力
  showConstructionCapabilities() {
    console.log(`🏭 === ${this.name} 的建造能力 ===`);
    console.log("⚔️ 標準戰士型：全功能戰鬥機器人");
    console.log("👁️ 輕裝偵察型：高機動偵察機器人");
    console.log("🔧 基礎型：基本功能機器人");
    console.log("🎯 自訂型：依需求客製化");
    console.log("「每一個偉大的創造，都始於精確的組裝。」");
  }
}

// 使用範例
function runBuilderExample() {
  console.log("=== 歡迎來到機械泰坦的建造工廠 ===\n");

  // 創建機械泰坦指揮者
  const titan = new MechTitanDirector("泰坦-001");
  titan.showConstructionCapabilities();
  console.log("");

  // 建造標準戰士型機器人
  console.log("1. 建造標準戰士型機器人：");
  const warriorBuilder = new WarriorRobotBuilder();
  const warrior = titan.constructWarriorRobot(warriorBuilder);
  console.log("");
  warrior.displaySpecs();
  warrior.activate();
  console.log("");

  // 建造輕裝偵察機器人
  console.log("2. 建造輕裝偵察機器人：");
  const scoutBuilder = new ScoutRobotBuilder();
  const scout = titan.constructScoutRobot(scoutBuilder);
  console.log("");
  scout.displaySpecs();
  scout.activate();
  console.log("");

  // 建造基礎型機器人
  console.log("3. 建造基礎型機器人：");
  const basicBuilder = new WarriorRobotBuilder();
  const basic = titan.constructBasicRobot(basicBuilder);
  console.log("");
  basic.displaySpecs();
  basic.activate();
  console.log("");

  // 自訂建造
  console.log("4. 建造自訂機器人：");
  const customBuilder = new ScoutRobotBuilder();
  const custom = titan.constructCustomRobot(customBuilder, true, false, true);
  console.log("");
  custom.displaySpecs();
  custom.activate();
}

// 執行範例
runBuilderExample();

/** output
=== 歡迎來到機械泰坦的建造工廠 ===

🤖 機械泰坦 泰坦-001 啟動建造程序
🏭 === 泰坦-001 的建造能力 ===
⚔️ 標準戰士型：全功能戰鬥機器人
👁️ 輕裝偵察型：高機動偵察機器人
🔧 基礎型：基本功能機器人
🎯 自訂型：依需求客製化
「每一個偉大的創造，都始於精確的組裝。」

1. 建造標準戰士型機器人：
🔨 開始建造戰士型機器人
⚡ 泰坦-001 開始建造標準戰士型機器人
🏗️ 安裝重裝甲戰鬥機體
⚡ 安裝高輸出反應爐
🔫 安裝重型火炮系統
🧠 安裝戰術指揮AI
🛡️ 安裝能量護盾產生器
🦵 安裝重型履帶系統
✅ 戰士型機器人建造完成！

🤖 === 機械機器人規格 ===
🏗️ 機體框架：重裝甲戰鬥機體
⚡ 動力核心：高輸出反應爐
🔫 武器系統：重型火炮系統
🧠 AI模組：戰術指揮AI
🛡️ 護盾系統：能量護盾產生器
🦵 移動系統：重型履帶系統
⚡ 機械機器人啟動中...
✅ 機器人成功啟動！
🧠 AI系統上線：戰術指揮AI
🔫 武器系統就緒：重型火炮系統

2. 建造輕裝偵察機器人：
🔨 開始建造偵察型機器人
⚡ 泰坦-001 開始建造輕裝偵察機器人
🏗️ 安裝輕量化機動機體
⚡ 安裝高效能電池組
🧠 安裝偵察分析AI
🛡️ 安裝光學迷彩系統
🦵 安裝高速飛行推進器
✅ 偵察型機器人建造完成！

🤖 === 機械機器人規格 ===
🏗️ 機體框架：輕量化機動機體
⚡ 動力核心：高效能電池組
🔫 武器系統：未安裝
🧠 AI模組：偵察分析AI
🛡️ 護盾系統：光學迷彩系統
🦵 移動系統：高速飛行推進器
⚡ 機械機器人啟動中...
✅ 機器人成功啟動！
🧠 AI系統上線：偵察分析AI

3. 建造基礎型機器人：
🔨 開始建造戰士型機器人
⚡ 泰坦-001 開始建造基礎型機器人
🏗️ 安裝重裝甲戰鬥機體
⚡ 安裝高輸出反應爐
🧠 安裝戰術指揮AI
✅ 戰士型機器人建造完成！

🤖 === 機械機器人規格 ===
🏗️ 機體框架：重裝甲戰鬥機體
⚡ 動力核心：高輸出反應爐
🔫 武器系統：未安裝
🧠 AI模組：戰術指揮AI
🛡️ 護盾系統：未安裝
🦵 移動系統：未安裝
⚡ 機械機器人啟動中...
✅ 機器人成功啟動！
🧠 AI系統上線：戰術指揮AI

4. 建造自訂機器人：
🔨 開始建造偵察型機器人
⚡ 泰坦-001 開始建造自訂機器人
🏗️ 安裝輕量化機動機體
⚡ 安裝高效能電池組
🧠 安裝偵察分析AI
🔫 安裝輕型雷射武器
🦵 安裝高速飛行推進器
✅ 偵察型機器人建造完成！

🤖 === 機械機器人規格 ===
🏗️ 機體框架：輕量化機動機體
⚡ 動力核心：高效能電池組
🔫 武器系統：輕型雷射武器
🧠 AI模組：偵察分析AI
🛡️ 護盾系統：未安裝
🦵 移動系統：高速飛行推進器
⚡ 機械機器人啟動中...
✅ 機器人成功啟動！
🧠 AI系統上線：偵察分析AI
🔫 武器系統就緒：輕型雷射武器
 */
```

## 小總結

Builder 設計模式就像我們故事中的專精機械泰坦，將`複雜物件的建構過程`與其`表現分離`，使同樣的建構過程可以創建不同的表現

**核心特點：**

- **分步建構**：將複雜物件的建構分解為多個簡單的步驟
- **靈活組合**：可以控制建構過程，選擇性地安裝組件
- **建構與表現分離**：同一個建構過程可以創建不同配置的物件
<!-- - **鏈式調用**：支援流暢的 API 設計，提高程式碼可讀性 -->

**主要角色：**

- **Product（產品）**：要建構的複雜物件
- **Builder（建造者）**：定義建構產品各部分的抽象介面
- **ConcreteBuilder（具體建造者）**：實作 Builder 介面，建構產品的具體部分
- **Director（指揮者）**：使用 Builder 介面來建構產品

**使用時機：**

- 需要建立的物件有很多可選配置（ex: 電腦組裝）

**與Factory一句話做比較：**

Factory ：「給我一台遊戲電腦」→ 拿到整台電腦
Builder ：「我要 i7 CPU + RTX 4070 + 16GB RAM」→ 自己組裝

<!-- **注意事項：**

- 會增加程式碼複雜度，特別是產品結構簡單時可能過度設計
- 需要為每種產品類型創建具體的建造者
- 產品內部結構變化時，可能需要修改多個建造者類別
- 適合參數較多且有複雜驗證邏輯的物件建構場景 -->

