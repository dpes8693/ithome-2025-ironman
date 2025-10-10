package file24;

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
