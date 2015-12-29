package org.resl.gs1.cms.interfaceback;

import org.resl.gs1.cms.backend.Persist;

public class InterfaceBack {
	
	public void configure(int companyPrifix,int itemRef,int locRef, int serviceRef){
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
	
	public String history(){
		String result="";
		
		
		
		return result;
		
	}
	
	public String EPCURIToGS1String(String EPCURIT){
		String GS1String="";
		
		
		return GS1String;
	}
	
	public String GS1StringToEPCURI(String EPCURIT){
		String GS1String="";
		
		
		return GS1String;
	}
}
