package org.resl.gs1.cms.service;
/**
 * @author Yalew
 */
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.resl.gs1.cms.backend.Persist;
import org.resl.gs1.cms.model.RegResponse;
import org.resl.gs1.cms.model.Slave;
 
@Path("/register")
public class Register {
	
	@Path("{bizLocation}/{writePoint}")
	@GET
	@Produces("application/json")
	public RegResponse registerSlave(@PathParam("bizLocation") String bizLocation,@PathParam("writePoint") String writePoint) {
		Persist persist=new Persist();
		int intRes=persist.selectFromIdStatus();
		intRes++;
		String result = "slave_"+intRes;
		persist.updateIdStatus(intRes);
		Slave slave=new Slave();
		slave.setId(result);
		slave.setBizLocation(bizLocation);
		slave.setWritePoint(writePoint);
		persist.insertIntoSlave(slave);
		
		RegResponse regResponse=new RegResponse();
		regResponse.setId(result);
		//return "<Account>" 	+ "<id>" + result + "</id>" +       "</Account>";
		return regResponse;
	}

	
}
