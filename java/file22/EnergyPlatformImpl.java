package file22;
//EnergyPlatformImpl.java
// 能量平台實現（空中交通）
public class EnergyPlatformImpl implements TravelImplementation {
    @Override
    public void startJourney() {
        System.out.println("啟動能量平台，浮空引擎開始運轉...");
    }

    @Override
    public void travel(String destination) {
        System.out.println("駕駛能量平台飛向: " + destination);
        System.out.println("平台在空中劃出璀璨的能量軌跡");
    }

    @Override
    public void endJourney() {
        System.out.println("能量平台緩緩降落，引擎停止運轉");
    }

    @Override
    public String getTransportType() {
        return "能量平台";
    }
}
