package file12;

// 葉子元件（Leaf）
public class Leaf extends ForestComponent {
    private String leafType;
    private boolean isHealthy;

    public Leaf(String name, String leafType, int level) {
        super(name, level);
        this.leafType = leafType;
        this.isHealthy = true;
    }

    @Override
    public void grow() {
        System.out.println(getIndent() + "🍃 " + name + "（" + leafType + "）正在生長，吸收陽光和雨露");
    }

    @Override
    public void photosynthesize() {
        if (isHealthy) {
            System.out.println(getIndent() + "☀️ " + name + " 進行光合作用，產生氧氣和葡萄糖");
        } else {
            System.out.println(getIndent() + "🍂 " + name + " 健康狀況不佳，光合作用效率降低");
        }
    }

    @Override
    public int calculateBiomass() {
        return isHealthy ? 1 : 0; // 健康葉子貢獻 1 單位生物量
    }

    @Override
    public void displayStructure() {
        String healthIcon = isHealthy ? "🍃" : "🍂";
        System.out.println(getIndent() + healthIcon + " " + name + "（" + leafType + "）");
    }

    public void setHealthy(boolean healthy) {
        this.isHealthy = healthy;
    }

    public boolean isHealthy() {
        return isHealthy;
    }
}