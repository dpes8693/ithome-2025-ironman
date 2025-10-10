package file23;
// 年輕法師（原發器）
public class YoungWizard {
    private int manaLevel;
    private String spellForm;
    private String experimentPhase;

    public YoungWizard() {
        this.manaLevel = 100;
        this.spellForm = "人形";
        this.experimentPhase = "準備階段";
    }

    // 創建備忘錄
    public MagicState createMemento() {
        System.out.println("老法師記錄當前狀態...");
        return new MagicState(manaLevel, spellForm, experimentPhase);
    }

    // 從備忘錄恢復狀態
    public void restoreFromMemento(MagicState memento) {
        if (memento != null) {
            this.manaLevel = memento.getManaLevel();
            this.spellForm = memento.getSpellForm();
            this.experimentPhase = memento.getExperimentPhase();
            System.out.println("從備忘錄泡泡中恢復狀態！");
        }
    }

    // 進行魔法實驗
    public void performExperiment(String newForm, String phase) {
        System.out.println("正在施展變形術...");
        this.spellForm = newForm;
        this.experimentPhase = phase;
        this.manaLevel -= 20;
        System.out.println("當前狀態: " + getCurrentState());
    }

    // 消耗魔力
    public void consumeMana(int amount) {
        this.manaLevel -= amount;
        System.out.println("消耗魔力 " + amount + "，當前魔力: " + manaLevel);
    }

    // 獲取當前狀態描述
    public String getCurrentState() {
        return String.format("魔力:%d, 形態:%s, 階段:%s", 
                           manaLevel, spellForm, experimentPhase);
    }

    // 顯示當前狀態
    public void showCurrentState() {
        System.out.println("=== 年輕法師當前狀態 ===");
        System.out.println(getCurrentState());
    }
}
