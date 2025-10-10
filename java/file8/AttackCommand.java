package file8;

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