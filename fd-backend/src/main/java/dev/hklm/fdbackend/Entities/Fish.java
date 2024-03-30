package dev.hklm.fdbackend.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

    public Fish() {};

    public Fish(String name, String location, String water, Boolean caught, Integer counter) {
        this.name = name;
        this.location = location;
        this.water = water;
        this.caught = caught;
        this.counter = counter;
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
}
