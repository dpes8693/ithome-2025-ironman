# Day13 合成模式 (Composite Pattern)

## 擬人化角色：【自然的森林之靈】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/12-Composite.png)

- 種族： 森林之靈/樹精
- 外貌： 一位由樹根和藤蔓編織而成的女性形態。她的頭髮是樹枝和嫩芽，裙襬是粗壯的樹根深入大地。她漂浮在充滿生機的魔法森林中央，周圍環繞著發光的葉子和小精靈。在她身旁，有全息的樹狀結構圖漂浮著，展示著她對森林的層次管理。
- 性格： 和諧、包容、充滿生命力。她既可以代表單一的生命（如一棵樹），也可以代表一個整體（如整片森林）。她對待所有單獨或組合的實體都一視同仁。
- 能力： 能夠處理樹狀結構的資料。她將森林中的每一棵樹、每一株植物，甚至每一片葉子都視為一個單獨的個體。同時，她也能將多棵樹組合成為一個樹叢，將多個樹叢組合成一個區域，最終形成整片森林。對她來說，無論是單一的葉子還是整個森林，都能用統一的方式進行管理和互動。
- 代表語： 「無論是單一的生命，還是萬物共生，皆為一體。」
- 背景故事： 這位森林之靈是古老森林的化身。她不僅能感知每一棵樹的生長，每一朵花的綻放，也能統籌整個森林的生態系統。當外界有力量想要入侵森林時，她可以調動單一的樹木進行防禦，也可以協調整個森林的植物發動攻擊。對她而言，森林就是一個巨大的、由無數「組件」構成的生命體，而她能以統一的介面來管理和操作這些組件，無論是單個還是整體。

---

## 範例

### Java

```java
//ForestComponent.java
// 森林元件抽象類別（Component）
public abstract class ForestComponent {
    protected String name;
    protected int level; // 層級（用於tab顯示）

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

    // 輔助方法：產生tab
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
        🌲 魔法森林 森林（溫帶濕潤氣候，2.5 km²，總生物量: 2059）
          🌳 千年橡樹（橡樹，1000年，生物量: 2003）
            🍃 橡葉1（橢圓形橡葉）
            🍃 橡葉2（鋸齒橡葉）
            🍃 橡葉3（成熟橡葉）
          🌳 青松（松樹，50年，生物量: 102）
            🍃 松針束1（長針葉）
            🍃 松針束2（短針葉）
          🌳 小樹叢（混合樹叢，0年，生物量: 29）
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
        整個森林的總生物量: 2059 單位

        === 森林變化模擬 ===
        🗓️ 千年橡樹 又長了一年，現在 1001 歲了
        🌱 病葉 已加入到 青松 中

        === 變化後的森林狀況 ===
        🌲 魔法森林 森林（溫帶濕潤氣候，2.5 km²，總生物量: 2161）
          🌳 千年橡樹（橡樹，1001年，生物量: 2005）
            🍃 橡葉1（橢圓形橡葉）
            🍃 橡葉2（鋸齒橡葉）
            🍃 橡葉3（成熟橡葉）
          🌳 青松（松樹，50年，生物量: 102）
            🍃 松針束1（長針葉）
            🍃 松針束2（短針葉）
            🍂 病葉（枯萎葉片）
          🌳 小樹叢（混合樹叢，0年，生物量: 29）
            🌳 小楓樹（楓樹，5年，生物量: 12）
              🍃 楓葉1（五角楓葉）
              🍃 楓葉2（紅楓葉）
            🌳 小白樺（白樺，8年，生物量: 17）
              🍃 樺葉1（卵形樺葉）

        更新後的總生物量: 2161 單位

        === 統一介面展示 ===
        森林之靈可以用相同方式操作任何組件：

        操作對象: 魔法森林
        生物量: 2161
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

### JavaScript

```javascript
// 森林元件抽象類別（Component）
class ForestComponent {
  constructor(name, level = 0) {
    this.name = name;
    this.level = level;
  }

  // 共同操作介面（抽象方法）
  grow() {
    throw new Error("子類必須實現 grow 方法");
  }

  photosynthesize() {
    throw new Error("子類必須實現 photosynthesize 方法");
  }

  calculateBiomass() {
    throw new Error("子類必須實現 calculateBiomass 方法");
  }

