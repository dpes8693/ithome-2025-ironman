package file8;

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