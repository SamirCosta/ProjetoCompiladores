package utils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class JavaRunner {
    public static StringBuilder javaCode = new StringBuilder();

    public static void run() {

        try {
            saveJavaCodeToFile(javaCode.toString(), "GeneratedCode.java");

            compileJavaFile("GeneratedCode.java");

            executeJavaClass("GeneratedCode");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void saveJavaCodeToFile(String code, String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(code);
        writer.close();
    }

    private static void compileJavaFile(String fileName) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("javac", fileName);
        Process process = processBuilder.start();
        process.waitFor();

        if (process.exitValue() != 0) {
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while ((line = errorReader.readLine()) != null) {
                System.err.println(line);
            }
            throw new RuntimeException("Erro ao compilar o arquivo " + fileName);
        }
    }

    private static void executeJavaClass(String className) throws IOException, InterruptedException {
        String scriptContent = "java " + className + " & pause";
        Path tempScript = Files.createTempFile("runScript", ".bat");
        Files.writeString(tempScript, scriptContent);
        new ProcessBuilder("cmd", "/c", "start", tempScript.toAbsolutePath().toString()).start();
    }
}