  displayStructure() {
    throw new Error("子類必須實現 displayStructure 方法");
  }

  // 組合相關操作（預設拋出異常，由 Composite 覆寫）
  addComponent(component) {
    throw new Error("此操作不適用於葉節點");
  }

  removeComponent(component) {
    throw new Error("此操作不適用於葉節點");
  }

  getChildren() {
    throw new Error("此操作不適用於葉節點");
  }

  // 輔助方法：產生tab
  getIndent() {
    return "  ".repeat(this.level);
  }

  getName() {
    return this.name;
  }

  getLevel() {
    return this.level;
  }
}

// 葉子元件（Leaf）
class Leaf extends ForestComponent {
  constructor(name, leafType, level = 0) {
    super(name, level);
    this.leafType = leafType;
    this.isHealthy = true;
  }

  grow() {
    console.log(
      `${this.getIndent()}🍃 ${this.name}（${
        this.leafType
      }）正在生長，吸收陽光和雨露`
    );
  }

  photosynthesize() {
    if (this.isHealthy) {
      console.log(
        `${this.getIndent()}☀️ ${this.name} 進行光合作用，產生氧氣和葡萄糖`
      );
    } else {
      console.log(
        `${this.getIndent()}🍂 ${this.name} 健康狀況不佳，光合作用效率降低`
      );
    }
  }

  calculateBiomass() {
    return this.isHealthy ? 1 : 0; // 健康葉子貢獻 1 單位生物量
  }

  displayStructure() {
    const healthIcon = this.isHealthy ? "🍃" : "🍂";
    console.log(
      `${this.getIndent()}${healthIcon} ${this.name}（${this.leafType}）`
    );
  }

  setHealthy(healthy) {
    this.isHealthy = healthy;
  }

  getIsHealthy() {
    return this.isHealthy;
  }
}

// 樹木元件（Composite）
class Tree extends ForestComponent {
  constructor(name, treeType, age, level = 0) {
    super(name, level);
    this.treeType = treeType;
    this.age = age;
    this.components = [];
  }

  addComponent(component) {
    this.components.push(component);
    console.log(`🌱 ${component.getName()} 已加入到 ${this.name} 中`);
  }

  removeComponent(component) {
    const index = this.components.indexOf(component);
    if (index > -1) {
      this.components.splice(index, 1);
      console.log(`🍂 ${component.getName()} 已從 ${this.name} 中移除`);
      return true;
    }
    return false;
  }

  getChildren() {
    return [...this.components];
  }

  grow() {
    console.log(
      `${this.getIndent()}🌳 ${this.name}（${this.treeType}，${
        this.age
      }年）正在茁壯成長`
    );

    // 樹木生長時，所有組件也會生長
    this.components.forEach((component) => component.grow());
  }

  photosynthesize() {
    console.log(`${this.getIndent()}🌞 ${this.name} 開始進行光合作用循環`);

    // 協調所有葉子進行光合作用
    this.components.forEach((component) => component.photosynthesize());
  }

  calculateBiomass() {
    let totalBiomass = this.age * 2; // 樹齡貢獻基礎生物量

    // 加上所有組件的生物量
    this.components.forEach((component) => {
      totalBiomass += component.calculateBiomass();
    });

    return totalBiomass;
  }

  displayStructure() {
    console.log(
      `${this.getIndent()}🌳 ${this.name}（${this.treeType}，${
        this.age
      }年，生物量: ${this.calculateBiomass()}）`
    );

    // 顯示所有子組件
    this.components.forEach((component) => component.displayStructure());
  }

  getTreeType() {
    return this.treeType;
  }

  getAge() {
    return this.age;
  }

  ageOneYear() {
    this.age++;
    console.log(`🗓️ ${this.name} 又長了一年，現在 ${this.age} 歲了`);
  }
}

// 森林元件（Composite）
class Forest extends ForestComponent {
  constructor(name, climate, area, level = 0) {
    super(name, level);
    this.climate = climate;
    this.area = area;
    this.components = [];
  }

  addComponent(component) {
    this.components.push(component);
    console.log(`🌲 ${component.getName()} 已加入到 ${this.name} 森林中`);
  }

