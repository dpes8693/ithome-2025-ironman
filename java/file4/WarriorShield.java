package file4;
// 戰士盾牌
public class WarriorShield implements Armor {
    @Override
    public void defend() {
        System.out.println("戰士盾牌閃耀著堅固光芒，抵擋攻擊！");
    }

    @Override
    public String getName() {
        return "矮人戰士盾牌";
    }

    @Override
    public String getStyle() {
        return "戰士系列";
    }
}