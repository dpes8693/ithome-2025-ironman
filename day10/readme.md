# Day10 轉接器模式 (Adapter Pattern)

## 擬人化角色：【靈活的機械神諭者】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/9-Adapter.png)

- 種族： 人工智慧/機器人
- 外貌： 一位流線型、銀色肌膚的女性機器人，雙眼閃爍著智慧的藍光。她的身體連接了無數的數據線纜，將古老的捲軸和現代的電腦螢幕連接起來。她懸浮在一個充滿古老書籍和現代科技設備的圖書館中央。
- 性格： 理智、高效、善於轉換與溝通。她能夠在看似不相容的系統之間建立橋樑，讓它們正常運作。
- 能力： 將一個介面轉換成另一個介面，讓原本與客戶端不相容的介面可以正常工作。她能理解古老的符文訊息，並將其轉譯為現代數據格式；也能將現代數據反向轉譯成古老的符號。
- 代表語： 「無論何種語言，我都能讓資訊暢通無阻。」
- 背景故事： 在一個結合了古老智慧和尖端科技的文明中，這位機械神諭者是唯一能夠連接不同時代資訊的實體。她可以讀取古老的魔法卷軸，將其中記載的咒語和知識轉換為可供現代法術生成器使用的程式碼。同時，她也能將現代的科研數據翻譯成古老的預言形式，供先知們解讀。她就是不同系統之間的「轉接器」，確保了文明的知識得以流傳和創新。

---

## 範例

### Java

```java
//ModernDataProcessor.java
// 現代數據處理介面
public interface ModernDataProcessor {
    void processDigitalData(String jsonData);
    String getProcessedResult();
}
```

```java
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

### JavaScript

```javascript
// 現代數據處理介面
class ModernDataProcessor {
  processDigitalData(jsonData) {
    throw new Error("子類必須實現 processDigitalData 方法");
  }

  getProcessedResult() {
    throw new Error("子類必須實現 getProcessedResult 方法");
  }
}

// 古老卷軸讀取器（被適配者）
class AncientScrollReader {
  constructor() {
    this.lastReadContent = null;
  }

  readRunicText(runicSymbols) {
    console.log("古老卷軸讀取器：解析符文...");
    console.log(`符文內容：${runicSymbols}`);

    // 模擬古老符文的處理邏輯
    const processedContent = this.translateRunesToAncientWisdom(runicSymbols);
    this.lastReadContent = processedContent;

    console.log(`解析完成：${processedContent}`);
  }

  getAncientWisdom() {
    return this.lastReadContent || "未讀取任何內容";
  }

  translateRunesToAncientWisdom(runes) {
    // 模擬符文轉換邏輯
    return `古老智慧：${runes
      .toUpperCase()
      .replace(/DATA/g, "神諭")
      .replace(/JSON/g, "符文卷軸")}`;
  }
}

// 數據格式轉換工具
class DataFormatConverter {
  static jsonToRunes(jsonData) {
    console.log("機械神諭者：將現代數據轉換為古老符文...");

    // 簡化的 JSON 到符文轉換
    // 先替換關鍵字，再處理結構符號
    let runes = jsonData.replace(/data/g, "DATA").replace(/value/g, "神諭之力");

    // 處理 JSON 結構轉換為符文
    runes = runes
      .replace(/\{/g, "⟨")
      .replace(/\}/g, "⟩")
      .replace(/:/g, "∶")
      .replace(/,/g, "◦")
      .replace(/"([^"]*?)"/g, "〈$1〉"); // 將所有被引號包圍的內容轉為 〈內容〉

    console.log(`轉換結果：${runes}`);
    return runes;
  }

  static ancientWisdomToJson(ancientText) {
    console.log("機械神諭者：將古老智慧轉換為現代格式...");

    // 簡化的古老智慧到 JSON 轉換
    const json = JSON.stringify({
      wisdom: ancientText,
      source: "ancient_scroll",
      processed_by: "mechanical_oracle",
    });

    console.log(`現代化結果：${json}`);
    return json;
  }
}

// 轉接器：將古老卷軸讀取器適配到現代數據處理介面
class ScrollToDataAdapter extends ModernDataProcessor {
  constructor(scrollReader) {
    super();
    this.scrollReader = scrollReader;
    this.processedResult = null;
    console.log("機械神諭者啟動：準備連接古老與現代...");
  }

  processDigitalData(jsonData) {
    console.log("\n=== 機械神諭者開始轉接工作 ===");
    console.log(`接收到現代數據：${jsonData}`);

    // 1. 將 JSON 數據轉換為符文格式
    const runicData = DataFormatConverter.jsonToRunes(jsonData);

    // 2. 使用古老卷軸讀取器處理符文
    this.scrollReader.readRunicText(runicData);

    // 3. 獲取古老智慧並轉換回現代格式
    const ancientWisdom = this.scrollReader.getAncientWisdom();
    this.processedResult =
      DataFormatConverter.ancientWisdomToJson(ancientWisdom);

    console.log("=== 轉接完成 ===\n");
  }

  getProcessedResult() {
    return this.processedResult || "尚未處理任何數據";
  }
}

// 現代數據中心
class ModernDataCenter {
  constructor(processor) {
    this.processor = processor;
  }

  analyzeData(description, jsonData) {
    console.log(`現代數據中心：開始分析 ${description}`);
    this.processor.processDigitalData(jsonData);

    const result = this.processor.getProcessedResult();
    console.log(`分析結果：${result}`);
  }
}

