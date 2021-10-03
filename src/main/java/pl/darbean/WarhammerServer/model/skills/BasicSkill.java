package pl.darbean.WarhammerServer.model.skills;

import pl.darbean.WarhammerServer.model.attributes.CharacterAttribute;

import java.util.HashMap;
import java.util.Map;

import static pl.darbean.WarhammerServer.model.attributes.CharacterAttribute.*;

public enum BasicSkill {

    ATLETIC(ZW, "Atletyka"),
    WHITE_WEAPON_BASIC(WW, "Biała broń podstawowa"),
    WHITE_WEAPON_ADVANCED(WW, "Biała broń zaawansowana", true),
    CHARISMA(OGD, "Charyzma"),
    LEADERSHIP(OGD, "Dowodzenie"),
    HAZARD(INT, "Hazard"),
    INTUITION(I, "Intuicja"),
    HORSE_RIDING(ZW, "Jeździectwo", true),
    STRONG_HEAD(WT, "Mocna głowa"),
    NAVIGATION(I, "Nawigacja"),
    RESISTANCE(WT, "Odporność"),
    RESTRAINT(SW, "Opanowanie"),
    TAMING(SW, "Oswajanie"),
    PERCEPTION(I, "Percepcja"),
    GOSSIP(I, "Plotkowanie"),
    CARRIAGE(ZW, "Powożenie"),
    BRIBERY(OGD, "Przekupstwo"),
    SNEAKING(ZW, "Skradanie", true),
    ART(ZR, "Sztuka", true),
    SURVIVAL(INT, "Sztuka przetrwania"),
    TRADING(OGD, "Targowanie"),
    DODGE(ZW, "Unik"),
    ROWING(S, "Wioślarstwo"),
    CLIMBING(S, "Wspinaczka"),
    SHOW(OGD, "Występy", true),
    BULLYING(S, "Zastraszanie");


    static Map<String, BasicSkill> labelMap;

    static {
        labelMap = new HashMap<>();
        for (BasicSkill skill : values()) {
            labelMap.put(skill.label, skill);
        }
    }

    final private CharacterAttribute basicAttribute;
    final private String label;
    final private boolean skillNeedSpecification;

    BasicSkill(CharacterAttribute basicAttribute, String label) {
        this.basicAttribute = basicAttribute;
        this.label = label;
        this.skillNeedSpecification = false;
    }

    BasicSkill(CharacterAttribute basicAttribute, String label, boolean skillNeedSpecification) {
        this.basicAttribute = basicAttribute;
        this.label = label;
        this.skillNeedSpecification = skillNeedSpecification;
    }

    public static BasicSkill getByLabel(String label) {
        return labelMap.get(label);
    }

    public CharacterAttribute getBasicAttribute() {
        return basicAttribute;
    }

    public String getLabel() {
        return label;
    }

    public boolean isSkillNeedSpecification() {
        return skillNeedSpecification;
    }
}
