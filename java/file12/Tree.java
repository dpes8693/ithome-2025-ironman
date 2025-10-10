package file12;

// 樹木元件（Composite）
public class Tree extends ForestComponent {
    private java.util.List<ForestComponent> components;
    private String treeType;
    private int age;

    public Tree(String name, String treeType, int age, int level) {
        super(name, level);
        this.treeType = treeType;
        this.age = age;
        this.components = new java.util.ArrayList<>();
    }

    @Override
    public void addComponent(ForestComponent component) {
        components.add(component);
        System.out.println("🌱 " + component.getName() + " 已加入到 " + name + " 中");
    }

    @Override
    public void removeComponent(ForestComponent component) {
        if (components.remove(component)) {
            System.out.println("🍂 " + component.getName() + " 已從 " + name + " 中移除");
        }
    }

    @Override
    public java.util.List<ForestComponent> getChildren() {
        return new java.util.ArrayList<>(components);
    }

    @Override
    public void grow() {
        System.out.println(getIndent() + "🌳 " + name + "（" + treeType + "，" + age + "年）正在茁壯成長");

        // 樹木生長時，所有組件也會生長
        for (ForestComponent component : components) {
            component.grow();
        }
    }

    @Override
    public void photosynthesize() {
        System.out.println(getIndent() + "🌞 " + name + " 開始進行光合作用循環");

        // 協調所有葉子進行光合作用
        for (ForestComponent component : components) {
            component.photosynthesize();
        }
    }

    @Override
    public int calculateBiomass() {
        int totalBiomass = age * 2; // 樹齡貢獻基礎生物量

        // 加上所有組件的生物量
        for (ForestComponent component : components) {
            totalBiomass += component.calculateBiomass();
        }

        return totalBiomass;
    }

    @Override
    public void displayStructure() {
        System.out.println(getIndent() + "🌳 " + name + "（" + treeType + "，" + age + "年，生物量: " + calculateBiomass() + "）");

        // 顯示所有子組件
        for (ForestComponent component : components) {
            component.displayStructure();
        }
    }

    public String getTreeType() {
        return treeType;
    }

    public int getAge() {
        return age;
    }

    public void ageOneYear() {
        this.age++;
        System.out.println("🗓️ " + name + " 又長了一年，現在 " + age + " 歲了");
    }
}