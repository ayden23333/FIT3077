package Controller.ProfileController;

import Model.BookingModel.AdminModel;
import Model.BookingModel.BookingInt;
import View.AdminSurface;
import View.ServiceDecideView;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
//This class is the controller for admin
public class AdminController {
    Scanner scan = new Scanner(System.in);
    //verify is an admin or not
    public void verify() throws IOException, InterruptedException, ParseException {
        AdminModel adminModel = new AdminModel();
        adminModel.verify();
    }
    public void initial() throws IOException, InterruptedException, ParseException {
        AdminModel adminModel = new AdminModel();
        adminModel.Initial();

    }
    //verify the identity successfully(is admin)
    public void verifySuccess() throws IOException, InterruptedException, ParseException {
        AdminSurface adminSurface = new AdminSurface();
        adminSurface.Initial();
    }
    //verify failed(not admin)
    public void verifyFail() throws InterruptedException, ParseException, IOException {
        AdminSurface adminSurface = new AdminSurface();
        adminSurface.Verify();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();
    }
    //cancel booking
    public void Cancel(String in){
        AdminSurface adminSurface = new AdminSurface();
        adminSurface.Cancel(in);
    }
    //change booking
    public void Changed(String in){
        AdminSurface adminSurface = new AdminSurface();
        adminSurface.Changed(in);
    }
    //initial booking information
    public void INITIAL(String in){
        AdminSurface adminSurface = new AdminSurface();
        adminSurface.INITIAL(in);

    }
    //next step, keep to do some action(cancel, change, refresh, delete) or exit
    public void nextStep() throws IOException, InterruptedException, ParseException {
        AdminSurface adminSurface = new AdminSurface();
        adminSurface.NextStep();
    }
    //Four options, choose to delete, cancel, exit or refresh
    public void Choice() throws IOException, InterruptedException, ParseException {
        String in = scan.nextLine();
        AdminModel adminModel = new AdminModel();
        adminModel.setChoice(in);
        BookingInt adminMode = new AdminModel();
        adminMode.booking();

    }
    //enter ID for change
    public void ReturnChoiceToView(){
        AdminSurface adminSurface = new AdminSurface();
        adminSurface.TypeId();
    }
    //set ID
    public void IdTypeIn(){
        String in = scan.nextLine();
        AdminModel adminModel = new AdminModel();
        adminModel.setId(in);

    }
    //back to service decide page
    public void Choice3() throws InterruptedException, ParseException, IOException {
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();
    }
    //the test has done, back to  the service decide page
    public void testDoneBefore() throws InterruptedException, ParseException, IOException {
        AdminSurface adminSurface = new AdminSurface();
        adminSurface.testDoneBefore();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();

    }
    //delete booking successfully
    public void DeleteSuccess() throws InterruptedException, ParseException, IOException {
        AdminSurface adminSurface = new AdminSurface();
        adminSurface.DeleteSuccess();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();

    }
    //enter an invalid ID
    public void InvaildId() throws InterruptedException, ParseException, IOException {
        AdminSurface adminSurface = new AdminSurface();
        adminSurface.InvaildId();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();


    }
    //cancel booking successfully
    public void CancelSuccess() throws InterruptedException, ParseException, IOException {
        AdminSurface adminSurface = new AdminSurface();
        adminSurface.CancelSuccess();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();

    }
}
