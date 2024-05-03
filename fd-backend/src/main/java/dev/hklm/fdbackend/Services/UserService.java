package dev.hklm.fdbackend.Services;
import dev.hklm.fdbackend.Entities.User;
import dev.hklm.fdbackend.Entities.Userboard;
import dev.hklm.fdbackend.Repositories.UserboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


/**
 * Service zum Behandeln der Anfragen vom UserController
 */
@Service
public class UserService {

    @Autowired
    private UserboardRepository userboardRepository; // Datenbank

    public Userboard getUserboard() { return userboardRepository.findById(1); }

    // alle User ausgeben
    public List<User> getUserList() { return this.getUserboard().getUserList(); }

    // Information von einem User bekommen
    public User getUser(Long uid) {
        List<User> userList = this.getUserList();

        User foundUser = null;
        for (User user : userList) {
            if (user.getId().equals(uid)) {
                foundUser = user;
                break;
            }
        }
        return foundUser;
    }

    // Userbild bekommen (wenn keins gesetzt gibt Beispiel-Bild zurück)
    public byte[] getUserImageById(Long uid) {
        List<User> userList = this.getUserList();

        byte[] imageData = null;
        for (User user : userList) {
            if (user.getId().equals(uid)) {
                imageData = user.getUserImage();
                break;
            }
        }
        return imageData;
    }

    // User hinzufügen
    public void addUser(User user) throws IOException {
        Userboard userboard = this.getUserboard();
        // Wenn kein Bild, mache das
        if (user.getUserImage() == null) {
            ClassPathResource userPathRessource = new ClassPathResource("img/user.png");
            user.setUserImage(FileCopyUtils.copyToByteArray(userPathRessource.getInputStream()));
        } else {
            user.setUserImage(user.getUserImage());
        }

        user.setImgUrl(String.valueOf(this.getUserList().size() + 1));

        userboard.addUser(user);
        userboardRepository.save(userboard);
    }

    // Custom-Bild für Catch hinzufügen
    public Boolean uploadUserImage(MultipartFile image, Long uid) throws IOException {
        if (image.isEmpty() || uid.toString().isEmpty()) {
            return false;
        }

        String contentType = image.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return false;
        }

        Userboard userboard = userboardRepository.findById(1);
        List<User> userList = userboard.getUserList();

        boolean found = false;
        for (User user : userList) {
            if (user.getId().equals(uid)) {
                user.setUserImage(image.getBytes());
                user.setImgUrl(uid.toString());
                found = true;
                break;
            }
        }

        if (!found) {
            return false;
        }

        userboard.setUserList(userList);
        userboardRepository.save(userboard);
        return true;
    }
}