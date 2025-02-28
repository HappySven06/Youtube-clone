package ytclone.backend.subscription;

import jakarta.persistence.*;
import ytclone.backend.user.User;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "channel_user_id", nullable = false)
    private User channel;
    @ManyToOne
    @JoinColumn(name = "user_user_id", nullable = false)
    private User user;
    private boolean notification;

    protected Subscription() {}

    public Subscription(User channelId, User userId, boolean notifications) {
        this.channel = channelId;
        this.user = userId;
        this.notification = notifications;
    }

    public Long getId() {
        return id;
    }
    public User getChannel() {
        return channel;
    }
    public User getUser() {
        return user;
    }
    public boolean hasNotification() {
        return notification;
    }
    public boolean setNotification(boolean notification) {
        this.notification = notification;
        return notification;
    }

}
