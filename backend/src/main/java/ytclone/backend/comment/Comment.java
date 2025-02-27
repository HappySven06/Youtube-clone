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
    private User userId;
    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video videoId;
    private String body;

    protected Comment() {}

    public Comment(User userId, Video videoId, String body) {
        this.userId = userId;
        this.videoId = videoId;
        this.body = body;
    }

    public long getId() {
        return id;
    }
    public User getUserId() {
        return userId;
    }
    public Video getVideoId() {
        return videoId;
    }
    public String getBody() {
        return body;
    }
}