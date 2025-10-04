# Day19 解譯器模式 (Interpreter Pattern)

## 擬人化角色：【部落符文戰薩】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/18-Interpreter.png)

- 種族： 獸人
- 外貌： 一位身穿厚重獸皮和鋼鐵護甲的強壯獸人戰薩，臉上有部落的戰紋，頭上綁著髮辮。他一手握著一根頂端鑲嵌著紅色水晶、燃燒著火焰的圖騰法杖，另一手則凝聚著不穩定的元素能量球。在他周圍，漂浮著幾張發光的羊皮卷軸，上面顯示著獸人古老的符文語法和戰歌結構，還有一張擺在桌上的原始符文圖譜。他身處一個充滿篝火和粗獷圖騰的部落營地或洞穴中。
- 性格： 狂野、堅韌、對部落古老傳統和符文知識有著深刻的理解。他能夠將部落古老的戰歌和符文語言解析成實際的元素魔法和戰鬥增益。
- 能力： 定義一個語言與其文法，使用一個解譯器來表示這個語言的敘述。這位戰薩（解譯器）能夠理解並「解譯」獸人部落的古老戰歌和符文語言。當他吟唱一段戰歌時，他會根據戰歌的「文法」和「符文序列」，將其解析成對應的元素魔法效果（如召喚火焰、強化族人力量、恐懼敵人）。他能將抽象的音節和符文，轉化為部落戰士可直接感受到的戰鬥增益或對敵人的詛咒。
- 代表語： 「古老的戰歌，將化為實體的怒火！」
- 背景故事： 在廣袤的荒原部落中，這位符文戰薩是部落與元素溝通的橋樑，也是古老獸人知識的守護者。獸人部落的戰歌不只是一種咆哮，更是一種蘊含元素之力的「語言」。當戰薩在儀式或戰鬥中吟唱一段特定的戰歌時，他會依據戰歌中的「符文語法」（如哪種音節代表火、哪種音節代表力量）來「解譯」其意義。這段戰歌可能被解譯為「召喚怒火，提升戰鬥力」，然後他就會釋放出灼熱的火焰，並賦予周圍戰士勇氣。他的存在使得部落能夠利用祖先傳承的符文語言，直接與大地和元素溝通，獲得強大的力量。

---

## 範例

### Java

```java
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
    威力等級：33
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
```

### JavaScript

