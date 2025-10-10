package file23;
// 魔法學徒（管理者）
import java.util.*;

public class MagicApprentice {
    private List<MagicState> mementoHistory;
    private String apprenticeName;

    public MagicApprentice(String name) {
        this.apprenticeName = name;
        this.mementoHistory = new ArrayList<>();
    }

    // 保存備忘錄
    public void saveMemento(MagicState memento) {
        mementoHistory.add(memento);
        System.out.println(apprenticeName + " 保存了第 " + mementoHistory.size() + " 個備忘錄泡泡");
    }

    // 獲取最近的備忘錄
    public MagicState getLastMemento() {
        if (mementoHistory.isEmpty()) {
            System.out.println("沒有可用的備忘錄泡泡！");
            return null;
        }
        MagicState memento = mementoHistory.get(mementoHistory.size() - 1);
        System.out.println(apprenticeName + " 取出最近的備忘錄泡泡");
        return memento;
    }

    // 獲取指定索引的備忘錄
    public MagicState getMementoAt(int index) {
        if (index < 0 || index >= mementoHistory.size()) {
            System.out.println("無效的備忘錄索引！");
            return null;
        }
        System.out.println(apprenticeName + " 取出第 " + (index + 1) + " 個備忘錄泡泡");
        return mementoHistory.get(index);
    }

    // 顯示所有備忘錄歷史
    public void showMementoHistory() {
        System.out.println("=== " + apprenticeName + " 的備忘錄歷史 ===");
        if (mementoHistory.isEmpty()) {
            System.out.println("目前沒有保存任何備忘錄");
            return;
        }

        for (int i = 0; i < mementoHistory.size(); i++) {
            System.out.println((i + 1) + ". " + mementoHistory.get(i));
        }
    }

    // 清除所有備忘錄
    public void clearHistory() {
        mementoHistory.clear();
        System.out.println(apprenticeName + " 清除了所有備忘錄泡泡");
    }

    public int getMementoCount() {
        return mementoHistory.size();
    }
}
