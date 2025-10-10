package file15;

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