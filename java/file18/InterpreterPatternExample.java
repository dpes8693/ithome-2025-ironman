package file18;

// 使用範例
public class InterpreterPatternExample {
    public static void main(String[] args) {
        System.out.println("🏔️ 歡迎來到荒原部落的戰歌儀式 🏔️\n");
        
        // 創建部落符文戰薩
        TribalShaman shaman = new TribalShaman("古魯什‧火拳");
        
        // 測試1：基礎元素符文
        System.out.println("【測試1：基礎元素符文】");
        RuneExpression fireRune = TribalShaman.createElementRune("fire", 3);
        shaman.chantWarSong(fireRune);
        
        // 測試2：戰吼符文
        System.out.println("【測試2：戰吼符文】");
        RuneExpression rageRune = TribalShaman.createWarCryRune("rage", 10);
        shaman.chantWarSong(rageRune);
        
        // 測試3：複合戰歌 - 烈焰怒吼
        System.out.println("【測試3：複合戰歌 - 烈焰狂戰士】");
        CompositeRuneExpression flameWarrior = TribalShaman.createCompositeWarSong("烈焰狂戰士");
        flameWarrior.addRune(TribalShaman.createElementRune("fire", 4));
        flameWarrior.addRune(TribalShaman.createWarCryRune("rage", 15));
        flameWarrior.addRune(TribalShaman.createElementRune("earth", 2));
        shaman.chantWarSong(flameWarrior);
        
        // 測試4：複合戰歌 - 風暴守護者
        System.out.println("【測試4：複合戰歌 - 風暴守護者】");
        CompositeRuneExpression stormGuardian = TribalShaman.createCompositeWarSong("風暴守護者");
        stormGuardian.addRune(TribalShaman.createElementRune("wind", 3));
        stormGuardian.addRune(TribalShaman.createElementRune("water", 3));
        stormGuardian.addRune(TribalShaman.createWarCryRune("rally", 20));
        shaman.chantWarSong(stormGuardian);
        
        // 測試5：終極戰歌 - 部落之怒
        System.out.println("【測試5：終極戰歌 - 部落之怒】");
        CompositeRuneExpression tribalFury = TribalShaman.createCompositeWarSong("部落之怒");
        tribalFury.addRune(TribalShaman.createElementRune("fire", 5));
        tribalFury.addRune(TribalShaman.createElementRune("earth", 4));
        tribalFury.addRune(TribalShaman.createElementRune("wind", 3));
        tribalFury.addRune(TribalShaman.createWarCryRune("rage", 25));
        tribalFury.addRune(TribalShaman.createWarCryRune("fear", 15));
        shaman.chantWarSong(tribalFury);
        
        System.out.println("🌟 戰歌儀式完成！部落戰士們充滿了古老符文的力量！");
    }
}
