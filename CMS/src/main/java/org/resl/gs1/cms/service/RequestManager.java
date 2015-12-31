package org.resl.gs1.cms.service;
/**
 * @author Yalew
 */
 
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.resl.gs1.cms.backend.Persist;
import org.resl.gs1.cms.model.KeyResponse;
import org.resl.gs1.cms.model.RegResponse;
import org.resl.gs1.cms.model.Slave;
 
@Path("/keyRequest")
public class RequestManager {
	@Path("{slaveId}/{keyType}/{prefix}/{ref}/{range}")
	@GET
	@Produces("application/json")
	public KeyResponse convertCtoFfromInput(@PathParam("slaveId") String slaveId,@PathParam("keyType") String keyType
			,@PathParam("prefix") int prefix,@PathParam("ref") int ref ,@PathParam("range") int range, @Context HttpServletRequest httpservlet) {
		KeyResponse resp=new KeyResponse();
		Persist persist=new Persist();
		if(persist.isIdExist(slaveId)){
			int assigned = persist.selectFromKeyType(keyType, prefix, ref);
			if(assigned!=-1){
				if(keyType.equals("gtin")){
					String from="urn:epc:id:sgtin:"+prefix+"."+0+ref+"."+assigned;
					assigned+=range;
					String to="urn:epc:id:sgtin:"+prefix+"."+0+ref+"."+assigned;
					resp.setFrom(from);
					resp.setTo(to);
					resp.setType(keyType);
					persist.updateKeyType(keyType, prefix, ref, assigned);
					Date date=new Date();
					Timestamp time=new Timestamp(date.getTime());
					persist.insertIntoAssignment(slaveId,keyType, from, to, httpservlet.getRemoteAddr(),time);
				}
				if(keyType.equals("gln")){
					String from="urn:epc:id:sgln:"+prefix+"."+ref+"."+assigned;
					assigned+=range;
					String to="urn:epc:id:sgln:"+prefix+"."+ref+"."+assigned;
					resp.setFrom(from);
					resp.setTo(to);
					resp.setType(keyType);
					persist.updateKeyType(keyType, prefix, ref, assigned);
					Date date=new Date();
					Timestamp time=new Timestamp(date.getTime());
					persist.insertIntoAssignment(slaveId,keyType, from, to, httpservlet.getRemoteAddr(),time);
				}
				if(keyType.equals("gsrn")){
					String from="urn:epc:id:gsrn:"+prefix+"."+ref;
					assigned+=range;
					String to=""+range;
					resp.setFrom(from);
					resp.setTo(to);
					resp.setType(keyType);
					persist.updateKeyType(keyType, prefix, ref, assigned);
					Date date=new Date();
					Timestamp time=new Timestamp(date.getTime());
					persist.insertIntoAssignment(slaveId,keyType, from, to, httpservlet.getRemoteAddr(),time);
				}
				
			}
		}
		
		return resp;
	}
}
