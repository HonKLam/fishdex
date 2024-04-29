package dev.hklm.fdbackend.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Diary {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    // Sag der Datenbank, dass die Liste an Fängen Java-Entities sind > sollen auch gespeichert werden
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Catch> catchList = new ArrayList<>();

    protected Diary() {}

    public Diary(List<Catch> catchList) {
        this.catchList = catchList;
    }

    @Override
    public String toString() {
        return String.format(
                "Diary[id=%d, catchList='%s']",
                id, catchList.toString());
    }

    public List<Catch> getCatchList() {
        return catchList;
    }

    public void setCatchList(List<Catch> catchList) {
        this.catchList = catchList;
    }

    public void addCatch(Catch fishCatch) {
        catchList.add(fishCatch);
    }
}
