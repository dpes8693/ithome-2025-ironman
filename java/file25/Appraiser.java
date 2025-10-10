package file25;
//Appraiser.java
// 鑑定師訪客介面
public interface Appraiser {
    void appraise(DwarfCraftsman craftsman);
    void appraise(MagicCrystal crystal);
    void appraise(RarePlant plant);
    void appraise(EnchantedWeapon weapon);
}