  removeComponent(component) {
    const index = this.components.indexOf(component);
    if (index > -1) {
      this.components.splice(index, 1);
      console.log(`⛏️ ${component.getName()} 已從 ${this.name} 森林中移除`);
      return true;
    }
    return false;
  }

  getChildren() {
    return [...this.components];
  }

  grow() {
    console.log(
      `${this.getIndent()}🌍 ${this.name} 森林在 ${
        this.climate
      } 氣候下蓬勃發展（面積: ${this.area} km²）`
    );

    // 整個森林生長時，所有組件都會生長
    this.components.forEach((component) => component.grow());
  }

  photosynthesize() {
    console.log(`${this.getIndent()}🌅 ${this.name} 森林開始大規模光合作用`);
    console.log(
      `${this.getIndent()}   氣候條件: ${this.climate}，促進光合作用效率`
    );

    // 協調整個森林進行光合作用
    this.components.forEach((component) => component.photosynthesize());

    console.log(
      `${this.getIndent()}🌿 ${this.name} 森林光合作用完成，生態系統更加繁榮`
    );
  }

  calculateBiomass() {
    let totalBiomass = Math.floor(this.area * 10); // 森林面積貢獻基礎生物量

    // 加上所有組件的生物量
    this.components.forEach((component) => {
      totalBiomass += component.calculateBiomass();
    });

    return totalBiomass;
  }

  displayStructure() {
    console.log(
      `${this.getIndent()}🌲 ${this.name} 森林（${this.climate}氣候，${
        this.area
      } km²，總生物量: ${this.calculateBiomass()}）`
    );

    // 顯示所有子組件的結構
    this.components.forEach((component) => component.displayStructure());
  }

  getClimate() {
    return this.climate;
  }

  getArea() {
    return this.area;
  }

  // 森林特有的方法
  seasonChange() {
    console.log(`🍂 ${this.name} 森林正在經歷季節變化...`);
  }
}

// 使用範例
console.log("=== 歡迎來到魔法森林 ===");
console.log("森林之靈：無論是單一的生命，還是萬物共生，皆為一體。\n");

// 創建森林
const enchantedForest = new Forest("魔法森林", "溫帶濕潤", 2.5, 0);

// 創建第一棵樹：古老橡樹
const ancientOak = new Tree("千年橡樹", "橡樹", 1000, 1);

// 為橡樹添加葉子
ancientOak.addComponent(new Leaf("橡葉1", "橢圓形橡葉", 2));
ancientOak.addComponent(new Leaf("橡葉2", "鋸齒橡葉", 2));
ancientOak.addComponent(new Leaf("橡葉3", "成熟橡葉", 2));

// 創建第二棵樹：年輕松樹
const youngPine = new Tree("青松", "松樹", 50, 1);

// 為松樹添加葉子（松針）
youngPine.addComponent(new Leaf("松針束1", "長針葉", 2));
youngPine.addComponent(new Leaf("松針束2", "短針葉", 2));

// 創建一個小樹叢（樹的組合）
const grove = new Tree("小樹叢", "混合樹叢", 0, 1);

// 創建樹叢中的小樹
const saplingMaple = new Tree("小楓樹", "楓樹", 5, 2);
saplingMaple.addComponent(new Leaf("楓葉1", "五角楓葉", 3));
saplingMaple.addComponent(new Leaf("楓葉2", "紅楓葉", 3));

const saplingBirch = new Tree("小白樺", "白樺", 8, 2);
saplingBirch.addComponent(new Leaf("樺葉1", "卵形樺葉", 3));

// 將小樹加入樹叢
grove.addComponent(saplingMaple);
grove.addComponent(saplingBirch);

// 將所有組件加入森林
enchantedForest.addComponent(ancientOak);
enchantedForest.addComponent(youngPine);
enchantedForest.addComponent(grove);

console.log("\n=== 森林結構展示 ===");
enchantedForest.displayStructure();

console.log("\n=== 森林生長過程 ===");
enchantedForest.grow();

console.log("\n=== 光合作用循環 ===");
enchantedForest.photosynthesize();

console.log("\n=== 生物量統計 ===");
console.log(`整個森林的總生物量: ${enchantedForest.calculateBiomass()} 單位`);

// 模擬一些變化
console.log("\n=== 森林變化模擬 ===");

// 橡樹年齡增加
ancientOak.ageOneYear();

