package file3;

//EarthStaff.java
// 大地法杖
public class EarthStaff implements MagicWeapon {
    @Override
    public void enchant() {
        System.out.println("土元素精靈凝聚大地之力...");
    }

    @Override
    public void use() {
        System.out.println("大地法杖召喚岩石風暴，震撼戰場！");
    }

    @Override
    public String getElement() {
        return "大地";
    }

    @Override
    public String getType() {
        return "磐石法杖";
    }
}