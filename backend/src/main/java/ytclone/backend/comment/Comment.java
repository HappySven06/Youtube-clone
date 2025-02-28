package ytclone.backend.comment;

import jakarta.persistence.*;
import ytclone.backend.user.User;
import ytclone.backend.video.Video;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;
    private String body;

    protected Comment() {}

    public Comment(User user, Video video, String body) {
        this.user = user;
        this.video = video;
        this.body = body;
    }

    public long getId() {
        return id;
    }
    public User getUserId() {
        return user;
    }
    public Video getVideoId() {
        return video;
    }
    public String getBody() {
        return body;
    }
}