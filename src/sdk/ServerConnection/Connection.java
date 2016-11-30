package sdk.ServerConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Denne klasse er lavet i fællesskab med øvelseslæreren Jesper.
 * kilde henvisning: https://github.com/Distribuerede-Systemer-2016/java-client/blob/master/src/sdk/connection/Connection.java
 */
public class Connection {

    /**
     * Denne er static så den kan tilgås overalt i klassen.
     */
    public static String serverURL = "http://localhost:6599/api";
    private CloseableHttpClient httpClient;

    /**
     * Dette er en constructor som kører en metode ved kaldet på httpClient
     */
    public Connection(){
            this.httpClient = HttpClients.createDefault();

    }

    /**
     * her burges responseParser til og give feedback på et callback til serveren
     */
    public void execute(HttpUriRequest uriRequest, final ResponseParser parser) {

        // Her laves en response handler, som er en "indbygget" metode
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            public String handleResponse(final HttpResponse response) throws IOException {
                /**
                 * Her hente status koden, så man kan få den som output
                 * og derfor få tilkendegivet om fejlen er af koden 200 til 400 = klient fejl
                 * eller 500 + = server fejl.
                 */
                int status = response.getStatusLine().getStatusCode();

                /**
                 * Her valideres status koden om der er en fejl eller succes.
                 */
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    /**
                     * Her bruges ResponseParseren, til og tilkendegive en fejl ved modtagelsen af json fra serveren og vidergive fejlkoden.
                     */
                    parser.error(status);
                }
                return null;
            }

        };

        try {

            /**
             * her bliver der retuneret noget fra serveren af json
             */
            String json = this.httpClient.execute(uriRequest, responseHandler);

            if (json != null) {
                /**
                 * Her bruges ResponseParseren, til og viderføre det json som fås fra serveren.
                 */
                parser.payload(json);
            }

        } catch (IOException e) {
            System.out.println("Serveren kører ikke!");
        }
    }
}

