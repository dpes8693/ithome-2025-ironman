// 火焰附魔裝飾器
package file6;

public class FireEnchantment extends SpellDecorator {
    public FireEnchantment(Spell spell) {
        super(spell);
    }

    @Override
    public void cast() {
        super.cast();
        System.out.println("→ 附加火焰附魔，造成額外灼燒傷害！");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + 火焰附魔";
    }

    @Override
    public int getPower() {
        return super.getPower() + 30;
    }
}