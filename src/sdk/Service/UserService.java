package sdk.Service;

import com.google.gson.Gson;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import sdk.Models.CurrentUser;
import sdk.Models.User;

import java.io.UnsupportedEncodingException;
import sdk.ServerConnection.Connection;
import sdk.ServerConnection.ResponseCallback;
import sdk.ServerConnection.ResponseParser;
import security.Digester;

public class UserService {

    private Connection connection;
    private Gson gson;

    public UserService(){
        this.connection = new Connection();
        this.gson = new Gson();
    }

    public void login(String mail, String password, final ResponseCallback<User> responseCallback){

        HttpPost postRequest = new HttpPost(Connection.serverURL + "/login");

        User login = new User();
        login.setCbsMail(mail);
        login.setPassword(password);

        try {

            StringEntity userInfo = new StringEntity(this.gson.toJson(login));
            postRequest.setEntity(userInfo);
            postRequest.setHeader("Content-Type", "application/json");

            connection.execute(postRequest, new ResponseParser() {
                public void payload(String json) {
                    User userLogin = gson.fromJson(json, User.class);
                    responseCallback.success(userLogin);

                }
                public void error(int status) {
                    responseCallback.error(status);
                }
            });


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
