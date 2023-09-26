package org.example;

import java.util.ArrayList;

public class AddView {
    public void printAddresses(ArrayList<Address> addressList){

        for(Address address : addressList) {
            System.out.printf("번호 : %d\n", address.getId());
            System.out.printf("이름 : %s\n", address.getName());
            System.out.printf("주소 : %s\n", address.getAddress());
            System.out.printf("전화번호 : %s\n", address.getPhone());
            System.out.println("===========================");
        }
    }
}
