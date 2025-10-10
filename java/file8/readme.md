
```java
package file8;
//Command.java
// 命令介面
public interface Command {
    void execute();
    void undo();
    String getDescription();
}
```

```java
package file8;
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
package file8;
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
package file8;
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
package file8;
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
package file8;
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
package file8;
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
package file8;
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

        System.out.println("國王改變心意，開始撤回命令：\n");

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

        國王改變心意，開始撤回命令：

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