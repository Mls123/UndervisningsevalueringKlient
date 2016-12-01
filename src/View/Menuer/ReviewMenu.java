package View.Menuer;

import Logic.Controller;
import View.ReviewView;
import sdk.Models.Lecture;
import sdk.Service.ReviewService;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReviewMenu {

    public void reviewMenuStudent (int currentUserId, int currentLectureId){

        System.out.println("(1) - Opret review");
        System.out.println("(2) - Gå tilbage til menuen");
        System.out.println("(3) - Shut down");

        try{
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        switch (choice) {

            case 1:
                ReviewView reviewView = new ReviewView();
                reviewView.createReviewStudent(currentUserId, currentLectureId);
                break;
            case 2:
                Controller controller = new Controller();
                controller.showStudentMenu(currentUserId);
                break;
            case 3:
                System.exit(0);
                break;

            default:
                System.out.println("Prøv igen");
                reviewMenuStudent(currentUserId, currentLectureId);
                break;
        }
        }catch (InputMismatchException e){
            System.out.println("Forkert værdi indtastet");
            reviewMenuStudent(currentUserId, currentLectureId);
        }
    }

    public void reviewMenuStudentMetode1 (int currentUserId){

        System.out.println("(1) - Slet review");
        System.out.println("(2) - Gå tilbage til menuen");
        System.out.println("(3) - Shut down");

        try{
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        switch (choice) {

            case 1:
                ReviewView reviewView = new ReviewView();
                reviewView.deleteReviewStudent(currentUserId);
                break;

            case 2:
                Controller controller = new Controller();
                controller.showStudentMenu(currentUserId);
                break;
            case 3:
                System.exit(0);
                break;

            default:
                System.out.println("Prøv igen");
                reviewMenuStudentMetode1(currentUserId);
                break;
        }
        }catch (InputMismatchException e){
            System.out.println("Forkert værdi indtastet");
            reviewMenuStudentMetode1(currentUserId);
        }
    }

    public void reviewMenuTeacher (int currentUserId){

        System.out.println("(1) - Slet review");
        System.out.println("(2) - Gå tilbage til menuen");
        System.out.println("(3) - Shut down");

        try{
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        switch (choice) {

            case 1:
                ReviewView reviewView = new ReviewView();
                reviewView.deleteReviewTeacher(currentUserId);
                break;
            case 2:
                Controller controller = new Controller();
                controller.showTeacherMenu(currentUserId);
                break;

            case 3:
                System.exit(0);
                break;

            default:
                System.out.println("Prøv igen");
                reviewMenuTeacher(currentUserId);
                break;
        }
        }catch (InputMismatchException e){
            System.out.println("Forkert værdi indtastet");
            reviewMenuTeacher(currentUserId);
        }
    }
}
