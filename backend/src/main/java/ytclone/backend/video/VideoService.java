package ytclone.backend.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    public final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video getById(long id) {
        return videoRepository.findById(id)
          .orElseThrow(() -> new RuntimeException("Video not found"));
    }

    public List<Video> getAll() {
        return videoRepository.findAll();
    }
}
