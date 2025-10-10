package file2;

// 法杖類實現
public class Staff implements Weapon {
    @Override
    public void use() {
        System.out.println("舉起法杖，詠唱出強力的魔法！");
    }

    @Override
    public String getType() {
        return "魔法法杖";
    }
}