package ytclone.backend.media;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mediaController {

    @GetMapping("/getMedia")
    public String getMedia() {
        return "This is the media conroller";
    }
}
