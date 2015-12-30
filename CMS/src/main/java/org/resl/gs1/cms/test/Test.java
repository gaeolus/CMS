package org.resl.gs1.cms.test;

import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.Date;

import org.resl.gs1.cms.backend.Persist;

public class Test {

	public static void main(String[] args) {
		Persist persist=new Persist();
		//persist.test();
		//persist.dropTbales();
		//persist.dropDatabase();
		//persist.createDatabase();
		//persist.createTables();
		//persist.insertIntoKeyType("gtin", 12, 1201);
		//persist.deleteKeyType("gtin", 12, 1201);
		//System.out.println(persist.selectFromKeyType("gtin", 10, 1201));
		//persist.updateKeyType("gtin", 120, 1201,34);
		//persist.updateIdStatus(0);
		//int x=100;
		//Date date=new Date();
		//Timestamp time=new Timestamp(date.getTime());
		
		//persist.insertIntoAssignment("slaveId","type", "idFrom", "idTo", "reqip",time);
		//String result = persist.selectFromAssignment();
		//System.out.println(result);
		//x=persist.selectFromIdStatus();
		//System.out.println("Result = " + x);
//		if(persist.isIdExist("12"))
//			System.out.println("Id existes");
		
		String format="urn:epc:id:sgtin:120.01201.111";
		String GS1ElementString="";
		if(format.contains(".") && format.contains(":")){
			
		}
		String[] output =format.split(":");
		output=output[4].split("\\.");
		String companyPrefix=output[0];
		String serial = output[2];
		if(serial.contains("%")){
			System.out.println("Need conversion"); // on progress 
		}
		System.out.println(companyPrefix);
		System.out.println(serial);
		String ref=output[1].substring(1);
		String indicator=output[1].substring(0,1);
		System.out.println("indicator: "+indicator);
		System.out.println("prefix: "+ref);
		System.out.println("Test End");
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
		System.out.println(output2[0]);
		System.out.println(output2.length);
		System.out.println(sum1);
		System.out.println(sum2);
		int check=(10-((3*(sum1)+(sum2))%10))%10;
		System.out.println(check);
		GS1ElementString+="(01) "+companyPrefix+" "+indicator+" "+ref+" "+indicator+" "+check+" (21) "+serial;
		System.out.println(GS1ElementString);
		System.out.println(output[0]+output[1]);
	}

}
