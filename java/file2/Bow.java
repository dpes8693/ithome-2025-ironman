package file2;

// 弓類實現
public class Bow implements Weapon {
    @Override
    public void use() {
        System.out.println("拉滿弓弦，射出精準的箭矢！");
    }

    @Override
    public String getType() {
        return "精靈短弓";
    }
}