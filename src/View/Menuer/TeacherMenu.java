package View.Menuer;

import View.KursusView;
import View.Menuer.ReviewMenu;
import View.ParticipationView;
import sdk.Models.Lecture;

import java.util.Scanner;

public class TeacherMenu {

    public void teacherMenu(int currentUserId){

    System.out.println("\n" + "============================================" + "\n");
    System.out.println("Main menu");
    System.out.println("(1) - Kurser og ratings");
    System.out.println("(2) - Statistik");
    System.out.println("(3) - Deltagelse og rating gennemsnit");
    System.out.println("(4) - Shut down");

    Scanner inputReader = new Scanner(System.in);
    int choice = inputReader.nextInt();

                switch (choice) {

        case 1:

            KursusView kursusView = new KursusView();
            kursusView.showCoursesTeacher(currentUserId);

            ReviewMenu reviewMenu = new ReviewMenu();
            reviewMenu.reviewMenuTeacher(currentUserId);

            break;

        case 2:
            System.out.println(
                    "░▄▀▄▀▀▀▀▄▀▄░░░░░░░░░\n" +
                            "░█░░░░░░░░▀▄░░░░░░▄░\n" +
                            "█░░▀░░▀░░░░░▀▄▄░░█░█\n" +
                            "█░▄░█▀░▄░░░░░░░▀▀░░█\n" +
                            "█░░▀▀▀▀░░░░░░░░░░░░█\n" +
                            "█░░░░░░░░░░░░░░░░░░█\n" +
                            "█░░░░░░░░░░░░░░░░░░█\n" +
                            "░█░░▄▄░░▄▄▄▄░░▄▄░░█░\n" +
                            "░█░▄▀█░▄▀░░█░▄▀█░▄▀░\n" +
                            "░░▀░░░▀░░░░░▀░░░▀░░░");
            System.out.println("\n" + "Work in progres...");
            teacherMenu(currentUserId);

            break;
        case 3:
            ParticipationView participationView = new ParticipationView();
            participationView.participationMenu(currentUserId);
            break;

        case 4:
            System.exit(0);

            break;

        default:
            System.out.println("Prøv igen");
            teacherMenu(currentUserId);
            break;
        }
    }

}
