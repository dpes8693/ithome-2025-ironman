# Day9 命令模式 (Command Pattern)

## 擬人化角色：【忠實的傳令官】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/8-Command.png)

- 種族： 人類
- 外貌： 一位身穿重甲的騎士，單膝跪地，雙手捧著一份發光的魔法卷軸。卷軸上刻畫著各種指令符號，周圍漂浮著多個代表不同行動（攻擊、防禦、施法、建造）的全息影像。在背景中，一位國王和幾位謀士正在靜靜地觀看。
- 性格： 嚴謹、忠誠、執行力強。他從不直接干預或執行命令，而是將所有請求都「包裝」成一個獨立的、可執行的指令物件。
- 能力： 將各種請求（命令 Command）封裝成一個物件。無論是國王下令攻擊敵軍、法師請求施放治療術，還是工匠要求建造城牆，所有的這些「動作」都被傳令官轉化為一個獨立的魔法卷軸（命令物件）。這個卷軸包含了執行動作所需的所有資訊，但不直接執行，而是等待被接收者「開啟」並執行。
- 代表語： 「陛下之令，已銘刻於此，只待執行。」
- 背景故事： 在一個戰火頻繁的王國裡，這位傳令官是國王與各路將領、法師、工匠之間的關鍵橋樑。由於戰場形勢瞬息萬變，國王的指令必須被精確且無誤地傳達。傳令官不會直接對士兵說「去攻擊」，而是將「攻擊」這個行為連同其目標、策略等資訊，全部記錄在一個符文卷軸中。當卷軸交到將領手中時，將領便能依據卷軸的內容，召集部隊並執行攻擊。這確保了命令的清晰、可追溯，且能靈活地進行撤銷或重做。

---

## 範例

### Java

```java
//Command.java
// 命令介面
public interface Command {
    void execute();
    void undo();
    String getDescription();
}
```

```java
//AttackCommand.java
// 攻擊命令
public class AttackCommand implements Command {
    private Army army;
    private String target;
    private boolean executed = false;

    public AttackCommand(Army army, String target) {
        this.army = army;
        this.target = target;
    }

    @Override
    public void execute() {
        System.out.println("傳令官展開卷軸：攻擊命令");
        army.attack(target);
        executed = true;
    }

    @Override
    public void undo() {
        if (executed) {
            System.out.println("傳令官展開撤回卷軸：停止攻擊");
            army.retreat();
            executed = false;
        }
    }

    @Override
    public String getDescription() {
        return "攻擊目標: " + target;
    }
}
```

```java
//DefendCommand.java
// 防禦命令
public class DefendCommand implements Command {
    private Army army;
    private String position;
    private boolean executed = false;

    public DefendCommand(Army army, String position) {
        this.army = army;
        this.position = position;
    }

    @Override
    public void execute() {
        System.out.println("傳令官展開卷軸：防禦命令");
        army.defend(position);
        executed = true;
    }

    @Override
    public void undo() {
        if (executed) {
            System.out.println("傳令官展開撤回卷軸：解除防禦");
            army.stopDefending();
            executed = false;
        }
    }

    @Override
    public String getDescription() {
        return "防禦位置: " + position;
    }
}
```

```java
//BuildCommand.java
// 建造命令
public class BuildCommand implements Command {
    private Engineer engineer;
    private String structure;
    private boolean executed = false;

    public BuildCommand(Engineer engineer, String structure) {
        this.engineer = engineer;
        this.structure = structure;
    }

    @Override
    public void execute() {
        System.out.println("傳令官展開卷軸：建造命令");
        engineer.build(structure);
        executed = true;
    }

    @Override
    public void undo() {
        if (executed) {
            System.out.println("傳令官展開撤回卷軸：拆除建築");
            engineer.demolish(structure);
            executed = false;
        }
    }

    @Override
    public String getDescription() {
        return "建造結構: " + structure;
    }
}
```

