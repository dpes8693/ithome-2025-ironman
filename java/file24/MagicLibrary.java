package file24;

// 魔法圖書館
import java.util.ArrayList;
import java.util.List;

public class MagicLibrary {
    private BookFlyweightFactory librarian;
    private List<Reader> readers;

    public MagicLibrary() {
        this.librarian = BookFlyweightFactory.getInstance();
        this.readers = new ArrayList<>();
    }

    // 註冊讀者
    public void registerReader(Reader reader) {
        readers.add(reader);
        System.out.println("歡迎新讀者: " + reader.getName());
    }

    // 為讀者提供書籍
    public BookContent provideBook(String title, String author, String content, String isbn) {
        return librarian.getBookContent(title, author, content, isbn);
    }

    // 顯示圖書館統計資訊
    public void showLibraryStats() {
        System.out.println("=== 魔法圖書館統計 ===");
        System.out.println("註冊讀者數: " + readers.size());
        System.out.println("書籍類型數: " + librarian.getBookTypeCount());
        
        // 統計當前借閱情況
        int borrowingCount = 0;
        for (Reader reader : readers) {
            if (reader.getCurrentBook() != null) {
                borrowingCount++;
            }
        }
        System.out.println("當前借閱人數: " + borrowingCount);
        System.out.println("享元記憶體節省比例: " + 
                         String.format("%.1f%%", 
                         (1.0 - (double)librarian.getBookTypeCount() / Math.max(borrowingCount, 1)) * 100));
        System.out.println();
    }
}
