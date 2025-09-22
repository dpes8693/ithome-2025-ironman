# Day8 觀察者模式 (Observer Pattern)

## 擬人化角色：擬人化角色：【敏銳的八卦記者】

![](https://raw.githubusercontent.com/dpes8693/ithome-2025-ironman/refs/heads/main/gemini-img/7-Observer.png)

- 種族： 人類
- 外貌： 一位身穿風衣、手持專業相機和發光平板的年輕女記者，她坐在城市高樓的邊緣，在漆黑的雨夜中，城市霓虹燈閃爍。她的平板上顯示著多個社交媒體的實時動態，而空中則有無數發光的數據線連接到城市中無數的「幽靈」人物，這些幽靈代表著她的「觀察對象」。
- 性格： 好奇、靈活、無孔不入。她不直接參與事件，而是專注於「訂閱」感興趣的對象或事件，一旦有任何「更新」，她便能立即知曉並採取行動。
- 能力： 處理一個物件對應多個物件之間的連動關係。這位記者（觀察者）會「訂閱」多個公眾人物（被觀察者）的動態。一旦這些公眾人物發布了新的消息、出席了活動，或者有了任何的「狀態變化」，記者就會立即收到通知，並可以選擇寫新聞、拍照或錄影。而公眾人物並不需要知道有多少記者在關注他們，只需要發布自己的動態即可。
- 代表語： 「世界風起雲湧，我知天下事。」
- 背景故事： 在一個訊息爆炸的未來都市，這位記者是訊息傳播的核心。她利用高科技裝備，隨時監控著數百位公眾人物、政治家和明星的網路動態和公開活動。一旦有新的「事件」發生，她的平板就會發出警報，她便能迅速撰寫報導，或派出攝影團隊。她的存在確保了任何重要的「變化」都能被即時捕捉，並傳達給廣大民眾。

---

## 範例

### Java

```java
//Observer.java
// 觀察者介面 - 八卦記者
public interface Observer {
    void update(String celebrityName, String news);
    String getReporterName();
}
```

```java
//Subject.java
// 被觀察者介面 - 公眾人物
public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String news);
}
```

```java
//Reporter.java
// 具體觀察者 - 八卦記者實現
public class Reporter implements Observer {
    private String name;
    private String media;

    public Reporter(String name, String media) {
        this.name = name;
        this.media = media;
    }

    @Override
    public void update(String celebrityName, String news) {
        System.out.println("📰 記者 " + name + " (" + media + ") 收到消息:");
        System.out.println("   「" + celebrityName + " " + news + "」");
        System.out.println("   立即發布新聞報導！\n");
    }

    @Override
    public String getReporterName() {
        return name + " (" + media + ")";
    }
}
```

```java
//Celebrity.java
// 具體被觀察者 - 明星實現
import java.util.ArrayList;
import java.util.List;

public class Celebrity implements Subject {
    private String name;
    private String profession;
    private List<Observer> reporters;

    public Celebrity(String name, String profession) {
        this.name = name;
        this.profession = profession;
        this.reporters = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        reporters.add(observer);
        System.out.println("🔔 " + observer.getReporterName() + " 開始關注 " + name);
    }

    @Override
    public void removeObserver(Observer observer) {
        reporters.remove(observer);
        System.out.println("🚫 " + observer.getReporterName() + " 取消關注 " + name);
    }

    @Override
    public void notifyObservers(String news) {
        System.out.println("📢 " + name + " 發布動態: " + news);
        System.out.println("通知 " + reporters.size() + " 位記者...\n");
        
        for (Observer reporter : reporters) {
            reporter.update(name, news);
        }
    }

    // 明星活動方法
    public void postSocialMedia(String content) {
        notifyObservers("在社群媒體發文: " + content);
    }

    public void attendEvent(String eventName) {
        notifyObservers("出席活動: " + eventName);
    }

    public void releaseStatement(String statement) {
        notifyObservers("發表聲明: " + statement);
    }

    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }
}
```

```java
//ObserverExample.java
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

        System.out.println("=== 記者調整關注對象 ===");
        // 記者取消/新增關注
        popStar.removeObserver(reporter1);
        popStar.addObserver(reporter3);

        System.out.println("\n=== 更多明星動態 ===");
        popStar.postSocialMedia("正在錄製新歌中... 🎵");

        /**output
        === 歡迎來到娛樂圈八卦中心 ===

        === 記者開始關注明星 ===
        🔔 小王 (娛樂週刊) 開始關注 艾莉亞
        🔔 小李 (八卦日報) 開始關注 艾莉亞
        🔔 小李 (八卦日報) 開始關注 傑森
        🔔 小張 (明星雜誌) 開始關注 傑森

        === 明星開始活動 ===
        📢 艾莉亞 發布動態: 在社群媒體發文: 感謝大家支持我的新專輯！💕
        通知 2 位記者...

        📰 記者 小王 (娛樂週刊) 收到消息:
           「艾莉亞 在社群媒體發文: 感謝大家支持我的新專輯！💕」
           立即發布新聞報導！

        📰 記者 小李 (八卦日報) 收到消息:
           「艾莉亞 在社群媒體發文: 感謝大家支持我的新專輯！💕」
           立即發布新聞報導！

        📢 艾莉亞 發布動態: 出席活動: 金曲獎頒獎典禮
        通知 2 位記者...

        📰 記者 小王 (娛樂週刊) 收到消息:
           「艾莉亞 出席活動: 金曲獎頒獎典禮」
           立即發布新聞報導！

        📰 記者 小李 (八卦日報) 收到消息:
           「艾莉亞 出席活動: 金曲獎頒獎典禮」
           立即發布新聞報導！

        📢 傑森 發布動態: 發表聲明: 我將主演新的超級英雄電影
        通知 2 位記者...

        📰 記者 小李 (八卦日報) 收到消息:
           「傑森 發表聲明: 我將主演新的超級英雄電影」
           立即發布新聞報導！

        📰 記者 小張 (明星雜誌) 收到消息:
           「傑森 發表聲明: 我將主演新的超級英雄電影」
           立即發布新聞報導！

        📢 傑森 發布動態: 出席活動: 電影首映會
        通知 2 位記者...

        📰 記者 小李 (八卦日報) 收到消息:
           「傑森 出席活動: 電影首映會」
           立即發布新聞報導！

        📰 記者 小張 (明星雜誌) 收到消息:
           「傑森 出席活動: 電影首映會」
           立即發布新聞報導！

        === 記者調整關注對象 ===
        🚫 小王 (娛樂週刊) 取消關注 艾莉亞
        🔔 小張 (明星雜誌) 開始關注 艾莉亞

        === 更多明星動態 ===
        📢 艾莉亞 發布動態: 在社群媒體發文: 正在錄製新歌中... 🎵
        通知 2 位記者...

        📰 記者 小李 (八卦日報) 收到消息:
           「艾莉亞 在社群媒體發文: 正在錄製新歌中... 🎵」
           立即發布新聞報導！

        📰 記者 小張 (明星雜誌) 收到消息:
           「艾莉亞 在社群媒體發文: 正在錄製新歌中... 🎵」
           立即發布新聞報導！
        */
    }
}
```

### JavaScript

```javascript
// 觀察者介面 - 八卦記者
class Observer {
  update(celebrityName, news) {
    throw new Error("子類必須實現 update 方法");
  }

  getReporterName() {
    throw new Error("子類必須實現 getReporterName 方法");
  }
}

// 被觀察者介面 - 公眾人物
class Subject {
  addObserver(observer) {
    throw new Error("子類必須實現 addObserver 方法");
  }

  removeObserver(observer) {
    throw new Error("子類必須實現 removeObserver 方法");
  }

  notifyObservers(news) {
    throw new Error("子類必須實現 notifyObservers 方法");
  }
}

// 具體觀察者 - 八卦記者實現
class Reporter extends Observer {
  constructor(name, media) {
    super();
    this.name = name;
    this.media = media;
  }

  update(celebrityName, news) {
    console.log(`📰 記者 ${this.name} (${this.media}) 收到消息:`);
    console.log(`   「${celebrityName} ${news}」`);
    console.log(`   立即發布新聞報導！\n`);
  }

  getReporterName() {
    return `${this.name} (${this.media})`;
  }
}

// 具體被觀察者 - 明星實現
class Celebrity extends Subject {
  constructor(name, profession) {
    super();
    this.name = name;
    this.profession = profession;
    this.reporters = [];
  }

  addObserver(observer) {
    this.reporters.push(observer);
    console.log(`🔔 ${observer.getReporterName()} 開始關注 ${this.name}`);
  }

  removeObserver(observer) {
    const index = this.reporters.indexOf(observer);
    if (index > -1) {
      this.reporters.splice(index, 1);
      console.log(`🚫 ${observer.getReporterName()} 取消關注 ${this.name}`);
    }
  }

  notifyObservers(news) {
    console.log(`📢 ${this.name} 發布動態: ${news}`);
    console.log(`通知 ${this.reporters.length} 位記者...\n`);
    
    this.reporters.forEach(reporter => {
      reporter.update(this.name, news);
    });
  }

  // 明星活動方法
  postSocialMedia(content) {
    this.notifyObservers(`在社群媒體發文: ${content}`);
  }

  attendEvent(eventName) {
    this.notifyObservers(`出席活動: ${eventName}`);
  }

  releaseStatement(statement) {
    this.notifyObservers(`發表聲明: ${statement}`);
  }

  getName() {
    return this.name;
  }

  getProfession() {
    return this.profession;
  }
}

// 使用範例
console.log("=== 歡迎來到娛樂圈八卦中心 ===\n");

// 創建明星（被觀察者）
const popStar = new Celebrity("艾莉亞", "流行歌手");
const actor = new Celebrity("傑森", "電影明星");

// 創建記者（觀察者）
const reporter1 = new Reporter("小王", "娛樂週刊");
const reporter2 = new Reporter("小李", "八卦日報");
const reporter3 = new Reporter("小張", "明星雜誌");

console.log("=== 記者開始關注明星 ===");
// 記者訂閱明星動態
popStar.addObserver(reporter1);
popStar.addObserver(reporter2);

actor.addObserver(reporter2);
actor.addObserver(reporter3);

console.log("\n=== 明星開始活動 ===");

// 流行歌手的活動
popStar.postSocialMedia("感謝大家支持我的新專輯！💕");
popStar.attendEvent("金曲獎頒獎典禮");

// 電影明星的活動
actor.releaseStatement("我將主演新的超級英雄電影");
actor.attendEvent("電影首映會");

console.log("=== 記者調整關注對象 ===");
// 記者取消/新增關注
popStar.removeObserver(reporter1);
popStar.addObserver(reporter3);

console.log("\n=== 更多明星動態 ===");
popStar.postSocialMedia("正在錄製新歌中... 🎵");

/** output
=== 歡迎來到娛樂圈八卦中心 ===

=== 記者開始關注明星 ===
🔔 小王 (娛樂週刊) 開始關注 艾莉亞
🔔 小李 (八卦日報) 開始關注 艾莉亞
🔔 小李 (八卦日報) 開始關注 傑森
🔔 小張 (明星雜誌) 開始關注 傑森

=== 明星開始活動 ===
📢 艾莉亞 發布動態: 在社群媒體發文: 感謝大家支持我的新專輯！💕
通知 2 位記者...

📰 記者 小王 (娛樂週刊) 收到消息:
   「艾莉亞 在社群媒體發文: 感謝大家支持我的新專輯！💕」
   立即發布新聞報導！

📰 記者 小李 (八卦日報) 收到消息:
   「艾莉亞 在社群媒體發文: 感謝大家支持我的新專輯！💕」
   立即發布新聞報導！

📢 艾莉亞 發布動態: 出席活動: 金曲獎頒獎典禮
通知 2 位記者...

📰 記者 小王 (娛樂週刊) 收到消息:
   「艾莉亞 出席活動: 金曲獎頒獎典禮」
   立即發布新聞報導！

📰 記者 小李 (八卦日報) 收到消息:
   「艾莉亞 出席活動: 金曲獎頒獎典禮」
   立即發布新聞報導！

📢 傑森 發布動態: 發表聲明: 我將主演新的超級英雄電影
通知 2 位記者...

📰 記者 小李 (八卦日報) 收到消息:
   「傑森 發表聲明: 我將主演新的超級英雄電影」
   立即發布新聞報導！

📰 記者 小張 (明星雜誌) 收到消息:
   「傑森 發表聲明: 我將主演新的超級英雄電影」
   立即發布新聞報導！

📢 傑森 發布動態: 出席活動: 電影首映會
通知 2 位記者...

📰 記者 小李 (八卦日報) 收到消息:
   「傑森 出席活動: 電影首映會」
   立即發布新聞報導！

📰 記者 小張 (明星雜誌) 收到消息:
   「傑森 出席活動: 電影首映會」
   立即發布新聞報導！

=== 記者調整關注對象 ===
🚫 小王 (娛樂週刊) 取消關注 艾莉亞
🔔 小張 (明星雜誌) 開始關注 艾莉亞

=== 更多明星動態 ===
📢 艾莉亞 發布動態: 在社群媒體發文: 正在錄製新歌中... 🎵
通知 2 位記者...

📰 記者 小李 (八卦日報) 收到消息:
   「艾莉亞 在社群媒體發文: 正在錄製新歌中... 🎵」
   立即發布新聞報導！

📰 記者 小張 (明星雜誌) 收到消息:
   「艾莉亞 在社群媒體發文: 正在錄製新歌中... 🎵」
   立即發布新聞報導！
 */
```

## 小總結

Observer 設計模式就像我們故事中的敏銳八卦記者，建立了一個`一對多的依賴關係`，當一個物件的狀態改變時，所有依賴它的物件都會自動收到通知並更新。

**核心特點：**

- **鬆散耦合**：被觀察者不需要知道觀察者的具體實現細節
- **動態訂閱**：觀察者可以在運行時動態地訂閱或取消訂閱
- **廣播通信**：一個事件可以同時通知多個觀察者
- **開放封閉原則**：新增觀察者不需要修改被觀察者的程式碼

**使用時機：**

- 需要在物件狀態改變時通知多個相關物件（ex: MVC 架構中的 Model-View 關係）
- 實現事件驅動的程式設計（ex: UI 事件處理、訊息推送系統）
- 需要降低物件間的耦合度（ex: 發布-訂閱系統）

**注意事項：**

- 觀察者數量過多可能影響通知效能
- 如果觀察者的 update 方法執行時間過長，會阻塞通知流程
- 需要注意記憶體洩漏問題，確保不再使用的觀察者能被正確移除
- 通知順序可能影響結果，需要謹慎設計
- 可以考慮使用非同步通知來提高效能
