package View;

import Logic.Controller;
import View.Menuer.ReviewMenu;
import View.Menuer.TeacherMenu;
import sdk.Models.Course;
import sdk.Models.Lecture;
import sdk.Models.Review;
import sdk.Models.Size;
import sdk.ServerConnection.ResponseCallback;
import sdk.Service.CourseService;
import sdk.Service.LectureService;
import sdk.Service.ReviewService;
import sdk.Service.TeacherService;

import java.util.ArrayList;
import java.util.Scanner;

public class ParticipationView {

    public void participationMenu(int currentUserId){
        System.out.println("(1) - Deltagelse for en lecture "); //Reviewparticipation
        System.out.println("(2) - Antal deltagere for et kursus ");
        System.out.println("(3) - Samlet rating for et kursus "); //calculateaverageratingoncourse
        System.out.println("(4) - Samlet rating for en lecture "); //calculateAverageRatingOnLecture
        System.out.println("(5) - Gå tilbage til main menu");

        Scanner inputReader = new Scanner(System.in);
        int choice = inputReader.nextInt();

        switch (choice) {

            case 1:
                showCourses(currentUserId);

                showLectures(currentUserId);

                Controller controller = new Controller();
                controller.showTeacherMenu(currentUserId);

                //reviewParticipation();

                break;
            case 2:
                showCourses(currentUserId);
                courseParticipation();

                break;
            case 3:
                StatisticView statisticView = new StatisticView();
                statisticView.calculateAverageRatingOnCourse();

                break;
            case 4:
                StatisticView statisticView1 = new StatisticView();
                statisticView1.calculateAverageRatingOnLecture();

                break;
            case 5:
                Controller controller1 = new Controller();
                controller1.showTeacherMenu(currentUserId);
                break;

            default:
                System.out.println("Prøv igen");
                participationMenu(currentUserId);
        }
    }

    public void courseParticipation(){

        System.out.println("indtast id for ønsket kursus ");
        Scanner input = new Scanner(System.in);
        int courseId = input.nextInt();

        TeacherService teacherService = new TeacherService();
        teacherService.getCourseParticipation(courseId, new ResponseCallback<ArrayList<Course>>() {
            public void success(ArrayList<Course> data) {
                    System.out.println("Deltagelse: " + data);
            }

            public void error(int status) {
                System.out.println(status);
            }
        });
    }

    public void reviewParticipation(){

    }


    public void showCourses(int currentUserId){
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

    public void showLectures(final int currentUserId){

        System.out.println("\nIndtast id for kurset hvis lectures ønskes vises: ");
        Scanner input = new Scanner(System.in);
        String code = input.nextLine();

            LectureService lectureService = new LectureService();
            lectureService.getAll(code, new ResponseCallback<ArrayList<Lecture>>() {
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
        reviewService.getAll(currentLectureId, new ResponseCallback<ArrayList<Review>>() {
            public void success(ArrayList<Review> data) {
                for (Review review : data) {
                    if (data.size() == 0){
                        System.out.println("Der er ingen der har deltaget");
                    } else{
                        System.out.println("\nDenne deltagelses tælling er på baggrund af besvarelser givet af en forelæsning eller lecture \n");
                        System.out.println("Deltagelse: " + data.size() + "\n");
                    }
                }
            }

            public void error(int status) {

            }
        });

        participationMenu(currentUserId);

    }
}
