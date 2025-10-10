package file4;
// 法師法袍
public class MageRobe implements Armor {
    @Override
    public void defend() {
        System.out.println("法師法袍散發魔法護盾，抵禦魔法攻擊！");
    }

    @Override
    public String getName() {
        return "矮人法師法袍";
    }

    @Override
    public String getStyle() {
        return "法師系列";
    }
}