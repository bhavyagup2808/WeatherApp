package com.weather.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class AppDBUtil {
    private DataSource dataSource;

    public AppDBUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isValidUser(String username, String password) throws SQLException {
        Connection myConn = null;
        PreparedStatement mystm = null;
        ResultSet myrs = null;
        boolean result = false;
        try {
            myConn = dataSource.getConnection();
            String sql = "SELECT * FROM userdetails WHERE username = ? AND user_password = ?";
            mystm = myConn.prepareStatement(sql);
            mystm.setString(1, username);
            mystm.setString(2, password);
            myrs = mystm.executeQuery();
            if (myrs.next()) {
                result = true;
            }
        } finally {
            close(myConn, mystm, myrs);
        }
        return result;
    }

    public boolean isValidUsername(String username) throws SQLException {
        Connection myConn = null;
        PreparedStatement mystm = null;
        ResultSet myrs = null;
        boolean result = false;
        try {
            myConn = dataSource.getConnection();
            String sql = "SELECT * FROM userdetails WHERE username = ?";
            mystm = myConn.prepareStatement(sql);
            mystm.setString(1, username);
            myrs = mystm.executeQuery();
            if (myrs.next()) {
                result = true;
            }
        } finally {
            close(myConn, mystm, myrs);
        }
        return result;
    }

    public boolean isValidPassword(String password) throws SQLException {
        Connection myConn = null;
        PreparedStatement mystm = null;
        ResultSet myrs = null;
        boolean result = false;
        try {
            myConn = dataSource.getConnection();
            String sql = "SELECT * FROM userdetails WHERE user_password = ?";
            mystm = myConn.prepareStatement(sql);
            mystm.setString(1, password);
            myrs = mystm.executeQuery();
            if (myrs.next()) {
                result = true;
            }
        } finally {
            close(myConn, mystm, myrs);
        }
        return result;
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

