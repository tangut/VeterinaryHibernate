package com.shuv;


import com.shuv.dao.AddressDao;
import com.shuv.model.Address;
import com.shuv.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        view.execute();
        /*
        String city = "Saratov";
        String street = "Kirova";
        String building = "8";
        AddressDao addressDao = new AddressDao();
        Address address = addressDao.findByFullAddres(city, street, building);
        System.out.println(address.toString());

         */
    }
}
