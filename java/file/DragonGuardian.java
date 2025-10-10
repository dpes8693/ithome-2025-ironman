package file;

public class DragonGuardian {
    // 私有靜態實例
    private static DragonGuardian instance;
    private String treasureOrb;

    // 私有建構子，防止外部直接實例化
    private DragonGuardian() {
        this.treasureOrb = "創世魔法寶珠";
        System.out.println("古老巨龍覺醒了，開始守護寶珠...");
    }

    // 公開的靜態方法，提供全域訪問點
    public static DragonGuardian getInstance() {
        if (instance == null) {
            instance = new DragonGuardian();
        }
        return instance;
    }

    // 取得寶珠的力量
    public String getTreasurePower() {
        return "你獲得了 " + treasureOrb + " 的力量！";
    }

    // 展示巨龍的獨特性
    public void showIdentity() {
        System.out.println("我即是唯一，別無他物。我是 " + this.hashCode());
    }
}

/**
古老巨龍覺醒了，開始守護寶珠...
我即是唯一，別無他物。我是 804564176
我即是唯一，別無他物。我是 804564176
你獲得了 創世魔法寶珠 的力量！
你獲得了 創世魔法寶珠 的力量！
是同一條龍嗎？ true
*/