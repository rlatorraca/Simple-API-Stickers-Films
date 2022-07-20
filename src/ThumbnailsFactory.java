import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

public class ThumbnailsFactory {
    
    void factory(InputStream urlInputStream, String thumbnailName) throws IOException {

        // reading image
                
        BufferedImage originalImage = ImageIO.read(urlInputStream);

        // generate new image with transparency and new size
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copy image source to new image (in memory)
        Graphics2D thumbImage = (Graphics2D) newImage.getGraphics();
        thumbImage.drawImage(originalImage, 0, 0, null);

        //setting up when drawing image
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 48);
        thumbImage.setColor(Color.YELLOW);
        thumbImage.setFont(font);

        // Write a comment/phrase in the image
        thumbImage.drawString("Approved by RLSP", width - 600, newHeight - 100);
        
        // write new image into the file
        File outputFile = new File("output/", thumbnailName);
        outputFile.mkdirs();
        ImageIO.write(newImage, "png", outputFile);
    }

}
