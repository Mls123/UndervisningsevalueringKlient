package View;

import View.Menuer.TeacherMenu;
import sdk.Models.Course;
import sdk.ServerConnection.ResponseCallback;
import sdk.Service.CourseService;
import sdk.Service.TeacherService;

import java.util.ArrayList;
import java.util.Scanner;

public class StatisticView {

    /**
     * Denne metode er til og få et input af hvilket kursus brugeren ønsker og se den gennemsnitlige rating for
     */
    public void calculateAverageRatingOnCourse(){
        TeacherService teacherService = new TeacherService();

        System.out.println("\nindtast id for ønsket kursus: ");
        Scanner input = new Scanner(System.in);
        int courseId = input.nextInt();

        /**
         * HEr kaldes service metoden der laver forbindelsen til serveren
         */
        teacherService.getAverageRatingCourse(courseId, new ResponseCallback<String>() {
            public void success(String data) {
                //Ved succes udskrives dataen
                System.out.println("Gennemsnittet for dette kursus rating er: " + data);
            }

            public void error(int status) {

            }
        });
    }

    /**
     * Her er metoden som spørger brugerenn efter et id for det kursus brugeren vil se antal studerende der er tilmeldt kurset.
     */
    public void courseParticipation(){

        System.out.println("indtast id for ønsket kursus ");
        Scanner input = new Scanner(System.in);
        int courseId = input.nextInt();

        TeacherService teacherService = new TeacherService();
        /**
         * Her kaldes på service metoden som laver forbindelse til databasen
         */
        teacherService.getCourseParticipation(courseId, new ResponseCallback<String>() {
            public void success(String data) {
                System.out.println("Deltagelse: " + data);
            }

            public void error(int status) {

            }
        });
    }

    /**
     * Denne metode skulle udskrive den gennemsnitlige rating for en lecture - den er dog ikke lavet færdig.
     * @param currentUser
     */
    public void calculateAverageRatingOnLecture(int currentUser){
        System.out.println("Work in progress..");
        TeacherMenu teacherMenu = new TeacherMenu();
        teacherMenu.teacherMenu(currentUser);
    }

}
