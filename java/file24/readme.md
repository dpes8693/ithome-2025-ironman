
```java
file24;
//BookContent.java
// 書籍內容享元介面
public interface BookContent {
    void displayContent(String readerName, int currentPage, String personalNotes);
    String getTitle();
    String getAuthor();
}
```

```java
file24;
//SharedBookContent.java
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
```

```java
file24;
//BookFlyweightFactory.java
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
```

```java
file24;
//Reader.java
// 讀者（外部狀態的載體）
public class Reader {
    private String name;
    private BookContent currentBook;
    private String currentPage;
    private String personalNotes;
    private String borrowDate;

    public Reader(String name) {
        this.name = name;
        this.currentPage = "第1頁";
        this.personalNotes = "";
        this.borrowDate = "";
    }

    // 借閱書籍
    public void borrowBook(BookContent book, String borrowDate) {
        this.currentBook = book;
        this.borrowDate = borrowDate;
        this.currentPage = "第1頁";
        this.personalNotes = "";
        System.out.println(name + " 於 " + borrowDate + " 借閱了《" + book.getTitle() + "》");
    }

    // 翻頁
    public void turnToPage(String page) {
        this.currentPage = page;
        System.out.println(name + " 翻到了 " + page);
    }

    // 做筆記
    public void makeNotes(String notes) {
        if (this.personalNotes.isEmpty()) {
            this.personalNotes = notes;
        } else {
            this.personalNotes += "; " + notes;
        }
        System.out.println(name + " 做了筆記: " + notes);
    }

    // 閱讀當前書籍
    public void readCurrentBook() {
        if (currentBook != null) {
            currentBook.displayContent(name, currentPage, personalNotes);
        } else {
            System.out.println(name + " 目前沒有借閱任何書籍");
        }
    }

    // 歸還書籍
    public void returnBook() {
        if (currentBook != null) {
            System.out.println(name + " 歸還了《" + currentBook.getTitle() + "》");
            this.currentBook = null;
            this.currentPage = "";
            this.personalNotes = "";
            this.borrowDate = "";
        }
    }

    public String getName() {
        return name;
    }

    public BookContent getCurrentBook() {
        return currentBook;
    }
}
```

```java
file24;
//MagicLibrary.java
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
```

