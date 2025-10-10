package file15;

// 聚合物介面
public interface Aggregate<T> {
    Iterator<T> createIterator();
    void addItem(T item);
    int getSize();
}