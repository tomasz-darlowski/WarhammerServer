package pl.darbean.WarhammerServer.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import pl.darbean.WarhammerServer.views.fragments.LeftMenu;

import javax.annotation.security.PermitAll;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Route(value = "")
@PermitAll
public class MainView extends AppLayout {

    public MainView(@Autowired Environment environment) {
        List<Tab> tabsList = new ArrayList<>();
        Tabs tabs = new Tabs(tabsList.toArray(new Tab[]{}));
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        setPrimarySection(Section.DRAWER);
        addToDrawer(LeftMenu.getLeftMenu());
        setDrawerOpened(true);
        DrawerToggle drawerToggle = new DrawerToggle();
        addToNavbar(drawerToggle);
        addToNavbar(new Label("Warhammer Server"), tabs);

        setContent(new Label("Adres servera: " + getAddress() + ":" + environment.getProperty("server.port")));
    }

    private String getAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "";
        }
    }

}
