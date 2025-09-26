# Day11 表象(外觀)模式 (Facade Pattern)

## 擬人化角色：【優雅的宮廷總管】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/10-Facade.png)

- 種族： 人類
- 外貌： 一位身穿華麗藍色禮服、佩戴精緻領結和白手套的紳士。他氣度非凡，手持一支手杖，站在富麗堂皇的宮殿書房中。背景可以看到有學者在各自忙碌，但所有人都透過他進行溝通。
- 性格： 從容、周到、富有條理。他將複雜的事務簡化，讓客戶只需與他一人打交道，即可解決多種問題。他善於協調，是各種內部複雜系統的唯一對外窗口。
- 能力： 擔任一個複雜宮廷系統的「前台」。無論是安排宴會、處理外交事務，還是調度僕人，所有請求都會先透過他。他會將這些請求轉發給相應的內部部門（廚房、外交部、僕人管理處），然後將結果回報給客戶。客戶無需了解內部的具體運作，只需與他一人溝通即可。
- 代表語： 「請將您的需求告訴我，我會為您安排妥當。」
- 背景故事： 在一個龐大的帝國宮廷中，這位總管是所有貴族和外賓的唯一聯絡人。宮廷內部有著無數的部門和規矩，但外人無需了解。只需向總管提出要求，他便能巧妙地將請求分發給各個子系統（如外交、內政、禮儀等），並最終回報一個簡單的結果。他以其高效率和無微不至的服務，確保了宮廷對外的運作總是順暢而體面。

---

## 範例

### Java

```java
//KitchenDepartment.java
// 廚房部門 - 內部子系統
public class KitchenDepartment {
    public void prepareBanquet(String guestCount, String menuType) {
        System.out.println("廚房部門：準備宴會");
        System.out.println("  - 客人數量：" + guestCount);
        System.out.println("  - 菜單類型：" + menuType);
        System.out.println("  - 正在準備食材和烹飪...");
        System.out.println("  - 宴會料理準備完成！");
    }

    public void cleanKitchen() {
        System.out.println("廚房部門：清潔廚房設施");
    }
}
```

```java
//DiplomacyDepartment.java
// 外交部門 - 內部子系統
public class DiplomacyDepartment {
    public void arrangeProtocol(String guestRank, String occasion) {
        System.out.println("外交部門：安排外交禮儀");
        System.out.println("  - 貴賓等級：" + guestRank);
        System.out.println("  - 場合類型：" + occasion);
        System.out.println("  - 制定接待規格和儀式流程");
        System.out.println("  - 外交禮儀安排完成！");
    }

    public void sendInvitations(String guestList) {
        System.out.println("外交部門：發送邀請函給 " + guestList);
    }
}
```

```java
//ServantDepartment.java
// 僕人管理部門 - 內部子系統
public class ServantDepartment {
    public void assignStaff(String eventType, int staffCount) {
        System.out.println("僕人管理部門：分配人力");
        System.out.println("  - 活動類型：" + eventType);
        System.out.println("  - 所需人數：" + staffCount + "名僕人");
        System.out.println("  - 分配侍者、守衛和清潔人員");
        System.out.println("  - 人力配置完成！");
    }

    public void briefStaff(String instructions) {
        System.out.println("僕人管理部門：向全體員工說明 - " + instructions);
    }
}
```

```java
//SecurityDepartment.java
// 安全部門 - 內部子系統
public class SecurityDepartment {
    public void arrangeSecurity(String securityLevel, String venue) {
        System.out.println("安全部門：部署安全措施");
        System.out.println("  - 安全等級：" + securityLevel);
        System.out.println("  - 場地：" + venue);
        System.out.println("  - 安排守衛和巡邏路線");
        System.out.println("  - 安全部署完成！");
    }

    public void screenGuests() {
        System.out.println("安全部門：進行來賓身份驗證");
    }
}
```

