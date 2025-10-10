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
