package file24;

// 具體享元：共享的書籍內容
public class SharedBookContent implements BookContent {
    private final String title;
    private final String author;
    private final String content;
    private final String isbn;

    public SharedBookContent(String title, String author, String content, String isbn) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.isbn = isbn;
        System.out.println("創建新的書籍內容享元: " + title);
    }

    @Override
    public void displayContent(String readerName, String currentPage, String personalNotes) {
        System.out.println("=== " + readerName + " 正在閱讀 ===");
        System.out.println("書名: " + title);
        System.out.println("作者: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("當前頁碼: " + currentPage);
        if (personalNotes != null && !personalNotes.isEmpty()) {
            System.out.println("個人筆記: " + personalNotes);
        }
        System.out.println("書籍內容: " + content);
        System.out.println();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    // 獲取享元物件的記憶體資訊
    @Override
    public String toString() {
        return String.format("SharedBookContent[%s by %s] @%s", 
                           title, author, Integer.toHexString(hashCode()));
    }
}
