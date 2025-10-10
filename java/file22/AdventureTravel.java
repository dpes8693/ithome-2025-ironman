package file22;
//AdventureTravel.java
// 冒險旅行（精煉抽象）
public class AdventureTravel extends Travel {
    private String adventurerName;

    public AdventureTravel(TravelImplementation implementation, String adventurerName) {
        super(implementation);
        this.adventurerName = adventurerName;
    }

    @Override
    public void planJourney(String destination) {
        System.out.println("=== 冒險旅行計劃 ===");
        System.out.println("冒險者: " + adventurerName);
        System.out.println("目的地: " + destination);
        System.out.println("交通工具: " + implementation.getTransportType());
        System.out.println("準備冒險裝備和補給...");
    }

    @Override
    public String getTravelType() {
        return "冒險旅行";
    }

    public void encounterChallenge() {
        System.out.println(adventurerName + " 遭遇挑戰，使用 " + implementation.getTransportType() + " 的優勢應對！");
    }
}
