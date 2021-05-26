package com.shuv.view;

import com.shuv.Utils.ValidationUtils;
import com.shuv.service.AuthService;

import java.util.Scanner;

public class View implements ViewInterface {

    public static final View viewInstance = new View();
    private final AuthService authService = new AuthService();
    private final UserView userView = new UserView();
    private final AddressView addressView = new AddressView();
    private final MedicinesView medicinesView = new MedicinesView();
    private final DiagnoseView diagnoseView = new DiagnoseView();
    private final PetView petView = new PetView();
    private static int page = 0;

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
                break;
            }
            case 1:{
                userView.parseCommand();
                break;
            }
            case 2:{
                addressView.parseCommand();
                break;
            }
            case 3:{
                medicinesView.parseCommand();
                break;
            }
            case 4:{
                diagnoseView.parseCommand();
            }
            case 5:{
                petView.parseCommand();
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
        String age = in.nextLine();
        while (!ValidationUtils.isDigit(age)){
            System.out.println("Enter digit value of age.");
        }
        authService.signUp(login, name, Integer.parseInt(age), password);
    }

    public void logout(){
        authService.logout();
    }

    public void enterNotAuthUser(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter command:");
        System.out.println("1 - login");
        System.out.println("2 - signUp");
        System.out.println("0 - exit from application");
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
            case 0 : {
                System.exit(0);
                break;
            }
            default:{
                System.out.println("Incorrect data!");
            }
        }
    }

    public void actionAuthUser(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of page, which you want to watch:");
        System.out.println("1 - list of users.");
        System.out.println("2 - list of addresses.");
        System.out.println("3 - list of medicines.");
        System.out.println("4 - list of diagnoses.");
        System.out.println("5 - list of pets.");
        System.out.println("0 - exit the application.");
        int mode = in.nextInt();
        switch (mode){
            case 1:{
                page = 1;
                break;
            }
            case 2:{
                page = 2;
                break;
            }
            case 3:{
                page = 3;
                break;
            }
            case 4:{
                page = 4;
                break;
            }
            case 5:{
                page = 5;
                break;
            }
            case 0:{
                System.exit(0);
            }
            default:{
                System.out.println("Incorrect data!");
                break;
            }
        }
    }

    void goToMain() {
        page = 0;
    }
}
