package pl.darbean.WarhammerServer.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import pl.darbean.WarhammerServer.controller.GameController;
import pl.darbean.WarhammerServer.model.ImportExportJsonObject;
import pl.darbean.WarhammerServer.model.attributes.Attrib;
import pl.darbean.WarhammerServer.model.hero.Hero;
import pl.darbean.WarhammerServer.model.inventory.ArmoryStaff;
import pl.darbean.WarhammerServer.model.inventory.Item;
import pl.darbean.WarhammerServer.model.inventory.Weapon;
import pl.darbean.WarhammerServer.model.skills.Skill;
import pl.darbean.WarhammerServer.model.talents.Talent;

import java.util.ArrayList;
import java.util.List;

@Route
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainView extends AppLayout {

    public MainView() {
        List<ImportExportJsonObject> values = new ArrayList<>(GameController.sessionHeroes.values());
        List<Tab> tabsList = new ArrayList<>();
        for (ImportExportJsonObject value : values) {
            Tab e = new Tab(value.getHero().getNameAndSurname());
            e.getElement().addEventListener("click", domEvent -> {
                setContent(createContentForCharacter(value));
            });
            tabsList.add(e);
        }
        setPrimarySection(AppLayout.Section.DRAWER);
        addToNavbar(new DrawerToggle());
        Tabs tabs = new Tabs(tabsList.toArray(new Tab[]{}));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        addToDrawer(tabs);
        if (!values.isEmpty()) {
            setContent(createContentForCharacter(values.get(0)));
        }
    }

    private Component createContentForCharacter(ImportExportJsonObject importExportJsonObject) {
        Hero hero = importExportJsonObject.getHero();

    Accordion accordion = new Accordion();

        FormLayout layout = new FormLayout();
        layout.add(new Label("Imię i nazwisko: \n" + hero.getNameAndSurname()));
        layout.add(new Label("Rasa: \n" + hero.getRace().getLabel()));
        layout.add(new Label("Profesja: \n" + hero.getProfession().getLabel()));
        layout.add(new Label("Wiek: \n" + hero.getAge()));
        layout.add(new Label("Wzrost: \n" + hero.getHeight()));
        layout.add(new Label("Kolor oczu: \n" + hero.getEyeColor()));
        layout.add(new Label("Kolor włosów \n:" + hero.getHairColor()));
        layout.add(new Label("Punkty przeznaczenia: \n" + hero.getDestinationPoint()));
        layout.add(new Label("Punkty szczęścia: \n" + hero.getLuckPoints()));
        layout.add(new Label("Punkty bohatera: \n" + hero.getHeroPoints()));
        layout.add(new Label("Punkty determinacji: \n" + hero.getDeterminationPoints()));
        accordion.add("Dane osobowe", layout);

        Grid<Attrib> attribGrid = new Grid<>(Attrib.class, false);
        attribGrid.setItems(importExportJsonObject.getAttribs());
        attribGrid.addColumn(attrib -> attrib.getAttribute().getLabel(), "attrib").setHeader("Nazwa").setAutoWidth(true);
        attribGrid.addColumn("total").setHeader("Poziom").setAutoWidth(true);
        accordion.add("Cechy", attribGrid);

        Grid<Skill> skillGrid = new Grid<>(Skill.class, false);
        skillGrid.setItems(importExportJsonObject.getBasicSkills());
        skillGrid.addColumn("label").setHeader("Nazwa").setAutoWidth(true);
        skillGrid.addColumn("advances").setHeader("Poziom").setAutoWidth(true);
        accordion.add("Umiejętności podstawowe", skillGrid);

        Grid<Skill> advSkillGrid = new Grid<>(Skill.class, false);
        advSkillGrid.setItems(importExportJsonObject.getAdvancedSkills());
        advSkillGrid.addColumn("label").setHeader("Nazwa").setAutoWidth(true);
        advSkillGrid.addColumn("advances").setHeader("Poziom").setAutoWidth(true);
        accordion.add("Umiejętności zaawansowane", advSkillGrid);

        Grid<Talent> talents = new Grid<>(Talent.class, false);
        talents.setItems(importExportJsonObject.getTalents());
        talents.addColumn("name").setHeader("Nazwa").setAutoWidth(true);
        talents.addColumn("total").setHeader("Poziom").setAutoWidth(true);
        accordion.add("Talenty", talents);

        Grid<ArmoryStaff> armor = new Grid<>(ArmoryStaff.class, false);
        armor.setItems(importExportJsonObject.getArmoryStaffsList());
        armor.addColumn(armoryStaff -> armoryStaff.getPlace().getLabel(), "place").setHeader("Typ zbroi").setAutoWidth(true);
        armor.addColumn("armorName").setHeader("Nazwa").setAutoWidth(true);
        armor.addColumn(armoryStaff -> armoryStaff.getArmorPoints()).setHeader("Punkty obrony").setAutoWidth(true);
        accordion.add("Pancerz", armor);

        Grid<Weapon> waepon = new Grid<>(Weapon.class, false);
        waepon.setItems(importExportJsonObject.getWeapons());
        waepon.addColumn("weaponName").setHeader("Nazwa").setAutoWidth(true);
        waepon.addColumn("attribs").setHeader("Cechy").setAutoWidth(true);
        waepon.addColumn("damage").setHeader("Obrażenia").setAutoWidth(true);
        waepon.addColumn(weapon -> weapon.getRange().getLabel()).setHeader("Zasięg").setAutoWidth(true);
        accordion.add("Broń", waepon);

        Grid<Item> items = new Grid<>(Item.class, false);
        items.setItems(importExportJsonObject.getItems());
        items.addColumn(item -> item.getName()).setHeader("Nazwa").setAutoWidth(true);
        items.addColumn(item -> item.getQuantity()).setHeader("Ilość").setAutoWidth(true);
        accordion.add("Plecak", items);

        return accordion;
    }
}
