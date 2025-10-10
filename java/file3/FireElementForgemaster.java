package file3;

//FireElementForgemaster.java
// 火元素精靈鑄造師
public class FireElementForgemaster extends ElfForgemaster {
    @Override
    protected MagicWeapon createMagicWeapon() {
        System.out.println("召喚火元素精靈協助鍛造...");
        return new FireSword();
    }
}