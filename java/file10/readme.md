```java
package file10;
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
package file10;
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
package file10;
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
package file10;
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
package file10;
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
package file10;
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
package file10;
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