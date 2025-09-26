# Day12 樣版模式 (Template Pattern)

## 擬人化角色：【聖典的首席編纂天使】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/11-Template.png)

- 種族： 天使
- 外貌： 一位身著潔白金邊長袍、背生巨大潔白羽翼的女性天使。她頭戴光環，手持一根閃耀著金色光芒的羽毛筆，站在一個充滿神聖光芒和無數古老卷軸的宏偉聖殿圖書館中央。她面前的祭壇上，一本巨大的聖典翻開著，一本寫有「神聖儀式：一步一步指南」的卷軸懸浮在她手中，周圍還有幾份細節不同的儀式卷軸漂浮著。
- 性格： 莊嚴、有條理、注重秩序與指引。她負責定義所有神聖儀式的基本框架和流程，但允許不同階層的天使或信徒根據具體情況來填補細節。
- 能力： 使用抽象類別定義一套演算法的架構，但是細節可延遲到子類別決定。這位首席編纂天使（樣版）撰寫了一份「神聖儀式通用指南」。這份指南明確規定了所有神聖儀式都必須遵循的核心步驟（如：開啟聖門、引導神力、賜福、關閉聖門）。然而，對於「引導何種神力」、「賜福的具體對象」等細節，則會留給不同的天使階層（例如治癒天使或戰鬥天使，作為子類別）根據他們各自的職責和力量來填充。
- 代表語： 「神聖的法則不可更改，但其彰顯的方式有萬千。」
- 背景故事： 在天界宏偉的圖書館中，這位首席編纂天使負責維護所有神聖儀式的完整性和規範性。她所編纂的「聖典」包含了所有儀式的「黃金標準」，確定了儀式進行的宏觀步驟。例如，所有治癒儀式都必須以「祈禱開啟」和「神聖光芒注入」為核心，但治癒天使可以自行決定使用何種咒語和聖物來實施光芒注入。同樣，戰鬥天使在執行戰鬥儀式時，也必須遵循「召喚聖盾」和「淨化邪惡」的基本流程，但可以選擇不同的聖盾形態和淨化方式。她確保了所有神聖行為都符合天界的秩序，同時也給予了執行者一定的靈活性去適應不同的情境。

---

## 範例

### Java

```java
//DivineCeremony.java
// 神聖儀式抽象類別（樣版類別）
public abstract class DivineCeremony {

    // 樣版方法：定義神聖儀式的完整流程
    public final void performCeremony() {
        System.out.println("=== 開始執行神聖儀式 ===");
        System.out.println("首席編纂天使：神聖的法則不可更改，但其彰顯的方式有萬千。\n");

        // 固定步驟：開啟聖門
        openSacredGate();

        // 抽象步驟：準備儀式（由子類實現）
        prepareRitual();

        // 抽象步驟：引導神力（由子類實現）
        channelDivinePower();

        // 鉤子方法：是否需要特殊聖物
        if (needsSpecialArtifact()) {
            useSpecialArtifact();
        }

        // 抽象步驟：賜予祝福（由子類實現）
        grantBlessing();

        // 固定步驟：感謝諸神
        offerGratitude();

        // 固定步驟：關閉聖門
        closeSacredGate();

        System.out.println("\n=== 神聖儀式完成 ===\n");
    }

    // 固定步驟：所有儀式都相同的開場
    private void openSacredGate() {
        System.out.println("🕊️ 開啟聖門：神聖之光照耀大地...");
        System.out.println("   天使之歌響徹天空，聖潔的力量開始聚集");
    }

    // 固定步驟：所有儀式都相同的感謝環節
    private void offerGratitude() {
        System.out.println("🙏 感謝諸神：向至高無上的神明致敬");
        System.out.println("   諸天使共同吟唱讚美詩");
    }

    // 固定步驟：所有儀式都相同的結尾
    private void closeSacredGate() {
        System.out.println("✨ 關閉聖門：神聖之力緩緩散去，回歸天界");
        System.out.println("   聖潔光芒淡去，儀式圓滿結束");
    }

    // 抽象方法：準備儀式（必須由子類實現）
    protected abstract void prepareRitual();

    // 抽象方法：引導神力（必須由子類實現）
    protected abstract void channelDivinePower();

    // 抽象方法：賜予祝福（必須由子類實現）
    protected abstract void grantBlessing();

    // 鉤子方法：是否需要特殊聖物（子類可覆寫）
    protected boolean needsSpecialArtifact() {
        return false; // 預設不需要
    }

    // 鉤子方法：使用特殊聖物（子類可覆寫）
    protected void useSpecialArtifact() {
        System.out.println("📿 使用通用聖物：聖光水晶開始發光");
    }

    // 獲取儀式名稱（由子類實現）
    public abstract String getCeremonyName();
}
```

