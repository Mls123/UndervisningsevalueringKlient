package View;

import sdk.Models.Course;
import sdk.ServerConnection.ResponseCallback;
import sdk.Service.CourseService;

import java.util.ArrayList;
import java.util.Scanner;

public class StatisticView {

    public void calculateAverageRatingOnCourse(){
        CourseService courseService = new CourseService();

        System.out.println("\nindtast id for kursus: ");
        Scanner input = new Scanner(System.in);
        int courseId = input.nextInt();

        courseService.getAverageRatingCourse(courseId, new ResponseCallback<ArrayList<Course>>() {
            public void success(ArrayList<Course> data) {
                System.out.println(data);
            }

            public void error(int status) {
                System.out.println(status);
            }
        });

    }

    public void calculateAverageRatingOnLecture(){

    }

}
