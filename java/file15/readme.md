
```java
package file15;
//Treasure.java
// 寶藏類
public class Treasure {
    private String name;
    private String description;
    private int value;

    public Treasure(String name, String description, int value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("💎 %s - %s (價值: %d金幣)", name, description, value);
    }
}
```

```java
package file15;
//Iterator.java
// 走訪器介面
public interface Iterator<T> {
    boolean hasNext();    // 是否還有下一個元素
    T next();            // 取得下一個元素
    void reset();        // 重置到開始位置
}
```

```java
package file15;
//Aggregate.java
// 聚合物介面
public interface Aggregate<T> {
    Iterator<T> createIterator();
    void addItem(T item);
    int getSize();
}
```

```java
package file15;
//TreasureIterator.java
// 寶藏走訪器
public class TreasureIterator implements Iterator<Treasure> {
    private Treasure[] treasures;
    private int currentPosition;
    private int size;

    public TreasureIterator(Treasure[] treasures, int size) {
        this.treasures = treasures;
        this.size = size;
        this.currentPosition = 0;
    }

    @Override
    public boolean hasNext() {
        return currentPosition < size;
    }

    @Override
    public Treasure next() {
        if (!hasNext()) {
            throw new RuntimeException("⚠️ 沒有更多寶藏了！");
        }
        Treasure treasure = treasures[currentPosition];
        currentPosition++;
        System.out.println("🗺️ 探險家發現了第 " + currentPosition + " 個寶藏");
        return treasure;
    }

    @Override
    public void reset() {
        currentPosition = 0;
        System.out.println("🔄 探險家回到遺跡入口，準備重新探索");
    }
}
```

```java
package file15;
//TreasureCollection.java
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
```

```java
package file15;
//Explorer.java
// 不知疲倦的探險家
public class Explorer {
    private String name;
    private int totalTreasuresFound;
    private int totalValue;

    public Explorer(String name) {
        this.name = name;
        this.totalTreasuresFound = 0;
        this.totalValue = 0;
    }

    // 探索遺跡
    public void exploreDungeon(TreasureCollection dungeon) {
        System.out.println("🚪 " + name + " 進入了 " + dungeon.getDungeonName());
        
        Iterator<Treasure> iterator = dungeon.createIterator();
        
        while (iterator.hasNext()) {
            Treasure treasure = iterator.next();
            System.out.println("🔍 " + name + " 發現：" + treasure);
            totalTreasuresFound++;
            totalValue += treasure.getValue();
            
            // 模擬探索時間
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println("✅ " + name + " 完成了 " + dungeon.getDungeonName() + " 的探索");
    }

    // 重新探索（展示重置功能）
    public void reExploreDungeon(TreasureCollection dungeon) {
        System.out.println("🔄 " + name + " 決定重新探索 " + dungeon.getDungeonName());
        
        Iterator<Treasure> iterator = dungeon.createIterator();
        iterator.reset(); // 重置到開始位置
        
        int count = 0;
        while (iterator.hasNext() && count < 3) { // 只看前3個
            Treasure treasure = iterator.next();
            System.out.println("👀 " + name + " 重新檢視：" + treasure.getName());
            count++;
        }
    }

    // 展示探險家統計
    public void showExplorerStats() {
        System.out.println("📊 === " + name + " 的探險統計 ===");
        System.out.println("💎 發現寶藏數量：" + totalTreasuresFound);
        System.out.println("💰 累積價值：" + totalValue + " 金幣");
        System.out.println("「再複雜的迷宮，也有其遍歷之法。」");
    }
}
```

