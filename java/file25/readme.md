
```java
package file25;
//Appraisable.java
// 可鑑定元素介面
public interface Appraisable {
    void accept(Appraiser visitor);
    String getName();
}
```

```java
package file25;
//DwarfCraftsman.java
// 矮人工匠
public class DwarfCraftsman implements Appraisable {
    private String name;
    private int craftingLevel;
    private String specialty;
    private int yearsOfExperience;

    public DwarfCraftsman(String name, int craftingLevel, String specialty, int yearsOfExperience) {
        this.name = name;
        this.craftingLevel = craftingLevel;
        this.specialty = specialty;
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public void accept(Appraiser visitor) {
        visitor.appraise(this);
    }

    @Override
    public String getName() {
        return name;
    }

    // Getter methods
    public int getCraftingLevel() {
        return craftingLevel;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
}
```

```java
package file25;
//MagicCrystal.java
// 魔法水晶
public class MagicCrystal implements Appraisable {
    private String name;
    private String crystalType;
    private int magicPower;
    private String rarity;
    private boolean isEnchanted;

    public MagicCrystal(String name, String crystalType, int magicPower, String rarity, boolean isEnchanted) {
        this.name = name;
        this.crystalType = crystalType;
        this.magicPower = magicPower;
        this.rarity = rarity;
        this.isEnchanted = isEnchanted;
    }

    @Override
    public void accept(Appraiser visitor) {
        visitor.appraise(this);
    }

    @Override
    public String getName() {
        return name;
    }

    // Getter methods
    public String getCrystalType() {
        return crystalType;
    }

    public int getMagicPower() {
        return magicPower;
    }

    public String getRarity() {
        return rarity;
    }

    public boolean isEnchanted() {
        return isEnchanted;
    }
}
```

```java
package file25;
//RarePlant.java
// 珍稀植物
public class RarePlant implements Appraisable {
    private String name;
    private String species;
    private int medicinalValue;
    private String habitat;
    private int growthTime;

    public RarePlant(String name, String species, int medicinalValue, String habitat, int growthTime) {
        this.name = name;
        this.species = species;
        this.medicinalValue = medicinalValue;
        this.habitat = habitat;
        this.growthTime = growthTime;
    }

    @Override
    public void accept(Appraiser visitor) {
        visitor.appraise(this);
    }

    @Override
    public String getName() {
        return name;
    }

    // Getter methods
    public String getSpecies() {
        return species;
    }

    public int getMedicinalValue() {
        return medicinalValue;
    }

    public String getHabitat() {
        return habitat;
    }

    public int getGrowthTime() {
        return growthTime;
    }
}
```

```java
package file25;
//EnchantedWeapon.java
// 附魔武器
public class EnchantedWeapon implements Appraisable {
    private String name;
    private String weaponType;
    private int damage;
    private String enchantment;
    private int durability;

    public EnchantedWeapon(String name, String weaponType, int damage, String enchantment, int durability) {
        this.name = name;
        this.weaponType = weaponType;
        this.damage = damage;
        this.enchantment = enchantment;
        this.durability = durability;
    }

    @Override
    public void accept(Appraiser visitor) {
        visitor.appraise(this);
    }

    @Override
    public String getName() {
        return name;
    }

    // Getter methods
    public String getWeaponType() {
        return weaponType;
    }

    public int getDamage() {
        return damage;
    }

    public String getEnchantment() {
        return enchantment;
    }

    public int getDurability() {
        return durability;
    }
}
```

```java
package file25;
//Appraiser.java
// 鑑定師訪客介面
public interface Appraiser {
    void appraise(DwarfCraftsman craftsman);
    void appraise(MagicCrystal crystal);
    void appraise(RarePlant plant);
    void appraise(EnchantedWeapon weapon);
}
```

