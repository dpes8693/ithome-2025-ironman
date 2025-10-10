package file15;

// 寶藏走訪器
public class TreasureIterator implements Iterator<Treasure> {
    private Treasure[] treasures;
    private int currentPosition;
    private int size;

    public TreasureIterator(Treasure[] treasures, int size) {
        this.treasures = treasures;
        this.size = size;
        this.currentPosition = 0;
    }

    @Override
    public boolean hasNext() {
        return currentPosition < size;
    }

    @Override
    public Treasure next() {
        if (!hasNext()) {
            throw new RuntimeException("⚠️ 沒有更多寶藏了！");
        }
        Treasure treasure = treasures[currentPosition];
        currentPosition++;
        System.out.println("🗺️ 探險家發現了第 " + currentPosition + " 個寶藏");
        return treasure;
    }

    @Override
    public void reset() {
        currentPosition = 0;
        System.out.println("🔄 探險家回到遺跡入口，準備重新探索");
    }
}