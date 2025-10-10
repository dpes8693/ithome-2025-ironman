package file14;

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