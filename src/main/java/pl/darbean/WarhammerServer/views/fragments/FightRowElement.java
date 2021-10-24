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
import pl.darbean.WarhammerServer.views.viewModel.Fighter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FightRowElement extends HorizontalLayout {


    public FightRowElement(Fighter fighter, List<String> availableTargets) {
        super();
        setSpacing(false);
        setPadding(false);
        Span name;
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
            if (event.getValue() == 0) {
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
        Details details = new Details("Stany", verticalLayout);
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(getCheckbox("Powalony", details));
        hl.add(getCheckbox("Nieprzytomny", details));
        hl.add(getCheckbox("Zaskoczony", details));
        hl.add(getCheckbox("Pochwycony", details));
        verticalLayout.add(hl);

        HorizontalLayout hl2 = new HorizontalLayout();
        hl2.add(getCheckbox("Krwawiący", details));
        hl2.add(getCheckbox("Oszołomiony", details));
        hl2.add(getCheckbox("Zatruty", details));
        hl2.add(getCheckbox("Spanikowany", details));
        verticalLayout.add(hl2);

        HorizontalLayout hl3 = new HorizontalLayout();
        hl3.add(getCheckbox("Ogłuszony", details));
        hl3.add(getCheckbox("Oślepiony", details));
        hl3.add(getCheckbox("Podpalony", details));
        hl3.add(getCheckbox("Zmęczony", details));
        verticalLayout.add(hl3);

        details.setOpened(false);

        return details;
    }

    private Checkbox getCheckbox(String label, Details detail) {
        Checkbox checkbox = new Checkbox(label);
        checkbox.addValueChangeListener(event -> {
            String summaryText = detail.getSummaryText();
            if (summaryText.equals("Stany")) {
                detail.setSummaryText(label);
            } else {
                if (event.getValue()) {
                    //add text
                    detail.setSummaryText(detail.getSummaryText() + "," + label);
                } else {
                    String[] split = detail.getSummaryText().split(",");
                    List<String> collect = Arrays.asList(split).stream().filter(state -> !state.equals(label)).collect(Collectors.toList());
                    if (collect.isEmpty()) {
                        detail.setSummaryText("Stany");
                    } else {
                        StringBuilder sb = new StringBuilder("");
                        for (String s : collect) {
                            sb.append(s);
                            sb.append(",");
                        }
                        detail.setSummaryText(sb.toString());

                    }
                }
            }
        });
        return checkbox;
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

        if (id == 0) {
            label.setClassName("gridLabelTextRed");
        } else if (id == 1) {
            label.setClassName("gridLabelTextGreen");
        } else if (id == 2) {
            label.setClassName("gridLabelTextYellow");
        } else if (id == 3) {
            label.setClassName("gridLabelTextBlue");
        } else {
            label.setClassName("gridLabelTextViolet");
        }

        label.setId("gridLabelId");
        return label;
    }
}