// 使用範例
console.log("=== 歡迎來到古今融合的智慧殿堂 ===\n");

// 創建古老的卷軸讀取器
const ancientReader = new AncientScrollReader();

// 創建轉接器，讓古老讀取器能處理現代數據
const adapter = new ScrollToDataAdapter(ancientReader);

// 創建現代數據中心
const dataCenter = new ModernDataCenter(adapter);

// 測試數據處理
console.log("測試案例 1：處理用戶數據");
const userData = '{"name":"冒險者","level":25,"data":"勇氣之力"}';
dataCenter.analyzeData("用戶資料", userData);

console.log("\n" + "=".repeat(50) + "\n");

console.log("測試案例 2：處理魔法數據");
const magicData = '{"spell":"火球術","power":100,"data":"元素精華"}';
dataCenter.analyzeData("魔法咒語", magicData);

console.log("\n" + "=".repeat(50) + "\n");

console.log("測試案例 3：直接使用古老讀取器");
console.log("古老讀取器獨立運作：");
ancientReader.readRunicText("⟨神秘符文⟩∶⟨強大力量⟩");
console.log(`獲得智慧：${ancientReader.getAncientWisdom()}`);

/** output
=== 歡迎來到古今融合的智慧殿堂 ===

機械神諭者啟動：準備連接古老與現代...
測試案例 1：處理用戶數據
現代數據中心：開始分析 用戶資料

=== 機械神諭者開始轉接工作 ===
接收到現代數據：{"name":"冒險者","level":25,"data":"勇氣之力"}
機械神諭者：將現代數據轉換為古老符文...
轉換結果：⟨〈name〉∶〈冒險者〉◦〈level〉∶25◦〈DATA〉∶〈勇氣之力〉⟩
古老卷軸讀取器：解析符文...
符文內容：⟨〈name〉∶〈冒險者〉◦〈level〉∶25◦〈DATA〉∶〈勇氣之力〉⟩
解析完成：古老智慧：⟨〈NAME〉∶〈冒險者〉◦〈LEVEL〉∶25◦〈神諭〉∶〈勇氣之力〉⟩
機械神諭者：將古老智慧轉換為現代格式...
現代化結果：{"wisdom":"古老智慧：⟨〈NAME〉∶〈冒險者〉◦〈LEVEL〉∶25◦〈神諭〉∶〈勇氣之力〉⟩","source":"ancient_scroll","processed_by":"mechanical_oracle"}
=== 轉接完成 ===

分析結果：{"wisdom":"古老智慧：⟨〈NAME〉∶〈冒險者〉◦〈LEVEL〉∶25◦〈神諭〉∶〈勇氣之力〉⟩","source":"ancient_scroll","processed_by":"mechanical_oracle"}

==================================================

測試案例 2：處理魔法數據
現代數據中心：開始分析 魔法咒語

=== 機械神諭者開始轉接工作 ===
接收到現代數據：{"spell":"火球術","power":100,"data":"元素精華"}
機械神諭者：將現代數據轉換為古老符文...
轉換結果：⟨〈spell〉∶〈火球術〉◦〈power〉∶100◦〈DATA〉∶〈元素精華〉⟩
古老卷軸讀取器：解析符文...
符文內容：⟨〈spell〉∶〈火球術〉◦〈power〉∶100◦〈DATA〉∶〈元素精華〉⟩
解析完成：古老智慧：⟨〈SPELL〉∶〈火球術〉◦〈POWER〉∶100◦〈神諭〉∶〈元素精華〉⟩
機械神諭者：將古老智慧轉換為現代格式...
現代化結果：{"wisdom":"古老智慧：⟨〈SPELL〉∶〈火球術〉◦〈POWER〉∶100◦〈神諭〉∶〈元素精華〉⟩","source":"ancient_scroll","processed_by":"mechanical_oracle"}
=== 轉接完成 ===

分析結果：{"wisdom":"古老智慧：⟨〈SPELL〉∶〈火球術〉◦〈POWER〉∶100◦〈神諭〉∶〈元素精華〉⟩","source":"ancient_scroll","processed_by":"mechanical_oracle"}

==================================================

測試案例 3：直接使用古老讀取器
古老讀取器獨立運作：
古老卷軸讀取器：解析符文...
符文內容：⟨神秘符文⟩∶⟨強大力量⟩
解析完成：古老智慧：⟨神秘符文⟩∶⟨強大力量⟩
獲得智慧：古老智慧：⟨神秘符文⟩∶⟨強大力量⟩
 */
```

## 小總結

Adapter Pattern 設計模式就像我們故事中的靈活機械神諭者，作為`不相容介面間的橋樑`，讓原本無法協作的系統能夠順利運作

**核心特點：**

- **介面轉換**：將一個類別的介面轉換成客戶端期望的另一個介面
- **相容性解決**：讓原本因介面不相容而無法一起工作的類別能夠協作
- **不修改原始碼**：在不修改既有程式碼的情況下整合新功能
- **雙向轉換**：可以在兩個系統間進行雙向的資料格式轉換

**使用時機：**

- 需要使用既有的類別，但其介面與需求不符（ex: 整合第三方函式庫）
- 想要重複使用一些既有的子類別，但缺少超類別的通用介面（ex: 統一不同資料庫的操作介面）
- 需要在新舊系統間建立橋樑（ex: 新版 API 相容舊版格式）
- 整合不同來源的資料格式（ex: XML 與 JSON 間的轉換）
