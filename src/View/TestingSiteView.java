package View;
import Controller.SearchController.SearchController;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
//This class is the view of searching testing site page
public class TestingSiteView {
    Scanner scan = new Scanner(System.in);
    //The initial page of searching testing site
    public void TestingSiteInit() throws IOException, InterruptedException, ParseException {
        System.out.println("|-------------------------------------------------------|");
        System.out.println(" You can search by both suburb name or type of facility\n Please type suburb name or type of facility you want");
        System.out.println("|-------------------------------------------------------|");
        SearchController searchController = new SearchController();
        searchController.getSiteName();
    }
    //present the result of searching after user type something to searching
    public void DisplaySearchInformation(String result) throws IOException, InterruptedException, ParseException {
        System.out.println("----------------------------------------------");
        System.out.println(result);
        System.out.println("----------------------------------------------");
        nextStep();
    }
    //The view of user want keep searching or exit
    public void nextStep() throws IOException, InterruptedException, ParseException {
        System.out.println("Would you like to keep searching? Type 1 for yes, 2 for back to service page");
        SearchController searchController = new SearchController();
        searchController.nextStep();
    }
    //Present a invalid value hints
    public void InvalidMethod(){
        System.out.println("Invalid method");
    }
}
