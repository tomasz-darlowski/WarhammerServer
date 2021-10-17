package pl.darbean.WarhammerServer.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import pl.darbean.WarhammerServer.controller.GameController;
import pl.darbean.WarhammerServer.model.ImportExportJsonObject;
import pl.darbean.WarhammerServer.views.fragments.EnemyForm;
import pl.darbean.WarhammerServer.views.fragments.FightRowElement;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Route(value = "/fight")
public class FightView extends AppLayout {

    private static final Map<String, Fighter> initiativeMap = new HashMap<>();
    private static final List<FighterFormModel> fighterModelList = new ArrayList<>();

    public FightView() {
        List<Tab> tabsList = new ArrayList<>();
        Tabs tabs = new Tabs(tabsList.toArray(new Tab[]{}));
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        setPrimarySection(Section.DRAWER);
        addToDrawer(LeftMenu.getLeftMenu());
        setDrawerOpened(true);
        DrawerToggle drawerToggle = new DrawerToggle();
        addToNavbar(drawerToggle);
        addToNavbar(new Label("Warhammer Player Server"), tabs);

        Div content = new Div();
        VerticalLayout vl = createContent();
        content.add(createForm(vl));
        content.add(vl);
        setContent(content);
    }

    private Component createForm(VerticalLayout content1) {
        Accordion accordion = new Accordion();
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSpacing(false);
        verticalLayout.setPadding(false);
        AccordionPanel panel = new AccordionPanel("Generator", verticalLayout);

        VerticalLayout vl = new VerticalLayout();
        AtomicInteger playerIdx = new AtomicInteger();
        for (ImportExportJsonObject value : GameController.sessionHeroes.values()) {
            HorizontalLayout hl = new HorizontalLayout();

            NumberField playerInitiative = new NumberField();
            playerInitiative.setLabel("Inicjatywa " + value.getHero().getNameAndSurname());
            playerInitiative.addValueChangeListener(event -> {
                Fighter fighter = new Fighter();
                fighter.setInitiative(event.getValue().intValue());
                fighter.setName(value.getHero().getNameAndSurname());
                fighter.setHealth(event.getValue().intValue());
                fighter.setPlayerFighterNumber(playerIdx.getAndIncrement());
                initiativeMap.put(value.getHero().getNameAndSurname(), fighter);
            });
            hl.add(playerInitiative);
            vl.add(hl);
        }
        verticalLayout.add(vl);

        final VerticalLayout form = new VerticalLayout();
        form.add(createForm());

        IntegerField typesQuantity = new IntegerField("Ilość typów");
        typesQuantity.setMin(0);
        typesQuantity.setHasControls(true);
        typesQuantity.setValue(fighterModelList.size());
        typesQuantity.addValueChangeListener(event -> {
            if (fighterModelList.size() > event.getValue().intValue()) {
                FighterFormModel[] ts = Arrays.copyOf(fighterModelList.toArray(new FighterFormModel[]{}), event.getValue().intValue());
                fighterModelList.retainAll(new ArrayList<>(Arrays.asList(ts)));
            } else {
                fighterModelList.add(new FighterFormModel());
            }
            form.removeAll();
            form.add(createForm());
        });
        verticalLayout.add(typesQuantity);
        verticalLayout.add(form);

        HorizontalLayout hl = new HorizontalLayout();
        Button generuj = new Button("Generuj");
        generuj.addClickListener(event -> {
            content1.removeAll();
            for (FighterFormModel fighterFormModel : fighterModelList) {
                int baseHealth = fighterFormModel.getBaseHealth();
                int randomize = fighterFormModel.getRandomizer();
                for (int i = 1; i <= fighterFormModel.getQuantity(); i++) {
                    Fighter f = new Fighter();
                    f.setName(fighterFormModel.getType() + " " + i);
                    f.setInitiative(new Random().nextInt(100));
                    if (randomize > 0) {
                        randomize = randomize - new Random().nextInt(randomize * 2);
                    }
                    f.setHealth(baseHealth + randomize);
                    initiativeMap.put(f.getName(), f);
                }
            }
            content1.add(createContent());
            accordion.close();
        });

        Button reset = new Button("Reset");
        reset.getStyle().set("color", "red");
        reset.addClickListener(event -> {
            content1.removeAll();
            initiativeMap.clear();
            fighterModelList.clear();
        });

        hl.add(generuj);
        hl.add(reset);


        verticalLayout.add(hl);

        panel.setSummary(new Label("Generator"));
        panel.addContent(verticalLayout);
        panel.setOpened(true);
        accordion.add(panel);
        return accordion;
    }

    private Component[] createForm() {

        List<Component> layouts = new ArrayList<>();
        for (int i = 0; i < fighterModelList.size(); i++) {
            FighterFormModel fighterFormModel = fighterModelList.get(i);
            HorizontalLayout row = new EnemyForm(fighterFormModel, initiativeMap);
            layouts.add(row);
        }

        return layouts.toArray(new Component[]{});
    }

    private VerticalLayout createContent() {

        VerticalLayout verticalLayout = new VerticalLayout();
        if (!initiativeMap.isEmpty()) {
            ListBox<Fighter> listBox = new ListBox<>();
            listBox.setItems(new ListDataProvider<>(initiativeMap.entrySet().stream().map(entry -> {
                Fighter fighter = entry.getValue();
                ImportExportJsonObject charracterData = GameController.sessionHeroes.get(entry.getKey());
                if (charracterData != null) {
                    fighter.setName(charracterData.getHero().getNameAndSurname());
                    fighter.setHealth(charracterData.getHero().getHealth());
                    fighter.setPlayer(true);
                }
                return fighter;
            }).sorted(Fighter::compareTo).collect(Collectors.toList())));
            listBox.setRenderer(new ComponentRenderer<>(fighter -> {
                if (fighter.isPlayer()) {
                    return new FightRowElement(fighter, initiativeMap.values().stream().filter(fighter1 -> !fighter1.isPlayer()).map(Fighter::getName).collect(Collectors.toList()));
                } else {
                    return new FightRowElement(fighter, initiativeMap.values().stream().filter(fighter1 -> fighter1.isPlayer()).map(Fighter::getName).collect(Collectors.toList()));
                }
            }));
            verticalLayout.add(listBox);
        }
        return verticalLayout;
    }

}

