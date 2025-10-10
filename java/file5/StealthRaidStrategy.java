package file5;
public class StealthRaidStrategy implements BattleStrategy {
    @Override
    public void execute() {
        System.out.println("夜幕降臨，刺客小隊悄然行動！");
        System.out.println("潛行繞後，直搗敵軍指揮部！");
        System.out.println("斬首行動成功，敵軍群龍無首！");
    }

    @Override
    public String getStrategyName() {
        return "奇襲策略";
    }

    @Override
    public String getDescription() {
        return "適用於夜戰或敵軍戒備鬆懈時使用";
    }
}