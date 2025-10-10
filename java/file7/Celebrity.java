package file7;

// 具體被觀察者 - 明星實現
import java.util.ArrayList;
import java.util.List;

public class Celebrity implements Subject {
    private final String name;
    private final String profession;
    private final List<Observer> reporters;

    public Celebrity(String name, String profession) {
        this.name = name;
        this.profession = profession;
        this.reporters = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        reporters.add(observer);
        System.out.println("🔔 " + observer.getReporterName() + " 開始關注 " + name);
    }

    @Override
    public void removeObserver(Observer observer) {
        reporters.remove(observer);
        System.out.println("🚫 " + observer.getReporterName() + " 取消關注 " + name);
    }

    @Override
    public void notifyObservers(String news) {
        System.out.println("📢 " + name + " 發布動態: " + news);
        System.out.println("通知 " + reporters.size() + " 位記者...\n");
        
        for (Observer reporter : reporters) {
            reporter.update(name, news);
        }
    }

    // 明星活動方法
    public void postSocialMedia(String content) {
        notifyObservers("在社群媒體發文: " + content);
    }

    public void attendEvent(String eventName) {
        notifyObservers("出席活動: " + eventName);
    }

    public void releaseStatement(String statement) {
        notifyObservers("發表聲明: " + statement);
    }

    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }
}