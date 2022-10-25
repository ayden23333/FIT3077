package Controller.BookingController;
import Model.BookingModel.BookingInforModel;
import Model.BookingModel.BookingInt;
import Model.BookingModel.BookingModel;
import Model.BookingModel.RecepBookingChangeModel;
import View.BookingView;
import View.ForRecepView;
import View.ServiceDecideView;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
// This class is the controller of receptionist
public class RecepController {
    Scanner scan = new Scanner(System.in);
    //select service for receptionist
    public void ServiceSelection() throws IOException, InterruptedException, ParseException {

        ForRecepView ForRecepview = new ForRecepView();
        BookingView bookingView = new BookingView();
        String in = scan.nextLine();
        //select 1. jump to booking
        if(in.equals("1")){
            bookingView.BookingInit();
        }
        //select 2. enter pin code to check the booking information
        else if(in.equals("2")){
            ForRecepview.getPinCode();

        }
        //select 3. change booking information
        else if(in.equals("3")){
            ForRecepview.bookingChange();

        }
        //back to receptionist booking initial page
        else
            ForRecepview.bookingForRecepInit();

    }
    //get pin code
    public void GetPin() throws IOException, InterruptedException, ParseException {
        String in = scan.nextLine();
        BookingInforModel bookingInforModel = new BookingInforModel();
        BookingInt bookingModel = new BookingInforModel();
        bookingInforModel.setPinCode(in);
        bookingModel.booking();


    }

    public void updateView() throws IOException, InterruptedException, ParseException {
        BookingInforModel bookingInforModel = new BookingInforModel();
        ForRecepView forRecepView = new ForRecepView();

        forRecepView.BookingInforDisplay(bookingInforModel.getResult());
        forRecepView.nextStep();

    }
    //two options for next step
    public void nextStep() throws IOException, InterruptedException, ParseException {
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        ForRecepView forRecepView = new ForRecepView();
        String in = scan.nextLine();
        //select 1. continue to get pin code
        if(in.equals("1")){
            forRecepView.getPinCode();
        }
        //select 2. back to the service decide page
        else if(in.equals("2")){
            serviceDecideView.ServiceDecide();

        }
        else
            serviceDecideView.ServiceDecide();
    }
    //change booking information page
    public void BookingInfoChangeInit() throws IOException, InterruptedException, ParseException {
        String in = scan.nextLine();
        RecepBookingChangeModel recepBookingChangeModel = new RecepBookingChangeModel();
        recepBookingChangeModel.setChangePinOrID(in);
        recepBookingChangeModel.ChangeInit();
    }

    //enter a invalid pin code or id
    public void InvaildPinOrId() throws InterruptedException, ParseException, IOException {
        ForRecepView forRecepView = new ForRecepView();
        forRecepView.InvaildPinOrId();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();
    }
    //testing has been completed before
    public void testDoneBefore() throws InterruptedException, ParseException, IOException {

        ForRecepView forRecepView = new ForRecepView();
        forRecepView.testDoneBefore();



    }
    //set change test site or date or time
    public void ChangeAsk() throws InterruptedException, ParseException, IOException {
        ForRecepView forRecepView = new ForRecepView();
        forRecepView.ChangeSelection();
    }
    //set change information type
    public void changeTypePass(){
        String in = scan.nextLine();RecepBookingChangeModel recepBookingChangeModel = new RecepBookingChangeModel();
        recepBookingChangeModel.setChangeType(in);
    }
    //set change information of booking
    public void ChangeInforPass(){
        String in = scan.nextLine();
        RecepBookingChangeModel recepBookingChangeModel = new RecepBookingChangeModel();
        recepBookingChangeModel.setChangeInfo(in);

    }
    //change successfully
    public void Success() throws InterruptedException, ParseException, IOException {
        ForRecepView forRecepView = new ForRecepView();
        forRecepView.Success();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();
    }
    //change a invalid information
    public void InvalidInformation(){
        ForRecepView forRecepView = new ForRecepView();
        forRecepView.InvalidInformation();
    }
    //change is failed
    public void Fail() throws InterruptedException, ParseException, IOException {
        ForRecepView forRecepView = new ForRecepView();
        forRecepView.Fail();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();

    }
    //choose what type you have changed before
    public void RequestType() throws IOException, InterruptedException, ParseException {
        String in = scan.nextLine();
        RecepBookingChangeModel recepBookingChangeModel = new RecepBookingChangeModel();
        recepBookingChangeModel.changeBack(in);

    }
    //ask which request type dose user want
    public void ask() throws InterruptedException, ParseException, IOException {
        ForRecepView forRecepView = new ForRecepView();
        forRecepView.RequestType();
    }
    //enter a invalid value
    public void InvaildMethod() throws InterruptedException, ParseException, IOException {
        ForRecepView forRecepView = new ForRecepView();
        forRecepView.InvalidMethod();
    }
    //enter a incorrect time
    public void IncorrectTime() throws InterruptedException, ParseException, IOException {
        ForRecepView forRecepView = new ForRecepView();
        forRecepView.IncorrectDate();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();
    }
}
