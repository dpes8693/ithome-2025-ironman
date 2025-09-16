# Day2 單例模式 (Singleton Pattern)

## 擬人化角色：【孤高的巨龍守護者】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/1-Singleton.png)

- 種族： 巨龍
- 外貌： 一條盤踞在巨大洞穴深處的古老黑龍，鱗片堅硬如鋼鐵，雙眼閃爍著金色的光芒。牠的爪下緊握著一顆閃耀著巨大能量的魔法寶珠，被金色的鎖鏈拴在地上，仿佛整個世界只有這一顆寶珠。
- 性格： 孤傲、強大、不可動搖。牠是獨一無二的存在，也是牠所守護寶藏的唯一實體。任何人都只能透過牠，才能接觸到牠所擁有的力量。
- 能力： 牠本身就是一個「唯一的物件」，並且提供了獲取這個物件的統一方法。無法直接創造新的巨龍或新的寶珠，只能通過與牠溝通來獲取它所代表的權能。
- 代表語： 「我即是唯一，別無他物。」
- 背景故事： 在遠古的傳說中，這條黑龍是世界的基石，守護著一顆蘊含著創世之力的寶珠。這顆寶珠是獨一無二的，無法被複製或創造。所有想要利用寶珠力量的英雄或反派，都必須找到這條巨龍，並說服牠，才能獲得寶珠的「存取權」。巨龍確保了這份力量不會被濫用或分散，因為只有一個實體存在，且其獲取方式被嚴格控制。

---

## 範例

### Java

```java
//DragonGuardian.java
public class DragonGuardian {
    // 私有靜態實例
    private static DragonGuardian instance;
    private String treasureOrb;

    // 私有建構子，防止外部直接實例化
    private DragonGuardian() {
        this.treasureOrb = "創世魔法寶珠";
        System.out.println("古老巨龍覺醒了，開始守護寶珠...");
    }

    // 公開的靜態方法，提供全域訪問點
    public static DragonGuardian getInstance() {
        if (instance == null) {
            instance = new DragonGuardian();
        }
        return instance;
    }

    // 取得寶珠的力量
    public String getTreasurePower() {
        return "你獲得了 " + treasureOrb + " 的力量！";
    }

    // 展示巨龍的獨特性
    public void showIdentity() {
        // 使用hashCode來判斷是否為同一個值
        System.out.println("我即是唯一，別無他物。我是 " + this.hashCode());
    }
}
```

```java
//SingletonExample.java
// 使用範例
public class SingletonExample {
    public static void main(String[] args) {
        // 嘗試獲取巨龍實例
        DragonGuardian dragon1 = DragonGuardian.getInstance();
        DragonGuardian dragon2 = DragonGuardian.getInstance();

        // 驗證是否為同一個實例
        dragon1.showIdentity();
        dragon2.showIdentity();

        // 使用寶珠力量
        System.out.println(dragon1.getTreasurePower());
        System.out.println(dragon2.getTreasurePower());

        // 驗證兩個變數指向同一個物件
        System.out.println("是同一條龍嗎？ " + (dragon1 == dragon2));

        /**output
        古老巨龍覺醒了，開始守護寶珠...
        我即是唯一，別無他物。我是 804564176
        我即是唯一，別無他物。我是 804564176
        你獲得了 創世魔法寶珠 的力量！
        你獲得了 創世魔法寶珠 的力量！
        是同一條龍嗎？ true
        */
    }
}
```

### JavaScript

```javascript
// 使用 ES6 方式的靜態方法版本
class DragonGuardianStatic {
  constructor() {
    this.treasureOrb = "創世魔法寶珠";
    console.log("古老巨龍覺醒了，開始守護寶珠...");
  }

  static getInstance() {
    if (!DragonGuardianStatic.instance) {
      DragonGuardianStatic.instance = new DragonGuardianStatic();
    }
    return DragonGuardianStatic.instance;
  }

  getTreasurePower() {
    return `你獲得了 ${this.treasureOrb} 的力量！`;
  }

  showIdentity() {
    // 每個函數（包括類別）都有一個 name 屬性，它包含了函數或類別的名稱。
    console.log(`我即是唯一，別無他物。實例: ${this.constructor.name}`);
  }
}

// 使用範例
const dragon3 = DragonGuardianStatic.getInstance();
const dragon4 = DragonGuardianStatic.getInstance();

dragon3.showIdentity();
dragon4.showIdentity();
console.log("是同一條龍嗎？", dragon3 === dragon4);
console.log(dragon3.getTreasurePower());

/** output
古老巨龍覺醒了，開始守護寶珠...
我即是唯一，別無他物。實例: DragonGuardianStatic
我即是唯一，別無他物。實例: DragonGuardianStatic
是同一條龍嗎？ true
你獲得了 創世魔法寶珠 的力量！
 */
```

## 小總結

Singleton 設計模式就像我們故事中的孤高巨龍守護者，確保整個系統中只有一個實例存在：

**核心特點：**

- **唯一性**：確保類別只有一個實例
- **全域存取**：提供統一的存取點
- **延遲初始化**：只有在真正需要時才創建實例

**使用時機：**

- 需要控制資源存取（ex:資料庫連線、檔案系統）
- 需要全域狀態管理（ex:設定檔、快取）
- 需要確保某個服務只有一個實例（ex:log）

**注意事項：**

- 在多執行緒環境中要注意執行緒安全
- 過度使用可能造成緊密耦合，降低可測試性
- 違反了單一職責原則（既要管理自己的生命週期，又要執行業務邏輯）
