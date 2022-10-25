package API;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API_Service {
    private HttpClient client = HttpClient.newHttpClient();
    private HttpRequest request;
    APIStore apiStore = new APIStore();
    HttpResponse<String> response;
    final String myApiKey = apiStore.getMyApiKey();
    final String rootUrl = apiStore.getRootUrl();
    private String bookingUrl = rootUrl + "/booking";
    private String usersUrl = rootUrl + "/user";
    private String testingUrl = rootUrl + "/covid-test";
    private String sitesUrl = rootUrl + "/testing-site";
    private String usersLoginUrl = usersUrl + "/login";
    private String usersVerifyTokenUrl = usersUrl + "/verify-token";

    //This method is used to get the booking information by sending the http request
    public HttpResponse<String> getBooking() throws IOException, InterruptedException {
        request = HttpRequest
                .newBuilder(URI.create(bookingUrl))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
    public HttpResponse<String> getSomeoneBooking(String id) throws IOException, InterruptedException {
        request = HttpRequest
                .newBuilder(URI.create(bookingUrl+"/"+id))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }
    //This method is used to get the site information by sending the http request
    public HttpResponse<String> getSite() throws IOException, InterruptedException {
        request = HttpRequest
                .newBuilder(URI.create(sitesUrl))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
    //This method is used to get the user information by sending the http request
    public HttpResponse<String> getuser() throws IOException, InterruptedException {
        request = HttpRequest
                .newBuilder(URI.create(usersUrl))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
    //This method is used to get the site information by sending the http request
    public HttpResponse<String> postBooking(String jsonString) throws IOException, InterruptedException {
        request = HttpRequest
                .newBuilder(URI.create(bookingUrl))
                .setHeader("Authorization", myApiKey)
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }//This method is used to post the testing information by sending the http request
    public HttpResponse<String> postTesting(String jsonString) throws IOException, InterruptedException {
        request = HttpRequest
                .newBuilder(URI.create(testingUrl))
                .setHeader("Authorization", myApiKey)
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }//This method is used to get the testing information by sending the http request
    public HttpResponse<String> getTesting() throws IOException, InterruptedException {
        request = HttpRequest
                .newBuilder(URI.create(testingUrl))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }//This method is used to Patch the testing information by sending the http request
    public HttpResponse<String> PatchTesting(String jsonString, String test) throws IOException, InterruptedException {
        request = HttpRequest
                .newBuilder(URI.create(testingUrl+"/"+test))
                .setHeader("Authorization",myApiKey)
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .method("PATCH",HttpRequest.BodyPublishers.ofString(jsonString))
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;




}
//This method is used to post the login information by sending the http request
public HttpResponse<String> Login(String jsonString) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create(usersLoginUrl + "?jwt=true"))
                .setHeader("Authorization", myApiKey)
                // This header needs to be set when sending a JSON request body.
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();
       response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }//This method is used to verify the login information by JWT token by sending the http request
    public HttpResponse<String> Verify(String jsonString) throws IOException, InterruptedException {
        request = HttpRequest.newBuilder(URI.create(usersVerifyTokenUrl)) // Return a JWT so we can use it in Part 5 later.
                .setHeader("Authorization", myApiKey)
                .header("Content-Type","application/json") // This header needs to be set when sending a JSON request body.
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }//This method is used to post the user information for register by sending the http request
    public HttpResponse<String> Register(String jsonString) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(usersUrl))
                .setHeader("Authorization", myApiKey)
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
    public HttpResponse<String> PatchBooking(String jsonString, String test) throws IOException, InterruptedException {
        request = HttpRequest
                .newBuilder(URI.create(bookingUrl+"/"+test))
                .setHeader("Authorization",myApiKey)
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .method("PATCH",HttpRequest.BodyPublishers.ofString(jsonString))
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
}public HttpResponse<String> DeleteBooking(String id) throws IOException, InterruptedException {
        request = HttpRequest
                .newBuilder(URI.create(bookingUrl+"/"+id))
                .setHeader("Authorization", myApiKey)
                .DELETE()
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

}
