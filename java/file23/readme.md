
```java
//MagicState.java
// 魔法狀態備忘錄
public class MagicState {
    private final int manaLevel;
    private final String spellForm;
    private final String experimentPhase;
    private final long timestamp;

    public MagicState(int manaLevel, String spellForm, String experimentPhase) {
        this.manaLevel = manaLevel;
        this.spellForm = spellForm;
        this.experimentPhase = experimentPhase;
        this.timestamp = System.currentTimeMillis();
    }

    // 只提供給原發器的恢復方法
    protected int getManaLevel() {
        return manaLevel;
    }

    protected String getSpellForm() {
        return spellForm;
    }

    protected String getExperimentPhase() {
        return experimentPhase;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("魔法狀態[魔力:%d, 形態:%s, 階段:%s, 時間:%d]", 
                           manaLevel, spellForm, experimentPhase, timestamp);
    }
}
```

```java
//YoungWizard.java
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
```

```java
//MagicApprentice.java
// 魔法學徒（管理者）
import java.util.*;

public class MagicApprentice {
    private List<MagicState> mementoHistory;
    private String apprenticeName;

    public MagicApprentice(String name) {
        this.apprenticeName = name;
        this.mementoHistory = new ArrayList<>();
    }

    // 保存備忘錄
    public void saveMemento(MagicState memento) {
        mementoHistory.add(memento);
        System.out.println(apprenticeName + " 保存了第 " + mementoHistory.size() + " 個備忘錄泡泡");
    }

    // 獲取最近的備忘錄
    public MagicState getLastMemento() {
        if (mementoHistory.isEmpty()) {
            System.out.println("沒有可用的備忘錄泡泡！");
            return null;
        }
        MagicState memento = mementoHistory.get(mementoHistory.size() - 1);
        System.out.println(apprenticeName + " 取出最近的備忘錄泡泡");
        return memento;
    }

    // 獲取指定索引的備忘錄
    public MagicState getMementoAt(int index) {
        if (index < 0 || index >= mementoHistory.size()) {
            System.out.println("無效的備忘錄索引！");
            return null;
        }
        System.out.println(apprenticeName + " 取出第 " + (index + 1) + " 個備忘錄泡泡");
        return mementoHistory.get(index);
    }

    // 顯示所有備忘錄歷史
    public void showMementoHistory() {
        System.out.println("=== " + apprenticeName + " 的備忘錄歷史 ===");
        if (mementoHistory.isEmpty()) {
            System.out.println("目前沒有保存任何備忘錄");
            return;
        }

        for (int i = 0; i < mementoHistory.size(); i++) {
            System.out.println((i + 1) + ". " + mementoHistory.get(i));
        }
    }

    // 清除所有備忘錄
    public void clearHistory() {
        mementoHistory.clear();
        System.out.println(apprenticeName + " 清除了所有備忘錄泡泡");
    }

    public int getMementoCount() {
        return mementoHistory.size();
    }
}
```

```java
//MementoPatternExample.java
// 使用範例
public class MementoPatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到魔法研究學院 ===\n");

        // 創建角色
        YoungWizard wizard = new YoungWizard();
        MagicApprentice apprentice = new MagicApprentice("學徒艾莉絲");

        // 顯示初始狀態
        wizard.showCurrentState();
        System.out.println();

        // 第一個安全點：保存初始狀態
        MagicState checkpoint1 = wizard.createMemento();
        apprentice.saveMemento(checkpoint1);
        System.out.println();

        // 開始實驗：變形術
        wizard.performExperiment("貓形", "初級變形");
        wizard.consumeMana(10);
        System.out.println();

        // 第二個安全點：保存中間狀態
        MagicState checkpoint2 = wizard.createMemento();
        apprentice.saveMemento(checkpoint2);
        System.out.println();

        // 繼續實驗：高級變形
        wizard.performExperiment("龍形", "高級變形");
        wizard.consumeMana(30);
        System.out.println();

        // 實驗出錯！法師變成了青蛙
        wizard.performExperiment("青蛙", "實驗失敗");
        wizard.consumeMana(20);
        System.out.println("糟糕！實驗失敗了！");
        wizard.showCurrentState();
        System.out.println();

        // 顯示備忘錄歷史
        apprentice.showMementoHistory();
        System.out.println();

        // 恢復到第二個安全點（龍形之前）
        System.out.println("=== 恢復到第二個安全點 ===");
        MagicState restorePoint = apprentice.getMementoAt(1);
        wizard.restoreFromMemento(restorePoint);
        wizard.showCurrentState();
        System.out.println();

        // 或者恢復到最初的安全點
        System.out.println("=== 恢復到初始狀態 ===");
        MagicState initialState = apprentice.getMementoAt(0);
        wizard.restoreFromMemento(initialState);
        wizard.showCurrentState();

        /**output
        === 歡迎來到魔法研究學院 ===

        === 年輕法師當前狀態 ===
        魔力:100, 形態:人形, 階段:準備階段

        老法師記錄當前狀態...
        學徒艾莉絲 保存了第 1 個備忘錄泡泡

        正在施展變形術...
        當前狀態: 魔力:80, 形態:貓形, 階段:初級變形
        消耗魔力 10，當前魔力: 70

        老法師記錄當前狀態...
        學徒艾莉絲 保存了第 2 個備忘錄泡泡

        正在施展變形術...
        當前狀態: 魔力:50, 形態:龍形, 階段:高級變形
        消耗魔力 30，當前魔力: 20

        正在施展變形術...
        當前狀態: 魔力:0, 形態:青蛙, 階段:實驗失敗
        消耗魔力 20，當前魔力: -20
        糟糕！實驗失敗了！
        === 年輕法師當前狀態 ===
        魔力:-20, 形態:青蛙, 階段:實驗失敗

        === 學徒艾莉絲 的備忘錄歷史 ===
        1. 魔法狀態[魔力:100, 形態:人形, 階段:準備階段, 時間:1727075847123]
        2. 魔法狀態[魔力:70, 形態:貓形, 階段:初級變形, 時間:1727075847124]

        === 恢復到第二個安全點 ===
        學徒艾莉絲 取出第 2 個備忘錄泡泡
        從備忘錄泡泡中恢復狀態！
        === 年輕法師當前狀態 ===
        魔力:70, 形態:貓形, 階段:初級變形

        === 恢復到初始狀態 ===
        學徒艾莉絲 取出第 1 個備忘錄泡泡
        從備忘錄泡泡中恢復狀態！
        === 年輕法師當前狀態 ===
        魔力:100, 形態:人形, 階段:準備階段
        */
    }
}
```