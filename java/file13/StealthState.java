package file13;

// 潛伏隱匿狀態
public class StealthState implements MageState {
    @Override
    public void cast() {
        System.out.println("🌙 暗影箭矢，無聲無息命中要害！");
    }

    @Override
    public void defend() {
        System.out.println("🌙 影分身術，真身消失在陰影中！");
    }

    @Override
    public void move() {
        System.out.println("🌙 如影隨行，悄無聲息地潛行！");
    }

    @Override
    public String getFormName() {
        return "潛伏隱匿形態";
    }
}