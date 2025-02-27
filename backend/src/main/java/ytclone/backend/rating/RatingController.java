package ytclone.backend.rating;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingController {

    @GetMapping("/getRating")
    public String getRating() {
        return "This is the rating controller";
    }
}
