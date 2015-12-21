package org.resl.gs1.cms.service;
/**
 * @author Yalew
 */
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.resl.gs1.cms.backend.Persist;
import org.resl.gs1.cms.model.KeyResponse;
import org.resl.gs1.cms.model.RegResponse;
import org.resl.gs1.cms.model.Slave;
 
@Path("/keyRequest")
public class RequestManager {
	@Path("{slaveId}/{keyType}")
	@GET
	@Produces("application/json")
	public KeyResponse convertCtoFfromInput(@PathParam("slaveId") String slaveId,@PathParam("c") String keyType) {
		KeyResponse resp=new KeyResponse();
		
		
		return resp;
	}
}
