package ytclone.backend.video.dto;

import ytclone.backend.video.Video;
import java.util.stream.Collectors;

public class VideoDTO {
    private Long id;
    private Long channelId;
    private String channelName;
    private String title;
    private String description;
    private String videoLink;
    private Long posLikes;
    private Long negLikes;

    public VideoDTO(Video video) {
        this.id = video.getId();
        this.channelId = video.getUser().getId();
        this.channelName = video.getUser().getChannelName();
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.videoLink = video.getVideo().getLink();
        this.posLikes = (long) video.getRatingList().stream().filter(rating -> Boolean.TRUE.equals(rating.getRating())).toList().size();
        this.negLikes = (long) video.getRatingList().stream().filter(rating -> Boolean.FALSE.equals(rating.getRating())).toList().size();
    }

    public Long getId() {
        return id;
    }
    public Long getChannelId() {
        return channelId;
    }
    public String getChannelName() {
        return channelName;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getVideoLink() {
        return videoLink;
    }
    public Long getPosLikes() {
        return posLikes;
    }
    public Long getNegLikes() {
        return negLikes;
    }
}
