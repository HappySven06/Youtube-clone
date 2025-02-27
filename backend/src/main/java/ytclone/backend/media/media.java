package ytclone.backend.media;

import jakarta.persistence.*;

@Entity
public class media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String type;
    private String link;
    private boolean access;

    protected media() {}

    public media(Long id, Long userId, String type, String link, boolean access) {
        this.id = id;
        this.userId = userId;
        this.type = type; 
        this.link = link;
        this.access = access;
    }

    public Long getId() {
        return id;
    }
    public Long getUserId() {
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
}
