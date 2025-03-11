package ytclone.backend.database;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageGenerator {
  public static File generateImage() throws IOException {
    int width = 400, height = 300;
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = image.createGraphics();

    // Random color background
    graphics.setColor(new Color((int) (Math.random() * 0x1000000)));
    graphics.fillRect(0, 0, width, height);

    // Save the file
    String filename = "image-" + UUID.randomUUID() + ".jpg";
    File outputFile = new File("./src/main/java/ytclone/backend/database/ImageFile/" + filename);
    ImageIO.write(image, "jpg", outputFile);

    return outputFile;
  }
}