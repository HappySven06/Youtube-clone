package ytclone.backend.database;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.File;
import java.util.UUID;

@Service
public class VideoGenerator {
  public static File generateVideo() {
    File videoFile = new File("../db/VideoFile/" + UUID.randomUUID() + ".mp4");
    try {
      // FFmpeg command to generate a 5-second silent video
      ProcessBuilder processBuilder = new ProcessBuilder(
        "ffmpeg", "-f", "lavfi", "-i", "color=c=blue:s=1280x720:d=5",
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
