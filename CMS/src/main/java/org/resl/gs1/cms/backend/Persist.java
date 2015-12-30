package org.resl.gs1.cms.backend;
import java.net.InetAddress;
import java.sql.*;

import org.resl.gs1.cms.model.Slave;
public class Persist {
	// JDBC driver name and database URL
	static String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static String DB_URL = "jdbc:mysql://localhost/cms";
	static String DB_URL2 = "jdbc:mysql://localhost/";

	//  Database credentials
	static String USER = "root";
	static String PASS = "root";
	
	public Persist(){
		try{
			InetAddress IP=InetAddress.getLocalHost();
			if (IP.getHostAddress().equals("192.168.0.2")){
				DB_URL = "jdbc:mysql://localhost:9999/cms";
				DB_URL2 = "jdbc:mysql://localhost:9999/";
				
				USER = "root";
				PASS = "resl18519";
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

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
	public int selectFromKeyType(String type, int companyPrefix, int ref){
        int assigned=-1;
		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql;
			
			sql = "Select * from keyType where type='"+type+"' and companyPrefix="+companyPrefix+" and ref="+ref;
			
			stmt.execute(sql);
			ResultSet rs=stmt.getResultSet();
			while (rs.next ()){
				assigned=rs.getInt("assigned");
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
		return assigned;

	}
	public void updateKeyType(String type, int companyPrefix, int ref, int assigned){
        
		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql;
			
			sql = "Select * from keyType where type='"+type+"' and companyPrefix="+companyPrefix+" and ref="+ref;
			
			stmt.execute(sql);
			ResultSet rs=stmt.getResultSet();
			int row=0;
			if(rs.last()){
				row=rs.getRow();
			}
			
			if(row > 0){
				//type varchar(100),companyPrefix int, ref int, assigned int
				sql = "update keyType set assigned="+assigned+" where type='"+type+"' and companyPrefix="+companyPrefix+" and ref="+ref;

				stmt.executeUpdate(sql);
				System.out.println("updated");
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
	public void insertIntoKeyType(String type, int companyPrefix, int ref){

		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			
			sql = "Select * from keyType where type='"+type+"' and companyPrefix="+companyPrefix+" and ref="+ref;
			
			stmt.execute(sql);
			ResultSet rs=stmt.getResultSet();
			
			int row=0;
			if(rs.last()){
				row=rs.getRow();
			}
			
			if(row == 0){
				//type varchar(100),companyPrefix int, ref int, assigned int
				sql = "insert into keyType (type, companyPrefix, ref, assigned)  values('"+type+"', "+companyPrefix+", "+ref+", "+0+")";

				stmt.executeUpdate(sql);
				System.out.println("gtin inserted");
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
	
	//"CREATE TABLE assignment (id int AUTO_INCREMENT PRIMARY KEY, "
	//+ "slaveId varchar(100),type varchar(100), idFrom varchar(100), idTo varchar(100),"
	//+ "reqIp varchar(100), reqTime  TIMESTAMP)";
	
	public void insertIntoAssignment(String slaveId, String type, String idFrom,  String idTo, String reqIp, Timestamp reqTime ){

		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			
			
			sql = "insert into assignment (slaveId, type, idFrom, idTo, reqIp, reqTime)  "
					+ "values('"+slaveId+"','"+type+"', '"+idFrom+"', '"+idTo+"','"+reqIp+"', '"+reqTime+"')";

			stmt.executeUpdate(sql);
			System.out.println("assignment inserted");
				
			
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
	public String selectFromAssignment(){
        String result="";
		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql;
			
			sql = "Select * from assignment";
			
			stmt.execute(sql);
			ResultSet rs=stmt.getResultSet();//(slaveId, type, idFrom, idTo, reqIp, reqTime) 
			while (rs.next ()){
				result+= String.format("%-15s", rs.getString("slaveId"));
				result+= String.format("%-7s", rs.getString("type"));
				result+= String.format("%-35s", rs.getString("idFrom"));
				result+= String.format("%-35s", rs.getString("idTo"));
				result+= String.format("%-15s", rs.getString("reqIp"));
				result+= String.format("%-15s", rs.getTimestamp("reqTime"));
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
	public void deleteKeyType(String type, int companyPrefix, int ref){

		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			
			sql = "Select * from keyType where type='"+type+"' and companyPrefix="+companyPrefix+" and ref="+ref;
			
			stmt.execute(sql);
			ResultSet rs=stmt.getResultSet();
			
			int row=0;
			if(rs.last()){
				row=rs.getRow();
			}
			
			if(row > 0){
				//type varchar(100),companyPrefix int, ref int, assigned int
				sql = "delete from keyType where type='"+type+"' and companyPrefix="+companyPrefix+" and ref="+ref;

				stmt.executeUpdate(sql);
				System.out.println("deleted");
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

	public void dropTables(){

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

	public void createTables(){

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
					+ "type varchar(100),companyPrefix int, ref int, assigned int)";
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

	public void dropDatabase(){

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
	
	public boolean isIdExist(String id){
		
		boolean existance=false;
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql;
			sql = "Select * from slave where id='"+id+"'";

			stmt.execute(sql);
			ResultSet rs=stmt.getResultSet();
			while (rs.next ()){
				existance=true;
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
		
		return existance;
	}
}

