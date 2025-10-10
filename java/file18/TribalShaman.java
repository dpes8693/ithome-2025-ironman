package file18;

// 部落符文戰薩
import java.util.*;

public class TribalShaman {
    private String name;
    private RuneContext context;
    
    public TribalShaman(String name) {
        this.name = name;
        this.context = new RuneContext();
    }
    
    // 解譯戰歌並施展魔法
    public void chantWarSong(RuneExpression warSong) {
        System.out.println("═".repeat(60));
        System.out.println("🔥 符文戰薩 " + name + " 開始吟唱古老戰歌...");
        System.out.println("═".repeat(60));
        
        context.clearEffects();
        String magicEffect = warSong.interpret(context);
        
        System.out.println(magicEffect);
        
        displayBattleStatus();
    }
    
    // 顯示當前戰鬥狀態
    private void displayBattleStatus() {
        System.out.println("\n📊 當前戰鬥狀態：");
        System.out.println("威力等級：" + context.getPowerLevel());
        
        List<String> effects = context.getBattleEffects();
        if (!effects.isEmpty()) {
            System.out.println("生效增益：");
            for (String effect : effects) {
                System.out.println("  • " + effect);
            }
        }
        
        System.out.println("─".repeat(60));
        System.out.println();
    }
    
    // 創建元素符文
    public static ElementRuneExpression createElementRune(String element, int intensity) {
        return new ElementRuneExpression(element, intensity);
    }
    
    // 創建戰吼符文
    public static WarCryExpression createWarCryRune(String cryType, int duration) {
        return new WarCryExpression(cryType, duration);
    }
    
    // 創建複合戰歌
    public static CompositeRuneExpression createCompositeWarSong(String name) {
        return new CompositeRuneExpression(name);
    }
}
