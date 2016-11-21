package sdk.Service;

import View.KursusView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

import java.awt.print.Book;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import sdk.Models.Course;
import sdk.Models.CurrentUser;
import sdk.Models.User;
import sdk.ServerConnection.Connection;
import sdk.ServerConnection.ResponseCallback;
import sdk.ServerConnection.ResponseParser;
import security.Digester;

public class CourseService {
    private Connection connection;
    private Gson gson;
    private Digester digester;
    private KursusView kursusView;

    //en constructor, når der initieres en bookservice kaldes denne også, og her laves en forbindelse.
    public CourseService(){
        this.connection = new Connection();
        this.gson = new Gson();
        this.digester = new Digester();
        this.kursusView = new KursusView();
    }

    //ArrayList<Book> = T, nu er pladsen T taget, derfor er den ikke en placeholder mere.
    public void getAll(int currentUserId, final ResponseCallback<ArrayList<Course>> responseCallback){

        //der er http også hvilken metode du skal bruge get fx.
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/course/" +  currentUserId);

        //i javascript skal this altid defineres, her behøves den ikke
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                //String jsonDecrypt = Digester.decrypt(json);
                //Her bliver det modtagede json gemt i en arrayliste
                ArrayList<Course> courses = gson.fromJson(json, new TypeToken<ArrayList<Course>>(){}.getType());
                responseCallback.success(courses);
            }

            public void error(int status) {
                responseCallback.error(status);
            }
        });

    }
}

