package file3;

//WaterBow.java
// 水流弓
public class WaterBow implements MagicWeapon {
    @Override
    public void enchant() {
        System.out.println("水元素精靈灌注流水之韻...");
    }

    @Override
    public void use() {
        System.out.println("水流弓射出冰冷的箭矢，穿透敵人！");
    }

    @Override
    public String getElement() {
        return "水流";
    }

    @Override
    public String getType() {
        return "潮汐長弓";
    }
}