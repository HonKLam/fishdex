package dev.hklm.fdbackend.Entities;

import jakarta.persistence.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

@Entity
public class Fish {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;
    private String location;
    private String water;
    private Boolean caught;
    private Integer counter;
    private String imgUrl;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] fishImage;

    public Fish() {};

    public Fish(String name, String location, String water, Boolean caught, Integer counter, String fishId) throws IOException {
        this.name = name;
        this.location = location;
        this.water = water;
        this.caught = caught;
        this.counter = counter;
        this.imgUrl = "http://localhost:8080/fish/image/" + fishId;

        /* Die Properties müssen nicht mitgegeben werden, wenn man POST /fish im Frontend macht.
         Bitte nach POST /fish extra nochmal Fisch-Bild hochladen. */
        ClassPathResource fishPathRessource = new ClassPathResource("img/fish.png");
        this.fishImage = FileCopyUtils.copyToByteArray(fishPathRessource.getInputStream());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getWater() {
        return water;
    }

    public Boolean getCaught() {
        return caught;
    }

    public Integer getCounter() {
        return counter;
    }

    public byte[] getFishImage() {
        return fishImage;
    }

    public void setFishImage(byte[] fishImage) {
        this.fishImage = fishImage;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String fishId) throws IOException {
        this.imgUrl = "http://localhost:8080/fish/image/" + fishId;
    }
}
