package View.Menuer;

import Logic.Controller;
import View.Menuer.TeacherMenu;
import View.StatisticView;
import sdk.Models.Course;
import sdk.Models.Lecture;
import sdk.Models.Review;
import sdk.ServerConnection.ResponseCallback;
import sdk.Service.CourseService;
import sdk.Service.LectureService;
import sdk.Service.ReviewService;
import sdk.Service.TeacherService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ParticipationMenu {

    public void participationMenu(int currentUserId){
        System.out.println("(1) - Deltagelse for en lecture ");
        System.out.println("(2) - Deltagere tildelt kursus ");
        System.out.println("(3) - Samlet rating for et kursus ");
        //System.out.println("(4) - Samlet rating for en lecture ");
        System.out.println("(4) - Gå til main menu");
        System.out.println("(5) - Stop programmet");

        try {
        Scanner inputReader = new Scanner(System.in);
        int choice = inputReader.nextInt();

            switch (choice) {

                case 1:
                    showCourses(currentUserId);
                    showLectures(currentUserId);
                    participationMenu(currentUserId);

                    break;
                case 2:
                    showCourses(currentUserId);
                    StatisticView statisticView = new StatisticView();
                    statisticView.courseParticipation();

                    participationMenu(currentUserId);

                    break;
                case 3:
                    showCourses(currentUserId);
                    StatisticView statisticView1 = new StatisticView();
                    statisticView1.calculateAverageRatingOnCourse();

                    participationMenu(currentUserId);

                    break;
                case 4:
                    Controller controller3 = new Controller();
                    controller3.showTeacherMenu(currentUserId);

                    break;
                case 5:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Prøv igen");
                    participationMenu(currentUserId);
            }
        }catch (InputMismatchException e){
            System.out.println("Forkert værdi indtastet");
            participationMenu(currentUserId);
        }
    }

    public void showCourses(int currentUserId){
        CourseService courseService = new CourseService();
        courseService.getAllCourses(currentUserId, new ResponseCallback<ArrayList<Course>>() {
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

    public void showLectures(final int currentUserId){

        System.out.println("\nIndtast id for kurset hvis lectures ønskes vises: ");
        Scanner input = new Scanner(System.in);
        String code = input.nextLine();

            LectureService lectureService = new LectureService();
            lectureService.getAllLectures(code, new ResponseCallback<ArrayList<Lecture>>() {
                public void success(ArrayList<Lecture> data) {
                    if (data.size() == 0) {
                        System.out.println("Der er ingen lectures til dette kursus");
                    }

                    for (Lecture lecture : data) {
                            System.out.println("\nid:          " + lecture.getId());
                            System.out.println("Type:        " + lecture.getType());
                            System.out.println("Description: " + lecture.getDescription());

                        }
                        lectureDataNullFalse(currentUserId);
                    }

                public void error(int status) {

                }
            });

        }

    public void lectureDataNullFalse (int currentUserId){
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id for at se deltagelse til den tilhørende lecture: ");
        int currentLectureId = input.nextInt();
        showRatings(currentLectureId, currentUserId);
    }

    public void showRatings(int currentLectureId, int currentUserId){

        ReviewService reviewService = new ReviewService();
        reviewService.getAllReviewFromLecttureId(currentLectureId, new ResponseCallback<ArrayList<Review>>() {
            public void success(ArrayList<Review> data) {
                for (Review review : data) {
                    /*if (data.size() == 0){
                        System.out.println("Der er ingen der har deltaget");
                    } else{
                        System.out.println("\nDenne deltagelses tælling er på baggrund af besvarelser givet af en forelæsning eller lecture \n");*/
                        System.out.println("Deltagelse: " + data.size() + "\n");
                }
            }

            public void error(int status) {

            }
        });
    }
}
