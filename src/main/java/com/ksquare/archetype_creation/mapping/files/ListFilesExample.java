package com.ksquare.archetype_creation.mapping.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListFilesExample {
    public static void main(String[] args) {
    	System.out.println("-> ListFilesExample");
        String directoryPath = DecompilerRunner.CLASS_PATH + "\\com\\productcache\\webservice\\mapping\\"; // Replace with your directory path
        System.out.println(": directoryPath : " + directoryPath);
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) { // Check if it's a file, not a subdirectory
                        System.out.println(file.getName());
                    }
                }
            }
        } else {
            System.out.println("Directory does not exist or is not a directory.");
        }
    }
    
    public static List<String> getClassFiles(String classPath) {
    	System.out.println("-> ListFilesExample");
        //String directoryPath = DecompilerRunner.CLASS_PATH + "\\com\\productcache\\webservice\\mapping\\"; // Replace with your directory path
        System.out.println(": classPath : " + classPath);
        File directory = new File(classPath);
        List<String> classFilesArray = new ArrayList<String>();

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) { // Check if it's a file, not a subdirectory
                    	classFilesArray.add(classPath + file.getName());
                        System.out.println(classPath + file.getName());
                    }
                }
            }
        } else {
            System.out.println("Directory does not exist or is not a directory.");
        }
        
        System.out.println(": classFilesArray : " + classFilesArray);
        
        return classFilesArray;
    }
}