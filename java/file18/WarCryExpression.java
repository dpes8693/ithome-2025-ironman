package file18;

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
