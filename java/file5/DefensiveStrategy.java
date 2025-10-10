package file5;
public class DefensiveStrategy implements BattleStrategy {
    @Override
    public void execute() {
        System.out.println("盾牆架起，長槍如林！");
        System.out.println("堅守要塞，以逸待勞！");
        System.out.println("消耗敵軍體力，等待反擊時機！");
    }

    @Override
    public String getStrategyName() {
        return "防禦策略";
    }

    @Override
    public String getDescription() {
        return "適用於敵強我弱或需要拖延時間時使用";
    }
}