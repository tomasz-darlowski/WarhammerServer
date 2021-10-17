package pl.darbean.WarhammerServer;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import pl.darbean.WarhammerServer.controller.GameController;

@SpringBootApplication
@ComponentScan(basePackageClasses = {GameController.class})
@Theme(themeClass = Material.class, variant = Material.DARK)
public class WarhammerServerApplication extends SpringBootServletInitializer implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(WarhammerServerApplication.class, args);
    }
}
