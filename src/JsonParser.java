import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*"); // fetch a list that exist between square brackets
    private static final Pattern REGEX_ATTRIBUTE_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\""); // fetch "ATTRIBUTE": "VALUE"in json
    
    public List<Map<String, String>> parse(String json) {

        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {
            throw new IllegalArgumentException("No items found.");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> data = new ArrayList<>();

        for (String item : items) {

            Map<String, String> atributesItem = new HashMap<>();

            Matcher matcherAtributesJson = REGEX_ATTRIBUTE_JSON.matcher(item);
            while (matcherAtributesJson.find()) {
                String atribute = matcherAtributesJson.group(1);
                String value = matcherAtributesJson.group(2);
                atributesItem.put(atribute, value);
            }
            data.add(atributesItem);
        }

        return data;
    }
}
