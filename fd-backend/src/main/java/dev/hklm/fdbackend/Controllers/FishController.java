package dev.hklm.fdbackend.Controllers;

import dev.hklm.fdbackend.Entities.Fish;
import dev.hklm.fdbackend.Repositories.FishdexRepository;
import org.springframework.web.bind.annotation.GetMapping;
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

}
