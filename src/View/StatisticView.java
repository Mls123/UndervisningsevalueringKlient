package View;

import sdk.Models.Course;
import sdk.ServerConnection.ResponseCallback;
import sdk.Service.CourseService;

import java.util.ArrayList;

public class StatisticView {

    public void calculateAverageRatingOnCourse(){
        CourseService courseService = new CourseService();

        int courseId = 0;

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
