package file4;
// 戰士護手
public class WarriorGauntlet implements Accessory {
    @Override
    public void enhance() {
        System.out.println("戰士護手增強握力，提升攻擊精準度！");
    }

    @Override
    public String getName() {
        return "矮人戰士護手";
    }

    @Override
    public String getStyle() {
        return "戰士系列";
    }
}