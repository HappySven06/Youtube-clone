package ytclone.backend.history;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import ytclone.backend.user.User;
import ytclone.backend.video.Video;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    private Long watchTime;

    protected History() {}

    public History(User user, Video video, Long watchTime) {
        this.user = user;
        this.video = video;
        this.watchTime = watchTime;
    }

    public Long getId() {
        return id;
    }
    public User getUser() {
        return user;
    }
    public Video getVideo() {
        return video;
    }
    public Long getWatchTime() {
        return watchTime;
    }
    public Long setWatchTime(Long watchTime) {
        this.watchTime = watchTime;
        return watchTime;
    }
}
