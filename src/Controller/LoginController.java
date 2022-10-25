package Controller;
import Model.Entity.Users;
import Model.UserLoginModel;
import View.LoginView;
import View.ServiceDecideView;
import View.UserProfileView;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
//This class is the controller of login
public class LoginController {
    //declare and initialize variables
    UserLoginModel userLoginModel = new UserLoginModel();
    LoginView loginView= new LoginView();
    private static String username;
    private static String password;
    //get user name
    public static String getUsername() {
        return username;
    }
    Scanner scan = new Scanner(System.in);

    public void Login() throws IOException, InterruptedException, ParseException {
        String in = scan.nextLine();
        //1. jump to login
        if(in.equals("1")){
           loginView.information();
        }
        //2. jump to register
        else if(in.equals("2")){
            loginView.Register();

        }
        //anything else, jump to login
        else{
            loginView.Login();
        }
    }
    //enter user name
    public void enter_userName(String username){
        this.username = username;
        userLoginModel.setUsername(username);//Set the username
    }
    //enter password
    public void enter_password(String password) throws IOException, InterruptedException, ParseException {
        this.password  = password;
        JsonStringFormat();

    }
    //login verify
    public void JsonStringFormat() throws IOException, InterruptedException, ParseException {
        String jsonString;
        jsonString = "{" +
                "\"userName\":\"" + username + "\"," +
                "\"password\":\"" + password + "\"" +
                "}";
        userLoginModel.LoginVerify(jsonString);
    }
    public void UpdateLoginView() throws IOException, InterruptedException, ParseException {
        loginView.errorLogin();
        LoginView loginView=new LoginView();
        loginView.Login();

    }
    //successfully login, jump to service decide page
    public void SuccessLoginView() throws IOException, InterruptedException, ParseException {
        ServiceDecideView serviceDecideview = new ServiceDecideView();
        loginView.LoginSuccess();
        UserInformationController();
        serviceDecideview.ServiceDecide();

    }
    //identify is the user registered or not
    public void UserInformationController() throws IOException, InterruptedException {

        String name = "\""+username + "\"";
        userLoginModel.UserInformation(name);
    }
    //get user information
    public void UserInformation() throws IOException, InterruptedException, ParseException {
        Users users = new Users();
        UserProfileView userProfileView = new UserProfileView();
        userProfileView.InformationUpdate(users.getId(), users.getPhoneNumber(),users.getIsRecep(),users.getIsHealth(),users.getIsCustomer(),users.getName(),users.getUserInfo());

    }
}