```java
//HealingCeremony.java
// 治癒儀式（具體實現類別）
public class HealingCeremony extends DivineCeremony {

    @Override
    protected void prepareRitual() {
        System.out.println("💊 治癒天使準備儀式：");
        System.out.println("   - 點燃治癒聖香，香氣瀰漫");
        System.out.println("   - 準備聖水和藥草");
        System.out.println("   - 治癒天使展開純白羽翼");
    }

    @Override
    protected void channelDivinePower() {
        System.out.println("🌟 引導治癒神力：");
        System.out.println("   - 召喚生命之光，溫暖的金色光芒籠罩四周");
        System.out.println("   - 治癒天使吟唱復原咒語");
        System.out.println("   - 神聖治癒能量開始流動");
    }

    @Override
    protected void grantBlessing() {
        System.out.println("💝 賜予治癒祝福：");
        System.out.println("   - 以神聖之光治癒身體創傷");
        System.out.println("   - 淨化心靈痛苦，帶來內心平靜");
        System.out.println("   - 祝福：『願汝身心康健，永沐神恩』");
    }

    @Override
    protected boolean needsSpecialArtifact() {
        return true; // 治癒儀式需要特殊聖物
    }

    @Override
    protected void useSpecialArtifact() {
        System.out.println("🔮 使用治癒聖物：生命之泉水晶");
        System.out.println("   水晶散發出療癒的翠綠光芒，加強治癒效果");
    }

    @Override
    public String getCeremonyName() {
        return "神聖治癒儀式";
    }
}
```

```java
//BattleCeremony.java
// 戰鬥儀式（具體實現類別）
public class BattleCeremony extends DivineCeremony {

    @Override
    protected void prepareRitual() {
        System.out.println("⚔️ 戰鬥天使準備儀式：");
        System.out.println("   - 點燃勇氣聖火，火光沖天");
        System.out.println("   - 準備聖劍和神聖護甲");
        System.out.println("   - 戰鬥天使展開燃燒的羽翼");
    }

    @Override
    protected void channelDivinePower() {
        System.out.println("⚡ 引導戰鬥神力：");
        System.out.println("   - 召喚正義之雷，天空雷電交加");
        System.out.println("   - 戰鬥天使高聲戰吼，震懾邪惡");
        System.out.println("   - 神聖戰鬥力量開始凝聚");
    }

    @Override
    protected void grantBlessing() {
        System.out.println("🛡️ 賜予戰鬥祝福：");
        System.log.println("   - 以聖光鍛造無堅不摧的聖盾");
        System.out.println("   - 淨化邪惡力量，驅散黑暗");
        System.out.println("   - 祝福：『願汝勇氣無懼，正義永存』");
    }

    @Override
    protected boolean needsSpecialArtifact() {
        return true; // 戰鬥儀式需要特殊聖物
    }

    @Override
    protected void useSpecialArtifact() {
        System.out.println("⚔️ 使用戰鬥聖物：正義審判之劍");
        System.out.println("   聖劍綻放出炙熱的金色光芒，象徵神聖正義");
    }

    @Override
    public String getCeremonyName() {
        return "神聖戰鬥儀式";
    }
}
```

