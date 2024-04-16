package dev.hklm.fdbackend.Services;
import dev.hklm.fdbackend.Entities.Catch;
import dev.hklm.fdbackend.Entities.Diary;
import dev.hklm.fdbackend.Entities.Fish;
import dev.hklm.fdbackend.Repositories.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class CatchService {

    @Autowired
    private DiaryRepository diaryRepository;

    public Diary getDiary() { return diaryRepository.findById(1); }

    // alle Fänge zurückgeben
    public List<Catch> getCatchList() { return this.getDiary().getCatchList(); }

    // Information von 1 Catch bekommen
    public Catch getCatch(Long catchId) {
        List<Catch> catchList = this.getCatchList();

        Catch foundCatch = null;
        for (Catch fishCatch : catchList) {
            if (fishCatch.getId().equals(catchId)) {
                foundCatch = fishCatch;
                break;
            }
        }
        return foundCatch;
    }

    // Bild von 1 Catch bekommen (wenn keins gesetzt gibt Beispiel-Bild zurück)
    public byte[] getCatchImageById(Long fishId) {
        List<Catch> catchList = this.getCatchList();

        byte[] imageData = null;
        for (Catch fishCatch : catchList) {
            if (fishCatch.getId().equals(fishId)) {
                imageData = fishCatch.getCatchImage();
                break;
            }
        }
        return imageData;
    }

    public void addCatch(Catch fishCatch) throws IOException {
        Diary diary = this.getDiary();
        FishService fishService = new FishService();

        // Wenn kein Bild, mache das
        if (fishCatch.getCatchImage() == null) {
            ClassPathResource fishPathRessource = new ClassPathResource("img/catch.png");
            fishCatch.setCatchImage(FileCopyUtils.copyToByteArray(fishPathRessource.getInputStream()));
        } else {
            fishCatch.setCatchImage(fishCatch.getCatchImage());
        }

        fishCatch.setImgUrl(String.valueOf(this.getCatchList().size() + 1));
        diary.addCatch(fishCatch);

        // Counter der Fischart erhöhen, die gefangen wurde
        fishService.increaseFishCounter(fishCatch.getFishId());

        diaryRepository.save(diary);
    }

    // Custom-Bild für Catch hinzufügen
    public Boolean uploadCatchImage(MultipartFile image, Long catchId) throws IOException {
        if (image.isEmpty() || catchId.toString().isEmpty()) {
            return false;
        }

        String contentType = image.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return false;
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
            return false;
        }

        diary.setCatchList(catchList);
        diaryRepository.save(diary);
        return true;
    }
}