package org.resl.cms.CMS_Slave.interfaceback;

import java.io.IOException;
import java.util.logging.Logger;

import org.resl.cms.CMS_Slave.Configuration.Configuration;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Test {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		//this variables must be assigned from interface values
		String  ip ="143.248.55.139";
	    String port = "8787";
	    String bizLocation = "bizLocation1";
		String writePoint = "writePoint1";
		
		// Step 1 configure 
		Configuration configuration=new Configuration();
		configuration.configure(ip, port, bizLocation, writePoint);
		//Step 2 register
		InterfaceBack back=new InterfaceBack();
		String registerStatus=back.register();
		System.out.println(registerStatus);
		System.out.println(Configuration.id);
		//Step 3 request
		//step 4 assign
		//step 5 history 
		
		System.out.println("Test End!");

	}

}
