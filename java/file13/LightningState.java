package file13;

// 雷電突襲狀態
public class LightningState implements MageState {
    @Override
    public void cast() {
        System.out.println("⚡ 雷霆萬鈞！閃電鏈連續打擊！");
    }

    @Override
    public void defend() {
        System.out.println("⚡ 雷電護罩反彈攻擊！");
    }

    @Override
    public void move() {
        System.out.println("⚡ 閃電瞬移，瞬間出現在敵人身後！");
    }

    @Override
    public String getFormName() {
        return "雷電突襲形態";
    }
}