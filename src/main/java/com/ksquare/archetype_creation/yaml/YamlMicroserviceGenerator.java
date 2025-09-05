package com.ksquare.archetype_creation.yaml;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.yaml.snakeyaml.Yaml;

import com.ksquare.archetype_creation.utils.CreateFolderUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public class YamlMicroserviceGenerator {
	private static final String YML_SERVICE_SPEC_PATH = "C:\\KSquare projects\\Chubb-ibm\\RATNA_CODE\\microservice-generator\\microservice-generator\\input\\yaml_dynamic_input.yaml";

    public static void main(String[] args) {
        Yaml yaml = new Yaml();
        
        // Create a File object
        File file = new File(YML_SERVICE_SPEC_PATH);

        // Declare an InputStream variable
        //InputStream inputStream = null;

        try (InputStream inputStream = new FileInputStream(file)) {
            if (inputStream != null) {
                Map<String, Object> data = yaml.load(inputStream);
                String serviceName = (String) data.get("serviceName");
                String packageBase = (String) data.get("packageBase");
                Integer port = (Integer) data.get("port");
                
                System.out.println("service Name: " + serviceName);
                System.out.println("packageBase Name: " + packageBase);
                System.out.println("port Name: " + port);
                
                //JSONObject entity = (JSONObject) jsonObject.get("entity");
                //System.out.println("entity: " + entity);
                
                String name = (String) ((Map<String, Object>) data.get("entity")).get("name");
                System.out.println("name: " + name);
                
                String tableName = (String) ((Map<String, Object>) data.get("entity")).get("tableName");
                System.out.println("tableName: " + tableName);
                /*
                JSONObject dependencies = (JSONObject) jsonObject.get("dependencies");
                System.out.println("dependencies: " + dependencies);
                
                JSONArray fields = (JSONArray) entity.get("fields");
                System.out.println("fields: " + fields);

                System.out.println("serviceName: " + serviceName);
                System.out.println("packageBase: " + packageBase);
                System.out.println("port: " + port);
     			*/

                boolean created = CreateFolderUtil.createNewFolder(serviceName, packageBase, null);
                System.out.println("created: " + created);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}