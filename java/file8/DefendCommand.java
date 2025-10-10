package file8;

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