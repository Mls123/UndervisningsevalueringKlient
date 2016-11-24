package sdk.ServerConnection;

/*Denne klasse er lavet i fællesskab med øvelselæreren Jesper
*Denne klasse har til formål og hente det Json serveren sender til kllienten, se klassen Connection.*/
public interface ResponseParser {

    void payload(String json);
    void error(int status);

}
