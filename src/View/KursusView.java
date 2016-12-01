package View;

import Logic.Controller;
import sdk.Models.*;
import sdk.ServerConnection.ResponseCallback;
import sdk.Service.CourseService;
import sdk.Service.LectureService;
import sdk.Service.ReviewService;
import sdk.Service.StudyService;

import java.util.ArrayList;
import java.util.Scanner;

public class KursusView {

    /**
     * denne metode viser study - er ikke brugt.
     * @param shortname
     * @return
     */
    public String showStudies(String shortname){

        StudyService studyService = new StudyService();
        /**
         * Her kaldes på service metoden som laver forbindelse til databasen
         */
        studyService.getAllStudies(new ResponseCallback<ArrayList<Study>>() {
            public void success(ArrayList<Study> data) {
                for (Study study : data) {
                    System.out.println("\n"+"Id:        " + study.getId() + "\n");
                    System.out.println("Name:      " + study.getName() + "\n");
                    System.out.println("Shortname: " + study.getShortname() + "\n");

                }
            }

            public void error(int status) {

            }
        });

        Scanner input = new Scanner(System.in);
        System.out.println("Indtast shortname for og se de tilhørende kurser: ");
        shortname = input.nextLine();

        return shortname;
    }

    /**
     * denne metode viser kurser udfra et userId - denne metode hiver fat i et student endpoint
     * @param currentUserId
     */
    public void showCoursesStudent(final int currentUserId) {
        CourseService courseService = new CourseService();
        /**
         * Her kaldes på service metoden som laver forbindelse til databasen
         */
        courseService.getAllCourses(currentUserId, new ResponseCallback<ArrayList<Course>>() {
            public void success(ArrayList<Course> data) {
                    for (Course course : data) {
                        System.out.println("\n" + "id: " + course.getId());
                        System.out.println("Name: " + course.getDisplaytext());
                        System.out.println("Code: " + course.getCode() + "\n");

                    }
            }
            public void error(int status) {

            }
        });
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id for at se Lectures til et af dine kurser: ");
        String code = input.nextLine();
        showLecturesStudent(code, currentUserId);
    }

    /**
     * denne metode er til og vise lectures udfra et code input
     * denne metode er for studerende og hiver fat i et student endpoint
     * @param code
     * @param currentUserId
     */
    public void showLecturesStudent(String code, final int currentUserId){
        LectureService lectureService = new LectureService();
        /**
         * Her kaldes på service metoden som laver forbindelse til databasen
         */
        lectureService.getAllLectures(code, new ResponseCallback<ArrayList<Lecture>>() {
            public void success(ArrayList<Lecture> data) {
                for (Lecture lecture : data) {
                    System.out.println("\n" + "id:          " + lecture.getId());
                    System.out.println("Type:        " + lecture.getType());
                    System.out.println("Description: " + lecture.getDescription());

                }
            }

            public void error(int status) {

            }
        });
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id for at se Reviews til den tilhørende lecture: ");
        int currentLectureId = input.nextInt();

        ReviewView reviewView = new ReviewView();
        reviewView.showRatingsStudent(currentUserId, currentLectureId);
    }

    /**
     * denne metode viser kurser udfra et userId - denne metode hiver fat i et teacher endpoint
     * @param currentUserId
     */
    public void showCoursesTeacher(int currentUserId){
        CourseService courseService = new CourseService();
        /**
         * Her kaldes på service metoden som laver forbindelse til databasen
         */
        courseService.getAllCourses(currentUserId, new ResponseCallback<ArrayList<Course>>() {
            public void success(ArrayList<Course> data) {
                for (Course course : data) {
                    System.out.println("\n"+"id: " + course.getId());
                    System.out.println("Name: " + course.getDisplaytext());
                    System.out.println("Code: " + course.getCode() + "\n");

                }
            }

            public void error(int status) {

            }
        });
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id for at se Lectures til et af dine kurser: ");
        String code = input.nextLine();
        showLecturesTeacher(code, currentUserId);
    }

    /**
     * denne metode er til og vise lectures udfra et code input
     * denne metode er for teachers og hiver fat i et teacher endpoint
     * @param code
     * @param currentUserId
     */
    public void showLecturesTeacher(String code, final int currentUserId){
        LectureService lectureService = new LectureService();
        /**
         * Her kaldes på service metoden som laver forbindelse til databasen
         */
        lectureService.getAllLectures(code, new ResponseCallback<ArrayList<Lecture>>() {
            public void success(ArrayList<Lecture> data) {
                for (Lecture lecture : data) {
                    System.out.println("\n" + "id:          " + lecture.getId());
                    System.out.println("Type:        " + lecture.getType());
                    System.out.println("Description: " + lecture.getDescription());

                }
            }

            public void error(int status) {

            }
        });
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast id for at se Reviews til den tilhørende lecture: ");
        int currentLectureId = input.nextInt();

        ReviewView reviewView = new ReviewView();
        reviewView.showRatingsTeacher(currentUserId, currentLectureId);

    }
}