```java
//EntertainmentDepartment.java
// 娛樂部門 - 內部子系統
public class EntertainmentDepartment {
    public void arrangeEntertainment(String entertainmentType, String duration) {
        System.out.println("娛樂部門：安排表演節目");
        System.out.println("  - 表演類型：" + entertainmentType);
        System.out.println("  - 表演時長：" + duration);
        System.out.println("  - 聯繫樂師和舞者");
        System.out.println("  - 娛樂節目安排完成！");
    }

    public void setupStage() {
        System.out.println("娛樂部門：搭建表演舞台");
    }
}
```

```java
//PalaceButlerFacade.java
// 宮廷總管 - 外觀類
public class PalaceButlerFacade {
    // 內部子系統實例
    private KitchenDepartment kitchen;
    private DiplomacyDepartment diplomacy;
    private ServantDepartment servants;
    private SecurityDepartment security;
    private EntertainmentDepartment entertainment;

    public PalaceButlerFacade() {
        // 初始化所有子系統
        this.kitchen = new KitchenDepartment();
        this.diplomacy = new DiplomacyDepartment();
        this.servants = new ServantDepartment();
        this.security = new SecurityDepartment();
        this.entertainment = new EntertainmentDepartment();

        System.out.println("宮廷總管就職：準備為您提供完善服務");
    }

    // 簡化的宴會安排介面
    public void arrangeFormalBanquet(String guestCount, String vipGuests) {
        System.out.println("\n=== 宮廷總管：安排正式宴會 ===");
        System.out.println("請將您的需求告訴我，我會為您安排妥當。");
        System.out.println("收到請求：為 " + guestCount + " 位貴賓（包含 " + vipGuests + "）安排正式宴會\n");

        // 協調各部門工作
        diplomacy.arrangeProtocol("高級貴族", "正式宴會");
        diplomacy.sendInvitations(vipGuests);

        kitchen.prepareBanquet(guestCount, "皇室盛宴");

        servants.assignStaff("正式宴會", 15);
        servants.briefStaff("確保服務品質，注意貴賓禮儀");

        security.arrangeSecurity("高級", "宴會大廳");
        security.screenGuests();

        entertainment.arrangeEntertainment("古典音樂與舞蹈", "3小時");
        entertainment.setupStage();

        System.out.println("\n宮廷總管：正式宴會已完美安排，敬候貴賓蒞臨！");
        System.out.println("=".repeat(45));
    }

    // 簡化的外交會談安排介面
    public void arrangeDiplomaticMeeting(String foreignDelegate, String meetingTopic) {
        System.out.println("\n=== 宮廷總管：安排外交會談 ===");
        System.out.println("收到請求：與 " + foreignDelegate + " 進行 " + meetingTopic + " 會談\n");

        // 協調相關部門
        diplomacy.arrangeProtocol("外國使節", "外交會談");

        servants.assignStaff("外交會談", 8);
        servants.briefStaff("保持機密，提供茶水服務");

        security.arrangeSecurity("最高級", "外交會議室");

        kitchen.prepareBanquet("10人", "精緻茶點");

        System.out.println("\n宮廷總管：外交會談已妥善安排，確保會談順利進行！");
        System.out.println("=".repeat(45));
    }

    // 簡化的節慶慶典安排介面
    public void arrangeFestivalCelebration(String festivalName, String expectedGuests) {
        System.out.println("\n=== 宮廷總管：安排節慶慶典 ===");
        System.out.println("收到請求：舉辦 " + festivalName + "，預計 " + expectedGuests + " 參與\n");

        // 協調所有部門準備大型活動
        diplomacy.arrangeProtocol("平民與貴族", "公眾慶典");

        kitchen.prepareBanquet(expectedGuests, "節慶美食");

        servants.assignStaff("大型慶典", 30);
        servants.briefStaff("維持秩序，協助民眾");

        security.arrangeSecurity("標準", "整個宮廷廣場");

        entertainment.arrangeEntertainment("民俗表演與煙火", "一整天");
        entertainment.setupStage();

        System.out.println("\n宮廷總管：" + festivalName + " 慶典準備就緒，將是一場盛大的慶祝！");
        System.out.println("=".repeat(45));
    }

    // 提供系統狀態查詢
    public void getSystemStatus() {
        System.out.println("\n=== 宮廷總管：各部門狀態報告 ===");
        System.out.println("✓ 廚房部門：運作正常");
        System.out.println("✓ 外交部門：運作正常");
        System.out.println("✓ 僕人管理：運作正常");
        System.out.println("✓ 安全部門：運作正常");
        System.out.println("✓ 娛樂部門：運作正常");
        System.out.println("宮廷系統一切就緒，隨時為您服務！\n");
    }
}
```

