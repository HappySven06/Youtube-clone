package ytclone.backend.video.dto;

import ytclone.backend.video.Video;

public class VideoSimpleDTO {
    private Long id;
    private String title;
    private String channelname;
    private String thumbnailLink;
    private Long views;

    public VideoSimpleDTO(Video video) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.channelname = video.getUser().getChannelName();
        this.thumbnailLink = video.getThumbnail().getLink();
        this.views = (long) video.getHistoryList().size();
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getChannelname() {
        return channelname;
    }
    public String getThumbnailLink() {
        return thumbnailLink;
    }
    public Long getViews() {
        return views;
    }
}
