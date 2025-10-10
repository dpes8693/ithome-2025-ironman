package file11;

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