package Controller.ProfileController;

import Model.BookingModel.BookingChangeModel;

import Model.BookingModel.BookingInt;
import View.ServiceDecideView;
import View.UserProfileView;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
// this class is the controller of user profile
public class ProfileController {
    Scanner scan = new Scanner(System.in);

    public void nextStep() throws IOException, InterruptedException, ParseException {
        UserProfileView userProfileView = new UserProfileView();
        ServiceDecideView  serviceDecideView = new ServiceDecideView();

        String in = scan.nextLine();
        //select 1. jump to change booking information
        if(in.equals("1")){
          userProfileView.InformationChange();

        }
        //select 2. back to the service decide
        else if(in.equals("2")){
            serviceDecideView.ServiceDecide();

        }
        //select anything else, jump to user profile page
        else{
            userProfileView.InformationDemon();
        }
    }
    //change test site location/date/time/change back
    public void changeRequest() throws IOException, InterruptedException, ParseException {
        BookingChangeModel bookingChangeModel = new BookingChangeModel();
        UserProfileView userProfileView = new UserProfileView();
        String in = scan.nextLine();
        bookingChangeModel.setDecison(in);
        bookingChangeModel.bookingInit();
        userProfileView.ChangeSelection();
    }
    //if enter a invalid ID
    public void InvaildInfo() throws IOException, InterruptedException, ParseException {
        UserProfileView userProfileView = new UserProfileView();
        userProfileView.InvaildId();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();

    }
    //type wanted information of test site/date/time
    public void Selection() throws IOException, InterruptedException, ParseException {
        String in = scan.nextLine();
        BookingChangeModel bookingChangeModel = new BookingChangeModel();
        UserProfileView userProfileView = new UserProfileView();
        bookingChangeModel.setChangeType(in);
        userProfileView.changeInformationIn();
    }
    //booking
    public void ChangeInfoPass() throws IOException, InterruptedException, ParseException {
        String in = scan.nextLine();
        BookingChangeModel bookingChangeModel = new BookingChangeModel();
        bookingChangeModel.setChangeInfo(in);
        BookingInt booking = new BookingChangeModel();
        booking.booking();

    }
    //type an invalid testing site
    public void InvaildtestingInfo() throws IOException, InterruptedException, ParseException {
        UserProfileView userProfileView = new UserProfileView();
        userProfileView.InvaildTestingInfo();
    }
    //change booking info successfully
    public void SuccessfulChange() throws IOException, InterruptedException, ParseException {
        UserProfileView userProfileView = new UserProfileView();
        userProfileView.SuccessfulChange();

    }
    //select a type that changed before
    public void requestTypeAsk() throws IOException, InterruptedException, ParseException {
        UserProfileView userProfileView = new UserProfileView();
        userProfileView.RequestType();
    }
    //change back to previous one
    public void SelectChangeBack() throws IOException, InterruptedException, ParseException {
        String in = scan.nextLine();
        BookingChangeModel bookingChangeModel = new BookingChangeModel();
        bookingChangeModel.changeBack(in);

    }
    //back failed, a booking info without any changes
    public void FailBack() throws IOException, InterruptedException, ParseException {
        UserProfileView userProfileView = new UserProfileView();
        userProfileView.InvaildSelectChangeBackInfo();
    }
    //back successfully
    public void SuccessBack() throws IOException, InterruptedException, ParseException {
        UserProfileView userProfileView = new UserProfileView();
        userProfileView.ChangeBackSuccess();

    }
    //enter an incorrect date
    public void incorrectDate() throws IOException, InterruptedException, ParseException {
        UserProfileView userProfileView = new UserProfileView();
        userProfileView.IncorrectDate();
    }

}
