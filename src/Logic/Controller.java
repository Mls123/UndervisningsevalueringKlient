package Logic;

import View.*;
import sdk.Models.Lecture;

public class Controller {

    public void showStudentMenu(int currentUserId){
        StudentView studentView = new StudentView();
        studentView.studentMenu(currentUserId);
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