```java
//WisdomCeremony.java
// 智慧儀式（具體實現類別）
public class WisdomCeremony extends DivineCeremony {

    @Override
    protected void prepareRitual() {
        System.out.println("📚 智慧天使準備儀式：");
        System.out.println("   - 點燃知識聖燭，藍色火焰搖曳");
        System.out.println("   - 準備古老聖典和智慧卷軸");
        System.out.println("   - 智慧天使閉目沉思，準備接受啟示");
    }

    @Override
    protected void channelDivinePower() {
        System.out.println("🔮 引導智慧神力：");
        System.out.println("   - 召喚啟示之眼，洞察一切真理");
        System.out.println("   - 智慧天使念誦古老的智慧咒語");
        System.out.println("   - 神聖智慧開始啟發心靈");
    }

    @Override
    protected void grantBlessing() {
        System.out.println("🧠 賜予智慧祝福：");
        System.out.println("   - 以神聖智慧照亮迷茫心靈");
        System.out.println("   - 開啟心智，賜予洞察力和判斷力");
        System.out.println("   - 祝福：『願汝智慧如海，明辨是非』");
    }

    // 智慧儀式不需要特殊聖物，使用預設的 needsSpecialArtifact() 回傳 false

    @Override
    public String getCeremonyName() {
        return "神聖智慧儀式";
    }
}
```

