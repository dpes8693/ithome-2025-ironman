package file11;

// 使用範例
public class TemplatePatternExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到天界聖殿 ===");
        System.out.println("首席編纂天使將指導各種神聖儀式的進行\n");

        // 創建不同類型的神聖儀式
        DivineCeremony healingRitual = new HealingCeremony();
        DivineCeremony battleRitual = new BattleCeremony();
        DivineCeremony wisdomRitual = new WisdomCeremony();

        // 展示儀式清單
        System.out.println("📋 今日儀式安排：");
        System.out.println("1. " + healingRitual.getCeremonyName());
        System.out.println("2. " + battleRitual.getCeremonyName());
        System.out.println("3. " + wisdomRitual.getCeremonyName());
        System.out.println();

        // 執行治癒儀式
        System.out.println("【第一場儀式】" + healingRitual.getCeremonyName());
        healingRitual.performCeremony();

        // 執行戰鬥儀式
        System.out.println("【第二場儀式】" + battleRitual.getCeremonyName());
        battleRitual.performCeremony();

        // 執行智慧儀式
        System.out.println("【第三場儀式】" + wisdomRitual.getCeremonyName());
        wisdomRitual.performCeremony();

        System.out.println("=".repeat(50));
        System.out.println("首席編纂天使的樣版方法確保了：");
        System.out.println("- 所有儀式都遵循相同的神聖流程");
        System.out.println("- 每種儀式都能發揮各自的特色");
        System.out.println("- 核心秩序不變，細節可以靈活調整");
    }
}