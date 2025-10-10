package file15;

// 走訪器介面
public interface Iterator<T> {
    boolean hasNext();    // 是否還有下一個元素
    T next();            // 取得下一個元素
    void reset();        // 重置到開始位置
}