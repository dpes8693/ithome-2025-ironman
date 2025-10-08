# Day25 訪客模式 (Visitor Pattern)

## 擬人化角色：【世界巡遊的鑑定師】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/24-Visitor.png)

- 種族： 人類/遊學者
- 外貌： 一位身穿旅行長袍、帶著微笑，看起來經驗豐富的長者。他正站在一個充滿各種奇特生物和物品的魔法商店或工作室中央。周圍環繞著發光的資訊介面，這些介面顯示著不同的生物（矮人工匠、地精商人、哥布林小偷，甚至是角落裡的盔甲傀儡）和物品（珍稀植物、魔法水晶、古老卷軸等）的詳細資訊。他正伸出雙手，仿佛在引導或鑑定這些元素。
- 性格： 開明、好奇、專業且不拘泥。他能夠以統一的方式「訪問」一個複雜集合中的不同類型元素，並根據每個元素的實際類別執行特定且獨立的行為，而無需修改元素本身的代碼。
- 能力： 使用不同的訪客使集合 (Collection) 中的元素行為與元素類別切離。這位鑑定師（訪客）可以走訪一個集合（例如一個綜合性的魔法市場或一支多元的冒險隊伍）。當他「訪問」一個「矮人工匠」時，他會評估其鍛造技藝；當他「訪問」一個「珍稀植物」時，他會鑑定其藥用價值；當他「訪問」一個「魔法水晶」時，他會測試其魔力儲量。關鍵在於，這些「鑑定行為」都是由鑑定師（訪客）定義的，而不是由矮人、植物或水晶本身定義的，這使得鑑定師可以輕鬆地添加新的鑑定行為，而無需修改被鑑定的元素。
- 代表語： 「萬物皆有其本，我來為你揭示。」
- 背景故事： 這位享譽世界的鑑定師，因其能辨識並評估世間萬物的能力而聞名。他經常受邀去各地的魔法市集、遺跡探險隊或貴族收藏庫進行鑑定。他的獨特之處在於，無論面對的是什麼物品或生物，他都有一套通用的「訪問」方法。然而，一旦他「接觸」到某個具體的物件（例如，一把被附魔的劍），他會立即根據這把劍的類型（武器、魔法物品、鋼鐵材質等），執行一套針對性的鑑定流程（測試鋒利度、探測魔力、檢查材質）。這種模式讓他能高效地應對各種複雜的鑑定需求，同時也讓被鑑定的物件保持其原有的獨立性，無需為適應鑑定而修改自身。

---

## 範例

### Java

```java
//Appraisable.java
// 可鑑定元素介面
public interface Appraisable {
    void accept(Appraiser visitor);
    String getName();
}
```

```java
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

### JavaScript

```javascript
// 可鑑定元素基類
class Appraisable {
  constructor(name) {
    this.name = name;
  }

  accept(visitor) {
    throw new Error("子類必須實現 accept 方法");
  }

  getName() {
    return this.name;
  }
}

// 矮人工匠
class DwarfCraftsman extends Appraisable {
  constructor(name, craftingLevel, specialty, yearsOfExperience) {
    super(name);
    this.craftingLevel = craftingLevel;
    this.specialty = specialty;
    this.yearsOfExperience = yearsOfExperience;
  }

  accept(visitor) {
    visitor.appraise(this);
  }

  getCraftingLevel() {
    return this.craftingLevel;
  }

  getSpecialty() {
    return this.specialty;
  }

  getYearsOfExperience() {
    return this.yearsOfExperience;
  }
}

// 魔法水晶
class MagicCrystal extends Appraisable {
  constructor(name, crystalType, magicPower, rarity, isEnchanted) {
    super(name);
    this.crystalType = crystalType;
    this.magicPower = magicPower;
    this.rarity = rarity;
    this.isEnchanted = isEnchanted;
  }

  accept(visitor) {
    visitor.appraise(this);
  }

  getCrystalType() {
    return this.crystalType;
  }

  getMagicPower() {
    return this.magicPower;
  }

  getRarity() {
    return this.rarity;
  }

  getIsEnchanted() {
    return this.isEnchanted;
  }
}

// 珍稀植物
class RarePlant extends Appraisable {
  constructor(name, species, medicinalValue, habitat, growthTime) {
    super(name);
    this.species = species;
    this.medicinalValue = medicinalValue;
    this.habitat = habitat;
    this.growthTime = growthTime;
  }

  accept(visitor) {
    visitor.appraise(this);
  }

  getSpecies() {
    return this.species;
  }

  getMedicinalValue() {
    return this.medicinalValue;
  }

