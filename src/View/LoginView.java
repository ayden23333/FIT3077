package View;

import Controller.LoginController;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
//This class is the login view page
public class LoginView {
    Scanner scan = new Scanner(System.in);
    public void Login() throws IOException, InterruptedException, ParseException {
        LoginController loginController = new LoginController();
            //Provides the options of login(1) or register(2)
            System.out.println("You are now in the Login surface");
            System.out.println("---------------------------------");
            System.out.println("For login, type 1.\nFor register, type 2.");
            System.out.println("---------------------------------");
            loginController.Login();

    }
    //Failed login
    public void errorLogin(){
        System.out.println("Invalid username or password, you will be back to the return page");
    }
    //successful login
    public void LoginSuccess(){
        System.out.println("---------------------------------");
        System.out.println("         Login success           ");
        System.out.println("---------------------------------");
    }
    //enter login user name and password page
    public void information() throws IOException, InterruptedException, ParseException {
        LoginController loginController = new LoginController();
        System.out.println("Please enter your username: ");
        String username = scan.nextLine();
        loginController.enter_userName(username);
        System.out.println("Please enter your password: ");
        String password = scan.nextLine();
        loginController.enter_password(password);
    }
    //register page
    public void Register() throws InterruptedException, ParseException, IOException {
        System.out.println("This function is not developed yet");
        Login();
    }
}