```java
//FacadePatternExample.java
// 使用範例
public class FacadePatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到帝國宮廷 ===\n");

        // 創建宮廷總管（外觀）
        PalaceButlerFacade butler = new PalaceButlerFacade();

        // 查詢系統狀態
        butler.getSystemStatus();

        // 測試案例 1：安排正式宴會
        System.out.println("【貴族請求】需要為聯姻慶典安排宴會");
        butler.arrangeFormalBanquet("50人", "鄰國王室成員");

        // 測試案例 2：安排外交會談
        System.out.println("\n【國王指令】安排重要外交會議");
        butler.arrangeDiplomaticMeeting("東方帝國使節團", "貿易協定談判");

        // 測試案例 3：安排節慶慶典
        System.out.println("\n【民眾請願】希望舉辦豐收節慶典");
        butler.arrangeFestivalCelebration("豐收節", "500人");

        System.out.println("\n" + "=".repeat(50));
        System.out.println("宮廷總管的工作展現了外觀模式的精髓：");
        System.out.println("- 客戶只需與總管一人溝通");
        System.out.println("- 複雜的內部協調工作被隱藏");
        System.out.println("- 提供簡化且統一的服務介面");

        /**output
        === 歡迎來到帝國宮廷 ===

        宮廷總管就職：準備為您提供完善服務

        === 宮廷總管：各部門狀態報告 ===
        ✓ 廚房部門：運作正常
        ✓ 外交部門：運作正常
        ✓ 僕人管理：運作正常
        ✓ 安全部門：運作正常
        ✓ 娛樂部門：運作正常
        宮廷系統一切就緒，隨時為您服務！

        【貴族請求】需要為聯姻慶典安排宴會

        === 宮廷總管：安排正式宴會 ===
        請將您的需求告訴我，我會為您安排妥當。
        收到請求：為 50人 位貴賓（包含 鄰國王室成員）安排正式宴會

        外交部門：安排外交禮儀
          - 貴賓等級：高級貴族
          - 場合類型：正式宴會
          - 制定接待規格和儀式流程
          - 外交禮儀安排完成！
        外交部門：發送邀請函給 鄰國王室成員
        廚房部門：準備宴會
          - 客人數量：50人
          - 菜單類型：皇室盛宴
          - 正在準備食材和烹飪...
          - 宴會料理準備完成！
        僕人管理部門：分配人力
          - 活動類型：正式宴會
          - 所需人數：15名僕人
          - 分配侍者、守衛和清潔人員
          - 人力配置完成！
        僕人管理部門：向全體員工說明 - 確保服務品質，注意貴賓禮儀
        安全部門：部署安全措施
          - 安全等級：高級
          - 場地：宴會大廳
          - 安排守衛和巡邏路線
          - 安全部署完成！
        安全部門：進行來賓身份驗證
        娛樂部門：安排表演節目
          - 表演類型：古典音樂與舞蹈
          - 表演時長：3小時
          - 聯繫樂師和舞者
          - 娛樂節目安排完成！
        娛樂部門：搭建表演舞台

        宮廷總管：正式宴會已完美安排，敬候貴賓蒞臨！
        =============================================

        【國王指令】安排重要外交會議

        === 宮廷總管：安排外交會談 ===
        收到請求：與 東方帝國使節團 進行 貿易協定談判 會談

        外交部門：安排外交禮儀
          - 貴賓等級：外國使節
          - 場合類型：外交會談
          - 制定接待規格和儀式流程
          - 外交禮儀安排完成！
        僕人管理部門：分配人力
          - 活動類型：外交會談
          - 所需人數：8名僕人
          - 分配侍者、守衛和清潔人員
          - 人力配置完成！
        僕人管理部門：向全體員工說明 - 保持機密，提供茶水服務
        安全部門：部署安全措施
          - 安全等級：最高級
          - 場地：外交會議室
          - 安排守衛和巡邏路線
          - 安全部署完成！
        廚房部門：準備宴會
          - 客人數量：10人
          - 菜單類型：精緻茶點
          - 正在準備食材和烹飪...
          - 宴會料理準備完成！

        宮廷總管：外交會談已妥善安排，確保會談順利進行！
        =============================================

        【民眾請願】希望舉辦豐收節慶典

        === 宮廷總管：安排節慶慶典 ===
        收到請求：舉辦 豐收節，預計 500人 參與

        外交部門：安排外交禮儀
          - 貴賓等級：平民與貴族
          - 場合類型：公眾慶典
          - 制定接待規格和儀式流程
          - 外交禮儀安排完成！
        廚房部門：準備宴會
          - 客人數量：500人
          - 菜單類型：節慶美食
          - 正在準備食材和烹飪...
          - 宴會料理準備完成！
        僕人管理部門：分配人力
          - 活動類型：大型慶典
          - 所需人數：30名僕人
          - 分配侍者、守衛和清潔人員
          - 人力配置完成！
        僕人管理部門：向全體員工說明 - 維持秩序，協助民眾
        安全部門：部署安全措施
          - 安全等級：標準
          - 場地：整個宮廷廣場
          - 安排守衛和巡邏路線
          - 安全部署完成！
        娛樂部門：安排表演節目
          - 表演類型：民俗表演與煙火
          - 表演時長：一整天
          - 聯繫樂師和舞者
          - 娛樂節目安排完成！
        娛樂部門：搭建表演舞台

        宮廷總管：豐收節 慶典準備就緒，將是一場盛大的慶祝！
        =============================================

        ==================================================
        宮廷總管的工作展現了外觀模式的精髓：
        - 客戶只需與總管一人溝通
        - 複雜的內部協調工作被隱藏
        - 提供簡化且統一的服務介面
        */
    }
}
```

