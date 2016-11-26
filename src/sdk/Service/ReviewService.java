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

public class ReviewService {

    private Connection connection;
    private Gson gson;
    private Digester digester;

    //en constructor, når der initieres en bookservice kaldes denne også, og her laves en forbindelse.
    public ReviewService(){
        this.connection = new Connection();
        this.gson = new Gson();
        this.digester = new Digester();
    }

    public void getAll(int lectureId, final ResponseCallback<ArrayList<Review>> responseCallback){

        //der er http også hvilken metode du skal bruge get fx.
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/review/" + lectureId);

        //i javascript skal this altid defineres, her behøves den ikke
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                //String jsonDecrypt = Digester.decrypt(json);
                //Her bliver det modtagede json gemt i en arrayliste
                ArrayList<Review> reviews = gson.fromJson(Digester.decrypt(json), new TypeToken<ArrayList<Review>>(){}.getType());
                responseCallback.success(reviews);
            }

            public void error(int status) {
                responseCallback.error(status);
            }
        });

    }
    public void getAllFromUser(int currentUser, final ResponseCallback<ArrayList<Review>> responseCallback){

        //der er http også hvilken metode du skal bruge get fx.
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/reviews/" + currentUser);

        //i javascript skal this altid defineres, her behøves den ikke
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                //String jsonDecrypt = Digester.decrypt(json);
                //Her bliver det modtagede json gemt i en arrayliste
                ArrayList<Review> reviews = gson.fromJson(Digester.decrypt(json), new TypeToken<ArrayList<Review>>(){}.getType());
                responseCallback.success(reviews);
            }

            public void error(int status) {
                responseCallback.error(status);
            }
        });
    }

    public void create(final Review review, final ResponseCallback<Boolean> responseCallback){
        try {
            HttpPost postRequest = new HttpPost(Connection.serverURL + "/student/review");
            postRequest.addHeader("Content-Type", "application/json");

            StringEntity jsonReview = new StringEntity(Digester.encrypt(gson.toJson(review)));
            postRequest.setEntity(jsonReview);

            this.connection.execute(postRequest, new ResponseParser() {
                public void payload(String json) {
                    responseCallback.success(true);
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
    public void deleteReviewStudent(String reviewSletId, final ResponseCallback<Boolean> responseCallback){

        String reviewSletIdEncrypt = Digester.encrypt(reviewSletId);

        HttpDelete deleteRequest = new HttpDelete(Connection.serverURL + "/student/review/" + reviewSletIdEncrypt);
        deleteRequest.addHeader("Content-Type", "application/json");

        connection.execute(deleteRequest, new ResponseParser() {
            public void payload(String json) {
                responseCallback.success(true);
            }

            public void error(int status) {
                responseCallback.error(status);
            }
        });

    }
    public void deleteReviewTeacher(String reviewSletId, final ResponseCallback<Boolean> responseCallback){

        String reviewSletIdEncrypt = Digester.encrypt(reviewSletId);

        HttpDelete deleteRequest = new HttpDelete(Connection.serverURL + "/teacher/review/" + reviewSletIdEncrypt);
        deleteRequest.addHeader("Content-Type", "application/json");

        connection.execute(deleteRequest, new ResponseParser() {
            public void payload(String json) {
                responseCallback.success(true);
            }

            public void error(int status) {
                responseCallback.error(status);
            }
        });

    }
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
