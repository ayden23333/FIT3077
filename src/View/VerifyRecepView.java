package View;

import Controller.VerifyController.VerifyController;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
//This class is the view of checking the user is a receptionist
public class VerifyRecepView {
    Scanner scan = new Scanner(System.in);
    public void verifyInt() throws IOException, InterruptedException, ParseException {
        System.out.println("|-------------------------------------------------------|");
        System.out.println(" This function is only for the Receptionist using");
        System.out.println(" Are you a Receptionist? Type 1 for Yes, Type 2 for No");
        System.out.println(" Receptionist allows to check the booking information and change booking Information for other people ");
        System.out.println("|-------------------------------------------------------|");
        //Verify that the user is a receptionist
        VerifyController verifyController = new VerifyController();
        verifyController.IsReInit();
    }

    //user is a receptionist
    public void SuccessVerifyResult(String Result){
        System.out.println("Your identity is "+ Result +" Now you will jump to the Booking information checking or booking page");

    }
    //user is not a receptionist
    public void FailureVerifyResult(String Result){
        System.out.println("Your identity is "+ Result +" Sorry you are not a receptionist, now it will jump to booking page");
    }
}
