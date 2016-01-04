package org.resl.cms.CMS_Slave.backend;

import java.sql.*;

import org.resl.cms.CMS_Slave.model.Account;
import org.resl.cms.CMS_Slave.model.KeyStatus;
import org.resl.cms.CMS_Slave.model.KeyType;
public class Persist {
    // JDBC driver name and database URL
       static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
       static final String DB_URL = "jdbc:mysql://localhost:9999/cms_slave";
       static final String DB_URL2 = "jdbc:mysql://localhost:9999/";

       //  Database credentials
       static final String USER = "root";
       static final String PASS = "resl18519";
      
       public void insertIntoSlave(Account account){
           
           Connection conn = null;
           Statement stmt = null;
        try{
              
              Class.forName("com.mysql.jdbc.Driver");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              stmt = conn.createStatement();
              String sql;
              sql = "insert into slave values('"+account.getId()+"', '"+account.getBizLocation()+"', '"+account.getWritePoint()+"')";
              stmt.executeUpdate(sql);
              stmt.close();
              conn.close();
           }catch(SQLException se){
              //Handle errors for JDBC
              se.printStackTrace();
           }catch(Exception e){
              //Handle errors for Class.forName
              e.printStackTrace();
           }finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           }
          
       }
       
       public void insertIntoKeyType(KeyType key){
           
           Connection conn = null;
           Statement stmt = null;
        try{
              
              Class.forName("com.mysql.jdbc.Driver");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              stmt = conn.createStatement();
              String sql;
              sql = "Select * from keyType where type='"+key.getType()+"' and companyPrefix="+key.getPrefix()+" and ref="+key.getRef();
  			
  			stmt.execute(sql);
  			ResultSet rs=stmt.getResultSet();
  			int row=0;
  			if(rs.last()){
  				row=rs.getRow();
  			}
  			
  			if(row == 0){
                sql = "insert into KeyType values('"+key.getType()+"', "+key.getPrefix()+", "+key.getRef()+")";
                stmt.executeUpdate(sql);
  			}

              stmt.close();
              conn.close();
           }catch(SQLException se){
              //Handle errors for JDBC
              se.printStackTrace();
           }catch(Exception e){
              //Handle errors for Class.forName
              e.printStackTrace();
           }finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           }
          
       }
       public void insertIntoGeneralLog(String idFrom, String idTo, Timestamp reqTime){
           
           Connection conn = null;
           Statement stmt = null;
        try{
              
              Class.forName("com.mysql.jdbc.Driver");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              stmt = conn.createStatement();
              String sql;
              
            sql = "insert into generalLog(idFrom, idTo, resTime ) values('"+idFrom+"', '"+idTo+"', '"+reqTime+"')";
            stmt.executeUpdate(sql);
  			

              stmt.close();
              conn.close();
           }catch(SQLException se){
              //Handle errors for JDBC
              se.printStackTrace();
           }catch(Exception e){
              //Handle errors for Class.forName
              e.printStackTrace();
           }finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           }
          
       }
       
       public void insertIntoSpecificLog(String type, String idAssigned, Timestamp assignmentTime){
           
           Connection conn = null;
           Statement stmt = null;
        try{
              
              Class.forName("com.mysql.jdbc.Driver");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              stmt = conn.createStatement();
              String sql;
            sql = "insert into specificLog(type, keyAssigned, assignedTime ) values('"+type+"', '"+idAssigned+"', '"+assignmentTime+"')";
            stmt.executeUpdate(sql);
  			

              stmt.close();
              conn.close();
           }catch(SQLException se){
              //Handle errors for JDBC
              se.printStackTrace();
           }catch(Exception e){
              //Handle errors for Class.forName
              e.printStackTrace();
           }finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           }
          
       }     
       public void insertIntokeyStatus(KeyType key, int left, String from){
           
           Connection conn = null;
           Statement stmt = null;
        try{
              
              Class.forName("com.mysql.jdbc.Driver");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              stmt = conn.createStatement();
              String sql;
              
              sql = "Select * from keyStatus where type='"+key.getType()+"' and companyPrefix="+key.getPrefix()+" and ref="+key.getRef();
    			
    			stmt.execute(sql);
    			ResultSet rs=stmt.getResultSet();
    			int row=0;
    			if(rs.last()){
    				row=rs.getRow();
    			}
    			
    			if(row == 0){
                  sql = "insert into keyStatus values('"+key.getType()+"', "+key.getPrefix()+", "+key.getRef()+", "+left+",'"+from+"')";
                  stmt.executeUpdate(sql);
    			}else{
					sql = "update  keyStatus set leftId="+left+", idFrom='"+from+"  where type='"+key.getType()+"' and companyPrefix="+key.getPrefix()+" and ref="+key.getRef();
	                stmt.executeUpdate(sql);
    			}
              stmt.close();
              conn.close();
           }catch(SQLException se){
              //Handle errors for JDBC
              se.printStackTrace();
           }catch(Exception e){
              //Handle errors for Class.forName
              e.printStackTrace();
           }finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           }
          
       }
       public void updatekeyStatus(KeyType key, int left,String from){
           
           Connection conn = null;
           Statement stmt = null;
        try{
              
              Class.forName("com.mysql.jdbc.Driver");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              stmt = conn.createStatement();
              String sql;
              
				sql = "update  keyStatus set leftId="+left+", idFrom='"+from+"'  where type='"+key.getType()+"' and companyPrefix="+key.getPrefix()+" and ref="+key.getRef();
                stmt.executeUpdate(sql);
    			
              stmt.close();
              conn.close();
           }catch(SQLException se){
              //Handle errors for JDBC
              se.printStackTrace();
           }catch(Exception e){
              //Handle errors for Class.forName
              e.printStackTrace();
           }finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           }
          
       }     
       public KeyStatus selectFromKeyStatus(KeyType key){
    	   KeyStatus keyStatus=new KeyStatus();
           Connection conn = null;
           Statement stmt = null;
        try{
              
              Class.forName("com.mysql.jdbc.Driver");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              stmt = conn.createStatement();
              String sql;
              
              sql = "Select * from keyStatus where type='"+key.getType()+"' and companyPrefix="+key.getPrefix()+" and ref="+key.getRef();
    			
    			stmt.execute(sql);
    			ResultSet rs=stmt.getResultSet();
    			while(rs.next()){
    				keyStatus.setLeft(rs.getInt("leftId"));
    				keyStatus.setIdFrom(rs.getString("idFrom"));
    			}
    			
              stmt.close();
              conn.close();
           }catch(SQLException se){
              //Handle errors for JDBC
              se.printStackTrace();
           }catch(Exception e){
              //Handle errors for Class.forName
              e.printStackTrace();
           }finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           }
          return keyStatus;
       }
   	public String selectFromGeneralLogt(){
        String result="";
		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql;
			
			sql = "Select * from generalLog";
			
			stmt.execute(sql);
			ResultSet rs=stmt.getResultSet();//(slaveId, type, idFrom, idTo, reqIp, reqTime) 
			while (rs.next ()){
				result+= String.format("%-35s", rs.getString("idFrom"));
				result+= String.format("%-35s", rs.getString("idTo"));
				result+= String.format("%-15s", rs.getTimestamp("resTime"));
				result+="\n";
				//assigned=rs.getInt("assigned");
			}
			
				
			
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}
		return result;

	}
   	
   	public String selectFromSpecificLogt(){
        String result="";
		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql;
			//CREATE TABLE specificLog (id int AUTO_INCREMENT PRIMARY KEY, "
            //        + "type varchar(100), keyAssigned varchar(100),assignedTime TIMESTAMP)";
			sql = "Select * from specificLog";
			
			stmt.execute(sql);
			ResultSet rs=stmt.getResultSet();//(slaveId, type, idFrom, idTo, reqIp, reqTime) 
			while (rs.next ()){
				result+= String.format("%-15s", rs.getString("type"));
				result+= String.format("%-35s", rs.getString("keyAssigned"));
				result+= String.format("%-15s", rs.getTimestamp("assignedTime"));
				result+="\n";
				//assigned=rs.getInt("assigned");
			}
			
				
			
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}
		return result;

	}
       public KeyType selectFromKeyType(String type){
    	   KeyType keyType=new KeyType();
    	   keyType.setType("Doesn't Exist");
           Connection conn = null;
           Statement stmt = null;
        try{
              
              Class.forName("com.mysql.jdbc.Driver");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              stmt = conn.createStatement();
              String sql;
              sql = "Select * from KeyType where type='"+type+"'";//where id='"+account.getId()+"' and bizLocation='"+account.getBizLocation()+"' and writePoint='"+account.getWritePoint()+"'";
  			
  			stmt.execute(sql);
  			ResultSet rs=stmt.getResultSet();
  			while (rs.next ()){
  				keyType.setType(rs.getString("type"));//
  				keyType.setPrefix(rs.getInt("companyPrefix"));
  				keyType.setRef(rs.getInt("ref"));
  			}
              stmt.close();
              conn.close();
           }catch(SQLException se){
              //Handle errors for JDBC
              se.printStackTrace();
           }catch(Exception e){
              //Handle errors for Class.forName
              e.printStackTrace();
           }finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           }
          return keyType;
       }
       public KeyType selectFromKeyType(KeyType key){
    	   KeyType keyType=new KeyType();
    	   keyType.setType("Doesn't Exist");
           Connection conn = null;
           Statement stmt = null;
        try{
              
              Class.forName("com.mysql.jdbc.Driver");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              stmt = conn.createStatement();
              String sql;
              sql = "Select * from keyType where type='"+key.getType()+"' and companyPrefix="+key.getPrefix()+" and ref="+key.getRef();
  			stmt.execute(sql);
  			ResultSet rs=stmt.getResultSet();
  			while (rs.next ()){
  				keyType.setType(rs.getString("type"));//
  				keyType.setPrefix(rs.getInt("companyPrefix"));
  				keyType.setRef(rs.getInt("ref"));
  				
  			}
              stmt.close();
              conn.close();
           }catch(SQLException se){
              //Handle errors for JDBC
              se.printStackTrace();
           }catch(Exception e){
              //Handle errors for Class.forName
              e.printStackTrace();
           }finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           }
          return keyType;
       }
   public String selectFromSlave(){
           String id="";
           Connection conn = null;
           Statement stmt = null;
        try{
              
              Class.forName("com.mysql.jdbc.Driver");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              stmt = conn.createStatement();
              String sql;
              sql = "Select * from slave ";//where id='"+account.getId()+"' and bizLocation='"+account.getBizLocation()+"' and writePoint='"+account.getWritePoint()+"'";
  			
  			stmt.execute(sql);
  			ResultSet rs=stmt.getResultSet();
  			while (rs.next ()){
  				id=rs.getString("id");
  			}
              stmt.close();
              conn.close();
           }catch(SQLException se){
              //Handle errors for JDBC
              se.printStackTrace();
           }catch(Exception e){
              //Handle errors for Class.forName
              e.printStackTrace();
           }finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           }
          return id;
       }
     
      public void dropTbales(){
          
           Connection conn = null;
           Statement stmt = null;
        try{
              //STEP 2: Register JDBC driver
              Class.forName("com.mysql.jdbc.Driver");

              //STEP 3: Open a connection
              System.out.println("Connecting to database...");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              System.out.println("Creating statement...");
              stmt = conn.createStatement();
              String sql;
              sql = "drop table slave";
              stmt.executeUpdate(sql);
              sql = "Drop table idStatus";
              stmt.executeUpdate(sql);
              sql = "Drop table assignment";
              stmt.executeUpdate(sql);
              sql = "Drop table keyType";
              stmt.executeUpdate(sql);
              stmt.close();
              conn.close();
           }catch(SQLException se){
              //Handle errors for JDBC
              se.printStackTrace();
           }catch(Exception e){
              //Handle errors for Class.forName
              e.printStackTrace();
           }finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           }
          
       }
     
      public void createTbales(){
          
           Connection conn = null;
           Statement stmt = null;
        try{
              //STEP 2: Register JDBC driver
              Class.forName("com.mysql.jdbc.Driver");

              //STEP 3: Open a connection
              System.out.println("Connecting to database...");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              System.out.println("Creating statement...");
              stmt = conn.createStatement();
              String sql;
              sql = "CREATE TABLE specificLog (id int AUTO_INCREMENT PRIMARY KEY, "
                      + "type varchar(100), keyAssigned varchar(100),assignedTime TIMESTAMP)";
              stmt.executeUpdate(sql);
              sql = "CREATE TABLE slave (id varchar(100), bizLocation varchar(100),"
                      + " writePoint varchar(100))";
              stmt.executeUpdate(sql);
              sql = "CREATE TABLE generalLog (id int AUTO_INCREMENT PRIMARY KEY, "
                      + " idFrom varchar(100), idTo varchar(100),"
                      + " resTime  TIMESTAMP)";
              stmt.executeUpdate(sql);
              sql = "CREATE TABLE KeyType (type varchar(100), companyPrefix int,"
                      + " ref int)";
              stmt.executeUpdate(sql);
              sql = "CREATE TABLE keyStatus (type varchar(100), companyPrefix int,"
                      + " ref int, leftId int, idFrom varchar(100))";
              stmt.executeUpdate(sql);
              stmt.close();
              conn.close();
           }catch(SQLException se){
              //Handle errors for JDBC
              se.printStackTrace();
           }catch(Exception e){
              //Handle errors for Class.forName
              e.printStackTrace();
           }finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           }
          
       }
     
      
      public void createDatabase(){
          
           Connection conn = null;
           Statement stmt = null;
        try{
              //STEP 2: Register JDBC driver
              Class.forName("com.mysql.jdbc.Driver");

              //STEP 3: Open a connection
              System.out.println("Connecting to database...");
              conn = DriverManager.getConnection(DB_URL2,USER,PASS);
              System.out.println("Creating statement...");
              stmt = conn.createStatement();
              String sql;
              sql = "CREATE DATABASE cms_slave";
              stmt.executeUpdate(sql);
              stmt.close();
              conn.close();
           }catch(SQLException se){
              //Handle errors for JDBC
              se.printStackTrace();
           }catch(Exception e){
              //Handle errors for Class.forName
              e.printStackTrace();
           }finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           }
          
       }
     
      public void dropeDatabase(){
          
           Connection conn = null;
           Statement stmt = null;
        try{
              //STEP 2: Register JDBC driver
              Class.forName("com.mysql.jdbc.Driver");

              //STEP 3: Open a connection
              System.out.println("Connecting to database...");
              conn = DriverManager.getConnection(DB_URL2,USER,PASS);
              System.out.println("Creating statement...");
              stmt = conn.createStatement();
              String sql = "Drop DATABASE cms_slave";
              stmt.executeUpdate(sql);
              stmt.close();
              conn.close();
           }catch(SQLException se){
              //Handle errors for JDBC
              se.printStackTrace();
           }catch(Exception e){
              //Handle errors for Class.forName
              e.printStackTrace();
           }finally{
              //finally block used to close resources
              try{
                 if(stmt!=null)
                    stmt.close();
              }catch(SQLException se2){
              }// nothing we can do
              try{
                 if(conn!=null)
                    conn.close();
              }catch(SQLException se){
                 se.printStackTrace();
              }//end finally try
           }
          
       }
}
