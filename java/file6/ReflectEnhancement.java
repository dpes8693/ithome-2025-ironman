// 反彈強化裝飾器
package file6;

public class ReflectEnhancement extends SpellDecorator {
    public ReflectEnhancement(Spell spell) {
        super(spell);
    }

    @Override
    public void cast() {
        super.cast();
        System.out.println("→ 附加反彈效果，可以反射敵人攻擊！");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + 反彈強化";
    }

    @Override
    public int getPower() {
        return super.getPower() + 25;
    }
}