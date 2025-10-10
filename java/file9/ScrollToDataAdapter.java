package file9;

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