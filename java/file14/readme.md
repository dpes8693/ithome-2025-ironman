
```java
package file14;
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
package file14;
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
package file14;
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
package file14;
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

        // 測試權限不足的情況
        System.out.println("\n7. 測試權限控制：");
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