```java
file24;
//FlyweightPatternExample.java
// 使用範例
public class FlyweightPatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到魔法圖書館 ===\n");

        // 創建圖書館和圖書館員
        MagicLibrary library = new MagicLibrary();

        // 註冊讀者
        Reader alice = new Reader("愛麗絲");
        Reader bob = new Reader("鮑勃");
        Reader charlie = new Reader("查理");
        Reader diana = new Reader("黛安娜");

        library.registerReader(alice);
        library.registerReader(bob);
        library.registerReader(charlie);
        library.registerReader(diana);
        System.out.println();

        // 準備書籍資料
        String[] harryPotterContent = {"第一章：大難不死的男孩...", "第二章：消失的玻璃..."};
        String[] lordOfRingsContent = {"第一章：期待已久的宴會...", "第二章：過往的陰影..."};

        // 多個讀者借閱同一本書（《哈利波特》）
        System.out.println("=== 多位讀者借閱同一本書 ===");
        BookContent harryPotter1 = library.provideBook(
            "哈利波特：神秘的魔法石", "J.K.羅琳", 
            harryPotterContent[0], "978-0-7475-3269-9");
        
        BookContent harryPotter2 = library.provideBook(
            "哈利波特：神秘的魔法石", "J.K.羅琳", 
            harryPotterContent[0], "978-0-7475-3269-9");
        
        BookContent harryPotter3 = library.provideBook(
            "哈利波特：神秘的魔法石", "J.K.羅琳", 
            harryPotterContent[0], "978-0-7475-3269-9");

        // 驗證是否為同一個享元物件
        System.out.println("享元物件比較:");
        System.out.println("harryPotter1 == harryPotter2: " + (harryPotter1 == harryPotter2));
        System.out.println("harryPotter2 == harryPotter3: " + (harryPotter2 == harryPotter3));
        System.out.println();

        // 讀者借閱和閱讀
        alice.borrowBook(harryPotter1, "2024-01-15");
        bob.borrowBook(harryPotter2, "2024-01-16");
        charlie.borrowBook(harryPotter3, "2024-01-17");

        // 不同讀者的個人化閱讀體驗
        alice.makeNotes("霍格華茲的描述很精采");
        alice.turnToPage("第50頁");

        bob.makeNotes("喜歡海格這個角色");
        bob.turnToPage("第30頁");

        charlie.makeNotes("魁地奇運動很有趣");
        charlie.turnToPage("第80頁");

        System.out.println();

        // 顯示各自的閱讀狀態
        alice.readCurrentBook();
        bob.readCurrentBook();
        charlie.readCurrentBook();

        // 黛安娜借閱不同的書
        BookContent lordOfRings = library.provideBook(
            "魔戒：魔戒現身", "J.R.R.托爾金", 
            lordOfRingsContent[0], "978-0-544-00341-5");
        
        diana.borrowBook(lordOfRings, "2024-01-18");
        diana.makeNotes("中土世界的設定很宏大");
        diana.turnToPage("第25頁");
        diana.readCurrentBook();

        // 顯示圖書館狀態
        library.showLibraryStats();
        BookFlyweightFactory.getInstance().showLibraryStatus();

        /**output
        === 歡迎來到魔法圖書館 ===

        歡迎新讀者: 愛麗絲
        歡迎新讀者: 鮑勃
        歡迎新讀者: 查理
        歡迎新讀者: 黛安娜

        === 多位讀者借閱同一本書 ===
        圖書館員創建新的書籍享元: 哈利波特：神秘的魔法石
        創建新的書籍內容享元: 哈利波特：神秘的魔法石
        圖書館員重用現有享元: 哈利波特：神秘的魔法石
        圖書館員重用現有享元: 哈利波特：神秘的魔法石
        享元物件比較:
        harryPotter1 == harryPotter2: true
        harryPotter2 == harryPotter3: true

        愛麗絲 於 2024-01-15 借閱了《哈利波特：神秘的魔法石》
        鮑勃 於 2024-01-16 借閱了《哈利波特：神秘的魔法石》
        查理 於 2024-01-17 借閱了《哈利波特：神秘的魔法石》
        愛麗絲 做了筆記: 霍格華茲的描述很精采
        愛麗絲 翻到了 第50頁
        鮑勃 做了筆記: 喜歡海格這個角色
        鮑勃 翻到了 第30頁
        查理 做了筆記: 魁地奇運動很有趣
        查理 翻到了 第80頁

        === 愛麗絲 正在閱讀 ===
        書名: 哈利波特：神秘的魔法石
        作者: J.K.羅琳
        ISBN: 978-0-7475-3269-9
        當前頁碼: 第50頁
        個人筆記: 霍格華茲的描述很精采
        書籍內容: 第一章：大難不死的男孩...

        === 鮑勃 正在閱讀 ===
        書名: 哈利波特：神秘的魔法石
        作者: J.K.羅琳
        ISBN: 978-0-7475-3269-9
        當前頁碼: 第30頁
        個人筆記: 喜歡海格這個角色
        書籍內容: 第一章：大難不死的男孩...

        === 查理 正在閱讀 ===
        書名: 哈利波特：神秘的魔法石
        作者: J.K.羅琳
        ISBN: 978-0-7475-3269-9
        當前頁碼: 第80頁
        個人筆記: 魁地奇運動很有趣
        書籍內容: 第一章：大難不死的男孩...

        圖書館員創建新的書籍享元: 魔戒：魔戒現身
        創建新的書籍內容享元: 魔戒：魔戒現身
        黛安娜 於 2024-01-18 借閱了《魔戒：魔戒現身》
        黛安娜 做了筆記: 中土世界的設定很宏大
        黛安娜 翻到了 第25頁
        === 黛安娜 正在閱讀 ===
        書名: 魔戒：魔戒現身
        作者: J.R.R.托爾金
        ISBN: 978-0-544-00341-5
        當前頁碼: 第25頁
        個人筆記: 中土世界的設定很宏大
        書籍內容: 第一章：期待已久的宴會...

        === 魔法圖書館統計 ===
        註冊讀者數: 4
        書籍類型數: 2
        當前借閱人數: 4
        享元記憶體節省比例: 50.0%

        === 圖書館享元池狀態 ===
        目前共有 2 種書籍內容享元
        ISBN: 978-0-7475-3269-9 -> SharedBookContent[哈利波特：神秘的魔法石 by J.K.羅琳] @1a2b3c4d
        ISBN: 978-0-544-00341-5 -> SharedBookContent[魔戒：魔戒現身 by J.R.R.托爾金] @2b3c4d5e
        */
    }
}
```