```java
//TemplatePatternExample.java
// 使用範例
public class TemplatePatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到天界聖殿 ===");
        System.out.println("首席編纂天使將指導各種神聖儀式的進行\n");

        // 創建不同類型的神聖儀式
        DivineCeremony healingRitual = new HealingCeremony();
        DivineCeremony battleRitual = new BattleCeremony();
        DivineCeremony wisdomRitual = new WisdomCeremony();

        // 展示儀式清單
        System.out.println("📋 今日儀式安排：");
        System.out.println("1. " + healingRitual.getCeremonyName());
        System.out.println("2. " + battleRitual.getCeremonyName());
        System.out.println("3. " + wisdomRitual.getCeremonyName());
        System.out.println();

        // 執行治癒儀式
        System.out.println("【第一場儀式】" + healingRitual.getCeremonyName());
        healingRitual.performCeremony();

        // 執行戰鬥儀式
        System.out.println("【第二場儀式】" + battleRitual.getCeremonyName());
        battleRitual.performCeremony();

        // 執行智慧儀式
        System.out.println("【第三場儀式】" + wisdomRitual.getCeremonyName());
        wisdomRitual.performCeremony();

        System.out.println("=".repeat(50));

        /**output
        === 歡迎來到天界聖殿 ===
        首席編纂天使將指導各種神聖儀式的進行

        📋 今日儀式安排：
        1. 神聖治癒儀式
        2. 神聖戰鬥儀式
        3. 神聖智慧儀式

        【第一場儀式】神聖治癒儀式
        === 開始執行神聖儀式 ===
        首席編纂天使：神聖的法則不可更改，但其彰顯的方式有萬千。

        🕊️ 開啟聖門：神聖之光照耀大地...
           天使之歌響徹天空，聖潔的力量開始聚集
        💊 治癒天使準備儀式：
           - 點燃治癒聖香，香氣瀰漫
           - 準備聖水和藥草
           - 治癒天使展開純白羽翼
        🌟 引導治癒神力：
           - 召喚生命之光，溫暖的金色光芒籠罩四周
           - 治癒天使吟唱復原咒語
           - 神聖治癒能量開始流動
        🔮 使用治癒聖物：生命之泉水晶
           水晶散發出療癒的翠綠光芒，加強治癒效果
        💝 賜予治癒祝福：
           - 以神聖之光治癒身體創傷
           - 淨化心靈痛苦，帶來內心平靜
           - 祝福：『願汝身心康健，永沐神恩』
        🙏 感謝諸神：向至高無上的神明致敬
           諸天使共同吟唱讚美詩
        ✨ 關閉聖門：神聖之力緩緩散去，回歸天界
           聖潔光芒淡去，儀式圓滿結束

        === 神聖儀式完成 ===

        【第二場儀式】神聖戰鬥儀式
        === 開始執行神聖儀式 ===
        首席編纂天使：神聖的法則不可更改，但其彰顯的方式有萬千。

        🕊️ 開啟聖門：神聖之光照耀大地...
           天使之歌響徹天空，聖潔的力量開始聚集
        ⚔️ 戰鬥天使準備儀式：
           - 點燃勇氣聖火，火光沖天
           - 準備聖劍和神聖護甲
           - 戰鬥天使展開燃燒的羽翼
        ⚡ 引導戰鬥神力：
           - 召喚正義之雷，天空雷電交加
           - 戰鬥天使高聲戰吼，震懾邪惡
           - 神聖戰鬥力量開始凝聚
        ⚔️ 使用戰鬥聖物：正義審判之劍
           聖劍綻放出炙熱的金色光芒，象徵神聖正義
        🛡️ 賜予戰鬥祝福：
           - 以聖光鍛造無堅不摧的聖盾
           - 淨化邪惡力量，驅散黑暗
           - 祝福：『願汝勇氣無懼，正義永存』
        🙏 感謝諸神：向至高無上的神明致敬
           諸天使共同吟唱讚美詩
        ✨ 關閉聖門：神聖之力緩緩散去，回歸天界
           聖潔光芒淡去，儀式圓滿結束

        === 神聖儀式完成 ===

        【第三場儀式】神聖智慧儀式
        === 開始執行神聖儀式 ===
        首席編纂天使：神聖的法則不可更改，但其彰顯的方式有萬千。

        🕊️ 開啟聖門：神聖之光照耀大地...
           天使之歌響徹天空，聖潔的力量開始聚集
        📚 智慧天使準備儀式：
           - 點燃知識聖燭，藍色火焰搖曳
           - 準備古老聖典和智慧卷軸
           - 智慧天使閉目沉思，準備接受啟示
        🔮 引導智慧神力：
           - 召喚啟示之眼，洞察一切真理
           - 智慧天使念誦古老的智慧咒語
           - 神聖智慧開始啟發心靈
        🧠 賜予智慧祝福：
           - 以神聖智慧照亮迷茫心靈
           - 開啟心智，賜予洞察力和判斷力
           - 祝福：『願汝智慧如海，明辨是非』
        🙏 感謝諸神：向至高無上的神明致敬
           諸天使共同吟唱讚美詩
        ✨ 關閉聖門：神聖之力緩緩散去，回歸天界
           聖潔光芒淡去，儀式圓滿結束

        === 神聖儀式完成 ===

        ==================================================
        */
    }
}
```

### JavaScript

