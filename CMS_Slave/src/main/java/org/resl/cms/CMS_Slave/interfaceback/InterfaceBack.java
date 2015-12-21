package org.resl.cms.CMS_Slave.interfaceback;

import java.io.IOException;

import org.resl.cms.CMS_Slave.Configuration.Configuration;
import org.resl.cms.CMS_Slave.service.Request;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class InterfaceBack {
	
	public String register() throws JsonParseException, JsonMappingException, IOException{
		String status="";
		
		if(Configuration.configured){
			Request request=new Request();
			Configuration.id=(request.register()).getId();
			status ="registration succeeded";
			
		}else{
			status="Configuration is needed";
		}
		return status;
	}
	public String request(){
		String status="";
		
		
		return status;
	}
	public String issue(){
		String status="";
		
		
		return status;
	}
	
	public String history1(){
		String status="";
		
		
		return status;
	}

}
