package ytclone.backend.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ytclone.backend.comment.dto.CommentDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentDTO> getCommentsByVideoId(@PathVariable long videoId) {
        List<Comment> comment = commentRepository.findAll().stream().filter(video -> video.getVideo().getId() == videoId).toList();
        return comment.stream().map(CommentDTO::new).collect(Collectors.toList());
    }
}
