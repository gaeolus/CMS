package org.resl.cms.CMS_Slave.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.resl.cms.CMS_Slave.Configuration.Configuration;
import org.resl.cms.CMS_Slave.backend.Persist;
import org.resl.cms.CMS_Slave.model.Account;
import org.resl.cms.CMS_Slave.model.RegResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Request {
	
	public Account register () throws JsonParseException, JsonMappingException, IOException{
		Account account=new Account();
		account.setBizLocation(Configuration.bizLocation);
		account.setWritePoint(Configuration.writePoint);
		String urlStr="http://"+Configuration.ip+":"+Configuration.port+
				"/CMS/service/register/"+account.getBizLocation()+
				"/"+account.getWritePoint();	
		String result=getQueryResult(urlStr);
		RegResponse ob = new ObjectMapper().readValue(result, RegResponse.class);
		account.setId(ob.getId());
		Persist persist=new Persist();
		persist.insertIntoSlave(account);
		return account;
		
	}
	
	public String getQueryResult(String urlStr){
		String outPut= "";
		try{
			URL url =new URL(urlStr);
			HttpURLConnection conn =(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode() !=200){
				throw new RuntimeException("Failed :HTTP error code :"+ conn.getResponseCode());
			}
			BufferedReader br= new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String readlines;
			
			while((readlines = br.readLine())!=null){
				outPut+=readlines;
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		return outPut;
	}

}
