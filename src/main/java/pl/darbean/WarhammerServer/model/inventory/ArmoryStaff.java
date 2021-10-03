package pl.darbean.WarhammerServer.model.inventory;

public class ArmoryStaff {

    private String armorName = "";
    private ArmorPlace place;
    private int weight = 0;
    private int armorPoints = 0;
    private String attributes = "";

    public ArmoryStaff() {
    }

    public ArmoryStaff(ArmorPlace armorPlace) {
        this.place = armorPlace;
    }

    public String getArmorName() {
        return armorName;
    }

    public void setArmorName(String armorName) {
        this.armorName = armorName;
    }

    public ArmorPlace getPlace() {
        return place;
    }

    public void setPlace(ArmorPlace place) {
        this.place = place;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getArmorPoints() {
        return armorPoints;
    }

    public void setArmorPoints(int armorPoints) {
        this.armorPoints = armorPoints;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
}
