package Controller.VerifyController;

import Controller.LoginController;
import Model.VerifyModel.VerifyModel;
import Model.VerifyModel.VerifyModelInt;
import View.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
//This class is the controller of verify identity
public class VerifyController {
    Scanner scan = new Scanner(System.in);
    public void IsReInit() throws IOException, InterruptedException, ParseException {
        String verify = scan.nextLine();
        BookingView bookingView = new BookingView();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        //verify user is a receptionist or not
        if(verify.equals("1")
        ){

            VerifyModelInt isREmodel = new VerifyModel();
            isREmodel.Verify();
            updateIsRaceView();
        }
        //no, jump to booking page
        else if(verify.equals("2")){

            bookingView.BookingInit();
        }
        //select anything else, back to service decide page
        else{
            serviceDecideView.ServiceDecide();
        }
    }
    public void updateIsRaceView() throws IOException, InterruptedException, ParseException {

        VerifyModel isREmodel = new VerifyModel();
        isREmodel.getIsRece();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        VerifyRecepView verifyRecepView = new VerifyRecepView();
        //if user is a receptionist, jump to booking for receptionist initial page
        if (isREmodel.getIsRece().equals("true")) {
            verifyRecepView.SuccessVerifyResult(isREmodel.getIsRece());
            ForRecepView forRecepView = new ForRecepView();
            forRecepView.bookingForRecepInit();
        }
        //if user is not, back to service decide page
        else {
            verifyRecepView.FailureVerifyResult(isREmodel.getIsRece());
            serviceDecideView.ServiceDecide();
        }
    }
    public void isHealthVerify() throws IOException, InterruptedException, ParseException {
        String verify = scan.nextLine();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        //verify user is a healthcare worker or not
        //yes
        if(verify.equals("1")
        ){

            VerifyModelInt isModel = new VerifyModel();
            isModel.Verify();
            updateIsHealthCareView();
        }
        //no, back to service decide page
        else if(verify.equals("2")){

            serviceDecideView.ServiceDecide();
        }
        //select anything else, back to service decide page
        else{
            serviceDecideView.ServiceDecide();
        }


    }
    public void updateIsHealthCareView() throws IOException, InterruptedException, ParseException {
        VerifyModel isModel = new VerifyModel();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        HealthCareView healthCareView =  new HealthCareView();
        VerifyHealthWorkerView verifyHealthWorkerView = new VerifyHealthWorkerView();
        //if user is a healthcare worker, jump to testing initial page
        if (isModel.getIsHealth().equals("true")) {
            verifyHealthWorkerView.SuccessVerify();
            healthCareView.TestingInit();
        }
        // if user is not, back to service decide page
        else {
            verifyHealthWorkerView.FailVerify();
            serviceDecideView.ServiceDecide();
        }
    }
}

