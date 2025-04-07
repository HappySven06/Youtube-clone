package ytclone.backend.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ytclone.backend.video.dto.VideoDTO;
import ytclone.backend.video.dto.VideoSimpleDTO;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class VideoService {

    public final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public VideoDTO getById(long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found"));
        return new VideoDTO(video);
    }

    public List<VideoSimpleDTO> getAll() {
        List<Video> videos = videoRepository.findAll();
        return videos.stream().map(VideoSimpleDTO::new).collect(Collectors.toList());
    }
}
