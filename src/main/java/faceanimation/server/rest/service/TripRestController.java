package faceanimation.server.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
public class TripRestController {

    @PostMapping("/test")
    public void show(@RequestBody MultipartFile file) {
        System.out.println("executing");
        System.out.println(file.getName());
        save(file);
    }

    public void save(MultipartFile file) {
        Path root = Paths.get("E:\\Coding\\first-order-model\\source_image\\");
        try {
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

}
