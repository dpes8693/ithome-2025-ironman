package file18;

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
