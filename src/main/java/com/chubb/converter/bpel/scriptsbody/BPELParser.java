package com.chubb.converter.bpel.scriptsbody;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BPELParser {
	public static String extractXsdName(String bpelFile, String originalXsdName) throws Exception {
    	System.out.println("-> extractXsdName.originalXsdName : " + originalXsdName);
    	System.out.println("-> extractXsdName.bpelFile : " + bpelFile);
        String xsdName = null;
       
        File newFile = new File(bpelFile); // Or new File(unixPath);
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(newFile);
        
        ////////// Services
        
        NodeList importNodes = doc.getElementsByTagName("bpws:import");
        for (int i = 0; i < importNodes.getLength(); i++) {
        	
            Node importNode = importNodes.item(i);

            if (importNode.getNodeType() == Node.ELEMENT_NODE) {
            	Element importElement = (Element) importNode;
            	
            	String importType = importElement.getAttribute("importType");
                String location = importElement.getAttribute("location");
                String namespace = importElement.getAttribute("namespace");
                
                //System.out.println("--> import.importType: " + importType);
                //System.out.println("--> import.location: " + location);
                //System.out.println("--> import.namespace: " + namespace);
                //System.out.println();
                
                if (originalXsdName.equals(location)) {
                	xsdName = namespace;
                }
            }
        }
        	        
        return xsdName;
    }

    public static String extractWsName(String bpelFile) throws Exception {
    	System.out.println("-> extractWsName : " + bpelFile);
        String wsName = null;

        File newFile = new File(bpelFile); // Or new File(unixPath);
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(newFile);
        
	    NodeList partnerLinksNodes = doc.getElementsByTagName("bpws:partnerLinks");
        
        for (int i = 0; i < partnerLinksNodes.getLength(); i++) {        	
            Node partnerLinksNode = partnerLinksNodes.item(i);
            
            if (partnerLinksNode.getNodeType() == Node.ELEMENT_NODE) {
                Element partnerLinksElement = (Element) partnerLinksNode;
                
                NodeList partnerLinkList = partnerLinksElement.getElementsByTagName("bpws:partnerLink");
                
                for (int j = 0; j < partnerLinkList.getLength(); j++) {
	                Element partnerLinkElement = (Element) partnerLinkList.item(j);
	
	                String name = partnerLinkElement.getAttribute("name");
	                String partnerLinkType = partnerLinkElement.getAttribute("partnerLinkType");
	                String myRole = partnerLinkElement.getAttribute("myRole");
	                String id = partnerLinkElement.getAttribute("wpc:id");
	                   
	                //System.out.println("--> partnerLinks.partnerLink.name: " + name);
	                //System.out.println("--> partnerLinks.partnerLink.partnerLinkType: " + partnerLinkType);
	                //System.out.println("--> partnerLinks.partnerLink.myRole: " + myRole);
	                //System.out.println("--> partnerLinks.partnerLink.id: " + id);
	                
	                if ("Interface".equals(myRole)) {
	                	wsName = name;
	                }
                }
            }
        }
	        
        return wsName;
    }

    public static String extractOutputsName(String bpelFile) throws Exception {
    	System.out.println("-> extractOutputsName : " + bpelFile);
        File newFile = new File(bpelFile); // Or new File(unixPath);
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(newFile);
        String outputsName = null;
	        
	    NodeList variablesNodes = doc.getElementsByTagName("bpws:variables");
	    for (int i = 0; i < variablesNodes.getLength(); i++) {
	     	
		    Node variablesNode = variablesNodes.item(i);
		    
		    if (variablesNode.getNodeType() == Node.ELEMENT_NODE) {
                Element variablesElement = (Element) variablesNode;
                
                NodeList variablesElementList = variablesElement.getElementsByTagName("bpws:variable");
                
                for (int j = 0; j < variablesElementList.getLength(); j++) {
	                Element variableElement = (Element) variablesElementList.item(j);
	
	                String name = variableElement.getAttribute("name");
	                String type = variableElement.getAttribute("type");
	                String id = variableElement.getAttribute("wpc:id");
	                
	                //System.out.println("--> variable.name: " + name);
	                //System.out.println("--> variable.type: " + type);
	                //System.out.println("--> variable.id: " + id);
	                //System.out.println();
	                
	                if (name.contains("Outputs")) {
	                	outputsName = name;
	                }
                }
            }
	     }
	        
        return outputsName;
    }

    public static String extractWsRequestName(String bpelFile, String wsOriginalName) throws Exception {
    	System.out.println("-> extractWsRequestName : " + bpelFile);
    	System.out.println("-> wsOriginalName : " + wsOriginalName);
        String wsName = null;

        File newFile = new File(bpelFile); // Or new File(unixPath);
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(newFile);
        
        NodeList receiveNodes = doc.getElementsByTagName("bpws:receive");
        for (int i = 0; i < receiveNodes.getLength(); i++) {
        	
            Node receiveNode = receiveNodes.item(i);

            if (receiveNode.getNodeType() == Node.ELEMENT_NODE) {
            	Element receiveElement = (Element) receiveNode;
            	
            	String createInstance = receiveElement.getAttribute("createInstance");
                String name = receiveElement.getAttribute("name");
                String operation = receiveElement.getAttribute("operation");
                String partnerLink = receiveElement.getAttribute("partnerLink");
                String portType = receiveElement.getAttribute("portType");
                String displayName = receiveElement.getAttribute("wpc:displayName");
                String id = receiveElement.getAttribute("wpc:id");
                String transactionalBehavior = receiveElement.getAttribute("wpc:transactionalBehavior");
                
                //System.out.println("-> receive.createInstance: " + createInstance);
                //System.out.println("-> receive.name: " + name);
                //System.out.println("-> receive.operation: " + operation);
                //System.out.println("-> receive.partnerLink: " + partnerLink);
                //System.out.println("-> receive.portType: " + portType);
                //System.out.println("-> receive.displayName: " + displayName);
                //System.out.println("-> receive.id: " + id);
                //System.out.println("-> receive.transactionalBehavior: " + transactionalBehavior);

                // Get the input element
                NodeList outputList = receiveElement.getElementsByTagName("wpc:output");
                if (outputList.getLength() > 0) {
                    Element outputElement = (Element) outputList.item(0);

                    // Get the value element inside body
                        NodeList parameterList = outputElement.getElementsByTagName("wpc:parameter");
                        if (parameterList.getLength() > 0) {
                            Element parameterElement = (Element) parameterList.item(0);
                            String parametername = parameterElement.getAttribute("name");
                            String parametervariable = parameterElement.getAttribute("variable");
                            
                            //System.out.println("-> receive.output.name: " + parametername);
                            //System.out.println("-> receive.output.variable: " + parametervariable);
                            
                            if (wsOriginalName.equals(partnerLink)) {
                            	wsName = parametername;
                            }
                        }
                    }
                }
        }
	        
        return wsName;
    }

    public static List<String> extractInvokes(String bpelFile) throws Exception {
    	System.out.println("-> extractInvokes : " + bpelFile);
        List<String> invokes = new ArrayList<>();

        File newFile = new File(bpelFile); // Or new File(unixPath);
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(newFile);
                
        NodeList invokeNodes = doc.getElementsByTagName("bpws:invoke");
        System.out.println("-> invokeNodes : " + invokeNodes);
        
        for (int i = 0; i < invokeNodes.getLength(); i++) {
            Node invokeNode = invokeNodes.item(i);

            if (invokeNode.getNodeType() == Node.ELEMENT_NODE) {
                Element invokeElement = (Element) invokeNode;
                
                String operation = invokeElement.getAttribute("operation");
                String partnerLink = invokeElement.getAttribute("partnerLink");
                String name = invokeElement.getAttribute("name");
                
                if (name.endsWith("Map")) {
                    System.out.println("--> invoke Map name : " + name);
                }
                
                String partnerLinkOperation = "OrderService.submitOrder";
                invokes.add(partnerLinkOperation);
                //System.out.println("-> partnerLinkOperation : " + partnerLinkOperation);

                // Get the input element
                NodeList inputList = invokeElement.getElementsByTagName("wpc:script");
                if (inputList.getLength() > 0) {
                    Element inputElement = (Element) inputList.item(0);

                    // Get the value element inside body
                        NodeList valueList = inputElement.getElementsByTagName("wpc:javaCode");
                        if (valueList.getLength() > 0) {
                            Element valueElement = (Element) valueList.item(0);
                            String valueText = valueElement.getTextContent();
                            //System.out.println("Value: " + valueText);
                        }
                    }
                }
            }
    
        return invokes;
    }
}
