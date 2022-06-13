package faceanimation.service;

import faceanimation.model.DrivingVideo;
import faceanimation.model.GeneratedVideo;
import faceanimation.model.SourceImage;
import faceanimation.persistence.GeneratedVideoRepository;
import faceanimation.utils.Constants;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class GeneratedVideoService {

    private GeneratedVideoRepository generatedVideoRepository;

    @Autowired
    public GeneratedVideoService(GeneratedVideoRepository generatedVideoRepository) {
        this.generatedVideoRepository = generatedVideoRepository;
    }

    private String[] generatePythonCommand(String imageName, String drivingVideoName, String generatedVideoName) {
        return new String[]{
                Constants.VENV_PATH,
                Constants.MODEL_DIRECTORY_PATH + "\\demo.py",
                "--config",
                Constants.MODEL_DIRECTORY_PATH + "\\config\\vox-adv-256.yaml",
                "--driving_video",
                Constants.MODEL_DIRECTORY_PATH + "\\driving_video\\" + drivingVideoName,
                "--source_image",
                Constants.MODEL_DIRECTORY_PATH + "\\source_image\\" + imageName,
                "--result_video",
                Constants.MODEL_DIRECTORY_PATH + "\\output_video\\" + generatedVideoName,
                "--checkpoint",
                Constants.MODEL_DIRECTORY_PATH + "\\checkpoints\\vox-adv-cpk.pth.tar",
                "--relative",
                "--adapt-scale"
        };
    }

    private void executeCommand(String[] commands) {
        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        processBuilder.redirectErrorStream(true);

        Process process = null;
        try {
            process = processBuilder.start();
            InputStream stdout = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
            String line;
            try{
                while((line = reader.readLine()) != null){
                    System.out.println("stdout: "+ line);
                }
            }catch(IOException e){
                System.out.println("Exception in reading output"+ e.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GeneratedVideo generateVideo(SourceImage sourceImage, DrivingVideo drivingVideo) {
        GeneratedVideo generatedVideo = GeneratedVideo.builder()
                        .sourceImage(sourceImage)
                        .drivingVideo(drivingVideo).dateTimeProduced(LocalDateTime.now())
                        .fileName(FilenameUtils.getBaseName(sourceImage.getFileName()) + "_" +
                                FilenameUtils.getBaseName(drivingVideo.getFileName()) + ".mp4")
                        .build();
        executeCommand(generatePythonCommand(sourceImage.getFileName(), drivingVideo.getFileName(),generatedVideo.getFileName()));
        return generatedVideoRepository.save(generatedVideo);
    }
}
