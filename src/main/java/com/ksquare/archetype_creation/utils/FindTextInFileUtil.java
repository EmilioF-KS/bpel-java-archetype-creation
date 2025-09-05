package com.ksquare.archetype_creation.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FindTextInFileUtil {

    public static String findTextInFile(String filePath) {
        //String filePath = "C:\\KSquare projects\\Chubb-ibm\\IMPORTED_WS\\Product Availability\\webservice\\package-info.java"; // Replace with the actual file path
        String searchText = "package"; // Replace with the text you want to find
        String packagePath = null;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            boolean found = false;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.contains(searchText)) {
                    System.out.println("Text found on line " + lineNumber + ": " + line);
                    found = true;
                    // If you only need to find the first occurrence, you can break here
                    // break; 
                    packagePath = line.substring(8, line.length() - 1);
                    System.out.println("Package Path: *" + packagePath + "*");
                    return packagePath;
                }
            }

            if (!found) {
                System.out.println("Text '" + searchText + "' not found in the file.");
            }

        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        
        return packagePath;
    }
    
    public static void main(String[] args) {
        String filePath = "C:\\KSquare projects\\Chubb-ibm\\IMPORTED_WS\\Product Availability\\webservice\\package-info.java"; // Replace with the actual file path
        String searchText = "package"; // Replace with the text you want to find

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            boolean found = false;
            String packagePath = null;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.contains(searchText)) {
                    System.out.println("Text found on line " + lineNumber + ": " + line);
                    found = true;
                    // If you only need to find the first occurrence, you can break here
                    // break; 
                    packagePath = line.substring(8, line.length() - 1);
                    System.out.println("Package Path: *" + packagePath + "*");
                }
            }

            if (!found) {
                System.out.println("Text '" + searchText + "' not found in the file.");
            }

        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}