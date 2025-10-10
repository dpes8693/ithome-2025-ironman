package file18;

// 符文表達式抽象類別
public abstract class RuneExpression {
    // 解譯符文表達式的抽象方法
    public abstract String interpret(RuneContext context);
    
    // 獲取符文類型
    public abstract String getRuneType();
}
