package file3;

//FireSword.java
// 火焰劍
public class FireSword implements MagicWeapon {
    @Override
    public void enchant() {
        System.out.println("火元素精靈注入烈焰之力...");
    }

    @Override
    public void use() {
        System.out.println("火焰劍爆發出炙熱的劍氣，灼燒敵人！");
    }

    @Override
    public String getElement() {
        return "火焰";
    }

    @Override
    public String getType() {
        return "烈焰長劍";
    }
}