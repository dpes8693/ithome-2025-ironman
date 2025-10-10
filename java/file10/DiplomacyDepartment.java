package file10;

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