```javascript
// 符文表達式抽象類別
class RuneExpression {
  // 解譯符文表達式的抽象方法
  interpret(context) {
    throw new Error("子類必須實現 interpret 方法");
  }

  // 獲取符文類型
  getRuneType() {
    throw new Error("子類必須實現 getRuneType 方法");
  }
}

// 符文上下文，存儲解譯過程中的資訊
class RuneContext {
  constructor() {
    this.variables = new Map();
    this.battleEffects = [];
    this.powerLevel = 0;
  }

  setVariable(key, value) {
    this.variables.set(key, value);
  }

  getVariable(key) {
    return this.variables.get(key) || "";
  }

  addBattleEffect(effect) {
    this.battleEffects.push(effect);
  }

  getBattleEffects() {
    return [...this.battleEffects];
  }

  increasePower(amount) {
    this.powerLevel += amount;
  }

  getPowerLevel() {
    return this.powerLevel;
  }

  clearEffects() {
    this.battleEffects = [];
    this.powerLevel = 0;
  }
}

// 元素符文表達式
class ElementRuneExpression extends RuneExpression {
  constructor(element, intensity) {
    super();
    this.element = element;
    this.intensity = intensity;
  }

  interpret(context) {
    let effect = "";

    switch (this.element.toLowerCase()) {
      case "fire":
      case "火":
        effect = `🔥 召喚烈焰之力 (強度: ${this.intensity})`;
        context.addBattleEffect(`火焰增傷 +${this.intensity * 10}%`);
        context.increasePower(this.intensity * 2);
        break;

      case "earth":
      case "土":
        effect = `🗿 凝聚大地之盾 (強度: ${this.intensity})`;
        context.addBattleEffect(`防禦力 +${this.intensity * 15}%`);
        context.increasePower(this.intensity);
        break;

      case "wind":
      case "風":
        effect = `💨 引導狂風之速 (強度: ${this.intensity})`;
        context.addBattleEffect(`移動速度 +${this.intensity * 20}%`);
        context.increasePower(this.intensity);
        break;

      case "water":
      case "水":
        effect = `💧 喚醒治癒之泉 (強度: ${this.intensity})`;
        context.addBattleEffect(`生命恢復 +${this.intensity * 5}/秒`);
        context.increasePower(this.intensity);
        break;

      default:
        effect = "❓ 未知的元素符文";
        break;
    }

    return effect;
  }

  getRuneType() {
    return "元素符文";
  }
}

// 戰吼符文表達式
class WarCryExpression extends RuneExpression {
  constructor(cryType, duration) {
    super();
    this.cryType = cryType;
    this.duration = duration;
  }

  interpret(context) {
    let effect = "";

    switch (this.cryType.toLowerCase()) {
      case "rage":
      case "怒吼":
        effect = `😡 發出狂暴怒吼 (持續: ${this.duration}秒)`;
        context.addBattleEffect(`攻擊力 +50%, 持續 ${this.duration}秒`);
        context.increasePower(3);
        break;

      case "fear":
      case "恐懼":
        effect = `😨 釋放恐懼戰吼 (持續: ${this.duration}秒)`;
        context.addBattleEffect(`敵人攻擊力 -30%, 持續 ${this.duration}秒`);
        context.increasePower(2);
        break;

      case "rally":
      case "集結":
        effect = `🛡️ 發出集結號令 (持續: ${this.duration}秒)`;
        context.addBattleEffect(`全隊防禦力 +40%, 持續 ${this.duration}秒`);
        context.increasePower(4);
        break;

      default:
        effect = "❓ 未知的戰吼符文";
        break;
    }

    return effect;
  }

  getRuneType() {
    return "戰吼符文";
  }
}

// 複合符文表達式（組合多個符文）
class CompositeRuneExpression extends RuneExpression {
  constructor(combinationName) {
    super();
    this.combinationName = combinationName;
    this.runeExpressions = [];
  }

  addRune(rune) {
    this.runeExpressions.push(rune);
  }

  interpret(context) {
    let result = `⚡ 啟動複合戰歌：【${this.combinationName}】\n`;

    const initialPower = context.getPowerLevel();

    for (const rune of this.runeExpressions) {
      const effect = rune.interpret(context);
      result += `  ➤ ${effect}\n`;
    }

    const powerGain = context.getPowerLevel() - initialPower;
    if (powerGain > 0) {
      // 複合符文有額外威力加成
      const bonus = Math.floor(powerGain / 2);
      context.increasePower(bonus);
      result += `  🌟 複合加成：額外威力 +${bonus}\n`;
    }

    return result;
  }

  getRuneType() {
    return "複合符文";
  }
}

// 部落符文戰薩
class TribalShaman {
  constructor(name) {
    this.name = name;
    this.context = new RuneContext();
  }

  // 解譯戰歌並施展魔法
  chantWarSong(warSong) {
    console.log("═".repeat(60));
    console.log(`🔥 符文戰薩 ${this.name} 開始吟唱古老戰歌...`);
    console.log("═".repeat(60));

    this.context.clearEffects();
    const magicEffect = warSong.interpret(this.context);

    console.log(magicEffect);

    this.displayBattleStatus();
  }

  // 顯示當前戰鬥狀態
  displayBattleStatus() {
    console.log("\n📊 當前戰鬥狀態：");
    console.log(`威力等級：${this.context.getPowerLevel()}`);

    const effects = this.context.getBattleEffects();
    if (effects.length > 0) {
      console.log("生效增益：");
      for (const effect of effects) {
        console.log(`  • ${effect}`);
      }
    }

    console.log("─".repeat(60));
    console.log("");
  }

  // 創建元素符文
  static createElementRune(element, intensity) {
    return new ElementRuneExpression(element, intensity);
  }

  // 創建戰吼符文
  static createWarCryRune(cryType, duration) {
    return new WarCryExpression(cryType, duration);
  }

  // 創建複合戰歌
  static createCompositeWarSong(name) {
    return new CompositeRuneExpression(name);
  }
}

// 使用範例
console.log("🏔️ 歡迎來到荒原部落的戰歌儀式 🏔️\n");

// 創建部落符文戰薩
const shaman = new TribalShaman("古魯什‧火拳");

// 測試1：基礎元素符文
console.log("【測試1：基礎元素符文】");
const fireRune = TribalShaman.createElementRune("fire", 3);
shaman.chantWarSong(fireRune);

// 測試2：戰吼符文
console.log("【測試2：戰吼符文】");
const rageRune = TribalShaman.createWarCryRune("rage", 10);
shaman.chantWarSong(rageRune);

// 測試3：複合戰歌 - 烈焰怒吼
console.log("【測試3：複合戰歌 - 烈焰狂戰士】");
const flameWarrior = TribalShaman.createCompositeWarSong("烈焰狂戰士");
flameWarrior.addRune(TribalShaman.createElementRune("fire", 4));
flameWarrior.addRune(TribalShaman.createWarCryRune("rage", 15));
flameWarrior.addRune(TribalShaman.createElementRune("earth", 2));
shaman.chantWarSong(flameWarrior);

// 測試4：複合戰歌 - 風暴守護者
console.log("【測試4：複合戰歌 - 風暴守護者】");
const stormGuardian = TribalShaman.createCompositeWarSong("風暴守護者");
stormGuardian.addRune(TribalShaman.createElementRune("wind", 3));
stormGuardian.addRune(TribalShaman.createElementRune("water", 3));
stormGuardian.addRune(TribalShaman.createWarCryRune("rally", 20));
shaman.chantWarSong(stormGuardian);

// 測試5：終極戰歌 - 部落之怒
console.log("【測試5：終極戰歌 - 部落之怒】");
const tribalFury = TribalShaman.createCompositeWarSong("部落之怒");
tribalFury.addRune(TribalShaman.createElementRune("fire", 5));
tribalFury.addRune(TribalShaman.createElementRune("earth", 4));
tribalFury.addRune(TribalShaman.createElementRune("wind", 3));
tribalFury.addRune(TribalShaman.createWarCryRune("rage", 25));
tribalFury.addRune(TribalShaman.createWarCryRune("fear", 15));
shaman.chantWarSong(tribalFury);

console.log("🌟 戰歌儀式完成！部落戰士們充滿了古老符文的力量！");

/** output
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
威力等級：33
生效增益：
  • 火焰增傷 +50% 
  • 防禦力 +60%
  • 移動速度 +60%
  • 攻擊力 +50%, 持續 25秒
  • 敵人攻擊力 -30%, 持續 15秒
────────────────────────────────────────────────────────────

🌟 戰歌儀式完成！部落戰士們充滿了古老符文的力量！

 */
```

