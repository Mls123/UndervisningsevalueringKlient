package sdk.Service;

import com.google.gson.Gson;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import sdk.Models.User;

import java.io.UnsupportedEncodingException;
import sdk.ServerConnection.Connection;
import sdk.ServerConnection.ResponseCallback;
import sdk.ServerConnection.ResponseParser;
import security.Digester;

/**
 * Service metoderne er inspireret af Jesper øvelseslærerens eksempler fra undervisningen
 * kilde henvisning: https://github.com/Distribuerede-Systemer-2016/java-client/blob/master/src/sdk/services/BookService.java
 */
public class UserService {

    private Connection connection;
    private Gson gson;

    public UserService(){
        this.connection = new Connection();
        this.gson = new Gson();
    }

    /**
     * Dette er en login metode som udfra et input af mail og password gemmer det i et objekt,
     * konvertere det til json, kryptere det og sender det til serveren for validering.
     * @param mail
     * @param password
     * @param responseCallback
     */
    public void login(String mail, String password, final ResponseCallback<User> responseCallback){

        HttpPost postRequest = new HttpPost(Connection.serverURL + "/login");

        User login = new User();
        login.setCbsMail(mail);
        login.setPassword(password);

        try {

            StringEntity userInfo = new StringEntity(Digester.encrypt(gson.toJson(login)));
            postRequest.setEntity(userInfo);
            /**
             * HEr defineres typen af contentet - Json
             */
            postRequest.setHeader("Content-Type", "application/json");

            connection.execute(postRequest, new ResponseParser() {
                public void payload(String json) {

                    /**
                     * Her dekrypteres den modtagen data og vidersendes - en succes træder i kraft ved at valideringen er en succes på serveren og brugeren findes.
                     */
                    User userLogin = gson.fromJson(Digester.decrypt(json), User.class);
                    responseCallback.success(userLogin);

                }
                public void error(int status) {
                    System.out.println(status);
                    responseCallback.error(status);
                }
            });


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
