package sdk.Service;

import View.ReviewView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import sdk.Models.Lecture;
import sdk.Models.Review;
import sdk.ServerConnection.Connection;
import sdk.ServerConnection.ResponseCallback;
import sdk.ServerConnection.ResponseParser;
import security.Digester;

import java.awt.print.Book;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Service metoderne er inspireret af Jesper øvelseslærerens eksempler fra undervisningen
 * kilde henvisning: https://github.com/Distribuerede-Systemer-2016/java-client/blob/master/src/sdk/services/BookService.java
 */
public class ReviewService {

    private Connection connection;
    private Gson gson;
    private Digester digester;

    public ReviewService(){
        this.connection = new Connection();
        this.gson = new Gson();
        this.digester = new Digester();
    }

    /**
     * Henter alle reviews udfra et lectureId
     * @param lectureId
     * @param responseCallback
     */
    public void getAllReviewFromLecttureId(int lectureId, final ResponseCallback<ArrayList<Review>> responseCallback){

        /**
         * kryptering
         */
        String lectureIdEncrypt = Digester.encrypt(String.valueOf(lectureId));

        /**
         * URL specifikation
         */
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/review/" + lectureIdEncrypt);

        /**
         * forbindelse skabes
         */
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                /**
                 * Her bliver det modtagede json gemt i en arrayliste
                 * der dekrypteres
                 */
                ArrayList<Review> reviews = gson.fromJson(Digester.decrypt(json), new TypeToken<ArrayList<Review>>(){}.getType());
                responseCallback.success(reviews);
            }

            public void error(int status) {
                System.out.println(status);
                responseCallback.error(status);
            }
        });

    }

    /**
     * henter alle review udfra et userId
     * @param currentUserId
     * @param responseCallback
     */
    public void getAllReviewFromUser(int currentUserId, final ResponseCallback<ArrayList<Review>> responseCallback){

        /**
         * Kryptering
         */
        String currentUserIdEncrypt = Digester.encrypt(String.valueOf(currentUserId));

        /**
         * URL defineres til endpointet
         */
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/reviews/" + currentUserIdEncrypt);

        /**
         * Forbindelse skabes til server
         */
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                /**
                 * Her bliver det modtagede json gemt i en arrayliste
                 * Her dekrypteres
                 */
                ArrayList<Review> reviews = gson.fromJson(Digester.decrypt(json), new TypeToken<ArrayList<Review>>(){}.getType());
                responseCallback.success(reviews);
            }

            public void error(int status) {
                System.out.println(status);
                responseCallback.error(status);
            }
        });
    }

    /**
     * Denne metode er til og oprette et nyt review.
     * @param review
     * @param responseCallback
     */
    public void createReview(final Review review, final ResponseCallback<Boolean> responseCallback){
        try {
            /**
             * Her defineres URL som passer til endpointet
             */
            HttpPost postRequest = new HttpPost(Connection.serverURL + "/student/review");

            /**
             * her defineres hvilken type data der hentes fra serveren - Json
             */
            postRequest.addHeader("Content-Type", "application/json");

            /**
             * her krypteres objektet review der skal tilføjes, og laves til en stringEntity.
             */
            StringEntity jsonReview = new StringEntity(Digester.encrypt(gson.toJson(review)));
            postRequest.setEntity(jsonReview);

            /**
             * Her laves en forbindelse til serveren
             */
            this.connection.execute(postRequest, new ResponseParser() {
                public void payload(String json) {
                    responseCallback.success(true);
                }

                public void error(int status) {
                    System.out.println(status);
                    responseCallback.error(status);
                }
            });
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    /**
     * Denne metode er til og slette er review udfra et reviewId
     * @param reviewId
     * @param responseCallback
     */
    public void deleteReviewStudent(String reviewId, final ResponseCallback<Boolean> responseCallback){

        /**
         * Kryptering
         */
        String reviewIdEncrypt = Digester.encrypt(reviewId);

        /**
         * HEr defineres den URL som bruges til endpointet
         */
        HttpDelete deleteRequest = new HttpDelete(Connection.serverURL + "/student/review/" + reviewIdEncrypt);

        /**
         * Definering af data type - json
         */
        deleteRequest.addHeader("Content-Type", "application/json");

        /**
         * Forbindelse til server oprettes
         */
        connection.execute(deleteRequest, new ResponseParser() {
            public void payload(String json) {
                responseCallback.success(true);
            }

            public void error(int status) {
                System.out.println(status);
                responseCallback.error(status);
            }
        });

    }

    /**
     * Denne metode sletter et review for en teacher udfra et reviewId
     * @param reviewId
     * @param responseCallback
     */
    public void deleteReviewTeacher(int reviewId, final ResponseCallback<Boolean> responseCallback){

        /**
         * Kryptering
         */
        String reviewIdEncrypt = Digester.encrypt(String.valueOf(reviewId));

        /**
         * Her defineres den URL som skal bruges for og skabe forbindelse til endpointet på serveren
         */
        HttpDelete deleteRequest = new HttpDelete(Connection.serverURL + "/teacher/review/" + reviewIdEncrypt);

        /**
         * her defineres hvilken type contentet er - Json
         */
        deleteRequest.addHeader("Content-Type", "application/json");

        /**
         * Her skabes en forbindelse til serveren
         */
        connection.execute(deleteRequest, new ResponseParser() {
            public void payload(String json) {
                responseCallback.success(true);
            }

            public void error(int status) {
                System.out.println(status);
                responseCallback.error(status);
            }
        });

    }

    /**
     * Denne metode er til og updatere review udfra et object af review, med de data som skal gemmes.
     * denne metode bruges ikke.
     * @param review
     * @param responseCallback
     */
    public void update(Review review, final ResponseCallback<Review> responseCallback){
        try {
            HttpPut updateRequest = new HttpPut(Connection.serverURL + "/review/" );
            //definition af type data der sendes
            updateRequest.addHeader("Content-Type", "application/json");

            StringEntity jsonReview = new StringEntity(gson.toJson(review));
            updateRequest.setEntity(jsonReview);
            connection.execute(updateRequest, new ResponseParser() {
                public void payload(String json) {
                    Review updatedReview = gson.fromJson(Digester.decrypt(json), Review.class);
                    responseCallback.success(updatedReview);
                }

                public void error(int status) {
                    responseCallback.error(status);
                }
            });

        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
