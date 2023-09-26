package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class board {
    static Scanner scan = new Scanner(System.in);
    static AddView addView = new AddView();
    static  AddressRepository addressRepository = new AddressRepository();
    public static void main(String[] args) {


        while(true) {

            System.out.print("명령어를 입력해주세요 : ");
            String cmd = scan.nextLine();

            if(cmd.equals("add")) {
                add();
            } else if(cmd.equals("list")) {
                list();
            } else if(cmd.equals("update")) {
                update();
            } else if(cmd.equals("exit")) {
                System.out.println("프로그램 종료.");
                break;
            }
        }

    }

    static void list() {
        ArrayList<Address> addressList = addressRepository.getAllAddresses();
        addView.printAddresses(addressList);
    }

    static void add() {
        Connection conn = null;

        System.out.print("이름 : ");
        String name = scan.nextLine();
        System.out.print("주소 : ");
        String address = scan.nextLine();
        System.out.print("전화번호 : ");
        String phone = scan.nextLine();

        Statement stmt = null; // SQL 전송하는 객체

        try {
            conn = addressRepository.getConnection();
            stmt = conn.createStatement();

            String sql = String.format("INSERT INTO add_book SET `name` = '%s', `address` = '%s', `phone` = '%s'", name, address, phone);
            stmt.executeUpdate(sql);
        }

        catch(Exception e) {
            e.printStackTrace();  // 에러를 더 자세히 알려줌
        } finally {
            try {
                if(stmt != null){
                    stmt.close();
                }if(conn != null){
                    conn.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    static void update(){

    }


}