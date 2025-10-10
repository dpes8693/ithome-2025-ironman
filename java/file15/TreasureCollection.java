package file15;

// 寶藏收集（聚合物實作）
public class TreasureCollection implements Aggregate<Treasure> {
    private Treasure[] treasures;
    private int currentSize;
    private int maxSize;
    private String dungeonName;

    public TreasureCollection(String dungeonName, int maxSize) {
        this.dungeonName = dungeonName;
        this.maxSize = maxSize;
        this.treasures = new Treasure[maxSize];
        this.currentSize = 0;
    }

    @Override
    public void addItem(Treasure treasure) {
        if (currentSize >= maxSize) {
            System.out.println("⚠️ 遺跡已滿，無法放置更多寶藏！");
            return;
        }
        treasures[currentSize] = treasure;
        currentSize++;
        System.out.println("✨ 在 " + dungeonName + " 中放置了寶藏：" + treasure.getName());
    }

    @Override
    public int getSize() {
        return currentSize;
    }

    @Override
    public Iterator<Treasure> createIterator() {
        System.out.println("🗺️ 為探險家準備了 " + dungeonName + " 的走訪卷軸");
        return new TreasureIterator(treasures, currentSize);
    }

    public String getDungeonName() {
        return dungeonName;
    }

    // 展示遺跡資訊
    public void showDungeonInfo() {
        System.out.println("🏛️ === " + dungeonName + " 遺跡資訊 ===");
        System.out.println("📊 寶藏總數：" + currentSize + "/" + maxSize);
        System.out.println("🎯 準備開始探險！");
    }
}