package org.resl.cms.CMS_Slave.Configuration;

import java.util.logging.Logger;

public class Configuration {
	
	public static String  ip ="143.248.55.139";
	public static String port = "8787";
	public static String bizLocation = "bizLocation1";
	public static String writePoint = "writePoint";
	public static String id = "id";
	public static Logger logger;
	public static Boolean configured=false;
	public static Boolean registerd=false;
	
	
	public Configuration(){
		ip ="143.248.55.139";
		port = "8787";
		bizLocation = "bizLocation1";
		writePoint = "writePoint";
		setLogger(logger);
	}
	public void configure(String  ip_n,String port_n, String bizLocation_n, String writePoint_n){
		ip =ip_n;
		port = port_n;
		bizLocation = bizLocation_n;
		writePoint = writePoint_n;
		configured=true;
	}
	
	public static String getBizLocation() {
		return bizLocation;
	}
	public static void setBizLocation(String bizLocation) {
		Configuration.bizLocation = bizLocation;
	}
	public static String getWritePoint() {
		return writePoint;
	}
	public static void setWritePoint(String writePoint) {
		Configuration.writePoint = writePoint;
	}
	public static Logger getLogger() {
		return logger;
	}
	public static void setLogger(Logger logger) {
		Configuration.logger = logger;
	}
	public static String getIp() {
		return ip;
	}
	public static void setIp(String ip) {
		Configuration.ip = ip;
	}
	public static String getPort() {
		return port;
	}
	public static void setPort(String port) {
		Configuration.port = port;
	}
	
	
	
	

}
