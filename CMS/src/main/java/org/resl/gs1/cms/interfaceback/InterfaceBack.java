package org.resl.gs1.cms.interfaceback;

import org.resl.gs1.cms.backend.Persist;

public class InterfaceBack {
	
	public void add(int companyPrifix,int itemRef,int locRef, int serviceRef){
		Persist persist=new Persist();
		// gtin configure
		if(itemRef!=0){
			persist.insertIntoKeyType("gtin", companyPrifix, itemRef);
		}
		// gln configure 
		if(locRef!=0){
			persist.insertIntoKeyType("gln", companyPrifix, locRef);
		}
		//gsrn configure
		if(serviceRef!=0){
			persist.insertIntoKeyType("gsrn", companyPrifix, serviceRef);
		}
		
	}
	
	public void remove(int companyPrifix,int itemRef,int locRef, int serviceRef){
		Persist persist=new Persist();
		// gtin configure
		if(itemRef!=0){
			persist.deleteKeyType("gtin", companyPrifix, itemRef);
		}
		// gln configure 
		if(locRef!=0){
			persist.deleteKeyType("gln", companyPrifix, locRef);
		}
		//gsrn configure 
		if(serviceRef!=0){
			persist.deleteKeyType("gsrn", companyPrifix, serviceRef);
		}
		
	}
	
	public String history(){
		Persist persist=new Persist();
		String result = persist.selectFromAssignment();
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
