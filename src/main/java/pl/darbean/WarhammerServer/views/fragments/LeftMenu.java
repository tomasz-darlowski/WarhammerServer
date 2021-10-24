package pl.darbean.WarhammerServer.views.fragments;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import pl.darbean.WarhammerServer.views.FightView;
import pl.darbean.WarhammerServer.views.MainView;
import pl.darbean.WarhammerServer.views.PlayerSessionView;

public class LeftMenu {

    public static VerticalLayout getLeftMenu() {
        VerticalLayout layout = new VerticalLayout();
        layout.add(new RouterLink("Strona główna", MainView.class));
        layout.add(new RouterLink("Sesja graczy", PlayerSessionView.class));
        layout.add(new RouterLink("Inicjatywa i potwory (NEW)", FightView.class));
        return layout;
    }
}
