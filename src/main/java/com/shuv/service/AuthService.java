package com.shuv.service;

import com.shuv.dao.UserDao;
import com.shuv.model.User;
import org.apache.commons.codec.digest.DigestUtils;

public class AuthService {

    private User currentUser;

    private final UserDao userDao = new UserDao();

    public void login(String login, String password){
        User user = userDao.findByLogin(login);
        if (user == null){
            System.out.println("Incorrect data.");
            return;
        }
        String sha256hex = DigestUtils.sha256Hex(password);
        if (user.getPassword().equals(sha256hex)){
            currentUser = user;
            System.out.println("Successful login!");
        }
        else{
            System.out.println("Incorrect data");
            return;
        }
    }

    public void logout(){
        currentUser = null;
    }

    public void signUp(String login, String name, int age, String password){
        User user = userDao.findByLogin(login);
        if (user != null){
            System.out.println("Please, enter another login.");
            return;
        }
        if (name.isEmpty()) {
            System.out.println("Name must be not empty!");
            return;
        }
        if (age < 12) {
            System.out.println("You are too young for our application!");
            return;
        }
        if (password.length() < 5) {
            System.out.println("Password length must be more than 4 symbols");
            return;
        }
        String hashPassword = DigestUtils.sha256Hex(password).toString();
        User addedUser = new User(name, age, hashPassword, login);
        userDao.save(addedUser);
    }

    public Boolean isAuth() {
        return currentUser != null;
    }
}
