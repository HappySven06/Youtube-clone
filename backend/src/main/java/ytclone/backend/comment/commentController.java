package ytclone.backend.comment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class commentController {

    @GetMapping("/getComment")
    public String getComment() {
        return "This is the comment controller";
    }
}
