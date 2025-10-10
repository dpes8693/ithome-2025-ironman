package file10;

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