```javascript
// 神聖儀式抽象類別（樣版類別）
class DivineCeremony {
  // 樣版方法：定義神聖儀式的完整流程
  performCeremony() {
    console.log("=== 開始執行神聖儀式 ===");
    console.log("首席編纂天使：神聖的法則不可更改，但其彰顯的方式有萬千。\n");

    // 固定步驟：開啟聖門
    this.openSacredGate();

    // 抽象步驟：準備儀式（由子類實現）
    this.prepareRitual();

    // 抽象步驟：引導神力（由子類實現）
    this.channelDivinePower();

    // 鉤子方法：是否需要特殊聖物
    if (this.needsSpecialArtifact()) {
      this.useSpecialArtifact();
    }

    // 抽象步驟：賜予祝福（由子類實現）
    this.grantBlessing();

    // 固定步驟：感謝諸神
    this.offerGratitude();

    // 固定步驟：關閉聖門
    this.closeSacredGate();

    console.log("\n=== 神聖儀式完成 ===\n");
  }

  // 固定步驟：所有儀式都相同的開場
  openSacredGate() {
    console.log("🕊️ 開啟聖門：神聖之光照耀大地...");
    console.log("   天使之歌響徹天空，聖潔的力量開始聚集");
  }

  // 固定步驟：所有儀式都相同的感謝環節
  offerGratitude() {
    console.log("🙏 感謝諸神：向至高無上的神明致敬");
    console.log("   諸天使共同吟唱讚美詩");
  }

  // 固定步驟：所有儀式都相同的結尾
  closeSacredGate() {
    console.log("✨ 關閉聖門：神聖之力緩緩散去，回歸天界");
    console.log("   聖潔光芒淡去，儀式圓滿結束");
  }

  // 抽象方法：準備儀式（必須由子類實現）
  prepareRitual() {
    throw new Error("子類必須實現 prepareRitual 方法");
  }

  // 抽象方法：引導神力（必須由子類實現）
  channelDivinePower() {
    throw new Error("子類必須實現 channelDivinePower 方法");
  }

  // 抽象方法：賜予祝福（必須由子類實現）
  grantBlessing() {
    throw new Error("子類必須實現 grantBlessing 方法");
  }

  // 鉤子方法：是否需要特殊聖物（子類可覆寫）
  needsSpecialArtifact() {
    return false; // 預設不需要
  }

  // 鉤子方法：使用特殊聖物（子類可覆寫）
  useSpecialArtifact() {
    console.log("📿 使用通用聖物：聖光水晶開始發光");
  }

  // 獲取儀式名稱（由子類實現）
  getCeremonyName() {
    throw new Error("子類必須實現 getCeremonyName 方法");
  }
}

// 治癒儀式（具體實現類別）
class HealingCeremony extends DivineCeremony {
  prepareRitual() {
    console.log("💊 治癒天使準備儀式：");
    console.log("   - 點燃治癒聖香，香氣瀰漫");
    console.log("   - 準備聖水和藥草");
    console.log("   - 治癒天使展開純白羽翼");
  }

  channelDivinePower() {
    console.log("🌟 引導治癒神力：");
    console.log("   - 召喚生命之光，溫暖的金色光芒籠罩四周");
    console.log("   - 治癒天使吟唱復原咒語");
    console.log("   - 神聖治癒能量開始流動");
  }

  grantBlessing() {
    console.log("💝 賜予治癒祝福：");
    console.log("   - 以神聖之光治癒身體創傷");
    console.log("   - 淨化心靈痛苦，帶來內心平靜");
    console.log("   - 祝福：『願汝身心康健，永沐神恩』");
  }

  needsSpecialArtifact() {
    return true; // 治癒儀式需要特殊聖物
  }

  useSpecialArtifact() {
    console.log("🔮 使用治癒聖物：生命之泉水晶");
    console.log("   水晶散發出療癒的翠綠光芒，加強治癒效果");
  }

  getCeremonyName() {
    return "神聖治癒儀式";
  }
}

// 戰鬥儀式（具體實現類別）
class BattleCeremony extends DivineCeremony {
  prepareRitual() {
    console.log("⚔️ 戰鬥天使準備儀式：");
    console.log("   - 點燃勇氣聖火，火光沖天");
    console.log("   - 準備聖劍和神聖護甲");
    console.log("   - 戰鬥天使展開燃燒的羽翼");
  }

  channelDivinePower() {
    console.log("⚡ 引導戰鬥神力：");
    console.log("   - 召喚正義之雷，天空雷電交加");
    console.log("   - 戰鬥天使高聲戰吼，震懾邪惡");
    console.log("   - 神聖戰鬥力量開始凝聚");
  }

  grantBlessing() {
    console.log("🛡️ 賜予戰鬥祝福：");
    console.log("   - 以聖光鍛造無堅不摧的聖盾");
    console.log("   - 淨化邪惡力量，驅散黑暗");
    console.log("   - 祝福：『願汝勇氣無懼，正義永存』");
  }

  needsSpecialArtifact() {
    return true; // 戰鬥儀式需要特殊聖物
  }

  useSpecialArtifact() {
    console.log("⚔️ 使用戰鬥聖物：正義審判之劍");
    console.log("   聖劍綻放出炙熱的金色光芒，象徵神聖正義");
  }

  getCeremonyName() {
    return "神聖戰鬥儀式";
  }
}

// 智慧儀式（具體實現類別）
class WisdomCeremony extends DivineCeremony {
  prepareRitual() {
    console.log("📚 智慧天使準備儀式：");
    console.log("   - 點燃知識聖燭，藍色火焰搖曳");
    console.log("   - 準備古老聖典和智慧卷軸");
    console.log("   - 智慧天使閉目沉思，準備接受啟示");
  }

  channelDivinePower() {
    console.log("🔮 引導智慧神力：");
    console.log("   - 召喚啟示之眼，洞察一切真理");
    console.log("   - 智慧天使念誦古老的智慧咒語");
    console.log("   - 神聖智慧開始啟發心靈");
  }

  grantBlessing() {
    console.log("🧠 賜予智慧祝福：");
    console.log("   - 以神聖智慧照亮迷茫心靈");
    console.log("   - 開啟心智，賜予洞察力和判斷力");
    console.log("   - 祝福：『願汝智慧如海，明辨是非』");
  }

  // 智慧儀式不需要特殊聖物，使用預設的 needsSpecialArtifact() 回傳 false

  getCeremonyName() {
    return "神聖智慧儀式";
  }
}

// 使用範例
console.log("=== 歡迎來到天界聖殿 ===");
console.log("首席編纂天使將指導各種神聖儀式的進行\n");

// 創建不同類型的神聖儀式
const healingRitual = new HealingCeremony();
const battleRitual = new BattleCeremony();
const wisdomRitual = new WisdomCeremony();

// 展示儀式清單
console.log("📋 今日儀式安排：");
console.log(`1. ${healingRitual.getCeremonyName()}`);
console.log(`2. ${battleRitual.getCeremonyName()}`);
console.log(`3. ${wisdomRitual.getCeremonyName()}`);
console.log();

// 執行治癒儀式
console.log(`【第一場儀式】${healingRitual.getCeremonyName()}`);
healingRitual.performCeremony();

// 執行戰鬥儀式
console.log(`【第二場儀式】${battleRitual.getCeremonyName()}`);
battleRitual.performCeremony();

// 執行智慧儀式
console.log(`【第三場儀式】${wisdomRitual.getCeremonyName()}`);
wisdomRitual.performCeremony();

console.log("=".repeat(50));

/** output
=== 歡迎來到天界聖殿 ===
首席編纂天使將指導各種神聖儀式的進行

📋 今日儀式安排：
1. 神聖治癒儀式
2. 神聖戰鬥儀式
3. 神聖智慧儀式

【第一場儀式】神聖治癒儀式
=== 開始執行神聖儀式 ===
首席編纂天使：神聖的法則不可更改，但其彰顯的方式有萬千。

🕊️ 開啟聖門：神聖之光照耀大地...
   天使之歌響徹天空，聖潔的力量開始聚集
💊 治癒天使準備儀式：
   - 點燃治癒聖香，香氣瀰漫
   - 準備聖水和藥草
   - 治癒天使展開純白羽翼
🌟 引導治癒神力：
   - 召喚生命之光，溫暖的金色光芒籠罩四周
   - 治癒天使吟唱復原咒語
   - 神聖治癒能量開始流動
🔮 使用治癒聖物：生命之泉水晶
   水晶散發出療癒的翠綠光芒，加強治癒效果
💝 賜予治癒祝福：
   - 以神聖之光治癒身體創傷
   - 淨化心靈痛苦，帶來內心平靜
   - 祝福：『願汝身心康健，永沐神恩』
🙏 感謝諸神：向至高無上的神明致敬
   諸天使共同吟唱讚美詩
✨ 關閉聖門：神聖之力緩緩散去，回歸天界
   聖潔光芒淡去，儀式圓滿結束

=== 神聖儀式完成 ===

【第二場儀式】神聖戰鬥儀式
=== 開始執行神聖儀式 ===
首席編纂天使：神聖的法則不可更改，但其彰顯的方式有萬千。

🕊️ 開啟聖門：神聖之光照耀大地...
   天使之歌響徹天空，聖潔的力量開始聚集
⚔️ 戰鬥天使準備儀式：
   - 點燃勇氣聖火，火光沖天
   - 準備聖劍和神聖護甲
   - 戰鬥天使展開燃燒的羽翼
⚡ 引導戰鬥神力：
   - 召喚正義之雷，天空雷電交加
   - 戰鬥天使高聲戰吼，震懾邪惡
   - 神聖戰鬥力量開始凝聚
⚔️ 使用戰鬥聖物：正義審判之劍
   聖劍綻放出炙熱的金色光芒，象徵神聖正義
🛡️ 賜予戰鬥祝福：
   - 以聖光鍛造無堅不摧的聖盾
   - 淨化邪惡力量，驅散黑暗
   - 祝福：『願汝勇氣無懼，正義永存』
🙏 感謝諸神：向至高無上的神明致敬
   諸天使共同吟唱讚美詩
✨ 關閉聖門：神聖之力緩緩散去，回歸天界
   聖潔光芒淡去，儀式圓滿結束

=== 神聖儀式完成 ===

【第三場儀式】神聖智慧儀式
=== 開始執行神聖儀式 ===
首席編纂天使：神聖的法則不可更改，但其彰顯的方式有萬千。

🕊️ 開啟聖門：神聖之光照耀大地...
   天使之歌響徹天空，聖潔的力量開始聚集
📚 智慧天使準備儀式：
   - 點燃知識聖燭，藍色火焰搖曳
   - 準備古老聖典和智慧卷軸
   - 智慧天使閉目沉思，準備接受啟示
🔮 引導智慧神力：
   - 召喚啟示之眼，洞察一切真理
   - 智慧天使念誦古老的智慧咒語
   - 神聖智慧開始啟發心靈
🧠 賜予智慧祝福：
   - 以神聖智慧照亮迷茫心靈
   - 開啟心智，賜予洞察力和判斷力
   - 祝福：『願汝智慧如海，明辨是非』
🙏 感謝諸神：向至高無上的神明致敬
   諸天使共同吟唱讚美詩
✨ 關閉聖門：神聖之力緩緩散去，回歸天界
   聖潔光芒淡去，儀式圓滿結束

=== 神聖儀式完成 ===

==================================================

 */
```

