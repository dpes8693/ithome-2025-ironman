package file14;

// 使用範例
public class ProxyPatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到影子特工組織 ===\n");

        // 創建代理特工
        ProxySecretAgent proxy = new ProxySecretAgent("Ghost-001", 7);
        proxy.showProxyCapabilities();
        System.out.println();

        // // 測試狀態報告
        // System.out.println("1. 初始狀態檢查：");
        // proxy.reportStatus();
        // System.out.println();

        // // 測試簡單任務（代理直接處理）
        // System.out.println("2. 執行簡單任務：");
        // proxy.executeTask("城市監控");
        // System.out.println();

        // // 測試情報收集（使用快取）
        // System.out.println("3. 收集快取情報：");
        // String info1 = proxy.gatherIntelligence("普通目標");
        // System.out.println("獲得情報：" + info1);
        // System.out.println();

        // 測試複雜任務（需要真實特工）
        System.out.println("4. 執行複雜任務：");
        proxy.executeTask("敵方基地滲透");
        System.out.println();

        // // 測試新情報收集（需要真實特工）
        // System.out.println("5. 收集新情報：");
        // String info2 = proxy.gatherIntelligence("高價值目標");
        // System.out.println("獲得情報：" + info2);
        // System.out.println();

        // // 最終狀態檢查
        // System.out.println("6. 最終狀態檢查：");
        // proxy.reportStatus();

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