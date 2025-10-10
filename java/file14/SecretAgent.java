package file14;

// 秘密特工介面
public interface SecretAgent {
    void executeTask(String taskName);
    String gatherIntelligence(String target);
    void reportStatus();
    boolean isAvailable();
}