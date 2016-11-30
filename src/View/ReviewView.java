package View;

import Logic.Controller;
import View.Menuer.ReviewMenu;
import View.Menuer.StudentMenu;
import com.sun.org.apache.xpath.internal.operations.Bool;
import sdk.Models.Course;
import sdk.Models.Lecture;
import sdk.Models.Review;
import sdk.ServerConnection.ResponseCallback;
import sdk.Service.CourseService;
import sdk.Service.LectureService;
import sdk.Service.ReviewService;

import java.util.ArrayList;
import java.util.Scanner;

public class ReviewView {

    /**
     * Denne metode viser reviews udfra et userId - det vil sige at metoden viser de review som brugeren der er logget ind har skrevet.
     * @param currentUserId
     * @param currentLectureId
     */
    public void showRatingsStudent(final int currentUserId, final int currentLectureId){

        ReviewService reviewService = new ReviewService();
        /**
         * Her kaldes på service metoden som laver forbindelse til databasen
         */
        reviewService.getAll(currentLectureId, new ResponseCallback<ArrayList<Review>>() {
            public void success(ArrayList<Review> data) {
                for (Review review : data) {
                    System.out.println("\n"+"id:        " + review.getId());
                    System.out.println("Rating:    " + review.getRating());
                    System.out.println("Kommentar: " + review.getComment() + "\n");
                }
            }

            public void error(int status) {

            }
        });
        ReviewMenu reviewMenu = new ReviewMenu();
        reviewMenu.reviewMenuStudent(currentUserId, currentLectureId);
    }

    /**
     *
     * @param currentUserId
     * @param currentLectureId
     */
    public void showRatingsTeacher(final int currentUserId, final int currentLectureId){

        ReviewService reviewService = new ReviewService();
        /**
         * Her kaldes på service metoden som laver forbindelse til databasen
         */
        reviewService.getAll(currentLectureId, new ResponseCallback<ArrayList<Review>>() {
            public void success(ArrayList<Review> data) {
                for (Review review : data) {
                    System.out.println("\n"+"id:        " + review.getId());
                    System.out.println("Rating:    " + review.getRating());
                    System.out.println("Kommentar: " + review.getComment() + "\n");

                }
            }

            public void error(int status) {

            }
        });
        ReviewMenu reviewMenu = new ReviewMenu();
        reviewMenu.reviewMenuTeacher(currentUserId);
    }

    /**
     *
     * @param currentUserId
     */
    public void showRatingsFromUser(int currentUserId){

        ReviewService reviewService = new ReviewService();
        /**
         * Her kaldes på service metoden som laver forbindelse til databasen
         */
        reviewService.getAllFromUser(currentUserId, new ResponseCallback<ArrayList<Review>>() {
            public void success(ArrayList<Review> data) {
                for (Review reviews : data) {
                    System.out.println("\n"+"id:        " + reviews.getId());
                    System.out.println("Rating:    " + reviews.getRating());
                    System.out.println("Kommentar: " + reviews.getComment() + "\n");

                }
            }

            public void error(int status) {

            }
        });
    }

    /**
     *
     * @param currentUserId
     * @param currentLectureId
     */
    public void createReviewStudent(final int currentUserId, final int currentLectureId) {

            //if ingen reviews - skriv det pænere.

            System.out.println("Oprettelse af review: ");

            System.out.println("Rating (fra 1 til 5, hvor 5 er bedst): ");
            Scanner inputReader = new Scanner(System.in);
            int rating = inputReader.nextInt();

            System.out.println("Kommentar: ");
            Scanner inputReader1 = new Scanner(System.in);
            final String comment = inputReader1.nextLine();

            ReviewService reviewService = new ReviewService();
            Review review = new Review();
            review.setComment(comment);
            review.setRating(rating);
            review.setUserId(currentUserId);
            review.setLectureId(currentLectureId);

            /**
            * Her kaldes på service metoden som laver forbindelse til databasen
            */
            reviewService.create(review, new ResponseCallback<Boolean>() {
                public void success(Boolean data) {
                    System.out.println("Reviewet er oprettet!");
                    Controller controller = new Controller();
                    controller.showStudentMenu(currentUserId);
                }

                public void error(int status) {

                }
            });
        }

    /**
     *
     * @param currentUserId
     */
    public void deleteReviewStudent(final int currentUserId) {

            if (currentUserId == 0) {
                System.out.println("Du skal logge ind for og slette et review!");

            } else {
                System.out.println("indtast review id, for den du vil slette: ");

                Scanner inputReader = new Scanner(System.in);
                int reviewSlet = inputReader.nextInt();

                String reviewSletId = String.valueOf(reviewSlet);

                ReviewService reviewService = new ReviewService();
                /**
                 * Her kaldes på service metoden som laver forbindelse til databasen
                 */
                reviewService.deleteReviewStudent(reviewSletId, new ResponseCallback<Boolean>() {
                    public void success(Boolean data) {
                        System.out.println("Reviewet er slettet.");
                        Controller controller = new Controller();
                        controller.showStudentMenu(currentUserId);
                    }

                    public void error(int status) {

                    }
                });

            }
        }

    /**
     *
     * @param currentUserId
     */
    public void deleteReviewTeacher(final int currentUserId) {

        if (currentUserId == 0) {
            System.out.println("Du skal logge ind for og slette et review!");

        } else {
            System.out.println("indtast review id, for den du vil slette: ");

            Scanner inputReader = new Scanner(System.in);
            int reviewSlet = inputReader.nextInt();

            String reviewSletId = String.valueOf(reviewSlet);

            ReviewService reviewService = new ReviewService();
            /**
             * Her kaldes på service metoden som laver forbindelse til databasen
             */
            reviewService.deleteReviewTeacher(reviewSletId, new ResponseCallback<Boolean>() {
                public void success(Boolean data) {
                    System.out.println("Reviewet er slettet.");
                    Controller controller = new Controller();
                    controller.showTeacherMenu(currentUserId);
                }

                public void error(int status) {

                }
            });

        }
    }

    /**
     * Denne meotde er til updatering af et review, der er en service metode til - ingen bliver brugt. 
     */
        public void update(){
            ReviewService reviewService = new ReviewService();
            Review reviewUpdate = new Review();
            //reviewUpdate.setComment("BOM");

            reviewService.update(reviewUpdate, new ResponseCallback<Review>() {
                public void success(Review data) {
                }

                public void error(int status) {
                }
            });
        }
}
