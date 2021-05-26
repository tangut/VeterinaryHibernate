package com.shuv.view;

import com.shuv.Utils.ValidationUtils;
import com.shuv.dao.MonkeyDao;
import com.shuv.dao.UserDao;
import com.shuv.model.Monkey;
import com.shuv.model.User;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Scanner;

public class MonkeyView implements ViewInterface {
    private final MonkeyDao monkeyDao = new MonkeyDao();

    @SneakyThrows
    @Override
    public void parseCommand() {
        Scanner in = new Scanner(System.in);
        System.out.println("Select mode:");
        System.out.println("1 - show all monkeys.");
        System.out.println("2 - find monkey by ID.");
        System.out.println("3 - edit monkey.");
        System.out.println("4 - create monkey.");
        System.out.println("5 - delete monkey.");
        System.out.println("0 - exit to main page.");
        int mode = in.nextInt();
        switch (mode) {
            default: {
                System.out.println("Incorrect data.");
                break;
            }
            case 0: {
                View.viewInstance.goToMain();
                break;
            }
            case 1:{
                showAllMonkeys();
                break;
            }
            case 2:{
                findMonkeyById();
                break;
            }
            case 3:{
                editMonkey();
                break;
            }
            case 4:{
                createMonkey();
                break;
            }
            case 5:{
                deleteMonkey();
                break;
            }
        }
    }

    @SneakyThrows
    public void showAllMonkeys(){
        List<Monkey> monkeyList = monkeyDao.findAll();
        if (monkeyList.isEmpty()){
            System.out.println("List of monkeys is empty.");
        }
        else{
            for (Monkey monkey:monkeyList){
                System.out.println(serializer.writeValueAsString(monkey));
            }
        }
    }

    @SneakyThrows
    public void findMonkeyById(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of monkey.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        Monkey monkey = monkeyDao.findById(Integer.parseInt(id));
        if (monkey == null){
            System.out.println("Monkey does not exist.");
        }
        else{
            System.out.println(serializer.writeValueAsString(monkey));
        }
    }

    public void editMonkey(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of monkey.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        Monkey monkey = monkeyDao.findById(Integer.parseInt(id));
        if (monkey == null){
            System.out.println("Monkey does not exist.");
        }
        else{
            System.out.println("Enter new name of monkey:");
            String name = in.nextLine();
            while (!ValidationUtils.isValidName(name)){
                System.out.println("Enter correct data.");
                name = in.nextLine();
            }
            System.out.println("Enter new breed of monkey:");
            String breed = in.nextLine();
            while (!ValidationUtils.isValidName(breed)){
                System.out.println("Enter correct data.");
                breed = in.nextLine();
            }
            System.out.println("Shaman status of monkey? y/n");
            String shaman = in.nextLine();
            while (!(shaman.equals("y") || shaman.equals("n"))){
                System.out.println("Please, answer y or n.");
                shaman = in.nextLine();
            }
            boolean isShaman = false;
            if (shaman.equals("y")){
                isShaman = true;
            }
            monkey.setBreed(breed);
            monkey.setName(name);
            monkey.setShaman(isShaman);
            monkeyDao.update(monkey);
            System.out.println("Monkey is updated.");
        }
    }

    public void createMonkey(){
        Scanner in = new Scanner(System.in);
        UserDao userDao = new UserDao();
        System.out.println("Enter id of master of this pet.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        User user = userDao.findById(Integer.parseInt(id));
        if (user == null){
            System.out.println("User does not exist.");
            return;
        }
        else{
            System.out.println("Enter name of monkey.");
            String name = in.nextLine();
            while (!ValidationUtils.isValidName(name)){
                System.out.println("Enter correct data.");
                name = in.nextLine();
            }
            String kind = "monkey";
            System.out.println("Enter breed of monkey.");
            String breed = in.nextLine();
            while (!ValidationUtils.isValidName(breed)){
                System.out.println("Enter correct data.");
                breed = in.nextLine();
            }
            System.out.println("Shaman status of monkey? y/n");
            String shaman = in.nextLine();
            while (!(shaman.equals("y") || shaman.equals("n"))){
                System.out.println("Please, answer y or n.");
                shaman = in.nextLine();
            }
            boolean isShaman = false;
            if (shaman.equals("y")){
                isShaman = true;
            }
            Monkey monkey = new Monkey(name, kind, breed, isShaman, user);
            monkeyDao.save(monkey);
            System.out.println("Monkey was saved.");
        }
    }

    public void deleteMonkey(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of monkey.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        Monkey monkey = monkeyDao.findById(Integer.parseInt(id));
        if (monkey == null){
            System.out.println("Monkey does not exist.");
        }
        else{
            monkeyDao.delete(monkey);
            System.out.println("Monkey was deleted.");
        }
    }
}
