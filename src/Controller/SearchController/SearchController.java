package Controller.SearchController;

import Model.SearchModel.SearchModelInt;
import Model.SearchModel.TypeInSearchModelModel;
import View.ServiceDecideView;
import View.TestingSiteView;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
// This class is about the controller of search
public class SearchController {

    Scanner scan = new Scanner(System.in);
    //get testing site name
    public void getSiteName() throws IOException, InterruptedException, ParseException {
        String in = scan.nextLine();
        String name = ("\""+in+"\"").toLowerCase();
        SearchModelInt typeInSearch = new TypeInSearchModelModel();
        TypeInSearchModelModel typeInSearchModel = new TypeInSearchModelModel();
        typeInSearchModel.setSitename(name);
        typeInSearch.search();
    }
    //display testing site information
    public void updateSearchResult() throws IOException, InterruptedException, ParseException {
        TypeInSearchModelModel typeInSearchModel = new TypeInSearchModelModel();
        TestingSiteView testingSiteView = new TestingSiteView();
        testingSiteView.DisplaySearchInformation(typeInSearchModel.getResult());
    }
    //keeping searching or back to service decide page or an invalid input prompt
    public void nextStep() throws IOException, InterruptedException, ParseException {
        String choose = scan.nextLine();
        TestingSiteView testingSiteView = new TestingSiteView();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        //If users type 1, it will keep searching
        if (choose.equals("1")) {
            testingSiteView.TestingSiteInit();
        }

        //If users type 2. Exit and back to the service decide view page
        else if (choose.equals("2"))
        {
            serviceDecideView.ServiceDecide();
        }
        //If users type something else. it will show there is a invalid value and back to service decide
        else{
            testingSiteView.InvalidMethod();
            serviceDecideView.ServiceDecide();
        }
    }
}
