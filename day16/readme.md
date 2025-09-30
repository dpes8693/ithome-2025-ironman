# Day16 走訪器模式 (Iterator Pattern)

## 擬人化角色：【不知疲倦的探險家】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/15-Iterator.png)

- 種族： 人類
- 外貌： 一位身穿戶外探險服、背著沉重行囊的女性探險家。她手持一張發光的全息地圖，地圖上標示著複雜的探索路線，旁邊還有一個捲起來的羊皮紙地圖和一盞煤油燈。她正從一個幽暗的地下迷宮深處走出來，身後有半透明的幽靈隊友跟隨。
- 性格： 堅韌、獨立、有條不紊。她能按照既定的路線一個接一個地遍歷集合中的所有物件，而無需了解集合的內部結構。
- 能力： 提供方法去走訪集合內的物件，走訪過程不需要知道集合內部的結構。這位探險家（迭代器）在探索一個複雜的古老遺跡（集合）時，她不會直接去了解遺跡的每一堵牆、每一個房間是如何連接的。相反，她只會拿到一份「走訪路線圖」。她只需按照路線圖上的指示，一步步地從一個房間走到下一個房間，從一個線索找到下一個線索，直到遍歷完整個遺跡。
- 代表語： 「再複雜的迷宮，也有其遍歷之法。」
- 背景故事： 在一個充滿未知遺跡和險惡迷宮的世界中，這位探險家是尋寶者們的傳奇。她不是靠死記硬背迷宮的每一個細節，而是精通於閱讀那些古老的「走訪卷軸」。這些卷軸並不是直接描述迷宮的地圖，而是告訴她「下一步該往哪裡走」的指示。無論迷宮的內部結構多麼詭異，她都能依賴這些卷軸，一個接一個地找到所有隱藏的寶藏和秘密，而無需被迷宮的複雜性所困擾。

---

## 範例

### Java

```java
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
//Iterator.java
// 走訪器介面
public interface Iterator<T> {
    boolean hasNext();    // 是否還有下一個元素
    T next();            // 取得下一個元素
    void reset();        // 重置到開始位置
}
```

```java
//Aggregate.java
// 聚合物介面
public interface Aggregate<T> {
    Iterator<T> createIterator();
    void addItem(T item);
    int getSize();
}
```

```java
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

### JavaScript

```javascript
// 寶藏類
class Treasure {
  constructor(name, description, value) {
    this.name = name;
    this.description = description;
    this.value = value;
  }

  toString() {
    return `💎 ${this.name} - ${this.description} (價值: ${this.value}金幣)`;
  }
}

// 走訪器介面
class Iterator {
  hasNext() {
    throw new Error("子類必須實現 hasNext 方法");
  }

  next() {
    throw new Error("子類必須實現 next 方法");
  }

  reset() {
    throw new Error("子類必須實現 reset 方法");
  }
}

// 聚合物介面
class Aggregate {
  createIterator() {
    throw new Error("子類必須實現 createIterator 方法");
  }

  addItem(item) {
    throw new Error("子類必須實現 addItem 方法");
  }

  getSize() {
    throw new Error("子類必須實現 getSize 方法");
  }
}

// 寶藏走訪器
class TreasureIterator extends Iterator {
  constructor(treasures) {
    super();
    this.treasures = treasures;
    this.currentPosition = 0;
  }

  hasNext() {
    return this.currentPosition < this.treasures.length;
  }

  next() {
    if (!this.hasNext()) {
      throw new Error("⚠️ 沒有更多寶藏了！");
    }
    const treasure = this.treasures[this.currentPosition];
    this.currentPosition++;
    console.log(`🗺️ 探險家發現了第 ${this.currentPosition} 個寶藏`);
    return treasure;
  }

  reset() {
    this.currentPosition = 0;
    console.log("🔄 探險家回到遺跡入口，準備重新探索");
  }
}

// 寶藏收集（聚合物實作）
class TreasureCollection extends Aggregate {
  constructor(dungeonName, maxSize = 10) {
    super();
    this.dungeonName = dungeonName;
    this.maxSize = maxSize;
    this.treasures = [];
  }

  addItem(treasure) {
    if (this.treasures.length >= this.maxSize) {
      console.log("⚠️ 遺跡已滿，無法放置更多寶藏！");
      return;
    }
    this.treasures.push(treasure);
    console.log(`✨ 在 ${this.dungeonName} 中放置了寶藏：${treasure.name}`);
  }

  getSize() {
    return this.treasures.length;
  }

  createIterator() {
    console.log(`🗺️ 為探險家準備了 ${this.dungeonName} 的走訪卷軸`);
    return new TreasureIterator(this.treasures);
  }

  getDungeonName() {
    return this.dungeonName;
  }

  // 展示遺跡資訊
  showDungeonInfo() {
    console.log(`🏛️ === ${this.dungeonName} 遺跡資訊 ===`);
    console.log(`📊 寶藏總數：${this.treasures.length}/${this.maxSize}`);
    console.log("🎯 準備開始探險！");
  }
}

