// 使用範例
package file6;

public class DecoratorExample {
    public static void main(String[] args) {
        System.out.println("=== 歡迎來到華麗魔法師的魔法工坊 ===\n");

        // 基礎法術
        System.out.println("--- 基礎法術展示 ---");
        Spell basicFireball = new BasicFireball();
        System.out.println("法術: " + basicFireball.getDescription());
        System.out.println("威力: " + basicFireball.getPower());
        basicFireball.cast();
        System.out.println();

        // 為火球術添加追蹤效果
        System.out.println("--- 魔法師開始施展裝飾魔法 ---");
        Spell trackingFireball = new TrackingEnhancement(basicFireball);
        System.out.println("法術: " + trackingFireball.getDescription());
        System.out.println("威力: " + trackingFireball.getPower());
        trackingFireball.cast();
        System.out.println();

        // 為火球術添加追蹤和火焰附魔雙重效果
        Spell superFireball = new FireEnchantment(new TrackingEnhancement(basicFireball));
        System.out.println("法術: " + superFireball.getDescription());
        System.out.println("威力: " + superFireball.getPower());
        superFireball.cast();
        System.out.println();

        // 為防護盾添加反彈效果
        System.out.println("--- 防護盾強化展示 ---");
        Spell basicShield = new BasicShield();
        Spell reflectShield = new ReflectEnhancement(basicShield);
        System.out.println("法術: " + reflectShield.getDescription());
        System.out.println("威力: " + reflectShield.getPower());
        reflectShield.cast();
        System.out.println();

        // 終極組合：火焰追蹤反彈防護盾
        Spell ultimateShield = new ReflectEnhancement(
            new FireEnchantment(
                new TrackingEnhancement(basicShield)
            )
        );
        System.out.println("--- 終極組合法術 ---");
        System.out.println("法術: " + ultimateShield.getDescription());
        System.out.println("威力: " + ultimateShield.getPower());
        ultimateShield.cast();

        /**output
        === 歡迎來到華麗魔法師的魔法工坊 ===

        --- 基礎法術展示 ---
        法術: 基礎火球術
        威力: 50
        施展基礎火球術！

        --- 魔法師開始施展裝飾魔法 ---
        法術: 基礎火球術 + 追蹤強化
        威力: 70
        施展基礎火球術！
        → 附加追蹤效果，法術會自動鎖定目標！

        法術: 基礎火球術 + 追蹤強化 + 火焰附魔
        威力: 100
        施展基礎火球術！
        → 附加追蹤效果，法術會自動鎖定目標！
        → 附加火焰附魔，造成額外灼燒傷害！

        --- 防護盾強化展示 ---
        法術: 基礎防護盾 + 反彈強化
        威力: 55
        展開基礎防護盾！
        → 附加反彈效果，可以反射敵人攻擊！

        --- 終極組合法術 ---
        法術: 基礎防護盾 + 追蹤強化 + 火焰附魔 + 反彈強化
        威力: 105
        展開基礎防護盾！
        → 附加追蹤效果，法術會自動鎖定目標！
        → 附加火焰附魔，造成額外灼燒傷害！
        → 附加反彈效果，可以反射敵人攻擊！
        */
    }
}