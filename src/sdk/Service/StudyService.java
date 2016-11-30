package sdk.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpGet;
import sdk.Models.Review;
import sdk.Models.Study;
import sdk.ServerConnection.Connection;
import sdk.ServerConnection.ResponseCallback;
import sdk.ServerConnection.ResponseParser;
import security.Digester;

import java.util.ArrayList;

/**
 * Service metoderne er inspireret af Jesper øvelseslærerens eksempler fra undervisningen
 * kilde henvisning: https://github.com/Distribuerede-Systemer-2016/java-client/blob/master/src/sdk/services/BookService.java
 */
public class StudyService {
    private Connection connection;
    private Gson gson;
    private Digester digester;

    //en constructor, når der initieres en bookservice kaldes denne også, og her laves en forbindelse.
    public StudyService(){
        this.connection = new Connection();
        this.gson = new Gson();
        this.digester = new Digester();
    }

    /**
     * Denne metode er til og hente alle studies
     * på nuværende tidspunkt bruges denne metode ikke.
     * @param responseCallback
     */
    public void getAll(final ResponseCallback<ArrayList<Study>> responseCallback){

        String shortname = "BIVKU";
        String shortnameEncrypt = Digester.encrypt(shortname);
        //der er http også hvilken metode du skal bruge get fx.
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/study/" + shortnameEncrypt);

        //i javascript skal this altid defineres, her behøves den ikke
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                //String jsonDecrypt = Digester.decrypt(json);
                //Her bliver det modtagede json gemt i en arrayliste
                ArrayList<Study> studies = gson.fromJson(Digester.decrypt(json), new TypeToken<ArrayList<Study>>(){}.getType());
                responseCallback.success(studies);
            }

            public void error(int status) {
                responseCallback.error(status);
            }
        });

    }
}
