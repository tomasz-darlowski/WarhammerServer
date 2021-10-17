package pl.darbean.WarhammerServer.views.fragments;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import pl.darbean.WarhammerServer.views.Fighter;

import java.util.List;

public class FightRowElement extends HorizontalLayout {


    public FightRowElement(Fighter fighter, List<String> availableTargets) {
        super();
        setSpacing(false);
        setPadding(false);
        Span name = null;
        if (fighter.isPlayer()) {
            Label gridColumnLabel = getGridColumnLabel(fighter.getName() + getDetails("" + fighter.getInitiative()), fighter.getPlayerFighterNumber());
            name = new Span(gridColumnLabel);
        } else {
            name = new Span(fighter.getName() + getDetails("" + fighter.getInitiative()));
        }
        setAlignItems(FlexComponent.Alignment.CENTER);

        VerticalLayout column = new VerticalLayout(name);
        column.setWidth(220, Unit.PIXELS);
        column.setPadding(false);
        column.setSpacing(false);
        add(column);

        IntegerField healthPoints = new IntegerField("Poziom życia");
        healthPoints.setValue(fighter.getHealth());
        healthPoints.setHasControls(true);
        healthPoints.addValueChangeListener(event -> {
            if (event.getValue().intValue() == 0) {
                this.setVisible(false);
            }
        });
        healthPoints.setMin(0);
        add(healthPoints);
        add(getStates());

        ComboBox<String> targets = new ComboBox<>("Cel", availableTargets);
        add(targets);

        getStyle().set("line-height", "var(--lumo-line-height-m)");
    }

    private Component getStates() {
        VerticalLayout verticalLayout = new VerticalLayout();

        HorizontalLayout hl = new HorizontalLayout();
        hl.add(new Checkbox("Powalony"));
        hl.add(new Checkbox("Nieprzytomny"));
        hl.add(new Checkbox("Zaskoczony"));
        hl.add(new Checkbox("Pochwycony"));
        verticalLayout.add(hl);

        HorizontalLayout hl2 = new HorizontalLayout();
        hl2.add(new Checkbox("Krwawiący"));
        hl2.add(new Checkbox("Oszołomiony"));
        hl2.add(new Checkbox("Zatruty"));
        hl2.add(new Checkbox("Spanikowany"));
        verticalLayout.add(hl2);

        HorizontalLayout hl3 = new HorizontalLayout();
        hl3.add(new Checkbox("Ogłuszony"));
        hl3.add(new Checkbox("Oślepiony"));
        hl3.add(new Checkbox("Podpalony"));
        hl3.add(new Checkbox("Zmęczony"));
        verticalLayout.add(hl3);

        Details details = new Details("Stany", verticalLayout);
        details.setOpened(false);

        return details;
    }

    private String getDetails(String details) {
        if (details != null && !details.isEmpty())
            return " (" + details + ")";
        return "";
    }

    private Label getGridColumnLabel(String nameSurname, int idx) {
        Label label = new Label(nameSurname);

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
}
