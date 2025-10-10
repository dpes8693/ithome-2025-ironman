package file8;

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