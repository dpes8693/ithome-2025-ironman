// 基礎火球術
package file6;

public class BasicFireball implements Spell {
    @Override
    public void cast() {
        System.out.println("施展基礎火球術！");
    }

    @Override
    public String getDescription() {
        return "基礎火球術";
    }

    @Override
    public int getPower() {
        return 50;
    }
}