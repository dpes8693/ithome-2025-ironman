# Day23 備忘錄模式 (Memento Pattern)

## 擬人化角色：【時光回溯的書記官】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/22-Memento.png)

- 種族： 人類/法師
- 外貌： 一位身穿深色長袍、面容嚴肅、白髮白鬚的老法師，他正用鵝毛筆在厚重的魔法典籍上書寫。在他身旁，一位年輕的學徒手持一個沙漏。周圍漂浮著數個透明的魔法泡泡，每個泡泡中都封存著法師過去某個時刻的「虛影」，代表著不同的歷史狀態。
- 性格： 沉穩、有遠見，精於記錄和恢復。他能夠捕捉任何物件在特定時刻的內部狀態，並將其獨立保存起來，以便日後需要時進行「還原」。
- 能力： 將一個物件的內部狀態儲存在另一個備忘錄物件中，備忘錄物件可用來還原物件狀態。這位老法師（原發器）在進行複雜的魔法實驗時，他可以隨時將自己（或實驗物件）的「當前狀態」（如魔力值、施法進度、實驗參數）封存到一個「備忘錄泡泡」中。如果實驗出錯，他可以選取任意一個泡泡，將自己或實驗物件的狀態精確地「還原」到那個時間點。年輕學徒（管理者）負責保管和遞交這些備忘錄泡泡。
- 代表語： 「時光無法倒流，但狀態可以重現。」
- 背景故事： 在一個高度危險的魔法研究學院，錯誤的實驗可能導致災難。為了確保研究人員的安全和實驗的可控性，這位老法師發明了「備忘錄魔法」。當一位年輕法師進行危險的變形術實驗時，每當他感覺自己處於一個「安全點」，老法師就會為他創建一個「備忘錄泡泡」，記錄下他當時的身體形態、魔力消耗等所有狀態。如果實驗過程中法師不慎變成一隻青蛙，他無需從頭開始，只需取出之前記錄的泡泡，就能瞬間變回人形，從安全的狀態繼續實驗。這些泡泡由學徒統一管理，成為了魔法世界中不可或缺的「撤銷/重做」機制。

---

## 範例

### Java

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
        1. 魔法狀態[魔力:100, 形態:人形, 階段:準備階段, 時間:1759838956934]
        2. 魔法狀態[魔力:70, 形態:貓形, 階段:初級變形, 時間:1759838956934]

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

### JavaScript

