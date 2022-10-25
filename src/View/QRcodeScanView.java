package View;

import Controller.QRcodeController;

import java.io.IOException;
import java.text.ParseException;
//This class is the view of QR code scan
public class QRcodeScanView {
    public void QR() throws IOException, InterruptedException, ParseException {
        //simulate the QR code scan information
        System.out.println("Please put your QR code here(type in)");
        QRcodeController qRcodeController = new QRcodeController();
        qRcodeController.QRcodeTakein();
    }
    //scan Failed page. There is no booking information
    public void Fail(){
        System.out.println("|-------------------------------------|");
        System.out.println(" No booking information detected");
        System.out.println("|-------------------------------------|");

    }
    //scan Successful page. There is a booking
    public void Success(){
        System.out.println("Scan success");
        System.out.println("You've received the tool, the URL now will send to your phone as SMS or Email");
    }
    //updating successfully page
    public void UpdateSuccess(){
        System.out.println("Updated");
    }
    //updating failed page
    public void UpdateFail(){
        System.out.println("There is a issue of renew the system");
    }
    //keeping scan page
    public void nextStep(){
        System.out.println("Keep scan type 1\n Exit type 2");
    }
    //prompt error page
    public void error(){
        System.out.println("Invaild Method");
    }
}
