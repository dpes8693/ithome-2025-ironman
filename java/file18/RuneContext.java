package file18;

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
