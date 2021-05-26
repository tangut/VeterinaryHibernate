package com.shuv.view;

import com.shuv.Utils.ValidationUtils;
import com.shuv.dao.AddressDao;
import com.shuv.model.Address;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Scanner;

public class AddressView implements ViewInterface {

    private AddressDao addressDao = new AddressDao();

    @Override
    @SneakyThrows
    public void parseCommand()  {
        Scanner in = new Scanner(System.in);
        System.out.println("Choose mode:");
        System.out.println("1 - show all addresses.");
        System.out.println("2 - find address by ID.");
        System.out.println("3 - edit address.");
        System.out.println("4 - delete address.");
        System.out.println("0 - exit to main page.");
        int mode = in.nextInt();
        switch (mode){
            default:{
                System.out.println("Incorrect data.");
                break;
            }
            case 0:{
                View.viewInstance.goToMain();
                break;
            }
            case 1:{
                showAllAddresses();
                break;
            }
            case 2:{
                findAddressByID();
                break;
            }
            case 3:{
                editAddress();
                break;
            }
            case 4:{
                deleteAddress();
                break;
            }
        }
    }

    @SneakyThrows
    public void showAllAddresses(){
        List<Address> addressList = addressDao.findAll();
        if (addressList.isEmpty()){
            System.out.println("No addresses.");
        }
        else{
            System.out.println("List of addresses:");
            for (Address address:addressList){
                System.out.println(serializer.writeValueAsString(address));
            }
        }
    }

    @SneakyThrows
    public void findAddressByID(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of address.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        List<Address> addressList = addressDao.findAll();//жуткий костыль, но Jackson себя непонятно вел при поиске по ID напрямую
        if (addressList.isEmpty()){
            System.out.println("Address does not exist!");
        }
        else{
            for (Address address:addressList){
                if(address.getId() == Integer.valueOf(id)){
                    System.out.println(serializer.writeValueAsString(address));
                }
            }
        }
    }

    public void editAddress(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of address.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        Address address = addressDao.findById(Integer.valueOf(id));
        System.out.println("Enter new city:");
        String city = in.nextLine();
        while (!ValidationUtils.isValidName(city)){
            System.out.println("Enter correct name.");
            city = in.nextLine();
        }
        System.out.println("Enter new street.");
        String street = in.nextLine();
        while (!ValidationUtils.isValidName(street)){
            System.out.println("Enter correct name.");
            street = in.nextLine();
        }
        System.out.println("Enter new building name.");
        String building = in.nextLine();
        while (!ValidationUtils.isValidName(building)){
            System.out.println("Enter correct name.");
            building = in.nextLine();
        }
        address.setCity(city);
        address.setBuilding(building);
        address.setStreet(street);
        addressDao.update(address);
        System.out.println("Address was changed");
    }

    public void deleteAddress(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of address.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        Address address = addressDao.findById(Integer.valueOf(id));
        addressDao.delete(address);
        System.out.println("Address was deleted.");
    }
}
