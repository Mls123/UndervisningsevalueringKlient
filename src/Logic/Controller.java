package Logic;

import View.*;
import View.Menuer.StudentMenu;
import View.Menuer.TeacherMenu;

public class Controller {

    /**
     * Her administreres de forskellige view eller menuer.
     * @param currentUserId
     */
    public void showStudentMenu(int currentUserId){
        StudentMenu studentView = new StudentMenu();
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
