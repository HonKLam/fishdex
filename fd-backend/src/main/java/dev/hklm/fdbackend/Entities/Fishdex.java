package dev.hklm.fdbackend.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Fishdex {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    // Sag der Datenbank, dass die Liste an Fischen Java-Entities sind > sollen auch gespeichert werden
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Fish> fishList = new ArrayList<>();

    protected Fishdex() {}

    public Fishdex(List<Fish> fishList) {
        this.fishList = fishList;
    }

    @Override
    public String toString() {
        return String.format(
                "Fishdex[id=%d, fishList='%s']",
                id, fishList.toString());
    }

    public List<Fish> getFishList() {
        return fishList;
    }

    public void setFishList(List<Fish> fishList) {
        this.fishList = fishList;
    }

    public void addFish(Fish fish) {
        fishList.add(fish);
    }
}
