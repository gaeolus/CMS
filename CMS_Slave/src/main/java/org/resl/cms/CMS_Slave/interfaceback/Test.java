package org.resl.cms.CMS_Slave.interfaceback;

import java.io.IOException;
import java.util.logging.Logger;

import org.resl.cms.CMS_Slave.Configuration.Configuration;
import org.resl.cms.CMS_Slave.model.KeyType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Test {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		//this variables must be assigned from interface values
		String  ip ="192.168.0.2";
	    String port = "8080";
	    String bizLocation = "bizLocation1";
		String writePoint = "writePoint1";
		
		String status="";
		Configuration configuration=new Configuration();
		
		String type="gtin";
		int companyPrefix=112345;
		int reference=2345;
		
		
		KeyType key=new KeyType(type,companyPrefix,reference);
		
		InterfaceBack back=new InterfaceBack();
		// Step 1 configure 
		back.configure(ip, port, bizLocation, writePoint);
		//Step 2 register
		String registerStatus=back.register();
		System.out.println(registerStatus);
		System.out.println(Configuration.id);
		//Step 3 request
		   //step 3.1 key config 
		System.out.println("Key Configuration");
		
		status=back.configure_key(key);
		System.out.println(status);
		
		  // step 3.2 key request
		System.out.println("Key Request");
		status=back.request(key, 10);
		System.out.println(status);
		//step 4 issue
		System.out.println("Id issuing");
		status=back.issue(key);
		System.out.println(status);
		//step 5 history 
		   //5.1 GeneralLog History
		System.out.println("GeneralLog History");
		status=back.historyGeneralLog();
		System.out.println(status);
		System.out.println("SpecificLog History");
		status=back.historySpecificLog();
		System.out.println(status);
		   //5.2 SpecificLog History
		System.out.println("Test End!");

	}

}
