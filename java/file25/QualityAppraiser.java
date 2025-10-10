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
        return "陰涼乾燥處保存,避免陽光直射";
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
