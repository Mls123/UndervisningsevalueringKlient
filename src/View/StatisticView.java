package View;

import View.Menuer.TeacherMenu;
import sdk.Models.Course;
import sdk.ServerConnection.ResponseCallback;
import sdk.Service.CourseService;
import sdk.Service.TeacherService;

import java.util.ArrayList;
import java.util.Scanner;

public class StatisticView {

    public void calculateAverageRatingOnCourse(){
        TeacherService teacherService = new TeacherService();

        System.out.println("\nindtast id for ønsket kursus: ");
        Scanner input = new Scanner(System.in);
        int courseId = input.nextInt();

        teacherService.getAverageRatingCourse(courseId, new ResponseCallback<String>() {
            public void success(String data) {
                System.out.println("Gennemsnittet for dette kursus rating er: " + data);
            }

            public void error(int status) {

            }
        });
    }

    public void courseParticipation(){

        System.out.println("indtast id for ønsket kursus ");
        Scanner input = new Scanner(System.in);
        int courseId = input.nextInt();

        TeacherService teacherService = new TeacherService();
        teacherService.getCourseParticipation(courseId, new ResponseCallback<String>() {
            public void success(String data) {
                System.out.println("Deltagelse: " + data);
            }

            public void error(int status) {

            }
        });
    }

    public void calculateAverageRatingOnLecture(int currentUser){
        System.out.println("Work in progress..");
        TeacherMenu teacherMenu = new TeacherMenu();
        teacherMenu.teacherMenu(currentUser);
    }

}
