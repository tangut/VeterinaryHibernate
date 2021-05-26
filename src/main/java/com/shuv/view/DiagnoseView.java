package com.shuv.view;

import com.shuv.Utils.ValidationUtils;
import com.shuv.dao.DiagnoseDao;
import com.shuv.dao.MedicinesDao;
import com.shuv.model.Diagnose;
import com.shuv.model.Medicines;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class DiagnoseView implements ViewInterface {

    private DiagnoseDao diagnoseDao = new DiagnoseDao();

    @SneakyThrows
    public void parseCommand() {
        Scanner in = new Scanner(System.in);
        System.out.println("Choose mode:");
        System.out.println("1 - show all diagnoses.");
        System.out.println("2 - find diagnose by ID.");
        System.out.println("3 - edit information about diagnose.");
        System.out.println("4 - add medicines to diagnose.");
        System.out.println("5 - create diagnose.");
        System.out.println("6 - delete diagnose.");
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
                showAllDiagnoses();
                break;
            }
            case 2:{
                findByID();
                break;
            }
            case 3:{
                editDiagnose();
                break;
            }
            case 4:{
                addMedicines();
                break;
            }
            case 5:{
                createDiagnose();
                break;
            }
            case 6:{
                deleteDiagnose();
                break;
            }
        }
    }

    @SneakyThrows
    public void showAllDiagnoses(){
        List<Diagnose> diagnoseList = diagnoseDao.findAll();
        if (diagnoseList.isEmpty()){
            System.out.println("Diagnose list is empty.");
        }
        else{
            for (Diagnose diagnose:diagnoseList){
                System.out.println(serializer.writeValueAsString(diagnose));
            }
        }
    }

    @SneakyThrows
    public void findByID(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of diagnose.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        Diagnose diagnose = diagnoseDao.findById(Integer.parseInt(id));
        if (diagnose == null){
            System.out.println("Diagnose does not exists");
        }
        else{
            List<Diagnose> diagnoseList = diagnoseDao.findAll();
            for (Diagnose diagnose1:diagnoseList){
                if (diagnose1.getId() == Integer.valueOf(id)){
                    System.out.println(serializer.writeValueAsString(diagnose1));
                }
            }
        }
    }

    public void editDiagnose(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of diagnose.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        Diagnose diagnose = diagnoseDao.findById(Integer.parseInt(id));
        if (diagnose == null){
            System.out.println("Diagnose does not exists");
        }
        else{
            System.out.println("Enter new name:");
            String name = in.nextLine();
            while (!ValidationUtils.isValidName(name)){
                System.out.println("Enter correct name.");
                name = in.nextLine();
            }
            System.out.println("Enter new simptomes:");
            String simptomes = in.nextLine();
            while (!ValidationUtils.isValidName(simptomes)){
                System.out.println("Enter correct simptomes.");
                simptomes = in.nextLine();
            }
            diagnose.setName(name);
            diagnose.setSimptome(simptomes);
            diagnoseDao.update(diagnose);
            System.out.println("Diagnose was changed.");
        }
    }

    public void addMedicines(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of diagnose.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        Diagnose diagnose = diagnoseDao.findById(Integer.parseInt(id));
        if (diagnose == null){
            System.out.println("Diagnose does not exists");
        }
        else{
            MedicinesDao medicinesDao = new MedicinesDao();
            System.out.println("Enter description of medicines:");
            String description = in.nextLine();
            while (!ValidationUtils.isValidName(description)){
                System.out.println("Enter correct data.");
                description = in.nextLine();
            }
            Medicines medicines = medicinesDao.findByDescription(description);
            if (medicines == null){
                medicines = new Medicines(description);
                medicinesDao.save(medicines);
                Set<Medicines> medicinesSet = diagnose.getMedicinesSet();
                medicinesSet.add(medicines);
                diagnose.setMedicinesSet(medicinesSet);
            }
            else{
                Set<Medicines> medicinesSet = diagnose.getMedicinesSet();
                medicinesSet.add(medicines);
                diagnose.setMedicinesSet(medicinesSet);
            }
            diagnoseDao.update(diagnose);
            System.out.println("Medicine for diagnose was added.");
        }
    }

    public void createDiagnose(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter new name:");
        String name = in.nextLine();
        while (!ValidationUtils.isValidName(name)){
            System.out.println("Enter correct name.");
            name = in.nextLine();
        }
        System.out.println("Enter new simptomes:");
        String simptomes = in.nextLine();
        while (!ValidationUtils.isValidName(simptomes)){
            System.out.println("Enter correct simptomes.");
            simptomes = in.nextLine();
        }
        Diagnose diagnose = new Diagnose(name, simptomes);
        diagnoseDao.save(diagnose);
        System.out.println("Diagnose was saved.");
    }

    public void deleteDiagnose(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of diagnose.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        Diagnose diagnose = diagnoseDao.findById(Integer.parseInt(id));
        if (diagnose == null){
            System.out.println("Diagnose does not exists");
        }
        else{
            diagnoseDao.delete(diagnose);
            System.out.println("Diagnose was deleted.");
        }
    }
}
