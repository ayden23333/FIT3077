package Controller.ServiceDecideController;

import Controller.ProfileController.AdminController;
import View.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
//This class is the controller about service decide by implementing the service decide interface
public class ServiceDecideContorller implements ServiceDecideInt {
    Scanner scan = new Scanner(System.in);
    @Override
    //implement method in service decide interface and override
    public void serviceDecide() throws IOException, InterruptedException, ParseException {
        String in = scan.nextLine();
        //1. jump to searching testing site
        if(in.equals("1")){

            //Jump to the search the testing site page
            TestingSiteView testingSiteView= new TestingSiteView();
            testingSiteView.TestingSiteInit();

        }
        //2. as a receptionist, jump to booking or change/check booking information
        //as a customer, jump to booking
        else if(in.equals("2")){
            VerifyRecepView verifyRecepView = new VerifyRecepView();
            verifyRecepView.verifyInt();

        }
        //3. as a healthcare worker, jump to check patients information
        else if(in.equals("3")){
            VerifyHealthWorkerView verifyHealthWorkerView = new VerifyHealthWorkerView();
            verifyHealthWorkerView.VerifyHealth();

        }
        //4. jump to QR code scan
        else if(in.equals("4")){
           QRcodeScanView qRcodeScanView = new QRcodeScanView();
           qRcodeScanView.QR();

        }
        //5. jump to home booking
        else if(in.equals("5")){
            HomeBookingView homeBookingView = new HomeBookingView();
            homeBookingView.homeBookingInit();

        }
        //6. exit service decide
        else if(in.equals("6")){
           LoginView loginView = new LoginView();
           loginView.Login();

        }
        //7. jump to user profile
        else if(in.equals("7")){
            UserProfileView  userProfileView = new UserProfileView();
            userProfileView.InformationDemon();
        }
        //8. as a receptionist, jump to checking booking information
        else if(in.equals("8")){
            VerifyRecepView verifyRecepView = new VerifyRecepView();
            verifyRecepView.verifyInt();
        }
        //9. jump to admin surface
        else if(in.equals("9")){
           AdminSurface adminSurface = new AdminSurface();
           adminSurface.VerifyInit();
        }
        else{
            serviceDecide();}

    }
}

