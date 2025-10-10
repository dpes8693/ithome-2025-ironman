// 法術裝飾器抽象類
package file6;

public abstract class SpellDecorator implements Spell {
    protected Spell spell;

    public SpellDecorator(Spell spell) {
        this.spell = spell;
    }

    @Override
    public void cast() {
        spell.cast();
    }

    @Override
    public String getDescription() {
        return spell.getDescription();
    }

    @Override
    public int getPower() {
        return spell.getPower();
    }
}