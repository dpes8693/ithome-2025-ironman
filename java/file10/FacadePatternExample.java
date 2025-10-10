package file10;

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
    }
}