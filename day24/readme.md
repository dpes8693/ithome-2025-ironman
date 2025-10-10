# Day24 享元模式 (Flyweight Pattern)

## 擬人化角色：【節儉的圖書館員】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/23-Flyweight.png)

- 種族： 人類
- 外貌： 一位身著樸素制服的年輕女性圖書館員，她專注地閱讀一本發光的古籍。在她周圍，有無數半透明的、一模一樣的幽靈學生在閱讀同一本書的不同副本。她的桌上堆滿了書籍，旁邊有一個老式的算盤。
- 性格： 節儉、高效、注重資源共享。她深知資源的珍貴，因此會盡力讓相似的物件共享共同的資源，以減少浪費。
- 能力： 讓大量物件共享一些共同性質，降低系統的負擔。在她的圖書館中，所有的「書本」都是獨一無二的實體，但如果有很多讀者想看同一本書，她並不會為每個人都提供一本全新的書。相反，她會讓所有讀者「共享」同一份書的「核心內容」，只為每個讀者提供他們各自的「閱讀進度」或「筆記」等獨有部分。
- 代表語： 「知識雖廣，但源頭皆一，何不共享？」
- 背景故事： 這位圖書館員管理著一個擁有海量書籍的魔法圖書館。由於書籍數量巨大，如果每本書都有獨立的魔法副本，會耗費巨大的魔法能量。因此，她設計了一種巧妙的方法：對於那些內容完全相同的書籍，她只保留一份「核心」魔法本，所有借閱者都通過這個核心本獲得知識。每個借閱者雖然感覺自己拿到了一本書，但實際上，他們只是共享了同一份「內容」，而各自的「書籤」或「批註」等個人數據是獨立的。這大大降低了圖書館的運營負擔。

---

## 範例

### Java

```java
//BookContent.java
// 書籍內容享元介面
public interface BookContent {
    void displayContent(String readerName, int currentPage, String personalNotes);
    String getTitle();
    String getAuthor();
}
```

```java
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

    // 獲取享元物件的記憶體資訊 ex: @6e2c634b
    @Override
    public String toString() {
        return String.format("SharedBookContent[%s by %s] @%s",
                           title, author, Integer.toHexString(hashCode()));
    }
}
```

```java
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
        ISBN: 978-0-7475-3269-9 -> SharedBookContent[哈利波特：神秘的魔法石 by J.K.羅琳] @6e2c634b
        ISBN: 978-0-544-00341-5 -> SharedBookContent[魔戒：魔戒現身 by J.R.R.托爾金] @7e6cbb7a
        */
    }
}
```

### JavaScript

