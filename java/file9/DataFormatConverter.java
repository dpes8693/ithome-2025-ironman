package file9;

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