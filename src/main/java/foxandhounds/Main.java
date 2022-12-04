package foxandhounds;

import foxandhounds.service.game.GameController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("foxandhounds");

        GameController gameController = applicationContext.getBean(GameController.class);
        gameController.start();

    }
}
