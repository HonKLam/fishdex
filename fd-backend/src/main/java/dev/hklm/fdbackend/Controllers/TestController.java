package dev.hklm.fdbackend.Controllers;

import dev.hklm.fdbackend.Entities.TestEntity;
import dev.hklm.fdbackend.Repositories.TestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Controller sind da, um die API-Schnittstellen zu beschreiben
@RestController
public class TestController {
    // Hierüber haben wir dann Zugriff auf die Datenbank
    private final TestRepository testRepository;

    public TestController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @GetMapping("/")
    public String index() {
        return "Hello from Spring Boot Application!";
    }

    // Testuser, die vorher reingeladen worden hier ausgeben
    @GetMapping("/testuser")
    public List<TestEntity> getAllUsers() {
        List<TestEntity> userList = new ArrayList<>();
        testRepository.findAll().forEach(userList::add);
        return userList;
    }

    @PostMapping("/user")
    public ResponseEntity<Object> addUser(@RequestBody TestEntity testEntity) {
        testRepository.save(testEntity);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
