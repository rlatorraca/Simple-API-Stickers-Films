public class ContentIMDb extends Content {

    private final String rank;
    private final String rate;

    public ContentIMDb(String title, String urlImage, String rank, String rate) {
        super(title, urlImage);
        this.rank = rank;
        this.rate = rate;
    }

    public String getRate() {
        return rate;
    }

    public String getRank() {
        return rank;
    }


}
