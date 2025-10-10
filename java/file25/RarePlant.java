package file25;
//RarePlant.java
// 珍稀植物
public class RarePlant implements Appraisable {
    private String name;
    private String species;
    private int medicinalValue;
    private String habitat;
    private int growthTime;

    public RarePlant(String name, String species, int medicinalValue, String habitat, int growthTime) {
        this.name = name;
        this.species = species;
        this.medicinalValue = medicinalValue;
        this.habitat = habitat;
        this.growthTime = growthTime;
    }

    @Override
    public void accept(Appraiser visitor) {
        visitor.appraise(this);
    }

    @Override
    public String getName() {
        return name;
    }

    // Getter methods
    public String getSpecies() {
        return species;
    }

    public int getMedicinalValue() {
        return medicinalValue;
    }

    public String getHabitat() {
        return habitat;
    }

    public int getGrowthTime() {
        return growthTime;
    }
}
