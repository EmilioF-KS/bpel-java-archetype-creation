package com.chubb.converter.bpel.scriptsbody;

public class BpelUtils {

	public static String getBpelLocationFormat(String wsOriginalName, String requestName) {
		System.out.println("----> getBpelLocationFormat.wsOriginalName : " + wsOriginalName);
		System.out.println("----> getBpelLocationFormat.requestName : " + requestName);
		return new StringBuilder().append("../").append(wsOriginalName)
				.append("/").append(requestName).append(".xsd").toString();
	}

	public static String getBpelFullQualifiedPojo(String namespaceToClass, String requestName) {
		System.out.println("----> requestName.namespaceToClass : " + namespaceToClass);
		System.out.println("----> requestName.requestName : " + requestName);
		
		String pojoClassPath = "com.chubb.generated." + namespaceToClass.substring(7);
        System.out.println("----> pojoClassPath : " + pojoClassPath.replaceAll("/", "."));
        
		return new StringBuilder().append(pojoClassPath.replaceAll("/", ".")).append(".").append(requestName).toString();
	}

}
