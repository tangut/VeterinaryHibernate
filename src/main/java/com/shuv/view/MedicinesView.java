package com.shuv.view;

import com.shuv.Utils.ValidationUtils;
import com.shuv.dao.MedicinesDao;
import com.shuv.model.Medicines;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Scanner;

public class MedicinesView implements ViewInterface {

    private final MedicinesDao medicinesDao = new MedicinesDao();

    @SneakyThrows
    @Override
    public void parseCommand() {
        Scanner in = new Scanner(System.in);
        System.out.println("Choose mode:");
        System.out.println("1 - show all medicines.");
        System.out.println("2 - find medicine by ID.");
        System.out.println("3 - edit medicine.");
        System.out.println("4 - create medicine.");
        System.out.println("5 - delete medicine.");
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
                showAllMedicines();
                break;
            }
            case 2:{
                showMedicineById();
                break;
            }
            case 3:{
                editMedicines();
                break;
            }
            case 4:{
                createMedicines();
                break;
            }
            case 5:{
                deleteMedicine();
                break;
            }
        }
    }

    @SneakyThrows
    public void showAllMedicines(){
        List<Medicines> medicinesList = medicinesDao.findAll();
        if (medicinesList.isEmpty()){
            System.out.println("Medicines list is empty.");
        }
        else{
            System.out.println("List of medicines:");
            for (Medicines medicines : medicinesList){
                System.out.println(serializer.writeValueAsString(medicines));
            }
        }
    }

    @SneakyThrows
    public void showMedicineById(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of medicine.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        Medicines medicines = medicinesDao.findById(Integer.parseInt(id));
        if (medicines == null){
            System.out.println("Medicines does not exists.");
        }
        else{
            System.out.println(serializer.writeValueAsString(medicines));
        }
    }

    @SneakyThrows
    public void editMedicines(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of medicine.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        Medicines medicines = medicinesDao.findById(Integer.parseInt(id));
        if (medicines == null){
            System.out.println("Medicines does not exists.");
        }
        else{
            System.out.println("Enter description.");
            String description = in.nextLine();
            while(!ValidationUtils.isValidName(description)){
                System.out.println("Enter correct description!");
                description = in.nextLine();
            }
            medicines.setDescription(description);
            medicinesDao.update(medicines);
            System.out.println("Medicine was updated.");
        }
    }

    @SneakyThrows
    public void createMedicines(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter description.");
        String description = in.nextLine();
        while(!ValidationUtils.isValidName(description)){
            System.out.println("Enter correct description!");
            description = in.nextLine();
        }
        Medicines medicines = new Medicines(description);
        medicinesDao.save(medicines);
        System.out.println("Medicine was created.");
    }

    @SneakyThrows
    public void deleteMedicine(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of medicine.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        Medicines medicines = medicinesDao.findById(Integer.parseInt(id));
        medicinesDao.delete(medicines);
        System.out.println("Medicine was deleted");
    }
}
