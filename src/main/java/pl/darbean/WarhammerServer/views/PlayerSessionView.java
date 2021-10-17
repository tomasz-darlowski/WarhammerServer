package pl.darbean.WarhammerServer.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
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

@Route(value = "player/session")
@CssImport("./style.css")
public class PlayerSessionView extends AppLayout {

    public PlayerSessionView() {
        List<ImportExportJsonObject> values = new ArrayList<>(GameController.sessionHeroes.values());
        List<Tab> tabsList = new ArrayList<>();
        for (HeroCategories category : HeroCategories.values()) {
            Tab e = new Tab(category.getLabel());
            e.getElement().addEventListener("click", domEvent -> {
                setContent(createTableForCategory(category));
            });
            tabsList.add(e);
        }
        Tabs tabs = new Tabs(tabsList.toArray(new Tab[]{}));
        Button reset_graczy = new Button("Reset graczy");

        Dialog dialog = new Dialog();
        dialog.add(new Text("Czy na pewno chcesz zresetować sesje?"));
        dialog.add(reset_graczy);

        reset_graczy.addThemeVariants(ButtonVariant.LUMO_SMALL);
        reset_graczy.addClickListener(buttonClickEvent -> {
            GameController.sessionHeroes.clear();
        });

        Button tabBtn = new Button("Reset sesji");
        tabBtn.addClickListener(event -> dialog.open());
        tabs.add(tabBtn);

        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        setPrimarySection(AppLayout.Section.DRAWER);
        addToDrawer(LeftMenu.getLeftMenu());
        setDrawerOpened(true);
        addToNavbar(new DrawerToggle());
        addToNavbar(new Anchor("Warhammer Player Server"), tabs);
        if (!values.isEmpty()) {
            setContent(createTableForCategory(HeroCategories.BASIC_DATA));
        }
    }

    private Component createTableForCategory(HeroCategories category) {
        List<ImportExportJsonObject> values = new ArrayList<>(GameController.sessionHeroes.values());
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

    private Component createInventoryAccordeon(List<ImportExportJsonObject> values) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        horizontalLayout.setSpacing(false);
        horizontalLayout.setMargin(false);
        horizontalLayout.setPadding(false);

        for (int i = 0; i < values.size(); i++) {
            ImportExportJsonObject value = values.get(i);
            VerticalLayout characterColumn = new VerticalLayout();
            characterColumn.setSpacing(false);
            characterColumn.setMargin(false);
            characterColumn.setPadding(false);
            characterColumn.setWidth(300, Unit.PIXELS);
            characterColumn.add(getGridColumnLabel(value.getHero(), i));

            ListBox<Item> lb = new ListBox<Item>();
            lb.setItems(value.getItems());
            lb.setReadOnly(true);
            lb.setRenderer(new ComponentRenderer<>(item -> {
                HorizontalLayout row = new HorizontalLayout();
                row.setAlignItems(FlexComponent.Alignment.CENTER);

                Span name = new Span(item.getName());
                Span profession = new Span("Ilość:" + item.getQuantity());
                profession.getStyle()
                        .set("color", "#dcdcdc")
                        .set("font-size", "small");

                VerticalLayout column = new VerticalLayout(name, profession);
                column.setPadding(false);
                column.setSpacing(false);

                row.add(column);
                row.getStyle().set("line-height", "var(--lumo-line-height-m)");
                return row;

            }));
            characterColumn.add(lb);
            horizontalLayout.add(characterColumn);
        }
        return horizontalLayout;
    }

    private Component createWeaponAccordeon(List<ImportExportJsonObject> values) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        horizontalLayout.setSpacing(false);
        horizontalLayout.setMargin(false);
        horizontalLayout.setPadding(false);

