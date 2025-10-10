package file8;

// 軍隊接收者
public class Army {
    private String name;

    public Army(String name) {
        this.name = name;
    }

    public void attack(String target) {
        System.out.println(name + " 向 " + target + " 發起攻擊！");
    }

    public void defend(String position) {
        System.out.println(name + " 在 " + position + " 建立防線！");
    }

    public void retreat() {
        System.out.println(name + " 停止攻擊，撤回原位！");
    }

    public void stopDefending() {
        System.out.println(name + " 解除防禦狀態！");
    }
}