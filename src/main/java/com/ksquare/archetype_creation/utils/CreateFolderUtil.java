package com.ksquare.archetype_creation.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ksquare.archetype_creation.mapping.files.DecompilerRunner;

public class CreateFolderUtil {
	private static final String FOLDER_PATH = "C:\\KSquare projects\\Chubb-ibm\\_NEW_PROJECTS_v2\\SpringBootApp\\";
	private static final String SRC = "src";
	private static final String LIBS = "libs";
	private static final String MAIN = "main";
	private static final String JAVA = "java";
	private static final String RESOURCES = "resources";
	private static final String SEPARATOR = "\\";
	//private static final String IMPORTED_WS_PATH = "C:\\KSquare projects\\Chubb-ibm\\IMPORTED_WS\\Product Availability\\webservice\\";
	
	public static boolean createNewFolder (String folderName, String packageBase, Map<String, String> bpelObjectsMap) throws IOException {
    	System.out.println("-> createNewFolder");
    	System.out.println("Single directory created		: " + folderName);
    	System.out.println("Single directory packageBase	: " + packageBase);
    	System.out.println("Single directory bpelObjectsMap	: " + bpelObjectsMap);
    	
    	String FULL_JAVA_PATH = FOLDER_PATH + folderName + SEPARATOR + SRC + SEPARATOR + MAIN + SEPARATOR + JAVA;
    	
    	boolean success = false;
    	boolean successSrc = false;
    	boolean successLib = false;
    	boolean successMain = false;
    	boolean successJava = false;
    	boolean successProjectPath = false;
    	
    	File newFolder = new File(FOLDER_PATH + folderName);

        // Create a single directory
        if (newFolder.mkdir()) {
            System.out.println("Single directory created: " + newFolder.getAbsolutePath());
            success = true;
        } else {
            System.out.println("Failed to create single directory or it already exists.");
        }
        
        if (success) {
        	File newFolderSrc = new File(FOLDER_PATH + folderName + SEPARATOR + SRC);

            // Create a single directory
            if (newFolderSrc.mkdir()) {
                //System.out.println("src directory created: " + newFolderSrc.getAbsolutePath());
                successSrc = true;
            } else {
                System.out.println("Failed to create src directory or it already exists.");
            }
            
            File newFolderLib = new File(FOLDER_PATH + folderName + SEPARATOR + LIBS);

            // Create a single directory
            if (newFolderLib.mkdir()) {
                //System.out.println("LIBS directory created: " + newFolderLib.getAbsolutePath());
                successLib = true;
            } else {
                System.out.println("Failed to create LIBS directory or it already exists.");
            }
        }
        
        if (successSrc && successLib) {
        	File newFolderMain = new File(FOLDER_PATH + folderName + SEPARATOR + SRC + SEPARATOR + MAIN);

            // Create a single directory
            if (newFolderMain.mkdir()) {
                //System.out.println("main directory created: " + newFolderMain.getAbsolutePath());
                successMain = true;
            } else {
                System.out.println("Failed to create main directory or it already exists.");
            }	
        }
        
        if (successMain) {
        	File newFolderResources = new File(FOLDER_PATH + folderName + SEPARATOR + SRC + SEPARATOR + MAIN + SEPARATOR + RESOURCES);

            // Create a single directory
            if (newFolderResources.mkdir()) {
                //System.out.println("resources directory created: " + newFolderResources.getAbsolutePath());
                
                ResourceCopierUtil.copyResourceToFile("/resources/application.yml", newFolderResources +
                		SEPARATOR + "application-" + folderName + ".yml");
                
                ResourceCopierUtil.copyResourceToFile("/resources/pom.xml", FOLDER_PATH + folderName + SEPARATOR + "pom.xml");
            } else {
                System.out.println("Failed to create resources directory or it already exists.");
            }
            
            File newFolderJava = new File(FULL_JAVA_PATH);

            // Create a single directory
            if (newFolderJava.mkdir()) {
                //System.out.println("java directory created: " + newFolderJava.getAbsolutePath());
                successJava = true;
            } else {
                System.out.println("Failed to create java directory or it already exists.");
            }
        }
    	
        if (successJava) {
        	String projectPathNew = packageBase.replace(".", SEPARATOR);
        	//System.out.println("projectPathNew: " + projectPathNew);
        	
        	File newFolderProjectPath = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew);

            // Create a single directory
            if (newFolderProjectPath.mkdirs()) {
                //System.out.println("ProjectPath directory created: " + newFolderProjectPath.getAbsolutePath());
                successProjectPath = true;
                
                File newFolderProjectControllerPath = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "controller");
                
                if (newFolderProjectControllerPath.mkdirs()) {
                    //System.out.println("controller directory created: " + newFolderProjectControllerPath.getAbsolutePath());
                    
                } else {
                    //System.out.println("Failed to create controller directory or it already exists.");
                }
                
                File newFolderProjectDtoPath = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "dto");
                
