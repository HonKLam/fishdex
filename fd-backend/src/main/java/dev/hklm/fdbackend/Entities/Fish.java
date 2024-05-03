package dev.hklm.fdbackend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

@Entity
public class Fish {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id; // Von Datenbank erstellt

    private Integer counter;
    private String name;
    private String water;
    private String bait;
    private Boolean edible;
    private String extraInfo;
    private String imgUrl;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private byte[] fishImage;

    public Fish() {};

    /**
     * Fischart
     * @param counter Wie viele Fische bisher gefangen wurden
     * @param name Name der Fischart
     * @param water Welches Gewässer
     * @param bait Welcher Köder wird benutzt
     * @param edible Essbar
     * @param extraInfo Beschreibung
     * @param fishId FishId (generiert von Datenabank)
     *
     */
    public Fish(
            Integer counter,
            String name,
            String water,
            String bait,
            Boolean edible,
            String extraInfo,
            Long fishId
    ) throws IOException {
        this.counter = counter;
        this.name = name;
        this.water = water;
        this.bait = bait;
        this.edible = edible;
        this.extraInfo = extraInfo;
        this.imgUrl = "http://localhost:8080/fish/image/" + fishId.toString();

        // Standard-Bild
        ClassPathResource fishPathRessource = new ClassPathResource("img/fish.png");
        this.fishImage = FileCopyUtils.copyToByteArray(fishPathRessource.getInputStream());
    }

    public Integer getCounter() { return counter; }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWater() {
        return water;
    }

    public String getBait() { return bait; }

    public Boolean getEdible() { return edible; }

    public String getExtraInfo() { return extraInfo; }

    public String getImgURL() { return imgUrl; }

    public byte[] getFishImage() {
        return fishImage;
    }

    public void setFishImage(byte[] fishImage) {
        this.fishImage = fishImage;
    }

    public void setImgUrl(String fishId) {
        this.imgUrl = "http://localhost:8080/fish/image/" + fishId;
    }

    public void setCounter(Integer counter) {this.counter = counter;}
}
