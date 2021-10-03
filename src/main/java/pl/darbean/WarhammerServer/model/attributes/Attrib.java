package pl.darbean.WarhammerServer.model.attributes;

public class Attrib {

    private CharacterAttribute attribute;
    private int attribLevel = 20;
    private int attribDeveloped = 0;


    public Attrib(long id, long hero_id, CharacterAttribute attribute, int attribLevel, int attribDeveloped) {
        this.attribute = attribute;
        this.attribLevel = attribLevel;
        this.attribDeveloped = attribDeveloped;
    }

    public Attrib(CharacterAttribute characteristic) {
        attribute = characteristic;
    }

    public int getTotal() {
        return attribLevel + attribDeveloped;
    }

    public int getBonus(){
        return (attribLevel+attribDeveloped)/10;
    }

    public int getAttribLevel() {
        return attribLevel;
    }

    public void setAttribLevel(int attribLevel) {
        this.attribLevel = attribLevel;
    }

    public int getAttribDeveloped() {
        return attribDeveloped;
    }

    public void setAttribDeveloped(int attribDeveloped) {
        this.attribDeveloped = attribDeveloped;
    }

    public CharacterAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(CharacterAttribute attribute) {
        this.attribute = attribute;
    }

    public void setAdvance(int advance) {
        setAttribDeveloped(advance);
    }

    public int getAdvance() {
        return getAttribDeveloped();
    }

    public String getItemLabel() {
        return getAttribute().getLabel();
    }

}
