package com.weather.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class CityDBUtil {
	private DataSource dataSource;
	
	public CityDBUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<String> getCity(String username) throws SQLException {
		List<String> cityList = new ArrayList<String>();
		Connection myConn = null;
        PreparedStatement mystm = null;
        ResultSet myrs = null;
        try {
            myConn = dataSource.getConnection();
            String sql = "SELECT * FROM city_list WHERE (username=?)";
            mystm = myConn.prepareStatement(sql);
            mystm.setString(1, username);
            myrs = mystm.executeQuery();
            while(myrs.next())
            {
            	String cityname= myrs.getString("city_name");
            	cityList.add(cityname);
            }
            
        } finally {
            close(myConn, mystm, myrs);
        }
		return cityList;
	}
	 private void close(Connection myConn, PreparedStatement myStm, ResultSet myrs) {
	        try {
	            if (myrs != null) {
	                myrs.close();
	            }
	            if (myStm != null) {
	                myStm.close();
	            }
	            if (myConn != null) {
	                myConn.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
}
