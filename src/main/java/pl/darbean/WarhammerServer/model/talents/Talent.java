package pl.darbean.WarhammerServer.model.talents;

public class Talent {

    public static final String TALENT_ID = "TALENT_ID";

    private String name;
    private int level = 0;
    private String description;

    public Talent() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAdvance() {
        return getLevel();
    }

    public void setAdvance(int advance) {
        setLevel(advance);
    }

    public int getTotal() {
        return getLevel();
    }

    public String getItemLabel() {
        return getName();
    }
}
