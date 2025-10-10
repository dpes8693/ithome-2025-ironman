package file5;
public class StrategyPatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到戰術大師的指揮帳 ===\n");

        // 創建指揮官
        TacticalCommander commander = new TacticalCommander("艾瑞克");

        // 分析戰場情況
        TacticalCommander.analyzeBattlefield();

        // 創建不同的策略
        BattleStrategy frontalAssault = new FrontalAssaultStrategy();
        BattleStrategy rangedSuppression = new RangedSuppressionStrategy();
        BattleStrategy stealthRaid = new StealthRaidStrategy();
        BattleStrategy defensive = new DefensiveStrategy();

        // 場景一：面對士氣低落的敵軍
        System.out.println("【場景一】偵查兵回報：敵軍士氣低落，防線鬆散");
        commander.setStrategy(frontalAssault);
        commander.executeBattle();

        // 場景二：在開闊平原作戰
        System.out.println("【場景二】來到開闊平原，敵軍缺乏掩護");
        commander.setStrategy(rangedSuppression);
        commander.executeBattle();

        // 場景三：夜戰環境
        System.out.println("【場景三】夜幕降臨，適合進行奇襲");
        commander.setStrategy(stealthRaid);
        commander.executeBattle();

        // 場景四：面對強大敵軍
        System.out.println("【場景四】敵軍實力強大，需要保存實力");
        commander.setStrategy(defensive);
        commander.executeBattle();
    }
}