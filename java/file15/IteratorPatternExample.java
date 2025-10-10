package file15;

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