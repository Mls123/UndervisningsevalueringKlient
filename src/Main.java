import Logic.ConfigLoader;
import Logic.Controller;

public class Main {

    public static void main(String[] args) {

        /**
         * Her parses config filen og dens informationer
         */
        ConfigLoader.parseConfig();

        Controller controller = new Controller();
        controller.showLoginView();
    }
}

