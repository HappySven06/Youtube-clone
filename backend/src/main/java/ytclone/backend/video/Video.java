package ytclone.backend.video;

import jakarta.persistence.*;
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
    private User userId;
    private String title;
    private String description;
    @OneToOne
    @JoinColumn(name = "media_id", nullable = false)
    private Media thumbnail;
    @OneToOne
    @JoinColumn(name = "media_id", nullable = false)
    private Media video;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList;
    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratingList;

    protected Video() {}

    public Video(Long id, User userId, String title, String description, Media thumbnail, Media video) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.video = video;
    }

    public Long getId() {
        return id;
    }
    public User getUserId() {
        return userId;
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
        return commentList;
    }
    public List<Rating> getRatingList() {
        return ratingList;
    }
}
