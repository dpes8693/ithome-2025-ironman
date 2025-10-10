package file13;

// 變形術士（Context上下文）
public class ShapeshifterMage {
    private MageState currentState;
    private String name;

    public ShapeshifterMage(String name) {
        this.name = name;
        // 預設狀態為火焰形態
        this.currentState = new FireState();
    }

    // 切換狀態
    public void changeState(MageState newState) {
        this.currentState = newState;
        System.out.println("✨ " + name + " 變形為：" + currentState.getFormName());
    }

    // 委託給當前狀態執行動作
    public void cast() {
        System.out.print(name + " - ");
        currentState.cast();
    }

    public void defend() {
        System.out.print(name + " - ");
        currentState.defend();
    }

    public void move() {
        System.out.print(name + " - ");
        currentState.move();
    }

    public String getCurrentForm() {
        return currentState.getFormName();
    }

    // 展示所有可用形態
    public void showAvailableForms() {
        System.out.println("=== " + name + " 的變形能力 ===");
        System.out.println("🔥 火焰狂怒形態 - 強力攻擊");
        System.out.println("❄️ 冰霜防禦形態 - 堅固防守");
        System.out.println("⚡ 雷電突襲形態 - 快速打擊");
        System.out.println("🌙 潛伏隱匿形態 - 隱密行動");
        System.out.println("「我非我，我為此刻之形。」");
    }
}