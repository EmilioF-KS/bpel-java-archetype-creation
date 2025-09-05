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
                System.out.println("src directory created: " + newFolderSrc.getAbsolutePath());
                successSrc = true;
            } else {
                System.out.println("Failed to create src directory or it already exists.");
            }
            
            File newFolderLib = new File(FOLDER_PATH + folderName + SEPARATOR + LIBS);

            // Create a single directory
            if (newFolderLib.mkdir()) {
                System.out.println("LIBS directory created: " + newFolderLib.getAbsolutePath());
                successLib = true;
            } else {
                System.out.println("Failed to create LIBS directory or it already exists.");
            }
        }
        
        if (successSrc && successLib) {
        	File newFolderMain = new File(FOLDER_PATH + folderName + SEPARATOR + SRC + SEPARATOR + MAIN);

            // Create a single directory
            if (newFolderMain.mkdir()) {
                System.out.println("main directory created: " + newFolderMain.getAbsolutePath());
                successMain = true;
            } else {
                System.out.println("Failed to create main directory or it already exists.");
            }	
        }
        
        if (successMain) {
        	File newFolderResources = new File(FOLDER_PATH + folderName + SEPARATOR + SRC + SEPARATOR + MAIN + SEPARATOR + RESOURCES);

            // Create a single directory
            if (newFolderResources.mkdir()) {
                System.out.println("resources directory created: " + newFolderResources.getAbsolutePath());
                
                ResourceCopierUtil.copyResourceToFile("/resources/application.yml", newFolderResources +
                		SEPARATOR + "application-" + folderName + ".yml");
                
                ResourceCopierUtil.copyResourceToFile("/resources/pom.xml", FOLDER_PATH + folderName + SEPARATOR + "pom.xml");
            } else {
                System.out.println("Failed to create resources directory or it already exists.");
            }
            
            File newFolderJava = new File(FULL_JAVA_PATH);

            // Create a single directory
            if (newFolderJava.mkdir()) {
                System.out.println("java directory created: " + newFolderJava.getAbsolutePath());
                successJava = true;
            } else {
                System.out.println("Failed to create java directory or it already exists.");
            }
        }
    	
        if (successJava) {
        	String projectPathNew = packageBase.replace(".", SEPARATOR);
        	System.out.println("projectPathNew: " + projectPathNew);
        	
        	File newFolderProjectPath = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew);

            // Create a single directory
            if (newFolderProjectPath.mkdirs()) {
                System.out.println("ProjectPath directory created: " + newFolderProjectPath.getAbsolutePath());
                successProjectPath = true;
                
                File newFolderProjectControllerPath = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "controller");
                
                if (newFolderProjectControllerPath.mkdirs()) {
                    System.out.println("controller directory created: " + newFolderProjectControllerPath.getAbsolutePath());
                    
                } else {
                    System.out.println("Failed to create controller directory or it already exists.");
                }
                
                File newFolderProjectDtoPath = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "dto");
                
                if (newFolderProjectDtoPath.mkdirs()) {
                    System.out.println("dto directory created: " + newFolderProjectDtoPath.getAbsolutePath());
                    
                } else {
                    System.out.println("Failed to create dto directory or it already exists.");
                }
                
                File newFolderProjectEntityPath = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "entity");
                
                if (newFolderProjectEntityPath.mkdirs()) {
                    System.out.println("entity directory created: " + newFolderProjectEntityPath.getAbsolutePath());
                    
                } else {
                    System.out.println("Failed to create entity directory or it already exists.");
                }
                
                File newFolderProjectRepositoryPath = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "repository");
                
                if (newFolderProjectRepositoryPath.mkdirs()) {
                    System.out.println("repository directory created: " + newFolderProjectRepositoryPath.getAbsolutePath());
                    
                } else {
                    System.out.println("Failed to create repository directory or it already exists.");
                }
                
                File newFolderProjectServicePath = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                		SEPARATOR + "service");
                
                if (newFolderProjectServicePath.mkdirs()) {
                    System.out.println("service directory created: " + newFolderProjectServicePath.getAbsolutePath());
                    
                } else {
                    System.out.println("Failed to create service directory or it already exists.");
                }
                
                File newFolderProjNamec = new File(FULL_JAVA_PATH + SEPARATOR + projectPathNew + SEPARATOR + folderName);

                // Create a single directory
                if (newFolderProjNamec.mkdir()) {
                    System.out.println("ProjName directory created: " + newFolderProjNamec.getAbsolutePath());
                    successSrc = true;
                } else {
                    System.out.println("Failed to create ProjName directory or it already exists.");
                }
                
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
                System.out.println("Application File Updated...........");

                System.out.println("Controller File to Update..........." + FULL_JAVA_PATH + "-" + projectPathNew +
                		"-" + "controller" + "-" + folderName.substring(0, 1).toUpperCase() +
                		folderName.substring(1, folderName.length()) + "Controller.java");
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
                
                TextFileModificationProgramUtil.modifyFile(FULL_JAVA_PATH + SEPARATOR + projectPathNew +
                   		SEPARATOR + "controller" + SEPARATOR + folderName.substring(0, 1).toUpperCase() +
                   		folderName.substring(1, folderName.length()) + "Controller.java", 
                   		"outputclasstoupdate", 
                   		bpelObjectsMap.get(Constants.OUTPUT_POJOCLASS));
                   			
                System.out.println("Controller File Updated...........");

                //Copying Gradle files
                System.out.println("Copying Gradle Files...........");
                
                ResourceCopierUtil.copyResourceToFile("/resources/build.gradle", 
                		FOLDER_PATH + folderName + SEPARATOR + "build.gradle");
                
                ResourceCopierUtil.copyResourceToFile("/resources/gradlew", 
                		FOLDER_PATH + folderName + SEPARATOR + "gradlew");
                
                ResourceCopierUtil.copyResourceToFile("/resources/gradlew.bat", 
                		FOLDER_PATH + folderName + SEPARATOR + "gradlew.bat");
                
                ResourceCopierUtil.copyResourceToFile("/resources/settings.gradle", 
                		FOLDER_PATH + folderName + SEPARATOR + "settings.gradle");
                
                ResourceCopierUtil.copyResourceToFile("/resources/xsd-javapojos-0.0.1-SNAPSHOT.jar", 
                		FOLDER_PATH + folderName + SEPARATOR + LIBS + SEPARATOR + "xsd-javapojos-0.0.1-SNAPSHOT.jar");
                
                ResourceCopierUtil.copyResourceToFile("/resources/soap-clients-0.0.1-SNAPSHOT.jar", 
                		FOLDER_PATH + folderName + SEPARATOR + LIBS + SEPARATOR + "soap-clients-0.0.1-SNAPSHOT.jar");
                
                //Copying Web Services files
                System.out.println("Copying Web Services Files...........");
                System.out.println(FULL_JAVA_PATH + SEPARATOR + projectPathNew + SEPARATOR);
                
                //ResourceCopierUtil.copyDirectory(IMPORTED_WS_PATH, 
                	//	FULL_JAVA_PATH + SEPARATOR + projectPathNew + SEPARATOR);
                
                //Generating Mappings
                /*try {
					MappingGenerator.generateMappingClasses(projectPathNew.replace("\\", ".") + "." + "mapping");
					//MappingGenerator.generateMappingClasses("com.mapping");
				} catch (ClassNotFoundException | JAXBException | CannotCompileException | NotFoundException
						| IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/

				//DecompilerRunner.generateMappingSources(projectPathNew + SEPARATOR + "mapping");
                
                ////////////////////////
                //String mappingFilesPath = DecompilerRunner.OUTPUT_SOURCE_PATH + SEPARATOR + projectPathNew + SEPARATOR + "mapping";
                		
                //System.out.println("...................mapping path " + mappingFilesPath);
                /*
                List<String> fileNames = Files.list(Paths.get(mappingFilesPath))
                        .filter(Files::isRegularFile)
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .collect(Collectors.toList());
                
                //System.out.println("...................fileNames " + fileNames);
                for (String fileName : fileNames) {
                	//System.out.println("...................fileName " + fileName);
                	
                	if (fileName.contains("Transformer")) {
                		TextFileModificationProgramUtil.modifyFile(mappingFilesPath + SEPARATOR + fileName, 
                           		"Transformer ", 
                           		"Transformer extends Address ");//TODO remove hardcode
                	}
                }*/
                ////////////////////////

                ResourceCopierUtil.copyDirectory(DecompilerRunner.OUTPUT_SOURCE_PATH, 
                		FULL_JAVA_PATH + SEPARATOR);
                System.out.println("Mapping Files copied successfully!");
                
            } else {
                System.out.println("Failed to create ProjectPath directory or it already exists.");
            }
        }
        
		return successProjectPath;
    }
}