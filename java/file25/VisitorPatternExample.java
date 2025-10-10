package file25;
//VisitorPatternExample.java
// 使用範例
public class VisitorPatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到世界巡遊鑑定師的魔法市集 ===\n");

        // 創建魔法市集
        MagicMarket market = new MagicMarket("奇蹟商會");

        // 創建各種待鑑定的物品和人物
        DwarfCraftsman dwarf1 = new DwarfCraftsman("鋼鬍巴林", 12, "傳奇工藝", 150);
        DwarfCraftsman dwarf2 = new DwarfCraftsman("銅斧索林", 8, "普通工藝", 80);

        MagicCrystal crystal1 = new MagicCrystal("暴風之眼", "風系水晶", 85, "傳說", true);
        MagicCrystal crystal2 = new MagicCrystal("微光石", "光系水晶", 45, "稀有", false);

        RarePlant plant1 = new RarePlant("千年血蓮", "蓮花科", 90, "深淵", 1000);
        RarePlant plant2 = new RarePlant("月光草", "草本科", 35, "森林", 10);

        EnchantedWeapon weapon1 = new EnchantedWeapon("烈焰之劍", "長劍", 95, "火焰傷害+30", 85);
        EnchantedWeapon weapon2 = new EnchantedWeapon("冰霜匕首", "匕首", 60, "冰霜減速", 70);

        // 將物品加入市集
        market.addItem(dwarf1);
        market.addItem(dwarf2);
        market.addItem(crystal1);
        market.addItem(crystal2);
        market.addItem(plant1);
        market.addItem(plant2);
        market.addItem(weapon1);
        market.addItem(weapon2);

        System.out.println();
        market.showMarketInfo();

        // 創建不同類型的鑑定師
        ValueAppraiser valueAppraiser = new ValueAppraiser("黃金眼商人");
        QualityAppraiser qualityAppraiser = new QualityAppraiser("品質大師");

        // 進行價值鑑定
        System.out.println("========================================");
        System.out.println("第一輪：價值鑑定");
        System.out.println("========================================");
        market.acceptAppraiser(valueAppraiser);

        // 進行品質鑑定
        System.out.println("========================================");
        System.out.println("第二輪：品質鑑定");
        System.out.println("========================================");
        market.acceptAppraiser(qualityAppraiser);

        System.out.println("=== 鑑定完成 ===");
        System.out.println("感謝世界巡遊鑑定師的專業服務！");
    }
}
