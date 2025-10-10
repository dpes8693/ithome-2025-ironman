package file7;

// 具體觀察者 - 八卦記者實現
public class Reporter implements Observer {
    private final String name;
    private final String media;

    public Reporter(String name, String media) {
        this.name = name;
        this.media = media;
    }

    @Override
    public void update(String celebrityName, String news) {
        System.out.println("📰 記者 " + name + " (" + media + ") 收到消息:");
        System.out.println("   「" + celebrityName + " " + news + "」");
        System.out.println("   立即發布新聞報導！\n");
    }

    @Override
    public String getReporterName() {
        return name + " (" + media + ")";
    }
}