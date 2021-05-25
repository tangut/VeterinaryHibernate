package com.shuv.view;

import com.shuv.Utils.ValidationUtils;
import com.shuv.dao.AddressDao;
import com.shuv.dao.PetDao;
import com.shuv.dao.UserDao;
import com.shuv.model.Address;
import com.shuv.model.Pet;
import com.shuv.model.User;
import com.shuv.service.AuthService;
import lombok.SneakyThrows;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserView implements ViewInterface {

    private UserDao userDao = new UserDao();

    @SneakyThrows
    @Override
    public void parseCommand() {
        System.out.println("Select mode:");
        System.out.println("1 - show all users.");
        System.out.println("2 - find user by ID.");
        System.out.println("3 - edit user.");
        System.out.println("4 - add user's address.");
        System.out.println("5 - delete user.");
        System.out.println("0 - exit to main page.");
        Scanner in = new Scanner(System.in);
        int mode = in.nextInt();
        switch (mode){
            case 0:{
                View.viewInstance.goToMain();
                break;
            }
            case 1:{
                showAllUsers();
                break;
            }
            case 2:{
                showUserById();
                break;
            }
            case 3:{
                editUserInfo();
                break;
            }
            case 4:{
                editUserAddress();
                break;
            }
            case 5:{
                deleteUser();
                break;
            }
            default:{
                System.out.println("Incorrect data!");
                break;
            }
        }
    }

    @SneakyThrows
    public void showAllUsers() {
        List<User> userList = userDao.findAll();
        if (userList.isEmpty()){
            System.out.println("List of users is empty");
        }
        else{
            System.out.println("List of users:");
            for (User user:userList){
                System.out.println(serializer.writeValueAsString(user));
            }
        }
    }

    @SneakyThrows
    public void showUserById(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of user.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        User user = userDao.findById(Integer.parseInt(id));
        if (user == null){
            System.out.println("User does not exist!");
        }
        else {
            System.out.println(serializer.writeValueAsString(user));
        }
    }

    public void editUserInfo(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of user.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        User user = userDao.findById(Integer.parseInt(id));
        if (user == null){
            System.out.println("User does not exist!");
            return;
        }
        System.out.println("Enter new login of this user:");
        String login = in.nextLine();
        System.out.println("Enter new name of this user:");
        String name = in.nextLine();
        System.out.println("Enter new age of this user:");
        String age = in.nextLine();
        while (!ValidationUtils.isDigit(age)){
            System.out.println("Enter correct digit value of age.");
            age = in.nextLine();
        }
        System.out.println("Enter new password:");
        String password = in.nextLine();
        while (!ValidationUtils.isValidPassword(password)){
            System.out.println("Enter correct password, it's length must be more than 4 symbols.");
            password = in.nextLine();
        }
        String hashPassword = DigestUtils.sha256Hex(password).toString();
        user.setName(name);
        user.setLogin(login);
        user.setAge(Integer.parseInt(age));
        user.setPassword(hashPassword);
        userDao.update(user);
        System.out.println("User was changed.");
    }

    public void editUserAddress(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of user.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        User user = userDao.findById(Integer.parseInt(id));
        if (user == null){
            System.out.println("User does not exist!");
            return;
        }
        Address userAddres = user.getAddress();
        AddressDao addressDao = new AddressDao();
        if (userAddres == null){
            System.out.println("Enter city name:");
            String city = in.nextLine();
            System.out.println("Enter street name:");
            String street = in.nextLine();
            System.out.println("Enter building:");
            String building = in.nextLine();
            Address address = addressDao.findByFullAddres(city, street, building);
            if (address != null){
                user.setAddress(address);
            }
            else{
                address = new Address(city, street, building);
                addressDao.save(address);
                user.setAddress(address);
            }
            userDao.update(user);
            System.out.println("User address was changed");
        }
        if (userAddres != null){
            Address address = addressDao.findByFullAddres(userAddres.getCity(), userAddres.getStreet(), userAddres.getBuilding());
            System.out.println("Enter city name:");
            String city = in.nextLine();
            System.out.println("Enter street name:");
            String street = in.nextLine();
            System.out.println("Enter building:");
            String building = in.nextLine();
            userAddres.setCity(city);
            userAddres.setStreet(street);
            userAddres.setBuilding(building);
            user.setAddress(userAddres);
            address.setCity(city);
            address.setBuilding(building);
            address.setStreet(street);
            addressDao.update(address);
            userDao.update(user);
            System.out.println("User address was changed.");
        }
    }

    public void deleteUser(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of user.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        User user = userDao.findById(Integer.parseInt(id));
        if (user == null){
            System.out.println("User does not exist!");
            return;
        }
        PetDao petDao = new PetDao();
        ArrayList<Pet> petArrayList = petDao.findByMasterID(user.getId());
        if (!petArrayList.isEmpty()) {
            for (Pet pet : petArrayList) {
                petDao.delete(pet);
            }
        }
        userDao.delete(user);
        System.out.println("User was deleted.");
    }
}
