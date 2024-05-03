package dev.hklm.fdbackend.Controllers;

import dev.hklm.fdbackend.Entities.Fish;
import dev.hklm.fdbackend.Services.FishService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * Controller zum Bereitstellen der API-Schnittstellen
 * für Fishdex-bedingte Aufgaben
 */
@RestController
@CrossOrigin
public class FishController {

    public FishController() {}

    @Autowired
    private FishService fishService;

    /**
     * Alle Fische zurückgeben
     */
    @GetMapping("/fishdex")
    public List<Fish> getFishdexList() {
        return fishService.getFishdexList();
    }

    /**
     * Information von 1 Fish bekommen
     * @param fishId von Frontend bereitgestellt
     */
    @GetMapping("/fish/info/{id}")
    public ResponseEntity<Fish> getFish(@PathVariable("id") Long fishId) {
        Fish fish = fishService.getFish(fishId);

        if (fish == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(fish, HttpStatus.OK);
    }

    /**
     * Bild von 1 Fish bekommen mit bestimmter ID
     * @param fishId von Frontend bereitgestellt
     */
    @GetMapping("/fish/image/{id}")
    public ResponseEntity<byte[]> getFishImageById(@PathVariable("id") Long fishId) {
        byte[] imageData = fishService.getFishImageById(fishId);

        if (imageData == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

    /**
     * JSON Fish-Objekt von Frontend zu richtigem Fish-Objekt umwandeln + hinzufügen zur Datenbank
     * @param fish - Entstandenes Fish-Objekt
     */
    @PostMapping("/fish")
    public ResponseEntity<Object> addFish(@RequestBody Fish fish) throws IOException {
        fishService.addFish(fish);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * Fish-Bild einem Fish hinzufügen
     * @param image Bilddatei
     * @param fishId von Frontend bereitgestellt
     */
    @PostMapping("/fish/upload/{id}")
    public ResponseEntity<?> uploadFishImage(@RequestParam("image") MultipartFile image, @PathVariable("id") Long fishId) throws IOException {
        Boolean check = fishService.uploadFishImage(image, fishId);
        if (!check) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
