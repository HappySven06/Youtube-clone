package ytclone.backend.video;

import jakarta.persistence.*;
import org.w3c.dom.Text;
import ytclone.backend.history.History;
import ytclone.backend.media.Media;
import ytclone.backend.rating.Rating;
import ytclone.backend.user.User;
import ytclone.backend.comment.Comment;

import java.util.List;

@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String title;
    private String description;
    @OneToOne
    @JoinColumn(name = "thumbnail_media_id", nullable = false)
    private Media thumbnail;
    @OneToOne
    @JoinColumn(name = "video_media_id", nullable = false)
    private Media video;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<History> history;
    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    protected Video() {}

    public Video(User user, String title, String description, Media thumbnail, Media video) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.video = video;
    }

    public Long getId() {
        return id;
    }
    public User getUserId() {
        return user;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public Media getThumbnail() {
        return thumbnail;
    }
    public Media getVideo() {
        return video;
    }

    public List<Comment> getCommentList() {
        return comments;
    }
    public List<Rating> getRatingList() {
        return ratings;
    }
    public List<History> getHistoryList() {
        return history;
    }
}
