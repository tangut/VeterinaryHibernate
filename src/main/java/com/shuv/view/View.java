package com.shuv.view;

import com.shuv.service.AuthService;

import java.util.Scanner;

public class View implements ViewInterface {

    public static final View intsance = new View();
    private final AuthService authService = new AuthService();
    private int page = 0;

    public void execute(){
        while (true){
            parseCommand();
        }
    }

    @Override
    public void parseCommand() {
        switch (page){
            case 0:{
                baseParseCommand();
            }
        }
    }

    public void baseParseCommand(){
        if (authService.isAuth()){
            actionAuthUser();
        }
        else{
            enterNotAuthUser();
        }
    }

    public void login(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your login:");
        String login = in.nextLine();
        System.out.println("Enter your password:");
        String password = in.nextLine();
        authService.login(login, password);
    }

    public void signUp(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your login:");
        String login = in.nextLine();
        System.out.println("Enter your name:");
        String name = in.nextLine();
        System.out.println("Enter your password:");
        String password = in.nextLine();
        System.out.println("Enter your age:");
        int age = in.nextInt();
        authService.signUp(login, name, age, password);
    }

    public void logout(){
        authService.logout();
    }

    public void enterNotAuthUser(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter command:");
        System.out.println("1 - login");
        System.out.println("2 - signUp");
        System.out.println("3 - exit from application");
        int mode = in.nextInt();
        switch (mode){
            case 1 : {
                login();
                break;
            }
            case 2 : {
                signUp();
                break;
            }
            case 3 : {
                System.exit(0);
                break;
            }
            default:{
                System.out.println("Incorrect data!");
            }
        }
    }

    public void actionAuthUser(){
        System.out.println("Soon");
        System.exit(0);
    }
}
