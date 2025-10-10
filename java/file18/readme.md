```java
package file18;
//RuneExpression.java
// 符文表達式抽象類別
public abstract class RuneExpression {
    // 解譯符文表達式的抽象方法
    public abstract String interpret(RuneContext context);
    
    // 獲取符文類型
    public abstract String getRuneType();
}
```

```java
package file18;
//RuneContext.java
// 符文上下文，存儲解譯過程中的資訊
import java.util.*;

public class RuneContext {
    private Map<String, String> variables;
    private List<String> battleEffects;
    private int powerLevel;
    
    public RuneContext() {
        this.variables = new HashMap<>();
        this.battleEffects = new ArrayList<>();
        this.powerLevel = 0;
    }
    
    public void setVariable(String key, String value) {
        variables.put(key, value);
    }
    
    public String getVariable(String key) {
        return variables.getOrDefault(key, "");
    }
    
    public void addBattleEffect(String effect) {
        battleEffects.add(effect);
    }
    
    public List<String> getBattleEffects() {
        return new ArrayList<>(battleEffects);
    }
    
    public void increasePower(int amount) {
        powerLevel += amount;
    }
    
    public int getPowerLevel() {
        return powerLevel;
    }
    
    public void clearEffects() {
        battleEffects.clear();
        powerLevel = 0;
    }
}
```

```java
package file18;
//ElementRuneExpression.java
// 元素符文表達式
public class ElementRuneExpression extends RuneExpression {
    private String element;
    private int intensity;
    
    public ElementRuneExpression(String element, int intensity) {
        this.element = element;
        this.intensity = intensity;
    }
    
    @Override
    public String interpret(RuneContext context) {
        String effect = "";
        
        switch (element.toLowerCase()) {
            case "fire":
            case "火":
                effect = String.format("🔥 召喚烈焰之力 (強度: %d)", intensity);
                context.addBattleEffect("火焰增傷 +" + (intensity * 10) + "%");
                context.increasePower(intensity * 2);
                break;
                
            case "earth":
            case "土":
                effect = String.format("🗿 凝聚大地之盾 (強度: %d)", intensity);
                context.addBattleEffect("防禦力 +" + (intensity * 15) + "%");
                context.increasePower(intensity);
                break;
                
            case "wind":
            case "風":
                effect = String.format("💨 引導狂風之速 (強度: %d)", intensity);
                context.addBattleEffect("移動速度 +" + (intensity * 20) + "%");
                context.increasePower(intensity);
                break;
                
            case "water":
            case "水":
                effect = String.format("💧 喚醒治癒之泉 (強度: %d)", intensity);
                context.addBattleEffect("生命恢復 +" + (intensity * 5) + "/秒");
                context.increasePower(intensity);
                break;
                
            default:
                effect = "❓ 未知的元素符文";
                break;
        }
        
        return effect;
    }
    
    @Override
    public String getRuneType() {
        return "元素符文";
    }
}
```

```java
package file18;
//WarCryExpression.java
// 戰吼符文表達式
public class WarCryExpression extends RuneExpression {
    private String cryType;
    private int duration;
    
    public WarCryExpression(String cryType, int duration) {
        this.cryType = cryType;
        this.duration = duration;
    }
    
    @Override
    public String interpret(RuneContext context) {
        String effect = "";
        
        switch (cryType.toLowerCase()) {
            case "rage":
            case "怒吼":
                effect = String.format("😡 發出狂暴怒吼 (持續: %d秒)", duration);
                context.addBattleEffect("攻擊力 +50%, 持續 " + duration + "秒");
                context.increasePower(3);
                break;
                
            case "fear":
            case "恐懼":
                effect = String.format("😨 釋放恐懼戰吼 (持續: %d秒)", duration);
                context.addBattleEffect("敵人攻擊力 -30%, 持續 " + duration + "秒");
                context.increasePower(2);
                break;
                
            case "rally":
            case "集結":
                effect = String.format("🛡️ 發出集結號令 (持續: %d秒)", duration);
                context.addBattleEffect("全隊防禦力 +40%, 持續 " + duration + "秒");
                context.increasePower(4);
                break;
                
            default:
                effect = "❓ 未知的戰吼符文";
                break;
        }
        
        return effect;
    }
    
    @Override
    public String getRuneType() {
        return "戰吼符文";
    }
}
```

```java
package file18;
//CompositeRuneExpression.java
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
```

```java
package file18;
//TribalShaman.java
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
```