### 威力等級

- fire 2
- earth 1
- wind 1
- water 1

- rage 3
- fear 2
- rally 4

## 小總結

Interpreter Pattern（解譯器模式）就像我們故事中的部落符文戰薩，定義一個語言的文法並建立解譯器來解釋該語言中的句子

**核心特點：**

- **語言定義**：為特定問題定義一個簡單的語言和文法
- **表達式層次**：使用組合模式建立抽象語法樹（AST）
- **遞迴解譯**：通過遞迴方式解譯複雜的表達式
- **上下文管理**：維護解譯過程中的狀態和變數

**主要組件：**

- **抽象表達式**：定義解譯操作的介面（`RuneExpression`）
- **終端表達式**：實現與文法中終端符號相關的解譯操作（`ElementRuneExpression`、`WarCryExpression`）
- **非終端表達式**：實現文法中非終端符號的解譯操作（`CompositeRuneExpression`）
- **上下文**：包含解譯器之外的一些全域信息（`RuneContext`）
- **客戶端**：建構抽象語法樹並調用解譯操作（`TribalShaman`）

**使用時機：**

- 需要解譯一個簡單的語言或表達式
- 文法相對簡單且穩定，不會頻繁變更
- 效率不是主要考量（解譯器模式通常效率較低）
- 需要將語言的文法表示為類別層次結構

**實際應用場景：**

- **正規表達式引擎**：解析和匹配正規表達式
- **SQL 解析器**：解譯 SQL 查詢語句
- **數學表達式計算器**：解譯和計算數學公式
- **配置檔解析**：解析特定格式的配置檔
- **腳本語言解譯器**：小型腳本語言的實現
- **模板引擎**：解析模板語法並生成內容

<!-- **優點：**

- 易於改變和擴展文法（添加新的表達式類別）
- 實現簡單文法相對容易
- 每個文法規則都可以表示為一個類別

**缺點：**

- 複雜文法難以維護（類別數量會迅速增加）
- 執行效率較低（大量的遞迴調用）
- 對於複雜語言，建議使用專門的解析器生成工具（如 ANTLR、Yacc） -->