```java
package file25;
//ValueAppraiser.java
// 價值鑑定師
public class ValueAppraiser implements Appraiser {
    private String name;
    private int totalValue;

    public ValueAppraiser(String name) {
        this.name = name;
        this.totalValue = 0;
    }

    @Override
    public void appraise(DwarfCraftsman craftsman) {
        int value = craftsman.getCraftingLevel() * 100 + craftsman.getYearsOfExperience() * 50;
        if ("傳奇工藝".equals(craftsman.getSpecialty())) {
            value *= 2;
        }
        totalValue += value;
        
        System.out.println("=== 價值鑑定：矮人工匠 ===");
        System.out.println("姓名: " + craftsman.getName());
        System.out.println("工藝等級: " + craftsman.getCraftingLevel());
        System.out.println("專精: " + craftsman.getSpecialty());
        System.out.println("經驗年數: " + craftsman.getYearsOfExperience());
        System.out.println("鑑定價值: " + value + " 金幣");
        System.out.println();
    }

    @Override
    public void appraise(MagicCrystal crystal) {
        int value = crystal.getMagicPower() * 10;
        switch (crystal.getRarity()) {
            case "普通": value *= 1; break;
            case "稀有": value *= 3; break;
            case "史詩": value *= 5; break;
            case "傳說": value *= 10; break;
        }
        if (crystal.isEnchanted()) {
            value *= 1.5;
        }
        totalValue += value;

        System.out.println("=== 價值鑑定：魔法水晶 ===");
        System.out.println("名稱: " + crystal.getName());
        System.out.println("類型: " + crystal.getCrystalType());
        System.out.println("魔力值: " + crystal.getMagicPower());
        System.out.println("稀有度: " + crystal.getRarity());
        System.out.println("是否附魔: " + (crystal.isEnchanted() ? "是" : "否"));
        System.out.println("鑑定價值: " + (int)value + " 金幣");
        System.out.println();
    }

    @Override
    public void appraise(RarePlant plant) {
        int value = plant.getMedicinalValue() * 20;
        if (plant.getGrowthTime() > 100) {
            value *= 2; // 生長時間長的植物更珍貴
        }
        if ("深淵".equals(plant.getHabitat())) {
            value *= 3; // 深淵植物極為稀有
        }
        totalValue += value;

        System.out.println("=== 價值鑑定：珍稀植物 ===");
        System.out.println("名稱: " + plant.getName());
        System.out.println("品種: " + plant.getSpecies());
        System.out.println("藥用價值: " + plant.getMedicinalValue());
        System.out.println("棲息地: " + plant.getHabitat());
        System.out.println("生長時間: " + plant.getGrowthTime() + " 年");
        System.out.println("鑑定價值: " + value + " 金幣");
        System.out.println();
    }

    @Override
    public void appraise(EnchantedWeapon weapon) {
        int value = weapon.getDamage() * 50 + weapon.getDurability() * 10;
        if (weapon.getEnchantment().contains("火焰") || weapon.getEnchantment().contains("冰霜")) {
            value *= 1.8;
        }
        totalValue += value;

        System.out.println("=== 價值鑑定：附魔武器 ===");
        System.out.println("名稱: " + weapon.getName());
        System.out.println("類型: " + weapon.getWeaponType());
        System.out.println("攻擊力: " + weapon.getDamage());
        System.out.println("附魔效果: " + weapon.getEnchantment());
        System.out.println("耐久度: " + weapon.getDurability());
        System.out.println("鑑定價值: " + (int)value + " 金幣");
        System.out.println();
    }

    public void showTotalValue() {
        System.out.println("=== " + name + " 的鑑定總結 ===");
        System.out.println("總估值: " + totalValue + " 金幣");
        System.out.println();
    }

    public int getTotalValue() {
        return totalValue;
    }
}
```

