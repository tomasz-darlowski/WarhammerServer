package pl.darbean.WarhammerServer.model.inventory;

import pl.darbean.WarhammerServer.model.attributes.Attrib;

public class Weapon {

    private long id;
    private long heroId;
    private String weaponName;
    private int weight;
    private WaeponRangeLength rangeLength;
    private int damage;
    private boolean withStrengthBonus;
    private String attribs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHeroId() {
        return heroId;
    }

    public void setHeroId(long heroId) {
        this.heroId = heroId;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public WaeponRangeLength getRange() {
        return rangeLength;
    }

    public void setRange(WaeponRangeLength range) {
        this.rangeLength = range;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage(Attrib strength) {
        if (isWithStrengthBonus()) {
            return damage + strength.getBonus();
        }
        return damage;
    }

    public String getAttribs() {
        return attribs;
    }

    public void setAttribs(String attribs) {
        this.attribs = attribs;
    }

    public WaeponRangeLength getRangeLength() {
        return rangeLength;
    }

    public void setRangeLength(WaeponRangeLength rangeLength) {
        this.rangeLength = rangeLength;
    }

    public boolean isWithStrengthBonus() {
        return withStrengthBonus;
    }

    public void setWithStrengthBonus(boolean withStrengthBonus) {
        this.withStrengthBonus = withStrengthBonus;
    }
}
