package file21;
//MagicSpell.java
// 魔法咒語介面
public interface MagicSpell {
    MagicSpell clone();
    void cast();
    String getSpellName();
    void setTarget(String target);
}
