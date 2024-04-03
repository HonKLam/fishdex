package dev.hklm.fdbackend.Entities;

import jakarta.persistence.*;

@Entity
public class Fish {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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

    public Fish(String name, String location, String water, Boolean caught, Integer counter, String imgUrl, byte[] fishImage) {
        this.name = name;
        this.location = location;
        this.water = water;
        this.caught = caught;
        this.counter = counter;
        this.imgUrl = imgUrl;
        this.fishImage = fishImage;
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

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
