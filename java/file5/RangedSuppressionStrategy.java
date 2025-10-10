package file5;
public class RangedSuppressionStrategy implements BattleStrategy {
    @Override
    public void execute() {
        System.out.println("弓箭手就位，魔法師開始詠唱！");
        System.out.println("箭雨如暴風般傾瀉而下！");
        System.out.println("魔法轟炸覆蓋戰場，敵軍陷入混亂！");
    }

    @Override
    public String getStrategyName() {
        return "遠程壓制策略";
    }

    @Override
    public String getDescription() {
        return "適用於地形開闊，敵軍缺乏掩護時使用";
    }
}