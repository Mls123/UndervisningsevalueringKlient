package View.Menuer;

import View.KursusView;
import View.Menuer.ReviewMenu;
import View.ReviewView;
import sdk.Models.Lecture;

import java.util.Scanner;

public class StudentMenu {

        public void studentMenu(int currentUserId) {

                System.out.println("\n" + "============================================" + "\n");
                System.out.println("Main menu");
                System.out.println("(1) - Dine Kurser");
                System.out.println("(2) - Dine Ratings");
                System.out.println("(4) - Shut down");

                Scanner inputReader = new Scanner(System.in);
                int choice = inputReader.nextInt();

                switch (choice) {

                    case 1:
                        KursusView kursusView = new KursusView();
                        kursusView.showCoursesStudent(currentUserId);

                        break;

                    case 2:
                        ReviewView reviewView = new ReviewView();
                        reviewView.showRatingsFromUser(currentUserId);

                        ReviewMenu reviewMenu1 = new ReviewMenu();
                        reviewMenu1.reviewMenuStudentMetode1(currentUserId);

                        break;

                    case 4:
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Prøv igen");
                        studentMenu(currentUserId);
                        break;
                }
            }
        }
