package file5;
public class TacticalCommander {
    private BattleStrategy currentStrategy;
    private String commanderName;

    public TacticalCommander(String name) {
        this.commanderName = name;
    }

    // 設定戰術策略
    public void setStrategy(BattleStrategy strategy) {
        this.currentStrategy = strategy;
        System.out.println(commanderName + " 選擇了: " + strategy.getStrategyName());
        System.out.println("策略說明: " + strategy.getDescription());
    }

    // 執行當前策略
    public void executeBattle() {
        if (currentStrategy == null) {
            System.out.println("警告：尚未選擇作戰策略！");
            return;
        }

        System.out.println("\n=== 戰術執行開始 ===");
        System.out.println("指揮官 " + commanderName + " 下達作戰命令：");
        currentStrategy.execute();
        System.out.println("=== 戰術執行完畢 ===\n");
    }

    // 分析戰況並建議策略
    public static void analyzeBattlefield() {
        System.out.println("=== 戰場分析 ===");
        System.out.println("1. 敵軍兵力薄弱，士氣低落 → 建議使用正面突擊");
        System.out.println("2. 地形開闊，敵軍無掩護 → 建議使用遠程壓制");
        System.out.println("3. 夜戰環境，敵軍戒備鬆懈 → 建議使用奇襲策略");
        System.out.println("4. 敵強我弱，需要拖延時間 → 建議使用防禦策略");
        System.out.println("「面對不同的敵人，需有不同的戰法。」\n");
    }
}