```java
//Army.java
// 軍隊接收者
public class Army {
    private String name;

    public Army(String name) {
        this.name = name;
    }

    public void attack(String target) {
        System.out.println(name + " 向 " + target + " 發起攻擊！");
    }

    public void defend(String position) {
        System.out.println(name + " 在 " + position + " 建立防線！");
    }

    public void retreat() {
        System.out.println(name + " 停止攻擊，撤回原位！");
    }

    public void stopDefending() {
        System.out.println(name + " 解除防禦狀態！");
    }
}
```

```java
//Engineer.java
// 工程師接收者
public class Engineer {
    private String name;

    public Engineer(String name) {
        this.name = name;
    }

    public void build(String structure) {
        System.out.println(name + " 開始建造 " + structure + "！");
    }

    public void demolish(String structure) {
        System.out.println(name + " 拆除了 " + structure + "！");
    }
}
```

```java
//CommandInvoker.java
// 傳令官 (調用者)
public class CommandInvoker {
    private java.util.List<Command> commandHistory = new java.util.ArrayList<>();
    private java.util.Stack<Command> undoStack = new java.util.Stack<>();

    public void executeCommand(Command command) {
        System.out.println("陛下之令，已銘刻於此，只待執行。");
        System.out.println("命令內容: " + command.getDescription());

        command.execute();
        commandHistory.add(command);
        undoStack.push(command);

        System.out.println("命令已執行完畢！\n");
    }

    public void undoLastCommand() {
        if (!undoStack.isEmpty()) {
            Command lastCommand = undoStack.pop();
            System.out.println("撤回上一道命令: " + lastCommand.getDescription());
            lastCommand.undo();
            System.out.println();
        } else {
            System.out.println("沒有可撤回的命令！\n");
        }
    }

    public void showCommandHistory() {
        System.out.println("=== 命令歷史記錄 ===");
        if (commandHistory.isEmpty()) {
            System.out.println("暫無命令記錄");
        } else {
            for (int i = 0; i < commandHistory.size(); i++) {
                System.out.println((i + 1) + ". " + commandHistory.get(i).getDescription());
            }
        }
        System.out.println();
    }
}
```

```java
//CommandPatternExample.java
// 使用範例
public class CommandPatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到王國的戰爭室 ===\n");

        // 創建接收者
        Army royalArmy = new Army("皇家軍隊");
        Engineer masterBuilder = new Engineer("首席工程師");

        // 創建傳令官
        CommandInvoker messenger = new CommandInvoker();

        // 創建各種命令
        Command attackEnemy = new AttackCommand(royalArmy, "敵方城堡");
        Command defendCastle = new DefendCommand(royalArmy, "王城大門");
        Command buildWall = new BuildCommand(masterBuilder, "城牆");

        System.out.println("國王開始下達命令：\n");

        // 執行攻擊命令
        messenger.executeCommand(attackEnemy);

        // 執行防禦命令
        messenger.executeCommand(defendCastle);

        // 執行建造命令
        messenger.executeCommand(buildWall);

        // 顯示命令歷史
        messenger.showCommandHistory();

        System.out.println(">國王改變心意，開始撤回命令：\n");

        // 撤回最後的命令
        messenger.undoLastCommand();
        messenger.undoLastCommand();
        messenger.undoLastCommand();

        // 再次嘗試撤回（應該沒有命令可撤回）
        messenger.undoLastCommand();

        /**output
        === 歡迎來到王國的戰爭室 ===

        國王開始下達命令：

        陛下之令，已銘刻於此，只待執行。
        命令內容: 攻擊目標: 敵方城堡
        傳令官展開卷軸：攻擊命令
        皇家軍隊 向 敵方城堡 發起攻擊！
        命令已執行完畢！

        陛下之令，已銘刻於此，只待執行。
        命令內容: 防禦位置: 王城大門
        傳令官展開卷軸：防禦命令
        皇家軍隊 在 王城大門 建立防線！
        命令已執行完畢！

        陛下之令，已銘刻於此，只待執行。
        命令內容: 建造結構: 城牆
        傳令官展開卷軸：建造命令
        首席工程師 開始建造 城牆！
        命令已執行完畢！

        === 命令歷史記錄 ===
        1. 攻擊目標: 敵方城堡
        2. 防禦位置: 王城大門
        3. 建造結構: 城牆

        >國王改變心意，開始撤回命令：

        撤回上一道命令: 建造結構: 城牆
        傳令官展開撤回卷軸：拆除建築
        首席工程師 拆除了 城牆！

        撤回上一道命令: 防禦位置: 王城大門
        傳令官展開撤回卷軸：解除防禦
        皇家軍隊 解除防禦狀態！

        撤回上一道命令: 攻擊目標: 敵方城堡
        傳令官展開撤回卷軸：停止攻擊
        皇家軍隊 停止攻擊，撤回原位！

        沒有可撤回的命令！
        */
    }
}
```

