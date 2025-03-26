package ytclone.backend.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {

    public final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public String getVideo() {
        return "This is the video controller";
    }

    @GetMapping("/{id}")
    public Video getVideoById(@PathVariable long id) {
        return videoService.getById(id);
    }

    @GetMapping("/videos")
    public List<Video> getVideos() {
        return videoService.getAll();
    }
}
