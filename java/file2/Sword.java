package file2;

// 劍類實現
public class Sword implements Weapon {
    @Override
    public void use() {
        System.out.println("揮舞長劍，劃出優美的弧線！");
    }

    @Override
    public String getType() {
        return "新手長劍";
    }
}