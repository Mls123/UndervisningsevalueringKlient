package View;

import Logic.Controller;
import sdk.Models.Lecture;
import sdk.Models.User;
import sdk.ServerConnection.ResponseCallback;
import sdk.Service.UserService;
import security.Digester;

import java.util.Scanner;

public class LoginView {

    /**
     * Denne metode er den første som bliver vist ved programmets opstart
     * brugeren bliver spurgt efter mail og password - passwordet hashes og sendes til serveren for validering.
     * er valideringen en succes bliver brugren sendt til den respektive menu.
     */
    public void presentLogin() {

        System.out.println("Velkommen til Undervisningsevaluering!");
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast Cbs mail:");
        String mail = input.nextLine();

        Scanner input1 = new Scanner(System.in);
        System.out.println("Indtast password:");
        String password = input1.nextLine();

        /**
         *  hashing af password
         */
        String securePW = Digester.hashWithSalt(password);

        UserService userService = new UserService();
        /**
         * Her kaldes på service metoden som laver forbindelse til databasen
         */
        userService.login(mail, securePW, new ResponseCallback<User>() {
            public void success(User data) {

                if (data == null) {

                    System.out.println(
                            "▄██████████████▄▐█▄▄▄▄█▌\n" +
                            "██████▌▄▌▄▐▐▌███▌▀▀██▀▀\n" +
                            "████▄█▌▄▌▄▐▐▌▀███▄▄█▌\n" +
                            "▄▄▄▄▄██████████████▀");
                    System.out.println("Fejl, prøv igen");
                    presentLogin();
                } else {
                    System.out.println("" +
                            "░░░░░░░░░░░░░░░░░░░░░░█████████\n" +
                            "░░███████░░░░░░░░░░███▒▒▒▒▒▒▒▒███\n" +
                            "░░█▒▒▒▒▒▒█░░░░░░░███▒▒▒▒▒▒▒▒▒▒▒▒▒███\n" +
                            "░░░█▒▒▒▒▒▒█░░░░██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██\n" +
                            "░░░░█▒▒▒▒▒█░░░██▒▒▒▒▒██▒▒▒▒▒▒██▒▒▒▒▒███\n" +
                            "░░░░░█▒▒▒█░░░█▒▒▒▒▒▒████▒▒▒▒████▒▒▒▒▒▒██\n" +
                            "░░░█████████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██\n" +
                            "░░░█▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒██\n" +
                            "░██▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒██▒▒▒▒▒▒▒▒▒▒██▒▒▒▒██\n" +
                            "██▒▒▒███████████▒▒▒▒▒██▒▒▒▒▒▒▒▒██▒▒▒▒▒██\n" +
                            "█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒████████▒▒▒▒▒▒▒██\n" +
                            "██▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██\n" +
                            "░█▒▒▒███████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██\n" +
                            "░██▒▒▒▒▒▒▒▒▒▒████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█\n" +
                            "░░████████████░░░█████████████████");


                    /**
                     * Her valideres typen af brugeren og brugeren sendes til den respektive menu
                     */
                    int currentUserId = data.getId();

                    if (data.getType().contentEquals("student")) {
                        System.out.println("\n Velkommen " + data.getCbsMail() + " du er logget in som en studerende");
                        Controller controller = new Controller();
                        controller.showStudentMenu(currentUserId);
                    }

                    if (data.getType().contentEquals("teacher")){
                        System.out.println("\n Velkommen " + data.getCbsMail() + " du er logget in som en teacher");
                        Controller controller = new Controller();
                        controller.showTeacherMenu(currentUserId);
                    }
                    if (data.getType().contentEquals("admin")){
                        System.out.println("\n Admin kan ikke benytte klienten, brug TUI i serveren.");
                        presentLogin();
                    }
                }
            }

            public void error(int status) {
                System.out.println(
                                "▄██████████████▄▐█▄▄▄▄█▌\n" +
                                "██████▌▄▌▄▐▐▌███▌▀▀██▀▀\n" +
                                "████▄█▌▄▌▄▐▐▌▀███▄▄█▌\n" +
                                "▄▄▄▄▄██████████████▀");
                System.out.println(status);
                presentLogin();
            }
        });
    }
}