## 小總結

Template Method Pattern 設計模式就像我們故事中的聖典首席編纂天使，定義`演算法的骨架`，讓子類可以覆寫特定步驟而不改變整體結構

確保了：

- 所有儀式都遵循相同的神聖流程
- 每種儀式都能發揮各自的特色
- 核心秩序不變，細節可以靈活調整

**核心特點：**

- **演算法骨架**：在抽象類中定義演算法的基本步驟和執行順序
- **延遲決策**：具體的實現細節延遲到子類中決定
- **不變與可變分離**：固定的步驟在父類實現，變化的步驟由子類覆寫
- **鉤子方法(Hook Method)**：提供可選的擴展點，子類可以選擇性地覆寫

**使用時機：**

- 多個類有相似的演算法，但某些步驟實現不同（ex: 不同格式的資料解析流程）
- 想要控制子類的擴展，只允許在特定點進行自定義（ex: 遊戲角色的升級流程）
- 有一組相關的演算法，想要統一管理其共同行為（ex: 不同支付方式的處理流程）
- 需要避免重複程式碼，將共同邏輯提取到父類（ex: 網頁渲染的基本步驟）

<!-- **注意事項：**

- 父類的樣版方法通常宣告為 `final`，防止子類修改演算法結構
- 抽象方法太多可能導致子類實現負擔過重
- 需要仔細設計鉤子方法的粒度，避免過度靈活或過度僵化
- 適合穩定的演算法框架，頻繁變動的演算法不適用
- 要注意里氏替換原則，確保所有子類都能正確執行演算法 -->
