package file11;

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