package file13;

// 法師狀態介面
public interface MageState {
    void cast();
    void defend();
    void move();
    String getFormName();
}