package View;

import sdk.Models.Lecture;

import java.util.Scanner;

public class StudentView {

        public void studentMenu(int currentUserId, int lectureId) {

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
                        kursusView.showCourses(currentUserId);

                        ReviewMenu reviewMenu = new ReviewMenu();
                        reviewMenu.reviewMenuStudent(currentUserId, lectureId);

                        break;

                    case 2:
                        ReviewView reviewView = new ReviewView();
                        reviewView.showRatingsFromUser(currentUserId);

                        ReviewMenu reviewMenu1 = new ReviewMenu();
                        reviewMenu1.reviewMenuStudentMetode1(currentUserId, lectureId);

                        break;

                    case 4:
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Prøv igen");
                        studentMenu(currentUserId, lectureId);
                        break;
                }
            }
        }
