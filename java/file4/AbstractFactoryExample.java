package file4;
// 使用範例
public class AbstractFactoryExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到矮人宗師的裝備工坊 ===\n");

        try {
            // 創建戰士套裝
            System.out.println(">>> 創建戰士套裝 <<<");
            DwarvenEquipmentFactory warriorFactory =
                DwarvenEquipmentFactory.getFactory("warrior");
            warriorFactory.createCompleteSet();

            // 創建法師套裝
            System.out.println(">>> 創建法師套裝 <<<");
            DwarvenEquipmentFactory mageFactory =
                DwarvenEquipmentFactory.getFactory("mage");
            mageFactory.createCompleteSet();

            // 示範客戶端如何使用
            demonstrateClientUsage();

        } catch (IllegalArgumentException e) {
            System.out.println("錯誤: " + e.getMessage());
        }
    }

    private static void demonstrateClientUsage() {
        System.out.println("=== 客戶端使用示範 ===");

        // 客戶端不需要知道具體的產品類別
        DwarvenEquipmentFactory factory = DwarvenEquipmentFactory.getFactory("warrior");

        Weapon weapon = factory.createWeapon();
        Armor armor = factory.createArmor();

        System.out.println("獲得 " + weapon.getName() + " 和 " + armor.getName());
        System.out.println("確認風格一致: " + weapon.getStyle().equals(armor.getStyle()));
    }
}