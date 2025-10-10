package file5;
public class FrontalAssaultStrategy implements BattleStrategy {
    @Override
    public void execute() {
        System.out.println("騎兵集結完畢！");
        System.out.println("重裝騎兵發起衝鋒，直取敵軍心臟！");
        System.out.println("正面突破敵軍防線，勢如破竹！");
    }

    @Override
    public String getStrategyName() {
        return "正面突擊策略";
    }

    @Override
    public String getDescription() {
        return "適用於敵軍士氣低落或防線薄弱時使用";
    }
}