package sdk.Service;

import View.KursusView;
import com.google.gson.Gson;
import org.apache.http.client.methods.HttpGet;
import sdk.ServerConnection.Connection;
import sdk.ServerConnection.ResponseCallback;
import sdk.ServerConnection.ResponseParser;
import security.Digester;

/**
 * Service metoderne er inspireret af Jesper øvelseslærerens eksempler fra undervisningen
 * kilde henvisning: https://github.com/Distribuerede-Systemer-2016/java-client/blob/master/src/sdk/services/BookService.java
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

    /**
     * Denne meotde henter det antal brugere som er tildelt et kursus, det kursus defineres udfra et kursus id-
     * @param courseId
     * @param responseCallback
     */
    public void getCourseParticipation(int courseId, final ResponseCallback<String> responseCallback){

        /**
         * Kryptering
         */
        String courseParticipationEncrypt = Digester.encrypt(String.valueOf(courseId));

        /**
         * HEr defineres URL som endpointet skal bruge
         */
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/teacher/courseParticipation/" +  courseParticipationEncrypt);

        /**
         * Her skabes en forbindelse til serveren
         */
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                /**
                 * HEr dekrypteres den modtagen json data og sendes videre.
                 */
                String jsonDecrypt = Digester.decrypt(json);
                responseCallback.success(jsonDecrypt);
            }

            public void error(int status) {
                System.out.println(status);
                responseCallback.error(status);
            }
        });

    }

    /**
     * Denne metode henter et gennemsnit af ratingsne på et kursus. dette er udfra et kursusid.
     * @param courseId
     * @param responseCallback
     */
    public void getAverageRatingCourse(int courseId, final ResponseCallback<String> responseCallback){
        /**
         * Her krypteres den data der sendes til serveren
         */
        String courseIdEncrypt = Digester.encrypt(String.valueOf(courseId));

        /**
         * Her defineres den URL som bruges til og ramme endpointet
         */
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/teacher/averageCourseRating/" +  courseIdEncrypt);

        /**
         * Her skabes en forbindelse til serveren
         */
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {
                /**
                 * HEr dekrypteres den modtagen json data og viderføres.
                 */
               String jsonDecrypt = Digester.decrypt(json);
               responseCallback.success(jsonDecrypt);
            }

            public void error(int status) {
                System.out.println(status);
                responseCallback.error(status);
            }
        });

    }
}
