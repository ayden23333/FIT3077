package View;

import Controller.TestingController.TestingController;

import java.io.IOException;
import java.text.ParseException;
//This is the view for healthcare worker to test
public class HealthCareView {
    TestingController testingController = new TestingController();
    public void TestingInit() throws IOException, InterruptedException, ParseException {
        //family name
        System.out.println("FamilyName:");
        testingController.TestingFamilyName();

    }
    public void GivenName() throws IOException, InterruptedException, ParseException {
        //given name
        System.out.println("GivenName:");
        testingController.GiveName();
    }
    //get the symptoms
    public void getSymptoms() throws IOException, InterruptedException, ParseException {
        System.out.println("Do you have Symptoms？type 1 for yes, type 2 for no");
        testingController.Symptoms();

    }
    //the view of symptoms: PCR test
    public void PCR(){
        System.out.println("Because you have symptoms, we advise you conduct a PCR test");
    }
    //the view of no symptoms: RAT test
    public void RAT(){
        System.out.println("Because you have no symptoms, we advise you conduct a RAT test");
    }
    //the view of checking if booked
    public void bookchecking(){
        System.out.println("|-------------------------------------|");
        System.out.println(" You haven't booked yet ");
        System.out.println("|-------------------------------------|");
    }
    //the view of checking if registered
    public void FailIdchecking(){
        System.out.println("|-------------------------------------|");
        System.out.println(" You haven't registered yet ");
        System.out.println("|-------------------------------------|");

    }
    //the view of starting testing
    public void testingStart(){

        System.out.println("Now starting you testing process");
    }
    //failed testing by can not find ID
    public void testingFail(){
        System.out.println("A patient, administerer, and/or booking with the provided ID was not found.");
        System.out.println("Now will return to the service decide page");
    }
    //finish testing view page
    public void testingSuccess(String result){
        System.out.println("Here is your testing information: "+result);
        System.out.println("You have successfully conduct the testing, Now will return to the service decide page");
    }


}
