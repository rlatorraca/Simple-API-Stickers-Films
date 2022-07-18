import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {
      
        // Connect HTTP and fetch 250 best films

        String url = "https://imdb-api.com/en/API/Top250Movies/k_8j0gdazj";
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String responseBody = response.body();
        

        // parse just interested data [title, image, rate]
        JsonParser parser = new JsonParser();
        List<Map<String, String>> filmsList = parser.parse(responseBody);
        
        //show and manipulating the data

        for (Map<String,String> film : filmsList) {
            System.out.println("Rank: " + film.get("rank"));
            System.out.println("Title: " + film.get("fullTitle"));
            System.out.println("Image: " + film.get("image"));
            System.out.print("Rate: " + film.get("imDbRating") + " - ");

            int totalStars = Math.round( Float.parseFloat(film.get("imDbRating")));
            String goldStar = new String(Character.toChars(0x2b50));
            String blackStar = new String(Character.toChars(0x2605));
            String glowingStar = new String(Character.toChars(0x1f31f));
            for(int i=0; i <= totalStars; i++){
                System.out.print(glowingStar);
            }
            for(int i=0 ; i < 10 - totalStars; i++){
                System.out.print(blackStar);
            }
            System.out.println();
            System.out.println();
          
        }
    }
}
