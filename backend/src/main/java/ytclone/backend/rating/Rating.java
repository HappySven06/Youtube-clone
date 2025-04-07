package ytclone.backend.rating;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import ytclone.backend.user.User;
import ytclone.backend.video.Video;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    private Boolean rating;

    protected Rating() {}

    public Rating(User user, Video video, Boolean rating) {
        this.user = user;
        this.video = video;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }
    public User getUserId() {
        return user;
    }
    public Video getVideoId() {
        return video;
    }
    public Boolean getRating() {
        return rating;
    }
}