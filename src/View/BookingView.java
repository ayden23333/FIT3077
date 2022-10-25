package View;

import Controller.BookingController.BookingController;

import java.io.IOException;
import java.text.ParseException;
//This class is about the view of booking page
public class BookingView {
    //booking page
    public void BookingInit() throws IOException, InterruptedException, ParseException {
        BookingController bookingController = new BookingController();
        System.out.println("Now you are in the booking page, Please provide your Username ");

        bookingController.BookingInformation();

    }
    //checking name view
    public void NameCheckingResult(String result){
        System.out.println("|-------------------------------------|");
        System.out.println(result);
        System.out.println("|-------------------------------------|");
    }
    //enter a testing site view
    public void WantTestingSite() throws IOException, InterruptedException, ParseException {
        BookingController bookingController = new BookingController();
        System.out.println(" Please provide your wanted test site's name ");
        bookingController.GetSiteName();
    }
    //print the result of testing site
    public void SiteResult(String result){
        System.out.println(result);

    }
    //enter booking month view
    public void MonthWant() throws IOException, InterruptedException, ParseException {
        BookingController bookingController = new BookingController();
        System.out.println("Which month are you going to do the test, \nYou must type the correct format example:02 "
        );
        bookingController.Month();

    }
    //enter booking day view
    public void DayWant() throws IOException, InterruptedException, ParseException {
        System.out.println("Which day are you going to do the test"
        );
        BookingController bookingController = new BookingController();
        bookingController.DateWant();
    }

    //enter booking notes view
    public void bookingNotes(){
        System.out.println("Do you have any notes? if not, please skip");

    }
    //present the result of booking successful
    public void booking(String response){
        System.out.println("You have booked successfully");
        System.out.println("Your booking information is here: "+ response);

    }
    //present the info of booking is invalid
    public void Fail(){
        System.out.println("Your booking information is Invalid");


    }
}