```java
package file25;
//QualityAppraiser.java
// 品質鑑定師
public class QualityAppraiser implements Appraiser {
    private String name;
    private int itemsAppraised;

    public QualityAppraiser(String name) {
        this.name = name;
        this.itemsAppraised = 0;
    }

    @Override
    public void appraise(DwarfCraftsman craftsman) {
        itemsAppraised++;
        String quality = evaluateCraftsmanQuality(craftsman);
        
        System.out.println("=== 品質鑑定：矮人工匠 ===");
        System.out.println("姓名: " + craftsman.getName());
        System.out.println("技藝品質: " + quality);
        System.out.println("推薦用途: " + getRecommendedUse(craftsman));
        System.out.println();
    }

    @Override
    public void appraise(MagicCrystal crystal) {
        itemsAppraised++;
        String quality = evaluateCrystalQuality(crystal);
        
        System.out.println("=== 品質鑑定：魔法水晶 ===");
        System.out.println("名稱: " + crystal.getName());
        System.out.println("品質等級: " + quality);
        System.out.println("建議用途: " + getRecommendedUse(crystal));
        System.out.println("安全性評估: " + getSafetyRating(crystal));
        System.out.println();
    }

    @Override
    public void appraise(RarePlant plant) {
        itemsAppraised++;
        String quality = evaluatePlantQuality(plant);
        
        System.out.println("=== 品質鑑定：珍稀植物 ===");
        System.out.println("名稱: " + plant.getName());
        System.out.println("品質等級: " + quality);
        System.out.println("最佳採收期: " + getBestHarvestTime(plant));
        System.out.println("保存建議: " + getStorageAdvice(plant));
        System.out.println();
    }

    @Override
    public void appraise(EnchantedWeapon weapon) {
        itemsAppraised++;
        String quality = evaluateWeaponQuality(weapon);
        
        System.out.println("=== 品質鑑定：附魔武器 ===");
        System.out.println("名稱: " + weapon.getName());
        System.out.println("品質等級: " + quality);
        System.out.println("戰鬥效能: " + getCombatEffectiveness(weapon));
        System.out.println("維護建議: " + getMaintenanceAdvice(weapon));
        System.out.println();
    }

    // 私有輔助方法
    private String evaluateCraftsmanQuality(DwarfCraftsman craftsman) {
        int score = craftsman.getCraftingLevel() + craftsman.getYearsOfExperience() / 10;
        if (score >= 15) return "大師級";
        if (score >= 10) return "專家級";
        if (score >= 5) return "熟練級";
        return "學徒級";
    }

    private String evaluateCrystalQuality(MagicCrystal crystal) {
        if (crystal.getMagicPower() >= 80 && "傳說".equals(crystal.getRarity())) return "完美品質";
        if (crystal.getMagicPower() >= 60) return "優秀品質";
        if (crystal.getMagicPower() >= 40) return "良好品質";
        return "普通品質";
    }

    private String evaluatePlantQuality(RarePlant plant) {
        if (plant.getMedicinalValue() >= 80) return "頂級品質";
        if (plant.getMedicinalValue() >= 60) return "高級品質";
        if (plant.getMedicinalValue() >= 40) return "中級品質";
        return "初級品質";
    }

    private String evaluateWeaponQuality(EnchantedWeapon weapon) {
        int score = weapon.getDamage() + weapon.getDurability() / 10;
        if (score >= 120) return "傳說品質";
        if (score >= 80) return "史詩品質";
        if (score >= 50) return "稀有品質";
        return "普通品質";
    }

    private String getRecommendedUse(DwarfCraftsman craftsman) {
        if ("傳奇工藝".equals(craftsman.getSpecialty())) return "皇室御用工匠";
        return "城鎮工坊負責人";
    }

    private String getRecommendedUse(MagicCrystal crystal) {
        if (crystal.getMagicPower() >= 70) return "高階法杖核心";
        return "魔法裝備強化材料";
    }

    private String getBestHarvestTime(RarePlant plant) {
        return "滿月之夜";
    }

    private String getStorageAdvice(RarePlant plant) {
        return "陰涼乾燥處保存，避免陽光直射";
    }

    private String getSafetyRating(MagicCrystal crystal) {
        return crystal.getMagicPower() > 90 ? "需要專業防護" : "相對安全";
    }

    private String getCombatEffectiveness(EnchantedWeapon weapon) {
        if (weapon.getDamage() >= 100) return "極高";
        if (weapon.getDamage() >= 70) return "高";
        return "中等";
    }

    private String getMaintenanceAdvice(EnchantedWeapon weapon) {
        return weapon.getDurability() < 50 ? "需要立即維修" : "定期保養即可";
    }

    public void showAppraisalSummary() {
        System.out.println("=== " + name + " 的鑑定總結 ===");
        System.out.println("已鑑定物品數量: " + itemsAppraised);
        System.out.println();
    }
}
```

```java
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
```

