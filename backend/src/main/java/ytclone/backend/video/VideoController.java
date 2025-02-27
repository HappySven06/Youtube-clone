package ytclone.backend.video;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

    @GetMapping("/getVideo")
    public String getVideo() {
        return "This is the video controller";
    }
}