```java
package file7;
//Observer.java

// 觀察者介面 - 八卦記者

publicinterfaceObserver {

    voidupdate(StringcelebrityName, Stringnews);

    StringgetReporterName();

}

```

```java
package file7;
//Subject.java

// 被觀察者介面 - 公眾人物

publicinterfaceSubject {

    voidaddObserver(Observerobserver);

    voidremoveObserver(Observerobserver);

    voidnotifyObservers(Stringnews);

}

```

```java
package file7;
//Reporter.java

// 具體觀察者 - 八卦記者實現

publicclassReporterimplementsObserver {

    privateStringname;

    privateStringmedia;


    publicReporter(Stringname, Stringmedia) {

        this.name = name;

        this.media = media;

    }


    @Override

    publicvoidupdate(StringcelebrityName, Stringnews) {

        System.out.println("📰 記者 " + name + " (" + media + ") 收到消息:");

        System.out.println("   「" + celebrityName + " " + news + "」");

        System.out.println("   立即發布新聞報導！\n");

    }


    @Override

    publicStringgetReporterName() {

        return name + " (" + media + ")";

    }

}

```

```java
package file7;
//Celebrity.java

// 具體被觀察者 - 明星實現

import java.util.ArrayList;

import java.util.List;


publicclassCelebrityimplementsSubject {

    privateStringname;

    privateStringprofession;

    privateList<Observer> reporters;


    publicCelebrity(Stringname, Stringprofession) {

        this.name = name;

        this.profession = profession;

        this.reporters = newArrayList<>();

    }


    @Override

    publicvoidaddObserver(Observerobserver) {

        reporters.add(observer);

        System.out.println("🔔 " + observer.getReporterName() + " 開始關注 " + name);

    }


    @Override

    publicvoidremoveObserver(Observerobserver) {

        reporters.remove(observer);

        System.out.println("🚫 " + observer.getReporterName() + " 取消關注 " + name);

    }


    @Override

    publicvoidnotifyObservers(Stringnews) {

        System.out.println("📢 " + name + " 發布動態: " + news);

        System.out.println("通知 " + reporters.size() + " 位記者...\n");

      

        for (Observerreporter: reporters) {

            reporter.update(name, news);

        }

    }


    // 明星活動方法

    publicvoidpostSocialMedia(Stringcontent) {

        notifyObservers("在社群媒體發文: " + content);

    }


    publicvoidattendEvent(StringeventName) {

        notifyObservers("出席活動: " + eventName);

    }


    publicvoidreleaseStatement(Stringstatement) {

        notifyObservers("發表聲明: " + statement);

    }


    publicStringgetName() {

        return name;

    }


    publicStringgetProfession() {

        return profession;

    }

}

```

```java
package file7;
//ObserverExample.java

// 使用範例

publicclassObserverExample {

    publicstaticvoidmain(String[] args) {

        System.out.println("=== 歡迎來到娛樂圈八卦中心 ===\n");


        // 創建明星（被觀察者）

        CelebritypopStar = newCelebrity("艾莉亞", "流行歌手");

        Celebrityactor = newCelebrity("傑森", "電影明星");


        // 創建記者（觀察者）

        Reporterreporter1 = newReporter("小王", "娛樂週刊");

        Reporterreporter2 = newReporter("小李", "八卦日報");

        Reporterreporter3 = newReporter("小張", "明星雜誌");


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
