package dev.hklm.fdbackend.Controllers;

import dev.hklm.fdbackend.Entities.Fish;
import dev.hklm.fdbackend.Entities.Fishdex;
import dev.hklm.fdbackend.Repositories.FishdexRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FishController {
    private final FishdexRepository fishdexRepository;

    public FishController(FishdexRepository fishdexRepository) {
        this.fishdexRepository = fishdexRepository;
    }

    @GetMapping("/fishdex")
    public List<Fish> getFishdex() {
        return fishdexRepository.findById(1).getFishList();
    }

    @PostMapping("/fish")
    public ResponseEntity<Object> addFish(@RequestBody Fish fish) {
        Fishdex fishdex = fishdexRepository.findById(1);
        fishdex.addFish(fish);
        fishdexRepository.save(fishdex);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
