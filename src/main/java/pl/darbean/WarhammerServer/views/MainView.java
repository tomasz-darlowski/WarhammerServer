package pl.darbean.WarhammerServer.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
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
import pl.darbean.WarhammerServer.model.attributes.CharacterAttribute;
import pl.darbean.WarhammerServer.model.hero.Hero;
import pl.darbean.WarhammerServer.model.inventory.ArmoryStaff;
import pl.darbean.WarhammerServer.model.inventory.Item;
import pl.darbean.WarhammerServer.model.inventory.Weapon;
import pl.darbean.WarhammerServer.model.skills.BasicSkill;
import pl.darbean.WarhammerServer.model.skills.Skill;
import pl.darbean.WarhammerServer.model.talents.Talent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Route
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainView extends AppLayout {

    public MainView() {
        List<ImportExportJsonObject> values = new ArrayList<>(GameController.sessionHeroes.values());
        List<Tab> tabsList = new ArrayList<>();
        for (HeroCategories category : HeroCategories.values()) {
            Tab e = new Tab(category.getLabel());
            e.getElement().addEventListener("click", domEvent -> {
                setContent(createTableForCategory(category, values));
            });
            tabsList.add(e);
        }
        Tabs tabs = new Tabs(tabsList.toArray(new Tab[]{}));
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        setPrimarySection(AppLayout.Section.DRAWER);
        Button resetData = new Button("Resetuj");
        resetData.addClickListener(buttonClickEvent -> {
            GameController.sessionHeroes.clear();
        });
        addToNavbar(new Label("Warhammer Player Server"), tabs, resetData);
        if (!values.isEmpty()) {
            setContent(createTableForCategory(HeroCategories.BASIC_DATA, values));
        }
    }

    private Component createTableForCategory(HeroCategories category, List<ImportExportJsonObject> values) {
        List<Hero> heroList = values.stream().map(importExportJsonObject -> importExportJsonObject.getHero()).collect(Collectors.toList());
        switch (category) {
            case BASIC_DATA:
                return createHeroDataTable(heroList);
            case CECHY:
                return createAttribsDataTable(values.stream().map(importExportJsonObject -> importExportJsonObject.getAttribs()).collect(Collectors.toList()), heroList);
            case BASIC_SKILL:
                return createBasicSkillsDataTable(values.stream().map(importExportJsonObject -> importExportJsonObject.getBasicSkills()).collect(Collectors.toList()), heroList);
            case ADVENCED_SKILLS:
                return createAdvencedSkillsAccordeon(values);
            case TALENTS:
                return createTalentsAccordeon(values);
            case ARMOR:
                return createArmoryAccordeon(values);
            case WEAPON:
                return createWeaponAccordeon(values);
            case INVENTORY:
                return createInventoryAccordeon(values);
            default:
                return null;
        }
    }

    private Accordion createInventoryAccordeon(List<ImportExportJsonObject> values) {
        Accordion accordion = new Accordion();
        accordion.close();
        for (ImportExportJsonObject value : values) {
            Grid<Item> items = new Grid<>(Item.class, false);
            items.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                    GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
            items.setItems(value.getItems());
            items.addColumn(item -> item.getName()).setHeader("Nazwa").setWidth("200px").setFlexGrow(0);
            items.addColumn(item -> item.getQuantity()).setHeader("Ilość").setAutoWidth(true);
            accordion.add(value.getHero().getNameAndSurname(), items);
        }
        return accordion;
    }

    private Accordion createWeaponAccordeon(List<ImportExportJsonObject> values) {
        Accordion accordion = new Accordion();
        accordion.close();
        for (ImportExportJsonObject value : values) {
            Grid<Weapon> waepon = new Grid<>(Weapon.class, false);
            waepon.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                    GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
            waepon.setItems(value.getWeapons());
            waepon.addColumn("weaponName").setHeader("Nazwa").setWidth("200px").setFlexGrow(0);
            waepon.addColumn("attribs").setHeader("Cechy").setWidth("300px").setFlexGrow(1);
            waepon.addColumn("damage").setHeader("Obrażenia").setWidth("150px").setFlexGrow(0);
            waepon.addColumn(weapon -> weapon.getRange().getLabel()).setHeader("Zasięg").setWidth("150px").setFlexGrow(0);
            accordion.add(value.getHero().getNameAndSurname(), waepon);
        }
        return accordion;
    }

    private Accordion createArmoryAccordeon(List<ImportExportJsonObject> values) {
        Accordion accordion = new Accordion();
        accordion.close();
        for (ImportExportJsonObject value : values) {
            Grid<ArmoryStaff> armor = new Grid<>(ArmoryStaff.class, false);
            armor.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                    GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
            armor.setItems(value.getArmoryStaffsList());
            armor.addColumn(armoryStaff -> armoryStaff.getPlace().getLabel(), "place").setWidth("200px").setFlexGrow(0);
            armor.addColumn("armorName").setHeader("Nazwa").setWidth("300px").setFlexGrow(0);
            armor.addColumn(armoryStaff -> armoryStaff.getArmorPoints()).setHeader("Punkty obrony").setWidth("200px").setFlexGrow(0);
            accordion.add(value.getHero().getNameAndSurname(), armor);
        }
        return accordion;
    }

    private Accordion createTalentsAccordeon(List<ImportExportJsonObject> values) {
        Accordion accordion = new Accordion();
        accordion.close();
        for (ImportExportJsonObject value : values) {
            Grid<Talent> talents = new Grid<>(Talent.class, false);
            talents.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                    GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
            talents.setItems(value.getTalents());
            talents.addColumn("name").setHeader("Nazwa").setWidth("200px").setFlexGrow(0);
            talents.addColumn("total").setHeader("Poziom").setAutoWidth(true).setWidth("200px").setFlexGrow(0);
            accordion.add(value.getHero().getNameAndSurname(), talents);
        }
        return accordion;
    }

    private Accordion createAdvencedSkillsAccordeon(List<ImportExportJsonObject> values) {
        Accordion accordion = new Accordion();
        accordion.close();
        for (ImportExportJsonObject value : values) {
            Grid<Skill> advSkillGrid = new Grid<>(Skill.class, false);
            advSkillGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                    GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
            advSkillGrid.setItems(value.getAdvancedSkills());
            advSkillGrid.addColumn("label").setHeader("Nazwa").setWidth("200px").setFlexGrow(0);
            advSkillGrid.addColumn("advances").setHeader("Poziom").setAutoWidth(true).setWidth("200px").setFlexGrow(0);
            accordion.add(value.getHero().getNameAndSurname(), advSkillGrid);
        }
        return accordion;
    }

    private Component createBasicSkillsDataTable(List<List<Skill>> collect, List<Hero> heroList) {
        List<Map<BasicSkill, Skill>> listOfMaps = new ArrayList<>();
        for (List<Skill> skills : collect) {
            Map<BasicSkill, Skill> mapOfSkills = new HashMap<>();
            for (Skill attrib : skills) {
                mapOfSkills.put(BasicSkill.getByLabel(attrib.getLabel()), attrib);
            }
            listOfMaps.add(mapOfSkills);
        }

        Grid<GridElement> grid = new Grid<>(GridElement.class, false);
        grid.setHeightFull();
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        List<GridElement> gridElements = getSkillRow(CharacterAttribute.WW.getLabel(), listOfMaps, null);
        grid.setItems(gridElements);
        grid.addColumn(gridElement -> gridElement.getLabel()).setHeader("").setWidth("200px").setFlexGrow(0);
        for (int i = 0; i < heroList.size(); i++) {
            final int idx = i;
            grid.addColumn(gridElement -> gridElement.getValues()[idx]).setHeader(heroList.get(idx).getNameAndSurname()).setWidth("200px").setFlexGrow(0);
        }
        return grid;
    }

    private Component createAttribsDataTable(List<List<Attrib>> collect, List<Hero> heroList) {
        List<Map<CharacterAttribute, Attrib>> listOfMaps = new ArrayList<>();
        for (List<Attrib> attribs : collect) {
            Map<CharacterAttribute, Attrib> mapOfSkills = new HashMap<>();
            for (Attrib attrib : attribs) {
                mapOfSkills.put(attrib.getAttribute(), attrib);
            }
            listOfMaps.add(mapOfSkills);
        }

        Grid<GridElement> grid = new Grid<>(GridElement.class, false);
        grid.setHeightFull();
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        List<GridElement> gridElements = getAttribRow(CharacterAttribute.WW.getLabel(), listOfMaps, null);
        grid.setItems(gridElements);
        grid.addColumn(gridElement -> gridElement.getLabel()).setHeader("").setWidth("200px").setFlexGrow(0);
        for (int i = 0; i < heroList.size(); i++) {
            final int idx = i;
            grid.addColumn(gridElement -> gridElement.getValues()[idx]).setHeader(heroList.get(idx).getNameAndSurname()).setWidth("200px").setFlexGrow(0);
        }
        return grid;
    }

    private Component createHeroDataTable(List<Hero> collect) {
        Grid<GridElement> grid = new Grid<>(GridElement.class, false);
        grid.setHeightFull();
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        List<GridElement> gridElements = new ArrayList<>();
        gridElements.add(getHeroRow("Imię i nazwisko", collect, Hero::getNameAndSurname));
        gridElements.add(getHeroRow("Rasa", collect, (hero) -> hero.getRace().getLabel()));
        gridElements.add(getHeroRow("Profesja", collect, (hero) -> hero.getProfession().getLabel()));
        gridElements.add(getHeroRow("Wiek", collect, (hero) -> "" + hero.getAge()));
        gridElements.add(getHeroRow("Wzrost", collect, (hero) -> "" + hero.getHeight()));
        gridElements.add(getHeroRow("Kolor oczu", collect, Hero::getEyeColor));
        gridElements.add(getHeroRow("Kolor włosów", collect, Hero::getHairColor));
        gridElements.add(getHeroRow("Punkty przeznaczenia", collect, (hero) -> "" + hero.getDestinationPoint()));
        gridElements.add(getHeroRow("Punkty szczęścia", collect, (hero) -> "" + hero.getLuckPoints()));
        gridElements.add(getHeroRow("Punkty bohatera", collect, (hero) -> "" + hero.getHeroPoints()));
        gridElements.add(getHeroRow("Punkty determinacji", collect, (hero) -> "" + hero.getDeterminationPoints()));

        grid.addColumn(GridElement::getLabel).setHeader("").setWidth("200px").setFlexGrow(0);
        for (int i = 0; i < collect.size(); i++) {
            Hero hero = collect.get(i);
            final int idx = i;
            grid.addColumn(gridElement -> gridElement.getValues()[idx]).setHeader(hero.getNameAndSurname()).setWidth("200px").setFlexGrow(0);
        }
        grid.setItems(gridElements);

        return grid;
    }

    private List<GridElement> getSkillRow(String label, List<Map<BasicSkill, Skill>> listOfMaps, Object o) {
        List<GridElement> elements = new ArrayList<>();
        for (BasicSkill value : BasicSkill.values()) {
            List<String> values = new ArrayList<>();
            for (Map<BasicSkill, Skill> characterAttributeAttribMap : listOfMaps) {
                values.add("" + characterAttributeAttribMap.get(value).getTotal());
            }
            elements.add(new GridElement(value.getLabel(), values.toArray(new String[0])));
        }
        return elements;
    }

    private List<GridElement> getAttribRow(String label, List<Map<CharacterAttribute, Attrib>> list, Function<Attrib, String> valueSupplier) {
        List<GridElement> elements = new ArrayList<>();
        for (CharacterAttribute value : CharacterAttribute.values()) {
            List<String> values = new ArrayList<>();
            for (Map<CharacterAttribute, Attrib> characterAttributeAttribMap : list) {
                values.add("" + characterAttributeAttribMap.get(value).getTotal());
            }
            elements.add(new GridElement(value.getLabel(), values.toArray(new String[0])));
        }
        return elements;
    }

    private GridElement getHeroRow(String category, List<Hero> list, Function<Hero, String> valueSupplier) {
        List<String> values = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            values.add(valueSupplier.apply(list.get(i)));
        }
        return new GridElement(category, values.toArray(new String[]{}));
    }

}
