package file3;

//ElfForgemaster.java
// 精靈鑄造師抽象類別
public abstract class ElfForgemaster {

    // 工廠方法 - 由子類別實現具體的創建邏輯
    protected abstract MagicWeapon createMagicWeapon();

    // 模板方法 - 定義武器製作的完整流程
    public final MagicWeapon forgeWeapon() {
        System.out.println("=== 精靈鑄造師開始工作 ===");

        // 準備工作
        prepareWorkshop();

        // 委託給子類別創建具體武器
        MagicWeapon weapon = createMagicWeapon();

        // 統一的後處理
        weapon.enchant();
        finalizeWeapon(weapon);

        return weapon;
    }

    private void prepareWorkshop() {
        System.out.println("準備魔法工坊，點燃元素火焰...");
    }

    private void finalizeWeapon(MagicWeapon weapon) {
        System.out.println("為 " + weapon.getType() + " 加上精靈祝福");
        System.out.println("武器鍛造完成！\n");
    }
}