package ytclone.backend.video;

import jakarta.persistence.*;

@Entity
public class video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private Long thumbnail;
    private Long video;

    protected video() {}

    public video(Long id, Long userId, String title, String description, Long thumbnail, Long video) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.video = video;
    }

    public Long getId() {
        return id;
    }
    public Long getUserId() {
        return userId;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public Long getThumbnail() {
        return thumbnail;
    }
    public Long getVideo() {
        return video;
    }
}
