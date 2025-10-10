package file22;
//RainbowBridgeImpl.java
// 彩虹橋實現（陸地交通）
public class RainbowBridgeImpl implements TravelImplementation {
    @Override
    public void startJourney() {
        System.out.println("踏上彩虹橋，感受七彩光芒的指引...");
    }

    @Override
    public void travel(String destination) {
        System.out.println("沿著彩虹橋穩步前進，目標: " + destination);
        System.out.println("橋面在腳下閃爍著美麗的光彩");
    }

    @Override
    public void endJourney() {
        System.out.println("安全抵達目的地，彩虹橋逐漸消散");
    }

    @Override
    public String getTransportType() {
        return "彩虹橋";
    }
}
