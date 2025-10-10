package file10;

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