package ytclone.backend.database;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

@Service
public class VideoGenerator {

  public static File generateVideo() {
    // Generate random RGB values (0-255 for each channel)
    Random random = new Random();
    int red = random.nextInt(256); // Random value between 0 and 255
    int green = random.nextInt(256);
    int blue = random.nextInt(256);

    // Convert RGB values to hexadecimal format
    String hexColor = String.format("#%02x%02x%02x", red, green, blue);

    // Create a new file for the video
    File videoFile = new File("../db/VideoFile/" + UUID.randomUUID() + ".mp4");

    try {
      // FFmpeg command to generate a 5-second silent video with the random RGB color (in hex format)
      ProcessBuilder processBuilder = new ProcessBuilder(
              "ffmpeg", "-f", "lavfi", "-i", "color=c=" + hexColor + ":s=1280x720:d=5",
              "-pix_fmt", "yuv420p", videoFile.getAbsolutePath()
      );
      processBuilder.inheritIO();
      Process process = processBuilder.start();
      process.waitFor();
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException("Error generating video", e);
    }

    return videoFile;
  }
}
