package com.ksquare.archetype_creation;

import java.io.FileReader;
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
    	LOGGER.debug("-> MicroserviceGenerator");
        JSONParser parser = new JSONParser();
        
        String COMPLETE_PATH = "C:\\CHUBB\\LocationServices\\LOC2X3\\";
    	String bpelFileName = "AddressStandardizationLOC2X3Process";
        
        try (FileReader reader = new FileReader(SERVICE_SPEC_PATH)) {
        	// Parse the JSON file
            Object obj = parser.parse(reader);

            // Cast the parsed object to a JSONObject
            JSONObject jsonObject = (JSONObject) obj;

            // Access data from the JSONObject
            String serviceName = (String) jsonObject.get("serviceName");
            String packageBase = (String) jsonObject.get("packageBase");
            
            LOGGER.debug("serviceName: " + serviceName);
            LOGGER.debug("packageBase: " + packageBase);
            
            Map<String, String> bpelObjectsMap = BPELToCamelGenerator.bpeljavacreate(COMPLETE_PATH, bpelFileName + Constants.BPEL_EXT);
            LOGGER.debug("bpelObjectsMap: " + bpelObjectsMap);
            
            boolean created = CreateFolderUtil.createNewFolder(bpelFileName.toLowerCase(), 
            		"com." + bpelFileName.toLowerCase() + ".webservice",
            		bpelObjectsMap);
            LOGGER.debug("created: " + created);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}