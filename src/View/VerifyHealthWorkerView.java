package View;

import Controller.VerifyController.VerifyController;

import java.io.IOException;
import java.text.ParseException;
//This class is the view of checking the user is a healthcare worker
public class VerifyHealthWorkerView {
    public void VerifyHealth() throws IOException, InterruptedException, ParseException {
        System.out.println("|-------------------------------------------------------|");
        System.out.println(" This function is only for the Healthcare worker using");
        System.out.println(" Are you a Healthcare worker? Type 1 for Yes, Type 2 for No");
        System.out.println("|-------------------------------------------------------|");
        VerifyController verifyController = new VerifyController();
        verifyController.isHealthVerify();

    }
    //user is a healthcare worker
    public void SuccessVerify(){
        System.out.println("Now you are in the patient information form");
    }
    //user is not a healthcare worker
    public void FailVerify(){
        System.out.println("Sorry you are not a healthcare worker, now it will return the service page");
    }
}
