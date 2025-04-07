package ytclone.backend.comment.dto;

import ytclone.backend.comment.Comment;

public class CommentDTO {
    private Long id;
    private Long userId;
    private String userName;
    private String userPicLink;
    private String comment;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUser().getId();
        this.userName = comment.getUser().getUserName();
        this.userPicLink = comment.getUser().getProfilePic().getLink();
        this.comment = comment.getBody();
    }

    public Long getId() {
        return id;
    }
    public Long getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }
    public String getUserPicLink() {
        return userPicLink;
    }
    public String getComment() {
        return comment;
    }
}
