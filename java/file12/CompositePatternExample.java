package file12;

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
    }
}