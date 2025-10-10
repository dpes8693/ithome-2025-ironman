```java
package file12;
//ForestComponent.java
// 森林元件抽象類別（Component）
public abstract class ForestComponent {
    protected String name;
    protected int level; // 層級（用於縮進顯示）

    public ForestComponent(String name, int level) {
        this.name = name;
        this.level = level;
    }

    // 共同操作介面
    public abstract void grow(); // 生長
    public abstract void photosynthesize(); // 光合作用
    public abstract int calculateBiomass(); // 計算生物量
    public abstract void displayStructure(); // 顯示結構

    // 組合相關操作（預設拋出異常，由 Composite 覆寫）
    public void addComponent(ForestComponent component) {
        throw new UnsupportedOperationException("此操作不適用於葉節點");
    }

    public void removeComponent(ForestComponent component) {
        throw new UnsupportedOperationException("此操作不適用於葉節點");
    }

    public java.util.List<ForestComponent> getChildren() {
        throw new UnsupportedOperationException("此操作不適用於葉節點");
    }

    // 輔助方法：產生縮進
    protected String getIndent() {
        return "  ".repeat(level);
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}
```

```java
package file12;
//Leaf.java
// 葉子元件（Leaf）
public class Leaf extends ForestComponent {
    private String leafType;
    private boolean isHealthy;

    public Leaf(String name, String leafType, int level) {
        super(name, level);
        this.leafType = leafType;
        this.isHealthy = true;
    }

    @Override
    public void grow() {
        System.out.println(getIndent() + "🍃 " + name + "（" + leafType + "）正在生長，吸收陽光和雨露");
    }

    @Override
    public void photosynthesize() {
        if (isHealthy) {
            System.out.println(getIndent() + "☀️ " + name + " 進行光合作用，產生氧氣和葡萄糖");
        } else {
            System.out.println(getIndent() + "🍂 " + name + " 健康狀況不佳，光合作用效率降低");
        }
    }

    @Override
    public int calculateBiomass() {
        return isHealthy ? 1 : 0; // 健康葉子貢獻 1 單位生物量
    }

    @Override
    public void displayStructure() {
        String healthIcon = isHealthy ? "🍃" : "🍂";
        System.out.println(getIndent() + healthIcon + " " + name + "（" + leafType + "）");
    }

    public void setHealthy(boolean healthy) {
        this.isHealthy = healthy;
    }

    public boolean isHealthy() {
        return isHealthy;
    }
}
```

```java
package file12;
//Tree.java
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
```

```java
package file12;
//Forest.java
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
```

