package file7;

// 觀察者介面 - 八卦記者
public interface Observer {
    void update(String celebrityName, String news);
    String getReporterName();
}