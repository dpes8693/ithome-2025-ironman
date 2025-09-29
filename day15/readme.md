# Day15 代理模式 (Proxy Pattern)

## 擬人化角色：【神秘的影子特工】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/14-Proxy.png)

- 種族： 人類/特工
- 外貌： 一位身穿黑色緊身衣，戴著兜帽的女性特工。她的雙眼在夜色中閃爍著藍色的微光，手中拿著夜視望遠鏡。她站在高樓的石像鬼上，監控著下方城市。旁邊有她代表的另一個「真實」目標的藍色虛影。
- 性格： 謹慎、隱秘、忠誠。她不會直接暴露她所代表的「真實」物件，而是作為其替身，處理大部分的外部請求。
- 能力： 為一個物件提供代理物件。她可以代替某個重要人物執行任務，處理訊息，甚至在必要時承擔風險。她會攔截所有對該重要人物的直接請求，並決定是否要將這些請求轉發給真正的目標。
- 代表語： 「無需驚動本尊，我在此為您代勞。」
- 背景故事： 在一個充滿間諜活動的賽博朋克城市中，這位影子特工是某位關鍵人物的專屬代理。這位關鍵人物極為重要，不能輕易暴露身份。因此，所有對他的接觸都必須透過這位特工。她負責過濾訊息、執行簡單任務，甚至在面臨危險時，她會作為誘餌來保護真正的目標。她確保了核心目標的安全和隱秘性，同時依然能處理外部世界的需求。

---

## 範例

### Java

```java
//SecretAgent.java
// 秘密特工介面
public interface SecretAgent {
    void executeTask(String taskName);
    String gatherIntelligence(String target);
    void reportStatus();
    boolean isAvailable();
}
```

```java
//RealSecretAgent.java
// 真實的秘密特工（RealSubject）
public class RealSecretAgent implements SecretAgent {
    private String codeName;
    private String location;
    private boolean onMission;

    public RealSecretAgent(String codeName, String location) {
        this.codeName = codeName;
        this.location = location;
        this.onMission = false;
        System.out.println("🕵️ 真實特工 " + codeName + " 已就位於 " + location);
    }

    @Override
    public void executeTask(String taskName) {
        this.onMission = true;
        System.out.println("🎯 " + codeName + " 正在執行高級任務：" + taskName);
        System.out.println("⚠️  風險警告：真實身份可能暴露！");

        // 模擬任務執行時間
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("✅ 任務完成：" + taskName);
        this.onMission = false;
    }

    @Override
    public String gatherIntelligence(String target) {
        System.out.println("🔍 " + codeName + " 親自收集關於 " + target + " 的機密情報");
        return "機密等級A：" + target + " 的詳細檔案";
    }

    @Override
    public void reportStatus() {
        String status = onMission ? "執行任務中" : "待命中";
        System.out.println("📋 " + codeName + " 狀態報告：" + status + " (位置：" + location + ")");
    }

    @Override
    public boolean isAvailable() {
        return !onMission;
    }
}
```

