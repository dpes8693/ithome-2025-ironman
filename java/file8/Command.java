package file8;

// 命令介面
public interface Command {
    void execute();
    void undo();
    String getDescription();
}