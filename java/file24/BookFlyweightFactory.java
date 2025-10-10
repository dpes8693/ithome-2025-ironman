package file24;

// 享元工廠：節儉的圖書館員
import java.util.HashMap;
import java.util.Map;

public class BookFlyweightFactory {
    private static final Map<String, BookContent> bookPool = new HashMap<>();
    private static BookFlyweightFactory instance;

    private BookFlyweightFactory() {}

    public static BookFlyweightFactory getInstance() {
        if (instance == null) {
            instance = new BookFlyweightFactory();
        }
        return instance;
    }

    // 獲取或創建書籍內容享元
    public BookContent getBookContent(String title, String author, String content, String isbn) {
        String key = isbn; // 使用 ISBN 作為唯一標識
        
        BookContent bookContent = bookPool.get(key);
        if (bookContent == null) {
            System.out.println("圖書館員創建新的書籍享元: " + title);
            bookContent = new SharedBookContent(title, author, content, isbn);
            bookPool.put(key, bookContent);
        } else {
            System.out.println("圖書館員重用現有享元: " + title);
        }
        
        return bookContent;
    }

    // 顯示享元池狀態
    public void showLibraryStatus() {
        System.out.println("=== 圖書館享元池狀態 ===");
        System.out.println("目前共有 " + bookPool.size() + " 種書籍內容享元");
        for (Map.Entry<String, BookContent> entry : bookPool.entrySet()) {
            System.out.println("ISBN: " + entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println();
    }

    // 清空享元池
    public void clearLibrary() {
        bookPool.clear();
        System.out.println("圖書館員清空了所有書籍享元");
    }

    public int getBookTypeCount() {
        return bookPool.size();
    }
}
