package View;

import Controller.LoginController;
import Controller.ProfileController.ProfileController;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
//This class is the view of user profile
public class UserProfileView {
    //the information of user
    public void InformationDemon() throws IOException, InterruptedException, ParseException {
        System.out.println("---------------------------------");
        System.out.println("        Information View         ");
        System.out.println("---------------------------------");

        LoginController loginController = new LoginController();
        loginController.UserInformation();

    }
    //update and show user information
    public void InformationUpdate(String Id, String Phone, String IsR, String IsH, String IsC, String UserName, ArrayList<String> userInfo) throws IOException, InterruptedException, ParseException {
        System.out.println("User Name: "+ UserName);
        System.out.println("------------------------");
        System.out.println("ID "+ Id);
        System.out.println("------------------------");
        System.out.println("Phone Number: "+Phone);
        System.out.println("------------------------");
        System.out.println("Receptionist: "+ IsR);
        System.out.println("------------------------");
        System.out.println("Health CareWorker: "+ IsH);
        System.out.println("------------------------");
        System.out.println("Customer: "+IsC);
        System.out.println("------------------------");
        System.out.println("Your booking Information, For the changed information the status will show Changed : ");
        for(int i = 0; i < userInfo.size(); i++){
            System.out.println(i + userInfo.get(i));
            System.out.println("------------------------");
        }
        nextStep();
    }
    //two options, one changing booking information, two back to service decide page
    public void nextStep() throws IOException, InterruptedException, ParseException {
        ProfileController profileController = new ProfileController();
        System.out.println("For changing booking information type 1\nFor leaving to main page type 2");
        profileController.nextStep();
    }
    //change booking information
    public void InformationChange() throws IOException, InterruptedException, ParseException {
        System.out.println("How would you like to select your Booking information?\n Type the number in the front or the booking id");
        ProfileController profileController = new ProfileController();
        profileController.changeRequest();

    }
    //if enter a invalid ID
    public void InvaildId() throws IOException, InterruptedException, ParseException {
        System.out.println("Invaild input or ID can not find");

    }
    //change test site location/date/time/change back
    public void ChangeSelection() throws IOException, InterruptedException, ParseException {
        System.out.println("Type 1 to change your location\nType 2 to change your date\nType 3 to change your time\nType 4 to Change Back");
        ProfileController profileController = new ProfileController();
        profileController.Selection();

    }
    //type wanted information of test site/date/time
    public void changeInformationIn() throws IOException, InterruptedException, ParseException {
        ProfileController profileController = new ProfileController();
        System.out.println("Type your wanted information in(Your wantted testing site name or suburb, \nYour wanted date in MM-DD formate \nYour wantted time in HH:MM format)\nIf you change back just skip");
        profileController.ChangeInfoPass();
    }
    //if type an invalid testing site
    public void InvaildTestingInfo() throws IOException, InterruptedException, ParseException {
        System.out.println("There is no such a TestingSite exist");
        InformationDemon();
    }
    //change booking info successfully
    public void SuccessfulChange() throws IOException, InterruptedException, ParseException {
        System.out.println("You have change your bookingInformation");
        LoginController loginController =new LoginController();
        loginController .UserInformationController();
        InformationDemon();
    }
    //select a type that changed before
    public void RequestType() throws IOException, InterruptedException, ParseException {
        ProfileController profileController = new ProfileController();
        System.out.println("What type you have changed before?\nType 1 is location\nType 2 is date or time");
        profileController.SelectChangeBack();
    }
    //select a booking info without changing
    public void InvaildSelectChangeBackInfo() throws IOException, InterruptedException, ParseException {
        System.out.println("There is no change in this booking");
        InformationDemon();
        LoginController loginController =new LoginController();
        loginController .UserInformationController();
        InformationDemon();
    }
    //change to back successfully
    public void ChangeBackSuccess() throws IOException, InterruptedException, ParseException {
        System.out.println("You have successful change back");
        LoginController loginController =new LoginController();
        loginController .UserInformationController();
        InformationDemon();
    }
    //if enter a incorrect date
    public void IncorrectDate() throws IOException, InterruptedException, ParseException {
        System.out.println("The date is not correct");

        LoginController loginController =new LoginController();
        loginController .UserInformationController();
        InformationDemon();
    }
}
