package Logic;

import View.*;
import sdk.Models.Lecture;

public class Controller {

    public void showStudentMenu(int currentUserId){
        StudentView studentView = new StudentView();
        int lectureId = 0;
        studentView.studentMenu(currentUserId, lectureId);
    }
    public void showTeacherMenu(int currentUserId){
        TeacherView teacherView = new TeacherView();
        teacherView.teacherMenu(currentUserId);
    }
    public void showLoginView(){

        LoginView loginView = new LoginView();
        loginView.presentLogin();
    }

    public LoginView getLoginView()
    {
        LoginView loginView = new LoginView();
        return loginView;
    }

    public void showKursusView(){

    }

    public void showRatingView(){

    }

    public void showOmView(){

    }

}
