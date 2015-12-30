package org.resl.cms.CMS_Slave.Configuration;

import java.util.logging.Logger;

import org.resl.cms.CMS_Slave.model.KeyType;

public class Configuration {
	
	public static String  ip ="192.168.56.1";
	public static String port = "8080";
	public static String bizLocation = "bizLocation1";
	public static String writePoint = "writePoint";
	public static String id = "id";
	public static Logger logger;
	public static Boolean configured=false;
	public static Boolean registerd=false;
	
	
	public Configuration(){
		ip ="192.168.56.1";
		port = "8080";
		bizLocation = "bizLocation1";
		writePoint = "writePoint";
		setLogger(logger);
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
