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

    public String showStudies(String shortname){

        StudyService studyService = new StudyService();
        studyService.getAll(new ResponseCallback<ArrayList<Study>>() {
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

    public void showCoursesStudent(int currentUserId){
        CourseService courseService = new CourseService();
        courseService.getAll(currentUserId, new ResponseCallback<ArrayList<Course>>() {
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
        showLecturesStudent(code, currentUserId);
    }

    public void showLecturesStudent(String code, final int currentUserId){
        LectureService lectureService = new LectureService();

        lectureService.getAll(code, new ResponseCallback<ArrayList<Lecture>>() {
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

    public void showCoursesTeacher(int currentUserId){
        CourseService courseService = new CourseService();
        courseService.getAll(currentUserId, new ResponseCallback<ArrayList<Course>>() {
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

    public void showLecturesTeacher(String code, final int currentUserId){
        LectureService lectureService = new LectureService();

        lectureService.getAll(code, new ResponseCallback<ArrayList<Lecture>>() {
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

