package Controller.TestingController;

import Model.TestingModel.HomeTestingModel;
import View.HealthCareView;
import View.HomeBookingView;
import View.ServiceDecideView;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
//this class is the controller of home testing
public class HomeTestingController {
    //declare and initialize variables
    private static String familyName;
    private static String GivenName;
    Scanner scan = new Scanner(System.in);
    //ask the user has tool for home testing or not
    public void ToolAsk() throws IOException, InterruptedException, ParseException {
        HomeTestingModel hometestingModel = new HomeTestingModel();
        HomeBookingView homeBookingView = new HomeBookingView();
        String in = scan.nextLine();
        hometestingModel.homeTesting(in);
        homeBookingView.TestingInit();

    }
    //set family name
    public void TestingFamilyName() throws IOException, InterruptedException, ParseException {
        String in = scan.nextLine().toLowerCase();
        familyName = "\""+in +"\"";
        HomeBookingView homeBookingView = new HomeBookingView();
        homeBookingView.GivenName();

    }
    //set given name
    public void GiveName() throws IOException, InterruptedException, ParseException {
        String in = scan.nextLine().toLowerCase();
        GivenName = "\""+in +"\"";
        HomeTestingModel hometestingModel = new HomeTestingModel();
        hometestingModel.Checking(familyName,GivenName);
    }
    //failed booking, not booking yet
    public void Failbooking() throws IOException, InterruptedException, ParseException {

        HomeBookingView healthCareView =new HomeBookingView();
        healthCareView.bookchecking();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();
    }
    //failed ID, not registered
    public void FailId() throws IOException, InterruptedException, ParseException {
        HomeBookingView healthCareView =new HomeBookingView();
        healthCareView.FailIdchecking();

        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();
    }
    //start testing

    //failed testing without booking
    public void TestingFail() throws IOException, InterruptedException, ParseException {
        HomeBookingView healthCareView =new HomeBookingView();
        healthCareView.testingFail();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();
    }
    //successful completed the testing
    public void TestingSuccess(String in) throws IOException, InterruptedException, ParseException {
        HomeBookingView healthCareView =new HomeBookingView();
        healthCareView.testingSuccess(in);
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();

    }
    //back to service decide page
    public void BacktoService() throws InterruptedException, ParseException, IOException {
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();
    }






}
