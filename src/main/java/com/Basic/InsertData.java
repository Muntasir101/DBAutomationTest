package com.Basic;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class InsertData {
    Connection connect=null;
    String sqldb_url="jdbc:mysql://localhost:3307/dockerDB";
    String sqldb_userName="root";
    String sqldb_userPass="my-secret-pw";

    @BeforeTest
    public void setup() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String dbClass="com.mysql.cj.jdbc.Driver";
        Class.forName(dbClass).newInstance();
    }

    @Test
    public void setTableData() throws SQLException {
        connect= DriverManager.getConnection(sqldb_url,sqldb_userName,sqldb_userPass);
        PreparedStatement insert=connect.prepareStatement("INSERT INTO `Users` (`id`, `Name`, `Email`, `City`) VALUES ('4', 'Rock', 'rock@gmail.com', 'London')");


        insert.execute();
        System.out.println("Successfully data insert");
    }

    @AfterTest
    public void closeTest() throws SQLException {
        if(connect!=null){
            connect.close();
        }
    }

}
