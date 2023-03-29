package com.muhammetkarakurt.util;

import java.sql.*;

public class JDBCHelper {

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBCConstant.URL, JDBCConstant.USERNAME , JDBCConstant.PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static void closeConnection(Connection connection) throws SQLException {
        if(connection != null){
            connection.close();
            System.out.println("\nConnection kapatildi");
        }
    }
    public static void closePreparedStatement(PreparedStatement stm) throws SQLException {
        if(stm != null){
            stm.close();
            System.out.println("PreparedStatement kapatildi");
        }
    }

    public static void closeResultSet(ResultSet rst) throws SQLException {
        if(rst != null){
            rst.close();
            System.out.println("ResultSet kapatildi");
        }
    }
}