  getHabitat() {
    return this.habitat;
  }

  getGrowthTime() {
    return this.growthTime;
  }
}

// 附魔武器
class EnchantedWeapon extends Appraisable {
  constructor(name, weaponType, damage, enchantment, durability) {
    super(name);
    this.weaponType = weaponType;
    this.damage = damage;
    this.enchantment = enchantment;
    this.durability = durability;
  }

  accept(visitor) {
    visitor.appraise(this);
  }

  getWeaponType() {
    return this.weaponType;
  }

  getDamage() {
    return this.damage;
  }

  getEnchantment() {
    return this.enchantment;
  }

  getDurability() {
    return this.durability;
  }
}

// 價值鑑定師
class ValueAppraiser {
  constructor(name) {
    this.name = name;
    this.totalValue = 0;
  }

  appraise(item) {
    if (item instanceof DwarfCraftsman) {
      this.appraiseCraftsman(item);
    } else if (item instanceof MagicCrystal) {
      this.appraiseCrystal(item);
    } else if (item instanceof RarePlant) {
      this.appraisePlant(item);
    } else if (item instanceof EnchantedWeapon) {
      this.appraiseWeapon(item);
    }
  }

  appraiseCraftsman(craftsman) {
    let value = craftsman.getCraftingLevel() * 100 + craftsman.getYearsOfExperience() * 50;
    if (craftsman.getSpecialty() === "傳奇工藝") {
      value *= 2;
    }
    this.totalValue += value;
    
    console.log("=== 價值鑑定：矮人工匠 ===");
    console.log(`姓名: ${craftsman.getName()}`);
    console.log(`工藝等級: ${craftsman.getCraftingLevel()}`);
    console.log(`專精: ${craftsman.getSpecialty()}`);
    console.log(`經驗年數: ${craftsman.getYearsOfExperience()}`);
    console.log(`鑑定價值: ${value} 金幣`);
    console.log();
  }

  appraiseCrystal(crystal) {
    let value = crystal.getMagicPower() * 10;
    const rarityMultiplier = {
      "普通": 1,
      "稀有": 3,
      "史詩": 5,
      "傳說": 10
    };
    value *= (rarityMultiplier[crystal.getRarity()] || 1);
    if (crystal.getIsEnchanted()) {
      value *= 1.5;
    }
    this.totalValue += value;

    console.log("=== 價值鑑定：魔法水晶 ===");
    console.log(`名稱: ${crystal.getName()}`);
    console.log(`類型: ${crystal.getCrystalType()}`);
    console.log(`魔力值: ${crystal.getMagicPower()}`);
    console.log(`稀有度: ${crystal.getRarity()}`);
    console.log(`是否附魔: ${crystal.getIsEnchanted() ? "是" : "否"}`);
    console.log(`鑑定價值: ${Math.floor(value)} 金幣`);
    console.log();
  }

  appraisePlant(plant) {
    let value = plant.getMedicinalValue() * 20;
    if (plant.getGrowthTime() > 100) {
      value *= 2;
    }
    if (plant.getHabitat() === "深淵") {
      value *= 3;
    }
    this.totalValue += value;

    console.log("=== 價值鑑定：珍稀植物 ===");
    console.log(`名稱: ${plant.getName()}`);
    console.log(`品種: ${plant.getSpecies()}`);
    console.log(`藥用價值: ${plant.getMedicinalValue()}`);
    console.log(`棲息地: ${plant.getHabitat()}`);
    console.log(`生長時間: ${plant.getGrowthTime()} 年`);
    console.log(`鑑定價值: ${value} 金幣`);
    console.log();
  }

  appraiseWeapon(weapon) {
    let value = weapon.getDamage() * 50 + weapon.getDurability() * 10;
    if (weapon.getEnchantment().includes("火焰") || weapon.getEnchantment().includes("冰霜")) {
      value *= 1.8;
    }
    this.totalValue += value;

    console.log("=== 價值鑑定：附魔武器 ===");
    console.log(`名稱: ${weapon.getName()}`);
    console.log(`類型: ${weapon.getWeaponType()}`);
    console.log(`攻擊力: ${weapon.getDamage()}`);
    console.log(`附魔效果: ${weapon.getEnchantment()}`);
    console.log(`耐久度: ${weapon.getDurability()}`);
    console.log(`鑑定價值: ${Math.floor(value)} 金幣`);
    console.log();
  }

  showTotalValue() {
    console.log(`=== ${this.name} 的鑑定總結 ===`);
    console.log(`總估值: ${this.totalValue} 金幣`);
    console.log();
  }

  getTotalValue() {
    return this.totalValue;
  }
}

