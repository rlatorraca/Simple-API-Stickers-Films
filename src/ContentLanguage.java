public class ContentLanguage extends Content {

    private final String rank;


    public ContentLanguage(String title, String urlImage, String rank) {
        super(title, urlImage);
        this.rank = rank;

    }


    public String getRank() {
        return rank;
    }


}
