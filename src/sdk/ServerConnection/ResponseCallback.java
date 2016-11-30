package sdk.ServerConnection;

/**
 * interface som bruges generisk
 * Denne klasse er lavet i fællesskab med øvelselæreren Jesper
 * kilde henvisning: https://github.com/Distribuerede-Systemer-2016/java-client/blob/master/src/sdk/connection/ResponseCallback.java
 */
public interface ResponseCallback<T> {

    /**
     * brugeren bestemmer hvad T skal være - intil videre er det en placeholder. T=type
     */
    void success(T data);
    void error(int status);

}
