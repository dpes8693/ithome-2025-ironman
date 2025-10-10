package file9;

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