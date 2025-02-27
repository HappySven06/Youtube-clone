package ytclone.backend.user;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String channelName;
    private String userName;
    private String email;
    private String password;

    protected user() {}

    public user(String channelName, String userName, String email, String password) {
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
}


