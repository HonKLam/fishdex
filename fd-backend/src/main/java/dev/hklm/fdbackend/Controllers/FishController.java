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

    // alle Fischarten zurückgeben - findById(1) ist der erste Fishdex der beim Laden erstellt wurde
    @GetMapping("/fishdex")
    public List<Fish> getFishdexList() {
        return fishService.getFishdexList();
    }

    // Information von 1 Fischart bekommen
    @GetMapping("/fish/info/{id}")
    public ResponseEntity<Fish> getFish(@PathVariable("id") Long fishId) {
        Fish fish = fishService.getFish(fishId);

        if (fish == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(fish, HttpStatus.OK);
    }

    // Bild von 1 Fischart bekommen (wenn keins gesetzt gibt Beispiel-Bild zurück)
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

    /* Frontend sendet JSON in Form des Fischobjekts {"name":"string", "location":"string", ...}
     -> wird durch @RequestBody zu einem Fish-Objekt gebaut
     -> Repository holen, fish hinzufügen, wieder speichern */
    @PostMapping("/fish")
    public ResponseEntity<Object> addFish(@RequestBody Fish fish) throws IOException {
        fishService.addFish(fish);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // Custom-Bild für Fish hinzufügen
    @PostMapping("/fish/upload/{id}")
    public ResponseEntity<?> uploadFishImage(@RequestParam("image") MultipartFile image, @PathVariable("id") Long fishId) throws IOException {
        Boolean check = fishService.uploadFishImage(image, fishId);
        if (!check) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
