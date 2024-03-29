import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PasswordFactory {

    static String getPassword() throws IOException {
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream("src/conf/conf.properties");
        properties.load(inputStream);
        return properties.getProperty("password");
    }

    static String getApiKey() throws IOException {
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream("src/conf/conf.properties");
        properties.load(inputStream);
        return properties.getProperty("api_key");
    }



}
