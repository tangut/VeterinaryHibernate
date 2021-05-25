package com.shuv;


import com.shuv.dao.UserDao;
import com.shuv.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HibernateUtil.getSessionFactory();
        UserDao userDao = new UserDao();
        List<User> users = new ArrayList<>();
        users = userDao.getUserByName("Cox");
        User user = new User();
        user = userDao.getUserByLogin("coxy@gmail.com");
        System.out.println(user);
        String str = "qwerty";
        String strHash = DigestUtils.sha256Hex(str).toString();
        System.out.println(strHash);
    }
}