                if (newFolderProjectDtoPath.mkdirs()) {
                    //System.out.println("dto directory created: " + newFolderProjectDtoPath.getAbsolutePath());
                    
                } else {
                    //System.out.println("Failed to create dto directory or it already exists.");
                }
                
                File newFolderProjectEntityPath = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "entity");
                
                if (newFolderProjectEntityPath.mkdirs()) {
                    //System.out.println("entity directory created: " + newFolderProjectEntityPath.getAbsolutePath());
                    
                } else {
                    //System.out.println("Failed to create entity directory or it already exists.");
                }
                
                File newFolderProjectRepositoryPath = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "repository");
                
                if (newFolderProjectRepositoryPath.mkdirs()) {
                    //System.out.println("repository directory created: " + newFolderProjectRepositoryPath.getAbsolutePath());
                    
                } else {
                    //System.out.println("Failed to create repository directory or it already exists.");
                }
                
                File newFolderProjectServicePath = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "service");
                
                if (newFolderProjectServicePath.mkdirs()) {
                    //System.out.println("service directory created: " + newFolderProjectServicePath.getAbsolutePath());
                    
                } else {
                    //System.out.println("Failed to create service directory or it already exists.");
                }
                
                File newFolderProjNamec = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew + SEPARATOR + folderName);

                // Create a single directory
                if (newFolderProjNamec.mkdir()) {
                    //System.out.println("ProjName directory created: " + newFolderProjNamec.getAbsolutePath());
                    successSrc = true;
                } else {
                    //System.out.println("Failed to create ProjName directory or it already exists.");
                }
                /*
                ResourceCopierUtil.copyResourceToFile("/resources/Application", FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + folderName + SEPARATOR + folderName.substring(0, 1).toUpperCase() +
                		folderName.substring(1, folderName.length()) + "Application.java");

                TextFileModificationProgramUtil.modifyFile(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + folderName + SEPARATOR + folderName.substring(0, 1).toUpperCase() +
                		folderName.substring(1, folderName.length()) + "Application.java", 
                		"packagetoupdate", 
                		packageBase + "." + folderName);
                
                TextFileModificationProgramUtil.modifyFile(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + folderName + SEPARATOR + folderName.substring(0, 1).toUpperCase() +
                		folderName.substring(1, folderName.length()) + "Application.java", 
                		"packagetoscan", 
                		packageBase);
                
           		TextFileModificationProgramUtil.modifyFile(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                   		SEPARATOR + folderName + SEPARATOR + folderName.substring(0, 1).toUpperCase() +
                   		folderName.substring(1, folderName.length()) + "Application.java", 
                   		"nametoupdate", 
                   		folderName.substring(0, 1).toUpperCase() +
                   			folderName.substring(1, folderName.length()));
                   			*/
                //System.out.println("Application File Updated...........");

                //System.out.println("Controller File to Update..........." + FULL_JAVA_PATH + "-" + projectPathNew +
                	//	"-" + "controller" + "-" + folderName.substring(0, 1).toUpperCase() +
                		//folderName.substring(1, folderName.length()) + "Controller.java");
                //Updating Controller
                ResourceCopierUtil.copyResourceToFile("/resources/Controller", FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "controller" + SEPARATOR + folderName.substring(0, 1).toUpperCase() +
                		folderName.substring(1, folderName.length()) + "Controller.java");

                TextFileModificationProgramUtil.modifyFile(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "controller" + SEPARATOR + folderName.substring(0, 1).toUpperCase() +
                		folderName.substring(1, folderName.length()) + "Controller.java", 
                		"packagetoupdate", 
                		packageBase + "." + "controller");
                
           		TextFileModificationProgramUtil.modifyFile(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                   		SEPARATOR + "controller" + SEPARATOR + folderName.substring(0, 1).toUpperCase() +
                   		folderName.substring(1, folderName.length()) + "Controller.java", 
                   		"nametoupdate", 
                   		folderName.substring(0, 1).toUpperCase() +
                   			folderName.substring(1, folderName.length()));
                   			
                TextFileModificationProgramUtil.modifyFile(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                   		SEPARATOR + "controller" + SEPARATOR + folderName.substring(0, 1).toUpperCase() +
                   		folderName.substring(1, folderName.length()) + "Controller.java", 
                   		"servicetoupdate", 
                   		bpelObjectsMap.get(Constants.WS_NAME));
                
                TextFileModificationProgramUtil.modifyFile(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                   		SEPARATOR + "controller" + SEPARATOR + folderName.substring(0, 1).toUpperCase() +
                   		folderName.substring(1, folderName.length()) + "Controller.java", 
                   		"inputclasstoupdate", 
                   		bpelObjectsMap.get(Constants.INPUT_POJOCLASS));
                
                if (bpelObjectsMap.get(Constants.OUTPUT_POJOCLASS) != null) {
                    TextFileModificationProgramUtil.modifyFile(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                       		SEPARATOR + "controller" + SEPARATOR + folderName.substring(0, 1).toUpperCase() +
                       		folderName.substring(1, folderName.length()) + "Controller.java", 
                       		"outputclasstoupdate", 
                       		bpelObjectsMap.get(Constants.OUTPUT_POJOCLASS));
                	
                }
                   			
                //System.out.println("Controller File Updated...........");

                //Copying Gradle files
                //System.out.println("Copying Gradle Files...........");
                
                ResourceCopierUtil.copyResourceToFile("/resources/build.gradle", 
                		FOLDER_PATH + folderName + SEPARATOR + "build.gradle");
                
                ResourceCopierUtil.copyResourceToFile("/resources/gradlew", 
                		FOLDER_PATH + folderName + SEPARATOR + "gradlew");
                
                ResourceCopierUtil.copyResourceToFile("/resources/gradlew.bat", 
                		FOLDER_PATH + folderName + SEPARATOR + "gradlew.bat");
                
                ResourceCopierUtil.copyResourceToFile("/resources/settings.gradle", 
                		FOLDER_PATH + folderName + SEPARATOR + "settings.gradle");
                
                ResourceCopierUtil.copyResourceToFile("/resources/xsd-javapojos-0.0.3-SNAPSHOT.jar", 
                		FOLDER_PATH + folderName + SEPARATOR + LIBS + SEPARATOR + "xsd-javapojos-0.0.3-SNAPSHOT.jar");
                
                ResourceCopierUtil.copyResourceToFile("/resources/soap-clients-0.0.1-SNAPSHOT.jar", 
                		FOLDER_PATH + folderName + SEPARATOR + LIBS + SEPARATOR + "soap-clients-0.0.1-SNAPSHOT.jar");

                ////////////////////////LOC3X1
                File newFolderProjectUtilsPath = new File(FULL_JAVA_PATH + SEPARATOR + 
                		"com" + SEPARATOR +
                		"chubb" + SEPARATOR + 
                		"converter" + SEPARATOR + 
                		"util");
                
                if (newFolderProjectUtilsPath.mkdirs()) {
                    System.out.println("repository directory created: " + newFolderProjectRepositoryPath.getAbsolutePath());
                    
                } else {
                    System.out.println("Failed to create repository directory or it already exists.");
                }
                
                File newConfigFolderProjNamec = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew + SEPARATOR + folderName +
                		SEPARATOR + "config");

                // Create a single directory
                if (newConfigFolderProjNamec.mkdir()) {
                    //System.out.println("Config directory created: " + newFolderProjNamec.getAbsolutePath());
                } else {
                    //System.out.println("Failed to create ProjName directory or it already exists.");
                }
                
                File newRouteFolderProjNamec = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew + SEPARATOR + folderName +
                		SEPARATOR + "routes");

                // Create a single directory
                if (newRouteFolderProjNamec.mkdir()) {
                    //System.out.println("Config directory created: " + newFolderProjNamec.getAbsolutePath());
                } else {
                    //System.out.println("Failed to create ProjName directory or it already exists.");
                }

                File newFolderProjectModelPath = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "model");
                
                if (newFolderProjectModelPath.mkdirs()) {
                    //System.out.println("controller directory created: " + newFolderProjectControllerPath.getAbsolutePath());
                    
                } else {
                    //System.out.println("Failed to create controller directory or it already exists.");
                }
                                
                File newServiceFolderProjName = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew + 
                		SEPARATOR + "service" + SEPARATOR + "mfc");

                // Create a single directory
                if (newServiceFolderProjName.mkdir()) {
                    //System.out.println("Config directory created: " + newFolderProjNamec.getAbsolutePath());
                } else {
                    //System.out.println("Failed to create ProjName directory or it already exists.");
                }

                ResourceCopierUtil.copyResourceToFile("/resources/GetStateOrProvinceResponse",  
                		newFolderProjectUtilsPath + SEPARATOR + 
                		"GetStateOrProvinceResponse.java");
                
                ResourceCopierUtil.copyResourceToFile("/resources/MapperUtility",  
                		newFolderProjectUtilsPath + SEPARATOR + 
                		"MapperUtility.java");
                
                ResourceCopierUtil.copyResourceToFile("/resources/XmlParser",  
                		newFolderProjectUtilsPath + SEPARATOR + 
                		"XmlParser.java");
                
                ResourceCopierUtil.copyResourceToFile("/resources/XmlParserXpathGetCountry",  
                		newFolderProjectUtilsPath + SEPARATOR + 
                		"XmlParserXpathGetCountry.java");
                
                ResourceCopierUtil.copyResourceToFile("/resources/XmlParserXpathGetStateOrProvince",  
                		newFolderProjectUtilsPath + SEPARATOR + 
                		"XmlParserXpathGetStateOrProvince.java");
                
                ResourceCopierUtil.copyResourceToFile("/resources/XmlParserXpathRand",  
                		newFolderProjectUtilsPath + SEPARATOR + 
                		"XmlParserXpathRand.java");
                
                ResourceCopierUtil.copyResourceToFile("/resources/XmlParserXpathLocation",  
                		newFolderProjectUtilsPath + SEPARATOR + 
                		"XmlParserXpathLocation.java");
                
                ResourceCopierUtil.copyResourceToFile("/resources/LocationRetrievalApplication", FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + folderName + SEPARATOR + "LocationRetrievalApplication.java");
                
                ResourceCopierUtil.copyResourceToFile("/resources/LocationController", FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "controller" + SEPARATOR + "LocationController.java");
                
                ResourceCopierUtil.copyResourceToFile("/resources/CamelConfig", FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + folderName + SEPARATOR + "config" + SEPARATOR + "CamelConfig.java");
                
                ResourceCopierUtil.copyResourceToFile("/resources/LocationRetrievalRoute", FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + folderName + SEPARATOR + "routes" + SEPARATOR + "LocationRetrievalRoute.java");
                
                ResourceCopierUtil.copyResourceToFile("/resources/Location", FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "model" + SEPARATOR + "Location.java");
                ResourceCopierUtil.copyResourceToFile("/resources/LocationReply", FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "model" + SEPARATOR + "LocationReply.java");
                ResourceCopierUtil.copyResourceToFile("/resources/StateOrProvinceBack", FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "model" + SEPARATOR + "StateOrProvinceBack.java");
                ResourceCopierUtil.copyResourceToFile("/resources/LocationRequest", FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "model" + SEPARATOR + "LocationRequest.java");
                
                ResourceCopierUtil.copyResourceToFile("/resources/ValidationService", FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "service" + SEPARATOR + "ValidationService.java");
                ResourceCopierUtil.copyResourceToFile("/resources/EnrichmentService", FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "service" + SEPARATOR + "EnrichmentService.java");
                ResourceCopierUtil.copyResourceToFile("/resources/FireDistrictService", FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "service" + SEPARATOR + "FireDistrictService.java");
                ResourceCopierUtil.copyResourceToFile("/resources/LocationListReplyService", FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "service" + SEPARATOR + "LocationListReplyService.java");
                ResourceCopierUtil.copyResourceToFile("/resources/MappingService", FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "service" + SEPARATOR + "MappingService.java");
                ResourceCopierUtil.copyResourceToFile("/resources/RansService", FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "service" + SEPARATOR + "RansService.java");
                ResourceCopierUtil.copyResourceToFile("/resources/OutputTerminalTypeFinder", FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "service" + SEPARATOR + "mfc" + SEPARATOR + "OutputTerminalTypeFinder.java");
                ////////////////////////LOC3X1

                System.out.println("Spring Boot Project copied successfully!");
                
            } else {
                System.out.println("Failed to create ProjectPath directory or it already exists.");
            }
        }
        
		return successProjectPath;
    }
}