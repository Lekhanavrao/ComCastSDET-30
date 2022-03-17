package com.crmPRACTICE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class SampleJDBCExecuteUpdate {
	@Test
	public void sampleJDBCExecuteUpdate() throws Throwable
	{ 
		Connection con=null;
		try
		{
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
	    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","root");
	    System.out.println("connection established");
		Statement state = con.createStatement();
		//insert into student values('3','Lekhana','Mandya')
		//;
		//rename table student to studentinfo;
		int result = state.executeUpdate("UPDATE studentinfo SET student_id=2 where lower(student_name)='lekhana';");
		if(result==1)
		{
			System.out.println("data added successfully");
		}
		else
		{
			System.out.println("data not added"); 
		}
		}
		catch(Exception e)
		{
			
		}
		finally
		{
		con.close();
		System.out.println("connection closed");
	    }
	}

}
