package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class board {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {


        while(true) {

            System.out.print("명령어를 입력해주세요 : ");
            String cmd = scan.nextLine();

            if(cmd.equals("add")) {
                add();
            } else if(cmd.equals("list")) {
                list();
            } else if(cmd.equals("update")) {

            } else if(cmd.equals("exit")) {
                System.out.println("프로그램 종료.");
                break;
            }
        }

    }

    static void list() {
        Statement stmt = null; // SQL 전송하는 객체
        ResultSet rs = null;

        try {

            Connection conn = getConnection();
            stmt = conn.createStatement();
            String sql = String.format("SELECT * FROM add_book");
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");

                System.out.printf("번호 : %d\n", id);
                System.out.printf("이름 : %s\n", name);
                System.out.printf("주소 : %s\n", address);
                System.out.printf("전화번호 : %s\n", phone);
                System.out.println("===========================");
            }


        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    static void add() {

        System.out.print("이름 : ");
        String name = scan.nextLine();
        System.out.print("주소 : ");
        String address = scan.nextLine();
        System.out.print("전화번호 : ");
        String phone = scan.nextLine();

        Statement stmt = null; // SQL 전송하는 객체

        try {
            Connection conn = getConnection();
            stmt = conn.createStatement();

            String sql = String.format("INSERT INTO add_book SET `name` = '%s', `address` = '%s', `phone` = '%s'", name, address, phone);
            stmt.executeUpdate(sql);

        } catch(Exception e) {
            e.printStackTrace();  // 에러를 더 자세히 알려줌
        }
    }

    static Connection getConnection() {

        Connection conn = null; // DB 접속하는 객체

        String url = "jdbc:mysql://localhost:3306/ad?serverTimezone=UTC";
        String user = "root";
        String pass = "";

        try {
            // 1. 드라이버 세팅
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connection 획득
            conn = DriverManager.getConnection(url, user, pass);

        } catch(Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}