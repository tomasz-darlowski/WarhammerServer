package pl.darbean.WarhammerServer.views;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Route(value = "/fight")
@Theme(value = Material.class, variant = Material.DARK)
public class FightView extends AppLayout {

    public FightView(@Autowired Environment environment) {
        List<Tab> tabsList = new ArrayList<>();
        Tabs tabs = new Tabs(tabsList.toArray(new Tab[]{}));
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        setPrimarySection(Section.DRAWER);
        addToDrawer(LeftMenu.getLeftMenu());
        setDrawerOpened(true);
        DrawerToggle drawerToggle = new DrawerToggle();
        addToNavbar(drawerToggle);
        addToNavbar(new Label("Warhammer Player Server"), tabs);

        Accordion accordion = new Accordion();
        AccordionPanel generateEnemyPanel = new AccordionPanel("Dodaj potwory", null);
        AccordionPanel generatedEnemies = new AccordionPanel("Walka", null);
        accordion.add(generateEnemyPanel);
        accordion.add(generatedEnemies);
        setContent(accordion);
    }

    private String getAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "";
        }
    }

}