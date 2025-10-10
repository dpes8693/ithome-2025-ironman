
```java
package file11;
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
package file11;
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
package file11;
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
package file11;
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
package file11;
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
        System.out.println("首席編纂天使的樣版方法確保了：");
        System.out.println("- 所有儀式都遵循相同的神聖流程");
        System.out.println("- 每種儀式都能發揮各自的特色");
        System.out.println("- 核心秩序不變，細節可以靈活調整");

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
        首席編纂天使的樣版方法確保了：
        - 所有儀式都遵循相同的神聖流程
        - 每種儀式都能發揮各自的特色
        - 核心秩序不變，細節可以靈活調整
        */
    }
}
```