```java
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

        /**output
        === 歡迎來到世界巡遊鑑定師的魔法市集 ===

        新商品入場: 鋼鬍巴林
        新商品入場: 銅斧索林
        新商品入場: 暴風之眼
        新商品入場: 微光石
        新商品入場: 千年血蓮
        新商品入場: 月光草
        新商品入場: 烈焰之劍
        新商品入場: 冰霜匕首

        === 奇蹟商會 市集資訊 ===
        目前商品數量: 8
        1. 鋼鬍巴林
        2. 銅斧索林
        3. 暴風之眼
        4. 微光石
        5. 千年血蓮
        6. 月光草
        7. 烈焰之劍
        8. 冰霜匕首

        ========================================
        第一輪：價值鑑定
        ========================================
        === 歡迎鑑定師來到 奇蹟商會 ===
        開始鑑定市集中的所有物品...

        === 價值鑑定：矮人工匠 ===
        姓名: 鋼鬍巴林
        工藝等級: 12
        專精: 傳奇工藝
        經驗年數: 150
        鑑定價值: 30000 金幣

        === 價值鑑定：矮人工匠 ===
        姓名: 銅斧索林
        工藝等級: 8
        專精: 普通工藝
        經驗年數: 80
        鑑定價值: 4800 金幣

        === 價值鑑定：魔法水晶 ===
        名稱: 暴風之眼
        類型: 風系水晶
        魔力值: 85
        稀有度: 傳說
        是否附魔: 是
        鑑定價值: 12750 金幣

        === 價值鑑定：魔法水晶 ===
        名稱: 微光石
        類型: 光系水晶
        魔力值: 45
        稀有度: 稀有
        是否附魔: 否
        鑑定價值: 1350 金幣

        === 價值鑑定：珍稀植物 ===
        名稱: 千年血蓮
        品種: 蓮花科
        藥用價值: 90
        棲息地: 深淵
        生長時間: 1000 年
        鑑定價值: 10800 金幣

        === 價值鑑定：珍稀植物 ===
        名稱: 月光草
        品種: 草本科
        藥用價值: 35
        棲息地: 森林
        生長時間: 10 年
        鑑定價值: 700 金幣

        === 價值鑑定：附魔武器 ===
        名稱: 烈焰之劍
        類型: 長劍
        攻擊力: 95
        附魔效果: 火焰傷害+30
        耐久度: 85
        鑑定價值: 9390 金幣

        === 價值鑑定：附魔武器 ===
        名稱: 冰霜匕首
        類型: 匕首
        攻擊力: 60
        附魔效果: 冰霜減速
        耐久度: 70
        鑑定價值: 6140 金幣

        === 黃金眼商人 的鑑定總結 ===
        總估值: 75930 金幣

        ========================================
        第二輪：品質鑑定
        ========================================
        === 歡迎鑑定師來到 奇蹟商會 ===
        開始鑑定市集中的所有物品...

        === 品質鑑定：矮人工匠 ===
        姓名: 鋼鬍巴林
        技藝品質: 大師級
        推薦用途: 皇室御用工匠

        === 品質鑑定：矮人工匠 ===
        姓名: 銅斧索林
        技藝品質: 專家級
        推薦用途: 城鎮工坊負責人

        === 品質鑑定：魔法水晶 ===
        名稱: 暴風之眼
        品質等級: 完美品質
        建議用途: 高階法杖核心
        安全性評估: 需要專業防護

        === 品質鑑定：魔法水晶 ===
        名稱: 微光石
        品質等級: 良好品質
        建議用途: 魔法裝備強化材料
        安全性評估: 相對安全

        === 品質鑑定：珍稀植物 ===
        名稱: 千年血蓮
        品質等級: 頂級品質
        最佳採收期: 滿月之夜
        保存建議: 陰涼乾燥處保存，避免陽光直射

        === 品質鑑定：珍稀植物 ===
        名稱: 月光草
        品質等級: 初級品質
        最佳採收期: 滿月之夜
        保存建議: 陰涼乾燥處保存，避免陽光直射

        === 品質鑑定：附魔武器 ===
        名稱: 烈焰之劍
        品質等級: 傳說品質
        戰鬥效能: 極高
        維護建議: 定期保養即可

        === 品質鑑定：附魔武器 ===
        名稱: 冰霜匕首
        品質等級: 稀有品質
        戰鬥效能: 中等
        維護建議: 定期保養即可

        === 品質大師 的鑑定總結 ===
        已鑑定物品數量: 8

        === 鑑定完成 ===
        感謝世界巡遊鑑定師的專業服務！
        */
    }
}
```