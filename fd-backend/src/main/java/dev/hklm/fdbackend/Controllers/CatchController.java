package dev.hklm.fdbackend.Controllers;

import dev.hklm.fdbackend.Entities.Catch;
import dev.hklm.fdbackend.Entities.Diary;
import dev.hklm.fdbackend.Services.CatchService;
import dev.hklm.fdbackend.Repositories.DiaryRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class CatchController {

    public CatchController() {}

    @Autowired
    private CatchService catchService;

    // alle Fänge zurückgeben - findById(1) ist das erste Diary der beim Laden erstellt wurde
    @GetMapping("/diary")
    public List<Catch> getDiary() { return catchService.getCatchList(); }

    // Information von 1 Catch bekommen
    @GetMapping("/catch/info/{id}")
    public ResponseEntity<Catch> getCatch(@PathVariable("id") Long catchId) {
        Catch fishCatch = catchService.getCatch(catchId);

        if (fishCatch == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(foundCatch, HttpStatus.OK);
    }

    // Bild von 1 Catch bekommen (wenn keins gesetzt gibt Beispiel-Bild zurück)
    @GetMapping("/catch/image/{id}")
    public ResponseEntity<byte[]> getImageByCatchName(@PathVariable("id") Long catchId) {
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
    public ResponseEntity<Object> addFish(@RequestBody Catch fishCatch) {
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
