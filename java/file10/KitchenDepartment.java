package file10;

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