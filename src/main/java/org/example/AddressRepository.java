package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AddressRepository {
    public ArrayList<Address> getAllAddresses() {
        Connection conn = null;
        Statement stmt = null; // SQL 전송하는 객체
        ResultSet rs = null;

        ArrayList<Address> addressList = new ArrayList<>();

        try {

            conn = getConnection();
            stmt = conn.createStatement();
            String sql = String.format("SELECT * FROM add_book");
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");

                Address a1 = new Address(id, name, address, phone);
                addressList.add(a1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                // 만들어진순서 역순대로 close
                if (rs != null) {
                    rs.close();
                }
                if(stmt != null) {
                    stmt.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return addressList;
    }

    Connection getConnection() {

        Connection conn = null; // DB 접속하는 객체

        String url = "jdbc:mysql://localhost:3306/ad?serverTimezone=UTC";
        String user = "root";
        String pass = "";

        try {
            // 1. 드라이버 세팅
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connection 획득
            conn = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    public void insert(String name, String address, String phone) {
        Statement stmt = null; // SQL 전송하는 객체
        Connection conn = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            String sql = String.format("INSERT INTO add_book SET `name` = '%s', `address` = '%s', `phone` = '%s'", name, address, phone);
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if(stmt != null) {
                    stmt.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}