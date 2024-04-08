package dev.hklm.fdbackend.Controllers;

import dev.hklm.fdbackend.Entities.Catch;
import dev.hklm.fdbackend.Entities.Diary;
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
    private final DiaryRepository diaryRepository;

    public CatchController(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    // alle Fänge zurückgeben - findById(1) ist das erste Diary der beim Laden erstellt wurde
    @GetMapping("/diary")
    public List<Catch> getDiary() {
        return diaryRepository.findById(1).getCatchList();
    }

    // Information von 1 Fischart bekommen
    @GetMapping("/catch/info/{id}")
    public ResponseEntity<Catch> getCatch(@PathVariable("id") Long catchId) {
        List<Catch> catchList = diaryRepository.findById(1).getCatchList();

        Catch foundCatch = null;
        for (Catch fishCatch : catchList) {
            if (fishCatch.getId().equals(catchId)) {
                foundCatch = fishCatch;
                break;
            }
        }

        if (foundCatch == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(foundCatch, HttpStatus.OK);
    }

    // Bild von 1 Fischart bekommen (wenn keins gesetzt gibt Beispiel-Bild zurück)
    @GetMapping("/catch/image/{id}")
    public ResponseEntity<byte[]> getImageByCatchName(@PathVariable("id") Long catchId) {
        Diary diary = diaryRepository.findById(1);
        List<Catch> catchList = diary.getCatchList();

        byte[] imageData = null;
        for (Catch fishCatch : catchList) {
            if (fishCatch.getId().equals(catchId)) {
                imageData = fishCatch.getCatchImage();
                break;
            }
        }

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
    @PostMapping("/catch")
    public ResponseEntity<Object> addFish(@RequestBody Catch fishCatch) {
        Diary diary = diaryRepository.findById(1);
        diary.addCatch(fishCatch);
        diaryRepository.save(diary);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // Custom-Bild für Catch hinzufügen
    @PostMapping("/catch/upload/{id}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable("id") Long catchId) throws IOException {
        if (image.isEmpty()) {
            return ResponseEntity.badRequest().body("Image cannot be empty");
        }

        String contentType = image.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return ResponseEntity.badRequest().body("Invalid image type. Please upload an image file");
        }

        Diary diary = diaryRepository.findById(1);
        List<Catch> catchList = diary.getCatchList();

        boolean found = false;
        for (Catch fishCatch : catchList) {
            if (fishCatch.getId().equals(catchId)) {
                fishCatch.setCatchImage(image.getBytes());
                fishCatch.setImgUrl(catchId.toString());
                found = true;
                break;
            }
        }

        if (!found) {
            return ResponseEntity.badRequest().body("Fish not found in FishList");
        }

        diary.setCatchList(catchList);
        diaryRepository.save(diary);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
