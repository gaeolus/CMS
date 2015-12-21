package org.resl.gs1.cms.backend;
import java.sql.*;

import org.resl.gs1.cms.model.Slave;
public class Persist {
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/cms";
	   static final String DB_URL2 = "jdbc:mysql://localhost/";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "root";
	   
	   public void updateIdStatus(int id){
	        
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
           sql = "update idStatus set id="+id+" where type='slave'";
         
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
	   
	   public int selectFromIdStatus(){
	          int id= 100;
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
              sql = "Select * from idStatus";
            
              stmt.execute(sql);
              ResultSet rs=stmt.getResultSet();
              while (rs.next ()){
            	  id=rs.getInt("id");
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
	   public void insertIntoSlave(Slave slave){
	          
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
              sql = "insert into slave values('"+slave.getId()+"', '"+slave.getBizLocation()+"', '"+slave.getWritePoint()+"')";

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
	   public void test(){
	   Connection conn = null;
	   Statement stmt = null;
	try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT * FROM slave";
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	        
	         
	        String id = rs.getString("id");
	     	String bizLocation= rs.getString("bizLocation");
	     	String writePoint= rs.getString("writePoint");

	         //Display values
	         System.out.println("ID: " + id);
	         System.out.println("bizLocation: " + bizLocation);
	         System.out.println("writePoint: " + writePoint);
	         
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
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
	   }//end try
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
		      sql = " CREATE TABLE idStatus (id int, type varchar(100))";
		      stmt.executeUpdate(sql);
		      sql = "insert into idStatus values(0,'slave')";
		      stmt.executeUpdate(sql);
		      sql = "CREATE TABLE slave (id varchar(100), bizLocation varchar(100),"
		      		+ " writePoint varchar(100))";
		      stmt.executeUpdate(sql);
		      sql = "CREATE TABLE assignment (id int AUTO_INCREMENT PRIMARY KEY, "
		      		+ "slaveId varchar(100),type varchar(100), idFrom varchar(100), idTo varchar(100),"
		      		+ "reqIp varchar(100), reqTime  TIMESTAMP)";
		      stmt.executeUpdate(sql);
		      sql = "CREATE TABLE keyType (id int AUTO_INCREMENT PRIMARY KEY, "
		      		+ "type varchar(100), keyStart varchar(100),keyEnd varchar(100), keyLeft int)";
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
		      sql = "CREATE DATABASE cms";
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
		      String sql = "Drop DATABASE cms";
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
