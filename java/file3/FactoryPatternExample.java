package file3;

//FactoryPatternExample.java
// 使用範例
public class FactoryPatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到精靈王國的魔法工坊 ===\n");

        // 創建不同的精靈鑄造師
        ElfForgemaster fireForgemaster = new FireElementForgemaster();
        ElfForgemaster waterForgemaster = new WaterElementForgemaster();
        ElfForgemaster earthForgemaster = new EarthElementForgemaster();

        // 讓每個鑄造師鍛造專屬武器
        MagicWeapon fireSword = fireForgemaster.forgeWeapon();
        MagicWeapon waterBow = waterForgemaster.forgeWeapon();
        MagicWeapon earthStaff = earthForgemaster.forgeWeapon();

        // 測試武器功能
        System.out.println("=== 武器測試 ===");
        testWeapon(fireSword);
        testWeapon(waterBow);
        testWeapon(earthStaff);
    }

    private static void testWeapon(MagicWeapon weapon) {
        System.out.println("測試武器: " + weapon.getType() + " (" + weapon.getElement() + "屬性)");
        weapon.use();
        System.out.println();
    }

    /**output
    === 歡迎來到精靈王國的魔法工坊 ===

    === 精靈鑄造師開始工作 ===
    準備魔法工坊，點燃元素火焰...
    召喚火元素精靈協助鍛造...
    火元素精靈注入烈焰之力...
    為 烈焰長劍 加上精靈祝福
    武器鍛造完成！

    === 精靈鑄造師開始工作 ===
    準備魔法工坊，點燃元素火焰...
    召喚水元素精靈協助鍛造...
    水元素精靈灌注流水之韻...
    為 潮汐長弓 加上精靈祝福
    武器鍛造完成！

    === 精靈鑄造師開始工作 ===
    準備魔法工坊，點燃元素火焰...
    召喚土元素精靈協助鍛造...
    土元素精靈凝聚大地之力...
    為 磐石法杖 加上精靈祝福
    武器鍛造完成！

    === 武器測試 ===
    測試武器: 烈焰長劍 (火焰屬性)
    火焰劍爆發出炙熱的劍氣，灼燒敵人！

    測試武器: 潮汐長弓 (水流屬性)
    水流弓射出冰冷的箭矢，穿透敵人！

    測試武器: 磐石法杖 (大地屬性)
    大地法杖召喚岩石風暴，震撼戰場！

    */
}