// 不知疲倦的探險家
class Explorer {
  constructor(name) {
    this.name = name;
    this.totalTreasuresFound = 0;
    this.totalValue = 0;
  }

  // 探索遺跡
  async exploreDungeon(dungeon) {
    console.log(`🚪 ${this.name} 進入了 ${dungeon.getDungeonName()}`);
    
    const iterator = dungeon.createIterator();
    
    while (iterator.hasNext()) {
      const treasure = iterator.next();
      console.log(`🔍 ${this.name} 發現：${treasure}`);
      this.totalTreasuresFound++;
      this.totalValue += treasure.value;
      
      // 模擬探索時間
      await new Promise(resolve => setTimeout(resolve, 500));
    }
    
    console.log(`✅ ${this.name} 完成了 ${dungeon.getDungeonName()} 的探索`);
  }

  // 重新探索（展示重置功能）
  reExploreDungeon(dungeon) {
    console.log(`🔄 ${this.name} 決定重新探索 ${dungeon.getDungeonName()}`);
    
    const iterator = dungeon.createIterator();
    iterator.reset(); // 重置到開始位置
    
    let count = 0;
    while (iterator.hasNext() && count < 3) { // 只看前3個
      const treasure = iterator.next();
      console.log(`👀 ${this.name} 重新檢視：${treasure.name}`);
      count++;
    }
  }

  // 展示探險家統計
  showExplorerStats() {
    console.log(`📊 === ${this.name} 的探險統計 ===`);
    console.log(`💎 發現寶藏數量：${this.totalTreasuresFound}`);
    console.log(`💰 累積價值：${this.totalValue} 金幣`);
    console.log("「再複雜的迷宮，也有其遍歷之法。」");
  }
}

// 使用範例
async function runIteratorExample() {
  console.log("=== 歡迎來到古老遺跡探險 ===\n");

  // 創建探險家
  const explorer = new Explorer("艾麗亞");

  // 創建第一個遺跡
  const ancientTomb = new TreasureCollection("古老陵墓", 5);
  ancientTomb.addItem(new Treasure("黃金王冠", "古代國王的權力象徵", 1000));
  ancientTomb.addItem(new Treasure("魔法水晶", "蘊含神秘力量的水晶", 800));
  ancientTomb.addItem(new Treasure("白銀匕首", "鋒利的刺客武器", 300));
  ancientTomb.addItem(new Treasure("古老卷軸", "記載失傳法術的卷軸", 500));

  console.log("");
  ancientTomb.showDungeonInfo();
  console.log("");

  // 探索第一個遺跡
  await explorer.exploreDungeon(ancientTomb);
  console.log("");

  // 創建第二個遺跡
  const dragonLair = new TreasureCollection("巨龍巢穴", 3);
  dragonLair.addItem(new Treasure("龍鱗護甲", "龍鱗製成的無堅不摧護甲", 2000));
  dragonLair.addItem(new Treasure("火焰寶石", "永不熄滅的火焰寶石", 1500));
  dragonLair.addItem(new Treasure("龍牙劍", "削鐵如泥的傳說之劍", 1800));

  console.log("");
  dragonLair.showDungeonInfo();
  console.log("");

  // 探索第二個遺跡
  await explorer.exploreDungeon(dragonLair);
  console.log("");

  // 展示重新探索功能
  explorer.reExploreDungeon(ancientTomb);
  console.log("");

  // 展示探險家統計
  explorer.showExplorerStats();
}

// 執行範例
runIteratorExample();

/** output
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
```

## 小總結

Iterator 設計模式就像我們故事中的不知疲倦探險家，提供`一種方法來順序訪問聚合物件中的元素`，而無需暴露其內部結構

**核心特點：**

- **統一訪問**：提供統一的介面來遍歷不同類型的集合
- **封裝內部結構**：客戶端不需要知道集合的內部實作細節
- **支援多種遍歷**：可以為同一個集合提供不同的遍歷方式
- **簡化集合介面**：將遍歷責任從集合類中分離出來

**主要角色：**

- **Iterator（走訪器）**：定義訪問和遍歷元素的介面
- **ConcreteIterator（具體走訪器）**：實作走訪器介面，追蹤當前位置
- **Aggregate（聚合物）**：定義創建相應走訪器物件的介面
- **ConcreteAggregate（具體聚合物）**：實作聚合物介面，返回具體走訪器實例

**使用時機：**

- 需要訪問聚合物件的內容而不暴露其內部表示（ex: 資料庫結果集遍歷）
- 需要為聚合物件提供多種遍歷方式（ex: 前序、中序、後序遍歷樹）

<!-- **注意事項：**

- 在簡單的集合中可能顯得過於複雜
- 需要維護走訪器的狀態，可能會有記憶體開銷
- 在多執行緒環境中需要考慮同步問題
- 現代程式語言通常有內建的迭代器支援（如 Java 的 foreach、JavaScript 的 for...of） -->
