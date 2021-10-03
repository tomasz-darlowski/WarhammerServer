package pl.darbean.WarhammerServer.model.skills;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Skills {

    private Map<BasicSkill,Skill> basicSkillMap;
    private Map<String, Skill> advancedSkillMap;

    public Skills() {
        basicSkillMap = new EnumMap<>(BasicSkill.class);
        advancedSkillMap = new HashMap<>();
        for (BasicSkill skill : BasicSkill.values()) {
            basicSkillMap.put(skill, new Skill(skill));
        }
    }

    public Skills(List<Skill> basicSkillsByHeroId, List<Skill> advancedSkills) {
        this();
        for (Skill skill : basicSkillsByHeroId) {
            basicSkillMap.put(BasicSkill.getByLabel(skill.getLabel()), skill);
        }
        for (Skill skill : advancedSkills) {
            advancedSkillMap.put(skill.getLabel(), skill);
        }

    }

    public Map<BasicSkill, Skill> getBasicSkillMap() {
        return basicSkillMap;
    }

    public void setBasicSkillMap(Map<BasicSkill, Skill> basicSkillMap) {
        this.basicSkillMap = basicSkillMap;
    }

    public Map<String, Skill> getAdvancedSkillMap() {
        return advancedSkillMap;
    }

    public void setAdvancedSkillMap(Map<String, Skill> advancedSkillMap) {
        this.advancedSkillMap = advancedSkillMap;
    }
}
