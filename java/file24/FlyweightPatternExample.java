package file24;

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
    }
}
