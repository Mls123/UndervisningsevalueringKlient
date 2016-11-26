package Logic;

import View.*;
import View.Menuer.StudentMenu;
import View.Menuer.TeacherMenu;

public class Controller {

    public void showStudentMenu(int currentUserId){
        StudentMenu studentView = new StudentMenu();
        int currentLectureId = 0;
        studentView.studentMenu(currentUserId);
    }
    public void showTeacherMenu(int currentUserId){
        TeacherMenu teacherView = new TeacherMenu();
        teacherView.teacherMenu(currentUserId);
    }
    public void showLoginView(){

        LoginView loginView = new LoginView();
        loginView.presentLogin();
    }

}
