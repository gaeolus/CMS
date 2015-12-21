package org.resl.cms.CMS_Slave;

import java.io.IOException;

import org.resl.cms.CMS_Slave.Configuration.Configuration;
import org.resl.cms.CMS_Slave.model.Account;
import org.resl.cms.CMS_Slave.service.Request;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JsonParseException, JsonMappingException, IOException
    {
    	Account account=new Account();
    	Request request= new Request();
    	Configuration conf= new Configuration();
    	account=request.register();
    	System.out.println(account.getId());
    	//Configuration.logger.info(account.getId());
        System.out.println( "Program End!" );
    }
    
    
}
