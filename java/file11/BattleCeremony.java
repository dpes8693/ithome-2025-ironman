package file11;

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
        System.out.println("   - 以聖光鍛造無堅不摧的聖盾");
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