// 品質鑑定師
class QualityAppraiser {
  constructor(name) {
    this.name = name;
    this.itemsAppraised = 0;
  }

  appraise(item) {
    if (item instanceof DwarfCraftsman) {
      this.appraiseCraftsman(item);
    } else if (item instanceof MagicCrystal) {
      this.appraiseCrystal(item);
    } else if (item instanceof RarePlant) {
      this.appraisePlant(item);
    } else if (item instanceof EnchantedWeapon) {
      this.appraiseWeapon(item);
    }
  }

  appraiseCraftsman(craftsman) {
    this.itemsAppraised++;
    const quality = this.evaluateCraftsmanQuality(craftsman);
    
    console.log("=== 品質鑑定：矮人工匠 ===");
    console.log(`姓名: ${craftsman.getName()}`);
    console.log(`技藝品質: ${quality}`);
    console.log(`推薦用途: ${this.getRecommendedUse(craftsman)}`);
    console.log();
  }

  appraiseCrystal(crystal) {
    this.itemsAppraised++;
    const quality = this.evaluateCrystalQuality(crystal);
    
    console.log("=== 品質鑑定：魔法水晶 ===");
    console.log(`名稱: ${crystal.getName()}`);
    console.log(`品質等級: ${quality}`);
    console.log(`建議用途: ${this.getRecommendedUseForCrystal(crystal)}`);
    console.log(`安全性評估: ${this.getSafetyRating(crystal)}`);
    console.log();
  }

  appraisePlant(plant) {
    this.itemsAppraised++;
    const quality = this.evaluatePlantQuality(plant);
    
    console.log("=== 品質鑑定：珍稀植物 ===");
    console.log(`名稱: ${plant.getName()}`);
    console.log(`品質等級: ${quality}`);
    console.log(`最佳採收期: 滿月之夜`);
    console.log(`保存建議: 陰涼乾燥處保存，避免陽光直射`);
    console.log();
  }

  appraiseWeapon(weapon) {
    this.itemsAppraised++;
    const quality = this.evaluateWeaponQuality(weapon);
    
    console.log("=== 品質鑑定：附魔武器 ===");
    console.log(`名稱: ${weapon.getName()}`);
    console.log(`品質等級: ${quality}`);
    console.log(`戰鬥效能: ${this.getCombatEffectiveness(weapon)}`);
    console.log(`維護建議: ${this.getMaintenanceAdvice(weapon)}`);
    console.log();
  }

  // 輔助方法
  evaluateCraftsmanQuality(craftsman) {
    const score = craftsman.getCraftingLevel() + Math.floor(craftsman.getYearsOfExperience() / 10);
    if (score >= 15) return "大師級";
    if (score >= 10) return "專家級";
    if (score >= 5) return "熟練級";
    return "學徒級";
  }

  evaluateCrystalQuality(crystal) {
    if (crystal.getMagicPower() >= 80 && crystal.getRarity() === "傳說") return "完美品質";
    if (crystal.getMagicPower() >= 60) return "優秀品質";
    if (crystal.getMagicPower() >= 40) return "良好品質";
    return "普通品質";
  }

  evaluatePlantQuality(plant) {
    if (plant.getMedicinalValue() >= 80) return "頂級品質";
    if (plant.getMedicinalValue() >= 60) return "高級品質";
    if (plant.getMedicinalValue() >= 40) return "中級品質";
    return "初級品質";
  }

  evaluateWeaponQuality(weapon) {
    const score = weapon.getDamage() + Math.floor(weapon.getDurability() / 10);
    if (score >= 120) return "傳說品質";
    if (score >= 80) return "史詩品質";
    if (score >= 50) return "稀有品質";
    return "普通品質";
  }

  getRecommendedUse(craftsman) {
    return craftsman.getSpecialty() === "傳奇工藝" ? "皇室御用工匠" : "城鎮工坊負責人";
  }

  getRecommendedUseForCrystal(crystal) {
    return crystal.getMagicPower() >= 70 ? "高階法杖核心" : "魔法裝備強化材料";
  }

  getSafetyRating(crystal) {
    return crystal.getMagicPower() > 90 ? "需要專業防護" : "相對安全";
  }

  getCombatEffectiveness(weapon) {
    if (weapon.getDamage() >= 100) return "極高";
    if (weapon.getDamage() >= 70) return "高";
    return "中等";
  }

  getMaintenanceAdvice(weapon) {
    return weapon.getDurability() < 50 ? "需要立即維修" : "定期保養即可";
  }

