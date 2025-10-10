package file4;
// 法師裝備具體工廠
public class MageEquipmentFactory extends DwarvenEquipmentFactory {
    @Override
    public Weapon createWeapon() {
        System.out.println("鍛造法師武器...");
        return new MageStaff();
    }

    @Override
    public Armor createArmor() {
        System.out.println("鍛造法師護甲...");
        return new MageRobe();
    }

    @Override
    public Accessory createAccessory() {
        System.out.println("鍛造法師配飾...");
        return new MageRing();
    }
}