```javascript
// 書籍內容享元類
class SharedBookContent {
  constructor(title, author, content, isbn) {
    this.title = title;
    this.author = author;
    this.content = content;
    this.isbn = isbn;
    console.log(`創建新的書籍內容享元: ${title}`);
  }

  displayContent(readerName, currentPage, personalNotes) {
    console.log(`=== ${readerName} 正在閱讀 ===`);
    console.log(`書名: ${this.title}`);
    console.log(`作者: ${this.author}`);
    console.log(`ISBN: ${this.isbn}`);
    console.log(`當前頁碼: ${currentPage}`);
    if (personalNotes && personalNotes.length > 0) {
      console.log(`個人筆記: ${personalNotes}`);
    }
    console.log(`書籍內容: ${this.content}`);
    console.log("");
  }

  getTitle() {
    return this.title;
  }

  getAuthor() {
    return this.author;
  }

  toString() {
    return `SharedBookContent[${this.title} by ${this.author}]`;
  }
}

// 享元工廠：節儉的圖書館員
class BookFlyweightFactory {
  static instance = null;
  static bookPool = new Map();

  static getInstance() {
    if (!BookFlyweightFactory.instance) {
      BookFlyweightFactory.instance = new BookFlyweightFactory();
    }
    return BookFlyweightFactory.instance;
  }

  // 獲取或創建書籍內容享元
  getBookContent(title, author, content, isbn) {
    const key = isbn; // 使用 ISBN 作為唯一標識

    let bookContent = BookFlyweightFactory.bookPool.get(key);
    if (!bookContent) {
      console.log(`圖書館員創建新的書籍享元: ${title}`);
      bookContent = new SharedBookContent(title, author, content, isbn);
      BookFlyweightFactory.bookPool.set(key, bookContent);
    } else {
      console.log(`圖書館員重用現有享元: ${title}`);
    }

    return bookContent;
  }

  // 顯示享元池狀態
  showLibraryStatus() {
    console.log("=== 圖書館享元池狀態 ===");
    console.log(
      `目前共有 ${BookFlyweightFactory.bookPool.size} 種書籍內容享元`
    );
    for (const [isbn, bookContent] of BookFlyweightFactory.bookPool) {
      console.log(`ISBN: ${isbn} -> ${bookContent}`);
    }
    console.log("");
  }

  // 清空享元池
  clearLibrary() {
    BookFlyweightFactory.bookPool.clear();
    console.log("圖書館員清空了所有書籍享元");
  }

  getBookTypeCount() {
    return BookFlyweightFactory.bookPool.size;
  }
}

// 讀者（外部狀態的載體）
class Reader {
  constructor(name) {
    this.name = name;
    this.currentBook = null;
    this.currentPage = "第1頁";
    this.personalNotes = "";
    this.borrowDate = "";
  }

  // 借閱書籍
  borrowBook(book, borrowDate) {
    this.currentBook = book;
    this.borrowDate = borrowDate;
    this.currentPage = "第1頁";
    this.personalNotes = "";
    console.log(`${this.name} 於 ${borrowDate} 借閱了《${book.getTitle()}》`);
  }

  // 翻頁
  turnToPage(page) {
    this.currentPage = page;
    console.log(`${this.name} 翻到了 ${page}`);
  }

  // 做筆記
  makeNotes(notes) {
    if (this.personalNotes.length === 0) {
      this.personalNotes = notes;
    } else {
      this.personalNotes += "; " + notes;
    }
    console.log(`${this.name} 做了筆記: ${notes}`);
  }

  // 閱讀當前書籍
  readCurrentBook() {
    if (this.currentBook) {
      this.currentBook.displayContent(
        this.name,
        this.currentPage,
        this.personalNotes
      );
    } else {
      console.log(`${this.name} 目前沒有借閱任何書籍`);
    }
  }

  // 歸還書籍
  returnBook() {
    if (this.currentBook) {
      console.log(`${this.name} 歸還了《${this.currentBook.getTitle()}》`);
      this.currentBook = null;
      this.currentPage = "";
      this.personalNotes = "";
      this.borrowDate = "";
    }
  }

  getName() {
    return this.name;
  }

  getCurrentBook() {
    return this.currentBook;
  }
}

// 魔法圖書館
class MagicLibrary {
  constructor() {
    this.librarian = BookFlyweightFactory.getInstance();
    this.readers = [];
  }

  // 註冊讀者
  registerReader(reader) {
    this.readers.push(reader);
    console.log(`歡迎新讀者: ${reader.getName()}`);
  }

  // 為讀者提供書籍
  provideBook(title, author, content, isbn) {
    return this.librarian.getBookContent(title, author, content, isbn);
  }

  // 顯示圖書館統計資訊
  showLibraryStats() {
    console.log("=== 魔法圖書館統計 ===");
    console.log(`註冊讀者數: ${this.readers.length}`);
    console.log(`書籍類型數: ${this.librarian.getBookTypeCount()}`);

    // 統計當前借閱情況
    const borrowingCount = this.readers.filter(
      (reader) => reader.getCurrentBook() !== null
    ).length;
    console.log(`當前借閱人數: ${borrowingCount}`);
    console.log(
      `享元記憶體節省比例: ${(
        (1.0 -
          this.librarian.getBookTypeCount() / Math.max(borrowingCount, 1)) *
        100
      ).toFixed(1)}%`
    );
    console.log("");
  }
}

// 使用範例
console.log("=== 歡迎來到魔法圖書館 ===\n");

// 創建圖書館和圖書館員
const library = new MagicLibrary();

// 註冊讀者
const alice = new Reader("愛麗絲");
const bob = new Reader("鮑勃");
const charlie = new Reader("查理");
const diana = new Reader("黛安娜");

library.registerReader(alice);
library.registerReader(bob);
library.registerReader(charlie);
library.registerReader(diana);
console.log("");

// 準備書籍資料
const harryPotterContent = [
  "第一章：大難不死的男孩...",
  "第二章：消失的玻璃...",
];
const lordOfRingsContent = [
  "第一章：期待已久的宴會...",
  "第二章：過往的陰影...",
];

// 多個讀者借閱同一本書（《哈利波特》）
console.log("=== 多位讀者借閱同一本書 ===");
const harryPotter1 = library.provideBook(
  "哈利波特：神秘的魔法石",
  "J.K.羅琳",
  harryPotterContent[0],
  "978-0-7475-3269-9"
);

const harryPotter2 = library.provideBook(
  "哈利波特：神秘的魔法石",
  "J.K.羅琳",
  harryPotterContent[0],
  "978-0-7475-3269-9"
);

const harryPotter3 = library.provideBook(
  "哈利波特：神秘的魔法石",
  "J.K.羅琳",
  harryPotterContent[0],
  "978-0-7475-3269-9"
);

// 驗證是否為同一個享元物件
console.log("享元物件比較:");
console.log(`harryPotter1 === harryPotter2: ${harryPotter1 === harryPotter2}`);
console.log(`harryPotter2 === harryPotter3: ${harryPotter2 === harryPotter3}`);
console.log("");

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

console.log("");

// 顯示各自的閱讀狀態
alice.readCurrentBook();
bob.readCurrentBook();
charlie.readCurrentBook();

// 黛安娜借閱不同的書
const lordOfRings = library.provideBook(
  "魔戒：魔戒現身",
  "J.R.R.托爾金",
  lordOfRingsContent[0],
  "978-0-544-00341-5"
);

diana.borrowBook(lordOfRings, "2024-01-18");
diana.makeNotes("中土世界的設定很宏大");
diana.turnToPage("第25頁");
diana.readCurrentBook();

// 顯示圖書館狀態
library.showLibraryStats();
BookFlyweightFactory.getInstance().showLibraryStatus();

/** output
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
harryPotter1 === harryPotter2: true
harryPotter2 === harryPotter3: true

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
ISBN: 978-0-7475-3269-9 -> SharedBookContent[哈利波特：神秘的魔法石 by J.K.羅琳]
ISBN: 978-0-544-00341-5 -> SharedBookContent[魔戒：魔戒現身 by J.R.R.托爾金]
 */
```

