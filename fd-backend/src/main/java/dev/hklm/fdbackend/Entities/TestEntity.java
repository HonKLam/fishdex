package dev.hklm.fdbackend.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TestEntity {
    // Erstellt für jede Zeile in Tabelle ein Schlüsselattribut ergo ID
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer age;
    private String location;

    // Das muss sein, weil Spring es so will
    public TestEntity() {}

    // Eigenen Konstruktor trz möglich
    public TestEntity(String name, Integer age, String location) {
        this.name = name;
        this.age = age;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getLocation() {
        return location;
    }
}
