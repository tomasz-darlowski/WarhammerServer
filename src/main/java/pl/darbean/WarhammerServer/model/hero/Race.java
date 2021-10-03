package pl.darbean.WarhammerServer.model.hero;

import java.util.LinkedHashMap;
import java.util.Map;

public enum Race {
    HUMAN("Człowiek",2,1,3,4),
    DWARF("Krasnolud",0,2,2,3),
    HALFLING("Niziołek",0,2,3,3),
    ELF("Elf",0,0,2,5);

    static Map<String, Race> map = new LinkedHashMap<>();

    static {
        for (Race race : values()) {
            map.put(race.label, race);
        }
    }

    private String label;
    private int pp;
    private int pb;
    private int addPoints;
    private int speed;

    Race(String label) {
        this.label = label;
    }

    Race(String label, int pp, int pb, int addPoints, int speed) {
        this.label = label;
        this.pp = pp;
        this.pb = pb;
        this.addPoints = addPoints;
        this.speed = speed;
    }

    public static Race getRaceByName(String name) {
        return map.get(name);
    }

    public static String[] getSpinnerOptions() {
        return map.keySet().toArray(new String[0]);
    }

    public String getLabel() {
        return label;
    }

    public int getPp() {
        return pp;
    }

    public int getPb() {
        return pb;
    }

    public int getAddPoints() {
        return addPoints;
    }

    public int getSpeed() {
        return speed;
    }
}
