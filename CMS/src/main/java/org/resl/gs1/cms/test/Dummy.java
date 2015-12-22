package org.resl.gs1.cms.test;
import java.net.InetAddress;

public class Dummy {
	public static void main(String[] args){
		try{
			InetAddress IP=InetAddress.getLocalHost();
			if (IP.getHostAddress().equals("192.168.0.2")){
				System.out.println("Yes!");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}	
}
