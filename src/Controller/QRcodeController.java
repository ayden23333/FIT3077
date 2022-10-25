package Controller;

import Model.TestingModel.QRcodeScanModel;
import View.QRcodeScanView;
import View.ServiceDecideView;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
//This is the controller of QR code
public class QRcodeController {
    Scanner scan = new Scanner(System.in);
    //scan qr code
    public void QRcodeTakein() throws IOException, InterruptedException, ParseException {
        String  QRCode= scan.nextLine();
        QRcodeScanModel qRcodeScanModel = new QRcodeScanModel();
        qRcodeScanModel.QR(QRCode);



    }
    //update failed, can not detect a booking
    public void UpdateFail() throws IOException, InterruptedException, ParseException {
        QRcodeScanView qRcodeScanView = new QRcodeScanView();
        qRcodeScanView.Fail();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();
    }
    //scan QR code successfully
    public void SuccressScan(){
        QRcodeScanView qRcodeScanView = new QRcodeScanView();
        qRcodeScanView.Success();
    }
    //update and go to next step: keep scan or exit
    public void updateSuccess(){
        QRcodeScanView qRcodeScanView = new QRcodeScanView();
        qRcodeScanView.UpdateSuccess();
        qRcodeScanView.nextStep();


    }
    //update failed, go to the next step: keep scan or exit
    public void updateFail(){
        QRcodeScanView qRcodeScanView = new QRcodeScanView();
        qRcodeScanView.UpdateFail();
        qRcodeScanView.nextStep();


    }
    //Two options, keep scan or exit to service decide page
    public void nextStep() throws IOException, InterruptedException, ParseException {
        String  choose= scan.nextLine();
        QRcodeScanView qRcodeScanView = new QRcodeScanView();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        //reload the method to keep scanning
        if(choose.equals("1")){
            qRcodeScanView.QR();

        }
        //back to the service decide page
        else if(choose.equals("1")){
            serviceDecideView.ServiceDecide();


        }
        //Invaild input process method
        else{
            qRcodeScanView.error();
            serviceDecideView.ServiceDecide();

        }
    }

}
