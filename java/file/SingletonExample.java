package file;

public class SingletonExample {
    public static void main(String[] args) {
        // 嘗試獲取巨龍實例
        DragonGuardian dragon1 = DragonGuardian.getInstance();
        DragonGuardian dragon2 = DragonGuardian.getInstance();

        // 驗證是否為同一個實例
        dragon1.showIdentity();
        dragon2.showIdentity();

        // 使用寶珠力量
        System.out.println(dragon1.getTreasurePower());
        System.out.println(dragon2.getTreasurePower());

        // 驗證兩個變數指向同一個物件
        System.out.println("是同一條龍嗎？ " + (dragon1 == dragon2));
    }
}
