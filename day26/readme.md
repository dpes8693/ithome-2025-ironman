# Day 26: 認識 UML 與類別圖

## 📖 前言

在軟體開發的世界中，良好的溝通是成功的關鍵。想像一下，如果建築師沒有藍圖，要如何與工程團隊溝通建築設計？同樣地，軟體開發也需要一種標準化的「藍圖」來表達系統架構和設計想法。這就是 **UML（Unified Modeling Language，統一塑模語言）** 的價值所在。

---

## 🎯 什麼是 UML？

UML 是一種標準化的視覺化建模語言，用於：

- **規劃**軟體系統的結構
- **視覺化**系統的設計
- **記錄**系統的架構
- **溝通**開發團隊之間的想法

> 💡 UML 不是程式語言，而是一種圖形化的表達方式，讓複雜的系統設計變得容易理解。

---

## 📊 UML 圖表的分類

UML 2.x 定義了 **14 種不同類型的圖表**，主要分為兩大類：

### 1️⃣ 結構圖（Structure Diagrams）

描述系統的靜態結構：

- **類別圖（Class Diagram）** ⭐
- **物件圖（Object Diagram）**
- **元件圖（Component Diagram）**
- **部署圖（Deployment Diagram）**
- **套件圖（Package Diagram）**
- **組合結構圖（Composite Structure Diagram）**
- **輪廓圖（Profile Diagram）**

### 2️⃣ 行為圖（Behavior Diagrams）

描述系統的動態行為：

- **使用案例圖（Use Case Diagram）** ⭐
- **活動圖（Activity Diagram）**
- **狀態機圖（State Machine Diagram）**
- **循序圖（Sequence Diagram）** ⭐
- **溝通圖（Communication Diagram）**
- **互動總覽圖（Interaction Overview Diagram）**
- **時序圖（Timing Diagram）**

---

## 🌟 本篇重點：類別圖（Class Diagram）

類別圖是 UML 中最常用、最重要的圖表之一。它展示系統中的類別、屬性、方法，以及類別之間的關係。

### 📝 用途

- 設計系統的靜態結構
- 展示類別之間的關係
- 作為程式碼實作的藍圖
- 溝通系統架構設計

---

## 🏗️ 類別圖基本結構

### 單一類別的表示

```text
┌─────────────────────┐
│    ClassName        │  ← 類別名稱
├─────────────────────┤
│ - attribute1: Type  │  ← 屬性（- 表示 private）
│ + attribute2: Type  │  ← 屬性（+ 表示 public）
│ # attribute3: Type  │  ← 屬性（# 表示 protected）
├─────────────────────┤
│ + method1(): void   │  ← 方法
│ - method2(): Type   │
│ # method3(): Type   │
└─────────────────────┘
```

### 可見性修飾符

| 符號 | 意義 | Java 對應 |
|------|------|-----------|
| `+` | Public | `public` |
| `-` | Private | `private` |
| `#` | Protected | `protected` |
| `~` | Package | (預設，無修飾符) |

### 特殊標記

- **抽象類別**：類別名稱使用斜體或加上 `{abstract}`
- **介面**：加上 `<<interface>>` 標籤
- **靜態成員**：加底線
- **抽象方法**：使用斜體

---

## 🔗 類別之間的關係

類別圖的精髓在於表達類別之間的關係，以下是六種主要關係：

### 1. 繼承（Inheritance / Generalization）

**符號**：`───▷` （實線空心三角箭頭）

**意義**：子類別繼承父類別，"is-a" 關係

**範例**：

```text
    ┌─────────┐
    │  動物   │
    └─────────┘
         △
         │
    ┌────┴────┐
    │         │
┌───────┐ ┌───────┐
│  貓   │ │  狗   │
└───────┘ └───────┘
```

貓 **是一種** 動物，狗 **是一種** 動物。

---

### 2. 實作（Realization / Implementation）

**符號**：`┄┄▷` （虛線空心三角箭頭）

**意義**：類別實作介面

**範例**：

