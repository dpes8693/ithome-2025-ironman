package file2;

// 地精工匠的武器工廠
public class GoblinWeaponFactory {

    // 工廠方法：根據類型創建武器
    public static Weapon createWeapon(String weaponType) {
        if (weaponType == null || weaponType.isEmpty()) {
            return null;
        }

        System.out.println("地精工匠開始製作: " + weaponType);

        switch (weaponType.toLowerCase()) {
            case "sword":
                return new Sword();
            case "bow":
                return new Bow();
            case "staff":
                return new Staff();
            default:
                System.out.println("抱歉，我還不會製作這種武器: " + weaponType);
                return null;
        }
    }

    // 展示工匠的能力
    public static void showCraftingSkills() {
        System.out.println("=== 地精工匠的技能清單 ===");
        System.out.println("1. 新手長劍 (sword)");
        System.out.println("2. 精靈短弓 (bow)");
        System.out.println("3. 魔法法杖 (staff)");
        System.out.println("告訴我你想要什麼，我馬上給你變出來！");
    }
}