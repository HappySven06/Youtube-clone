package ytclone.backend.user;

import jakarta.persistence.*;
import ytclone.backend.history.History;
import ytclone.backend.media.Media;
import ytclone.backend.subscription.Subscription;
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
    @OneToOne
    @JoinColumn(name = "profilePic_media_id", nullable = false)
    private Media profilePic;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> mediaList;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Video> videoList;
    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscription>  channelIdList;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscription>  userIdList;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<History> history;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;


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
        return comments;
    }
    public List<Rating> getRatingList() {
        return ratings;
    }
    public List<Subscription> getChannelIdList() {
        return channelIdList;
    }
    public List<Subscription> getUserIdList() {
        return userIdList;
    }
    public List<History> getHistoryList() {
        return history;
    }
}