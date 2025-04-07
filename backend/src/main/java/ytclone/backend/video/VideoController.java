package ytclone.backend.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ytclone.backend.video.dto.VideoDTO;
import ytclone.backend.video.dto.VideoSimpleDTO;

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
    public VideoDTO getVideoById(@PathVariable long id) {
        return videoService.getById(id);
    }

    @GetMapping("/videos")
    public List<VideoSimpleDTO> getVideos() {
        return videoService.getAll();
    }
}
