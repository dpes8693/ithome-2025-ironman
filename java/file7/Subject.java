package file7;

// 被觀察者介面 - 公眾人物
public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String news);
}