package file23;
// 魔法狀態備忘錄
public class MagicState {
    private final int manaLevel;
    private final String spellForm;
    private final String experimentPhase;
    private final long timestamp;

    public MagicState(int manaLevel, String spellForm, String experimentPhase) {
        this.manaLevel = manaLevel;
        this.spellForm = spellForm;
        this.experimentPhase = experimentPhase;
        this.timestamp = System.currentTimeMillis();
    }

    // 只提供給原發器的恢復方法
    protected int getManaLevel() {
        return manaLevel;
    }

    protected String getSpellForm() {
        return spellForm;
    }

    protected String getExperimentPhase() {
        return experimentPhase;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("魔法狀態[魔力:%d, 形態:%s, 階段:%s, 時間:%d]", 
                           manaLevel, spellForm, experimentPhase, timestamp);
    }
}
