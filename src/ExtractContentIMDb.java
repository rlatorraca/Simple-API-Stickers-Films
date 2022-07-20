import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtractContentIMDb implements ExtractContentGeneric {

    @Override
    public List<Content> extractContent(String json) {

        // parse just interested data [title, image, rate]
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);

        //show and manipulating the data
        List<Content> contentList = new ArrayList<>();

        for (Map<String, String> attribute : attributesList) {
            String imageURL = attribute.get("image");
            imageURL = attribute.get("image").replaceAll("(@+)(.*).jpg$|(.)(_*).jpg$", "$1.jpg");


//            StringBuffer sb = new StringBuffer();
//            sb.append(imageURL);
//            int positionToDelete = 0;
//            int imageURLLength = imageURL.length();
//
//            for (int j = 0 ; j < imageURL.length() ; j++){
//                if (imageURL.charAt(j) == '.' && imageURL.charAt(j+1) == '_'){
//                    positionToDelete  = j;
//                }
//
//            }
//
//            imageURL = sb.replace(positionToDelete+1, imageURLLength, "jpg").toString();

            String rank = attribute.get("rank");
            String title = attribute.get("fullTitle");
            String rate = attribute.get("imDbRating") ;
            System.out.println("Rank: " + rank);
            System.out.println("Title: " + title);
            System.out.println("Image: " + imageURL);
            System.out.print("Rate: " + rate + " - ");


            float totalStars = Float.parseFloat(attribute.get("imDbRating"));
            String goldStar = new String(Character.toChars(0x2b50));
            String blackStar = new String(Character.toChars(0x2605));
            String glowingStar = new String(Character.toChars(0x1f31f));
            int totalStarsFor = Math.round(totalStars);
            for (int i = 0; i <= totalStarsFor; i++) {
                System.out.print(glowingStar);

            }

            for (int i = 0; i < 10 - totalStarsFor; i++) {
                System.out.print(blackStar);
            }
            System.out.println("\n");

            ContentIMDb content = new ContentIMDb(title, imageURL, rank, rate);
            contentList.add(content);
        }
        return contentList;
    }


}
