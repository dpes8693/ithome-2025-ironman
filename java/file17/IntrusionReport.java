package file17;
// 入侵報告
public class IntrusionReport {
    private String location;
    private int threatLevel;
    private String description;
    private String intruderType;

    public IntrusionReport(String location, int threatLevel, String description, String intruderType) {
        this.location = location;
        this.threatLevel = threatLevel;
        this.description = description;
        this.intruderType = intruderType;
    }

    public String getLocation() {
        return location;
    }

    public int getThreatLevel() {
        return threatLevel;
    }

    public String getDescription() {
        return description;
    }

    public String getIntruderType() {
        return intruderType;
    }

    @Override
    public String toString() {
        return String.format("【位置：%s】威脅等級：%d - %s（%s）", 
                           location, threatLevel, description, intruderType);
    }
}
