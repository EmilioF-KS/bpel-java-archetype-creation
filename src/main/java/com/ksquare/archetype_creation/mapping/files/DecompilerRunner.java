package com.ksquare.archetype_creation.mapping.files;

import org.benf.cfr.reader.api.CfrDriver;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecompilerRunner {

	public static final String CLASS_PATH = "C:\\KSquare projects\\Chubb-ibm\\ProductAvailability\\Map\\Mapping\\";
	
	public static final String OUTPUT_SOURCE_PATH = "C:\\KSquare projects\\Chubb-ibm\\ProductAvailability\\Map\\Mapping\\Source\\";
	
    public static void main(String[] args) {
        // Option 1: Basic usage - output to console
        CfrDriver driver = new CfrDriver.Builder().build();
        driver.analyse(Collections.singletonList(CLASS_PATH + "Address.class"));

        // Option 2: Customizing options - output to console
        Map<String, String> options = new HashMap<>();
        options.put("decodestringswitch", "false"); // Example: Disable string switch decompiler madness
        CfrDriver driver2 = new CfrDriver.Builder().withOptions(options).build();
        driver2.analyse(Collections.singletonList(CLASS_PATH + "Address.class"));

        // Option 3: Decompiling multiple files with custom output
        Map<String, String> outputOptions = new HashMap<>();
        outputOptions.put("outputdir", OUTPUT_SOURCE_PATH);
        CfrDriver driver3 = new CfrDriver.Builder().withOptions(outputOptions).build();
        driver3.analyse(Arrays.asList(CLASS_PATH + "Address.class", CLASS_PATH + "CRM_ADDRESS.class", CLASS_PATH + "AddressTransformer.class"));
    }
    
    public static void generateMappingSources(String classPath) {
    	System.out.println("-> generateMappingSources ");
    	System.out.println(": classPath : " + classPath);
    	
    	List<String> classesList = ListFilesExample.getClassFiles(CLASS_PATH + classPath + "\\");
        System.out.println("::::::::: classesList : " + classesList);
        
        Map<String, String> outputOptions = new HashMap<>();
        outputOptions.put("outputdir", OUTPUT_SOURCE_PATH);
        CfrDriver driver3 = new CfrDriver.Builder().withOptions(outputOptions).build();
        
        driver3.analyse(classesList);
    }
}