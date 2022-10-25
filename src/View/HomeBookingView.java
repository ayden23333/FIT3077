package View;

import Controller.TestingController.HomeTestingController;


import java.io.IOException;
import java.text.ParseException;
//This class is about the view of home booking and home testing page
public class HomeBookingView {
    //the home booking initial view page
    public void homeBookingInit() throws IOException, InterruptedException, ParseException {
        System.out.println("Do you have test tools? type 1 for yes, type 2 for no, if you have tool, you don't need to go to the site with QR code");
        HomeTestingController testingController = new HomeTestingController();
        testingController.ToolAsk();


    }
    //the home testing initial view page
    public void TestingInit() throws IOException, InterruptedException, ParseException {

        HomeTestingController testingController = new HomeTestingController();
        System.out.println("FamilyName:");
        testingController.TestingFamilyName();

    }
    //set given name
    public void GivenName() throws IOException, InterruptedException, ParseException {
        HomeTestingController testingController = new HomeTestingController();
        System.out.println("GivenName:");
        testingController.GiveName();

    }
    //the view of checking if booked
    public void bookchecking(){
        System.out.println("|-------------------------------------|");
        System.out.println(" You haven't booked yet ");
        System.out.println("|-------------------------------------|");
    }
    //the view of checking if registered
    public void FailIdchecking(){
        System.out.println("|-------------------------------------|");
        System.out.println(" You haven't registered yet ");
        System.out.println("|-------------------------------------|");

    }
    //the view of starting testing

    //failed testing by can not find ID
    public void testingFail(){


        System.out.println("A patient, administerer, and/or booking with the provided ID was not found.");
        System.out.println("Now will return to the service decide page");

    }
    //finish testing view page
    public void testingSuccess(String result){
        System.out.println("Here is your testing information: "+result);
        System.out.println("You have successfully conduct the testing, Now will return to the service decide page");
    }


}


