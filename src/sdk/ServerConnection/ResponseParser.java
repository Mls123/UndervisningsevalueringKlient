package sdk.ServerConnection;

public interface ResponseParser {

    void payload(String json);
    void error(int status);

}
