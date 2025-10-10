// 使用範例
package file23;
public class MementoPatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到魔法研究學院 ===\n");

        // 創建角色
        YoungWizard wizard = new YoungWizard();
        MagicApprentice apprentice = new MagicApprentice("學徒艾莉絲");

        // 顯示初始狀態
        wizard.showCurrentState();
        System.out.println();

        // 第一個安全點：保存初始狀態
        MagicState checkpoint1 = wizard.createMemento();
        apprentice.saveMemento(checkpoint1);
        System.out.println();

        // 開始實驗：變形術
        wizard.performExperiment("貓形", "初級變形");
        wizard.consumeMana(10);
        System.out.println();

        // 第二個安全點：保存中間狀態
        MagicState checkpoint2 = wizard.createMemento();
        apprentice.saveMemento(checkpoint2);
        System.out.println();

        // 繼續實驗：高級變形
        wizard.performExperiment("龍形", "高級變形");
        wizard.consumeMana(30);
        System.out.println();

        // 實驗出錯！法師變成了青蛙
        wizard.performExperiment("青蛙", "實驗失敗");
        wizard.consumeMana(20);
        System.out.println("糟糕！實驗失敗了！");
        wizard.showCurrentState();
        System.out.println();

        // 顯示備忘錄歷史
        apprentice.showMementoHistory();
        System.out.println();

        // 恢復到第二個安全點（龍形之前）
        System.out.println("=== 恢復到第二個安全點 ===");
        MagicState restorePoint = apprentice.getMementoAt(1);
        wizard.restoreFromMemento(restorePoint);
        wizard.showCurrentState();
        System.out.println();

        // 或者恢復到最初的安全點
        System.out.println("=== 恢復到初始狀態 ===");
        MagicState initialState = apprentice.getMementoAt(0);
        wizard.restoreFromMemento(initialState);
        wizard.showCurrentState();

        /**output
        === 歡迎來到魔法研究學院 ===

        === 年輕法師當前狀態 ===
        魔力:100, 形態:人形, 階段:準備階段

        老法師記錄當前狀態...
        學徒艾莉絲 保存了第 1 個備忘錄泡泡

        正在施展變形術...
        當前狀態: 魔力:80, 形態:貓形, 階段:初級變形
        消耗魔力 10，當前魔力: 70

        老法師記錄當前狀態...
        學徒艾莉絲 保存了第 2 個備忘錄泡泡

        正在施展變形術...
        當前狀態: 魔力:50, 形態:龍形, 階段:高級變形
        消耗魔力 30，當前魔力: 20

        正在施展變形術...
        當前狀態: 魔力:0, 形態:青蛙, 階段:實驗失敗
        消耗魔力 20，當前魔力: -20
        糟糕！實驗失敗了！
        === 年輕法師當前狀態 ===
        魔力:-20, 形態:青蛙, 階段:實驗失敗

        === 學徒艾莉絲 的備忘錄歷史 ===
        1. 魔法狀態[魔力:100, 形態:人形, 階段:準備階段, 時間:1727075847123]
        2. 魔法狀態[魔力:70, 形態:貓形, 階段:初級變形, 時間:1727075847124]

        === 恢復到第二個安全點 ===
        學徒艾莉絲 取出第 2 個備忘錄泡泡
        從備忘錄泡泡中恢復狀態！
        === 年輕法師當前狀態 ===
        魔力:70, 形態:貓形, 階段:初級變形

        === 恢復到初始狀態 ===
        學徒艾莉絲 取出第 1 個備忘錄泡泡
        從備忘錄泡泡中恢復狀態！
        === 年輕法師當前狀態 ===
        魔力:100, 形態:人形, 階段:準備階段
        */
    }
}
