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
