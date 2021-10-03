package pl.darbean.WarhammerServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import pl.darbean.WarhammerServer.controller.GameController;

@SpringBootApplication
@ComponentScan(basePackageClasses = {GameController.class})
public class WarhammerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarhammerServerApplication.class, args);
    }


}
