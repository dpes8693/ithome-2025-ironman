package file4;
// 戰士裝備具體工廠
public class WarriorEquipmentFactory extends DwarvenEquipmentFactory {
    @Override
    public Weapon createWeapon() {
        System.out.println("鍛造戰士武器...");
        return new WarriorSword();
    }

    @Override
    public Armor createArmor() {
        System.out.println("鍛造戰士護甲...");
        return new WarriorShield();
    }

    @Override
    public Accessory createAccessory() {
        System.out.println("鍛造戰士配飾...");
        return new WarriorGauntlet();
    }
}