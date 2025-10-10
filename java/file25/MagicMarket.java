package file25;
//MagicMarket.java
// 魔法市集
import java.util.ArrayList;
import java.util.List;

public class MagicMarket {
    private List<Appraisable> items;
    private String marketName;

    public MagicMarket(String marketName) {
        this.marketName = marketName;
        this.items = new ArrayList<>();
    }

    public void addItem(Appraisable item) {
        items.add(item);
        System.out.println("新商品入場: " + item.getName());
    }

    public void removeItem(Appraisable item) {
        items.remove(item);
        System.out.println("商品離場: " + item.getName());
    }

    // 接受訪客鑑定師進行全面鑑定
    public void acceptAppraiser(Appraiser appraiser) {
        System.out.println("=== 歡迎鑑定師來到 " + marketName + " ===");
        System.out.println("開始鑑定市集中的所有物品...\n");

        for (Appraisable item : items) {
            item.accept(appraiser);
        }

        // 顯示鑑定總結
        if (appraiser instanceof ValueAppraiser) {
            ((ValueAppraiser) appraiser).showTotalValue();
        } else if (appraiser instanceof QualityAppraiser) {
            ((QualityAppraiser) appraiser).showAppraisalSummary();
        }
    }

    public void showMarketInfo() {
        System.out.println("=== " + marketName + " 市集資訊 ===");
        System.out.println("目前商品數量: " + items.size());
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName());
        }
        System.out.println();
    }

    public int getItemCount() {
        return items.size();
    }
}
