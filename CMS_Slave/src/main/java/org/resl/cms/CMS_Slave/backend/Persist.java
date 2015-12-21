package org.resl.cms.CMS_Slave.backend;

import java.sql.*;

import org.resl.cms.CMS_Slave.model.Account;
public class Persist {
    // JDBC driver name and database URL
       static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
       static final String DB_URL = "jdbc:mysql://localhost/cms_slave";
       static final String DB_URL2 = "jdbc:mysql://localhost/";

       //  Database credentials
       static final String USER = "root";
       static final String PASS = "";
      
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
              sql = "CREATE TABLE keyType (id int AUTO_INCREMENT PRIMARY KEY, "
                      + "type varchar(100), keyStart varchar(100),keyEnd varchar(100))";
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
