package pl.darbean.WarhammerServer.model.attributes;

import static pl.darbean.WarhammerServer.model.attributes.CharacterAttribute.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Characteristics {


    private Map<CharacterAttribute, Attrib> attribMap;

    public Characteristics() {
        attribMap = new EnumMap<>(CharacterAttribute.class);
        attribMap.put(WW, new Attrib(WW));
        attribMap.put(US, new Attrib(US));
        attribMap.put(S, new Attrib(S));
        attribMap.put(WT, new Attrib(WT));
        attribMap.put(I, new Attrib(I));
        attribMap.put(ZW, new Attrib(ZW));
        attribMap.put(SW, new Attrib(SW));
        attribMap.put(OGD, new Attrib(OGD));
        attribMap.put(ZR, new Attrib(ZR));
        attribMap.put(INT, new Attrib(INT));
    }

    public Characteristics(List<Attrib> attribsByHeroId) {
        this();
        for (Attrib attrib : attribsByHeroId) {
            attribMap.put(attrib.getAttribute(), attrib);
        }
    }

    public Map<CharacterAttribute, Attrib> getAttribMap() {
        return attribMap;
    }

    public void setAttribMap(Map<CharacterAttribute, Attrib> attribMap) {
        this.attribMap = attribMap;
    }

    public List<Attrib> getAttribList(){
        return new ArrayList<>(attribMap.values());
    }
}
