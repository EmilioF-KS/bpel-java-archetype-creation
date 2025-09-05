package com.ksquare.archetype_creation.mapping.files;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.CannotCompileException;
import java.util.Map;

import com.ibm.xmlns.prod.websphere.wbiserver.map._6_0.BusinessObjectMap;
import com.ibm.xmlns.prod.websphere.wbiserver.map._6_0.BusinessObjectMap.PropertyMap;

import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class MappingGenerator {
	private static Class<?> generate(String className, Map<String, Class<?>> properties) 
            throws CannotCompileException, ClassNotFoundException, NotFoundException, IOException {
    	
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass(className);

        for (Map.Entry<String, Class<?>> entry : properties.entrySet()) {
            String fieldName = entry.getKey();

            Class<?> fieldType = entry.getValue();

            // Add field
            CtField field = new CtField(pool.get(fieldType.getName()), fieldName, ctClass);
            ctClass.addField(field);

            // Add getter
            String getterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            CtMethod getter = CtMethod.make(
                "public " + fieldType.getName() + " " + getterName + "() { return this." + fieldName + "; }", 
                ctClass);
            ctClass.addMethod(getter);

            // Add setter
            String setterName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            CtMethod setter = CtMethod.make(
                "public void " + setterName + "(" + fieldType.getName() + " " + fieldName + ") { this." + fieldName + " = " + fieldName + "; }", 
                ctClass);
            ctClass.addMethod(setter);
            
        }


        ctClass.writeFile("C:\\KSquare projects\\Chubb-ibm\\ProductAvailability\\Map\\Mapping"); // Saves to ./generated_classes/com/example/MyPojo.class
        System.out.println("Mapping Files saved to folder.");
        
        return ctClass.toClass();
    }

    private static Class<?> generate(String className, Map<String, Class<?>> properties, 
    		String otherClass, Map<String, String> propsOtherClass) 
            throws CannotCompileException, ClassNotFoundException, NotFoundException, IOException {
    	//System.out.println(":::::::::::::::::::::::::::::: otherClass: " + otherClass);
    	
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass(className);

        for (Map.Entry<String, Class<?>> entry : properties.entrySet()) {
            String fieldName = entry.getKey();
            if (otherClass != null) {
                //System.out.println("::::: Get Oher Class: " + propsOtherClass.get(fieldName));            	
            }

            Class<?> fieldType = entry.getValue();
            //System.out.println(":::::::::::::::::::::::::::::: fieldName: " + fieldName);
            
            // Add field
            CtField field = new CtField(pool.get(fieldType.getName()), fieldName, ctClass);
            //System.out.println(":::::::::::::::::::::::::::::: field: " + field);
            //System.out.println(":::::::::::::::::::::::::::::: ctClass: " + ctClass);

            ctClass.addField(field);

            // Add setter
            String setterName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            CtMethod setter = CtMethod.make(
                "public void " + setterName + "() { this." + fieldName + " = new " + otherClass + "().get" + propsOtherClass.get(fieldName) + "(); }", 
                ctClass);
            //System.out.println("::::: setterName: " + setterName);
            ctClass.addMethod(setter);
            ctClass.removeField(field);
            
        }


        ctClass.writeFile("C:\\KSquare projects\\Chubb-ibm\\ProductAvailability\\Map\\Mapping"); // Saves to ./generated_classes/com/example/MyPojo.class
        System.out.println("Mapping Java File saved to file.");
        
        return ctClass.toClass();
    }
    
    public static void main(String[] args) throws jakarta.xml.bind.JAXBException, ClassNotFoundException, CannotCompileException, NotFoundException, IOException {
        try {
            // Assuming 'YourRootObject' is the root element class generated from your XSD
            jakarta.xml.bind.JAXBContext jaxbContext = jakarta.xml.bind.JAXBContext.newInstance(BusinessObjectMap.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            File xmlFile = new File("C:\\KSquare projects\\Chubb-ibm\\ProductAvailability\\Map\\AddressSubmapXML.xml");
            //File xmlFile = new File("C:\\KSquare projects\\Chubb-ibm\\ProductAvailability\\Map\\GetFirstBookedTermListReplyMapXml.xml");
            //File xmlFile = new File("C:\\KSquare projects\\Chubb-ibm\\ProductAvailability\\Map\\GetFirstBookedTermListRequestMapXml.xml");
            Object unmarshallerRes = unmarshaller.unmarshal(xmlFile);
            BusinessObjectMap rootObject = (BusinessObjectMap) unmarshallerRes;
            
            //try (FileOutputStream fileOut = new FileOutputStream("C:\\KSquare projects\\Chubb-ibm\\ProductAvailability\\Map\\GetFirstBookedTermListRequestMapJava.java");
            //try (FileOutputStream fileOut = new FileOutputStream("C:\\KSquare projects\\Chubb-ibm\\ProductAvailability\\Map\\GetFirstBookedTermListReplyMapJava.java");
       		try (FileOutputStream fileOut = new FileOutputStream("C:\\KSquare projects\\Chubb-ibm\\ProductAvailability\\Map\\AddressSubmapJava.java");
                 ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {

                objOut.writeObject(rootObject);
                System.out.println("Object serialized and saved to " + rootObject.getName());

            } catch (IOException i) {
                i.printStackTrace();
            }

            // Now 'rootObject' contains the data from your XML file
            System.out.println("Object unmarshalled successfully!");
            
            System.out.println("Name: " + rootObject.getName());
            System.out.println("TargetNamespace: " + rootObject.getTargetNamespace());
            System.out.println("InputBusinessObjectVariable: " + rootObject.getInputBusinessObjectVariable());
            System.out.println("OutputBusinessObjectVariable: " + rootObject.getOutputBusinessObjectVariable());
            System.out.println("PropertyMaps: " + rootObject.getPropertyMaps());
            
            for (PropertyMap propertyMap : rootObject.getPropertyMaps()) {
            	if (propertyMap.getCustom() != null && 
            			propertyMap.getCustom().getInput() != null &&
            			propertyMap.getCustom().getOutput() != null) {
                	//System.out.println("propertyMap Input : " + propertyMap.getCustom().getInput().getBusinessObjectVariableRef() + "." + propertyMap.getCustom().getInput().getProperty());
                	System.out.println("propertyMap Output: " + propertyMap.getCustom().getOutput().getBusinessObjectVariableRef() + "." + propertyMap.getCustom().getOutput().getProperty());
            	}
            }

            ///////////////////////////////////////
            Map<String, Class<?>> propsCrm = new HashMap<>();
          
            String classNameCrm = null; 
            
            for (PropertyMap propertyMap : rootObject.getPropertyMaps()) {
            	if (propertyMap.getCustom() != null) {
            		if (classNameCrm == null) {
            			System.out.println("getBusinessObjectVariableRef: " + propertyMap.getCustom().getInput().getBusinessObjectVariableRef());
            			if (propertyMap.getCustom().getInput().getBusinessObjectVariableRef().contains("CRM_")) {
            				classNameCrm = propertyMap.getCustom().getInput().getBusinessObjectVariableRef();
            			}
            		}
            		System.out.println("propertyMap: " + propertyMap.getCustom().getInput().getProperty());
            		if (propertyMap.getCustom().getInput().getProperty().contains("CRM_")) {
            			propsCrm.put(propertyMap.getCustom().getInput().getProperty(), String.class);            			
            		}

            		System.out.println("classNameCrm: " + classNameCrm);
            	}
            }

            Class<?> dynamicMappingCrmClass = MappingGenerator.generate("com.example." + classNameCrm, propsCrm);
            System.out.println("Generated CRM Class: " + dynamicMappingCrmClass.getName());

            ///////////////////////////////////////
            Map<String, Class<?>> props = new HashMap<>();
            Map<String, String> propsOtherClass = new HashMap<>();
          
            String className = null; 
            
            for (PropertyMap propertyMap : rootObject.getPropertyMaps()) {
            	if (propertyMap.getCustom() != null) {
            		if (className == null) {
            			System.out.println("getBusinessObjectVariableRef: " + propertyMap.getCustom().getOutput().getBusinessObjectVariableRef());
            			if (!propertyMap.getCustom().getOutput().getBusinessObjectVariableRef().contains("CRM_")) {
            				className = propertyMap.getCustom().getOutput().getBusinessObjectVariableRef();
            			}
            		}
            		System.out.println("propertyMap: " + propertyMap.getCustom().getOutput().getProperty());
            		if (!propertyMap.getCustom().getOutput().getProperty().contains("CRM_")) {
                		props.put(propertyMap.getCustom().getOutput().getProperty(), String.class);    
                		propsOtherClass.put(propertyMap.getCustom().getOutput().getProperty(), 
                				propertyMap.getCustom().getInput().getProperty());
            		}

            		System.out.println("className: " + className);
            	}
            }

            Class<?> dynamicMappingClass = MappingGenerator.generate("com.example." + className, props);
            System.out.println("Generated Class: " + dynamicMappingClass.getName());

            ///////////////////////////////////////
            Map<String, Class<?>> propsTransformer = new HashMap<>();
            Map<String, String> propsOtherClassTransformer = new HashMap<>();
          
            String classNameTransformer = null; 
            
            for (PropertyMap propertyMap : rootObject.getPropertyMaps()) {
            	if (propertyMap.getCustom() != null) {
            		if (classNameTransformer == null) {
            			System.out.println("getBusinessObjectVariableRef: " + propertyMap.getCustom().getOutput().getBusinessObjectVariableRef());
            			if (!propertyMap.getCustom().getOutput().getBusinessObjectVariableRef().contains("CRM_")) {
            				classNameTransformer = propertyMap.getCustom().getOutput().getBusinessObjectVariableRef();
            			}
            		}
            		System.out.println("propertyMap: " + propertyMap.getCustom().getOutput().getProperty());
            		if (!propertyMap.getCustom().getOutput().getProperty().contains("CRM_")) {
            			propsTransformer.put(propertyMap.getCustom().getOutput().getProperty(), String.class);    
            			propsOtherClassTransformer.put(propertyMap.getCustom().getOutput().getProperty(), 
                				propertyMap.getCustom().getInput().getProperty());
            		}

            		System.out.println("className: " + classNameTransformer);
            	}
            }

            Class<?> Transformer = MappingGenerator.generate("com.example." + classNameTransformer + "Transformer", propsTransformer, 
            		dynamicMappingCrmClass.getName(), 
            		propsOtherClassTransformer);
            System.out.println("Generated Transformer Class: " + Transformer.getName());
            
            // Access data, e.g., rootObject.getPropertyName()
        } catch (jakarta.xml.bind.JAXBException e) {
            e.printStackTrace();
        }
    }
    
    public static void generateMappingClasses(String packagePath) throws jakarta.xml.bind.JAXBException, ClassNotFoundException, CannotCompileException, NotFoundException, IOException {
    	System.out.println("-> generateMappingClasses ");
    	System.out.println(": packagePath : " + packagePath);
    	
    	try {
            jakarta.xml.bind.JAXBContext jaxbContext = jakarta.xml.bind.JAXBContext.newInstance(BusinessObjectMap.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            File xmlFile = new File("C:\\KSquare projects\\Chubb-ibm\\ProductAvailability\\Map\\AddressSubmapXML.xml");
            Object unmarshallerRes = unmarshaller.unmarshal(xmlFile);
            BusinessObjectMap rootObject = (BusinessObjectMap) unmarshallerRes;
            
      		try (FileOutputStream fileOut = new FileOutputStream("C:\\KSquare projects\\Chubb-ibm\\ProductAvailability\\Map\\AddressSubmapJava.java");
                 ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {

                objOut.writeObject(rootObject);
                System.out.println("Object serialized and saved to " + rootObject.getName());

            } catch (IOException i) {
                i.printStackTrace();
            }

            System.out.println("Object unmarshalled successfully!");
            
            System.out.println("Name: " + rootObject.getName());
            System.out.println("TargetNamespace: " + rootObject.getTargetNamespace());
            System.out.println("InputBusinessObjectVariable: " + rootObject.getInputBusinessObjectVariable());
            System.out.println("OutputBusinessObjectVariable: " + rootObject.getOutputBusinessObjectVariable());
            System.out.println("PropertyMaps: " + rootObject.getPropertyMaps());
            
            for (PropertyMap propertyMap : rootObject.getPropertyMaps()) {
            	if (propertyMap.getCustom() != null && 
            			propertyMap.getCustom().getInput() != null &&
            			propertyMap.getCustom().getOutput() != null) {
                	//System.out.println("propertyMap Input : " + propertyMap.getCustom().getInput().getBusinessObjectVariableRef() + "." + propertyMap.getCustom().getInput().getProperty());
                	//System.out.println("propertyMap Output: " + propertyMap.getCustom().getOutput().getBusinessObjectVariableRef() + "." + propertyMap.getCustom().getOutput().getProperty());
            	}
            }

            Map<String, Class<?>> propsCrm = new HashMap<>();
          
            String classNameCrm = null; 
            
            for (PropertyMap propertyMap : rootObject.getPropertyMaps()) {
            	if (propertyMap.getCustom() != null) {
            		if (classNameCrm == null) {
            			//System.out.println("getBusinessObjectVariableRef: " + propertyMap.getCustom().getInput().getBusinessObjectVariableRef());
            			if (propertyMap.getCustom().getInput().getBusinessObjectVariableRef().contains("CRM_")) {
            				classNameCrm = propertyMap.getCustom().getInput().getBusinessObjectVariableRef();
            			}
            		}
            		//System.out.println("propertyMap: " + propertyMap.getCustom().getInput().getProperty());
            		if (propertyMap.getCustom().getInput().getProperty().contains("CRM_")) {
            			propsCrm.put(propertyMap.getCustom().getInput().getProperty(), String.class);            			
            		}

            		//System.out.println("classNameCrm: " + classNameCrm);
            	}
            }

            Class<?> dynamicMappingCrmClass = MappingGenerator.generate(packagePath + "." + classNameCrm, propsCrm);
            //System.out.println("Generated CRM Class: " + dynamicMappingCrmClass.getName());

            Map<String, Class<?>> props = new HashMap<>();
            Map<String, String> propsOtherClass = new HashMap<>();
          
            String className = null; 
            
            for (PropertyMap propertyMap : rootObject.getPropertyMaps()) {
            	if (propertyMap.getCustom() != null) {
            		if (className == null) {
            			//System.out.println("getBusinessObjectVariableRef: " + propertyMap.getCustom().getOutput().getBusinessObjectVariableRef());
            			if (!propertyMap.getCustom().getOutput().getBusinessObjectVariableRef().contains("CRM_")) {
            				className = propertyMap.getCustom().getOutput().getBusinessObjectVariableRef();
            			}
            		}
            		//System.out.println("propertyMap: " + propertyMap.getCustom().getOutput().getProperty());
            		if (!propertyMap.getCustom().getOutput().getProperty().contains("CRM_")) {
                		props.put(propertyMap.getCustom().getOutput().getProperty(), String.class);    
                		propsOtherClass.put(propertyMap.getCustom().getOutput().getProperty(), 
                				propertyMap.getCustom().getInput().getProperty());
            		}

            		//System.out.println("className: " + className);
            	}
            }

            Class<?> dynamicMappingClass = MappingGenerator.generate(packagePath + "." + className, props);
            System.out.println("Generated Class: " + dynamicMappingClass.getName());

            Map<String, Class<?>> propsTransformer = new HashMap<>();
            Map<String, String> propsOtherClassTransformer = new HashMap<>();
          
            String classNameTransformer = null; 
            
            for (PropertyMap propertyMap : rootObject.getPropertyMaps()) {
            	if (propertyMap.getCustom() != null) {
            		if (classNameTransformer == null) {
            			//System.out.println("getBusinessObjectVariableRef: " + propertyMap.getCustom().getOutput().getBusinessObjectVariableRef());
            			if (!propertyMap.getCustom().getOutput().getBusinessObjectVariableRef().contains("CRM_")) {
            				classNameTransformer = propertyMap.getCustom().getOutput().getBusinessObjectVariableRef();
            			}
            		}
            		//System.out.println("propertyMap: " + propertyMap.getCustom().getOutput().getProperty());
            		if (!propertyMap.getCustom().getOutput().getProperty().contains("CRM_")) {
            			propsTransformer.put(propertyMap.getCustom().getOutput().getProperty(), String.class);    
            			propsOtherClassTransformer.put(propertyMap.getCustom().getOutput().getProperty(), 
                				propertyMap.getCustom().getInput().getProperty());
            		}

            		//System.out.println("className: " + classNameTransformer);
            	}
            }

            Class<?> Transformer = MappingGenerator.generate(packagePath + "." + classNameTransformer + "Transformer", propsTransformer, 
            		dynamicMappingCrmClass.getName(), 
            		propsOtherClassTransformer);
            System.out.println("Generated Transformer Class: " + Transformer.getName());
        } catch (jakarta.xml.bind.JAXBException e) {
            e.printStackTrace();
        }
    }
}