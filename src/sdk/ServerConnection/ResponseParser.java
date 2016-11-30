package sdk.ServerConnection;

/**
 * Denne klasse er lavet i fællesskab med øvelselæreren Jesper
 * Denne klasse har til formål og hente det Json serveren sender til kllienten, se klassen Connection.
 * kilde henvisning: https://github.com/Distribuerede-Systemer-2016/java-client/blob/master/src/sdk/connection/ResponseParser.java
 */
public interface ResponseParser {

    void payload(String json);
    void error(int status);

}
