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

    // alle Fänge zurückgeben - findById(1), ist das erste Diary der beim Laden erstellt wurde
    @GetMapping("/diary")
    public List<Catch> getDiary() { return catchService.getCatchList(); }

    // Information von 1 Catch bekommen
    @GetMapping("/catch/info/{id}")
    public ResponseEntity<Catch> getCatch(@PathVariable("id") Long catchId) {
        Catch fishCatch = catchService.getCatch(catchId);

        if (fishCatch == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(fishCatch, HttpStatus.OK);
    }

    // Information von 1 Catch bekommen
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

    // Bild von 1 Catch bekommen (wenn keins gesetzt gibt Beispiel-Bild zurück)
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

    /* Frontend sendet JSON in Form des Catchobjekts {"location":"string", ...}
     -> wird durch @RequestBody zu einem Catch-Objekt gebaut
     -> Repository holen, Catch hinzufügen, wieder speichern */
    @PostMapping("/catch")
    public ResponseEntity<Object> addCatch(@RequestBody Catch fishCatch) throws IOException {
        catchService.addCatch(fishCatch);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // Custom-Bild für Catch hinzufügen
    @PostMapping("/catch/upload/{id}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable("id") Long catchId) throws IOException {
        Boolean check = catchService.uploadCatchImage(image, catchId);
        if (!check) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
