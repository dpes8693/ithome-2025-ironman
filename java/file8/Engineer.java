package file8;

// 工程師接收者
public class Engineer {
    private String name;

    public Engineer(String name) {
        this.name = name;
    }

    public void build(String structure) {
        System.out.println(name + " 開始建造 " + structure + "！");
    }

    public void demolish(String structure) {
        System.out.println(name + " 拆除了 " + structure + "！");
    }
}