```java
//ProxySecretAgent.java
// 代理特工（Proxy）
public class ProxySecretAgent implements SecretAgent {
    private RealSecretAgent realAgent;
    private String proxyCodeName;
    private int accessLevel;

    public ProxySecretAgent(String proxyCodeName, int accessLevel) {
        this.proxyCodeName = proxyCodeName;
        this.accessLevel = accessLevel;
        System.out.println("👤 代理特工 " + proxyCodeName + " 已部署 (權限等級：" + accessLevel + ")");
    }

    // 延遲初始化真實特工
    private void initializeRealAgent() {
        if (realAgent == null) {
            System.out.println("🔐 代理特工驗證通過，正在聯繫真實特工...");
            realAgent = new RealSecretAgent("Shadow-007", "機密位置");
        }
    }

    @Override
    public void executeTask(String taskName) {
        System.out.println("📞 " + proxyCodeName + " 接收任務請求：" + taskName);

        // 權限檢查
        if (accessLevel < 5) {
            System.out.println("❌ 權限不足！任務 " + taskName + " 需要更高權限");
            return;
        }

        // 任務複雜度評估
        if (isSimpleTask(taskName)) {
            // 代理直接處理簡單任務
            System.out.println("💼 " + proxyCodeName + " 直接處理簡單任務：" + taskName);
            System.out.println("✅ 任務完成，無需驚動真實特工");
        } else {
            // 複雜任務轉交給真實特工
            System.out.println("⚠️  高級任務檢測，轉交真實特工處理");
            initializeRealAgent();
            realAgent.executeTask(taskName);
        }
    }

    @Override
    public String gatherIntelligence(String target) {
        System.out.println("🔎 " + proxyCodeName + " 分析情報請求：" + target);

        // 檢查是否有快取的情報
        String cachedInfo = getCachedIntelligence(target);
        if (cachedInfo != null) {
            System.out.println("📋 使用快取情報，避免暴露真實特工");
            return cachedInfo;
        }

        // 沒有快取，需要真實特工收集
        System.out.println("🚨 需要新情報，聯繫真實特工");
        initializeRealAgent();
        return realAgent.gatherIntelligence(target);
    }

    @Override
    public void reportStatus() {
        System.out.println("📊 " + proxyCodeName + " 狀態：正常運作");

        if (realAgent != null) {
            System.out.println("🔗 真實特工連線狀態：");
            realAgent.reportStatus();
        } else {
            System.out.println("🔒 真實特工未啟動（保持隱密）");
        }
    }

    @Override
    public boolean isAvailable() {
        // 代理總是可用的
        if (realAgent == null) {
            return true;
        }
        return realAgent.isAvailable();
    }

    // 輔助方法：判斷是否為簡單任務
    private boolean isSimpleTask(String taskName) {
        return taskName.toLowerCase().contains("監控") ||
               taskName.toLowerCase().contains("巡邏") ||
               taskName.toLowerCase().contains("觀察");
    }

    // 輔助方法：獲取快取情報
    private String getCachedIntelligence(String target) {
        // 模擬快取查詢
        if (target.equals("普通目標")) {
            return "快取情報：普通目標的基本資料";
        }
        return null; // 沒有快取
    }

    // 展示代理能力
    public void showProxyCapabilities() {
        System.out.println("=== " + proxyCodeName + " 代理能力 ===");
        System.out.println("🛡️  保護真實特工身份");
        System.out.println("⚡ 處理簡單任務");
        System.out.println("🗄️  快取常用情報");
        System.out.println("🔐 權限控制與驗證");
        System.out.println("「無需驚動本尊，我在此為您代勞。」");
    }
}
```

