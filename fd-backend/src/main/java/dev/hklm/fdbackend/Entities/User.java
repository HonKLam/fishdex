package dev.hklm.fdbackend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.time.Instant;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long uid;

    private String username;
    private String imageURL;
    private String description;
    private String userLocation;
    private Integer totalCatches;
    // evtl. "aktiv seit x" Varible?

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private byte[] userImage;

    public User() {}

    public User(String userLocation, String username, String description, Integer totalCatches, Long uid) throws IOException {
        this.userLocation = userLocation;
        this.username = username;
        this.description = description;
        this.totalCatches = totalCatches;
        this.imageURL = "http://localhost:8080/user/image/" + uid;

        ClassPathResource userPathRessource = new ClassPathResource("img/catch.png");
        this.userImage = FileCopyUtils.copyToByteArray(userPathRessource.getInputStream());

    }

    public Long getUid() {
        return uid;
    }

    public String getUsername() { return username; }

    public String getLocation() { return userLocation; }

    public String getImageURL() {
        return imageURL;
    }

    public Integer getTotalCatches() { return totalCatches; }

    public String getDescription() {
        return description;
    }

    public byte[] getUserImage() {
        return userImage;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public String getImgUrl() {
        return imageURL;
    }

    public void setImgUrl(String catchId) {
        this.imageURL = "http://localhost:8080/user/image/" + uid;
    }

}
