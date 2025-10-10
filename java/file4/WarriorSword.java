package file4;
// 戰士劍
public class WarriorSword implements Weapon {
    @Override
    public void attack() {
        System.out.println("戰士劍發出沉重的劈砍聲，勇猛攻擊！");
    }

    @Override
    public String getName() {
        return "矮人戰士劍";
    }

    @Override
    public String getStyle() {
        return "戰士系列";
    }
}