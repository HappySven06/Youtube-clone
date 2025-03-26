package ytclone.backend.database;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;

@Service
public class MinioService {

  private final MinioClient minioClient;

  @Value("${minio.bucket}")
  private String bucketName;

  public MinioService(@Value("${minio.url}") String url,
                      @Value("${minio.accessKey}") String accessKey,
                      @Value("${minio.secretKey}") String secretKey) {
    this.minioClient = MinioClient.builder()
      .endpoint(url)
      .credentials(accessKey, secretKey)
      .build();
  }

  // 🏆 Overloaded method to upload a File
  public String uploadFile(File file, String contentType) {
    try (InputStream inputStream = new FileInputStream(file)) {
      String uploadedFilePath = uploadFileStream(inputStream, contentType, file.length(), getFileExtension(file));

      // Delete the file from the local machine
      if (file.delete()) {
        System.out.println("File deleted successfully: " + file.getAbsolutePath());
      } else {
        System.err.println("Failed to delete the file: " + file.getAbsolutePath());
      }

      return uploadedFilePath;
    } catch (Exception e) {
      throw new RuntimeException("Error uploading file to MinIO", e);
    }
  }

  public String uploadFileStream(InputStream inputStream, String contentType, long size, String extension) {
    try {
      String filename = UUID.randomUUID() + extension;

      minioClient.putObject(PutObjectArgs.builder()
        .bucket(bucketName)
        .object(filename)
        .stream(inputStream, size, -1)
        .contentType(contentType)
        .build());

      return String.format("%s/%s", bucketName, filename);
    } catch (Exception e) {
      throw new RuntimeException("Error uploading file to MinIO", e);
    }
  }

  // Helper method to get file extension
  private String getFileExtension(File file) {
    String name = file.getName();
    return name.substring(name.lastIndexOf("."));
  }
}
