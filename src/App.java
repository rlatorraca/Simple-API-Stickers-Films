import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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
            String title = film.get("fullTitle");
            
            String imageURL = film.get("image");
            System.out.println("Rank: " + film.get("rank"));
            
            StringBuffer sb = new StringBuffer();
            sb.append(imageURL);
            int positionToDelete = 0;
            int imageURLLength = imageURL.length();

            
            
            
            for (int j = 0 ; j < imageURL.length() ; j++){
                if (imageURL.charAt(j) == 'Y' && (imageURL.charAt(j+1) == '2' || imageURL.charAt(j+1) == '3' || imageURL.charAt(j+1) == '@')) {
                    positionToDelete  = j+1;
                }

                if (imageURL.charAt(j) == '@' && imageURL.charAt(j+1) == '@') {
                    positionToDelete  = j+1;
                }

                if (imageURL.charAt(j) == '@' && imageURL.charAt(j+1) == '.'){
                    positionToDelete  = j;
                }
                
            }    
        

            imageURL = sb.replace(positionToDelete+1, imageURLLength, ".jpg").toString();

            System.out.println("Title: " + title);
            System.out.println("Image: " + imageURL);
            System.out.print("Rate: " + film.get("imDbRating") + " - ");
           

            int totalStars = Math.round( Float.parseFloat(film.get("imDbRating")));
            //String goldStar = new String(Character.toChars(0x2b50));
            String blackStar = new String(Character.toChars(0x2605));
            String glowingStar = new String(Character.toChars(0x1f31f));
            for(int i=0; i <= totalStars; i++){
                System.out.print(glowingStar);
            }
            for(int i=0 ; i < 10 - totalStars; i++){
                System.out.print(blackStar);
            }
            System.out.println("\n");
            

            // generate thumbnails using the film image existing on IMBd server
            String thumbName = title + ".png";
            ThumbnailsFactory newThumb = new ThumbnailsFactory();

            InputStream urlInputStream = new URL(imageURL).openStream();
            

            newThumb.factory(urlInputStream, thumbName);
            urlInputStream.close();
          
        }
    }
}
