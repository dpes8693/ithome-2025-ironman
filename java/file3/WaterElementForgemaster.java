package file3;

//WaterElementForgemaster.java
// 水元素精靈鑄造師
public class WaterElementForgemaster extends ElfForgemaster {
    @Override
    protected MagicWeapon createMagicWeapon() {
        System.out.println("召喚水元素精靈協助鍛造...");
        return new WaterBow();
    }
}