```javascript
// 魔法狀態備忘錄
class MagicState {
  constructor(manaLevel, spellForm, experimentPhase) {
    this.manaLevel = manaLevel;
    this.spellForm = spellForm;
    this.experimentPhase = experimentPhase;
    this.timestamp = Date.now();
  }

  // 只提供給原發器的恢復方法
  getManaLevel() {
    return this.manaLevel;
  }

  getSpellForm() {
    return this.spellForm;
  }

  getExperimentPhase() {
    return this.experimentPhase;
  }

  getTimestamp() {
    return this.timestamp;
  }

  toString() {
    return `魔法狀態[魔力:${this.manaLevel}, 形態:${this.spellForm}, 階段:${this.experimentPhase}, 時間:${this.timestamp}]`;
  }
}

// 年輕法師（原發器）
class YoungWizard {
  constructor() {
    this.manaLevel = 100;
    this.spellForm = "人形";
    this.experimentPhase = "準備階段";
  }

  // 創建備忘錄
  createMemento() {
    console.log("老法師記錄當前狀態...");
    return new MagicState(this.manaLevel, this.spellForm, this.experimentPhase);
  }

  // 從備忘錄恢復狀態
  restoreFromMemento(memento) {
    if (memento) {
      this.manaLevel = memento.getManaLevel();
      this.spellForm = memento.getSpellForm();
      this.experimentPhase = memento.getExperimentPhase();
      console.log("從備忘錄泡泡中恢復狀態！");
    }
  }

  // 進行魔法實驗
  performExperiment(newForm, phase) {
    console.log("正在施展變形術...");
    this.spellForm = newForm;
    this.experimentPhase = phase;
    this.manaLevel -= 20;
    console.log(`當前狀態: ${this.getCurrentState()}`);
  }

  // 消耗魔力
  consumeMana(amount) {
    this.manaLevel -= amount;
    console.log(`消耗魔力 ${amount}，當前魔力: ${this.manaLevel}`);
  }

  // 獲取當前狀態描述
  getCurrentState() {
    return `魔力:${this.manaLevel}, 形態:${this.spellForm}, 階段:${this.experimentPhase}`;
  }

  // 顯示當前狀態
  showCurrentState() {
    console.log("=== 年輕法師當前狀態 ===");
    console.log(this.getCurrentState());
  }
}

// 魔法學徒（管理者）
class MagicApprentice {
  constructor(name) {
    this.apprenticeName = name;
    this.mementoHistory = [];
  }

  // 保存備忘錄
  saveMemento(memento) {
    this.mementoHistory.push(memento);
    console.log(
      `${this.apprenticeName} 保存了第 ${this.mementoHistory.length} 個備忘錄泡泡`
    );
  }

  // 獲取最近的備忘錄
  getLastMemento() {
    if (this.mementoHistory.length === 0) {
      console.log("沒有可用的備忘錄泡泡！");
      return null;
    }
    const memento = this.mementoHistory[this.mementoHistory.length - 1];
    console.log(`${this.apprenticeName} 取出最近的備忘錄泡泡`);
    return memento;
  }

  // 獲取指定索引的備忘錄
  getMementoAt(index) {
    if (index < 0 || index >= this.mementoHistory.length) {
      console.log("無效的備忘錄索引！");
      return null;
    }
    console.log(`${this.apprenticeName} 取出第 ${index + 1} 個備忘錄泡泡`);
    return this.mementoHistory[index];
  }

  // 顯示所有備忘錄歷史
  showMementoHistory() {
    console.log(`=== ${this.apprenticeName} 的備忘錄歷史 ===`);
    if (this.mementoHistory.length === 0) {
      console.log("目前沒有保存任何備忘錄");
      return;
    }

    this.mementoHistory.forEach((memento, index) => {
      console.log(`${index + 1}. ${memento.toString()}`);
    });
  }

  // 清除所有備忘錄
  clearHistory() {
    this.mementoHistory = [];
    console.log(`${this.apprenticeName} 清除了所有備忘錄泡泡`);
  }

  getMementoCount() {
    return this.mementoHistory.length;
  }
}

// 使用範例
console.log("=== 歡迎來到魔法研究學院 ===");

// 創建角色
const wizard = new YoungWizard();
const apprentice = new MagicApprentice("學徒艾莉絲");

// 顯示初始狀態
wizard.showCurrentState();
console.log("");

// 第一個安全點：保存初始狀態
const checkpoint1 = wizard.createMemento();
apprentice.saveMemento(checkpoint1);
console.log("");

// 開始實驗：變形術
wizard.performExperiment("貓形", "初級變形");
wizard.consumeMana(10);
console.log("");

// 第二個安全點：保存中間狀態
const checkpoint2 = wizard.createMemento();
apprentice.saveMemento(checkpoint2);
console.log("");

// 繼續實驗：高級變形
wizard.performExperiment("龍形", "高級變形");
wizard.consumeMana(30);
console.log("");

// 實驗出錯！法師變成了青蛙
wizard.performExperiment("青蛙", "實驗失敗");
wizard.consumeMana(20);
console.log("糟糕！實驗失敗了！");
wizard.showCurrentState();
console.log("");

// 顯示備忘錄歷史
apprentice.showMementoHistory();
console.log("");

// 恢復到第二個安全點（龍形之前）
console.log("=== 恢復到第二個安全點 ===");
const restorePoint = apprentice.getMementoAt(1);
wizard.restoreFromMemento(restorePoint);
wizard.showCurrentState();
console.log("");

// 或者恢復到最初的安全點
console.log("=== 恢復到初始狀態 ===");
const initialState = apprentice.getMementoAt(0);
wizard.restoreFromMemento(initialState);
wizard.showCurrentState();

/** output
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
1. 魔法狀態[魔力:100, 形態:人形, 階段:準備階段, 時間:1759838956934]
2. 魔法狀態[魔力:70, 形態:貓形, 階段:初級變形, 時間:1759838956934]

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
```

## 備忘錄模式的三大角色

1. **Originator（原發器）**

   - 負責創建備忘錄，記錄自己的內部狀態
   - 可以使用備忘錄來恢復自己的狀態
   - 在範例中：`YoungWizard` 年輕法師就是原發器

2. **Memento（備忘錄）**

   - 儲存原發器的內部狀態
   - 防止原發器以外的物件訪問備忘錄
   - 在範例中：`MagicState` 魔法狀態就是備忘錄

3. **Caretaker（管理者/負責人）**
   - 負責保管備忘錄
   - 不能檢查或操作備忘錄的內容
   - 在範例中：`MagicApprentice` 魔法學徒就是管理者

## 小總結

Memento 設計模式就像我們故事中的時光回溯書記官，透過`備忘錄物件`來保存和恢復物件的狀態

**核心特點：**

- **狀態封裝**：將物件的內部狀態保存在獨立的備忘錄物件中
- **不破壞封裝性**：備忘錄只對原發器開放內部狀態訪問權限
- **狀態恢復**：可以將物件恢復到之前保存的任意狀態
- **歷史管理**：由管理者統一保管多個備忘錄

**使用時機：**

- 需要實現撤銷/重做功能（ex: 文字編輯器的 Ctrl+Z）
- 需要保存物件的檢查點狀態（ex: 遊戲存檔系統）
- 在危險操作前保存狀態（ex: 系統還原點）
- 需要實現狀態歷史記錄（ex: 版本控制系統）

<!-- **注意事項：**

- 備忘錄物件可能佔用大量記憶體，需要考慮儲存成本
- 管理者需要負責備忘錄的生命週期管理
- 原發器的內部結構變化可能影響舊備忘錄的有效性
- 適合用於狀態相對穩定且需要回溯的場景 -->
