package sdk.Service;

import View.KursusView;
import com.google.gson.Gson;
import org.apache.http.client.methods.HttpGet;
import sdk.ServerConnection.Connection;
import sdk.ServerConnection.ResponseCallback;
import sdk.ServerConnection.ResponseParser;
import security.Digester;

/**
 * Created by User on 26-11-2016.
 */
public class TeacherService {

    private Connection connection;
    private Gson gson;
    private Digester digester;
    private KursusView kursusView;

    //en constructor, når der initieres en bookservice kaldes denne også, og her laves en forbindelse.
    public TeacherService(){
        this.connection = new Connection();
        this.gson = new Gson();
        this.digester = new Digester();
        this.kursusView = new KursusView();
    }

    public void getCourseParticipation(int courseId, final ResponseCallback<String> responseCallback){

        String courseParticipationEncrypt = Digester.encrypt(String.valueOf(courseId));

        //der er http også hvilken metode du skal bruge get fx.
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/teacher/courseParticipation/" +  courseParticipationEncrypt);

        //i javascript skal this altid defineres, her behøves den ikke
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                String jsonDecrypt = Digester.decrypt(json);
                responseCallback.success(jsonDecrypt);
            }

            public void error(int status) {
                responseCallback.error(status);
            }
        });

    }
    public void getAverageRatingCourse(int courseId, final ResponseCallback<String> responseCallback){
        String courseIdEncrypt = Digester.encrypt(String.valueOf(courseId));

        //der er http også hvilken metode du skal bruge get fx.
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/teacher/averageCourseRating/" +  courseIdEncrypt);

        //i javascript skal this altid defineres, her behøves den ikke
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {
               String jsonDecrypt = Digester.decrypt(json);
               responseCallback.success(jsonDecrypt);
            }

            public void error(int status) {
                responseCallback.error(status);
            }
        });

    }
}