```text
    ┌─────────────┐
    │<<interface>>│
    │   可飛行    │
    └─────────────┘
         △
         ┊
    ┌────┴────┐
    ┊         ┊
┌───────┐ ┌───────┐
│  鳥   │ │ 飛機  │
└───────┘ └───────┘
```

---

### 3. 關聯（Association）

**符號**：`───` （實線）

**意義**：類別之間有某種關聯，"has-a" 或 "uses" 關係

**範例**：

```text
┌─────┐       ┌─────┐
│教師 │──────>│學生 │
└─────┘ 1  *  └─────┘
```

一位教師可以教導多位學生。

**多重性標記**：

- `1` : 正好一個
- `0..1` : 零或一個
- `*` 或 `0..*` : 零或多個
- `1..*` : 一或多個
- `n..m` : n 到 m 個

---

### 4. 聚合（Aggregation）

**符號**：`───◇` （實線空心菱形）

**意義**：整體包含部分的關係，但部分可以獨立存在（弱擁有關係）

**範例**：

```text
┌─────┐       ┌─────┐
│部門 │◇──────│員工 │
└─────┘       └─────┘
```

部門包含員工，但員工離開部門後仍然存在。

---

### 5. 組合（Composition）

**符號**：`───◆` （實線實心菱形）

**意義**：整體包含部分的關係，部分不能獨立於整體存在（強擁有關係）

**範例**：

```text
┌─────┐       ┌─────┐
│房子 │◆──────│房間 │
└─────┘       └─────┘
```

房子包含房間，房子不存在了，房間也就不存在了。

---

### 6. 相依（Dependency）

**符號**：`┄┄>` （虛線箭頭）

**意義**：一個類別使用另一個類別，通常是暫時性的關係

**範例**：

```text
┌─────┐       ┌──────────┐
│訂單 │ ┄┄┄┄┄> │折扣計算器│
└─────┘       └──────────┘
```

訂單在計算價格時會使用折扣計算器。

---

## 💼 範例：RPG 遊戲角色系統

### 系統需求

設計一個 RPG 遊戲的角色系統：

- 有不同類型的角色：戰士、法師
- 所有角色都有基本屬性：名字、等級、生命值
- 所有角色都能攻擊、防禦、升級
- 戰士有專屬武器和技能
- 法師有專屬法杖和魔法

### UML 類別圖

```text
┌─────────────────────────┐
│   <<abstract>>          │
│      Character          │
├─────────────────────────┤
│ - name: String          │
│ - level: int            │
│ - hp: int               │
├─────────────────────────┤
│ + Character(name)       │
│ + attack(): void        │ ← 抽象方法
│ + defend(): void        │
│ + levelUp(): void       │
└─────────────────────────┘
           △
           │ (繼承)
    ┌──────┴──────┐
    │             │
┌───────────┐ ┌───────────┐
│  Warrior  │ │   Mage    │
├───────────┤ ├───────────┤
│-sword:    │ │-staff:    │
│ String    │ │ String    │
├───────────┤ ├───────────┤
│+Warrior() │ │+Mage()    │
│+attack()  │ │+attack()  │
│+slash()   │ │+cast()    │
└───────────┘ └───────────┘
```

### Java 程式碼實作

#### Character 抽象父類別

```java
// 抽象父類別
public abstract class Character {
    private String name;
    private int level;
    private int hp;
    
    public Character(String name) {
        this.name = name;
        this.level = 1;
        this.hp = 100;
    }
    
    // 抽象方法，子類別必須實作
    public abstract void attack();
    
    // 具體方法
    public void defend() {
        System.out.println(name + " 防禦中...");
    }
    
    public void levelUp() {
        level++;
        hp += 50;
        System.out.println(name + " 升級到 Lv." + level + "！生命值: " + hp);
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getHp() {
        return hp;
    }
}
```

#### Warrior 戰士類別

