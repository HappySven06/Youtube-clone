package ytclone.backend.media;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import ytclone.backend.user.User;
import ytclone.backend.video.Video;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    @OneToOne(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private Video videoMedia;

    @OneToOne(mappedBy = "thumbnail", cascade = CascadeType.ALL, orphanRemoval = true)
    private Video thumbnailMedia;

    @OneToOne(mappedBy = "profilePic", cascade = CascadeType.ALL, orphanRemoval = true)
    private User userPic;

    protected Media() {}

    public Media(User userId, String type, String link, boolean access) {
        this.userId = userId;
        this.type = type; 
        this.link = link;
        this.access = access;
    }

    public Long getId() {
        return id;
    }
    public Long getUserId() {
        return userId.getId();
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

    public Video getMedia() {
        return thumbnailMedia;
    }
    public Video getVideo() {
        return videoMedia;
    }
}