        for (int i = 0; i < values.size(); i++) {
            ImportExportJsonObject value = values.get(i);
            VerticalLayout characterColumn = new VerticalLayout();
            characterColumn.setSpacing(false);
            characterColumn.setMargin(false);
            characterColumn.setPadding(false);
            characterColumn.setWidth(300, Unit.PIXELS);
            characterColumn.add(getGridColumnLabel(value.getHero(), i));

            ListBox<Weapon> lb = new ListBox<Weapon>();
            lb.setItems(value.getWeapons());
            lb.setReadOnly(true);
            lb.setRenderer(new ComponentRenderer<>(advSkill -> {
                HorizontalLayout row = new HorizontalLayout();
                row.setAlignItems(FlexComponent.Alignment.CENTER);

                Span name = new Span(advSkill.getWeaponName() + getDetails("Obrażenia:" + advSkill.getDamage()));
                Span profession = new Span(advSkill.getAttribs());
                profession.getStyle()
                        .set("color", "#dcdcdc")
                        .set("font-size", "small");

                VerticalLayout column = new VerticalLayout(name, profession);
                column.setPadding(false);
                column.setSpacing(false);

                row.add(column);
                row.getStyle().set("line-height", "var(--lumo-line-height-m)");
                return row;

            }));
            characterColumn.add(lb);
            horizontalLayout.add(characterColumn);
        }
        return horizontalLayout;
    }

    private Component createArmoryAccordeon(List<ImportExportJsonObject> values) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        horizontalLayout.setSpacing(false);
        horizontalLayout.setMargin(false);
        horizontalLayout.setPadding(false);

        for (int i = 0; i < values.size(); i++) {
            ImportExportJsonObject value = values.get(i);
            VerticalLayout characterColumn = new VerticalLayout();
            characterColumn.setSpacing(false);
            characterColumn.setMargin(false);
            characterColumn.setPadding(false);
            characterColumn.setWidth(300, Unit.PIXELS);
            characterColumn.add(getGridColumnLabel(value.getHero(), i));

            ListBox<ArmoryStaff> lb = new ListBox<ArmoryStaff>();
            lb.setItems(value.getArmoryStaffsList());
            lb.setReadOnly(true);
            lb.setRenderer(new ComponentRenderer<>(armoryStaff -> {
                HorizontalLayout row = new HorizontalLayout();
                row.setAlignItems(FlexComponent.Alignment.CENTER);

                Span name = new Span(armoryStaff.getArmorName() + getDetails(armoryStaff.getPlace().name()));
                Span profession = new Span("Punkty pancerza:" + armoryStaff.getArmorPoints());
                profession.getStyle()
                        .set("color", "#dcdcdc")
                        .set("font-size", "small");

                VerticalLayout column = new VerticalLayout(name, profession);
                column.setPadding(false);
                column.setSpacing(false);

                row.add(column);
                row.getStyle().set("line-height", "var(--lumo-line-height-m)");
                return row;

            }));
            characterColumn.add(lb);
            horizontalLayout.add(characterColumn);
        }
        return horizontalLayout;
    }

    private Component createTalentsAccordeon(List<ImportExportJsonObject> values) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        horizontalLayout.setSpacing(false);
        horizontalLayout.setMargin(false);
        horizontalLayout.setPadding(false);

        for (int i = 0; i < values.size(); i++) {
            ImportExportJsonObject value = values.get(i);
            VerticalLayout characterColumn = new VerticalLayout();
            characterColumn.setSpacing(false);
            characterColumn.setMargin(false);
            characterColumn.setPadding(false);
            characterColumn.setWidth(300, Unit.PIXELS);
            characterColumn.add(getGridColumnLabel(value.getHero(), i));

            ListBox<Talent> lb = new ListBox<Talent>();
            lb.setItems(value.getTalents());
            lb.setReadOnly(true);
            lb.setRenderer(new ComponentRenderer<>(talent -> {
                HorizontalLayout row = new HorizontalLayout();
                row.setAlignItems(FlexComponent.Alignment.CENTER);

                Span name = new Span(talent.getName());
                Span profession = new Span("Poziom:" + talent.getAdvance());
                profession.getStyle()
                        .set("color", "#dcdcdc")
                        .set("font-size", "small");

                VerticalLayout column = new VerticalLayout(name, profession);
                column.setPadding(false);
                column.setSpacing(false);

                row.add(column);
                row.getStyle().set("line-height", "var(--lumo-line-height-m)");
                return row;

            }));
            characterColumn.add(lb);
            horizontalLayout.add(characterColumn);
        }
        return horizontalLayout;
    }

    private Component createAdvencedSkillsAccordeon(List<ImportExportJsonObject> values) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        horizontalLayout.setSpacing(false);
        horizontalLayout.setMargin(false);
        horizontalLayout.setPadding(false);

        for (int i = 0; i < values.size(); i++) {
            ImportExportJsonObject value = values.get(i);
            VerticalLayout characterColumn = new VerticalLayout();
            characterColumn.setSpacing(false);
            characterColumn.setMargin(false);
            characterColumn.setPadding(false);
            characterColumn.setWidth(300, Unit.PIXELS);
            characterColumn.add(getGridColumnLabel(value.getHero(), i));

            ListBox<Skill> lb = new ListBox<Skill>();
            lb.setItems(value.getAdvancedSkills());
            lb.setReadOnly(true);
            lb.setRenderer(new ComponentRenderer<>(advSkill -> {
                HorizontalLayout row = new HorizontalLayout();
                row.setAlignItems(FlexComponent.Alignment.CENTER);

                Span name = new Span(advSkill.getName() + getSpecialization(advSkill));
                Span profession = new Span("Poziom:" + advSkill.getAdvance());
                profession.getStyle()
                        .set("color", "#dcdcdc")
                        .set("font-size", "small");

                VerticalLayout column = new VerticalLayout(name, profession);
                column.setPadding(false);
                column.setSpacing(false);

                row.add(column);
                row.getStyle().set("line-height", "var(--lumo-line-height-m)");
                return row;

            }));
            characterColumn.add(lb);
            horizontalLayout.add(characterColumn);
        }
        return horizontalLayout;
    }

    private String getSpecialization(Skill advSkill) {
        if (advSkill.getSpecialisation() != null && !advSkill.getSpecialisation().isEmpty())
            return " (" + advSkill.getSpecialisation() + ")";
        return "";
    }

    private String getDetails(String details) {
        if (details != null && !details.isEmpty())
            return " (" + details + ")";
        return "";
    }

    private Component createBasicSkillsDataTable(List<List<Skill>> collect, List<Hero> heroList) {
        List<Map<BasicSkill, Skill>> listOfMaps = new ArrayList<>();
        for (List<Skill> skills : collect) {
            Map<BasicSkill, Skill> mapOfSkills = new HashMap<>();
            for (Skill attrib : skills) {
                mapOfSkills.put(BasicSkill.getByLabel(attrib.getName()), attrib);
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
            grid.addColumn(gridElement -> gridElement.getValues()[idx]).setHeader(getGridColumnLabel(heroList.get(idx), idx)).setWidth("200px").setFlexGrow(0);

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
            grid.addColumn(gridElement -> gridElement.getValues()[idx]).setHeader(getGridColumnLabel(heroList.get(idx), idx)).setWidth("200px").setFlexGrow(0);

        }
        return grid;
    }

    private Component createHeroDataTable(List<Hero> collect) {
        Grid<GridElement> grid = new Grid<>(GridElement.class, false);
        grid.setId("HeroGrid");
        grid.setHeightFull();
        grid.addThemeVariants(GridVariant.MATERIAL_COLUMN_DIVIDERS,
                GridVariant.LUMO_COLUMN_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        List<GridElement> gridElements = new ArrayList<>();
        gridElements.add(getHeroRow("Imię i nazwisko", collect, Hero::getNameAndSurname));
        gridElements.add(getHeroRow("Rasa", collect, (hero) -> hero.getRace()));
        gridElements.add(getHeroRow("Profesja", collect, (hero) -> hero.getProfession()));
        gridElements.add(getHeroRow("Wiek", collect, (hero) -> "" + hero.getAge()));
        gridElements.add(getHeroRow("Wzrost", collect, (hero) -> "" + hero.getHeight()));
        gridElements.add(getHeroRow("Kolor oczu", collect, Hero::getEyeColor));
        gridElements.add(getHeroRow("Kolor włosów", collect, Hero::getHairColor));
        gridElements.add(getHeroRow("Punkty przeznaczenia", collect, (hero) -> "" + hero.getDestinationPoint()));
        gridElements.add(getHeroRow("Punkty szczęścia", collect, (hero) -> "" + hero.getLuckPoints()));
        gridElements.add(getHeroRow("Punkty bohatera", collect, (hero) -> "" + hero.getHeroPoints()));
        gridElements.add(getHeroRow("Punkty determinacji", collect, (hero) -> "" + hero.getDeterminationPoints()));
        gridElements.add(getHeroRow("Punkty życia", collect, (hero) -> "" + hero.getHealth()));
        gridElements.add(getHeroRow("Doświadczenie aktualne", collect, (hero) -> "" + hero.getCurrentExp()));

        grid.addColumn(GridElement::getLabel).setHeader("").setWidth("200px").setFlexGrow(0);
        for (int i = 0; i < collect.size(); i++) {
            Hero hero = collect.get(i);
            final int idx = i;
            grid.addColumn(gridElement -> gridElement.getValues()[idx]).setHeader(getGridColumnLabel(hero, idx)).setWidth("200px").setFlexGrow(0);
        }
        grid.setItems(gridElements);

        return grid;
    }

    private Label getGridColumnLabel(Hero hero, int idx) {
        Label label = new Label(hero.getNameAndSurname());

        int id = idx;
        if (idx > 4) {
            id = idx % 5;
        }

        if (idx == 0) {
            label.setClassName("gridLabelTextRed");
        } else if (idx == 1) {
            label.setClassName("gridLabelTextGreen");
        } else if (idx == 2) {
            label.setClassName("gridLabelTextYellow");
        } else if (idx == 3) {
            label.setClassName("gridLabelTextBlue");
        } else {
            label.setClassName("gridLabelTextViolet");
        }

        label.setId("gridLabelId");
        return label;
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
