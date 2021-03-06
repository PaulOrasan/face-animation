package faceanimation.server.rest.service;

import faceanimation.model.AppUser;
import faceanimation.model.GeneratedVideo;
import faceanimation.service.ServiceController;
import faceanimation.utils.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
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

@CrossOrigin()
@RestController
public class FaceAnimationRestController {


    private ServiceController serviceController;

    @Autowired
    public FaceAnimationRestController(ServiceController serviceController) {
        this.serviceController = serviceController;
    }

    @PostMapping("/generate")
    public ResponseEntity<Resource> generateVideo(@RequestPart MultipartFile sourceImage, @RequestPart String expression) {
        System.out.println(expression);
        GeneratedVideo generatedVideo = serviceController.generateVideo(sourceImage, expression);

        String path = Constants.MODEL_DIRECTORY_PATH + "\\output_video\\" + generatedVideo.getFileName();
        File fileVid = new File(path);
        try {
            byte[] bytes = Base64.encodeBase64(FileUtils.readFileToByteArray(fileVid));
            return ResponseEntity.ok(new ByteArrayResource(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AppUser user) {
        System.out.println(user.getUsername());
        serviceController.registerUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/expressions")
    public ResponseEntity<List<String>> findExpressionsAvailable() {
        return ResponseEntity.ok(serviceController.findDrivingVideosAnnotations());
    }
/*
    @PostMapping("/test")
    public ResponseEntity<Resource> show(@ModelAttribute MultipartFile file) {
        System.out.println("executing");
        System.out.println(file.getName());
        String path = "E:\\Coding\\first-order-model\\output_video\\result.mp4";
        File fileVid = new File(path);
        try {
            byte[] bytes = Base64.encodeBase64(FileUtils.readFileToByteArray(fileVid));
            return ResponseEntity.ok(new ByteArrayResource(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/test2")
    public ResponseEntity<Resource> test2() {
        String path = "E:\\Coding\\first-order-model\\output_video\\result.mp4";
        File fileVid = new File(path);
        try {
            byte[] bytes = Base64.encodeBase64(FileUtils.readFileToByteArray(fileVid));
            return ResponseEntity.ok(new ByteArrayResource(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/hope")
    public ResponseEntity<Resource> work() {
        String path = "E:\\Coding\\first-order-model\\output_video\\result.mp4";
        File fileVid = new File(path);
        try {
            return ResponseEntity.ok(new ByteArrayResource(FileUtils.readFileToByteArray(fileVid)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(MultipartFile file) {
        Path root = Paths.get("E:\\Coding\\first-order-model\\source_image\\");
        try {
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }*/

}
