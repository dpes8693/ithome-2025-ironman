package file15;

// 寶藏類
public class Treasure {
    private String name;
    private String description;
    private int value;

    public Treasure(String name, String description, int value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("💎 %s - %s (價值: %d金幣)", name, description, value);
    }
}