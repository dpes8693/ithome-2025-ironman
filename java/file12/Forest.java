package file12;

// 森林元件（Composite）
public class Forest extends ForestComponent {
    private java.util.List<ForestComponent> components;
    private String climate;
    private double area; // 面積（平方公里）

    public Forest(String name, String climate, double area, int level) {
        super(name, level);
        this.climate = climate;
        this.area = area;
        this.components = new java.util.ArrayList<>();
    }

    @Override
    public void addComponent(ForestComponent component) {
        components.add(component);
        System.out.println("🌲 " + component.getName() + " 已加入到 " + name + " 森林中");
    }

    @Override
    public void removeComponent(ForestComponent component) {
        if (components.remove(component)) {
            System.out.println("⛏️ " + component.getName() + " 已從 " + name + " 森林中移除");
        }
    }

    @Override
    public java.util.List<ForestComponent> getChildren() {
        return new java.util.ArrayList<>(components);
    }

    @Override
    public void grow() {
        System.out.println(getIndent() + "🌍 " + name + " 森林在 " + climate + " 氣候下蓬勃發展（面積: " + area + " km²）");

        // 整個森林生長時，所有組件都會生長
        for (ForestComponent component : components) {
            component.grow();
        }
    }

    @Override
    public void photosynthesize() {
        System.out.println(getIndent() + "🌅 " + name + " 森林開始大規模光合作用");
        System.out.println(getIndent() + "   氣候條件: " + climate + "，促進光合作用效率");

        // 協調整個森林進行光合作用
        for (ForestComponent component : components) {
            component.photosynthesize();
        }

        System.out.println(getIndent() + "🌿 " + name + " 森林光合作用完成，生態系統更加繁榮");
    }

    @Override
    public int calculateBiomass() {
        int totalBiomass = (int)(area * 10); // 森林面積貢獻基礎生物量

        // 加上所有組件的生物量
        for (ForestComponent component : components) {
            totalBiomass += component.calculateBiomass();
        }

        return totalBiomass;
    }

    @Override
    public void displayStructure() {
        System.out.println(getIndent() + "🌲 " + name + " 森林（" + climate + "氣候，" + area + " km²，總生物量: " + calculateBiomass() + "）");

        // 顯示所有子組件的結構
        for (ForestComponent component : components) {
            component.displayStructure();
        }
    }

    public String getClimate() {
        return climate;
    }

    public double getArea() {
        return area;
    }

    // 森林特有的方法
    public void seasonChange() {
        System.out.println("🍂 " + name + " 森林正在經歷季節變化...");
        // 可以添加季節變化的特殊邏輯
    }
}