```java
package file15;
//IteratorPatternExample.java
// 使用範例
public class IteratorPatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到古老遺跡探險 ===\n");

        // 創建探險家
        Explorer explorer = new Explorer("艾麗亞");

        // 創建第一個遺跡
        TreasureCollection ancientTomb = new TreasureCollection("古老陵墓", 5);
        ancientTomb.addItem(new Treasure("黃金王冠", "古代國王的權力象徵", 1000));
        ancientTomb.addItem(new Treasure("魔法水晶", "蘊含神秘力量的水晶", 800));
        ancientTomb.addItem(new Treasure("白銀匕首", "鋒利的刺客武器", 300));
        ancientTomb.addItem(new Treasure("古老卷軸", "記載失傳法術的卷軸", 500));

        System.out.println();
        ancientTomb.showDungeonInfo();
        System.out.println();

        // 探索第一個遺跡
        explorer.exploreDungeon(ancientTomb);
        System.out.println();

        // 創建第二個遺跡
        TreasureCollection dragonLair = new TreasureCollection("巨龍巢穴", 3);
        dragonLair.addItem(new Treasure("龍鱗護甲", "龍鱗製成的無堅不摧護甲", 2000));
        dragonLair.addItem(new Treasure("火焰寶石", "永不熄滅的火焰寶石", 1500));
        dragonLair.addItem(new Treasure("龍牙劍", "削鐵如泥的傳說之劍", 1800));

        System.out.println();
        dragonLair.showDungeonInfo();
        System.out.println();

        // 探索第二個遺跡
        explorer.exploreDungeon(dragonLair);
        System.out.println();

        // 展示重新探索功能
        explorer.reExploreDungeon(ancientTomb);
        System.out.println();

        // 展示探險家統計
        explorer.showExplorerStats();

        /**output
        === 歡迎來到古老遺跡探險 ===

        ✨ 在 古老陵墓 中放置了寶藏：黃金王冠
        ✨ 在 古老陵墓 中放置了寶藏：魔法水晶
        ✨ 在 古老陵墓 中放置了寶藏：白銀匕首
        ✨ 在 古老陵墓 中放置了寶藏：古老卷軸

        🏛️ === 古老陵墓 遺跡資訊 ===
        📊 寶藏總數：4/5
        🎯 準備開始探險！

        🚪 艾麗亞 進入了 古老陵墓
        🗺️ 為探險家準備了 古老陵墓 的走訪卷軸
        🗺️ 探險家發現了第 1 個寶藏
        🔍 艾麗亞 發現：💎 黃金王冠 - 古代國王的權力象徵 (價值: 1000金幣)
        🗺️ 探險家發現了第 2 個寶藏
        🔍 艾麗亞 發現：💎 魔法水晶 - 蘊含神秘力量的水晶 (價值: 800金幣)
        🗺️ 探險家發現了第 3 個寶藏
        🔍 艾麗亞 發現：💎 白銀匕首 - 鋒利的刺客武器 (價值: 300金幣)
        🗺️ 探險家發現了第 4 個寶藏
        🔍 艾麗亞 發現：💎 古老卷軸 - 記載失傳法術的卷軸 (價值: 500金幣)
        ✅ 艾麗亞 完成了 古老陵墓 的探索

        ✨ 在 巨龍巢穴 中放置了寶藏：龍鱗護甲
        ✨ 在 巨龍巢穴 中放置了寶藏：火焰寶石
        ✨ 在 巨龍巢穴 中放置了寶藏：龍牙劍

        🏛️ === 巨龍巢穴 遺跡資訊 ===
        📊 寶藏總數：3/3
        🎯 準備開始探險！

        🚪 艾麗亞 進入了 巨龍巢穴
        🗺️ 為探險家準備了 巨龍巢穴 的走訪卷軸
        🗺️ 探險家發現了第 1 個寶藏
        🔍 艾麗亞 發現：💎 龍鱗護甲 - 龍鱗製成的無堅不摧護甲 (價值: 2000金幣)
        🗺️ 探險家發現了第 2 個寶藏
        🔍 艾麗亞 發現：💎 火焰寶石 - 永不熄滅的火焰寶石 (價值: 1500金幣)
        🗺️ 探險家發現了第 3 個寶藏
        🔍 艾麗亞 發現：💎 龍牙劍 - 削鐵如泥的傳說之劍 (價值: 1800金幣)
        ✅ 艾麗亞 完成了 巨龍巢穴 的探索

        🔄 艾麗亞 決定重新探索 古老陵墓
        🗺️ 為探險家準備了 古老陵墓 的走訪卷軸
        🔄 探險家回到遺跡入口，準備重新探索
        🗺️ 探險家發現了第 1 個寶藏
        👀 艾麗亞 重新檢視：黃金王冠
        🗺️ 探險家發現了第 2 個寶藏
        👀 艾麗亞 重新檢視：魔法水晶
        🗺️ 探險家發現了第 3 個寶藏
        👀 艾麗亞 重新檢視：白銀匕首

        📊 === 艾麗亞 的探險統計 ===
        💎 發現寶藏數量：7
        💰 累積價值：7900 金幣
        「再複雜的迷宮，也有其遍歷之法。」
        */
    }
}
```