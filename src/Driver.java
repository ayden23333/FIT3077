
import View.LoginView;

import java.io.IOException;
import java.text.ParseException;


public class Driver {
    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        LoginView loginView = new LoginView();
        //This function will be derived and call the function selection first. Then it will goes to the service decide page
        loginView.Login();
}
}
