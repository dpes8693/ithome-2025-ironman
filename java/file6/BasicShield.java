// 基礎防護盾
package file6;

public class BasicShield implements Spell {
    @Override
    public void cast() {
        System.out.println("展開基礎防護盾！");
    }

    @Override
    public String getDescription() {
        return "基礎防護盾";
    }

    @Override
    public int getPower() {
        return 30;
    }
}