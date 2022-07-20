import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtractContentNASA implements ExtractContentGeneric {
    @Override
    public List<Content> extractContent(String json) {
        // parse just interested data [title, image, rate]
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);

        //show and manipulating the data
        List<Content> contentList = new ArrayList<>();

        for (Map<String, String> attribute : attributesList) {
            String imageURL = attribute.get("url");
            String title = attribute.get("title");
            ContentNASA content = new ContentNASA(title, imageURL);
            contentList.add(content);
        }
        return contentList;
    }
}
