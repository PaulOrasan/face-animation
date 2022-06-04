package faceanimation.server.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Service {

    private final String MODEL_DIRECTORY_PATH = "E:\\Coding\\first-order-model\\";
    private final String VENV_PATH = "E:\\Programe\\Anaconda\\envs\\FOM\\python.exe";

    private String[] generateCommand(String imageName) {
        return new String[]{
                VENV_PATH,
                MODEL_DIRECTORY_PATH + "\\demo.py",
                "--config",
                MODEL_DIRECTORY_PATH + "\\config\\vox-adv-256.yaml",

        }
    }
    private void executePythonCommand() {
        String script = "E:\\Coding\\first-order-model\\demo.py --config E:\\Coding\\first-order-model\\config\\vox-adv-256.yaml --driving_video E:\\Coding\\first-order-model\\driving_video\\crop.mp4 --source_image E:\\Coding\\first-order-model\\source_image\\source_3.jpg --checkpoint E:\\Coding\\first-order-model\\checkpoints\\vox-adv-cpk.pth.tar --relative --adapt_scale";
        String venv = "E:\\Programe\\Anaconda\\envs\\FOM\\python.exe";
        String[] scriptSplit = script.split(" ");
        String[] commands = new String[scriptSplit.length + 1];
        commands[0] = venv;
        for (int i = 1; i <= scriptSplit.length; i++) {
            commands[i] = scriptSplit[i - 1];
        }
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

    public void animateImage(String imagePath) {
        executePythonCommand();
    }
}
