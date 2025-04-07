package ytclone.backend.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import ytclone.backend.comment.dto.CommentDTO;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public String getComment() {
        return "This is the comment controller";
    }

    @GetMapping("/{videoId}")
    public List<CommentDTO> getCommentsByVideoId(@PathVariable long videoId) {
        return commentService.getCommentsByVideoId(videoId);
    }
}
