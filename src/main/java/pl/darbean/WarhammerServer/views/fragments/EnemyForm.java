package pl.darbean.WarhammerServer.views.fragments;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import pl.darbean.WarhammerServer.views.Fighter;
import pl.darbean.WarhammerServer.views.FighterFormModel;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class EnemyForm extends HorizontalLayout {

    public EnemyForm(FighterFormModel fighterFormModel, Map<String, Fighter> initiativeMap) {
        ComboBox<String> comboBox = new ComboBox("Przeciwnik");
        comboBox.setItems(Arrays.stream(new String[]{"Trol", "Ogr", "Goblin", "Skaven", "Zwierzoczłowiek", "Demon", "Bandyta"}).collect(Collectors.toList()));
        comboBox.addValueChangeListener(event -> {
            fighterFormModel.setType(event.getValue());
        });
        comboBox.setValue(fighterFormModel.getType());
        add(comboBox);

        IntegerField numberField = new IntegerField();
        numberField.setLabel("Ilość przeciwników");
        numberField.setHasControls(true);
        numberField.setAutoselect(true);
        numberField.setMin(0);
        numberField.setMax(30);
        numberField.setValue(fighterFormModel.getQuantity());
        numberField.addValueChangeListener(event -> {
            fighterFormModel.setQuantity(event.getValue().intValue());
        });
        add(numberField);

        IntegerField baseEnemyHealth = new IntegerField();
        baseEnemyHealth.setAutoselect(true);
        baseEnemyHealth.setLabel("Bazowy poziom życia");
        baseEnemyHealth.setHasControls(true);
        baseEnemyHealth.setMin(0);
        baseEnemyHealth.addValueChangeListener(event -> {
            fighterFormModel.setBaseHealth(event.getValue().intValue());
        });
        baseEnemyHealth.setValue(fighterFormModel.getBaseHealth());
        add(baseEnemyHealth);

        IntegerField randomizer = new IntegerField();
        randomizer.setLabel("Losowość +/-");
        randomizer.setHasControls(true);
        randomizer.setAutoselect(true);
        randomizer.setMin(0);
        randomizer.addValueChangeListener(event -> {
            fighterFormModel.setRandomizer(event.getValue().intValue());
        });
        randomizer.setValue(fighterFormModel.getRandomizer());
        add(randomizer);
    }
}
