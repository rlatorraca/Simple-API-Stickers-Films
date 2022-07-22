import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
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

        String option = "0";
        do{
            BufferedReader my_reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter [I]mdb] , [N]ASA], [L]anguages programing or [E]xit] : ");
            option = my_reader.readLine().toLowerCase();
            // Connect HTTP and fetch 250 best films

            String url = "";
            ExtractContentGeneric extractor = null ;

            // show and manipulating data
            if(option.equalsIgnoreCase("i")){
                url = "https://imdb-api.com/en/API/Top250Movies/"+ PasswordFactory.getPassword();
                extractor = new ExtractContentIMDb() ;
            } else if(option.equalsIgnoreCase("n")){
                url = "https://api.nasa.gov/planetary/apod?api_key="+ PasswordFactory.getApiKey() +"&start_date=2022-06-12&end_date=2022-07-14";
                extractor = new ExtractContentNASA() ;
            } else if(option.equalsIgnoreCase("l")){
                //url = "http://localhost:8080/languages";
                url = "https://programing-languages-api.herokuapp.com/languages";
                extractor = new ExtractContentProgramingLanguages() ;
            }else {
                continue;
            }

            String json = new AppHttpClient().getClientBody(url);

            List<Content> contestList = extractor.extractContent(json);

            for (Content c : contestList) {

                Content content = c;

                // generate thumbnails using the content image existing on IMBd server
                String thumbName = c.getTitle() + ".png";
                ThumbnailsFactory newThumb = new ThumbnailsFactory();

                InputStream urlInputStream = new URL(c.getUrlImage()).openStream();

                newThumb.factory(urlInputStream, thumbName);
                urlInputStream.close();

            }

        } while(!option.equalsIgnoreCase("e"));
    }
}
