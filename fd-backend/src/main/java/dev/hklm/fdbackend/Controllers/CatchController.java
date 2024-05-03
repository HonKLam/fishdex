package dev.hklm.fdbackend.Controllers;

import dev.hklm.fdbackend.Entities.Catch;
import dev.hklm.fdbackend.Services.CatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller zum Bereitstellen der API-Schnittstellen
 * für Timeline-bedingte Aufgaben
 */
@RestController
@CrossOrigin
public class CatchController {

    public CatchController() {}

    @Autowired
    private CatchService catchService;

    /**
     * Alle Fänge zurückgeben
     */
    @GetMapping("/diary")
    public List<Catch> getDiary() { return catchService.getCatchList(); }


    /**
     * Information von 1 Catch bekommen
     * @param catchId catchID die von Frontend übergeben wird
     */
    @GetMapping("/catch/info/{id}")
    public ResponseEntity<Catch> getCatch(@PathVariable("id") Long catchId) {
        Catch fishCatch = catchService.getCatch(catchId);

        if (fishCatch == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(fishCatch, HttpStatus.OK);
    }

    // Information von 1 Catch bekommen

    /**
     * Liste der Fänge von einer FishId zurückgeben
     * @param fishId von Frontend bereitgestellt
     */
    @GetMapping("/catch/list/{id}")
    public ResponseEntity<List<Catch>> getCatchListByFishId(@PathVariable("id") Long fishId) {
        List<Catch> catchListByFishId = new ArrayList<>();

        for(Catch catchEntry : catchService.getCatchList()) {
            if (catchEntry.getFishId().equals(fishId)) {
                catchListByFishId.add(catchEntry);
            }
        }

        return new ResponseEntity<>(catchListByFishId, HttpStatus.OK);
    }

    /**
     * Bild von 1 Catch bekommen mit bestimmter ID
     * @param catchId von Frontend bereitgestellt
     */
    @GetMapping("/catch/image/{id}")
    public ResponseEntity<byte[]> getImageByCatchId(@PathVariable("id") Long catchId) {
        byte[] imageData = catchService.getCatchImageById(catchId);

        if (imageData == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

    /**
     * JSON Catch-Objekt von Frontend zu richtigem Catch-Objekt umwandeln + hinzufügen zur Datenbank
     * @param fishCatch - Entstandenes Catch-Objekt
     */
    @PostMapping("/catch")
    public ResponseEntity<Object> addCatch(@RequestBody Catch fishCatch) throws IOException {
        catchService.addCatch(fishCatch);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // Custom-Bild für Catch hinzufügen

    /**
     * Fang-Bild einem Catch hinzufügen
     * @param image Bilddatei
     * @param catchId von Frontend bereitgestellt
     */
    @PostMapping("/catch/upload/{id}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable("id") Long catchId) throws IOException {
        Boolean check = catchService.uploadCatchImage(image, catchId);
        if (!check) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
