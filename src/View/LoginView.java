package View;

import Logic.Controller;
import sdk.Models.Lecture;
import sdk.Models.User;
import sdk.ServerConnection.ResponseCallback;
import sdk.Service.UserService;
import security.Digester;

import java.util.Scanner;

public class LoginView {

    public void presentLogin() {

        System.out.println("Velkommen til Undervisningsevaluering!");
        Scanner input1 = new Scanner(System.in);
        System.out.println("Indtast Cbs mail:");
        String mail = input1.nextLine();

        Scanner input2 = new Scanner(System.in);
        System.out.println("Indtast password:");
        String password = input2.nextLine();

        //hash password at login ("klient" side)
        String securePW = Digester.hashWithSalt(password);

        UserService userService = new UserService();
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
                System.out.println("Fejl: " + status);
                presentLogin();
            }
        });
    }
}
