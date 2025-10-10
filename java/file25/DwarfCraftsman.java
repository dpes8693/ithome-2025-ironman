package file25;
//DwarfCraftsman.java
// 矮人工匠
public class DwarfCraftsman implements Appraisable {
    private String name;
    private int craftingLevel;
    private String specialty;
    private int yearsOfExperience;

    public DwarfCraftsman(String name, int craftingLevel, String specialty, int yearsOfExperience) {
        this.name = name;
        this.craftingLevel = craftingLevel;
        this.specialty = specialty;
        this.yearsOfExperience = yearsOfExperience;
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
    public int getCraftingLevel() {
        return craftingLevel;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
}
