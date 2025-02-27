package ytclone.backend.user;

import jakarta.persistence.*;
import ytclone.backend.media.Media;
import ytclone.backend.video.Video;
import ytclone.backend.comment.Comment;
import ytclone.backend.rating.Rating;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String channelName;
    private String userName;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> mediaList;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Video> videoList;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratingList;

    protected User() {}

    public User(String channelName, String userName, String email, String password) {
        this.channelName = channelName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }
    public String getChannelName() {
        return channelName;
    }
    public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }
    public List<Video> getVideoList() {
        return videoList;
    }
    public List<Comment> getCommentList() {
        return commentList;
    }
    public List<Rating> getRatingList() {
        return ratingList;
    }
}