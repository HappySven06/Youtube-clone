package ytclone.backend.database;

import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.w3c.dom.Text;
import ytclone.backend.user.UserRepository;
import ytclone.backend.media.MediaRepository;
import ytclone.backend.video.VideoRepository;
import ytclone.backend.comment.CommentRepository;
import ytclone.backend.rating.RatingRepository;
import ytclone.backend.subscription.SubscriptionRepository;
import ytclone.backend.history.HistoryRepository;

import ytclone.backend.user.User;
import ytclone.backend.media.Media;
import ytclone.backend.video.Video;
import ytclone.backend.comment.Comment;
import ytclone.backend.rating.Rating;
import ytclone.backend.subscription.Subscription;
import ytclone.backend.history.History;

import ytclone.backend.database.MinioService;
import ytclone.backend.database.VideoGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.IOException;

@Component
public class DatabaseSeeder implements CommandLineRunner {

  private final UserRepository userRepository;
  private final MediaRepository mediaRepository;
  private final VideoRepository videoRepository;
  private final CommentRepository commentRepository;
  private final RatingRepository ratingRepository;
  private final SubscriptionRepository subscriptionRepository;
  private final HistoryRepository historyRepository;

  private final MinioService minioService;
  private final VideoGenerator videoGenerator;
  private final ImageGenerator imageGenerator;

  private final Faker faker = new Faker();

  public DatabaseSeeder(UserRepository userRepository, MediaRepository mediaRepository, VideoRepository videoRepository, CommentRepository commentRepository, RatingRepository ratingRepository, SubscriptionRepository subscriptionRepository, HistoryRepository historyRepository, MinioService minioService, VideoGenerator videoGenerator, ImageGenerator imageGenerator) {
    this.userRepository = userRepository;
    this.mediaRepository = mediaRepository;
    this.videoRepository = videoRepository;
    this.commentRepository = commentRepository;
    this.ratingRepository = ratingRepository;
    this.subscriptionRepository = subscriptionRepository;
    this.historyRepository = historyRepository;
    this.minioService = minioService;
    this.videoGenerator = videoGenerator;
    this.imageGenerator = imageGenerator;
  }

  @Override
  @Transactional
  public void run(String... args) {
    seedUsers(5);
  }

  private void seedUsers(int count) {
    List<User> users = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      User user = new User(faker.funnyName().name(), faker.name().username(), faker.internet().emailAddress(), faker.internet().password());
      users.add(user);
    }
    userRepository.saveAll(users);

    List<Media> profilePics = new ArrayList<>();
    for (User user : users) {
      String imageUrl = uploadFakeFile("image/png", ".png"); // Upload fake image

      Media media = new Media(user, "image", imageUrl, true);
      profilePics.add(media);

      user.setProfilePic(media);
    }
    mediaRepository.saveAll(profilePics);
    userRepository.saveAll(users);

    List<Video> videos = new ArrayList<>();
    for (User user : users) {
      for (int j = 0; j < 3; j++) { // Each user uploads 3 videos
        String thumbnailUrl = uploadFakeFile("image/png", ".png"); // Fake thumbnail
        String videoUrl = uploadFakeFile("video/mp4", ".mp4"); // Fake video file

        Media thumbnail = new Media(user, "image", thumbnailUrl, true);
        mediaRepository.save(thumbnail);

        Media videoFile = new Media(user, "video", videoUrl, true);
        mediaRepository.save(videoFile);

        Video video = new Video(user, faker.book().title(), faker.lorem().paragraph(), thumbnail, videoFile);
        videos.add(video);
      }
    }
    videoRepository.saveAll(videos);

    System.out.println("Database seeding completed!");
  }

  private String uploadFakeFile(String contentType, String extension) {
    if (contentType.equals("video/mp4")) {
      File videoFile = null;
      try {
        videoFile = videoGenerator.generateVideo();
      }
      catch (Exception e) {
        throw new RuntimeException("Video generation failed:", e);
      }
      return minioService.uploadFile(videoFile, "video/mp4");
    }

    // Generate a fake image file for thumbnails
    if(contentType.equals("image/png")) {
      File imageFile = null;
      try {
        imageFile = imageGenerator.generateImage();
      }
      catch (Exception e) {
        throw new RuntimeException("Image generation failed:", e);
      }
      return minioService.uploadFile(imageFile, "image/jpg");
    }

    return null;
  }
}
