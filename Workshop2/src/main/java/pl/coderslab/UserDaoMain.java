package pl.coderslab;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

public class UserDaoMain {
    public static void main(String[] args) {
       /* User user = new User("Jules Verne", "jv@nautilus.org", "mobilisinmobili");
        UserDao userDao = new UserDao();
        userDao.create(user);
        System.out.println(user.getId());*/
       /* UserDao userDao = new UserDao();
        User user = userDao.read(1);
        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());*/
       /* UserDao userDao = new UserDao();
        User user = userDao.read(1);
        user.setUserName("James T Kirk");
        user.setEmail("ncc1701@starfleet.org");
        user.setPassword("Enterprise");
        userDao.update(user);*/
       /* UserDao userDao = new UserDao();
        userDao.delete(1);
    }*/
        UserDao userDao = new UserDao();
        User[] users = userDao.findAll();
        for (User user: users) {
            System.out.println(user.getUserName());
        }
        System.out.println(users.length);
    }
}
