package pl.darbean.WarhammerServer.model.inventory;

import java.util.HashMap;
import java.util.Map;

public enum ArmorPlace {
    GLOWA("Głowa"),
    KORPUS("Korpus"),
    NOGI("Nogi"),
    RECE("Ręce"),
    TARCZA("Tarcza");

    private String label;

    static Map<String, ArmorPlace> labelMap;

    static {
        labelMap = new HashMap<>();
        for (ArmorPlace armorPlace : values()) {
            labelMap.put(armorPlace.label, armorPlace);
        }
    }

    ArmorPlace(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Map<String, ArmorPlace> getLabelMap() {
        return labelMap;
    }
}
