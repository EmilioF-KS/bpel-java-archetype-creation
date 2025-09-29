package com.ksquare.archetype_creation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.LoggerFactory;

import com.chubb.converter.bpel.scriptsbody.BPELToCamelGenerator;
import com.ksquare.archetype_creation.utils.Constants;
import com.ksquare.archetype_creation.utils.CreateFolderUtil;

public class MicroserviceGenerator {

	private static final String SERVICE_SPEC_PATH = "C:\\KSquare projects\\Chubb-ibm\\RATNA_CODE\\microservice-generator\\microservice-generator\\input\\dynamic_input.json";
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MicroserviceGenerator.class);

    public static void main(String[] args) {
    	LOGGER.info("-> MicroserviceGenerator");
    	
    	InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

    	System.out.print("Please enter your LOC type: ");
    	
    	String locType = null;
    	/*
    	try {
            // Read a line of text from the console
    		locType = br.readLine();
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        } finally {
            // Close the BufferedReader to release system resources
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing BufferedReader: " + e.getMessage());
            }
        }*/
    	
    	locType = "LOC3X1";
    	
        String COMPLETE_PATH = null;
        String bpelFileName = null;
        
        if ("LOC3X1".equalsIgnoreCase(locType)) {
            //LOC3X1
        	LOGGER.info("Processing LOC3X1.....");
        	
            COMPLETE_PATH = "C:\\CHUBB\\LocationServices\\";
        	bpelFileName = "LocationRetrievalLOC3X1Process"; 
        	
        } else if ("LOC3X2".equalsIgnoreCase(locType)) {
            //LOC3X2
        	LOGGER.info("Processing LOC3X2.....");
        	
        	COMPLETE_PATH = "C:\\CHUBB\\LocationServices\\";
        	bpelFileName = "LocationRetrievalLOC3X2Process";        	
        } else if ("LOC3X3".equalsIgnoreCase(locType)) {
            //LOC3X3
        	LOGGER.info("Processing LOC3X3.....");
        	
        	COMPLETE_PATH = "C:\\CHUBB\\LocationServices\\LOC3X3\\";
        	bpelFileName = "LocationRetrievalLOC3X3Process";        	
        } if ("LOC3X4".equalsIgnoreCase(locType)) {
            //LOC3X4
        	LOGGER.info("Processing LOC3X4.....");
        	
        	COMPLETE_PATH = "C:\\CHUBB\\LocationServices\\LOC3X4\\";
        	bpelFileName = "LocationRetrievalLOC3X4Process";        	
        } 
        
        //COMPLETE_PATH = "C:\\CHUBB\\LocationServices\\";
    	//bpelFileName = "LocationRetrievalLOC3X1Process";        
        
        JSONParser parser = new JSONParser();
        
        try (FileReader reader = new FileReader(SERVICE_SPEC_PATH)) {
        	// Parse the JSON file
            Object obj = parser.parse(reader);

            // Cast the parsed object to a JSONObject
            JSONObject jsonObject = (JSONObject) obj;

            // Access data from the JSONObject
            String serviceName = (String) jsonObject.get("serviceName");
            String packageBase = (String) jsonObject.get("packageBase");
            
            //LOGGER.info("serviceName: " + serviceName);
            //LOGGER.info("packageBase: " + packageBase);
            
            Map<String, String> bpelObjectsMap = BPELToCamelGenerator.bpeljavacreate(COMPLETE_PATH, bpelFileName + Constants.BPEL_EXT);
            //LOGGER.info("bpelObjectsMap: " + bpelObjectsMap);
            
            boolean created = CreateFolderUtil.createNewFolder(bpelFileName.toLowerCase(), 
            		"com." + bpelFileName.toLowerCase() + ".webservice",
            		bpelObjectsMap);
            LOGGER.info("created: " + created);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}