```java
//ProxyPatternExample.java
// 使用範例
public class ProxyPatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到影子特工組織 ===\n");

        // 創建代理特工
        ProxySecretAgent proxy = new ProxySecretAgent("Ghost-001", 7);
        proxy.showProxyCapabilities();
        System.out.println();

        // 測試狀態報告
        System.out.println("1. 初始狀態檢查：");
        proxy.reportStatus();
        System.out.println();

        // 測試簡單任務（代理直接處理）
        System.out.println("2. 執行簡單任務：");
        proxy.executeTask("城市監控");
        System.out.println();

        // 測試情報收集（使用快取）
        System.out.println("3. 收集快取情報：");
        String info1 = proxy.gatherIntelligence("普通目標");
        System.out.println("獲得情報：" + info1);
        System.out.println();

        // 測試複雜任務（需要真實特工）
        System.out.println("4. 執行複雜任務：");
        proxy.executeTask("敵方基地滲透");
        System.out.println();

        // 測試新情報收集（需要真實特工）
        System.out.println("5. 收集新情報：");
        String info2 = proxy.gatherIntelligence("高價值目標");
        System.out.println("獲得情報：" + info2);
        System.out.println();

        // 最終狀態檢查
        System.out.println("6. 最終狀態檢查：");
        proxy.reportStatus();
        System.out.println();

        // 測試權限不足的情況
        System.out.println("7. 測試權限控制：");
        ProxySecretAgent lowLevelProxy = new ProxySecretAgent("Rookie-001", 3);
        lowLevelProxy.executeTask("核心機密任務");

        /**output
        === 歡迎來到影子特工組織 ===

        👤 代理特工 Ghost-001 已部署 (權限等級：7)
        === Ghost-001 代理能力 ===
        🛡️  保護真實特工身份
        ⚡ 處理簡單任務
        🗄️  快取常用情報
        🔐 權限控制與驗證
        「無需驚動本尊，我在此為您代勞。」

        1. 初始狀態檢查：
        📊 Ghost-001 狀態：正常運作
        🔒 真實特工未啟動（保持隱密）

        2. 執行簡單任務：
        📞 Ghost-001 接收任務請求：城市監控
        💼 Ghost-001 直接處理簡單任務：城市監控
        ✅ 任務完成，無需驚動真實特工

        3. 收集快取情報：
        🔎 Ghost-001 分析情報請求：普通目標
        📋 使用快取情報，避免暴露真實特工
        獲得情報：快取情報：普通目標的基本資料

        4. 執行複雜任務：
        📞 Ghost-001 接收任務請求：敵方基地滲透
        ⚠️  高級任務檢測，轉交真實特工處理
        🔐 代理特工驗證通過，正在聯繫真實特工...
        🕵️ 真實特工 Shadow-007 已就位於 機密位置
        🎯 Shadow-007 正在執行高級任務：敵方基地滲透
        ⚠️  風險警告：真實身份可能暴露！
        ✅ 任務完成：敵方基地滲透

        5. 收集新情報：
        🔎 Ghost-001 分析情報請求：高價值目標
        🚨 需要新情報，聯繫真實特工
        🔍 Shadow-007 親自收集關於 高價值目標 的機密情報
        獲得情報：機密等級A：高價值目標 的詳細檔案

        6. 最終狀態檢查：
        📊 Ghost-001 狀態：正常運作
        🔗 真實特工連線狀態：
        📋 Shadow-007 狀態報告：待命中 (位置：機密位置)

        7. 測試權限控制：
        👤 代理特工 Rookie-001 已部署 (權限等級：3)
        📞 Rookie-001 接收任務請求：核心機密任務
        ❌ 權限不足！任務 核心機密任務 需要更高權限
        */
    }
}
```

### JavaScript

