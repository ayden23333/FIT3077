package View;

import Controller.ProfileController.AdminController;

import java.io.IOException;
import java.text.ParseException;
//This class is the view for admin
public class AdminSurface {
    public void VerifyInit() throws InterruptedException, ParseException, IOException {
        System.out.println(
                "This function is for Admin only"
        );
        AdminController adminController = new AdminController();
        adminController.verify();
    }

    public void Verify(){
        System.out.println("Sorry you are not a admin");
    }

    public void Initial() throws IOException, InterruptedException, ParseException {
        System.out.println(" Here is all the booking information. \nFor canceled booking the status will show"+"\""+ "CANCEL"+"\""+"\nfor changed booking information the status will show "+"\""+" CHANGED "+"\"");

        AdminController adminController = new AdminController();
        adminController.initial();
    }

    //has changed information
    public void Changed(String in){
        System.out.println("-----------------------------");
        System.out.println("CHANGED INFORMATION");
        System.out.println("-----------------------------");
        System.out.println(in);
    }
    //the initial information
    public void INITIAL(String in){
        System.out.println("-----------------------------");
        System.out.println("INITIATED INFORMATION");
        System.out.println("-----------------------------");
        System.out.println(in);
    }
    //has canceled information
    public void Cancel(String in){
        System.out.println("-----------------------------");
        System.out.println("CANCELED INFORMATION");
        System.out.println("-----------------------------");
        System.out.println(in);
    }
    //If admin still want to delete, cancel, refresh or back to the service decide page
    public void NextStep() throws IOException, InterruptedException, ParseException {
        System.out.println("Type 1 for delete the booking, Type 2 for cancel the booking, Type 3 for back to main page, Type 4 for refresh the page");
        AdminController adminController = new AdminController();
        adminController.Choice();

    }
    //enter ID for change
    public void TypeId(){
        System.out.println("Please type the ID you want to change");
        AdminController adminController = new AdminController();
        adminController.IdTypeIn();
    }
    //This test has done before
    public void testDoneBefore(){
        System.out.println("You have done the test before, there fore you can't delete the booking");

    }
    //delete booking successful
    public void DeleteSuccess(){
        System.out.println("You have delete the booking");


    }
    //invalid ID
    public void InvaildId(){
        System.out.println("Your booking ID is wrong");


    }
    //cancel booking successful
    public void CancelSuccess(){
        System.out.println("You have cancel the booking");


    }

}
