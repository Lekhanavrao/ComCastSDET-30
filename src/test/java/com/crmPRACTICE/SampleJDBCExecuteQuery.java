package com.crmPRACTICE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class SampleJDBCExecuteQuery {
	@Test
	public void sampleJDBCExecutequery() throws Throwable
	{
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","root");
		Statement state = con.createStatement();
		ResultSet result = state.executeQuery("select * from student");
		while(result.next())
		{
			System.out.println(result.getString(1)+" "+ result.getString(2)+" "+ result.getString(3));
		}
		con.close();
	}

}
 