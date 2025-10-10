package file3;

//EarthElementForgemaster.java
// 土元素精靈鑄造師
public class EarthElementForgemaster extends ElfForgemaster {
    @Override
    protected MagicWeapon createMagicWeapon() {
        System.out.println("召喚土元素精靈協助鍛造...");
        return new EarthStaff();
    }
}