package file4;
// 矮人裝備抽象工廠
public abstract class DwarvenEquipmentFactory {
    // 抽象工廠方法 - 創建武器
    public abstract Weapon createWeapon();

    // 抽象工廠方法 - 創建護甲
    public abstract Armor createArmor();

    // 抽象工廠方法 - 創建配飾
    public abstract Accessory createAccessory();

    // 模板方法 - 創建完整套裝
    public final void createCompleteSet() {
        System.out.println("=== 矮人宗師開始鍛造套裝 ===");

        Weapon weapon = createWeapon();
        Armor armor = createArmor();
        Accessory accessory = createAccessory();

        System.out.println("套裝鍛造完成！");
        System.out.println("風格: " + weapon.getStyle());
        System.out.println("包含: " + weapon.getName() + "、" +
                          armor.getName() + "、" + accessory.getName());

        // 測試套裝
        System.out.println("\n=== 套裝測試 ===");
        weapon.attack();
        armor.defend();
        accessory.enhance();
        System.out.println();
    }

    // 靜態工廠方法 - 根據類型創建工廠
    public static DwarvenEquipmentFactory getFactory(String type) {
        switch (type.toLowerCase()) {
            case "warrior":
            case "戰士":
                return new WarriorEquipmentFactory();
            case "mage":
            case "法師":
                return new MageEquipmentFactory();
            default:
                throw new IllegalArgumentException("未知的裝備類型: " + type);
        }
    }
}