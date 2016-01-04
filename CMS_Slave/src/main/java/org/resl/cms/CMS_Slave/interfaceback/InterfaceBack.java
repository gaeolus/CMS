package org.resl.cms.CMS_Slave.interfaceback;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.resl.cms.CMS_Slave.Configuration.Configuration;
import org.resl.cms.CMS_Slave.backend.Persist;
import org.resl.cms.CMS_Slave.model.KeyResponse;
import org.resl.cms.CMS_Slave.model.KeyStatus;
import org.resl.cms.CMS_Slave.model.KeyType;
import org.resl.cms.CMS_Slave.service.Request;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class InterfaceBack {
	
	public void configure(String  ip_n,String port_n, String bizLocation_n, String writePoint_n){
		Configuration.ip =ip_n;
		Configuration.port = port_n;
		Configuration.bizLocation = bizLocation_n;
		Configuration.writePoint = writePoint_n;
		Configuration.configured=true;
		
	}
	public String register() throws JsonParseException, JsonMappingException, IOException{
		String status="";
		Persist persist=new Persist();
		String id=persist.selectFromSlave();
		if(!id.equals("")){
			status ="already registerd: id="+ id;
		}else{
			if(Configuration.configured){
				Request request=new Request();
				Configuration.id=(request.register()).getId();
				
				status ="registration succeeded";
				
			}else{
				status="Configuration is needed";
			}
		}
		
		return status;
	}
	
	public String configure_key(KeyType key){
		String status="";
		Persist persist=new Persist();
		persist.insertIntoKeyType(key);
		status+= "Key Configured";
		return status;
	}
	public String request(String type, int range){
		String status="";
		Persist persist=new Persist();
		Request request=new Request();
		String id=persist.selectFromSlave();
		if(!id.equals("")){
			KeyType keyType=persist.selectFromKeyType(type);
			if(!keyType.getType().equals("Doesn't Exist")){
				try {
					if(!(persist.selectFromKeyStatus(keyType).getLeft()>0)){
						KeyResponse keyResponse=request.keyRequest(id, keyType.getType(), keyType.getPrefix(), keyType.getRef(), range);
						persist.insertIntokeyStatus(keyType, range,keyResponse.getFrom());
						status="ID request succeed";
					}else{
						status="There is ID left";
					}
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				status="Need key configuration";
			}
		}else{
			status="Need id registration";
		}
		return status;
	}
	public String request(KeyType key, int range){
		String status="";
		Persist persist=new Persist();
		Request request=new Request();
		String id=persist.selectFromSlave();
		if(!id.equals("")){
			KeyType keyType=persist.selectFromKeyType(key);
			if(!keyType.getType().equals("Doesn't Exist")){
				try {
					if(!(persist.selectFromKeyStatus(keyType).getLeft()>0)){
						KeyResponse keyResponse=request.keyRequest(id, keyType.getType(), keyType.getPrefix(), keyType.getRef(), range);
						persist.insertIntokeyStatus(keyType, range,keyResponse.getFrom());
						status="ID request succeed";
					}else{
						status="There is ID left";
					}
										
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				status="Need key configuration";
			}
		}else{
			status="Need id registration";
		}
		return status;
	}
	public String issue(KeyType key){
		String status="";
		String EPC="";
		Persist persist=new Persist();
		if(Configuration.registerd){
			KeyType keyType=persist.selectFromKeyType(key);
			if(!keyType.getType().equals("Doesn't Exist")){
				KeyStatus keyStatus=persist.selectFromKeyStatus(keyType);
				if(keyStatus.getLeft()>0){
					if(keyType.getType().equals("gtin")){
						String[] output =keyStatus.getIdFrom().split(":");
						EPC+=output[0]+":"+output[1]+":"+output[2]+":"+output[3]+":";
						output=output[4].split("\\.");
						String companyPrefix=output[0];
						String ref=output[1];
						String serial =""+(Integer.parseInt(output[2])+1); 
						EPC+=companyPrefix+"."+ref+"."+serial;
						
						persist.updatekeyStatus(keyType,keyStatus.getLeft()-1,EPC);
						Date date=new Date();
						Timestamp time=new Timestamp(date.getTime());
						persist.insertIntoSpecificLog(keyType.getType(), EPC, time);
						status+="EPC URI FORM: "+EPC+"\n";
						status+="GS! ELEMENT STRING FORM: "+EPCURIToGS1String(EPC);
					}else if(keyType.getType().equals("gln")){
						String[] output =keyStatus.getIdFrom().split(":");
						EPC+=output[0]+":"+output[1]+":"+output[2]+":"+output[3]+":";
						output=output[4].split("\\.");
						String companyPrefix=output[0];
						String ref=output[1];
						String serial =""+(Integer.parseInt(output[2])+1); 
						EPC+=companyPrefix+"."+ref+"."+serial;
						
						persist.updatekeyStatus(keyType,keyStatus.getLeft()-1,EPC);
						Date date=new Date();
						Timestamp time=new Timestamp(date.getTime());
						persist.insertIntoSpecificLog(keyType.getType(), EPC, time);
						status+="EPC URI FORM: "+EPC+"\n";
						status+="GS! ELEMENT STRING FORM: "+EPCURIToGS1String(EPC);
					}else if(keyType.getType().equals("gsrn")){
						EPC+=keyStatus.getIdFrom();
						persist.updatekeyStatus(keyType,keyStatus.getLeft(),EPC);
						Date date=new Date();
						Timestamp time=new Timestamp(date.getTime());
						persist.insertIntoSpecificLog(keyType.getType(), EPC, time);
						status+="EPC URI FORM: "+EPC+"\n";
						status+="GS! ELEMENT STRING FORM: "+EPCURIToGS1String(EPC);
					}
				}else{
					status="All id are assigned. Request ID";
					return status;
				}
			}
		}else{
			String id=persist.selectFromSlave();
			if(!id.equals("")){
				Configuration.registerd=true;
				Configuration.id=id;
				KeyType keyType=persist.selectFromKeyType(key);
				if(!keyType.getType().equals("Doesn't Exist")){
					KeyStatus keyStatus=persist.selectFromKeyStatus(keyType);
					if(keyStatus.getLeft()>0){
						if(keyType.getType().equals("gtin")){
							String[] output =keyStatus.getIdFrom().split(":");
							for (int i=0; i<output.length; i++){
								System.out.println("output number " + i + ": " + output[i]);
							}
							EPC+=output[0]+":"+output[1]+":"+output[2]+":"+output[3]+":";
							output=output[4].split("\\.");
							String companyPrefix=output[0];
							String ref=output[1];
							String serial =""+(Integer.parseInt(output[2])+1); 
							EPC+=companyPrefix+"."+ref+"."+serial;
							
							persist.updatekeyStatus(keyType,keyStatus.getLeft()-1,EPC);
							Date date=new Date();
							Timestamp time=new Timestamp(date.getTime());
							persist.insertIntoSpecificLog(keyType.getType(), EPC, time);
							status+="EPC URI FORM: "+EPC+"\n";
							status+="GS! ELEMENT STRING FORM: "+EPCURIToGS1String(EPC);
						}else if(keyType.getType().equals("gln")){
							String[] output =keyStatus.getIdFrom().split(":");
							EPC+=output[0]+":"+output[1]+":"+output[2]+":"+output[3]+":";
							output=output[4].split("\\.");
							String companyPrefix=output[0];
							String ref=output[1];
							String serial =""+(Integer.parseInt(output[2])+1); 
							EPC+=companyPrefix+"."+ref+"."+serial;
							
							persist.updatekeyStatus(keyType,keyStatus.getLeft()-1,EPC);
							Date date=new Date();
							Timestamp time=new Timestamp(date.getTime());
							persist.insertIntoSpecificLog(keyType.getType(), EPC, time);
							status+="EPC URI FORM: "+EPC+"\n";
							status+="GS! ELEMENT STRING FORM: "+EPCURIToGS1String(EPC);
						}else if(keyType.getType().equals("gsrn")){
							EPC+=keyStatus.getIdFrom();
							persist.updatekeyStatus(keyType,keyStatus.getLeft(),EPC);
							Date date=new Date();
							Timestamp time=new Timestamp(date.getTime());
							persist.insertIntoSpecificLog(keyType.getType(), EPC, time);
							status+="EPC URI FORM: "+EPC+"\n";
							status+="GS! ELEMENT STRING FORM: "+EPCURIToGS1String(EPC);
						}
					}else{
						status="All id are assigned. Request ID";
						return status;
					}
				}
			}else{
				status="Need id registration";
				return status;
			}
		}
		
		return status;
	}
	public String issue(String type){
		String status="";
				
		return status;
	}
	
	public String historyGeneralLog(){
		String result="";
		Persist persist=new Persist();
		result=persist.selectFromGeneralLogt();		
		return result;
	}
	public String historySpecificLog(){
		String result="";
		Persist persist=new Persist();
		result=persist.selectFromSpecificLogt();		
		return result;
	}
	public String EPCURIToGS1String(String EPCURI){
		String GS1String="";
		if(EPCURI.contains(".") && EPCURI.contains(":")){
			String[] output =EPCURI.split(":");
			String keyType=output[3];
			if(keyType.equals("sgtin")){
				output=output[4].split("\\.");
				String companyPrefix=output[0];
				String serial = output[2];
				if(serial.contains("%")){
					System.out.println("Need conversion"); // on progress 
				}
				String ref=output[1].substring(1);
				String indicator=output[1].substring(0,1);
				String total=output[0]+output[1];
				String[] output2 =total.split("");
				int sum1=0, sum2=0;
				int size=output2.length;
				if((size%2)!=0){
					sum1+=Integer.parseInt(output2[size-1]);
					size--;
				}
				for(int i=0; i<size; i+=2){
					sum1+=Integer.parseInt(output2[i]);
					sum2+=Integer.parseInt(output2[i+1]);
				}
				int check=(10-((3*(sum1)+(sum2))%10))%10;
				GS1String+="(01) "+indicator+" "+companyPrefix+" "+ref+" "+check+" (21) "+serial;
			}
			
			if(keyType.equals("sgln")){
				output=output[4].split("\\.");
				String companyPrefix=output[0];
				String ref=output[1];
				String serial = output[2];
				if(serial.contains("%")){
					System.out.println("Need conversion"); // on progress 
				}	
				String total=output[0]+output[1];
				String[] output2 =total.split("");
				int sum1=0, sum2=0;
				int size=output2.length;
				if((size%2)!=0){
					sum2+=Integer.parseInt(output2[size-1]);
					size--;
				}
				for(int i=0; i<size; i+=2){
					sum1+=Integer.parseInt(output2[i+1]);
					sum2+=Integer.parseInt(output2[i]);
				}
				int check=(10-((3*(sum1)+(sum2))%10))%10;
				if(serial.equals("0")){
					GS1String+="(414) "+companyPrefix+" "+ref+" "+check;
				}else{
					GS1String+="(414) "+companyPrefix+" "+ref+" "+check+" (254) "+serial;
				}
				
			}
			if(keyType.equals("gsrn")){
				output=output[4].split("\\.");
				String companyPrefix=output[0];
				String ref=output[1];	
				String total=output[0]+output[1];
				String[] output2 =total.split("");
				int sum1=0, sum2=0;
				int size=output2.length;
				if((size%2)!=0){
					sum1+=Integer.parseInt(output2[size-1]);
					size--;
				}
				for(int i=0; i<size; i+=2){
					sum1+=Integer.parseInt(output2[i]);
					sum2+=Integer.parseInt(output2[i+1]);
				}
				int check=(10-((3*(sum1)+(sum2))%10))%10;
				GS1String+="(8018) "+companyPrefix+" "+ref+" "+check;
				
			}
			if(keyType.equals("gtin")){
				output=output[4].split("\\.");
				String companyPrefix=output[0];
				String ref=output[1].substring(1);
				String indicator=output[1].substring(0,1);
				String total=output[0]+output[1];
				String[] output2 =total.split("");
				int sum1=0, sum2=0;
				int size=output2.length;
				if((size%2)!=0){
					sum1+=Integer.parseInt(output2[size-1]);
					size--;
				}
				for(int i=0; i<size; i+=2){
					sum1+=Integer.parseInt(output2[i]);
					sum2+=Integer.parseInt(output2[i+1]);
				}
				int check=(10-((3*(sum1)+(sum2))%10))%10;
				GS1String+="(01) "+indicator+" "+companyPrefix+" "+ref+" "+check;
			}
			System.out.println(keyType);
			
			
		}
		
		return GS1String;
	}
	
	public String GS1StringToEPCURI(String GS1String){
		String EPCURI="";
		String[] output =GS1String.split(" ");
		String keyType=output[0].split("[\\(\\)]")[1];
		//String keyType=key[1];
		if(keyType.equals("01")){
			if(output.length>6){
				String indicator=output[1];
				String companyPrefix=output[2];
				String ref=output[3];
				String serial = output[6];
				if(serial.contains("/")){
					System.out.println("Need conversion"); // on progress 
				}
				EPCURI+="urn:epc:id:sgtin:"+companyPrefix+"."+indicator+ref+"."+serial;
			}else{
				String indicator=output[1];
				String companyPrefix=output[2];
				String ref=output[3];
				EPCURI+="urn:epc:id:gtin:"+companyPrefix+"."+indicator+ref;
			}
		}
		if(keyType.equals("414")){
			if(output.length>4){
				String companyPrefix=output[1];
				String ref=output[2];
				String serial = output[4];
				if(serial.contains("/")){
					System.out.println("Need conversion"); // on progress 
				}
				EPCURI+="urn:epc:id:sgln:"+companyPrefix+"."+ref+"."+serial;
			}else{
				String companyPrefix=output[1];
				String ref=output[2];
				EPCURI+="urn:epc:id:sgln:"+companyPrefix+"."+ref+"."+0;
			}
			
		}
		if(keyType.equals("8018")){
			String companyPrefix=output[1];
			String ref=output[2];
			EPCURI+="urn:epc:id:gsrn:"+companyPrefix+"."+ref;
		}
		System.out.println(keyType);
		System.out.println(output[1]);
		System.out.println(output[2]);
		//if()urn:epc:id:sgtin:
		
		return EPCURI;
	}

}
