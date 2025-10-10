package file18;

// 複合符文表達式（組合多個符文）
import java.util.*;

public class CompositeRuneExpression extends RuneExpression {
    private List<RuneExpression> runeExpressions;
    private String combonationName;
    
    public CompositeRuneExpression(String combonationName) {
        this.combonationName = combonationName;
        this.runeExpressions = new ArrayList<>();
    }
    
    public void addRune(RuneExpression rune) {
        runeExpressions.add(rune);
    }
    
    @Override
    public String interpret(RuneContext context) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("⚡ 啟動複合戰歌：【%s】\n", combonationName));
        
        int initialPower = context.getPowerLevel();
        
        for (RuneExpression rune : runeExpressions) {
            String effect = rune.interpret(context);
            result.append("  ➤ ").append(effect).append("\n");
        }
        
        int powerGain = context.getPowerLevel() - initialPower;
        if (powerGain > 0) {
            // 複合符文有額外威力加成
            int bonus = powerGain / 2;
            context.increasePower(bonus);
            result.append(String.format("  🌟 複合加成：額外威力 +%d\n", bonus));
        }
        
        return result.toString();
    }
    
    @Override
    public String getRuneType() {
        return "複合符文";
    }
}
