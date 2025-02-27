package ytclone.backend.media;

import jakarta.persistence.*;
import ytclone.backend.user.User;
import ytclone.backend.video.Video;

@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
    private String type;
    private String link;
    private boolean access;

    @OneToOne(mappedBy = "media", cascade = CascadeType.ALL, orphanRemoval = true)
    private Media media;
    @OneToOne(mappedBy = "media", cascade = CascadeType.ALL, orphanRemoval = true)
    private Video video;
    @OneToOne(mappedBy = "media", cascade = CascadeType.ALL, orphanRemoval = true)
    private User userPic;

    protected Media() {}

    public Media(Long id, User userId, String type, String link, boolean access) {
        this.id = id;
        this.userId = userId;
        this.type = type; 
        this.link = link;
        this.access = access;
    }

    public Long getId() {
        return id;
    }
    public User getUserId() {
        return userId;
    }
    public String getType() {
        return type;
    }
    public String getLink() {
        return link;
    }
    public boolean isAccess() {
        return access;
    }

    public Media getMedia() {
        return media;
    }
    public Video getVideo() {
        return video;
    }
}