```java
package file12;
//CompositePatternExample.java
// 使用範例
public class CompositePatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到魔法森林 ===");
        System.out.println("森林之靈：無論是單一的生命，還是萬物共生，皆為一體。\n");

        // 創建森林
        Forest enchantedForest = new Forest("魔法森林", "溫帶濕潤", 2.5, 0);

        // 創建第一棵樹：古老橡樹
        Tree ancientOak = new Tree("千年橡樹", "橡樹", 1000, 1);

        // 為橡樹添加葉子
        ancientOak.addComponent(new Leaf("橡葉1", "橢圓形橡葉", 2));
        ancientOak.addComponent(new Leaf("橡葉2", "鋸齒橡葉", 2));
        ancientOak.addComponent(new Leaf("橡葉3", "成熟橡葉", 2));

        // 創建第二棵樹：年輕松樹
        Tree youngPine = new Tree("青松", "松樹", 50, 1);

        // 為松樹添加葉子（松針）
        youngPine.addComponent(new Leaf("松針束1", "長針葉", 2));
        youngPine.addComponent(new Leaf("松針束2", "短針葉", 2));

        // 創建一個小樹叢（樹的組合）
        Tree grove = new Tree("小樹叢", "混合樹叢", 0, 1);

        // 創建樹叢中的小樹
        Tree saplingMaple = new Tree("小楓樹", "楓樹", 5, 2);
        saplingMaple.addComponent(new Leaf("楓葉1", "五角楓葉", 3));
        saplingMaple.addComponent(new Leaf("楓葉2", "紅楓葉", 3));

        Tree saplingBirch = new Tree("小白樺", "白樺", 8, 2);
        saplingBirch.addComponent(new Leaf("樺葉1", "卵形樺葉", 3));

        // 將小樹加入樹叢
        grove.addComponent(saplingMaple);
        grove.addComponent(saplingBirch);

        // 將所有組件加入森林
        enchantedForest.addComponent(ancientOak);
        enchantedForest.addComponent(youngPine);
        enchantedForest.addComponent(grove);

        System.out.println("\n=== 森林結構展示 ===");
        enchantedForest.displayStructure();

        System.out.println("\n=== 森林生長過程 ===");
        enchantedForest.grow();

        System.out.println("\n=== 光合作用循環 ===");
        enchantedForest.photosynthesize();

        System.out.println("\n=== 生物量統計 ===");
        System.out.println("整個森林的總生物量: " + enchantedForest.calculateBiomass() + " 單位");

        // 模擬一些變化
        System.out.println("\n=== 森林變化模擬 ===");

        // 橡樹年齡增加
        ancientOak.ageOneYear();

        // 一片葉子生病了
        Leaf sickLeaf = new Leaf("病葉", "枯萎葉片", 2);
        sickLeaf.setHealthy(false);
        youngPine.addComponent(sickLeaf);

        // 再次顯示結構和統計
        System.out.println("\n=== 變化後的森林狀況 ===");
        enchantedForest.displayStructure();
        System.out.println("\n更新後的總生物量: " + enchantedForest.calculateBiomass() + " 單位");

        // 展示統一介面的威力
        System.out.println("\n=== 統一介面展示 ===");
        System.out.println("森林之靈可以用相同方式操作任何組件：");

        // 對不同類型的組件執行相同操作
        ForestComponent[] components = {
            enchantedForest,    // 森林
            ancientOak,         // 樹
            saplingMaple,       // 小樹
            new Leaf("測試葉", "普通葉片", 0)  // 葉子
        };

        for (ForestComponent component : components) {
            System.out.println("\n操作對象: " + component.getName());
            System.out.println("生物量: " + component.calculateBiomass());
            component.photosynthesize();
        }

        /**output
        === 歡迎來到魔法森林 ===
        森林之靈：無論是單一的生命，還是萬物共生，皆為一體。

        🌱 橡葉1 已加入到 千年橡樹 中
        🌱 橡葉2 已加入到 千年橡樹 中
        🌱 橡葉3 已加入到 千年橡樹 中
        🌱 松針束1 已加入到 青松 中
        🌱 松針束2 已加入到 青松 中
        🌱 楓葉1 已加入到 小楓樹 中
        🌱 楓葉2 已加入到 小楓樹 中
        🌱 樺葉1 已加入到 小白樺 中
        🌱 小楓樹 已加入到 小樹叢 中
        🌱 小白樺 已加入到 小樹叢 中
        🌲 千年橡樹 已加入到 魔法森林 森林中
        🌲 青松 已加入到 魔法森林 森林中
        🌲 小樹叢 已加入到 魔法森林 森林中

        === 森林結構展示 ===
        🌲 魔法森林 森林（溫帶濕潤氣候，2.5 km²，總生物量: 2051）
          🌳 千年橡樹（橡樹，1000年，生物量: 2003）
            🍃 橡葉1（橢圓形橡葉）
            🍃 橡葉2（鋸齒橡葉）
            🍃 橡葉3（成熟橡葉）
          🌳 青松（松樹，50年，生物量: 102）
            🍃 松針束1（長針葉）
            🍃 松針束2（短針葉）
          🌳 小樹叢（混合樹叢，0年，生物量: 21）
            🌳 小楓樹（楓樹，5年，生物量: 12）
              🍃 楓葉1（五角楓葉）
              🍃 楓葉2（紅楓葉）
            🌳 小白樺（白樺，8年，生物量: 17）
              🍃 樺葉1（卵形樺葉）

        === 森林生長過程 ===
        🌍 魔法森林 森林在 溫帶濕潤 氣候下蓬勃發展（面積: 2.5 km²）
          🌳 千年橡樹（橡樹，1000年）正在茁壯成長
            🍃 橡葉1（橢圓形橡葉）正在生長，吸收陽光和雨露
            🍃 橡葉2（鋸齒橡葉）正在生長，吸收陽光和雨露
            🍃 橡葉3（成熟橡葉）正在生長，吸收陽光和雨露
          🌳 青松（松樹，50年）正在茁壯成長
            🍃 松針束1（長針葉）正在生長，吸收陽光和雨露
            🍃 松針束2（短針葉）正在生長，吸收陽光和雨露
          🌳 小樹叢（混合樹叢，0年）正在茁壯成長
            🌳 小楓樹（楓樹，5年）正在茁壯成長
              🍃 楓葉1（五角楓葉）正在生長，吸收陽光和雨露
              🍃 楓葉2（紅楓葉）正在生長，吸收陽光和雨露
            🌳 小白樺（白樺，8年）正在茁壯成長
              🍃 樺葉1（卵形樺葉）正在生長，吸收陽光和雨露

        === 光合作用循環 ===
        🌅 魔法森林 森林開始大規模光合作用
           氣候條件: 溫帶濕潤，促進光合作用效率
          🌞 千年橡樹 開始進行光合作用循環
            ☀️ 橡葉1 進行光合作用，產生氧氣和葡萄糖
            ☀️ 橡葉2 進行光合作用，產生氧氣和葡萄糖
            ☀️ 橡葉3 進行光合作用，產生氧氣和葡萄糖
          🌞 青松 開始進行光合作用循環
            ☀️ 松針束1 進行光合作用，產生氧氣和葡萄糖
            ☀️ 松針束2 進行光合作用，產生氧氣和葡萄糖
          🌞 小樹叢 開始進行光合作用循環
            🌞 小楓樹 開始進行光合作用循環
              ☀️ 楓葉1 進行光合作用，產生氧氣和葡萄糖
              ☀️ 楓葉2 進行光合作用，產生氧氣和葡萄糖
            🌞 小白樺 開始進行光合作用循環
              ☀️ 樺葉1 進行光合作用，產生氧氣和葡萄糖
        🌿 魔法森林 森林光合作用完成，生態系統更加繁榮

        === 生物量統計 ===
        整個森林的總生物量: 2051 單位

        === 森林變化模擬 ===
        🗓️ 千年橡樹 又長了一年，現在 1001 歲了
        🌱 病葉 已加入到 青松 中

        === 變化後的森林狀況 ===
        🌲 魔法森林 森林（溫帶濕潤氣候，2.5 km²，總生物量: 2053）
          🌳 千年橡樹（橡樹，1001年，生物量: 2005）
            🍃 橡葉1（橢圓形橡葉）
            🍃 橡葉2（鋸齒橡葉）
            🍃 橡葉3（成熟橡葉）
          🌳 青松（松樹，50年，生物量: 102）
            🍃 松針束1（長針葉）
            🍃 松針束2（短針葉）
            🍂 病葉（枯萎葉片）
          🌳 小樹叢（混合樹叢，0年，生物量: 21）
            🌳 小楓樹（楓樹，5年，生物量: 12）
              🍃 楓葉1（五角楓葉）
              🍃 楓葉2（紅楓葉）
            🌳 小白樺（白樺，8年，生物量: 17）
              🍃 樺葉1（卵形樺葉）

        更新後的總生物量: 2053 單位

        === 統一介面展示 ===
        森林之靈可以用相同方式操作任何組件：

        操作對象: 魔法森林
        生物量: 2053
        🌅 魔法森林 森林開始大規模光合作用
           氣候條件: 溫帶濕潤，促進光合作用效率
          🌞 千年橡樹 開始進行光合作用循環
            ☀️ 橡葉1 進行光合作用，產生氧氣和葡萄糖
            ☀️ 橡葉2 進行光合作用，產生氧氣和葡萄糖
            ☀️ 橡葉3 進行光合作用，產生氧氣和葡萄糖
          🌞 青松 開始進行光合作用循環
            ☀️ 松針束1 進行光合作用，產生氧氣和葡萄糖
            ☀️ 松針束2 進行光合作用，產生氧氣和葡萄糖
            🍂 病葉 健康狀況不佳，光合作用效率降低
          🌞 小樹叢 開始進行光合作用循環
            🌞 小楓樹 開始進行光合作用循環
              ☀️ 楓葉1 進行光合作用，產生氧氣和葡萄糖
              ☀️ 楓葉2 進行光合作用，產生氧氣和葡萄糖
            🌞 小白樺 開始進行光合作用循環
              ☀️ 樺葉1 進行光合作用，產生氧氣和葡萄糖
        🌿 魔法森林 森林光合作用完成，生態系統更加繁榮

        操作對象: 千年橡樹
        生物量: 2005
        🌞 千年橡樹 開始進行光合作用循環
          ☀️ 橡葉1 進行光合作用，產生氧氣和葡萄糖
          ☀️ 橡葉2 進行光合作用，產生氧氣和葡萄糖
          ☀️ 橡葉3 進行光合作用，產生氧氣和葡萄糖

        操作對象: 小楓樹
        生物量: 12
        🌞 小楓樹 開始進行光合作用循環
          ☀️ 楓葉1 進行光合作用，產生氧氣和葡萄糖
          ☀️ 楓葉2 進行光合作用，產生氧氣和葡萄糖

        操作對象: 測試葉
        生物量: 1
        ☀️ 測試葉 進行光合作用，產生氧氣和葡萄糖
        */
    }
}
```