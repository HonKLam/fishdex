package dev.hklm.fdbackend.Controllers;

import dev.hklm.fdbackend.Entities.Fish;
import dev.hklm.fdbackend.Entities.Fishdex;
import dev.hklm.fdbackend.Repositories.FishdexRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class FishController {
    private final FishdexRepository fishdexRepository;

    public FishController(FishdexRepository fishdexRepository) {
        this.fishdexRepository = fishdexRepository;
    }

    // alle Fischarten zurückgeben - findById(1) ist der erste Fishdex der beim Laden erstellt wurde
    @GetMapping("/fishdex")
    public List<Fish> getFishdex() {
        return fishdexRepository.findById(1).getFishList();
    }

    // Information von 1 Fischart bekommen
    @GetMapping("/fish/info/{name}")
    public ResponseEntity<Fish> getFish(@PathVariable("name") String fishName) {
        List<Fish> fishList = fishdexRepository.findById(1).getFishList();

        Fish foundFish = null;
        for (Fish fish : fishList) {
            if (fish.getName().equals(fishName)) {
                foundFish = fish;
            }
        }

        if (foundFish == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(foundFish, HttpStatus.OK);
    }

    // Bild von 1 Fischart bekommen (wenn keins gesetzt gibt Beispiel-Bild zurück)
    @GetMapping("/fish/image/{name}")
    public ResponseEntity<byte[]> getImageByFishName(@PathVariable("name") String fishName) {
        Fishdex fishdex = fishdexRepository.findById(1);
        List<Fish> fishList = fishdex.getFishList();

        byte[] imageData = null;
        for (Fish fish : fishList) {
            if (fish.getName().equals(fishName)) {
                imageData = fish.getFishImage();
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
    @PostMapping("/fish")
    public ResponseEntity<Object> addFish(@RequestBody Fish fish) {
        Fishdex fishdex = fishdexRepository.findById(1);
        fishdex.addFish(fish);
        fishdexRepository.save(fishdex);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // Custom-Bild für Fish hinzufügen
    @PostMapping("/fish/upload/{name}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable("name") String fishName) throws IOException {
        if (image.isEmpty() || fishName.isEmpty()) {
            return ResponseEntity.badRequest().body("Image or fish name cannot be empty");
        }

        String contentType = image.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return ResponseEntity.badRequest().body("Invalid image type. Please upload an image file");
        }

        Fishdex fishdex = fishdexRepository.findById(1);
        List<Fish> fishList = fishdex.getFishList();

        boolean found = false;
        for (Fish fish : fishList) {
            if (fish.getName().equals(fishName)) {
                fish.setFishImage(image.getBytes());
                fish.setImgUrl(fishName);
                found = true;
                break;
            }
        }

        if (!found) {
            return ResponseEntity.badRequest().body("Fish not found in FishList");
        }

        fishdex.setFishList(fishList);
        fishdexRepository.save(fishdex);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
