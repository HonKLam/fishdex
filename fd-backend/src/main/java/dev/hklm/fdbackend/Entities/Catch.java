package dev.hklm.fdbackend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.ZonedDateTime;

@Entity
public class Catch {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    // ZonedDateTime gibt Moment an in einer bestimmten Zeitzone
    // source gibt an, wie das Datum zu ermitteln ist --> SourceType.DB bedeutet, die DB gibt es an
    //Erklärung zu ZonedDateTime: https://stackoverflow.com/questions/32437550/whats-the-difference-between-instant-and-localdatetime
    @CreationTimestamp(source = SourceType.DB)
    private ZonedDateTime createdOn;

    private ZonedDateTime catchDate;
    private String location;
    private String imageURL;
    private Double length;
    private Double weight;
    private String description;
    private Long fishId;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private byte[] catchImage;

    public Catch() {}

    // ZonedDateTime kann folgendermaßen angelegt werden:
    // ZonedDateTime.parse("2024-04-02T01:10:22+02[Europe/Berlin]")
    // ZonedDateTime.of(2015, 11, 30, 23, 45, 59, 0, ZoneId.of("GMT+02:00")
    public Catch(String location, Double length, String description, ZonedDateTime catchDate, Long fishId, Long catchId) throws IOException {
        this.location = location;
        this.length = length;
        this.weight = weight;
        this.description = description;
        this.catchDate = catchDate;
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

    public ZonedDateTime getCatchDate() {return catchDate;}

    public void setCatchDate(ZonedDateTime catchDate) {this.catchDate = catchDate;}

    public ZonedDateTime getCreatedOn(){return createdOn;}

    public String getLocation() {
        return location;
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
        this.imageURL = "http://localhost:8080/catch/image/" + catchId;
    }

    public Double getWeight() {
        return weight;
    }
}
