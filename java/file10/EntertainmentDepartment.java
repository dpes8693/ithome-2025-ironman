package file10;

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