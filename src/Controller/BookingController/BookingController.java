package Controller.BookingController;

import Model.BookingModel.BookingInt;
import Model.BookingModel.BookingModel;
import View.BookingView;
import View.ServiceDecideView;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

//This class is the controller of booking
public class BookingController {
    Scanner scan = new Scanner(System.in);


    //booking information transfer to booking model
    public void BookingInformation() throws IOException, InterruptedException, ParseException {
        BookingModel bookingModel = new BookingModel();
        String in = scan.nextLine();
        String run = "\"" +in+ "\"";
        bookingModel.setUsername(run);

        bookingModel.NameChecking();

    }
    //checking the booking result and update
    public void updateCheckingResult(String result) throws IOException, InterruptedException, ParseException {
        BookingView bookingView = new BookingView();

        ServiceDecideView serviceDecideView = new ServiceDecideView();
        if(result.equals(" You haven't registered yet "))
        {
            bookingView.NameCheckingResult(result);
            serviceDecideView.ServiceDecide();
        }
        else{
            bookingView.WantTestingSite();
        }
    }
    //get testing site name
    public void GetSiteName() throws IOException, InterruptedException, ParseException {
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        BookingView bookingView = new BookingView();
        BookingModel bookingModel = new BookingModel();
        String in = scan.nextLine();
        bookingModel.SiteVerify(in);
        bookingView.SiteResult(bookingModel.getSiteResult());
        if(bookingModel.getSiteResult().equals(" There is no such a facility exit"))
           serviceDecideView.ServiceDecide();
        else{
            bookingView.bookingNotes();
            String notes = scan.nextLine();
            if(notes.length()==0)
                notes = " ";
            bookingModel.setNotes(notes);
            bookingView.MonthWant();
        }

    }
    //set date
    //set the month
    public void Month() throws IOException, InterruptedException, ParseException {
        BookingView bookingView = new BookingView();
        BookingModel bookingModel = new BookingModel();
        String month = scan.nextLine();
        bookingModel.setMonth(month);
        bookingView.DayWant();


    }
    //set the day
    public void DateWant() throws IOException, InterruptedException, ParseException {
        BookingModel bookingModel = new BookingModel();
        String date = scan.nextLine();
        bookingModel.setDay(date);
        booking();
    }
    public void booking() throws IOException, InterruptedException, ParseException {
        BookingInt bookingModel = new BookingModel();
        bookingModel.booking();

    }
    //result of booking successful
    public void Sccuess() throws IOException, InterruptedException, ParseException {
        BookingModel bookingModel = new BookingModel();
        BookingView bookingView = new BookingView();
        bookingView.booking(bookingModel.getBooking());
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();

    }
    //info of booking is invalid
    public void Fail() throws IOException, InterruptedException, ParseException {
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        BookingView bookingView = new BookingView();
        bookingView.Fail();
        serviceDecideView.ServiceDecide();
    }

}