### JavaScript

```javascript
// 廚房部門 - 內部子系統
class KitchenDepartment {
  prepareBanquet(guestCount, menuType) {
    console.log("廚房部門：準備宴會");
    console.log(`  - 客人數量：${guestCount}`);
    console.log(`  - 菜單類型：${menuType}`);
    console.log("  - 正在準備食材和烹飪...");
    console.log("  - 宴會料理準備完成！");
  }

  cleanKitchen() {
    console.log("廚房部門：清潔廚房設施");
  }
}

// 外交部門 - 內部子系統
class DiplomacyDepartment {
  arrangeProtocol(guestRank, occasion) {
    console.log("外交部門：安排外交禮儀");
    console.log(`  - 貴賓等級：${guestRank}`);
    console.log(`  - 場合類型：${occasion}`);
    console.log("  - 制定接待規格和儀式流程");
    console.log("  - 外交禮儀安排完成！");
  }

  sendInvitations(guestList) {
    console.log(`外交部門：發送邀請函給 ${guestList}`);
  }
}

// 僕人管理部門 - 內部子系統
class ServantDepartment {
  assignStaff(eventType, staffCount) {
    console.log("僕人管理部門：分配人力");
    console.log(`  - 活動類型：${eventType}`);
    console.log(`  - 所需人數：${staffCount}名僕人`);
    console.log("  - 分配侍者、守衛和清潔人員");
    console.log("  - 人力配置完成！");
  }

  briefStaff(instructions) {
    console.log(`僕人管理部門：向全體員工說明 - ${instructions}`);
  }
}

// 安全部門 - 內部子系統
class SecurityDepartment {
  arrangeSecurity(securityLevel, venue) {
    console.log("安全部門：部署安全措施");
    console.log(`  - 安全等級：${securityLevel}`);
    console.log(`  - 場地：${venue}`);
    console.log("  - 安排守衛和巡邏路線");
    console.log("  - 安全部署完成！");
  }

  screenGuests() {
    console.log("安全部門：進行來賓身份驗證");
  }
}

// 娛樂部門 - 內部子系統
class EntertainmentDepartment {
  arrangeEntertainment(entertainmentType, duration) {
    console.log("娛樂部門：安排表演節目");
    console.log(`  - 表演類型：${entertainmentType}`);
    console.log(`  - 表演時長：${duration}`);
    console.log("  - 聯繫樂師和舞者");
    console.log("  - 娛樂節目安排完成！");
  }

  setupStage() {
    console.log("娛樂部門：搭建表演舞台");
  }
}

// 宮廷總管 - 外觀類
class PalaceButlerFacade {
  constructor() {
    // 初始化所有子系統
    this.kitchen = new KitchenDepartment();
    this.diplomacy = new DiplomacyDepartment();
    this.servants = new ServantDepartment();
    this.security = new SecurityDepartment();
    this.entertainment = new EntertainmentDepartment();

    console.log("宮廷總管就職：準備為您提供完善服務");
  }

  // 簡化的宴會安排介面
  arrangeFormalBanquet(guestCount, vipGuests) {
    console.log("\n=== 宮廷總管：安排正式宴會 ===");
    console.log("請將您的需求告訴我，我會為您安排妥當。");
    console.log(
      `收到請求：為 ${guestCount} 位貴賓（包含 ${vipGuests}）安排正式宴會\n`
    );

    // 協調各部門工作
    this.diplomacy.arrangeProtocol("高級貴族", "正式宴會");
    this.diplomacy.sendInvitations(vipGuests);

    this.kitchen.prepareBanquet(guestCount, "皇室盛宴");

    this.servants.assignStaff("正式宴會", 15);
    this.servants.briefStaff("確保服務品質，注意貴賓禮儀");

    this.security.arrangeSecurity("高級", "宴會大廳");
    this.security.screenGuests();

    this.entertainment.arrangeEntertainment("古典音樂與舞蹈", "3小時");
    this.entertainment.setupStage();

    console.log("\n宮廷總管：正式宴會已完美安排，敬候貴賓蒞臨！");
    console.log("=".repeat(45));
  }

  // 簡化的外交會談安排介面
  arrangeDiplomaticMeeting(foreignDelegate, meetingTopic) {
    console.log("\n=== 宮廷總管：安排外交會談 ===");
    console.log(`收到請求：與 ${foreignDelegate} 進行 ${meetingTopic} 會談\n`);

    // 協調相關部門
    this.diplomacy.arrangeProtocol("外國使節", "外交會談");

    this.servants.assignStaff("外交會談", 8);
    this.servants.briefStaff("保持機密，提供茶水服務");

    this.security.arrangeSecurity("最高級", "外交會議室");

    this.kitchen.prepareBanquet("10人", "精緻茶點");

    console.log("\n宮廷總管：外交會談已妥善安排，確保會談順利進行！");
    console.log("=".repeat(45));
  }

  // 簡化的節慶慶典安排介面
  arrangeFestivalCelebration(festivalName, expectedGuests) {
    console.log("\n=== 宮廷總管：安排節慶慶典 ===");
    console.log(
      `收到請求：舉辦 ${festivalName}，預計 ${expectedGuests} 參與\n`
    );

    // 協調所有部門準備大型活動
    this.diplomacy.arrangeProtocol("平民與貴族", "公眾慶典");

    this.kitchen.prepareBanquet(expectedGuests, "節慶美食");

    this.servants.assignStaff("大型慶典", 30);
    this.servants.briefStaff("維持秩序，協助民眾");

    this.security.arrangeSecurity("標準", "整個宮廷廣場");

    this.entertainment.arrangeEntertainment("民俗表演與煙火", "一整天");
    this.entertainment.setupStage();

    console.log(
      `\n宮廷總管：${festivalName} 慶典準備就緒，將是一場盛大的慶祝！`
    );
    console.log("=".repeat(45));
  }

  // 提供系統狀態查詢
  getSystemStatus() {
    console.log("\n=== 宮廷總管：各部門狀態報告 ===");
    console.log("✓ 廚房部門：運作正常");
    console.log("✓ 外交部門：運作正常");
    console.log("✓ 僕人管理：運作正常");
    console.log("✓ 安全部門：運作正常");
    console.log("✓ 娛樂部門：運作正常");
    console.log("宮廷系統一切就緒，隨時為您服務！\n");
  }
}

// 使用範例
console.log("=== 歡迎來到帝國宮廷 ===\n");

// 創建宮廷總管（外觀）
const butler = new PalaceButlerFacade();

// 查詢系統狀態
butler.getSystemStatus();

// 測試案例 1：安排正式宴會
console.log("【貴族請求】需要為聯姻慶典安排宴會");
butler.arrangeFormalBanquet("50人", "鄰國王室成員");

// 測試案例 2：安排外交會談
console.log("\n【國王指令】安排重要外交會議");
butler.arrangeDiplomaticMeeting("東方帝國使節團", "貿易協定談判");

// 測試案例 3：安排節慶慶典
console.log("\n【民眾請願】希望舉辦豐收節慶典");
butler.arrangeFestivalCelebration("豐收節", "500人");

console.log("\n" + "=".repeat(50));
console.log("宮廷總管的工作展現了外觀模式的精髓：");
console.log("- 客戶只需與總管一人溝通");
console.log("- 複雜的內部協調工作被隱藏");
console.log("- 提供簡化且統一的服務介面");

/** output
=== 歡迎來到帝國宮廷 ===

宮廷總管就職：準備為您提供完善服務

=== 宮廷總管：各部門狀態報告 ===
✓ 廚房部門：運作正常
✓ 外交部門：運作正常
✓ 僕人管理：運作正常
✓ 安全部門：運作正常
✓ 娛樂部門：運作正常
宮廷系統一切就緒，隨時為您服務！

【貴族請求】需要為聯姻慶典安排宴會

=== 宮廷總管：安排正式宴會 ===
請將您的需求告訴我，我會為您安排妥當。
收到請求：為 50人 位貴賓（包含 鄰國王室成員）安排正式宴會

外交部門：安排外交禮儀
  - 貴賓等級：高級貴族
  - 場合類型：正式宴會
  - 制定接待規格和儀式流程
  - 外交禮儀安排完成！
外交部門：發送邀請函給 鄰國王室成員
廚房部門：準備宴會
  - 客人數量：50人
  - 菜單類型：皇室盛宴
  - 正在準備食材和烹飪...
  - 宴會料理準備完成！
僕人管理部門：分配人力
  - 活動類型：正式宴會
  - 所需人數：15名僕人
  - 分配侍者、守衛和清潔人員
  - 人力配置完成！
僕人管理部門：向全體員工說明 - 確保服務品質，注意貴賓禮儀
安全部門：部署安全措施
  - 安全等級：高級
  - 場地：宴會大廳
  - 安排守衛和巡邏路線
  - 安全部署完成！
安全部門：進行來賓身份驗證
娛樂部門：安排表演節目
  - 表演類型：古典音樂與舞蹈
  - 表演時長：3小時
  - 聯繫樂師和舞者
  - 娛樂節目安排完成！
娛樂部門：搭建表演舞台

宮廷總管：正式宴會已完美安排，敬候貴賓蒞臨！
=============================================

【國王指令】安排重要外交會議

=== 宮廷總管：安排外交會談 ===
收到請求：與 東方帝國使節團 進行 貿易協定談判 會談

外交部門：安排外交禮儀
  - 貴賓等級：外國使節
  - 場合類型：外交會談
  - 制定接待規格和儀式流程
  - 外交禮儀安排完成！
僕人管理部門：分配人力
  - 活動類型：外交會談
  - 所需人數：8名僕人
  - 分配侍者、守衛和清潔人員
  - 人力配置完成！
僕人管理部門：向全體員工說明 - 保持機密，提供茶水服務
安全部門：部署安全措施
  - 安全等級：最高級
  - 場地：外交會議室
  - 安排守衛和巡邏路線
  - 安全部署完成！
廚房部門：準備宴會
  - 客人數量：10人
  - 菜單類型：精緻茶點
  - 正在準備食材和烹飪...
  - 宴會料理準備完成！

宮廷總管：外交會談已妥善安排，確保會談順利進行！
=============================================

【民眾請願】希望舉辦豐收節慶典

=== 宮廷總管：安排節慶慶典 ===
收到請求：舉辦 豐收節，預計 500人 參與

外交部門：安排外交禮儀
  - 貴賓等級：平民與貴族
  - 場合類型：公眾慶典
  - 制定接待規格和儀式流程
  - 外交禮儀安排完成！
廚房部門：準備宴會
  - 客人數量：500人
  - 菜單類型：節慶美食
  - 正在準備食材和烹飪...
  - 宴會料理準備完成！
僕人管理部門：分配人力
  - 活動類型：大型慶典
  - 所需人數：30名僕人
  - 分配侍者、守衛和清潔人員
  - 人力配置完成！
僕人管理部門：向全體員工說明 - 維持秩序，協助民眾
安全部門：部署安全措施
  - 安全等級：標準
  - 場地：整個宮廷廣場
  - 安排守衛和巡邏路線
  - 安全部署完成！
娛樂部門：安排表演節目
  - 表演類型：民俗表演與煙火
  - 表演時長：一整天
  - 聯繫樂師和舞者
  - 娛樂節目安排完成！
娛樂部門：搭建表演舞台

宮廷總管：豐收節 慶典準備就緒，將是一場盛大的慶祝！
=============================================

==================================================
宮廷總管的工作展現了外觀模式的精髓：
- 客戶只需與總管一人溝通
- 複雜的內部協調工作被隱藏
- 提供簡化且統一的服務介面
 */
```

## 小總結

Facade Pattern 設計模式就像我們故事中的優雅宮廷總管，提供`簡化的統一介面`來存取複雜子系統的功能

**核心特點：**

- **統一介面**：為複雜的子系統提供一個簡單、統一的介面
- **隱藏複雜性**：客戶端不需要了解子系統的內部實作細節
- **鬆散耦合**：客戶端與子系統之間的依賴關係被外觀類隔離
- **可選擇性使用**：客戶端可以選擇直接使用子系統或透過外觀類

**使用時機：**

- 想要將客戶端與子系統解耦（ex: API Gateway 統一管理微服務）
- 需要為舊有系統提供新的、更清晰的介面（ex: 包裝舊系統）
- 系統層次化設計，每層都需要定義入口點（ex: 分層架構的服務介面）

**注意事項：**

- 外觀類可能變成「萬能類」，承擔過多責任
- 不當使用可能隱藏重要的系統細節，降低靈活性
- 需要在簡化介面與功能完整性之間取得平衡
- 適合穩定的子系統，頻繁變動的系統不適用
- 要避免外觀類成為系統的瓶頸或單點故障