  showAppraisalSummary() {
    console.log(`=== ${this.name} 的鑑定總結 ===`);
    console.log(`已鑑定物品數量: ${this.itemsAppraised}`);
    console.log();
  }
}

// 魔法市集
class MagicMarket {
  constructor(marketName) {
    this.marketName = marketName;
    this.items = [];
  }

  addItem(item) {
    this.items.push(item);
    console.log(`新商品入場: ${item.getName()}`);
  }

  removeItem(item) {
    const index = this.items.indexOf(item);
    if (index > -1) {
      this.items.splice(index, 1);
      console.log(`商品離場: ${item.getName()}`);
    }
  }

  acceptAppraiser(appraiser) {
    console.log(`=== 歡迎鑑定師來到 ${this.marketName} ===`);
    console.log("開始鑑定市集中的所有物品...\n");

    for (const item of this.items) {
      item.accept(appraiser);
    }

    // 顯示鑑定總結
    if (appraiser instanceof ValueAppraiser) {
      appraiser.showTotalValue();
    } else if (appraiser instanceof QualityAppraiser) {
      appraiser.showAppraisalSummary();
    }
  }

  showMarketInfo() {
    console.log(`=== ${this.marketName} 市集資訊 ===`);
    console.log(`目前商品數量: ${this.items.length}`);
    this.items.forEach((item, index) => {
      console.log(`${index + 1}. ${item.getName()}`);
    });
    console.log();
  }

  getItemCount() {
    return this.items.length;
  }
}

// 使用範例
console.log("=== 歡迎來到世界巡遊鑑定師的魔法市集 ===\n");

// 創建魔法市集
const market = new MagicMarket("奇蹟商會");

// 創建各種待鑑定的物品和人物
const dwarf1 = new DwarfCraftsman("鋼鬍巴林", 12, "傳奇工藝", 150);
const dwarf2 = new DwarfCraftsman("銅斧索林", 8, "普通工藝", 80);

const crystal1 = new MagicCrystal("暴風之眼", "風系水晶", 85, "傳說", true);
const crystal2 = new MagicCrystal("微光石", "光系水晶", 45, "稀有", false);

const plant1 = new RarePlant("千年血蓮", "蓮花科", 90, "深淵", 1000);
const plant2 = new RarePlant("月光草", "草本科", 35, "森林", 10);

const weapon1 = new EnchantedWeapon("烈焰之劍", "長劍", 95, "火焰傷害+30", 85);
const weapon2 = new EnchantedWeapon("冰霜匕首", "匕首", 60, "冰霜減速", 70);

// 將物品加入市集
market.addItem(dwarf1);
market.addItem(dwarf2);
market.addItem(crystal1);
market.addItem(crystal2);
market.addItem(plant1);
market.addItem(plant2);
market.addItem(weapon1);
market.addItem(weapon2);

console.log();
market.showMarketInfo();

// 創建不同類型的鑑定師
const valueAppraiser = new ValueAppraiser("黃金眼商人");
const qualityAppraiser = new QualityAppraiser("品質大師");

// 進行價值鑑定
console.log("========================================");
console.log("第一輪：價值鑑定");
console.log("========================================");
market.acceptAppraiser(valueAppraiser);

// 進行品質鑑定
console.log("========================================");
console.log("第二輪：品質鑑定");
console.log("========================================");
market.acceptAppraiser(qualityAppraiser);

console.log("=== 鑑定完成 ===");
console.log("感謝世界巡遊鑑定師的專業服務！");

/** output
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
```

## 小總結

Visitor 設計模式就像我們故事中的世界巡遊鑑定師，透過`訪客物件`來對不同類型的元素執行特定操作

**核心特點：**

- **操作與結構分離**：將操作邏輯從資料結構中分離出來
- **雙重分派**：透過 accept 方法實現正確的方法調用
- **擴展性**：容易添加新的操作，無需修改現有元素類別
- **統一介面**：提供統一的方式處理不同類型的物件

**使用時機：**

- 需要對一組不同類型的物件執行相似操作（ex: 編譯器的語法分析）
- 操作經常變化但物件結構相對穩定（ex: 文件格式轉換器）
- 需要在不修改物件的情況下添加新功能（ex: 報表生成系統）
- 想要將相關的操作集中在一個訪客類別中（ex: 資料驗證器）

**注意事項：**

- 添加新的元素類型比較困難，需要修改所有訪客介面
- 破壞了封裝性，訪客需要存取元素的內部狀態
- 元素類別必須暴露足夠的介面給訪客使用
- 適合用於物件結構穩定但操作多變的場景
- 雙重分派機制增加了程式碼複雜度，但提供了強大的靈活性
