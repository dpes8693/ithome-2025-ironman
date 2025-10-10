package file4;
// 法師法杖
public class MageStaff implements Weapon {
    @Override
    public void attack() {
        System.out.println("法師法杖釋放魔法能量，施展強力法術！");
    }

    @Override
    public String getName() {
        return "矮人法師法杖";
    }

    @Override
    public String getStyle() {
        return "法師系列";
    }
}