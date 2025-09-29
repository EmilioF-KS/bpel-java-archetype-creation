package com.ksquare.archetype_creation.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;


public class ResourceCopierUtil {

    public static void copyResourceToFile(String resourcePath, String targetFilePath) throws IOException {
    	//System.out.println("resourcePath: " + resourcePath);
        try (InputStream inputStream = ResourceCopierUtil.class.getResourceAsStream(resourcePath);
             OutputStream outputStream = new FileOutputStream(targetFilePath)) {

            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    public static void copyDirectory(String source, String target) throws IOException {
    	
        	File sourceDir = new File(source); // Replace with your source directory
            File targetDir = new File(target); // Replace with your target directory

            try {
                FileUtils.copyDirectory(sourceDir, targetDir);
                System.out.println("Directory copied successfully!");
            } catch (IOException e) {
                System.err.println("Error copying directory: " + e.getMessage());
                e.printStackTrace();
            }
    }
    
    public static void main(String[] args) {
        try {
            // Example usage: copy "myResource.txt" from resources to a new file named "copiedResource.txt"
            copyResourceToFile("/resources/templates/application.yml", "C:\\KSquare projects\\Chubb-ibm\\_NEW_PROJECTS\\copiedResource.yml");
            System.out.println("Resource copied successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}