package file9;

// 使用範例
public class AdapterPatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到古今融合的智慧殿堂 ===\n");

        // 創建古老的卷軸讀取器
        AncientScrollReader ancientReader = new AncientScrollReader();

        // 創建轉接器，讓古老讀取器能處理現代數據
        ScrollToDataAdapter adapter = new ScrollToDataAdapter(ancientReader);

        // 創建現代數據中心
        ModernDataCenter dataCenter = new ModernDataCenter(adapter);

        // 測試數據處理
        System.out.println("測試案例 1：處理用戶數據");
        String userData = "{\"name\":\"冒險者\",\"level\":25,\"data\":\"勇氣之力\"}";
        dataCenter.analyzeData("用戶資料", userData);

        System.out.println("\n" + "=".repeat(50) + "\n");

        System.out.println("測試案例 2：處理魔法數據");
        String magicData = "{\"spell\":\"火球術\",\"power\":100,\"data\":\"元素精華\"}";
        dataCenter.analyzeData("魔法咒語", magicData);

        System.out.println("\n" + "=".repeat(50) + "\n");

        System.out.println("測試案例 3：直接使用古老讀取器");
        System.out.println("古老讀取器獨立運作：");
        ancientReader.readRunicText("⟨神秘符文⟩∶⟨強大力量⟩");
        System.out.println("獲得智慧：" + ancientReader.getAncientWisdom());

        /**output
        === 歡迎來到古今融合的智慧殿堂 ===

        機械神諭者啟動：準備連接古老與現代...
        現代數據中心：開始分析 用戶資料

        === 機械神諭者開始轉接工作 ===
        接收到現代數據：{"name":"冒險者","level":25,"data":"勇氣之力"}
        機械神諭者：將現代數據轉換為古老符文...
        轉換結果：⟨〈name〉∶〈冒險者〉◦〈level〉∶25◦〈DATA〉∶〈勇氣之力〉⟩
        古老卷軸讀取器：解析符文...
        符文內容：⟨〈name〉∶〈冒險者〉◦〈level〉∶25◦〈DATA〉∶〈勇氣之力〉⟩
        解析完成：古老智慧：⟨〈NAME〉∶〈冒險者〉◦〈LEVEL〉∶25◦〈神諭〉∶〈勇氣之力〉⟩
        機械神諭者：將古老智慧轉換為現代格式...
        現代化結果：{"wisdom": "古老智慧：⟨〈NAME〉∶〈冒險者〉◦〈LEVEL〉∶25◦〈神諭〉∶〈勇氣之力〉⟩","source": "ancient_scroll","processed_by": "mechanical_oracle"}
        === 轉接完成 ===

        分析結果：{"wisdom": "古老智慧：⟨〈NAME〉∶〈冒險者〉◦〈LEVEL〉∶25◦〈神諭〉∶〈勇氣之力〉⟩","source": "ancient_scroll","processed_by": "mechanical_oracle"}

        ==================================================

        現代數據中心：開始分析 魔法咒語

        === 機械神諭者開始轉接工作 ===
        接收到現代數據：{"spell":"火球術","power":100,"data":"元素精華"}
        機械神諭者：將現代數據轉換為古老符文...
        轉換結果：⟨〈spell〉∶〈火球術〉◦〈power〉∶100◦〈DATA〉∶〈元素精華〉⟩
        古老卷軸讀取器：解析符文...
        符文內容：⟨〈spell〉∶〈火球術〉◦〈power〉∶100◦〈DATA〉∶〈元素精華〉⟩
        解析完成：古老智慧：⟨〈SPELL〉∶〈火球術〉◦〈POWER〉∶100◦〈神諭〉∶〈元素精華〉⟩
        機械神諭者：將古老智慧轉換為現代格式...
        現代化結果：{"wisdom": "古老智慧：⟨〈SPELL〉∶〈火球術〉◦〈POWER〉∶100◦〈神諭〉∶〈元素精華〉⟩","source": "ancient_scroll","processed_by": "mechanical_oracle"}
        === 轉接完成 ===

        分析結果：{"wisdom": "古老智慧：⟨〈SPELL〉∶〈火球術〉◦〈POWER〉∶100◦〈神諭〉∶〈元素精華〉⟩","source": "ancient_scroll","processed_by": "mechanical_oracle"}

        ==================================================

        測試案例 3：直接使用古老讀取器
        古老讀取器獨立運作：
        古老卷軸讀取器：解析符文...
        符文內容：⟨神秘符文⟩∶⟨強大力量⟩
        解析完成：古老智慧：⟨神秘符文⟩∶⟨強大力量⟩
        獲得智慧：古老智慧：⟨神秘符文⟩∶⟨強大力量⟩
        */
    }
}