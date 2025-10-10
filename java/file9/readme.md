
```java
package file9;
//ModernDataProcessor.java
// 現代數據處理介面
public interface ModernDataProcessor {
    void processDigitalData(String jsonData);
    String getProcessedResult();
}
```

```java
package file9;
//AncientScrollReader.java
// 古老卷軸讀取器（被適配者）
public class AncientScrollReader {
    private String lastReadContent;

    public void readRunicText(String runicSymbols) {
        System.out.println("古老卷軸讀取器：解析符文...");
        System.out.println("符文內容：" + runicSymbols);

        // 模擬古老符文的處理邏輯
        String processedContent = translateRunesToAncientWisdom(runicSymbols);
        this.lastReadContent = processedContent;

        System.out.println("解析完成：" + processedContent);
    }

    public String getAncientWisdom() {
        return lastReadContent != null ? lastReadContent : "未讀取任何內容";
    }

    private String translateRunesToAncientWisdom(String runes) {
        // 模擬符文轉換邏輯
        return "古老智慧：" + runes.toUpperCase().replace("DATA", "神諭").replace("JSON", "符文卷軸");
    }
}
```

```java
package file9;
//DataFormatConverter.java
// 數據格式轉換工具
public class DataFormatConverter {

    public static String jsonToRunes(String jsonData) {
        System.out.println("機械神諭者：將現代數據轉換為古老符文...");

        // 簡化的 JSON 到符文轉換
        // 先替換關鍵字，再處理結構符號
        String runes = jsonData
            .replace("data", "DATA")
            .replace("value", "神諭之力")
            .replace("{", "⟨")
            .replace("}", "⟩")
            .replace(":", "∶")
            .replace(",", "◦")
            .replaceAll("\"([^\"]*?)\"", "〈$1〉");  // 將所有被引號包圍的內容轉為 〈內容〉

        System.out.println("轉換結果：" + runes);
        return runes;
    }

    public static String ancientWisdomToJson(String ancientText) {
        System.out.println("機械神諭者：將古老智慧轉換為現代格式...");

        // 簡化的古老智慧到 JSON 轉換
        String json = "{"
            + "\"wisdom\": \"" + ancientText + "\","
            + "\"source\": \"ancient_scroll\","
            + "\"processed_by\": \"mechanical_oracle\""
            + "}";

        System.out.println("現代化結果：" + json);
        return json;
    }
}
```

```java
package file9;
//ScrollToDataAdapter.java
// 轉接器：將古老卷軸讀取器適配到現代數據處理介面
public class ScrollToDataAdapter implements ModernDataProcessor {
    private AncientScrollReader scrollReader;
    private String processedResult;

    public ScrollToDataAdapter(AncientScrollReader scrollReader) {
        this.scrollReader = scrollReader;
        System.out.println("機械神諭者啟動：準備連接古老與現代...");
    }

    @Override
    public void processDigitalData(String jsonData) {
        System.out.println("\n=== 機械神諭者開始轉接工作 ===");
        System.out.println("接收到現代數據：" + jsonData);

        // 1. 將 JSON 數據轉換為符文格式
        String runicData = DataFormatConverter.jsonToRunes(jsonData);

        // 2. 使用古老卷軸讀取器處理符文
        scrollReader.readRunicText(runicData);

        // 3. 獲取古老智慧並轉換回現代格式
        String ancientWisdom = scrollReader.getAncientWisdom();
        this.processedResult = DataFormatConverter.ancientWisdomToJson(ancientWisdom);

        System.out.println("=== 轉接完成 ===\n");
    }

    @Override
    public String getProcessedResult() {
        return processedResult != null ? processedResult : "尚未處理任何數據";
    }
}
```

```java
package file9;
//ModernDataCenter.java
// 現代數據中心
public class ModernDataCenter {
    private ModernDataProcessor processor;

    public ModernDataCenter(ModernDataProcessor processor) {
        this.processor = processor;
    }

    public void analyzeData(String description, String jsonData) {
        System.out.println("現代數據中心：開始分析 " + description);
        processor.processDigitalData(jsonData);

        String result = processor.getProcessedResult();
        System.out.println("分析結果：" + result);
    }
}
```

```java
package file9;
//AdapterPatternExample.java
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
```