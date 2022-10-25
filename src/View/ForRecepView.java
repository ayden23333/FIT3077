package View;

import Controller.BookingController.RecepController;


import java.io.IOException;
import java.text.ParseException;
//This class is the view for receptionist
public class ForRecepView {
    //receptionist booking initial page
    public void bookingForRecepInit() throws IOException, InterruptedException, ParseException {
        RecepController recepController = new RecepController();
        System.out.println("1 for Booking, 2 for booking information checking, 3 for change customer's booking Information");
        recepController.ServiceSelection();
    }
    //enter pin code
    public void getPinCode() throws IOException, InterruptedException, ParseException {
        System.out.println("Please type your pin code");
        RecepController recepController = new RecepController();
        recepController.GetPin();

    }
    //present information of booking
    public void BookingInforDisplay(String result ){
        System.out.println("|-------------------------------------|");
        System.out.println(result);
        System.out.println("|-------------------------------------|");
    }
    //continue to search by pin code or exit to service decide page
    public void nextStep() throws IOException, InterruptedException, ParseException {
        RecepController recepController = new RecepController();
        System.out.println("Do you want to continue for search? Type 1 for yes, Type 2 for return to the Service decide page");
        recepController.nextStep();
    }
    //change booking information
    public void bookingChange() throws IOException, InterruptedException, ParseException {
        System.out.println("Please type in your pinCode or ID");
        RecepController recepController = new RecepController();
        recepController.BookingInfoChangeInit();
    }
    //if enter with a invalid pin code or ID
    public void InvaildPinOrId(){
        System.out.println("There is no information detected");
    }
    //if enter something wrong, it is a invalid value
    public void InvalidMethod() throws InterruptedException, ParseException, IOException {

        System.out.println("Invaild method");
        bookingForRecepInit();
    }
    //when the testing has done before of the booking
    public void testDoneBefore(){
        System.out.println("The booking has done the test before");

    }
    //change testing site or date or time or change to a previous one
    public void ChangeSelection() {
        RecepController recepController = new RecepController();
        System.out.println("Type 1 to change your location\nType 2 to change your date\nType 3 to change your time\nType 4 to Change Back");
        recepController.changeTypePass();
        System.out.println("Type your wanted information in(Your wanted testing site name or suburb, \nYour wanted date in MM-DD formate \nYour wantted time in HH:MM format)\nIf you change back just skip");
        recepController.ChangeInforPass();

    }
    //change successfully
    public void Success(){
        System.out.println("You've help the customer change the information");
    }
    //there is no exist testing site
    public void InvalidInformation(){
        System.out.println("Invalid testing site information");
    }
    //change failed and back to service decide page
    public void Fail(){
        System.out.println("Change failed");

    }
    //change one type which has been changed before: testing site location or date or time
    public void RequestType() throws IOException, InterruptedException, ParseException {
        RecepController recepController = new RecepController();
        System.out.println("What type you have changed before?\nType 1 is location\nType 2 is date or time");
        recepController.RequestType();

    }
    //if enter a incorrect/invalid date
    public void IncorrectDate(){
        System.out.println("The date is not correct");

    }
}
