package file13;

// 冰霜防禦狀態
public class IceState implements MageState {
    @Override
    public void cast() {
        System.out.println("❄️ 冰霜風暴！凍結所有敵人！");
    }

    @Override
    public void defend() {
        System.out.println("❄️ 堅固冰牆升起，形成絕對防護！");
    }

    @Override
    public void move() {
        System.out.println("❄️ 冰面滑行，優雅且迅速！");
    }

    @Override
    public String getFormName() {
        return "冰霜防禦形態";
    }
}