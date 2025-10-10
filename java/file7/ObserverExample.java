package file7;

// 使用範例
public class ObserverExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到娛樂圈八卦中心 ===\n");

        // 創建明星（被觀察者）
        Celebrity popStar = new Celebrity("艾莉亞", "流行歌手");
        Celebrity actor = new Celebrity("傑森", "電影明星");

        // 創建記者（觀察者）
        Reporter reporter1 = new Reporter("小王", "娛樂週刊");
        Reporter reporter2 = new Reporter("小李", "八卦日報");
        Reporter reporter3 = new Reporter("小張", "明星雜誌");

        System.out.println("=== 記者開始關注明星 ===");
        // 記者訂閱明星動態
        popStar.addObserver(reporter1);
        popStar.addObserver(reporter2);
        
        actor.addObserver(reporter2);
        actor.addObserver(reporter3);

        System.out.println("\n=== 明星開始活動 ===");
        
        // 流行歌手的活動
        popStar.postSocialMedia("感謝大家支持我的新專輯！💕");
        popStar.attendEvent("金曲獎頒獎典禮");

        // 電影明星的活動
        actor.releaseStatement("我將主演新的超級英雄電影");
        actor.attendEvent("電影首映會");

        System.out.println("\n=== 記者調整關注對象 ===");
        // 記者取消/新增關注
        popStar.removeObserver(reporter1);
        popStar.addObserver(reporter3);

        System.out.println("\n=== 更多明星動態 ===");
        popStar.postSocialMedia("正在錄製新歌中... 🎵");
    }
}