// 一片葉子生病了
const sickLeaf = new Leaf("病葉", "枯萎葉片", 2);
sickLeaf.setHealthy(false);
youngPine.addComponent(sickLeaf);

// 再次顯示結構和統計
console.log("\n=== 變化後的森林狀況 ===");
enchantedForest.displayStructure();
console.log(`\n更新後的總生物量: ${enchantedForest.calculateBiomass()} 單位`);

// 展示統一介面的威力
console.log("\n=== 統一介面展示 ===");
console.log("森林之靈可以用相同方式操作任何組件：");

// 對不同類型的組件執行相同操作
const components = [
  enchantedForest, // 森林
  ancientOak, // 樹
  saplingMaple, // 小樹
  new Leaf("測試葉", "普通葉片", 0), // 葉子
];

components.forEach((component) => {
  console.log(`\n操作對象: ${component.getName()}`);
  console.log(`生物量: ${component.calculateBiomass()}`);
  component.photosynthesize();
});

/** output
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
🌲 魔法森林 森林（溫帶濕潤氣候，2.5 km²，總生物量: 2159）
  🌳 千年橡樹（橡樹，1000年，生物量: 2003）
    🍃 橡葉1（橢圓形橡葉）
    🍃 橡葉2（鋸齒橡葉）
    🍃 橡葉3（成熟橡葉）
  🌳 青松（松樹，50年，生物量: 102）
    🍃 松針束1（長針葉）
    🍃 松針束2（短針葉）
  🌳 小樹叢（混合樹叢，0年，生物量: 29）
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
整個森林的總生物量: 2159 單位

=== 森林變化模擬 ===
🗓️ 千年橡樹 又長了一年，現在 1001 歲了
🌱 病葉 已加入到 青松 中

=== 變化後的森林狀況 ===
🌲 魔法森林 森林（溫帶濕潤氣候，2.5 km²，總生物量: 2161）
  🌳 千年橡樹（橡樹，1001年，生物量: 2005）
    🍃 橡葉1（橢圓形橡葉）
    🍃 橡葉2（鋸齒橡葉）
    🍃 橡葉3（成熟橡葉）
  🌳 青松（松樹，50年，生物量: 102）
    🍃 松針束1（長針葉）
    🍃 松針束2（短針葉）
    🍂 病葉（枯萎葉片）
  🌳 小樹叢（混合樹叢，0年，生物量: 29）
    🌳 小楓樹（楓樹，5年，生物量: 12）
      🍃 楓葉1（五角楓葉）
      🍃 楓葉2（紅楓葉）
    🌳 小白樺（白樺，8年，生物量: 17）
      🍃 樺葉1（卵形樺葉）

更新後的總生物量: 2161 單位

=== 統一介面展示 ===
森林之靈可以用相同方式操作任何組件：

操作對象: 魔法森林
生物量: 2161
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
```

## 小總結

Composite Pattern 設計模式就像我們故事中的森林之靈，讓客戶端能以`統一的方式`處理個別物件和物件的組合

總生物量:

- [葉子]=健康葉子貢獻 1 單位生物量
- [樹]=age\*2 + 組件數量
- [森林]=森林面積\*10+ 組件數量

**核心特點：**

- **樹狀結構**：將物件組合成樹形結構，表現整體與部分的層次關係
- **統一介面**：讓單個物件和組合物件對客戶端來說具有一致性
- **透明性**：客戶端可以一致地使用組合結構中的所有物件
- **遞歸處理**：組合物件可以包含其他組合物件，形成複雜的樹狀結構

**使用時機：**

- 需要表示物件的整體與部分層次結構（ex: 檔案系統的資料夾和檔案）
- 希望客戶端能夠忽略組合物件與單個物件的差異（ex: 圖形編輯器的圖形元素）
- 需要統一處理樹狀結構中的所有節點（ex: 組織架構的員工和部門）

<!-- **注意事項：**

- 可能使設計過於抽象，增加理解和維護的複雜度
- 組合物件的類型檢查變得困難，失去編譯時的類型安全
- 如果樹狀結構過深，可能影響效能（特別是遞歸操作）
- 需要小心處理組合操作的安全性，避免循環引用
- 適合結構相對穩定的場景，頻繁變動結構的系統不適用 -->
