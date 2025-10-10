package file2;

// 使用範例
public class SimpleFactoryExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到地精工匠的工作坊 ===\n");

        // 展示工匠技能
        GoblinWeaponFactory.showCraftingSkills();
        System.out.println();

        // 創建不同類型的武器
        Weapon sword = GoblinWeaponFactory.createWeapon("sword");
        Weapon bow = GoblinWeaponFactory.createWeapon("bow");
        Weapon staff = GoblinWeaponFactory.createWeapon("staff");
        Weapon unknown = GoblinWeaponFactory.createWeapon("hammer");

        System.out.println();

        // 測試武器功能
        if (sword != null) {
            System.out.println("獲得武器: " + sword.getType());
            sword.use();
        }

        if (bow != null) {
            System.out.println("獲得武器: " + bow.getType());
            bow.use();
        }

        if (staff != null) {
            System.out.println("獲得武器: " + staff.getType());
            staff.use();
        }

        if (unknown == null) {
            System.out.println("無法製作未知武器");
        }

        /**output
        === 歡迎來到地精工匠的工作坊 ===

        === 地精工匠的技能清單 ===
        1. 新手長劍 (sword)
        2. 精靈短弓 (bow)
        3. 魔法法杖 (staff)
        告訴我你想要什麼，我馬上給你變出來！

        地精工匠開始製作: sword
        地精工匠開始製作: bow
        地精工匠開始製作: staff
        地精工匠開始製作: hammer
        抱歉，我還不會製作這種武器: hammer

        獲得武器: 新手長劍
        揮舞長劍，劃出優美的弧線！
        獲得武器: 精靈短弓
        拉滿弓弦，射出精準的箭矢！
        獲得武器: 魔法法杖
        舉起法杖，詠唱出強力的魔法！
        無法製作未知武器
        */
    }
}
