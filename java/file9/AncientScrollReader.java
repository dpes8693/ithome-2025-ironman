package file9;

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