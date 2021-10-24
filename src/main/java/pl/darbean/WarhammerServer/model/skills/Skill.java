package pl.darbean.WarhammerServer.model.skills;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.darbean.WarhammerServer.model.attributes.CharacterAttribute;

public class Skill {

    private CharacterAttribute attribute;
    private String name;
    private int advances;
    private String description;
    private String specialisation;
    @JsonProperty("isBasicSkill")
    private boolean isBasicSkill;

    public Skill() {
    }

    public CharacterAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(CharacterAttribute attribute) {
        this.attribute = attribute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdvances() {
        return advances;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public boolean isBasicSkill() {
        return isBasicSkill;
    }

    public void setBasicSkill(boolean basicSkill) {
        isBasicSkill = basicSkill;
    }

    public void setAdvance(int advance) {
        this.advances=advance;
    }

    public int getAdvance() {
        return getAdvances();
    }

    public int getTotal() {
        return getAdvances();
    }

    public String getItemLabel() {
        return getName();
    }
}
