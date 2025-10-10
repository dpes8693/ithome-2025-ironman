package file13;

// 火焰狂怒狀態
public class FireState implements MageState {
    @Override
    public void cast() {
        System.out.println("🔥 火焰爆發！釋放大範圍火球術！");
    }

    @Override
    public void defend() {
        System.out.println("🔥 火焰護盾展開，燒毀來犯敵人！");
    }

    @Override
    public void move() {
        System.out.println("🔥 火焰衝刺，留下燃燒足跡！");
    }

    @Override
    public String getFormName() {
        return "火焰狂怒形態";
    }
}