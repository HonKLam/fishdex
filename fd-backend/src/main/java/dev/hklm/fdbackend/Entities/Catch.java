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
public class Catch {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    // Instant referenziert einen Zeitpunkt in der UTC Timeline und kann deswegen gut für Timelines benutzt werden
    // source gibt an, wie das Datum zu ermitteln ist --> SourceType.DB bedeutet, die DB gibt es an
    // Referenz: https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html
    @CreationTimestamp(source = SourceType.DB)
    private Instant createdOn;

    private String location;
    private String imageURL;
    private Double length;
    private String description;
    private Long fishId;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private byte[] catchImage;

    public Catch() {}

    public Catch(String location, Double length, String description, Long fishId, Long catchId) throws IOException {
        this.location = location;
        this.length = length;
        this.description = description;
        this.fishId = fishId;
        this.imageURL = "http://localhost:8080/catch/image/" + catchId;

        /* Die Properties müssen nicht mitgegeben werden, wenn man POST /catch im Frontend macht.
         Bitte nach POST /catch extra nochmal Fisch-Bild hochladen. */
        ClassPathResource catchPathRessource = new ClassPathResource("img/catch.png");
        this.catchImage = FileCopyUtils.copyToByteArray(catchPathRessource.getInputStream());

    }

    public Long getId() {
        return id;
    }

    public Instant getCreatedOn() {return createdOn;}

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

    public Long getFishId(){return fishId;}

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
