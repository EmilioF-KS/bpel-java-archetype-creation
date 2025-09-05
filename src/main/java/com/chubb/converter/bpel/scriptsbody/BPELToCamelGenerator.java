package com.chubb.converter.bpel.scriptsbody;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

import com.chubb.location.library.interfaces.SoapClientInterface;
import com.ksquare.archetype_creation.utils.Constants;

public class BPELToCamelGenerator {

    public static Map<String, String> bpeljavacreate(String COMPLETE_PATH, String bpelFile) throws Exception {
    	System.out.println("->>> BPELToCamelGenerator.bpeljavacreate");
    	System.out.println("->>> COMPLETE_PATH : " + COMPLETE_PATH);
    	System.out.println("->>> bpelFile : " + bpelFile);
    	
    	Map<String, String> bpelObjectsMap = new HashMap<String, String>();
    	
    	String bpelFilePath = COMPLETE_PATH + bpelFile;
    	List<String> invokes = BPELParser.extractInvokes(bpelFilePath);
        //System.out.println("-> BPELToCamelGenerator invokes : " + invokes);
        
        String wsOriginalName = BPELParser.extractWsName(bpelFilePath);
        System.out.println("---> wsOriginalName : " + wsOriginalName);
        String wsName = "com.chubb.location.library." + wsOriginalName + "Client";
        System.out.println("---> wsName : " + wsName);
        bpelObjectsMap.put(Constants.WS_NAME, wsName);
        
        String requestName = BPELParser.extractWsRequestName(bpelFilePath, wsOriginalName);
        System.out.println("---> requestName : " + requestName);
        
        String outputName = BPELParser.extractOutputsName(bpelFilePath);
        System.out.println("---> outputName : " + outputName);
        
        try {
            // Load the class by name
            Class<?> clazz = Class.forName(wsName);

            // Create a new instance (assumes a no-arg constructor)
            Object instance = clazz.getDeclaredConstructor().newInstance();
            System.out.println("---> instance : " + instance);
            
            //String xsdClass = "../" + wsOriginalName + "/" + requestName + ".xsd";
            String xsdClass = BpelUtils.getBpelLocationFormat(wsOriginalName, requestName);
            System.out.println("----> xsdClass : " + xsdClass);
            String namespaceToClass = BPELParser.extractXsdName(bpelFilePath, xsdClass);
            System.out.println("----> namespaceToClass : " + namespaceToClass);
                        
            String fullyQualifiedClass = BpelUtils.getBpelFullQualifiedPojo(namespaceToClass, requestName);
            System.out.println("----> fullyQualifiedClass : " + fullyQualifiedClass);
            bpelObjectsMap.put(Constants.INPUT_POJOCLASS, fullyQualifiedClass);
            
            Class<?> clazzReq = Class.forName(fullyQualifiedClass);
                    
            // Create a new instance (assumes a no-arg constructor)
            Object instanceReq = clazzReq.getDeclaredConstructor().newInstance();
            System.out.println("---> instanceReq : " + instanceReq);
            
            String xsdClassOutput = BpelUtils.getBpelLocationFormat(wsOriginalName, outputName);
            System.out.println("-----> xsdClassOutput : " + xsdClassOutput);
            String namespaceToOutputClass = BPELParser.extractXsdName(bpelFilePath, xsdClassOutput);
            System.out.println("-----> namespaceToOutputClass : " + namespaceToOutputClass);
            String fullyQualifiedOutputClass = BpelUtils.getBpelFullQualifiedPojo(namespaceToOutputClass, outputName);
            System.out.println("----> fullyQualifiedOutputClass : " + fullyQualifiedOutputClass);
            bpelObjectsMap.put(Constants.OUTPUT_POJOCLASS, fullyQualifiedOutputClass);
            
            Class<?> clazzRes = Class.forName(fullyQualifiedOutputClass);

            // Create a new instance (assumes a no-arg constructor)
            Object instanceRes = clazzRes.getDeclaredConstructor().newInstance();
            System.out.println("---> instanceRes : " + instanceRes);

            if (instance instanceof SoapClientInterface) {
            	instanceRes = ((SoapClientInterface) instance).invokeService(instanceReq);
            	System.out.println("---> final instanceRes : " + instanceRes);
            }

        } catch (ClassNotFoundException |
                 InstantiationException |
                 IllegalAccessException |
                 NoSuchMethodException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
        
        Main main = new Main();
        main.configure().addRoutesBuilder(new RouteBuilder() {
            @Override
            public void configure() {
            	System.out.println("-> BPELToCamelGenerator.configure");
            	
                for (String invoke : invokes) {
                	System.out.println("-> invoke : " + invoke);
                    String[] parts = invoke.split("\\.");
                    System.out.println("-> parts : " + parts);
                    
                    String partner = parts[0];
                    String operation = parts[1];
                    
                    System.out.println("--> timer://" + operation + "?repeatCount=1");
                    System.out.println("--> setBody : " + "Calling " + operation + " on " + partner);
                    System.out.println("--> to : " + "log:" + operation);

                    from("timer://" + operation + "?repeatCount=1")
                        .setBody(constant("Calling " + operation + " on " + partner))
                        .to("log:" + operation);
                }
            }
        });
        
        return bpelObjectsMap;

        //main.run();
    }
}
