package pl.darbean.WarhammerServer.model;

import pl.darbean.WarhammerServer.model.attributes.Attrib;
import pl.darbean.WarhammerServer.model.hero.Hero;
import pl.darbean.WarhammerServer.model.inventory.ArmoryStaff;
import pl.darbean.WarhammerServer.model.inventory.Item;
import pl.darbean.WarhammerServer.model.inventory.Weapon;
import pl.darbean.WarhammerServer.model.pouch.Pouch;
import pl.darbean.WarhammerServer.model.skills.Skill;
import pl.darbean.WarhammerServer.model.talents.Talent;

import java.util.List;

public class ImportExportJsonObject {

    private Hero hero;
    private List<Attrib> attribs;
    private List<Skill> basicSkills;
    private List<Skill> advancedSkills;
    private List<Talent> talents;
    private List<ArmoryStaff> armoryStaffsList;
    private Pouch pouch;
    private List<Item> items;
    private List<Weapon> weapons;

    public ImportExportJsonObject() {

    }

    public ImportExportJsonObject(Hero hero, List<Attrib> attribs, List<Skill> basicSkills, List<Skill> advancedSkills, List<Talent> talents, List<ArmoryStaff> armoryStaffsList, Pouch pouch, List<Item> items, List<Weapon> weapons) {
        this.hero = hero;
        this.attribs = attribs;
        this.basicSkills = basicSkills;
        this.advancedSkills = advancedSkills;
        this.talents = talents;
        this.armoryStaffsList = armoryStaffsList;
        this.pouch = pouch;
        this.items = items;
        this.weapons = weapons;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public List<Attrib> getAttribs() {
        return attribs;
    }

    public void setAttribs(List<Attrib> attribs) {
        this.attribs = attribs;
    }

    public List<Skill> getBasicSkills() {
        return basicSkills;
    }

    public void setBasicSkills(List<Skill> basicSkills) {
        this.basicSkills = basicSkills;
    }

    public List<Skill> getAdvancedSkills() {
        return advancedSkills;
    }

    public void setAdvancedSkills(List<Skill> advancedSkills) {
        this.advancedSkills = advancedSkills;
    }

    public List<Talent> getTalents() {
        return talents;
    }

    public void setTalents(List<Talent> talents) {
        this.talents = talents;
    }

    public List<ArmoryStaff> getArmoryStaffsList() {
        return armoryStaffsList;
    }

    public void setArmoryStaffsList(List<ArmoryStaff> armoryStaffsList) {
        this.armoryStaffsList = armoryStaffsList;
    }

    public Pouch getPouch() {
        return pouch;
    }

    public void setPouch(Pouch pouch) {
        this.pouch = pouch;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }
}
