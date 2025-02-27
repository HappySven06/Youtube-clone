package ytclone.backend.video;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class videoController {

    @GetMapping("/getVideo")
    public String getVideo() {
        return "This is the video controller";
    }
}