package dev.hklm.fdbackend.Controllers;


import dev.hklm.fdbackend.Entities.Catch;
import dev.hklm.fdbackend.Services.UserService;
import dev.hklm.fdbackend.Entities.User;
import dev.hklm.fdbackend.Entities.Userboard;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    public UserController() {}

    @Autowired
    private UserService userService;


    // alle User zurückgeben - findById(1), ist der erste User der beim Laden erstellt wurde
    @GetMapping("/user")
    public List<User> getUserboard() { return userService.getUserList(); }

    // Information von User bekommen
    @GetMapping("/user/info/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long uid) {
        User user = userService.getUser(uid);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Bild vom User bekommen (wenn keins gesetzt gibt Beispiel-Bild zurück)
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

    /* Frontend sendet JSON in Form des Userobjekts {"location":"string", ...}
     -> wird durch @RequestBody zu einem User-Objekt gebaut
     -> Repository holen, User hinzufügen, wieder speichern */
    @PostMapping("/user")
    public ResponseEntity<Object> addUser(@RequestBody User user) throws IOException {
        userService.addUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // eigenes custom User-Bild hinzufügen
    @PostMapping("/user/upload/{id}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable("id") Long uid) throws IOException {
        Boolean check = userService.uploadUserImage(image, uid);
        if (!check) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
