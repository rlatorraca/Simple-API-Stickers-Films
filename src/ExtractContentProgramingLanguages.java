import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtractContentProgramingLanguages implements ExtractContentGeneric {
    @Override
    public List<Content> extractContent(String json) {
        // parse just interested data [title, image, rate]
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);

        //show and manipulating the data
        List<Content> contentList = new ArrayList<>();

        for (Map<String, String> attribute : attributesList) {
            String imageURL = attribute.get("image");
            String title = attribute.get("title");
            String ranking = attribute.get("ranking");
            ContentLanguage content = new ContentLanguage(title, imageURL,ranking);
            contentList.add(content);

            System.out.println("Title: " + title);
            System.out.println("Image: " + imageURL);
            System.out.println("Ranking: " + ranking);
        }
        return contentList;
    }
}
