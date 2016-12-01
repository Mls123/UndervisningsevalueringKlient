package View.Menuer;

import View.KursusView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TeacherMenu {

    public void teacherMenu(int currentUserId) {

        System.out.println("\n" + "============================================" + "\n");
        System.out.println("Main menu");
        System.out.println("(1) - Kurser og ratings");
        System.out.println("(2) - Deltagelse og statistik");
        System.out.println("(3) - Shut down");
        try {
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();

            switch (choice) {

                case 1:

                    KursusView kursusView = new KursusView();
                    kursusView.showCoursesTeacher(currentUserId);

                    ReviewMenu reviewMenu = new ReviewMenu();
                    reviewMenu.reviewMenuTeacher(currentUserId);

                    break;

                case 2:
                    ParticipationMenu participationView = new ParticipationMenu();
                    participationView.participationMenu(currentUserId);
                    break;

                case 3:
                    System.exit(0);

                    break;

                default:
                    System.out.println("Prøv igen");
                    teacherMenu(currentUserId);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Forkert værdi indtastet");
            teacherMenu(currentUserId);

        }
    }
}
