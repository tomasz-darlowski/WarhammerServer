package pl.darbean.WarhammerServer.model.skills;

import pl.darbean.WarhammerServer.model.attributes.CharacterAttribute;

public class Skill {

    public static final String SKILL_ID = "SKILL_ID";

    private CharacterAttribute attribute;
    private String label;
    private int advances = 0;
    private String description;
    private String specialisation = "";
    private boolean isSkillNeedSpecification = false;
    private boolean isBasicSkill = false;

    public Skill() {
    }

    public Skill(BasicSkill skill) {
        this.attribute = skill.getBasicAttribute();
        this.label = skill.getLabel();
        this.advances = 0;
        this.description = "";
        this.specialisation = "";
        this.isSkillNeedSpecification = skill.isSkillNeedSpecification();
        this.isBasicSkill = true;
    }

    public CharacterAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(CharacterAttribute attribute) {
        this.attribute = attribute;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public boolean isSkillNeedSpecification() {
        return isSkillNeedSpecification;
    }

    public void setSkillNeedSpecification(boolean skillNeedSpecification) {
        isSkillNeedSpecification = skillNeedSpecification;
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
        return getLabel();
    }
}
