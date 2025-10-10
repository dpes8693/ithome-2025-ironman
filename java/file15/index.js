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