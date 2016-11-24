import Logic.ConfigLoader;
import Logic.Controller;

public class Main {

    public static void main(String[] args) {

        ConfigLoader.parseConfig();

        Controller controller = new Controller();
        controller.showLoginView();
    }
}

