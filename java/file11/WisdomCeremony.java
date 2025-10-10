package file11;

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