## 小總結

Flyweight 設計模式就像我們故事中的節儉圖書館員，透過`共享內部狀態`來大幅減少記憶體使用量

**核心特點：**

- **內部狀態共享**：多個物件共享相同的內部狀態，只創建一份實例
- **外部狀態分離**：將變化的外部狀態從享元物件中分離出來
- **工廠模式控制**：使用工廠來確保享元物件的唯一性和重用
- **記憶體優化**：當需要大量相似物件時，能顯著節省記憶體

**使用時機：**

- 系統中存在大量相似物件（ex: 遊戲中的樹木、子彈物件）
- 物件的多數狀態可以設為外部狀態（ex: 文字編輯器中的字串格式）
<!-- - 記憶體成本是系統瓶頸（ex: 移動裝置應用程式）
- 物件群組可以用較少的共享物件替代（ex: 圖形系統中的圖案） -->
<!-- 
**注意事項：**


- 需要明確區分內部狀態和外部狀態，設計時要謹慎考慮
- 外部狀態的傳遞可能增加程式碼複雜度
- 享元工廠需要管理物件生命週期，避免記憶體洩漏
- 適合用於物件數量龐大且狀態相似度高的場景
- 執行時間可能因為外部狀態的計算而增加 -->

## 補充

```java
// 享元模式:根據不同的內部狀態,可能有多個享元實例
BookFlyweightFactory factory = BookFlyweightFactory.getInstance();
BookContent book1 = factory.getBookContent("書名1", "作者1", "內容1", "ISBN1"); // 實例1
BookContent book2 = factory.getBookContent("書名2", "作者2", "內容2", "ISBN2"); // 實例2
BookContent book3 = factory.getBookContent("書名1", "作者1", "內容1", "ISBN1"); // 重用實例1
// book1 == book3 (true), 但 book1 != book2

// 單例模式:全域只有一個實例
BookFlyweightFactory factory1 = BookFlyweightFactory.getInstance(); // 唯一實例
BookFlyweightFactory factory2 = BookFlyweightFactory.getInstance(); // 相同實例
// factory1 == factory2 (true)
```