```javascript
// 秘密特工基類
class SecretAgent {
  executeTask(taskName) {
    throw new Error("子類必須實現 executeTask 方法");
  }

  gatherIntelligence(target) {
    throw new Error("子類必須實現 gatherIntelligence 方法");
  }

  reportStatus() {
    throw new Error("子類必須實現 reportStatus 方法");
  }

  isAvailable() {
    throw new Error("子類必須實現 isAvailable 方法");
  }
}

// 真實的秘密特工（RealSubject）
class RealSecretAgent extends SecretAgent {
  constructor(codeName, location) {
    super();
    this.codeName = codeName;
    this.location = location;
    this.onMission = false;
    console.log(`🕵️ 真實特工 ${codeName} 已就位於 ${location}`);
  }

  async executeTask(taskName) {
    this.onMission = true;
    console.log(`🎯 ${this.codeName} 正在執行高級任務：${taskName}`);
    console.log("⚠️  風險警告：真實身份可能暴露！");

    // 模擬任務執行時間
    await new Promise((resolve) => setTimeout(resolve, 1000));

    console.log(`✅ 任務完成：${taskName}`);
    this.onMission = false;
  }

  gatherIntelligence(target) {
    console.log(`🔍 ${this.codeName} 親自收集關於 ${target} 的機密情報`);
    return `機密等級A：${target} 的詳細檔案`;
  }

  reportStatus() {
    const status = this.onMission ? "執行任務中" : "待命中";
    console.log(
      `📋 ${this.codeName} 狀態報告：${status} (位置：${this.location})`
    );
  }

  isAvailable() {
    return !this.onMission;
  }
}

// 代理特工（Proxy）
class ProxySecretAgent extends SecretAgent {
  constructor(proxyCodeName, accessLevel) {
    super();
    this.realAgent = null;
    this.proxyCodeName = proxyCodeName;
    this.accessLevel = accessLevel;
    console.log(
      `👤 代理特工 ${proxyCodeName} 已部署 (權限等級：${accessLevel})`
    );
  }

  // 延遲初始化真實特工
  initializeRealAgent() {
    if (!this.realAgent) {
      console.log("🔐 代理特工驗證通過，正在聯繫真實特工...");
      this.realAgent = new RealSecretAgent("Shadow-007", "機密位置");
    }
  }

  async executeTask(taskName) {
    console.log(`📞 ${this.proxyCodeName} 接收任務請求：${taskName}`);

    // 權限檢查
    if (this.accessLevel < 5) {
      console.log(`❌ 權限不足！任務 ${taskName} 需要更高權限`);
      return;
    }

    // 任務複雜度評估
    if (this.isSimpleTask(taskName)) {
      // 代理直接處理簡單任務
      console.log(`💼 ${this.proxyCodeName} 直接處理簡單任務：${taskName}`);
      console.log("✅ 任務完成，無需驚動真實特工");
    } else {
      // 複雜任務轉交給真實特工
      console.log("⚠️  高級任務檢測，轉交真實特工處理");
      this.initializeRealAgent();
      await this.realAgent.executeTask(taskName);
    }
  }

  gatherIntelligence(target) {
    console.log(`🔎 ${this.proxyCodeName} 分析情報請求：${target}`);

    // 檢查是否有快取的情報
    const cachedInfo = this.getCachedIntelligence(target);
    if (cachedInfo) {
      console.log("📋 使用快取情報，避免暴露真實特工");
      return cachedInfo;
    }

    // 沒有快取，需要真實特工收集
    console.log("🚨 需要新情報，聯繫真實特工");
    this.initializeRealAgent();
    return this.realAgent.gatherIntelligence(target);
  }

  reportStatus() {
    console.log(`📊 ${this.proxyCodeName} 狀態：正常運作`);

    if (this.realAgent) {
      console.log("🔗 真實特工連線狀態：");
      this.realAgent.reportStatus();
    } else {
      console.log("🔒 真實特工未啟動（保持隱密）");
    }
  }

  isAvailable() {
    // 代理總是可用的
    if (!this.realAgent) {
      return true;
    }
    return this.realAgent.isAvailable();
  }

  // 輔助方法：判斷是否為簡單任務
  isSimpleTask(taskName) {
    const lowerCaseTask = taskName.toLowerCase();
    return (
      lowerCaseTask.includes("監控") ||
      lowerCaseTask.includes("巡邏") ||
      lowerCaseTask.includes("觀察")
    );
  }

  // 輔助方法：獲取快取情報
  getCachedIntelligence(target) {
    // 模擬快取查詢
    if (target === "普通目標") {
      return "快取情報：普通目標的基本資料";
    }
    return null; // 沒有快取
  }

  // 展示代理能力
  showProxyCapabilities() {
    console.log(`=== ${this.proxyCodeName} 代理能力 ===`);
    console.log("🛡️  保護真實特工身份");
    console.log("⚡ 處理簡單任務");
    console.log("🗄️  快取常用情報");
    console.log("🔐 權限控制與驗證");
    console.log("「無需驚動本尊，我在此為您代勞。」");
  }
}

// 使用範例
async function runProxyExample() {
  console.log("=== 歡迎來到影子特工組織 ===\n");

  // 創建代理特工
  const proxy = new ProxySecretAgent("Ghost-001", 7);
  proxy.showProxyCapabilities();

  // 測試狀態報告
  console.log("\n1. 初始狀態檢查：");
  proxy.reportStatus();

  // 測試簡單任務（代理直接處理）
  console.log("\n2. 執行簡單任務：");
  await proxy.executeTask("城市監控");

  // 測試情報收集（使用快取）
  console.log("\n3. 收集快取情報：");
  const info1 = proxy.gatherIntelligence("普通目標");
  console.log(`獲得情報：${info1}`);

  // 測試複雜任務（需要真實特工）
  console.log("\n4. 執行複雜任務：");
  await proxy.executeTask("敵方基地滲透");

  // 測試新情報收集（需要真實特工）
  console.log("\n5. 收集新情報：");
  const info2 = proxy.gatherIntelligence("高價值目標");
  console.log(`獲得情報：${info2}`);

  // 最終狀態檢查
  console.log("\n6. 最終狀態檢查：");
  proxy.reportStatus();

  // 測試權限不足的情況
  console.log("\n7. 測試權限控制：");
  const lowLevelProxy = new ProxySecretAgent("Rookie-001", 3);
  await lowLevelProxy.executeTask("核心機密任務");
}

// 執行範例
runProxyExample();

/** output
=== 歡迎來到影子特工組織 ===

👤 代理特工 Ghost-001 已部署 (權限等級：7)
=== Ghost-001 代理能力 ===
🛡️  保護真實特工身份
⚡ 處理簡單任務
🗄️  快取常用情報
🔐 權限控制與驗證
「無需驚動本尊，我在此為您代勞。」

1. 初始狀態檢查：
📊 Ghost-001 狀態：正常運作
🔒 真實特工未啟動（保持隱密）

2. 執行簡單任務：
📞 Ghost-001 接收任務請求：城市監控
💼 Ghost-001 直接處理簡單任務：城市監控
✅ 任務完成，無需驚動真實特工

3. 收集快取情報：
🔎 Ghost-001 分析情報請求：普通目標
📋 使用快取情報，避免暴露真實特工
獲得情報：快取情報：普通目標的基本資料

4. 執行複雜任務：
📞 Ghost-001 接收任務請求：敵方基地滲透
⚠️  高級任務檢測，轉交真實特工處理
🔐 代理特工驗證通過，正在聯繫真實特工...
🕵️ 真實特工 Shadow-007 已就位於 機密位置
🎯 Shadow-007 正在執行高級任務：敵方基地滲透
⚠️  風險警告：真實身份可能暴露！
//promise
✅ 任務完成：敵方基地滲透

5. 收集新情報：
🔎 Ghost-001 分析情報請求：高價值目標
🚨 需要新情報，聯繫真實特工
🔍 Shadow-007 親自收集關於 高價值目標 的機密情報
獲得情報：機密等級A：高價值目標 的詳細檔案

6. 最終狀態檢查：
📊 Ghost-001 狀態：正常運作
🔗 真實特工連線狀態：
📋 Shadow-007 狀態報告：待命中 (位置：機密位置)

7. 測試權限控制：
👤 代理特工 Rookie-001 已部署 (權限等級：3)
📞 Rookie-001 接收任務請求：核心機密任務
❌ 權限不足！任務 核心機密任務 需要更高權限
 */
```

## 小總結

Proxy 設計模式就像我們故事中的神秘影子特工，為真實物件提供`代理`多了一層來間接控制對它的訪問

- executeTask 簡單任務包含關鍵字: "監控" "巡邏" "觀察"
- gatherIntelligence 若為"普通目標"則會判斷為快取
- gatherIntelligence 非快取 會聯繫真實特工

**核心特點：**

- **訪問控制**：代理可以控制對真實物件的訪問，提供權限檢查
- **延遲初始化**：只有在真正需要時才創建昂貴的真實物件
- **快取機制**：代理可以快取結果，避免重複的昂貴操作
- **附加功能**：在不修改原始類別的前提下，添加額外的行為

**常見類型：**

- **虛擬代理**：控制對資源消耗較大物件的訪問（ex: 圖片延遲載入）
- **保護代理**：控制對敏感物件的訪問權限（ex: 權限驗證）
- **快取代理**：為開銷大的運算結果提供暫時存儲（ex: Web 快取）

**使用時機：**

- 需要控制對物件的訪問時（ex: 權限控制、延遲載入）
- 想要在訪問物件時添加額外功能（ex: 日誌記錄、效能監控）
