package ytclone.backend.user.dto;

import ytclone.backend.user.User;

public class UserDTO {
    private Long userId;
    private String channelName;
    private String userName;
    private String email;
    private String profilePicLink;

    public UserDTO(User user) {
        this.userId = user.getId();
        this.channelName = user.getChannelName();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.profilePicLink = user.getProfilePic().getLink();
    }

    public Long getUserId() {
        return userId;
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
    public String getProfilePicLink() {
        return profilePicLink;
    }
}
