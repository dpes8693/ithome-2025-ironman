// 追蹤強化裝飾器
package file6;

public class TrackingEnhancement extends SpellDecorator {
    public TrackingEnhancement(Spell spell) {
        super(spell);
    }

    @Override
    public void cast() {
        super.cast();
        System.out.println("→ 附加追蹤效果，法術會自動鎖定目標！");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + 追蹤強化";
    }

    @Override
    public int getPower() {
        return super.getPower() + 20;
    }
}