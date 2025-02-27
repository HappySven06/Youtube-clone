package ytclone.backend.media;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaController {

    @GetMapping("/getMedia")
    public String getMedia() {
        return "This is the media conroller";
    }
}
