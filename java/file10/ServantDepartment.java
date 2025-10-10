package file10;

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