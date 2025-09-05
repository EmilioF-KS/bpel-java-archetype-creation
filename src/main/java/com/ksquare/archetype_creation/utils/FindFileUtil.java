package com.ksquare.archetype_creation.utils;

import java.io.File;

public class FindFileUtil {
    public static String findFile(String directoryPath) {
        //String directoryPath = "C:\\KSquare projects\\Chubb-ibm\\IMPORTED_WS\\Product Availability\\webservice\\"; // Replace with your directory path
        String fileNameToFind = "package-info.java"; // Replace with the name of the file you're looking for

        File directory = new File(directoryPath);
        String foundFile = null;

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles(); // Get all files and directories in the path

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().equals(fileNameToFind)) {
                    	foundFile = file.getAbsolutePath();
                        System.out.println("Found file: " + foundFile);
                        return foundFile; // Exit after finding the first match
                    }
                }
                System.out.println("File not found: " + fileNameToFind);
            } else {
                System.out.println("No files found in directory: " + directoryPath);
            }
        } else {
            System.out.println("Directory does not exist or is not a directory: " + directoryPath);
        }
        
        return foundFile;
    }
    
    public static void main(String[] args) {
        String directoryPath = "C:\\KSquare projects\\Chubb-ibm\\IMPORTED_WS\\Product Availability\\webservice\\"; // Replace with your directory path
        String fileNameToFind = "package-info.java"; // Replace with the name of the file you're looking for

        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles(); // Get all files and directories in the path

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().equals(fileNameToFind)) {
                        System.out.println("Found file: " + file.getAbsolutePath());
                        return; // Exit after finding the first match
                    }
                }
                System.out.println("File not found: " + fileNameToFind);
            } else {
                System.out.println("No files found in directory: " + directoryPath);
            }
        } else {
            System.out.println("Directory does not exist or is not a directory: " + directoryPath);
        }
    }
}