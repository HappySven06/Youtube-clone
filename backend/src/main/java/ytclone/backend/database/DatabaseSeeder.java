package ytclone.backend.database;

import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;

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
    seedUsers(100);
  }

  private void seedUsers(int count) {
    if(userRepository.count() < count) {
      List<User> users = new ArrayList<>();
      for (int i = 0; i < count; i++) {
        User user = new User(faker.funnyName().name(), faker.name().fullName(), faker.internet().emailAddress(), faker.internet().password());
        users.add(user);
      }
      userRepository.saveAll(users);

      System.out.println("User seeding completed!");

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

      System.out.println("Video seeding completed!");

      List<Comment> comments = new ArrayList<>();
      List<History> historys = new ArrayList<>();
      List<Rating> ratings = new ArrayList<>();
      for (Video video : videos) {
        for (User user : users) {
          int diceRoll;

          diceRoll = getRandom(2, 2);
          if(diceRoll == 1) {
            History history = new History(user, video, (long) getRandom(1, 5));
            historys.add(history);

            diceRoll = getRandom(2, 2);
            Comment comment = null;
            if (diceRoll == 1) {
              comment = new Comment(user, video, faker.lorem().paragraph());
            }
            if (comment != null) {
              comments.add(comment);
            }

            diceRoll = getRandom(1, 3);
            Rating rating = null;
            if (diceRoll == 1) {
              rating = new Rating(user, video, true);
            } else if (diceRoll == 3) {
              rating = new Rating(user, video, false);
            }
            if (rating != null) {
              ratings.add(rating);
            }
          }
        }
      }
      commentRepository.saveAll(comments);
      historyRepository.saveAll(historys);
      ratingRepository.saveAll(ratings);

      System.out.println("Comments seeding completed!");
      System.out.println("History seeding completed!");
      System.out.println("Ratings seeding completed!");

      List<Subscription> subscriptions = new ArrayList<>();
      for (User channel : users) {
        for (User user : users) {
          int diceRoll = getRandom(2, 2);
          Subscription subscription = null;

          if (diceRoll == 1) {
            boolean notifications = false;
            diceRoll = getRandom(2, 2);

            if(diceRoll == 1) {
              notifications = true;
            }

            subscription = new Subscription(channel, user, notifications);
          }
          if (subscription != null) {
            subscriptions.add(subscription);
          }
        }
      }
      subscriptionRepository.saveAll(subscriptions);

      System.out.println("Subscriptions seeding completed!");

      System.out.println("Database seeding completed!");
    }
    else {
      System.out.println("Database seeding completed before!");
    }
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

  private int getRandom(int type, int max) {
    Random random = new Random();

    if(type == 1) {
      return random.nextInt(max) + 1;
    }
    if(type == 2) {
      return random.nextBoolean() ? 1 : 0;
    }

    return 0;
  }
}
