package sdk.Service;

import View.KursusView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpGet;

import java.util.ArrayList;

import sdk.Models.Course;
import sdk.ServerConnection.Connection;
import sdk.ServerConnection.ResponseCallback;
import sdk.ServerConnection.ResponseParser;
import security.Digester;

/**
 * Service metoderne er inspireret af Jesper øvelseslærerens eksempler fra undervisningen
 * kilde henvisning: https://github.com/Distribuerede-Systemer-2016/java-client/blob/master/src/sdk/services/BookService.java
 */
public class CourseService {
    private Connection connection;
    private Gson gson;

    //en constructor, når der initieres en bookservice kaldes denne også, og her laves en forbindelse.
    public CourseService(){
        this.connection = new Connection();
        this.gson = new Gson();
    }

    /**
     * ArrayList<course> = T, nu er pladsen T taget, derfor er den ikke en placeholder mere.
     * denne metode henter alle kurser.
     */
    public void getAll(int currentUserId, final ResponseCallback<ArrayList<Course>> responseCallback){

        /**
         * Denne metode kryptere det data som sendes til serveren
         */
        String currentUserIdEncrypt = Digester.encrypt(String.valueOf(currentUserId));

        /**
         *  her specificeres URL'en som skal burges til og fange det rigtige endpoint i serveren
         */
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/course/" +  currentUserIdEncrypt);

        /**
         * Her oprettes en forbindelse
         */
        connection.execute(getRequest, new ResponseParser() {

            /**
             * Her bruges responseParser metoden som har to metoder, en som træder til i tilfældet af en succes og en i tilfældet af en error
             * @param json
             */
            public void payload(String json) {

                /**
                 * Her bliver det modtagede json gemt i en arrayliste
                 * I denne linje bliver dataen også dekrypteret
                 */
                ArrayList<Course> courses = gson.fromJson(Digester.decrypt(json), new TypeToken<ArrayList<Course>>(){}.getType());
                responseCallback.success(courses);
            }

            public void error(int status) {
                System.out.println(status);
                responseCallback.error(status);
            }
        });

    }
}

