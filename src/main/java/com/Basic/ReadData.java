package com.Basic;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class ReadData {
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
    public void getTableData() throws SQLException {
        connect= DriverManager.getConnection(sqldb_url,sqldb_userName,sqldb_userPass);
        PreparedStatement pstmt=connect.prepareStatement("SELECT * from Users");
        ResultSet res=pstmt.executeQuery();

        System.out.println("id "+ "Name "+ "Email "+ "City");

        while (res.next()){
            System.out.println(String.format("%s - %s - %s - %s",
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4)
                    ));
        }
    }

    @AfterTest
    public void closeTest() throws SQLException {
        if(connect!=null){
            connect.close();
        }
    }

}
