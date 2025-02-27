package ytclone.backend.comment;

import jakarta.persistence.*;

@Entity
public class comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private long videoId;
    private String body;

    protected comment() {}

    public comment(long userId, long videoId, String body) {
        this.userId = userId;
        this.videoId = videoId;
        this.body = body;
    }

    public long getId() {
        return id;
    }
    public long getUserId() {
        return userId;
    }
    public long getVideoId() {
        return videoId;
    }
    public String getBody() {
        return body;
    }
}