```java
// 戰士子類別
public class Warrior extends Character {
    private String sword;
    
    public Warrior(String name) {
        super(name);
        this.sword = "鐵劍";
    }
    
    @Override
    public void attack() {
        System.out.println(getName() + " 戰士揮劍斬擊！");
    }
    
    // 戰士專屬技能
    public void slash() {
        System.out.println(getName() + " 使用 " + sword + " 進行強力斬擊！造成 150% 傷害！");
    }
    
    // 升級時可以強化武器
    public void upgradeSword() {
        if (getLevel() >= 5) {
            this.sword = "鋼劍";
            System.out.println("武器升級為：" + sword);
        } else if (getLevel() >= 10) {
            this.sword = "神劍";
            System.out.println("武器升級為：" + sword);
        }
    }
}
```

#### Mage 法師類別

```java
// 法師子類別
public class Mage extends Character {
    private String staff;
    private int mana;
    
    public Mage(String name) {
        super(name);
        this.staff = "木杖";
        this.mana = 100;
    }
    
    @Override
    public void attack() {
        System.out.println(getName() + " 法師施放魔法攻擊！");
        useMana(10);
    }
    
    // 法師專屬技能
    public void cast() {
        if (mana >= 30) {
            System.out.println(getName() + " 使用 " + staff + " 施放火球術！造成 200% 傷害！");
            useMana(30);
        } else {
            System.out.println("魔力不足！");
        }
    }
    
    private void useMana(int amount) {
        mana -= amount;
        System.out.println("剩餘魔力: " + mana);
    }
    
    public void restoreMana() {
        mana = 100;
        System.out.println("魔力已恢復！");
    }
}
```

#### 測試程式

```java
public class RPGGameExample {
    public static void main(String[] args) {
        // 創建戰士角色
        Warrior warrior = new Warrior("亞瑟");
        System.out.println("=== 戰士 " + warrior.getName() + " ===");
        warrior.attack();      // 一般攻擊
        warrior.slash();       // 專屬技能
        warrior.defend();      // 防禦
        warrior.levelUp();     // 升級
        
        System.out.println();
        
        // 創建法師角色
        Mage mage = new Mage("梅林");
        System.out.println("=== 法師 " + mage.getName() + " ===");
        mage.attack();         // 一般攻擊
        mage.cast();           // 專屬技能
        mage.attack();         // 再次攻擊
        mage.attack();         // 魔力不足
        mage.restoreMana();    // 恢復魔力
        mage.levelUp();        // 升級
    }
}
```

#### 執行結果

```text
=== 戰士 亞瑟 ===
亞瑟 戰士揮劍斬擊！
亞瑟 使用 鐵劍 進行強力斬擊！造成 150% 傷害！
亞瑟 防禦中...
亞瑟 升級到 Lv.2！生命值: 150

=== 法師 梅林 ===
梅林 法師施放魔法攻擊！
剩餘魔力: 90
梅林 使用 木杖 施放火球術！造成 200% 傷害！
剩餘魔力: 60
梅林 法師施放魔法攻擊！
剩餘魔力: 50
梅林 法師施放魔法攻擊！
剩餘魔力: 40
魔力已恢復！
梅林 升級到 Lv.2！生命值: 150
```

<!-- ---

## 🎯 進階範例：加入介面設計

讓我們擴展系統，加入介面來實現更靈活的設計。

### 系統擴展需求

- 某些角色可以飛行（如飛行法師）
- 某些角色可以治療（如牧師）
- 某些角色可以同時擁有多種能力

### 擴展的 UML 類別圖

```text
┌──────────────┐     ┌──────────────┐
│<<interface>> │     │<<interface>> │
│   Flyable    │     │   Healable   │
├──────────────┤     ├──────────────┤
│ + fly():void │     │ + heal():void│
└──────────────┘     └──────────────┘
       △                    △
       ┊                    ┊
       ┊              ┌─────┴─────┐
       ┊              ┊           ┊
┌──────────────┐  ┌────────┐  ┌────────┐
│  Character   │  │ Mage   │  │ Priest │
│  (abstract)  │  └────────┘  └────────┘
└──────────────┘       ┊
       △               ┊
       │          ┌────┴────┐
       │          │         │
   ┌───────┐ ┌──────────┐  │
   │Warrior│ │FlyingMage│──┘
   └───────┘ └──────────┘
```

### Java 程式碼實作

