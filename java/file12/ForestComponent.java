package file12;

// 森林元件抽象類別（Component）
public abstract class ForestComponent {
    protected String name;
    protected int level; // 層級（用於縮進顯示）

    public ForestComponent(String name, int level) {
        this.name = name;
        this.level = level;
    }

    // 共同操作介面
    public abstract void grow(); // 生長
    public abstract void photosynthesize(); // 光合作用
    public abstract int calculateBiomass(); // 計算生物量
    public abstract void displayStructure(); // 顯示結構

    // 組合相關操作（預設拋出異常，由 Composite 覆寫）
    public void addComponent(ForestComponent component) {
        throw new UnsupportedOperationException("此操作不適用於葉節點");
    }

    public void removeComponent(ForestComponent component) {
        throw new UnsupportedOperationException("此操作不適用於葉節點");
    }

    public java.util.List<ForestComponent> getChildren() {
        throw new UnsupportedOperationException("此操作不適用於葉節點");
    }

    // 輔助方法：產生縮進
    protected String getIndent() {
        return "  ".repeat(level);
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}