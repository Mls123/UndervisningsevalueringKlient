package View;

import Logic.Controller;
import View.Menuer.ReviewMenu;
import sdk.Models.Course;
import sdk.Models.Lecture;
import sdk.Models.Review;
import sdk.Models.Size;
import sdk.ServerConnection.ResponseCallback;
import sdk.Service.CourseService;
import sdk.Service.LectureService;
import sdk.Service.ReviewService;

import java.util.ArrayList;
import java.util.Scanner;

public class ParticipationView {

    public void participationMenu(int currentUserId){
        System.out.println("(1) - Se deltagelse for en lecture ");
        System.out.println("(2) - Se samlet deltagelse for et kursus");
        System.out.println("(3) - Se gennemsnit for deltagelsesraten af alle kurser ");

        Scanner inputReader = new Scanner(System.in);
        int choice = inputReader.nextInt();

        switch (choice) {

            case 1:
                showCourseParticipation(currentUserId);

                System.out.println("\nIndtast id for kurset hvis lectures ønskes vises: ");
                Scanner input = new Scanner(System.in);
                String code = input.nextLine();

                showLecturesParticipation(code);

                Controller controller = new Controller();
                controller.showTeacherMenu(currentUserId);

                break;
            case 2:
               showCourseParticipation(currentUserId);
               gatheredParticipationCourse();


                break;
            case 3:
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
                participationMenu(currentUserId);

                break;

            default:
                System.out.println("Prøv igen");
                participationMenu(currentUserId);
        }
    }

    public void showCourseParticipation(int currentUserId){
        CourseService courseService = new CourseService();
        courseService.getAll(currentUserId, new ResponseCallback<ArrayList<Course>>() {
            public void success(ArrayList<Course> data) {
                for (Course course : data) {
                    System.out.println("\nid: " + course.getId());
                    System.out.println("Name: " + course.getDisplaytext());
                    System.out.println("Code: " + course.getCode() + "\n");

                }
            }

            public void error(int status) {

            }
        });

    }

    public void showLecturesParticipation(String code){

            LectureService lectureService = new LectureService();
            lectureService.getAll(code, new ResponseCallback<ArrayList<Lecture>>() {
                public void success(ArrayList<Lecture> data) {
                for (Lecture lecture : data) {
                    if (data.size() == 0) {
                        System.out.println("Der er ingen lectures til dette kursus");

                    }else if (data.size() != 0){
                        for (Lecture lecture1 : data) {
                            System.out.println("\nid:          " + lecture.getId());
                            System.out.println("Type:        " + lecture.getType());
                            System.out.println("Description: " + lecture.getDescription());

                        }
                        lectureDataNullFalse();
                    }
                }
            }

                public void error(int status) {

                }
            });

        }

    public void lectureDataNullFalse (){
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id for at se deltagelse til den tilhørende lecture: ");
        int currentLectureId = input.nextInt();
        showRatingsParticipation(currentLectureId);
    }

    public void showRatingsParticipation(int currentLectureId){

        ReviewService reviewService = new ReviewService();
        reviewService.getAll(currentLectureId, new ResponseCallback<ArrayList<Review>>() {
            public void success(ArrayList<Review> data) {
                for (Review review : data) {
                    if (data.size() == 0){
                        System.out.println("Der er ingen der har deltaget");
                    } else{
                        System.out.println("\nDenne deltagelses tælling er på baggrund af besvarelser givet af en forelæsning´eller lecture \n");
                        System.out.println("Deltagelse: " + data.size());
                    }
                }
            }

            public void error(int status) {

            }
        });

    }

    public void gatheredParticipationCourse() {
        System.out.println("\nIndtast id for kursuset hvis samlede deltagelse ønskes vises: ");
        Scanner input = new Scanner(System.in);
        String code = input.nextLine();

        LectureService lectureService = new LectureService();
        lectureService.getAll(code, new ResponseCallback<ArrayList<Lecture>>() {
            public void success(ArrayList<Lecture> data) {
                int lectureId = 0;
                for (Lecture lecture : data) {
                    lectureId = lecture.getId();
                    bom(lectureId);
                }

            }

            public void error(int status) {

            }
        });

    }

    public void bom(int lectureId){
        ReviewService reviewService = new ReviewService();
        reviewService.getAll(lectureId, new ResponseCallback<ArrayList<Review>>() {

            public void success(ArrayList<Review> data) {

                for (Review review : data) {
                    int size = data.size();
                    size++;
                    System.out.println(size+1);

                    ArrayList<Size> size1 = new ArrayList<Size>();

                }
            }

            public void error(int status) {

            }
        });
    }
}
