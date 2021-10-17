package pl.darbean.WarhammerServer.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class LeftMenu {

    public static VerticalLayout getLeftMenu() {
        VerticalLayout layout = new VerticalLayout();
        layout.add(new RouterLink("Strona główna", MainView.class));
        layout.add(new RouterLink("Sesja graczy", PlayerSessionView.class));
        layout.add(new RouterLink("Inicjatywa i potwory (NEW)", FightView.class));
        return layout;
    }
}