```java
package file18;
//InterpreterPatternExample.java
// 使用範例
public class InterpreterPatternExample {
    public static void main(String[] args) {
        System.out.println("🏔️ 歡迎來到荒原部落的戰歌儀式 🏔️\n");
        
        // 創建部落符文戰薩
        TribalShaman shaman = new TribalShaman("古魯什‧火拳");
        
        // 測試1：基礎元素符文
        System.out.println("【測試1：基礎元素符文】");
        RuneExpression fireRune = TribalShaman.createElementRune("fire", 3);
        shaman.chantWarSong(fireRune);
        
        // 測試2：戰吼符文
        System.out.println("【測試2：戰吼符文】");
        RuneExpression rageRune = TribalShaman.createWarCryRune("rage", 10);
        shaman.chantWarSong(rageRune);
        
        // 測試3：複合戰歌 - 烈焰怒吼
        System.out.println("【測試3：複合戰歌 - 烈焰狂戰士】");
        CompositeRuneExpression flameWarrior = TribalShaman.createCompositeWarSong("烈焰狂戰士");
        flameWarrior.addRune(TribalShaman.createElementRune("fire", 4));
        flameWarrior.addRune(TribalShaman.createWarCryRune("rage", 15));
        flameWarrior.addRune(TribalShaman.createElementRune("earth", 2));
        shaman.chantWarSong(flameWarrior);
        
        // 測試4：複合戰歌 - 風暴守護者
        System.out.println("【測試4：複合戰歌 - 風暴守護者】");
        CompositeRuneExpression stormGuardian = TribalShaman.createCompositeWarSong("風暴守護者");
        stormGuardian.addRune(TribalShaman.createElementRune("wind", 3));
        stormGuardian.addRune(TribalShaman.createElementRune("water", 3));
        stormGuardian.addRune(TribalShaman.createWarCryRune("rally", 20));
        shaman.chantWarSong(stormGuardian);
        
        // 測試5：終極戰歌 - 部落之怒
        System.out.println("【測試5：終極戰歌 - 部落之怒】");
        CompositeRuneExpression tribalFury = TribalShaman.createCompositeWarSong("部落之怒");
        tribalFury.addRune(TribalShaman.createElementRune("fire", 5));
        tribalFury.addRune(TribalShaman.createElementRune("earth", 4));
        tribalFury.addRune(TribalShaman.createElementRune("wind", 3));
        tribalFury.addRune(TribalShaman.createWarCryRune("rage", 25));
        tribalFury.addRune(TribalShaman.createWarCryRune("fear", 15));
        shaman.chantWarSong(tribalFury);
        
        System.out.println("🌟 戰歌儀式完成！部落戰士們充滿了古老符文的力量！");
    }

    /**output
    🏔️ 歡迎來到荒原部落的戰歌儀式 🏔️

    【測試1：基礎元素符文】
    ════════════════════════════════════════════════════════════
    🔥 符文戰薩 古魯什‧火拳 開始吟唱古老戰歌...
    ════════════════════════════════════════════════════════════
    🔥 召喚烈焰之力 (強度: 3)

    📊 當前戰鬥狀態：
    威力等級：6
    生效增益：
      • 火焰增傷 +30%
    ────────────────────────────────────────────────────────────

    【測試2：戰吼符文】
    ════════════════════════════════════════════════════════════
    🔥 符文戰薩 古魯什‧火拳 開始吟唱古老戰歌...
    ════════════════════════════════════════════════════════════
    😡 發出狂暴怒吼 (持續: 10秒)

    📊 當前戰鬥狀態：
    威力等級：3
    生效增益：
      • 攻擊力 +50%, 持續 10秒
    ────────────────────────────────────────────────────────────

    【測試3：複合戰歌 - 烈焰狂戰士】
    ════════════════════════════════════════════════════════════
    🔥 符文戰薩 古魯什‧火拳 開始吟唱古老戰歌...
    ════════════════════════════════════════════════════════════
    ⚡ 啟動複合戰歌：【烈焰狂戰士】
      ➤ 🔥 召喚烈焰之力 (強度: 4)
      ➤ 😡 發出狂暴怒吼 (持續: 15秒)
      ➤ 🗿 凝聚大地之盾 (強度: 2)
      🌟 複合加成：額外威力 +6

    📊 當前戰鬥狀態：
    威力等級：19
    生效增益：
      • 火焰增傷 +40%
      • 攻擊力 +50%, 持續 15秒
      • 防禦力 +30%
    ────────────────────────────────────────────────────────────

    【測試4：複合戰歌 - 風暴守護者】
    ════════════════════════════════════════════════════════════
    🔥 符文戰薩 古魯什‧火拳 開始吟唱古老戰歌...
    ════════════════════════════════════════════════════════════
    ⚡ 啟動複合戰歌：【風暴守護者】
      ➤ 💨 引導狂風之速 (強度: 3)
      ➤ 💧 喚醒治癒之泉 (強度: 3)
      ➤ 🛡️ 發出集結號令 (持續: 20秒)
      🌟 複合加成：額外威力 +5

    📊 當前戰鬥狀態：
    威力等級：15
    生效增益：
      • 移動速度 +60%
      • 生命恢復 +15/秒
      • 全隊防禦力 +40%, 持續 20秒
    ────────────────────────────────────────────────────────────

    【測試5：終極戰歌 - 部落之怒】
    ════════════════════════════════════════════════════════════
    🔥 符文戰薩 古魯什‧火拳 開始吟唱古老戰歌...
    ════════════════════════════════════════════════════════════
    ⚡ 啟動複合戰歌：【部落之怒】
      ➤ 🔥 召喚烈焰之力 (強度: 5)
      ➤ 🗿 凝聚大地之盾 (強度: 4)
      ➤ 💨 引導狂風之速 (強度: 3)
      ➤ 😡 發出狂暴怒吼 (持續: 25秒)
      ➤ 😨 釋放恐懼戰吼 (持續: 15秒)
      🌟 複合加成：額外威力 +11

    📊 當前戰鬥狀態：
    威力等級：34
    生效增益：
      • 火焰增傷 +50%
      • 防禦力 +60%
      • 移動速度 +60%
      • 攻擊力 +50%, 持續 25秒
      • 敵人攻擊力 -30%, 持續 15秒
    ────────────────────────────────────────────────────────────

    🌟 戰歌儀式完成！部落戰士們充滿了古老符文的力量！

    */
}