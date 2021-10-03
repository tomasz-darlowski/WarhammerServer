package pl.darbean.WarhammerServer.model.attributes;

import java.util.LinkedHashMap;
import java.util.Map;

public enum CharacterAttribute {
    WW("Walka wręcz"),
    US("Umiejętności strzeleckie"),
    S("Siła"),
    WT("Wytrzymałość"),
    I("Inicjatywa"),
    ZW("Zwinność"),
    ZR("Zręczność"),
    INT("Inteligencja"),
    SW("Siła Woli"),
    OGD("Ogłada");

    static Map<String, CharacterAttribute> map = new LinkedHashMap<>();

    static {
        for (CharacterAttribute attrib : values()) {
            map.put(attrib.label, attrib);
        }
    }

    private String label;

    CharacterAttribute(String label) {
        this.label = label;
    }


    public static CharacterAttribute getAttribByLabel(String name) {
        return map.get(name);
    }

    public static String[] getSpinnerOptions() {
        return map.keySet().toArray(new String[0]);
    }

    public String getLabel() {
        return label;
    }
}
