package ytclone.backend.comment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @GetMapping("/getComment")
    public String getComment() {
        return "This is the comment controller";
    }
}
