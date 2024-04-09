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

@Service
public class FishService {

    @Autowired
    private FishdexRepository fishdexRepository;

    public List<Fish> getFishdex() {
        return fishdexRepository.findById(1).getFishList();
    }

    public Fish getFish(Long fishId) {
        List<Fish> fishList = fishdexRepository.findById(1).getFishList();

        Fish foundFish = null;
        for (Fish fish : fishList) {
            if (fish.getId().equals(fishId)) {
                foundFish = fish;
                break;
            }
        }
        return foundFish;

    }

    public byte[] getFishImageById(Long fishId) {
        List<Fish> fishList = fishdexRepository.findById(1).getFishList();

        byte[] imageData = null;
        for (Fish fish : fishList) {
            if (fish.getId().equals(fishId)) {
                imageData = fish.getFishImage();
                break;
            }
        }
        return imageData;
    }

    public void addFish(Fish fish) throws IOException {
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
    }

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




}