```java
// 可飛行介面
public interface Flyable {
    void fly();
}

// 可治療介面
public interface Healable {
    void heal(Character target);
}

// 飛行法師（實作飛行介面）
public class FlyingMage extends Mage implements Flyable {
    public FlyingMage(String name) {
        super(name);
    }
    
    @Override
    public void fly() {
        System.out.println(getName() + " 使用魔法飛行在空中！");
    }
    
    @Override
    public void attack() {
        System.out.println(getName() + " 從空中發射魔法飛彈！");
    }
}

// 牧師（實作治療介面）
public class Priest extends Character implements Healable {
    public Priest(String name) {
        super(name);
    }
    
    @Override
    public void attack() {
        System.out.println(getName() + " 使用神聖之光攻擊！");
    }
    
    @Override
    public void heal(Character target) {
        System.out.println(getName() + " 治療 " + target.getName() + "，恢復 50 生命值！");
    }
}

// 測試程式
public class AdvancedRPGExample {
    public static void main(String[] args) {
        FlyingMage flyingMage = new FlyingMage("艾莉絲");
        Priest priest = new Priest("莉亞");
        Warrior warrior = new Warrior("布魯斯");
        
        System.out.println("=== 戰鬥開始 ===");
        flyingMage.fly();
        flyingMage.attack();
        
        warrior.attack();
        warrior.slash();
        
        priest.heal(warrior);
        priest.attack();
    }
}
``` -->

---

## 📌 類別圖設計原則

### 1. 單一職責原則（Single Responsibility Principle）

每個類別應該只有一個職責。

❌ **不好的設計**：

```java
public class User {
    private String name;
    
    public void saveToDatabase() { }     // 資料庫操作
    public void sendEmail() { }          // 郵件發送
    public void generateReport() { }     // 報告生成
}
```

✅ **好的設計**：

```java
public class User {
    private String name;
    // 只負責使用者資料
}

public class UserRepository {
    public void save(User user) { }  // 負責資料庫操作
}

public class EmailService {
    public void send(User user) { }  // 負責郵件發送
}
```

### 2. 優先使用組合而非繼承（Composition over Inheritance）

**繼承**建立了強耦合關係，**組合**更加靈活。

❌ **過度使用繼承**：

```text
    Animal
      │
  ┌───┴───┐
  │       │
 Bird    Fish
  │       │
┌─┴─┐     │
│   │     │
Duck Swan Shark
```

✅ **使用組合**：

```text
┌─────────┐      ┌──────────────┐
│ Animal  │◇────>│ MoveBehavior │
└─────────┘      └──────────────┘
                       △
                  ┌────┴────┐
            ┌──────┐    ┌──────┐
            │ Fly  │    │ Swim │
            └──────┘    └──────┘
```

### 3. 介面隔離原則（Interface Segregation Principle）

不要強迫類別實作它們不需要的方法。

❌ **過大的介面**：

```java
public interface Worker {
    void work();
    void eat();
    void sleep();
    void fly();  // 不是所有 worker 都會飛
}
```

✅ **分離介面**：

```java
public interface Workable {
    void work();
}

public interface Eatable {
    void eat();
}

public interface Flyable {
    void fly();
}
```

---

## 本篇總結

1. **UML 的定義與用途**
   - 標準化的視覺建模語言
   - 溝通、設計、文件化的工具

2. **UML 圖表分類**
   - 結構圖：描述靜態結構
   - 行為圖：描述動態行為

3. **類別圖的完整知識**
   - 基本結構：類別名稱、屬性、方法
   - 六種關係：繼承、實作、關聯、聚合、組合、相依
   - 可見性修飾符：public、private、protected
<!-- 
### 💡 實戰技能

- 如何設計 RPG 遊戲的角色系統
- 如何使用介面實現多重能力
- 類別圖的設計原則

### 📖 下一篇預告

在 **Day 27（中篇）**，我們將學習：

- **使用案例圖**：從使用者角度描述系統功能
- **循序圖**：展示物件之間的互動流程
- 線上購物系統與登入系統的完整範例

---

## 🏷️ 標籤

`#UML` `#類別圖` `#ClassDiagram` `#物件導向設計` `#軟體架構` `#設計模式` `#Java` `#軟體工程` -->
