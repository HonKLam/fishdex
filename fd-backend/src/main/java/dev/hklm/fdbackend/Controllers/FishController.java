package dev.hklm.fdbackend.Controllers;

import dev.hklm.fdbackend.Entities.Fish;
import dev.hklm.fdbackend.Entities.Fishdex;
import dev.hklm.fdbackend.Repositories.FishdexRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
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
    @GetMapping("/fish/info/{id}")
    public ResponseEntity<Fish> getFish(@PathVariable("id") Long fishId) {
        List<Fish> fishList = fishdexRepository.findById(1).getFishList();

        Fish foundFish = null;
        for (Fish fish : fishList) {
            if (fish.getId().equals(fishId)) {
                foundFish = fish;
                break;
            }
        }

        if (foundFish == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(foundFish, HttpStatus.OK);
    }

    // Bild von 1 Fischart bekommen (wenn keins gesetzt gibt Beispiel-Bild zurück)
    @GetMapping("/fish/image/{id}")
    public ResponseEntity<byte[]> getImageByFishName(@PathVariable("id") Long fishId) {
        Fishdex fishdex = fishdexRepository.findById(1);
        List<Fish> fishList = fishdex.getFishList();

        byte[] imageData = null;
        for (Fish fish : fishList) {
            if (fish.getId().equals(fishId)) {
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
    public ResponseEntity<Object> addFish(@RequestBody Fish fish) throws IOException {
        Fishdex fishdex = fishdexRepository.findById(1);
        List<Fish> fishList = fishdex.getFishList();

        // Wenn kein Bild, mach das
        if (fish.getFishImage() == null) {
            ClassPathResource fishPathRessource = new ClassPathResource("img/fish.png");
            fish.setFishImage(FileCopyUtils.copyToByteArray(fishPathRessource.getInputStream()));
        } else {
            fish.setFishImage(fish.getFishImage());
        }

        fish.setImgUrl(String.valueOf(fishList.size() + 1));
        fishdex.addFish(fish);

        fishdexRepository.save(fishdex);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // Custom-Bild für Fish hinzufügen
    @PostMapping("/fish/upload/{id}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable("id") Long fishId) throws IOException {
        if (image.isEmpty() || fishId.toString().isEmpty()) {
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
            if (fish.getId().equals(fishId)) {
                fish.setFishImage(image.getBytes());
                fish.setImgUrl(fishId.toString());
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
