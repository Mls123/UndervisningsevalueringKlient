package sdk.ServerConnection;

/**interface som bruges generisk
*Denne klasse er lavet i fællesskab med øvelselæreren Jesper
 */
public interface ResponseCallback<T> {

    /** brugeren bestemmer hvad T skal være - intil videre er det en placeholder. T=type*/
    void success(T data);
    void error(int status);

}
