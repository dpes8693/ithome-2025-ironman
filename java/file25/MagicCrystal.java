package file25;
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
