package sdk.ServerConnection;

//er til så man kan lave et URL request.
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Connection {

    //Denne er static så den kan tilgås overalt i klassen.
    public static String serverURL = "http://localhost:6599/api";
    private CloseableHttpClient httpClient;

    public Connection(){
        this.httpClient = HttpClients.createDefault();
    }

    //her burges responseParser til og give feedback på et callback til serveren
    public void execute(HttpUriRequest uriRequest, final ResponseParser parser){

        // Create a custom response handler
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            public String handleResponse(final HttpResponse response) throws IOException {
                int status = response.getStatusLine().getStatusCode();

                //Er der en status kode på over 300 er der en fejl på serveren - under 300 er det succes.
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    parser.error(status);
                }
                return null;
            }

        };

        try {
            //her bliver der retuneret noget fra serveren af json
            String json = this.httpClient.execute(uriRequest, responseHandler);

            parser.payload(json);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

