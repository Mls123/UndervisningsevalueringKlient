package sdk.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpGet;
import sdk.Models.Lecture;
import sdk.ServerConnection.Connection;
import sdk.ServerConnection.ResponseCallback;
import sdk.ServerConnection.ResponseParser;
import security.Digester;

import java.util.ArrayList;

/**
 * Service metoderne er inspireret af Jesper øvelseslærerens eksempler fra undervisningen
 * kilde henvisning: https://github.com/Distribuerede-Systemer-2016/java-client/blob/master/src/sdk/services/BookService.java
 */
public class LectureService {

    private Connection connection;
    private Gson gson;
    private Digester digester;

    /**
     * en constructor, når der initieres en bookservice kaldes denne også, og her laves en forbindelse.
     */
    public LectureService(){
        this.connection = new Connection();
        this.gson = new Gson();
        this.digester = new Digester();
    }

    /**
     * denne metode henter alle lectures.
     */
    public void getAllLectures(String code, final ResponseCallback<ArrayList<Lecture>> responseCallback){

        /**
         * Kryptering
         */
        String codeEncrypt = Digester.encrypt(code);

        /**
         * URL specifikation til endpoint
         */
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/lecture/" + codeEncrypt);

        /**
         * forbindelse skabes til serveren
         */
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                /**
                 * Her bliver det modtagede json gemt i en arrayliste
                 * dekryptering
                 */
                ArrayList<Lecture> lectures = gson.fromJson(Digester.decrypt(json), new TypeToken<ArrayList<Lecture>>(){}.getType());
                responseCallback.success(lectures);
            }

            public void error(int status) {
                System.out.println(status);
                responseCallback.error(status);
            }
        });

    }
}
