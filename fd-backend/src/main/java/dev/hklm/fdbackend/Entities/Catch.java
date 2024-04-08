package dev.hklm.fdbackend.Entities;

import jakarta.persistence.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

@Entity
public class Catch {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String location;
    private String imageURL;
    private Double length;
    private String description;
    private String fish;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] catchImage;

    public Catch() {}

    public Catch(String location, Double length, String description, Fish fish, Long catchId) throws IOException {
        this.location = location;
        this.length = length;
        this.description = description;
        this.fish = fish.getName();
        this.imageURL = "http://localhost:8080/catch/image/" + catchId;

        /* Die Properties müssen nicht mitgegeben werden, wenn man POST /fish im Frontend macht.
         Bitte nach POST /fish extra nochmal Fisch-Bild hochladen. */
        ClassPathResource catchPathRessource = new ClassPathResource("img/catch.png");
        this.catchImage = FileCopyUtils.copyToByteArray(catchPathRessource.getInputStream());
    }

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Double getLength() {
        return length;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getCatchImage() {
        return catchImage;
    }

    public String getFish(){return fish;}

    public void setCatchImage(byte[] catchImage) {
        this.catchImage = catchImage;
    }

    public String getImgUrl() {
        return imageURL;
    }

    public void setImgUrl(String catchId) {
        this.imageURL = "http://localhost:8080/fish/image/" + catchId;
    }
}
