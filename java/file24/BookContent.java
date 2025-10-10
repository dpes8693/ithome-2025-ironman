package file24;

// 書籍內容享元介面
public interface BookContent {
    void displayContent(String readerName, String currentPage, String personalNotes);
    String getTitle();
    String getAuthor();
}
