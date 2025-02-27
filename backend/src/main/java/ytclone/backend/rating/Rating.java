package ytclone.backend.rating;

import jakarta.persistence.*;
import ytclone.backend.user.User;
import ytclone.backend.video.Video;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video videoId;
    private Boolean rating;

    protected Rating() {}

    public Rating(User userId, Video videoId, Boolean rating) {
        this.userId = userId;
        this.videoId = videoId;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }
    public User getUserId() {
        return userId;
    }
    public Video getVideoId() {
        return videoId;
    }
    public Boolean getRating() {
        return rating;
    }
}