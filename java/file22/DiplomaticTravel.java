package file22;
//DiplomaticTravel.java
// 外交旅行（精煉抽象）
public class DiplomaticTravel extends Travel {
    private String diplomatName;
    private String mission;

    public DiplomaticTravel(TravelImplementation implementation, String diplomatName, String mission) {
        super(implementation);
        this.diplomatName = diplomatName;
        this.mission = mission;
    }

    @Override
    public void planJourney(String destination) {
        System.out.println("=== 外交旅行計劃 ===");
        System.out.println("外交官: " + diplomatName);
        System.out.println("目的地: " + destination);
        System.out.println("任務: " + mission);
        System.out.println("交通工具: " + implementation.getTransportType());
        System.out.println("準備外交文件和禮品...");
    }

    @Override
    public String getTravelType() {
        return "外交旅行";
    }

    public void conductNegotiation() {
        System.out.println(diplomatName + " 通過 " + implementation.getTransportType() + " 展現威嚴，進行 " + mission);
    }
}
