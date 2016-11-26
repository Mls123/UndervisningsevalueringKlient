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

public class LectureService {

    private Connection connection;
    private Gson gson;
    private Digester digester;

    //en constructor, når der initieres en bookservice kaldes denne også, og her laves en forbindelse.
    public LectureService(){
        this.connection = new Connection();
        this.gson = new Gson();
        this.digester = new Digester();
    }

    public void getAll(String code, final ResponseCallback<ArrayList<Lecture>> responseCallback){

        //der er http også hvilken metode du skal bruge get fx.
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/lecture/" + code);

        //i javascript skal this altid defineres, her behøves den ikke
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                //Her bliver det modtagede json gemt i en arrayliste
                ArrayList<Lecture> lectures = gson.fromJson(Digester.decrypt(json), new TypeToken<ArrayList<Lecture>>(){}.getType());
                responseCallback.success(lectures);
            }

            public void error(int status) {
                responseCallback.error(status);
            }
        });

    }
}
