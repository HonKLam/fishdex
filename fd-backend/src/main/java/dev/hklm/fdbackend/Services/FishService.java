package dev.hklm.fdbackend.Services;
import dev.hklm.fdbackend.Entities.Fish;
import dev.hklm.fdbackend.Entities.Fishdex;
import dev.hklm.fdbackend.Repositories.FishdexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Service zum Behandeln der Anfragen vom FishController
 */
@Service
public class FishService {

    @Autowired
    private FishdexRepository fishdexRepository; // Datenbank

    public Fishdex getFishdex() {
        return fishdexRepository.findById(1);
    }

    public List<Fish> getFishdexList() {
        return this.getFishdex().getFishList();
    }

    // Fish bekommen
    public Fish getFish(Long fishId) {
        List<Fish> fishList = this.getFishdexList();

        Fish foundFish = null;
        for (Fish fish : fishList) {
            if (fish.getId().equals(fishId)) {
                foundFish = fish;
                break;
            }
        }
        return foundFish;
    }

    // Bild bekommen mit Id
    public byte[] getFishImageById(Long fishId) {
        List<Fish> fishList = this.getFishdexList();

        byte[] imageData = null;
        for (Fish fish : fishList) {
            if (fish.getId().equals(fishId)) {
                imageData = fish.getFishImage();
                break;
            }
        }
        return imageData;
    }

    // Fish hinzufügen
    public void addFish(Fish fish) throws IOException {
        Fishdex fishdex = this.getFishdex();

        // Wenn kein Bild, mach das
        if (fish.getFishImage() == null) {
            ClassPathResource fishPathRessource = new ClassPathResource("img/fish.png");
            fish.setFishImage(FileCopyUtils.copyToByteArray(fishPathRessource.getInputStream()));
        } else {
            fish.setFishImage(fish.getFishImage());
        }

        fish.setImgUrl(String.valueOf(this.getFishdexList().size() + 1));
        fishdex.addFish(fish);

        fishdexRepository.save(fishdex);
    }

    // Bild hochladen
    public Boolean uploadFishImage(MultipartFile image, Long fishId) throws IOException {
        if (image.isEmpty() || fishId.toString().isEmpty()) {
            return false;
        }

        String contentType = image.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return false;
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
            return false;
        }

        fishdex.setFishList(fishList);
        fishdexRepository.save(fishdex);
        return true;
    }

    // Counter erhöhen
    public void increaseFishCounter(Long fishId) {
        Fish fish = this.getFish(fishId);

        Integer increasedCounter = fish.getCounter() + 1;
        fish.setCounter(increasedCounter);

        fishdexRepository.save(this.getFishdex());
    }
}
