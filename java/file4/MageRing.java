package file4;
// 法師魔戒
public class MageRing implements Accessory {
    @Override
    public void enhance() {
        System.out.println("法師魔戒增強魔力，加速法術詠唱！");
    }

    @Override
    public String getName() {
        return "矮人法師魔戒";
    }

    @Override
    public String getStyle() {
        return "法師系列";
    }
}