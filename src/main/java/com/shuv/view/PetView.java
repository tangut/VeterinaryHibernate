package com.shuv.view;

import com.shuv.Utils.ValidationUtils;
import com.shuv.dao.DiagnoseDao;
import com.shuv.dao.PetDao;
import com.shuv.dao.UserDao;
import com.shuv.model.Diagnose;
import com.shuv.model.Pet;
import com.shuv.model.User;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Scanner;

public class PetView implements ViewInterface {
    private final PetDao petDao = new PetDao();
    @Override
    @SneakyThrows
    public void parseCommand() {
        Scanner in = new Scanner(System.in);
        System.out.println("Select mode:");
        System.out.println("1 - show all pets.");
        System.out.println("2 - find pet by ID.");
        System.out.println("3 - edit pet.");
        System.out.println("4 - create pet.");
        System.out.println("5 - add diagnose to pet");
        System.out.println("6 - delete pet.");
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
                showAllPets();
                break;
            }
            case 2:{
                findPetById();
                break;
            }
            case 3:{
                editPet();
                break;
            }
            case 4:{
                createPet();
                break;
            }
            case 5:{
                addDiagnose();
                break;
            }
            case 6:{
                deletePet();
                break;
            }
        }
    }

    @SneakyThrows
    public void showAllPets(){
        List<Pet> petList = petDao.findAll();
        if (petList.isEmpty()){
            System.out.println("List of pets is empty.");
        }
        else{
            for (Pet pet:petList){
                System.out.println(serializer.writeValueAsString(pet));
            }
        }
    }

    @SneakyThrows
    public void findPetById(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of pet.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        Pet pet = petDao.findById(Integer.parseInt(id));
        if (pet == null){
            System.out.println("Pet does not exist.");
        }
        else{
            System.out.println(serializer.writeValueAsString(pet));
        }
    }

    public void editPet(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of pet.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        Pet pet = petDao.findById(Integer.parseInt(id));
        if (pet == null){
            System.out.println("Pet does not exist.");
        }
        else{
            System.out.println("Enter new name.");
            String name = in.nextLine();
            while (!ValidationUtils.isValidName(name)){
                System.out.println("Enter correct data.");
                name = in.nextLine();
            }
            System.out.println("Enter new kind.");
            String kind = in.nextLine();
            while (!ValidationUtils.isValidName(kind)){
                System.out.println("Enter correct data.");
                kind = in.nextLine();
            }
            System.out.println("Enter new breed");
            String breed = in.nextLine();
            while (!ValidationUtils.isValidName(breed)){
                System.out.println("Enter correct data.");
                breed = in.nextLine();
            }
            pet.setName(name);
            pet.setBreed(breed);
            pet.setKind(kind);
            petDao.update(pet);
            System.out.println("Pet was updated.");
        }
    }

    public void createPet(){
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
            System.out.println("Enter name of pet.");
            String name = in.nextLine();
            while (!ValidationUtils.isValidName(name)){
                System.out.println("Enter correct data.");
                name = in.nextLine();
            }
            System.out.println("Enter kind of pet.");
            String kind = in.nextLine();
            while (!ValidationUtils.isValidName(kind)){
                System.out.println("Enter correct data.");
                kind = in.nextLine();
            }
            System.out.println("Enter breed of pet");
            String breed = in.nextLine();
            while (!ValidationUtils.isValidName(breed)){
                System.out.println("Enter correct data.");
                breed = in.nextLine();
            }
            Pet pet = new Pet(name, kind, breed);
            pet.setMaster(user);
            petDao.save(pet);
            System.out.println("Pet was saved.");
        }
    }

    public void addDiagnose(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of pet.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        Pet pet = petDao.findById(Integer.parseInt(id));
        if (pet == null){
            System.out.println("Pet does not exist.");
        }
        else{
            System.out.println("Enter name of diagnose:");
            String name = in.nextLine();
            while (!ValidationUtils.isValidName(name)){
                System.out.println("Enter correct data.");
                name = in.nextLine();
            }
            System.out.println("Enter simptomes:");
            String simptome = in.nextLine();
            while (!ValidationUtils.isValidName(simptome)){
                System.out.println("Enter correct data.");
                simptome = in.nextLine();
            }
            DiagnoseDao diagnoseDao = new DiagnoseDao();
            Diagnose diagnose = diagnoseDao.findByNameSimptome(name, simptome);
            if (diagnose == null){
                diagnose = new Diagnose(name, simptome);
                diagnoseDao.save(diagnose);
                pet.setDiagnose(diagnose);
            }
            else{
                pet.setDiagnose(diagnose);
            }
            petDao.update(pet);
            System.out.println("Diagnose was added to pet.");
        }
    }

    public void deletePet(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of pet.");
        String id = in.nextLine();
        while (!ValidationUtils.isDigit(id)){
            System.out.println("Enter correct data.");
            id = in.nextLine();
        }
        Pet pet = petDao.findById(Integer.parseInt(id));
        if (pet == null){
            System.out.println("Pet does not exist.");
        }
        else{
            petDao.delete(pet);
            System.out.println("Pet was deleted.");
        }
    }

}
