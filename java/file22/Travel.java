package file22;
//Travel.java
// 旅行抽象類（抽象部分）
public abstract class Travel {
    protected TravelImplementation implementation;

    public Travel(TravelImplementation implementation) {
        this.implementation = implementation;
    }

    // 基本旅行方法
    public void executeJourney(String destination) {
        implementation.startJourney();
        implementation.travel(destination);
        implementation.endJourney();
    }

    // 抽象方法，由子類實現
    public abstract void planJourney(String destination);
    public abstract String getTravelType();
}
