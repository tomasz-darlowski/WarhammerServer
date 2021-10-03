package pl.darbean.WarhammerServer.model.inventory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public enum WaeponRangeLength {
    VERY_SHORT("Bardzo krótka"),
    SHORT("Krótka"),
    MEDIUM("Średnia"),
    CONTACT("Kontaktowa"),
    LONG("Długa"),
    VERY_LONG("Bardzo długa"),
    HUGE("Ogromna"),
    SIX("6"),
    TWENTY("20"),
    THIRTY("30"),
    FIFTY("50"),
    SIXTY("60"),
    HUNDRED("100"),
    BS("BS",1),
    BSx2("BSx2",2),
    BSx3("BSx3",3);

    private static final Map<String, WaeponRangeLength> labelMap = new LinkedHashMap<>();

    private String label;
    private int bsToRange=0;

    static {
        for (WaeponRangeLength el : values()) {
            labelMap.put(el.label, el);
        }
    }


    WaeponRangeLength(String label) {
        this.label = label;
    }

    WaeponRangeLength(String label, int bsToRange) {
        this.label = label;
        this.bsToRange = bsToRange;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getBsToRange() {
        return bsToRange;
    }

    public void setBsToRange(int bsToRange) {
        this.bsToRange = bsToRange;
    }

    public static Map<String, WaeponRangeLength> getLabelMap() {
        return labelMap;
    }
}
