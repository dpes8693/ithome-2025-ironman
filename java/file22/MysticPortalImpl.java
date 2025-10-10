package file22;
//MysticPortalImpl.java
// 神秘傳送門實現（瞬間傳送）
public class MysticPortalImpl implements TravelImplementation {
    @Override
    public void startJourney() {
        System.out.println("神秘傳送門開始發光，空間開始扭曲...");
    }

    @Override
    public void travel(String destination) {
        System.out.println("通過傳送門瞬間抵達: " + destination);
        System.out.println("感受到時空穿梭的神奇力量");
    }

    @Override
    public void endJourney() {
        System.out.println("傳送門關閉，空間恢復平靜");
    }

    @Override
    public String getTransportType() {
        return "神秘傳送門";
    }
}