### JavaScript

```javascript
// 命令基類
class Command {
  execute() {
    throw new Error("子類必須實現 execute 方法");
  }

  undo() {
    throw new Error("子類必須實現 undo 方法");
  }

  getDescription() {
    throw new Error("子類必須實現 getDescription 方法");
  }
}

// 攻擊命令
class AttackCommand extends Command {
  constructor(army, target) {
    super();
    this.army = army;
    this.target = target;
    this.executed = false;
  }

  execute() {
    console.log("傳令官展開卷軸：攻擊命令");
    this.army.attack(this.target);
    this.executed = true;
  }

  undo() {
    if (this.executed) {
      console.log("傳令官展開撤回卷軸：停止攻擊");
      this.army.retreat();
      this.executed = false;
    }
  }

  getDescription() {
    return `攻擊目標: ${this.target}`;
  }
}

// 防禦命令
class DefendCommand extends Command {
  constructor(army, position) {
    super();
    this.army = army;
    this.position = position;
    this.executed = false;
  }

  execute() {
    console.log("傳令官展開卷軸：防禦命令");
    this.army.defend(this.position);
    this.executed = true;
  }

  undo() {
    if (this.executed) {
      console.log("傳令官展開撤回卷軸：解除防禦");
      this.army.stopDefending();
      this.executed = false;
    }
  }

  getDescription() {
    return `防禦位置: ${this.position}`;
  }
}

// 建造命令
class BuildCommand extends Command {
  constructor(engineer, structure) {
    super();
    this.engineer = engineer;
    this.structure = structure;
    this.executed = false;
  }

  execute() {
    console.log("傳令官展開卷軸：建造命令");
    this.engineer.build(this.structure);
    this.executed = true;
  }

  undo() {
    if (this.executed) {
      console.log("傳令官展開撤回卷軸：拆除建築");
      this.engineer.demolish(this.structure);
      this.executed = false;
    }
  }

  getDescription() {
    return `建造結構: ${this.structure}`;
  }
}

// 軍隊接收者
class Army {
  constructor(name) {
    this.name = name;
  }

  attack(target) {
    console.log(`${this.name} 向 ${target} 發起攻擊！`);
  }

  defend(position) {
    console.log(`${this.name} 在 ${position} 建立防線！`);
  }

  retreat() {
    console.log(`${this.name} 停止攻擊，撤回原位！`);
  }

  stopDefending() {
    console.log(`${this.name} 解除防禦狀態！`);
  }
}

// 工程師接收者
class Engineer {
  constructor(name) {
    this.name = name;
  }

  build(structure) {
    console.log(`${this.name} 開始建造 ${structure}！`);
  }

  demolish(structure) {
    console.log(`${this.name} 拆除了 ${structure}！`);
  }
}

// 傳令官 (調用者)
class CommandInvoker {
  constructor() {
    this.commandHistory = [];
    this.undoStack = [];
  }

  executeCommand(command) {
    console.log("陛下之令，已銘刻於此，只待執行。");
    console.log(`命令內容: ${command.getDescription()}`);

    command.execute();
    this.commandHistory.push(command);
    this.undoStack.push(command);

    console.log("命令已執行完畢！\n");
  }

  undoLastCommand() {
    if (this.undoStack.length > 0) {
      const lastCommand = this.undoStack.pop();
      console.log(`撤回上一道命令: ${lastCommand.getDescription()}`);
      lastCommand.undo();
      console.log();
    } else {
      console.log("沒有可撤回的命令！\n");
    }
  }

  showCommandHistory() {
    console.log("=== 命令歷史記錄 ===");
    if (this.commandHistory.length === 0) {
      console.log("暫無命令記錄");
    } else {
      this.commandHistory.forEach((command, index) => {
        console.log(`${index + 1}. ${command.getDescription()}`);
      });
    }
    console.log();
  }
}

// 使用範例
console.log("=== 歡迎來到王國的戰爭室 ===\n");

// 創建接收者
const royalArmy = new Army("皇家軍隊");
const masterBuilder = new Engineer("首席工程師");

// 創建傳令官
const messenger = new CommandInvoker();

// 創建各種命令
const attackEnemy = new AttackCommand(royalArmy, "敵方城堡");
const defendCastle = new DefendCommand(royalArmy, "王城大門");
const buildWall = new BuildCommand(masterBuilder, "城牆");

console.log("國王開始下達命令：\n");

// 執行攻擊命令
messenger.executeCommand(attackEnemy);

// 執行防禦命令
messenger.executeCommand(defendCastle);

// 執行建造命令
messenger.executeCommand(buildWall);

// 顯示命令歷史
messenger.showCommandHistory();

console.log(">國王改變心意，開始撤回命令：\n");

// 撤回最後的命令
messenger.undoLastCommand();
messenger.undoLastCommand();
messenger.undoLastCommand();

// 再次嘗試撤回（應該沒有命令可撤回）
messenger.undoLastCommand();

/** output
=== 歡迎來到王國的戰爭室 ===

國王開始下達命令：

陛下之令，已銘刻於此，只待執行。
命令內容: 攻擊目標: 敵方城堡
傳令官展開卷軸：攻擊命令
皇家軍隊 向 敵方城堡 發起攻擊！
命令已執行完畢！

陛下之令，已銘刻於此，只待執行。
命令內容: 防禦位置: 王城大門
傳令官展開卷軸：防禦命令
皇家軍隊 在 王城大門 建立防線！
命令已執行完畢！

陛下之令，已銘刻於此，只待執行。
命令內容: 建造結構: 城牆
傳令官展開卷軸：建造命令
首席工程師 開始建造 城牆！
命令已執行完畢！

=== 命令歷史記錄 ===
1. 攻擊目標: 敵方城堡
2. 防禦位置: 王城大門
3. 建造結構: 城牆

>國王改變心意，開始撤回命令：

撤回上一道命令: 建造結構: 城牆
傳令官展開撤回卷軸：拆除建築
首席工程師 拆除了 城牆！

撤回上一道命令: 防禦位置: 王城大門
傳令官展開撤回卷軸：解除防禦
皇家軍隊 解除防禦狀態！

撤回上一道命令: 攻擊目標: 敵方城堡
傳令官展開撤回卷軸：停止攻擊
皇家軍隊 停止攻擊，撤回原位！

沒有可撤回的命令！
 */
```

## 小總結

Command Pattern 設計模式就像我們故事中的忠實傳令官，將`請求封裝成物件`，讓發送者與接收者解耦合

**核心特點：**

- **請求封裝**：將請求（方法調用）封裝成獨立的命令物件
- **發送者解耦**：調用者不需要知道接收者的具體實現
- **支援撤銷**：命令物件可以記錄狀態，實現 undo 功能
- **可排隊執行**：命令可以被儲存、排隊或延遲執行
- **支援巨集命令**：可以組合多個命令成為複合命令

**使用時機：**

- 需要將請求排隊、記錄或支援撤銷操作（ex: 文字編輯器的復原功能）
- 想要將調用操作的物件與執行操作的物件解耦（ex: GUI 按鈕與實際動作）
- 需要在不同時間指定、排列和執行請求（ex: 批次處理、延遲執行）
