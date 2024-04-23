package dev.hklm.fdbackend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String username;
    private String imageURL;
    private String description;
    private String location;
    private Integer totalCatches;
    private Integer fishdexId;
    private Integer diaryId;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private byte[] userImage;

    public User() {}

    public User(String location, String username, String description, Integer totalCatches, Long uid, Integer fishdexId, Integer diaryId) throws IOException {
        this.location = location;
        this.username = username;
        this.description = description;
        this.totalCatches = totalCatches;
        this.imageURL = "http://localhost:8080/user/image/" + uid;

        this.fishdexId = fishdexId;
        this.diaryId = diaryId;

        ClassPathResource userPathRessource = new ClassPathResource("img/user.png");
        this.userImage = FileCopyUtils.copyToByteArray(userPathRessource.getInputStream());

    }

    public Long getId() {
        return id;
    }

    public String getUsername() { return username; }

    public String getLocation() { return location; }

    public Integer getTotalCatches() { return totalCatches; }

    public String getDescription() {
        return description;
    }

    public Integer getFishdexId() { return fishdexId; }

    public Integer getDiaryId() { return diaryId; }
    public byte[] getUserImage() {
        return userImage;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public String getImgUrl() {
        return imageURL;
    }

    public void setImgUrl(String userId) {
        this.imageURL = "http://localhost:8080/user/image/" + userId;
    }

}
