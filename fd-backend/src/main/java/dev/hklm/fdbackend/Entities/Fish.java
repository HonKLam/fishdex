package dev.hklm.fdbackend.Entities;

import jakarta.persistence.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

@Entity
public class Fish {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id; // id of the fish

    private Integer counter; // how many fish of this type have been caught
    private String name; // name of the fish
    private String water; // fresh, salt or both
    private String bait; // which type of bait should be used
    private Boolean edible; // is the fish edible
    private String extraInfo; // additional information about the fish
    private String imgUrl; // link to image of the fish

    // this is the IMG data typ thingy
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] fishImage;

    public Fish() {};

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

        /* Die Properties müssen nicht mitgegeben werden, wenn man POST /fish im Frontend macht.
         Bitte nach POST /fish extra nochmal Fisch-Bild hochladen. */
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
}
