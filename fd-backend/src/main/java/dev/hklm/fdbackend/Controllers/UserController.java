package dev.hklm.fdbackend.Controllers;

import dev.hklm.fdbackend.Services.UserService;
import dev.hklm.fdbackend.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Controller zum Bereitstellen der API-Schnittstellen
 * für Nutzer-bedingte Aufgaben
 */
@RestController
@CrossOrigin
public class UserController {

    public UserController() {}

    @Autowired
    private UserService userService;

    /**
     * Alle User zurückgeben
     */
    @GetMapping("/user")
    public List<User> getUserboard() { return userService.getUserList(); }

    /**
     * Information von 1 User bekommen
     * @param uid von Frontend bereitgestellt
     */
    @GetMapping("/user/info/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long uid) {
        User user = userService.getUser(uid);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Bild von 1 User bekommen mit bestimmter ID
     * @param uid von Frontend bereitgestellt
     */
    @GetMapping("/user/image/{id}")
    public ResponseEntity<byte[]> getImageByCatchId(@PathVariable("id") Long uid) {
        byte[] imageData = userService.getUserImageById(uid);

        if (imageData == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

    /**
     * JSON User-Objekt von Frontend zu richtigem User-Objekt umwandeln + hinzufügen zur Datenbank
     * @param user - Entstandenes User-Objekt
     */
    @PostMapping("/user")
    public ResponseEntity<Object> addUser(@RequestBody User user) throws IOException {
        userService.addUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    /**
     * User-Bild einem User hinzufügen
     * @param image Bilddatei
     * @param uid von Frontend bereitgestellt
     */
    @PostMapping("/user/upload/{id}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable("id") Long uid) throws IOException {
        Boolean check = userService.uploadUserImage(image, uid);
        if (!check) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
