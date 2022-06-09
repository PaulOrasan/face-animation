package faceanimation.service;

import faceanimation.model.User;
import faceanimation.persistence.DrivingVideoRepository;
import faceanimation.persistence.GeneratedVideoRepository;
import faceanimation.persistence.SourceImageRepository;
import faceanimation.persistence.UserRepository;
import faceanimation.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class ServiceController {

    private DrivingVideoRepository drivingVideoRepository;
    private GeneratedVideoRepository generatedVideoRepository;
    private UserRepository<User> userRepository;

    private SourceImageService sourceImageService;

    @Autowired
    public ServiceController(DrivingVideoRepository drivingVideoRepository, GeneratedVideoRepository generatedVideoRepository,
                             UserRepository<User> userRepository, SourceImageService sourceImageService) {
        this.drivingVideoRepository = drivingVideoRepository;
        this.generatedVideoRepository = generatedVideoRepository;
        this.userRepository = userRepository;
        this.sourceImageService = sourceImageService;
    }

    private String[] generateCommand(String imageName, String videoName) {
        return new String[]{
                Constants.VENV_PATH,
                Constants.MODEL_DIRECTORY_PATH + "\\demo.py",
                "--config",
                Constants.MODEL_DIRECTORY_PATH + "\\config\\vox-adv-256.yaml",
                "--driving_video",
                Constants.MODEL_DIRECTORY_PATH + "\\driving_video\\" + videoName,
                "--source_image",
                Constants.MODEL_DIRECTORY_PATH + "\\source_image\\" + imageName,
                "--result_video",
                Constants.MODEL_DIRECTORY_PATH + "\\output_video\\" + imageName + "_" + videoName + ".mp4",
                "--checkpoint",
                Constants.MODEL_DIRECTORY_PATH + "\\checkpoints\\vox-adv-cpk.pth.tar",
                "--relative",
                "--adapt-scale"
        };
    }
    private void executePythonCommand(String imageName, String videoName) {
//        String script = "E:\\Coding\\first-order-model\\demo.py --config E:\\Coding\\first-order-model\\config\\vox-adv-256.yaml --driving_video E:\\Coding\\first-order-model\\driving_video\\crop.mp4 --source_image E:\\Coding\\first-order-model\\source_image\\source_3.jpg --checkpoint E:\\Coding\\first-order-model\\checkpoints\\vox-adv-cpk.pth.tar --relative --adapt_scale";
//        String venv = "E:\\Programe\\Anaconda\\envs\\FOM\\python.exe";
//        String[] scriptSplit = script.split(" ");
//        String[] commands = new String[scriptSplit.length + 1];
//        commands[0] = venv;
//        for (int i = 1; i <= scriptSplit.length; i++) {
//            commands[i] = scriptSplit[i - 1];
//        }
//        try {
//            Process process = Runtime.getRuntime().exec(venv + " " + script);
//            System.out.println("called");
//            InputStream stdout = process.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, StandardCharsets.UTF_8));
//            String line;
//            try{
//                while((line = reader.readLine()) != null){
//                    System.out.println("stdout: "+ line);
//                }
//            }catch(IOException e){
//                System.out.println("Exception in reading output"+ e.toString());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String[] commands = generateCommand(imageName, videoName);
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

    public void animateImage(String imageName, String videoName) {
        executePythonCommand(imageName, videoName);
    }

    public void saveSourceImage(MultipartFile sourceImage) {
        sourceImageService.saveSourceImage(sourceImage);
    }
}
