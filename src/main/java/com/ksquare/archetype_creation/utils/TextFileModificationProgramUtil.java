package com.ksquare.archetype_creation.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
 
public class TextFileModificationProgramUtil
{   
    public static void modifyFile(String filePath, String oldString, String newString)
    {
    	//System.out.println("-> modifyFile");
    	//System.out.println(": filePath: " + filePath);
    	//System.out.println(": oldString: " + oldString);
    	//System.out.println(": newString: " + newString);
    	
        File fileToBeModified = new File(filePath);
         
        String oldContent = "";
         
        BufferedReader reader = null;
         
        FileWriter writer = null;
         
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
             
            //Reading all the lines of input text file into oldContent
             
            String line = reader.readLine();
             
            while (line != null) 
            {
                oldContent = oldContent + line + System.lineSeparator();
                 
                line = reader.readLine();
            }
             
            //Replacing oldString with newString in the oldContent
            
            String newContent = oldContent.replaceAll(oldString, newString);
            /*if (oldContent.contains(oldString)) {
            	System.out.println(": oldContent: " + oldContent);
            	System.out.println(": newContent: " + newContent);
            } */
            //Rewriting the input text file with newContent
             
            writer = new FileWriter(fileToBeModified);
             
            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources
                 
                reader.close();
                 
                writer.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
     
    public static void main(String[] args)
    {
        modifyFile("src/main/java/resources/templates/Application", "packagetoupdate", "com.example.service.order");
         
        System.out.println("done");
    }
}