package file8;

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
    }
}