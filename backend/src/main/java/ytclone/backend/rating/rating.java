package ytclone.backend.rating;

import jakarta.persistence.*;

@Entity
public class rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long videoId;
    private Boolean rating;

    protected rating() {}

    public rating(Long userId, Long videoId, Boolean rating) {
        this.userId = userId;
        this.videoId = videoId;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }
    public Long getUserId() {
        return userId;
    }
    public Long getVideoId() {
        return videoId;
    }
    public Boolean getRating() {
        return rating;
    }
}